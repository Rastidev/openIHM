package openihm.api.graphics;

import openihm.api.utils.BasicList;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;

public class Graphics extends Shape{
	
	private final List<Shape> listShape = new BasicList<>();

	@Override
	public void draw() {
		for(final Iterator<Shape> it = listShape.iterator();!it.isEnd(); it.next())
			drawShape(it.get(), 0, 0, 1, 1);
	}
	
	public void clear() { listShape.resize(0); }
	
	public void addShape(final Shape shape) { listShape.add(shape); }

}
