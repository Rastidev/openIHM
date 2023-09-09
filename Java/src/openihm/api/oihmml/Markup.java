package openihm.api.oihmml;

import openihm.api.lang.String;
import openihm.api.system.System;
import openihm.api.utils.Array;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;
import openihm.api.utils.Map;
import openihm.api.utils.Pair;
import openihm.api.utils.TreeMap;
import openihm.api.view.View;

public abstract class Markup{
	
	private final View view;
	
	private final MarkupType type;
	
	private final Markup parent;
	
	private final Map<String, String> infos = new TreeMap<>();
	
	
	protected Markup(final MarkupType type) { this(type, null); }
	
	protected Markup(final MarkupType type, final Markup parent) { this(type, parent, new Array<>(0)); }
	
	protected Markup(final MarkupType type, final Markup parent, final List<Pair<String, String>> infos) {
		for(Iterator<Pair<String, String>> it = infos.iterator(); !it.isEnd(); it.next()) {
			final Pair<String, String> pair = it.get();
			if(pair == null || pair.getKey() == null || pair.getValue() == null) continue;
			this.infos.put(pair.getKey(), pair.getValue());
		}
		this.view = load();
		this.type = type;
		this.parent = parent;
	}

	public View getView() { return view; }

	public MarkupType getType() { return type; }
	
	public Map<String, String> getInfos() { return infos; }
	
	boolean setInternStyle(final String name, final String param) {
		final InternStyle style = type.getInternStyles().get(name);
		if(style == null) {
			System.cerr.$(new String("style \"")).$(name).$(new String("\" not found")).endl();
			return false;
		}
		return style.run(this, param);
	}
	
	private boolean setExternStyle(final String name, final String param, final Markup child) {
		final ExternStyle style = type.getExternStyles().get(name);
		if(style == null) {
			System.cerr.$(new String("style \"")).$(name).$(new String("\" not found")).endl();
			return false;
		}
		return style.run(this, param, child);
	}
	
	
	boolean setExternStyle(final String name, String param) { return parent.setExternStyle(name, param, this); }
	
	protected abstract boolean addMarkup(final Markup markup);
	
	protected abstract View load();
	
	

}
