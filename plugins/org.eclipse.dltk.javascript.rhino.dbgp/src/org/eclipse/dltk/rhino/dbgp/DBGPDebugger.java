package org.eclipse.dltk.rhino.dbgp;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.WeakHashMap;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJavaArray;
import org.mozilla.javascript.NativeJavaClass;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.UniqueTag;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.debug.DebugFrame;
import org.mozilla.javascript.debug.DebuggableScript;
import org.mozilla.javascript.debug.Debugger;
import org.mozilla.javascript.debug.IDeguggerWithWatchPoints;
import org.mozilla.javascript.xml.XMLObject;

public class DBGPDebugger extends Thread
		implements Debugger, Observer, IDeguggerWithWatchPoints {

	private Socket socket;
	private OutputStream out;
	private HashMap strategies = new HashMap();
	HashMap properties = new HashMap();
	String runTransctionId;

	static abstract class Command {
		abstract void parseAndExecute(String command, HashMap options);
	}

	private static void writeResponseLength(OutputStream out, int value)
			throws IOException {
		out.write(String.valueOf(value).getBytes());
	}

	void printResponse(String response) {
		try {
			byte[] bytes = response.getBytes("UTF-8"); //$NON-NLS-1$
			writeResponseLength(out, bytes.length);
			out.write(0);
			out.write(bytes, 0, bytes.length);
			out.write(0);
			out.flush();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e2) {
				// ignore
			}
		}
	}

	DBGPStackManager stackmanager;
	public boolean isInited;

	public void setContext(Context cx) {
		DBGPStackManager manager = DBGPStackManager.getManager(cx);
		manager.setDebugger(this);
		this.stackmanager = manager;
	}

	public DBGPDebugger(Socket socket, String file, String string, Context ct)
			throws IOException {
		super();
		this.socket = socket;

		stackmanager = DBGPStackManager.getManager(ct);
		stackmanager.suspend();
		out = new BufferedOutputStream(socket.getOutputStream(), 2048);
		stackmanager.setDebugger(this);
		String response = "<init appid=\"APPID\"\r\n" + "      idekey=\""
				+ string + "\"\r\n" + "      session=\"" + string + "\"\r\n"
				+ "      thread=\"THREAD_ID\"\r\n"
				+ "      parent=\"PARENT_APPID\"\r\n"
				+ "      language=\"javascript\"\r\n"
				+ "      protocol_version=\"1.0\"\r\n"
				+ "      fileuri=\"file://" + file + "\"\r\n" + "/>";
		printResponse(response);
		strategies.put("feature_get", new FeatureGetCommand(this));
		strategies.put("feature_set", new FeatureSetCommand(this));
		strategies.put("stdin", new StdInCommand(this));
		strategies.put("stdout", new StdOutCommand(this));
		strategies.put("stderr", new StdErrCommand(this));
		strategies.put("run", new RunCommand(this));
		strategies.put("context_names", new ContextNamesCommand(this));
		strategies.put("stop", new StopCommand(this));
		strategies.put("step_over", new StepOverCommand(this));
		strategies.put("step_into", new StepIntoCommand(this));
		strategies.put("step_out", new StepOutCommand(this));
		strategies.put("breakpoint_get", new GetBreakPointCommand(this));
		strategies.put("breakpoint_set", new SetBreakPointCommand(this));
		strategies.put("breakpoint_remove", new RemoveBreakPointCommand(this));
		strategies.put("breakpoint_update", new UpdateBreakPointCommand(this));
		strategies.put("context_get", new ContextGetCommand(this));
		strategies.put("property_set", new PropertySetCommand(this));
		strategies.put("eval", new EvalCommand(this));
		strategies.put("property_get", new PropertyGetCommand(this));
		strategies.put("break", new BreakCommand(this));
		strategies.put("stack_depth", new StackDepthCommand(this));
		strategies.put("stack_get", new StackGetCommand(this));
	}

	protected void printProperty(String id, String fullName, Object value,
			StringBuffer properties, int level, boolean addChilds) {
		boolean hasChilds = false;

		int numC = 0;
		String vlEncoded;
		String name_of_object_class = "";
		String data_type = getDataType(value);

		if (value instanceof Scriptable) {
			hasChilds = true;
			StringBuffer stringBuffer = new StringBuffer();
			Scriptable p = (Scriptable) value;
			value = stringBuffer;
			String nv = p.getClassName();
			name_of_object_class = nv;
			if (p instanceof NativeJavaObject) {

				NativeJavaObject obj = (NativeJavaObject) p;

				Object unwrap = obj.unwrap();
				if (unwrap instanceof Class) {
					nv = ((Class) unwrap).getName();
				} else if (unwrap.getClass().isArray()) {
					nv = "Array";
					// String string = unwrap.getClass().getName();
					// int len = Array.getLength(unwrap);
					// int q = string.indexOf('[');
					// if (q != -1)
					// string = string.substring(0, q);
					// int q1 = string.indexOf(']');
					// nv = string + "[" + len + "]";
					// if (q1 != -1)
					// nv += string.substring(q1);
				} else {
					if (unwrap instanceof String) {
						nv = "JavaString " + '"' + unwrap.toString() + '"';
					} else {
						String string = unwrap.toString();

						nv = string;
						// nv = unwrap.getClass().getName() + "(" + string +
						// ")";
					}
				}

			} else if (p instanceof Wrapper) {
				Wrapper wrapper = (Wrapper) p;
				Object wrapped = wrapper.unwrap();

				if (wrapped == null) {
					nv = "Undefined";
				} else if (!wrapped.getClass().isArray()) {
					// if it is an array let the normal array handling do the
					// job
					// now just do toString();
					nv = wrapped.toString();
				}
			} else if (p instanceof XMLObject) {
				nv = ((XMLObject) p).toString();
				data_type = "XML";
			}

			stringBuffer.append(Base64Helper.encodeString(nv));
			if (addChilds) {
				HashSet duplicates = new HashSet();
				Scriptable prototype = p;
				while (prototype != null) {
					numC += createChilds(fullName, level, stringBuffer,
							prototype, duplicates);
					prototype = prototype.getPrototype();
				}
			} else {
				HashSet duplicates = new HashSet();
				Scriptable prototype = p;
				while (prototype != null) {
					Object[] ids = null;
					if (prototype instanceof LazyInitScope) {
						ids = ((LazyInitScope) prototype).getInitializedIds();
					} else {
						ids = prototype.getIds();
					}
					for (int a = 0; a < ids.length; a++) {
						if (!duplicates.add(ids[a]))
							continue;
						Object pvalue = null;
						try {
							if (ids[a] instanceof Integer) {
								pvalue = p.get(((Integer) ids[a]).intValue(),
										p);
							} else
								pvalue = p.get(ids[a].toString(), p);
						} catch (Exception e) {
							// dont let the debugger crash.
							e.printStackTrace();
						}

						if (!(pvalue instanceof Function)) // HACK because
						// ShowFunctionsAction
						// doesnt work because of
						// the lazy behavior of
						// plugins in Eclipse
						{
							numC++;
						}
					}
					prototype = prototype.getPrototype();
				}
			}
			vlEncoded = stringBuffer.toString();
		} else {
			if (!(value instanceof Undefined)) {
				if (value == UniqueTag.NOT_FOUND) {
					vlEncoded = "";
				} else
					vlEncoded = Base64Helper.encodeString(
							value != null ? value.toString() : "null");
			} else {
				vlEncoded = Base64Helper.encodeString("Undefined");
			}
			if (value != null)
				name_of_object_class = value.getClass().getName();
		}
		id = escapeHTML(id);
		fullName = escapeHTML(fullName);

		properties.append("<property\r\n" + "    name=\"" + id + "\"\r\n"
				+ "    fullname=\"" + fullName + "\"\r\n" + "    type=\""
				+ data_type + "\"\r\n" + "    classname=\""
				+ name_of_object_class + "\"\r\n" + "    constant=\"0\"\r\n"
				+ "    children=\"" + (hasChilds ? 1 : 0) + "\"\r\n"
				+ "    encoding=\"base64\"\r\n" + "    numchildren=\"" + numC
				+ "\">\r\n" + vlEncoded + "</property>\r\n");
	}

	private static String escapeHTML(String content) {
		content = replace(content, '&', "&amp;"); //$NON-NLS-1$
		content = replace(content, '"', "&quot;"); //$NON-NLS-1$
		content = replace(content, '<', "&lt;"); //$NON-NLS-1$
		return replace(content, '>', "&gt;"); //$NON-NLS-1$
	}

	private static String replace(String text, char c, String s) {

		int previous = 0;
		int current = text.indexOf(c, previous);

		if (current == -1)
			return text;

		StringBuffer buffer = new StringBuffer();
		while (current > -1) {
			buffer.append(text.substring(previous, current));
			buffer.append(s);
			previous = current + 1;
			current = text.indexOf(c, previous);
		}
		buffer.append(text.substring(previous));

		return buffer.toString();
	}

	/**
	 * @param fullName
	 * @param level
	 * @param stringBuffer
	 * @param p
	 * @param ids
	 */
	private int createChilds(String fullName, int level,
			StringBuffer stringBuffer, Scriptable p, HashSet duplicates) {
		Object[] ids = null;
		if (p instanceof LazyInitScope) {
			ids = ((LazyInitScope) p).getInitializedIds();
		} else if (p instanceof ScriptableObject && !(p instanceof XMLObject)
				&& !(p instanceof NativeArray)) {

			ids = ((ScriptableObject) p).getAllIds();
		} else {
			ids = p.getIds();
		}
		int counter = 0;
		for (int a = 0; a < ids.length; a++) {
			if (!duplicates.add(ids[a]))
				continue;
			Object pvalue = null;
			try {
				if (ids[a] instanceof Integer) {
					pvalue = p.get(((Integer) ids[a]).intValue(), p);
				} else
					pvalue = p.get(ids[a].toString(), p);
			} catch (Throwable e) {
				// dont let the debugger crash.
				e.printStackTrace();
			}
			if (!(pvalue instanceof Function)) // HACK because
			// ShowFunctionsAction
			// doesnt work because of the lazy
			// behavior of plugins in Eclipse
			{
				counter++;
				if (ids[a] instanceof Integer) {
					printProperty(ids[a].toString(),
							fullName + "[" + ids[a] + "]", pvalue, stringBuffer,
							level + 1, false);
				} else {
					printProperty(ids[a].toString(), fullName + "." + ids[a],
							pvalue, stringBuffer, level + 1, false);
				}
			}
		}
		return counter;
	}

	private String getDataType(Object value) {
		String data_type = "Object";
		if (value instanceof Function) {
			data_type = "function";
		} else if (value instanceof NativeJavaArray) {
			data_type = "javaarray";
		} else if (value instanceof NativeArray) {
			data_type = "array";
		} else if (value instanceof NativeJavaObject) {
			data_type = "javaobject";
		} else if (value instanceof NativeJavaClass) {
			data_type = "javaclass";
		} else if (value instanceof String) {
			data_type = "string";
		} else if (value instanceof Number) {
			data_type = "number";
		} else if (value instanceof Boolean) {
			data_type = "boolean";
		} else if (value instanceof Date) {
			data_type = "date";
		} else if (value instanceof Undefined || value == null) {
			data_type = "undefined";
		} else if (value instanceof Wrapper) {
			return getDataType(((Wrapper) value).unwrap());
		}
		return data_type;
	}

	@Override
	public void run() {
		try {
			DataInputStream ds = new DataInputStream(socket.getInputStream());
			StringBuffer buf = new StringBuffer();
			Context.enter();
			while (ds.available() >= 0) {
				int c = ds.read();
				if (c < 0)
					break;
				if (c < 32) {
					String s = buf.toString();

					int indexOf = s.indexOf(' ');
					if (indexOf != -1)

					{
						String commandId = buf.substring(0, indexOf);
						Command object = (Command) strategies.get(commandId);
						if (object == null) {
							System.err.println(commandId);
							continue;
						}
						HashMap options = new HashMap();

						String result = buf.substring(indexOf + 1);
						String[] split = result.split(" ");

						try {
							for (int a = 0; a < split.length; a++) {
								options.put(split[a], split[++a]);
							}
						} catch (Exception e) {

						}
						object.parseAndExecute(result, options);
					}
					buf = new StringBuffer();
				} else {
					buf.append((char) c);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public DebugFrame getFrame(Context cx, DebuggableScript fnOrScript) {
		return new DBGPDebugFrame(cx, fnOrScript, this);
	}

	@Override
	public void handleCompilationDone(Context cx, DebuggableScript fnOrScript,
			String source) {
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (runTransctionId != null)
			printResponse("<response command=\"run\"\r\n" + "status=\"break\""
					+ " reason=\"ok\"" + " transaction_id=\"" + runTransctionId
					+ "\">\r\n" + "</response>\r\n" + "");
	}

	public void notifyEnd() {
		printResponse("<response command=\"run\"\r\n" + "status=\"stopped\""
				+ " reason=\"ok\"" + " transaction_id=\"" + runTransctionId
				+ "\">\r\n" + "</response>\r\n" + "");
		System.exit(0);
	}

	@Override
	public void access(String property, ScriptableObject object) {
		ArrayList list = (ArrayList) stackmanager.getManager()
				.getWatchPoints(property);
		if (list != null) {
			int size = list.size();

			for (int a = 0; a < size; a++) {
				BreakPoint watchPoint = (BreakPoint) list.get(a);
				if (watchPoint != null) {
					if (watchPoint.enabled)
						if (watchPoint.isAccess) {
							String wkey = watchPoint.file + watchPoint.line;
							String s = (String) cache.get(object);
							if ((s != null) && (s.equals(wkey))) {
								stackmanager.getObserver().update(null, this);
								stackmanager.waitForNotify();
							}
						}
				}
			}
		}
	}

	WeakHashMap cache = new WeakHashMap();

	@Override
	public void modification(String property, ScriptableObject object) {

		ArrayList list = (ArrayList) stackmanager.getManager()
				.getWatchPoints(property);
		if (list != null && stackmanager.getStackDepth() > 0) {
			int size = list.size();
			for (int a = 0; a < size; a++) {

				BreakPoint watchPoint = (BreakPoint) list.get(a);
				if (watchPoint != null) {
					if (watchPoint.enabled) {
						String sn = stackmanager.getStackFrame(0)
								.getSourceName();
						int ln = stackmanager.getStackFrame(0).getLineNumber();
						String key = sn + ln;
						String wkey = watchPoint.file + watchPoint.line;
						if (key.equals(wkey)) {
							cache.put(object, wkey);
						}
						if (watchPoint.isModification) {
							Object object2 = cache.get(object);
							if (object2 != null)
								if (object2.equals(wkey)) {

									stackmanager.getObserver().update(null,
											this);
									synchronized (this.stackmanager) {
										try {
											this.stackmanager.wait();
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								}
						}
					}
				}
			}
		}
	}

	public void setProperty(String name, String value) {

	}

	public void setSuspendOnExit(boolean parseBoolean) {
		stackmanager.setSuspendOnExit(parseBoolean);
	}

	public void setSuspendOnEntry(boolean parseBoolean) {
		stackmanager.setSuspendOnEntry(parseBoolean);
	}
}
