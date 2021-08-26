package openihm.api.essential;

import openihm.api.system.System;
import openihm.interfaces.Main;
import openihm.interfaces.Root;

public abstract class Launcher extends Main{

	
	@Override
	public int main(final Root root) {
		System.init(root);
		start(new Stage(root));
		return Root.NOTHING;
	}
	
	
	public abstract void start(final Stage stage);
	
	

}
