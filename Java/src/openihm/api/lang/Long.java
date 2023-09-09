package openihm.api.lang;

public class Long extends Value<java.lang.Long>{
	
	public Long() { super((long) 0); }

	public Long(long value) { super(value); }
	
	public Long(Long value) { super(value.value); }

}
