package openihm.api.utils;

import openihm.api.exception.NullPointerException;
import openihm.api.lang.Object;
import openihm.api.lang.String;

public class TreeMap<K,V> extends Object implements Map<K, V>{
	
	private int size = 0;
	
	private final DoubleNode<Pair<Long, Pair<K, V>>> begin_node;
	
	public TreeMap() {
		begin_node = new DoubleNode<>();
		begin_node.setValue(new Pair<Long, Pair<K,V>>((long) -1, null));
	}
	
	//constructeur par copie
	public TreeMap(final TreeMap<K, V> map) {
		size = map.size;
		begin_node = new DoubleNode<>(map.begin_node);
	}
	
	
	public TreeMap(final Map<K, V> map) { this(map.values()); }
	
	public TreeMap(final List<Pair<K, V>> tab) {
		this();
		for(Iterator<Pair<K, V>> it = tab.iterator(); !it.isEnd(); it.next())
			put(it.get());
	}

	@Override
	public int size() { return size; }

	@Override
	public V get(K key) {
		final BinaryReader btr = new BinaryReader(((Object) key)._data_());
		DoubleNode<Pair<Long, Pair<K, V>>> node = this.begin_node;
		while(true) {
			final int r = btr.read();
			if(r == -1) break;
			else {
				if (r == 0) node = node.prevNode();
				else node = node.nextNode();
				if(node == null) return null;
				btr.setCursor(node.getValue().getKey() + 1);
			}
		}
		if(node.getValue().getValue() != null && key.equals(node.getValue().getValue().getKey())) return node.getValue().getValue().getValue();
		return null;
	}
	
	@Override
	public boolean put(K key, V value) {
		
		//verif
		if(key == null) {
			new NullPointerException(this, 3, new String("key"));
			return false;
		}
		
		//cherche le data le plus semblable
		final BinaryReader btr = new BinaryReader(((Object) key)._data_());
		DoubleNode<Pair<Long, Pair<K, V>>> node = this.begin_node;
		while(true) {
			final int r = btr.read();
			if(r == -1) {
				while(node.getValue().getValue() == null) {
					if (node.prevNode() != null) node = node.prevNode();
					else node = node.nextNode();
				}
				break;
			}
			else {
				if (r == 0 && node.prevNode() != null) node = node.prevNode();
				else if(node.nextNode() != null) node = node.nextNode();
				else break;
				btr.setCursor(node.getValue().getKey() + 1);
			}
		}
		final BinaryReader btrFindNode;
		if(node.getValue().getValue() == null) btrFindNode = new BinaryReader();
		else btrFindNode = new BinaryReader(((Object) node.getValue().getValue().getKey())._data_());
		
		//partie 2 trouver la différence
		btr.setCursor(0);
		
		node = begin_node;
		DoubleNode<Pair<Long, Pair<K, V>>> oldNode = null;
		long rang = node.getValue().getKey();
		long i = 0;
		
		int bit_btr = btr.read();
		int bit_btrFindNode = btrFindNode.read();
		while(bit_btr == bit_btrFindNode && bit_btr != -1) {
			if(rang < i) {
				oldNode = node;
				if(bit_btr == 0) node = node.prevNode();
				else node = node.nextNode();
				rang = node.getValue().getKey();
			}
			bit_btr = btr.read();
			bit_btrFindNode = btrFindNode.read();
			i++;
		}
		//partie 3 insersion des noeux et de la valeur
		final Pair<K, V> key_value = new Pair<>(key, value);
		final DoubleNode<Pair<Long, Pair<K, V>>> newNode;
		final long keySize = Data.sizeBit(btr.getData());
		if(bit_btrFindNode == -1) {
			//meme node
			if(bit_btr == -1) {
				if(node == begin_node && node.getValue().getValue() == null) this.size += 1;
				node.getValue().setValue(key_value);
			}
			//la data trouvee trop courte
			else {
				newNode = new DoubleNode<>(null, null, new Pair<>(keySize - 1, key_value));
				if(bit_btr == 0) node.setPrevNode(newNode);
				else node.setNextNode(newNode);
				this.size += 1;
			}
			return true;
		}
		//key trop courte
		else if(bit_btr == -1) {
			if(i == rang || btr.size() == 0) {
				node.getValue().setValue(key_value);
				this.size += 1;
				return true;
			}
			if(bit_btrFindNode == 0) newNode = new DoubleNode<>(node, null, new Pair<>(keySize - 1, key_value));
			else newNode = new DoubleNode<>(null, node, new Pair<>(keySize, key_value));
		}
		else {
			if(bit_btr == 0) {
				if(rang < i) {
					oldNode = node;
					node = node.prevNode();
				}
				newNode = new DoubleNode<>(new DoubleNode<>(null, null, new Pair<>(keySize - 1, key_value)), node);
			}
			else {
				if(rang < i) {
					oldNode = node;
					node = node.nextNode();
				}
				newNode = new DoubleNode<>(node, new DoubleNode<>(null, null, new Pair<>(keySize - 1, key_value)));
			}
			newNode.setValue(new Pair<>(i - 1, null));
		}
		if(oldNode.prevNode() == node) oldNode.setPrevNode(newNode);
		else oldNode.setNextNode(newNode);
		this.size += 1;
		return true;
	}

