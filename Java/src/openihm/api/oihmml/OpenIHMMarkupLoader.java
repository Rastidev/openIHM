package openihm.api.oihmml;

import openihm.api.lang.String;
import openihm.api.system.System;
import openihm.api.utils.AdvencedList;
import openihm.api.utils.IdMap;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;
import openihm.api.utils.Map;
import openihm.api.utils.Pair;
import openihm.api.utils.TreeMap;

public class OpenIHMMarkupLoader{
	
	private final Map<String, MarkupType> markupTypesLoad = new TreeMap<>();
	
	private final Map<String, List<Markup>> classs = new TreeMap<>();
	
	private final Map<Integer, Markup> ids = new IdMap<>();
	
	
	public OpenIHMMarkupLoader(final List<Pair<String, MarkupType>> markupTypes) {
		for(Iterator<Pair<String, MarkupType>> it = markupTypes.iterator(); !it.isEnd(); it.next())
			if(it.get() != null && it.get().getValue() != null) add(it.get());

	}
	
	public Markup load(final String data) {
		
		return null;
	}
	
	public boolean add(final Pair<String, MarkupType> pair) {
		if(pair == null) {
			System.cerr.$(new String("OpenIHMMarkupLoader :pair is null"));
			return false;
		}
		return add(pair.getKey(), pair.getValue()); 
	}

	public boolean add(final String name, final MarkupType markup) {
		if(markup == null) {
			System.cerr.$(new String("markup is null"));
			return false;
		}
		return markupTypesLoad.put(name, markup);
	}

	protected Map<String, MarkupType> getMarkupTypesLoad() { return markupTypesLoad; }

	protected Map<String, List<Markup>> getClasss() { return classs; }
	
	public List<Markup> getByClass(final String class_) {
		final List<Markup> result = classs.get(class_);
		if(result == null) return new AdvencedList<>(0);
		return result;
	}
	
	public Markup getById(final int id) { return ids.get(id); }

}
