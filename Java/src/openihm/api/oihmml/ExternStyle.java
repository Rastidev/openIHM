package openihm.api.oihmml;

import openihm.api.lang.String;

public interface ExternStyle {
	
	public abstract boolean run(Markup markup, String param, Markup child);

}
