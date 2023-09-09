package openihm.api.utils.png;

import openihm.api.lang.Int;

public class Chunk {
	
	public static final int ID_IHDR = 0x49484452;
	public static final int ID_IDAT = 0x49444154;
	public static final int ID_IEND =0x49454E44;
	
	private  int length;
	private  int id;
	private  byte[] data;
		
	public Chunk(final int length, final int id, final byte[] data) {
		this.length = length;
		this.id = id;
		this.data = data;
	}
	
	public boolean setId(final int id) { 
		this.id = id; 
		return true;
	}
	
	public int getId() { return id; }
	
	
	public byte[] getData() { return data; }
	
	
	
	public final boolean update(final int length, final byte[] data) {
		this.length = length;
		this.data = data;
		return true;
	}
	
	final byte[] getChunkString(final int CRC) {
		byte[] str = new byte[length + 12];
		Int.toChars(length, str, 0);
		Int.toChars(id, str, 4);
		for(int i = 0; i < length; i++) str[i + 8] = data[i];
		Int.toChars(CRC, str, length + 8);
		return str;
	}

}
