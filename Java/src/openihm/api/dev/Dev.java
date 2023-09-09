package openihm.api.dev;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import openihm.api.file.File;
import openihm.api.file.Paths;
import openihm.api.graphics.Image;
import openihm.api.lang.String;
import openihm.interfaces.FileSystem;
import openihm.api.utils.BinaryReader;
import openihm.api.system.System;

public final class Dev {
	
	private static int getInt(final byte a, final byte b, final byte c, final byte d) {
		return (a << 24) | ((b << 16) | ((c << 8) | d));
	}

private static final int ID_IHDR = 0x49484452;
private static final int ID_IDAT = 0x49444154;
private static final int ID_IEND =0x49454E44;

public static Image readPNG(final FileSystem fs) {
	String paths = new String("a.png");
	if(!fs.fileExist(paths.getValue(), paths.size(), FileSystem.WINDOWS_FILE)) {
		System.cerr.$(new String("test.png no found"));
		return null;
	}
	String allFile = new File(Paths.getWindows(paths)).getContain();
	final byte[] data = allFile.getValue();
	final int dataSize = allFile.size();
	if(data[0] != 0x89 || data[1] != 0x50 || data[2] != 0x4E || data[3] != 0x47 || data[4] != 0x0D
			|| data[5] != 0x0A || data[6] != 0x1A || data[7] != 0x0A) {
		 	System.cerr.$(new String("isNotPng"));
		 	return null;
	}
	int cursor = 8;
	//lecture IHDR
	int lengthChunk = getInt(data[8], data[9], data[10], data[11]);
	final Image img = new Image(getInt(data[16], data[17], data[18], data[19]),
			getInt(data[20], data[21], data[22], data[23]));
	final byte NbBitPixel = data[24];
	System.cout.$(new String("bitpixel ")).$((int) NbBitPixel).endl();
	final byte TypeColor = data[25];
	System.cout.$(new String("colortype ")).$((int) TypeColor).endl();
	cursor = cursor + lengthChunk + 12;
	//lecture des autres blocs
	while (cursor < dataSize) {
		lengthChunk = getInt(data[cursor], data[cursor + 1], data[cursor + 2], data[cursor + 3]);
		final int idChunk = getInt(data[cursor + 4], data[cursor + 5], data[cursor + 6], data[cursor + 7]);
		cursor += 8;
		switch(idChunk) {
		case ID_IDAT:
			System.cout.$(new String("found")).endl().$((int) data[cursor]).endl();
			///////
			final byte[] pixelData = new byte[lengthChunk];
			for(int i = 0; i < lengthChunk; i++) pixelData[i] = (byte) data[cursor + i];
			Inflater inflater = new Inflater();
			inflater.setInput(pixelData);
			byte[] inflate_return = new byte[img.height() * img.width() * 3];
			try {inflater.inflate(inflate_return);} catch (DataFormatException e) {e.printStackTrace();}
			for(int i = 0; i < inflate_return.length && i / img.height() < img.width(); i += 3) img.value()[i / img.height()][i % img.height()] =
				getInt((byte)0, inflate_return[i], inflate_return[i + 1], inflate_return[i + 2]);
			///////
			//cursor = cursor + lengthChunk + 4;
			break;
		case ID_IEND:
			System.cout.$(new String("end")).endl();
			return img;
		default:
			cursor = cursor + lengthChunk + 4;
			break;
		}
	}
	 return null;
}


public String deflate(final byte[] data, final int size) {
	BinaryReader btr = new BinaryReader(data, size, 0);
	boolean isNotLast = true;
	while(isNotLast) {
		if(btr.read() == 1) isNotLast = false;
		switch(btr.read(2)) {
		case 0x00:
			break;
		case 0x10:
		case 0x01:
		default:
		}
	}
	
	return null;
}
	
}
