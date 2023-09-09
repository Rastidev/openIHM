package openihm.api.system;



class Cerr extends OutPrint {

	@Override
	public void print(byte c) {
		System.get.print_err(c);
	}

}