	@Override
	public boolean remove(K key) {
		final BinaryReader btr = new BinaryReader(((Object) key)._data_());
		DoubleNode<Pair<Long, Pair<K, V>>> node = this.begin_node;
		DoubleNode<Pair<Long, Pair<K, V>>> oldNode = null;
		DoubleNode<Pair<Long, Pair<K, V>>> oldOldNode = null;
		while(true) {
			final int r = btr.read();
			if(r == -1) break;
			else {
				oldOldNode = oldNode;
				oldNode = node;
				if (r == 0) node = node.prevNode();
				else node = node.nextNode();
				if(node == null) return false;
				btr.setCursor(node.getValue().getKey() + 1);
			}
		}
		if(node.getValue().getValue() != null && key.equals(node.getValue().getValue().getKey())) {
			if(oldNode == null) {
				node.getValue().setValue(null);
				size -= 1;
				return true;
			}
			final DoubleNode<Pair<Long, Pair<K, V>>> saveNode;
			if(node.prevNode() != null) {
				if(node.nextNode() != null) {
					node.getValue().setValue(null);
					size -= 1;
					return true;
				}
				else saveNode = node.prevNode();
			}
			else if(node.nextNode() != null) saveNode = node.nextNode();
			else {
				if(oldNode.getValue().getValue() == null && oldOldNode != null) {
					if(oldNode.prevNode() == node) saveNode = oldNode.nextNode();
					else saveNode = oldNode.prevNode();
					node = oldNode;
					oldNode = oldOldNode;
				}
				else saveNode = null;
			}
			if(oldNode.prevNode() == node) oldNode.setPrevNode(saveNode);
			else oldNode.setNextNode(saveNode);
			size -= 1;
			return true;
		}
		return false;
	}
	

	@Override
	public boolean clear() {
		 size = 0;
		 begin_node.setNextNode(null);
		 begin_node.setPrevNode(null);
		 begin_node.getValue().setValue(null);
		 return true;
	}

	@Override
	public List<Pair<K, V>> values() { return valuesCore(new BasicList<>() , begin_node).getArray(); }
	
	private List<Pair<K, V>> valuesCore(final List<Pair<K, V>> list, final DoubleNode<Pair<Long, Pair<K, V>>> node) {
		if(node.getValue().getValue() != null) list.add(node.getValue().getValue());
		if(node.prevNode() != null) valuesCore(list, node.prevNode());
		if(node.nextNode() != null) valuesCore(list, node.nextNode());
		return list;
	}
	
	public String _className_(){ return new String("openihm.api.utils.TreeMap"); }
	
}
