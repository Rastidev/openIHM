package openihm.api.system;



class Cerr extends Ostream {

	@Override
	public void print(char c) {
		System.get.print_err(c);
	}

	@Override
	public void print(int i) {
		System.get.print_err(i);
	}

}
