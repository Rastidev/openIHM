package openihm.api.utils;

public class IdMap<V> implements Map<Integer, V> {
	
	private List<Pair<Integer, V>> array = new ArrayList<>();

	@Override
	public int size() { return array.size(); }

	@Override
	public V get(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean put(Integer key, V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Integer key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Pair<Integer, V>> values() { return array; }

}
