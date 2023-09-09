package openihm.api.utils;

import openihm.api.exception.NullPointerException;
import openihm.api.lang.Object;
import openihm.api.lang.String;

public interface Map<K, V> {
	
	/*
	 * renvoie @int le nombre d'elements de la map
	 */
	public int size();
	
	/*
	 * renvoie @V la valeur associer a la clé
	 * $key @K la clé
	 * renvoie @V:null si il n'a pas trouvé
	 */
	public V get(K key);
	
	/*
	 * ajoute à une clé une valeur dans la map
	 * $key @K la cle et $value @V la valeur
	 * renvoie @boolean:true si cela à fonctionne sinon renvoie faux
	 */
	public boolean put(K key, V value);
	
	/*
	 * supprime la clé et sa valeur
	 * $key @K la clé
	 * renvoie @boolean:false si il n'a pas reussi a la supprimer sinon renvoie vrai
	 */
	public boolean remove(K key);
	
	/*
	 * renvoie la liste de tout les elements sous forme de pair <cle, valeur>
	 */
	List<Pair<K, V>> values();
	
	
	/*
	 * ajoute à une clé une valeur dans la map
	 * $pair @Pair<K, V> la pair <clé, valeur>
	 * renvoie vrai si cela à fonctionne sinon renvoie faux
	 */
	public default boolean put(final Pair<K, V> pair) {
		if(pair != null) return put(pair.getKey(), pair.getValue());
		new NullPointerException((Object) this, 0x00000001, new String("pair"));
		return false;
	}
	
	/*
	 * reinitialise la map
	 * renvoie vrai si cela a fonctionner
	 */
	public default boolean clear() {
		for(Iterator<Pair<K, V>> it  = values().iterator(); !it.isEnd(); it.next()) remove(it.get().getKey());
		return true;
	}
	
	/*
	 * test si il existe la valeur
	 * $value @V la valeur
	 * renvoie faux si il n'a pas trouvé sinon renvoie vrai
	 */
	public default boolean containsValue(final V value) {
		for(Iterator<Pair<K, V>> it = values().iterator(); !it.isEnd(); it.next()) if(it.get().getValue() == value) return true;
		return false;
	}
	
	/*
	 * test si il existe la clé
	 * $key @K la clé
	 * renvoie faux si il n'a pas trouvé sinon renvoie vrai
	 */
	public default boolean containesKey(final K key) {
		for(Iterator<Pair<K, V>> it = values().iterator(); !it.isEnd(); it.next()) if(it.get().getKey() == key) return true;
		return false;
	}
	
	/*
	 * renvoie la valeur associer a la clé
	 * $key @K la clé
	 * renvoie null si il n'a pas trouvé
	 */
	public default V getOrDefault(final K key, final V defaultValue) {
		return (containesKey(key)) ? get(key) : defaultValue;
	}
	
	/*
	 * ajoute à une clé une valeur si la clé n'existe pas dans la map
	 * $key @K la cle et $value @V la valeur
	 * renvoie vrai si cela à fonctionne sinon renvoie faux
	 */
	public default boolean putIfAbsent(final K key, final V value) {
		if(containesKey(key)) return false;
		return put(key, value);
	}
	
	/*
	 * remplace à une clé, une valeur si la clé existe dans la map
	 * $key @K la cle et $value @V la valeur
	 * renvoie vrai si cela à fonctionne sinon renvoie faux
	 */
	public default boolean replace(final K key, final V value) {
		if(!containesKey(key)) return false;
		return put(key, value);
	}
	
	/*
	 * remplace à une clé par une autre tout en gardant la valeur
	 * $key1 @K l'ancienne cle et $key2 @K la nouvelle clé
	 * renvoie vrai si cela à fonctionne sinon renvoie faux
	 */
	public default boolean replaceKey(final K key1, final K key2) {
		for(Iterator<Pair<K, V>> it = values().iterator(); !it.isEnd(); it.next()) if(it.get().getKey() == key1)
			return put(key2, it.get().getValue()) && remove(key1);
		return false;
	}
	
	/*
	 * remplace à une valeur par une autre valeur pour chaque la clé qui possede la valeur
	 * $value1 @V l'ancienne valeur et $value2 @V la nouvelle valeur
	 * renvoie vrai si cela à fonctionne sinon renvoie faux
	 */
	public default boolean replaceValue(final V value1, final V value2) {
		boolean isReplace = false;
		boolean noError = true;
		for(Iterator<Pair<K, V>> it = values().iterator(); !it.isEnd(); it.next()) if(it.get().getValue() == value1) {
			if(put(it.get().getKey(), value2)) isReplace = true;
			else noError = false;
		}
		return isReplace && noError;
	}
	
	public default boolean remove(final K key, final V value) {
		return get(key) == value && remove(key);
	}
	
	public default boolean remove(final Pair<K, V> pair) {
		if(pair != null) return remove(pair.getKey(), pair.getValue());
		new NullPointerException((Object) this, 2, new String("pair"));
		return false;
	}
	
	public default boolean removeValue(final V value) {
		boolean isRemove = false;
		boolean noError = true;
		for(Iterator<Pair<K, V>> it = values().iterator(); !it.isEnd(); it.next()) if(it.get().getValue() == value) {
			if(remove(it.get().getKey())) isRemove = true;
			else noError = false;
		}
		return isRemove && noError;
	}
	
	/*
	 * renvoie vrai si la map est vide
	 */
	public default boolean isEmpty() { return size() == 0; }
	
	
	
	
	
}
