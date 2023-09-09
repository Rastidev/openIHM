package openihm.api.exception;

import openihm.api.lang.Int;
import openihm.api.lang.Object;
import openihm.api.lang.String;

public class BadIndexListException extends Exception{
	
	public static final int ID = 2;

	public BadIndexListException(final Object obj, final int idPosition, final int length, final int indexError) {
		super(obj, ID, idPosition, 
				String.merge(new String[] {new String("bad index : "), Int.toString(indexError), new String(" | actual size : "), Int.toString(length)}, 4));
	}

}
