package openihm.api.exception;

import openihm.api.lang.Object;
import openihm.api.lang.String;

public class NullPointerException extends Exception{
	
	public static final int ID = 1;

	public NullPointerException(final Object obj, final int idPosition, final String varName) { super(obj, ID, idPosition, getMsg(varName)); }
	
	private static String getMsg(final String varName) {
		varName.add(new String(" is null"));
		return varName;
	}

}
