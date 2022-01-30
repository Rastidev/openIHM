package openihm.api.dev;

public final class Dev {
	
//	private static int getInt(final char a, final char b, final char c, final char d) {
//	return (a << 24) | ((b << 16) | ((c << 8) | d));
//}
//
//private static final int ID_IHDR = 0x49484452;
//private static final int ID_IDAT = 0x49444154;
//private static final int ID_IEND =0x49454E44;

//private static Image readPNG(final FileSystem fs) {
//	String paths = String.$("test.png");
//	if(!fs.fileExist(paths.getValue(), paths.size(), FileSystem.WINDOWS_FILE)) {
//		System.cerr.$(String.$("test.png no found"));
//		return null;
//	}
//	 final char[] data = fs.getFile(paths.getValue(), paths.size(), FileSystem.WINDOWS_FILE);
//	 final int dataSize = data.length;
//	 if(data[0] != 0x89 || data[1] != 0x50 || data[2] != 0x4E || data[3] != 0x47 || data[4] != 0x0D
//			 || data[5] != 0x0A || data[6] != 0x1A || data[7] != 0x0A) {
//		 		System.cerr.$(String.$("isNotPng"));
//		 		return null;
//	 }
//	 int cursor = 8;
//	 //lecture IHDR
//	 int lengthChunk = getInt(data[8], data[9], data[10], data[11]);
//	 final Image img = new Image(getInt(data[16], data[17], data[18], data[19]),
//			 getInt(data[20], data[21], data[22], data[23]));
//	 final char NbBitPixel = data[24];
//	 final char TypeColor = data[25];
//	 cursor = cursor + lengthChunk + 12;
//	 //lecture des autres blocs
//	 while (cursor < dataSize) {
//		 lengthChunk = getInt(data[cursor], data[cursor + 1], data[cursor + 2], data[cursor + 3]);
//		 final int idChunk = getInt(data[cursor + 4], data[cursor + 5], data[cursor + 6], data[cursor + 7]);
//		 cursor += 8;
//		 switch(idChunk) {
//		 case ID_IDAT:
//			 System.cout.$(String.$("found")).endl().$((int) data[cursor + 1]).endl();
//			 Inflater inflater = new Inflater();
//			 cursor = cursor + lengthChunk + 4;
//			 break;
//		 case ID_IEND:
//			 System.cout.$(String.$("end")).endl();
//			 return img;
//		 default:
//			 cursor = cursor + lengthChunk + 4;
//			 break;
//		 }
//	 }
//	 return null;
//}
	
}
