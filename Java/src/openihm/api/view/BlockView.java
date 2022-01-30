package openihm.api.view;

import openihm.api.graphics.Graphics;
import openihm.api.utils.AdvencedList;
import openihm.api.utils.Iterator;
import openihm.api.utils.List;

public class BlockView extends View{
	
	private List<View> listView = new AdvencedList<>();
	
	/*
	 *  liste de (tableau de double de taille 4) avec
	 * ind 0 la position x en % du coin haut gauche de la View
	 * ind 1 la position y en % du coin haut gauche de la View
	 * ind 2 la longueur horizontale en % de la View
	 * ind 3 la hauteur vertical en % de la View
	 */
	private List<double[]> listPosSize = new AdvencedList<>();
	private static final int POSPCX = 0;
	private static final int POSPCY = 1;
	private static final int SIZEPCX = 2;
	private static final int SIZEPCY = 3;
	
	public BlockView() {
		setGraphics(new Graphics() {
			
			/*
			 * affiche toutes les vue de l'objet actuelle avec leur position
			 * 
			 */
			@Override
			public void draw() {
				Iterator<View> view = listView.iterator();
				Iterator<double[]> posSize = listPosSize.iterator();
				while(!view.isEnd()) {
					drawGraphics(view.get().getGraphics(), posSize.get()[0], 
							posSize.get()[1], posSize.get()[2], posSize.get()[3]);
					view.next();
					posSize.next();
				}
			}
		});
	}
	/*
	 * ajoute une View a la listView et enregistre sa position en %
	 * view la référence de vue
	 * posPcX la position x en % du coin haut gauche de la View
	 * posPcY la position y en % du coin haut gauche de la View
	 * SizePcX la longueur horizontale en % de la View
	 * SizePcY la hauteur vertical en % de la View
	 */
	public void addView(final View view, final double posPcX, final double posPcY,
			final double SizePcX ,final double SizePcY) {
		listView.add(view);
		listPosSize.add(new double[] {posPcX, posPcY, SizePcX, SizePcY} );
	}
	
	private List<List<View>> caseEvent;
	
	private List<Double> listWidth;
	
	private List<Double> listHeight;
	
	public void reloadEvent() {
		final List<Double> listWidth = new AdvencedList<>();
		final List<Double> listHeight = new AdvencedList<>();
		final List<List<View>> caseEvent = new AdvencedList<>();
		///////////////////////////////////////////////////////
		caseEvent.add(new AdvencedList<>());
		caseEvent.get(0).add(null);
		///////////////////////////////////////////////////////
		listWidth.add(0.);
		listWidth.add(1.);
		listHeight.add(0.);
		listHeight.add(1.);
		///////////////////////////////////////////////////////
		////////////////////////////////////////////////////////
		Iterator<View> view = listView.iterator();
		Iterator<double[]> posSize = listPosSize.iterator();
		while(!view.isEnd()) {
			final int MinX = getIndInsert(listWidth, posSize.get()[POSPCX]);
			listWidth.insert(MinX, posSize.get()[POSPCX]);
			caseEvent.insert(MinX - 1, clone(caseEvent.get(MinX - 1)));
			////////////
			final int MaxX = getIndInsert(listWidth, posSize.get()[POSPCX] + posSize.get()[SIZEPCX]);
			listWidth.insert(MaxX, posSize.get()[POSPCX] + posSize.get()[SIZEPCX]);
			caseEvent.insert(MaxX - 1, clone(caseEvent.get(MaxX - 1)));
			///////////
			final int MinY = getIndInsert(listHeight, posSize.get()[POSPCY]);
			listHeight.insert(MinY, posSize.get()[POSPCY]);
			insertHeight(caseEvent, MinY - 1);
			//
			//////////
			final int MaxY = getIndInsert(listHeight, posSize.get()[POSPCY] + posSize.get()[SIZEPCY]);
			listHeight.insert(MaxY, posSize.get()[POSPCY] + posSize.get()[SIZEPCY]);
			insertHeight(caseEvent, MaxY - 1);
			///////////////
			for(int i = MinX; i < MaxX; i++)
				for (int j = MinY; j < MaxY; j++)
					caseEvent.get(i).set(j, view.get());
			view.next();
			posSize.next();
		}
		this.listWidth = listWidth.getArray();
		this.listHeight = listHeight.getArray();
		this.caseEvent = caseEvent.getArray();
		for(int i = 0; i < this.caseEvent.size(); i++)
			this.caseEvent.set(i, this.caseEvent.get(i).getArray());
		
		
	}
	
