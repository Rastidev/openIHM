package openihm.api.oihmml;

import openihm.api.lang.String;
import openihm.api.utils.Array;
import openihm.api.utils.List;
import openihm.api.utils.Map;
import openihm.api.utils.Pair;
import openihm.api.utils.TreeMap;

public abstract class MarkupType {
	
	private final String name;
	
	private final Map<String, InternStyle> internStyles = new TreeMap<>();
	
	private final Map<String, ExternStyle> externStyles = new TreeMap<>();
	
	public MarkupType(final String name) { this.name = name; }

	public String getName() { return name; }
	
	protected Markup newMarkup() { return newMarkup(new Array<>(0)); }
	
	protected abstract Markup newMarkup(final List<Pair<String, String>> infos);

	public Map<String, InternStyle> getInternStyles() { return internStyles; }

	public Map<String, ExternStyle> getExternStyles() { return externStyles; }
	
	public boolean addStyle(final String name, final InternStyle internStyle) { return internStyles.put(name, internStyle); }
	
	public boolean addStyle(final String name, final ExternStyle externStyle) { return externStyles.put(name, externStyle); }
	
	
	
	
	
	
	

}