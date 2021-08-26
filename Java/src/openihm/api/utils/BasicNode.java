package openihm.api.utils;

public class BasicNode<T>  extends Value<T>{
	
	// le prochiane noeud
	private BasicNode<T> nextNode;
	
	//contructeur par defaut
	public BasicNode(){};
	
	
	/*
	 * met une valeur au noeud
	 * $value @T la valeur
	 */
	public BasicNode(final T value){ super(value); };
	
	/*
	 * met une valeur et un prochain noeux à ce noeud
	 * $value @T la valeur $node @SimpleNode le prochain noeud
	 */
	public BasicNode(final BasicNode<T> node){
		nextNode = node;
	};
	
	public BasicNode(final T value, final BasicNode<T> node){
		super(value);
		nextNode = node;
	};
	
	/*
	 * renvoie @SimpleNode le prochain noeud
	 */
	public final BasicNode<T> nextNode() { return nextNode; }
	
	/*
	 * met un prochain noeux à ce noeud
	 * $node @SimpleNode le prochain noeud
	 */
	public void setNextNode(final BasicNode<T> node) { nextNode = node; }

}
