package openihm.api.utils;

import openihm.api.exception.NotNaturalNumberException;
import openihm.api.lang.Object;
import openihm.api.lang.String;
import openihm.api.system.System;

public class AdvencedList<T> extends Object implements List<T>{
	
	private int size = 0;
	
	private final DoubleNode<T> HEAD;
	
	private final DoubleNode<T> FOOT;
	
	
	public AdvencedList() {
		this.HEAD = new DoubleNode<>();
		this.FOOT = new DoubleNode<>(HEAD, null);
		this.HEAD.setNextNode(FOOT);
		this.HEAD.setPrevNode(HEAD);
		this.FOOT.setNextNode(FOOT);
	}
	
	public AdvencedList(final int size) {
		if(size < 0) {
			new NotNaturalNumberException(this, 1, new String("size"), size);
			this.size = 0;
		}
		this.HEAD = new DoubleNode<>();
		this.HEAD.setPrevNode(HEAD);
		this.FOOT = new DoubleNode<>();
		this.FOOT.setNextNode(FOOT);
		DoubleNode<T> node = HEAD;
		for(int i = 0; i < size; i++) {
			node.setNextNode(new DoubleNode<>(node, null));
			node = node.nextNode();
		}
		node.setNextNode(FOOT);
		this.FOOT.setPrevNode(node);
	}
	
	

	@Override
	public int size() { return size; }

	@Override
	public T get(int index) {
		if(isBadIndex(index)) return null;
		return getNode(index).getValue();
	}

	@Override
	public boolean set(int index, T e) {
		if(isBadIndex(index)) return false;
		getNode(index).setValue(e);
		return true;
	}

	@Override
	public boolean resize(int size) {
		if(size < 0) {
			new NotNaturalNumberException(this, 1, new String("size"), size);
			return false;
		}
		if(size == this.size) return true;
		if(size < this.size) {
			final DoubleNode<T> node;
			if(size == 0) node = HEAD;
			else node = getNode(size - 1);
			node.setNextNode(FOOT);
			FOOT.setPrevNode(node);
		}
		else {
			final int indStop = size - this.size;
			final DoubleNode<T> firstnode = new DoubleNode<>(FOOT.prevNode(), null);
			DoubleNode<T> lastnode = firstnode;
			for(int i = 1; i < indStop; i++) {
				lastnode.setNextNode(new DoubleNode<>());
				lastnode.nextNode().setPrevNode(lastnode);
				lastnode = lastnode.nextNode();
			}
			FOOT.prevNode().setNextNode(firstnode);
			lastnode.setNextNode(FOOT);
			FOOT.setPrevNode(lastnode);
		}
		this.size = size;
		return true;
	}
	
	@Override
	public boolean add(final T e) {
		final DoubleNode<T> node = new DoubleNode<>(FOOT.prevNode(), FOOT, e);
		FOOT.prevNode().setNextNode(node);
		FOOT.setPrevNode(node);
		size++;
		return true;
	}
	
	@Override
	public boolean remove(final int index) {
		if(isBadIndex(index)) return false;
		final DoubleNode<T> node = getNode(index);
		node.prevNode().setNextNode(node.nextNode());
		node.nextNode().setPrevNode(node.prevNode());
		size--;
		return true;
	}
	
	@Override
	public boolean insert(final int index, final T e) {
		if(isBadIndex(index)) return false;
		final DoubleNode<T> node = getNode(index);
		node.setPrevNode(new DoubleNode<>(node.prevNode(), node, e));
		node.prevNode().prevNode().setNextNode(node.prevNode());
		size++;
		return true;
	}
	
	@Override
	public Iterator<T> iterator(){
		return new Iterator<>() {
		
			private DoubleNode<T> node = HEAD.nextNode();
			
			@Override
			public void next() { node = node.nextNode(); }

			@Override
			public T get() { return node.getValue(); }

			@Override
			public boolean set(T value) { 
				node.setValue(value); 
				return true;
			}

			@Override
			public boolean isEnd() { return node == FOOT; }
		};
	}
	
	@Override
	public boolean addFirst(final T e) {
		final DoubleNode<T> node = new DoubleNode<>(HEAD, HEAD.nextNode(), e);
		HEAD.nextNode().setPrevNode(node);
		HEAD.setNextNode(node);
		size++;
		return true;
	}
	
	@Override
	public boolean removeFirst() {
		if(size == 0) {
			System.cerr.$(new String("error nothing to remove: size = 0"));
			return false;
		}
		HEAD.nextNode().nextNode().setPrevNode(HEAD);
		HEAD.setNextNode(HEAD.nextNode().nextNode());
		size--;
		return true;
	}
	
	@Override
	public boolean removeLast() {
		if(size == 0) {
			System.cerr.$(new String("error nothing to remove: size = 0"));
			return false;
		}
		FOOT.prevNode().prevNode().setNextNode(FOOT);
		FOOT.setPrevNode(FOOT.prevNode().prevNode());
		size--;
		return true;
	}
	
	private DoubleNode<T> getNode(final int index) {
		DoubleNode<T> node = null;
		if(index < size / 2) {
			node = HEAD.nextNode();
			for(int i = 0; i < index; i++) node = node.nextNode();
		}
		else {
			node = FOOT.prevNode();
			for(int i = size - 1; i > index; i--) node = node.prevNode();
		}
		return node;
	}
	
	private boolean isBadIndex(final int index) {
		if(index >= size) {
			System.cerr.$(new String("bad index : size=")).$(size);
			System.cerr.$(new String(" / index=")).$(index).endl();
			return true;
		}
		if(index < 0) {
			System.cerr.$(new String("bad index: index < 0 : index =")).$(index).endl();
			return true;
		}
		return false;
	}

}
