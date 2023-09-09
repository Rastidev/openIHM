package openihm.api.utils;

public class DoubleNode<T> extends Value<T>{
	
	// le prochiane noeud
	private DoubleNode<T> nextNode = null;
	
	// le noeud precedent
	private DoubleNode<T> prevNode = null;
	
	//contructeur par defaut
	public DoubleNode(){};
	
	
	/*
	 * met une valeur au noeud
	 * $value @T la valeur
	 */
	public DoubleNode(final T value){
		super(value);
	};
	
	//constructeur par copie
	public DoubleNode(final DoubleNode<T> node){
		super(node);
		prevNode = node.prevNode;
		nextNode = node.nextNode;
	}
	
	/*
	 * met une valeur et un prochain noeux à ce noeud
	 * $value @T la valeur $prevnode @DoubleNode le noeud precedent $nextnode @DoubleNode le prochain noeud
	 */
	public DoubleNode(final DoubleNode<T> prevNode, final DoubleNode<T> nextNode){
		this.prevNode = prevNode;
		this.nextNode = nextNode;
	};
	
	public DoubleNode(final DoubleNode<T> prevNode, final DoubleNode<T> nextNode, final T value){
		super(value);
		this.prevNode = prevNode;
		this.nextNode = nextNode;
	};
	
	/*
	 * renvoie @SimpleNode le prochain noeud
	 */
	public final DoubleNode<T> nextNode() { return nextNode; }
	
	/*
	 * met un prochain noeux à ce noeud
	 * $node @SimpleNode le prochain noeud
	 */
	public void setNextNode(final DoubleNode<T> node) { nextNode = node; }
	
	/*
	 * renvoie @SimpleNode le noeud precedent
	 */
	public final DoubleNode<T> prevNode() { return prevNode; }
	
	/*
	 * met un noeux precedent à ce noeud
	 * $node @SimpleNode le noeud precedent
	 */
	public void setPrevNode(final DoubleNode<T> node) { prevNode = node; }
	
	
	
	

}
