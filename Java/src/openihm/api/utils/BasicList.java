package openihm.api.utils;

import openihm.api.system.System;

public class BasicList<T> implements List<T>{
	
	private int size = 0;
	
	private BasicNode<T> firstNode = null;
	
	private BasicNode<T> lastNode = null;
	
	
	public BasicList(){}
	
	public BasicList(final int size, final T default_value){
		if(size < 0) {
			System.cerr.$(String.$("bad size: size < 0 : size =")).$(size).endl();
			this.size = 0;
		}
		else if(size > 0) {
			firstNode = new BasicNode<T>(default_value);
			BasicNode<T> node = firstNode;
			for(int i = 0; i < size; i++) {
				node.setNextNode(new BasicNode<>(default_value));
				node = node.nextNode();
			}
			lastNode = node;
			this.size = size;
		}
	}
	
	public BasicList(final int size) { this(size, null); }
	
	private boolean isBadIndex(final int index) {
		if(index >= size) {
			System.cerr.$(String.$("bad index : size=")).$(size);
			System.cerr.$(String.$(" / index=")).$(index).endl();
			return true;
		}
		if(index < 0) {
			System.cerr.$(String.$("bad index: index < 0 : index =")).$(index).endl();
			return true;
		}
		return false;
	}
	
	@Override
	public int size() { return size; }

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			private BasicNode<T> node = firstNode;

			@Override
			public void next() {
				node = node.nextNode(); 
			}

			@Override
			public T get() { return node.getValue(); }

			@Override
			public boolean isEnd() { return node == null; }

			@Override
			public boolean set(T value) {
				node.setValue(value);
				return true;
			}
			
		};
	}

	@Override
	public boolean add(T e) {
		if(lastNode == null) {
			firstNode = new BasicNode<>(e);
			lastNode = firstNode;
			size++;
			return true;
		}
		BasicNode<T> node = new BasicNode<>(e);
		lastNode.setNextNode(node);
		lastNode = node;
		size++;
		return true;
	}

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
	public boolean insert(int index, T e) {
		if(isBadIndex(index)) return false;
		if(index == 0) {
			firstNode = new BasicNode<T>(e, firstNode);
			if(size == 1) lastNode = firstNode.nextNode();
		}
		else {
			BasicNode<T> node = getNode(index - 1);
			node.setNextNode(new BasicNode<T>(e, node.nextNode()));
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(int index) {
		if(isBadIndex(index)) return false;
		if(index == 0) firstNode = firstNode.nextNode();
		if(size <= 2) lastNode = firstNode;
		else {
			BasicNode<T> node = getNode(index - 1);
			node.setNextNode(node.nextNode().nextNode());
			if(index == size - 1) lastNode = node;
		}
		size--;
		return true;
	}

	@Override
	public boolean resize(int size) {
		if(size < 0) {
			System.cerr.$(String.$("bad size: size < 0 : size =")).$(size).endl();
			return false;
		}
		if(size == this.size) return true;
		if(this.size == 0) {
			firstNode = new BasicNode<T>(null, null);
			lastNode = firstNode;
		}
		else if(size == 0) firstNode = null;
		if(size <= 1) lastNode = firstNode;
		else if(size < this.size) {
			BasicNode<T> node = getNode(size - 1);
			node.setNextNode(null);
			lastNode = node;
		}
		else {
			final int indStop = size - this.size;
			for(int i = 0; i < indStop; i++) {
				lastNode.setNextNode(new BasicNode<>());
				lastNode = lastNode.nextNode();
			}
		}
		this.size = size;
		return true;
	}
	
	@Override
	public boolean addFirst(T e) {
		firstNode = new BasicNode<>(e, firstNode);
		if(size == 0) lastNode = firstNode;
		size++;
		return true;
	}

	@Override
	public boolean removeLast() {
		if(size == 0) {
			System.cerr.$(String.$("error nothing to remove: size = 0"));
			return false;
		}
		if(size <= 2) {
			if(size == 1) firstNode = null;
			lastNode = firstNode;
		}
		else {
			BasicNode<T> node = getNode(size - 2);
			node.setNextNode(null);
			lastNode = node;
		}
		size--;
		return true;
	}

	@Override
	public boolean removeFirst() {
		if(size == 0) {
			System.cerr.$(String.$("error nothing to remove: size = 0"));
			return false;
		}
		if(size <= 2) {
			if(size == 1) lastNode = null;
			firstNode = lastNode;
		}
		else firstNode = firstNode.nextNode();
		size--;
		return true;
	}
	
	private BasicNode<T> getNode(final int index) {
		BasicNode<T> node = firstNode;
		for(int i = 0; i < index; i++) node = node.nextNode();
		return node;
	}
	
	public BasicNode<T> getFirstNode(final int index) { return firstNode; }
	
	public BasicNode<T> getLastNode(final int index) { return lastNode; }

}
