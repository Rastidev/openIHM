package openihm.api.graphics;

import openihm.api.utils.BasicList;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;

public class GraphicsOld extends Graphics{
	
	private final List<Graphics> listShape = new BasicList<>();

	@Override
	public void draw() {
		for(final Iterator<Graphics> it = listShape.iterator();!it.isEnd(); it.next())
			drawGraphics(it.get(), 0, 0, 1, 1);
	}
	
	public void clear() { listShape.resize(0); }
	
	public void addShape(final Graphics shape) { listShape.add(shape); }

}
