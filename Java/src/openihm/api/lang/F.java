package openihm.api.lang;

public final class F {


	
	public static int[] codeClassIds(final int id, final int[] parent, final int[] interface_, final int sizeInterface) {
		int a = parent[0] + sizeInterface + 1;
		final int[] result = new int[a];
		result[0] = a;
		result[1] = id;
		a = parent[0] + 1;
		for(int i = 1; i < a; i += 1) result[i + 1] = parent[i];
		a += 1;
		for(int i = 0; i < sizeInterface; i += 1) result[i + a] = interface_[i];
		return result;
	}
	
	public static int[] codeClassIds(final int id, final int[] parent) {
		return codeClassIds(id, parent, null, 0);
	}
	
	// data begin with 4 octet for size of vector 1 octet for size inside octet (0-7)
	public static byte[] newData(final byte[] data, final int size, final int sizeBit) {
		byte[] result = new byte[size + 5];
		result[0] = (byte) (((sizeBit % 9) << 3) | 0x06);
		result[1] = Int.toChar(size, 0);
		result[2] = Int.toChar(size, 1);
		result[3] = Int.toChar(size, 2);
		result[4] = Int.toChar(size, 3);
		for(int i = 0; i < size; i += 1) result[i + 5] = data[i];
		return result;
	}
	
	public static byte[] newClassData(final byte[] data, final int size) {
		final byte[] result = new byte[size + 5];
		result[0] = 0x60;
		result[1] = (byte) ((size & 0xff000000) >> 24);
		result[2] = (byte) ((size & 0x00ff0000) >> 16);
		result[3] = (byte) ((size & 0x0000ff00) >> 8);
		result[4] = (byte) (size & 0x000000ff);
		for(int i = 0; i < size; i += 1) result[i + 5] = data[i];
		return result;
	}
	
//	public static Data decode(final byte[] data) {
//		final int typeData = data[0] & 0x01;
//		if(typeData == 0) {
//			int size = 0;
//			int sizeInByte = 8;
//			int typeSize = (data[0] >> 1) & 0x03;
//			byte skip = 1;
//			switch (typeSize) {
//				//one byte of size
//				case 0x00:
//					size = data[1];
//					skip += 1;
//					break;
//				// two byte of size
//				case 0x01:
//					skip += 2;
//					break;
//				// four byte of size
//				case 0x03:
//					sizeInByte = (data[0] >> 3) & 0x0f;
//				// four byte of size
//				case 0x02:
//					size = Char.toInt(data[1], data[2], data[3], data[4]);
//					skip += 4;
//					break;
//				default:
//					size = 0;
//					break;
//			}
//			byte[] decodeData = new byte[size];
//			for(int i = 0; i < size; ++i) decodeData[i] = data[i + skip];
//			return new DataAPI(decodeData, typeData, typeSize);
//			
//		}
//		else return null;
//	}
	
	public static int sizeof(final byte[] data) { return Char.toInt(data[1], data[2], data[3], data[4]); }
	
//	public static boolean equals(Data data){
//		if(data.sizeData() != sizeData()) return false;
//		final int sizeTab = (int) ((sizeData() + 7) / 8);;
//		final byte[] obj_data = data.getData();
//		final byte[] this_data = getData();
//		for(int i = 0; i < sizeTab; i++)
//			if(obj_data[i] != this_data[i]) return false;
//		return true;
//	}
}
