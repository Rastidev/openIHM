package openihm.api.utils;

import openihm.api.exception.BadIndexListException;
import openihm.api.lang.Object;

public interface List<T> {

	/*
	 * renvoie la taille de la liste
	 * $value @T la valeur $prevnode @DoubleNode le noeud precedent $nextnode @DoubleNode le prochain noeud
	 */
	    int size();
	    
	    T get(int index);

	    boolean set(int index, T e);
	    
	    boolean resize(int size);
	    
	    default Iterator<T> iterator(){
	    	return new Iterator<>() {
	    		int ind = 0;
				@Override
				public void next() { ind++; }
				@Override
				public T get() { return List.this.get(ind); }
				@Override
				public boolean set(T value) { return List.this.set(ind, value); }
				@Override
				public boolean isEnd() { return ind >= size(); }
			};
	    }
	    
	    default boolean insert(final int index, final T e) {
	    	if(index >= size() || index < 0) {
	    		new BadIndexListException((Object) this, 1, size(), index);
	    		return false;
	    	}
	    	if(size() == 0 || !resize(size() + 1)) return false;
	    	final int OldSize = size() - 1;
	    	for(int i = OldSize - 1; i >= index; i--)
	    		if(!set(i + 1, get(i))) return false;
	    	return set(index, e);
	    }

	    default boolean remove(final int index) {
	    	if(index >= size() || index < 0) {
	    		new BadIndexListException((Object) this, 2, size(), index);
	    		return false;
	    	}
	    	final int NewSize = size() - 1;
	    	for(int i = index; i < NewSize; i++) 
	    		if(!set(i, get(i + 1))) return false;
	    	return resize(NewSize);
	    }
	    
	    default boolean add(final T e) { return resize(size() + 1) && set(size() - 1, e); }
	    
	    default boolean addFirst(final T e) {
	    	if(size() != 0) return insert(0, e);
	    	return add(e);
	    	
	    }
	    
	    default boolean removeLast() { return remove(size() - 1); }
	    
	    default boolean removeFirst() { return remove(0); }
	    
	    default Array<T> getArray() { return new Array<>(this); }
	    
		default boolean isEmpty() {
			for(final Iterator<T> it = iterator(); !it.isEnd() ; it.next()) if(it.get() != null) return false;
			return true;
		}


		default boolean contains(final T o) {
			for(final Iterator<T> it = iterator(); !it.isEnd() ; it.next()) 
				if(it.get() == o) return true;
			return false;
		}

		default int indexOf(final T o) {
			final Iterator<T> it = iterator();
			for(int i = 0; !it.isEnd() ; i++) {
				if(it.get() == o) return i;
				it.next();
			}
			return size();
		}

		default int lastIndexOf(final T o) {
			int ind = size();
			final Iterator<T> it = iterator();
			for(int i = 0; !it.isEnd() ; i++) {
				if(it.get() == o) ind = i;
				it.next();
			}
			return ind;
		}
		
		default boolean containsValue(final T o) {
			if(o == null) return contains(null);
			for(final Iterator<T> it = iterator(); !it.isEnd() ; it.next()) 
				if(((Object) it.get())._eql_((Object) o)) return true;
			return false;
		}

		default int indexOfValue(final T o) {
			if(o == null) return indexOf(null);
			final Iterator<T> it = iterator();
			for(int i = 0; !it.isEnd() ; i++) {
				if(((Object) it.get())._eql_((Object) o)) return i;
				it.next();
			}
			return size();
		}

		default int lastIndexOfValue(final T o) {
			if(o == null) return lastIndexOf(null);
			int ind = size();
			final Iterator<T> it = iterator();
			for(int i = 0; !it.isEnd() ; i++) {
				if(((Object) it.get())._eql_((Object) o)) ind = i;
				it.next();
			}
			return ind;
		}
		
		default boolean clear() { return resize(0); }
}