	private static void insertHeight(final List<List<View>> caseEvent, final int IndInsert) {
		for(Iterator<List<View>> it = caseEvent.iterator(); !it.isEnd(); it.next()) 
			it.get().insert(IndInsert, it.get().get(IndInsert));
	}
	
	/*
	 * renvoie @List<View> le clone de la liste de vue
	 * $list @List<View> la liste de vue
	 */
	private List<View> clone(final List<View> list){
		final List<View> clone = new AdvencedList<>();
		for(Iterator<View> it = list.iterator(); !it.isEnd(); it.next()) clone.add(it.get());
		return clone;
	}
	
	
	/*
	 * renvoie @int l'indice ou l'on peut inserer la valeur pour que 
	 * la valeur precedente soit inferieur et la prochaine valeur soit supérieur
	 * $list @List<Double> la liste
	 * $val @double la valeur
	 */
	private static int getIndInsert(final List<Double> list, final double val) {
		Iterator<Double> it = list.iterator();
		for(int i = 0; !it.isEnd() ; i++) {
			if(val < it.get()) return i;
			it.next();
		}
		return list.size() - 1;
	}
	
	private View getViewInterfaceEvent(final double x, final double y) {
		final int IndX = getCaseVal(listWidth.iterator(), x);
		final int IndY = getCaseVal(listHeight.iterator(), y);
		return caseEvent.get(IndX).get(IndY);
	}
	
	@Override
	public void eventScroll(double scroll) {
		super.eventScroll(scroll);
		for(Iterator<View> it = listView.iterator(); !it.isEnd(); it.next()) it.get().eventScroll(scroll);
	}
	@Override
	public void eventReleased(double x, double y, int type) {
		super.eventReleased(x, y, type);
		View view = getViewInterfaceEvent(x, y);
		if(view == null) return;
		double[] posSize = listPosSize.get(listView.indexOf(view));
		view.eventReleased((x - posSize[POSPCX]) / posSize[SIZEPCX], 
				(y - posSize[POSPCY]) / posSize[SIZEPCY], type);
	}
	@Override 
	public void eventPressed(double x, double y, int type) {
		super.eventPressed(x, y, type);
		View view = getViewInterfaceEvent(x, y);
		if(view == null) return;
		double[] posSize = listPosSize.get(listView.indexOf(view));
		view.eventPressed((x - posSize[POSPCX]) / posSize[SIZEPCX], 
				(y - posSize[POSPCY]) / posSize[SIZEPCY], type);
	}
	@Override 
	public void eventMoved(double x, double y) {
		super.eventMoved(x, y);
		View view = getViewInterfaceEvent(x, y);
		if(view == null) return;
		double[] posSize = listPosSize.get(listView.indexOf(view));
		view.eventMoved((x - posSize[POSPCX]) / posSize[SIZEPCX], 
				(y - posSize[POSPCY]) / posSize[SIZEPCY]);
	}
	@Override 
	public void eventDragged(double x, double y, int type) {
		super.eventDragged(x, y, type);
		View view = getViewInterfaceEvent(x, y);
		if(view == null) return;
		double[] posSize = listPosSize.get(listView.indexOf(view));
		view.eventDragged((x - posSize[POSPCX]) / posSize[SIZEPCX], 
				(y - posSize[POSPCY]) / posSize[SIZEPCY], type);
	}
	@Override 
	public void eventClick(double x, double y, int type) {
		super.eventClick(x, y, type);
		View view = getViewInterfaceEvent(x, y);
		if(view == null) return;
		double[] posSize = listPosSize.get(listView.indexOf(view));
		view.eventClick((x - posSize[POSPCX]) / posSize[SIZEPCX], 
				(y - posSize[POSPCY]) / posSize[SIZEPCY], type);
	}
	
	private static int getCaseVal(final Iterator<Double> it, final double val) {
		for(int i = 0; !it.isEnd(); i++) {
			if(val < it.get()) return i - 1;
			it.next();
		}
		return 0;
	}
}
