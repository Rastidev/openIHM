package openihm.api.utils.png;

import openihm.api.graphics.Image;
import openihm.api.lang.Char;
import openihm.api.lang.String;
import openihm.api.system.System;
import openihm.api.utils.BasicList;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;

public class PortableNetworkGraphics {
	
	List<Chunk> chunks = new BasicList<>();
	
	private Image image = null;
	
	private int width = 0;
	private int height = 0;
	private byte bitDepth = 0;
	private byte colorType = 0;
	private byte compressionMethod = 0;
	private byte filterMethod = 0;
	private byte interlaceMethod = 0;
	
	
	
	public Image getImage() { 
		if(image == null) return null;
		return new Image(image);
	}
	
	public boolean read(final byte[] data, final int size) {
		if(data[0] != 0x89 || data[1] != 0x50 || data[2] != 0x4E || data[3] != 0x47 || data[4] != 0x0D
				|| data[5] != 0x0A || data[6] != 0x1A || data[7] != 0x0A) {
			 	System.cerr.$(new String("isNotPng"));
			 	return false;
		}
		int cursor = 8;
		while(cursor < size) {
			final int lengthChunk = Char.toInt(data[cursor], data[cursor + 1], data[cursor + 2], data[cursor + 3]);
			final int idChunk = Char.toInt(data[cursor + 4], data[cursor + 5], data[cursor + 6], data[cursor + 7]);
			cursor += 8;
			final byte[] dataChunk = new byte[lengthChunk];
			for(int i = 0; i < lengthChunk; i++) dataChunk[i] = data[cursor + i];
			cursor += lengthChunk;
			final int crcChunk = Char.toInt(data[cursor], data[cursor + 1], data[cursor + 2], data[cursor + 3]);
			cursor += 4;
			chunks.add(new Chunk(lengthChunk, idChunk, dataChunk));
		}
		return readChunks();
		
		
	}
	
	public List<Chunk> getChunks(){ return chunks.getArray(); }
	
	public List<Chunk> getChunks(final int id){
		List<Chunk> result = new BasicList<>();
		for(Iterator<Chunk> it = chunks.iterator(); !it.isEnd(); it.next()) if(it.get().getId() == id) result.add(it.get());
		return result.getArray();
	}
	
	public boolean addChunks(final Chunk chunk) {
		chunks.add(chunk);
		organise();
		return true;
	}
	
	public boolean setChunks(final List<Chunk> chunks) {
		this.chunks = chunks;
		organise();
		return true;
	}
	
	private final void organise() {
		
	}
	
	public boolean readChunks() {
		if(chunks.isEmpty() || chunks.get(0).getId() != Chunk.ID_IHDR) return false;
		for(Iterator<Chunk> it = chunks.iterator(); !it.isEnd(); it.next()) {
			switch (it.get().getId()) {
			case Chunk.ID_IHDR:
				readIHDR(it.get());
				break;
			case Chunk.ID_IDAT:
				readIDAT(it.get());
				break;
			case Chunk.ID_IEND:
				System.cout.$(new String("end")).endl();
				return true;
			default:
				break;
			}
		}
		return false;
		
	}
	
	
	private final void readIHDR(final Chunk chunk) {
		byte[] data = chunk.getData();
		width = Char.toInt(data[0], data[1], data[2], data[3]);
		height = Char.toInt(data[4], data[5], data[6], data[7]);
		bitDepth = data[8];
		colorType = data[9];
		compressionMethod = data[10];
		filterMethod = data[11];
		interlaceMethod = data[12];
	}
	
	private final void readIDAT(final Chunk chunk) {
		if(image == null) image = new Image(width, height, 0x00ffffff);
	}
	
	

}
