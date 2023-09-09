package openihm.api.exception;

import openihm.api.lang.F;
import openihm.api.lang.Object;
import openihm.api.lang.String;
import openihm.api.system.System;
import openihm.api.utils.AdvencedList;
import openihm.api.utils.List;

public class Exception {
	
	private static final List<Exception> list = new AdvencedList<>();
	
	private static boolean debug = false;
	
	private static boolean print = true;
	
	private final String className;
	
	private final int id;
	
	private final int idPosition;
	
	private final String msg;
	
	public Exception(final Object obj, final int id, final int idPosition, final String msg, final boolean debug) {
		this.className = obj._className_();
		this.id = id;
		this.idPosition = idPosition;
		if(msg == null) this.msg = new String();
		else this.msg = msg;
		if(debug) {
			list.add(this);
			action(this);
		}
	}
	
	public Exception(final Object obj, final int id, final int idPosition, final String msg) { this(obj, id, idPosition, msg, debug); }
	
	public Exception(final Object obj, final int id, final String msg) { this(obj, id, 0, msg); }
	
	public Exception(Object obj, int id, int idPosition) { this(obj, id, idPosition, null); }
	
	public Exception(final Object obj, final int id) { this(obj, id, null); }
	
	public Exception(final Object obj) { this(obj, 0); }

	public String getClassName() { return className; }

	public int getId() { return id; }

	public String getMsg() { return msg; }
	
	public int getIdPosition() { return idPosition; }
	
	private static void action(final Exception e) {
		if(print) {
			System.cerr.$(new String("Error code = ")).$(e.id).endl().$(new String("at class :")).
			$(e.className).endl().$(new String("at position id : ")).$(e.idPosition).endl().$( new String(" : ")).$(e.msg).endl();
		}
	}
	
	public static Exception throw_() {
		Exception result = list.get(list.size() - 1);
		list.removeLast();
		return result;
	}
	
	public static void clear() { list.clear(); }
	
	public static void setDebug(final boolean b) { debug = b; }
	
	public static void setPrint(final boolean b) { print = b; }

}
