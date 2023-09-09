package openihm.api.exception;

import openihm.api.lang.Int;
import openihm.api.lang.Object;
import openihm.api.lang.String;

public class NotNaturalNumberException extends Exception{
	
	public static final int ID = 3;

	public NotNaturalNumberException(final Object obj, final int idPosition, final String varName, final int value) {
		super(obj, ID, idPosition, String.merge(new String[]{ varName, new String(" is not a natural number : "), Int.toString(value) }, 3));
	}

}
