package sabras.coll8.collection;

import java.util.Map;

public interface DefaultValueMap<K,V> 
extends Map<K, V> {

	public V defaultValue(K key) ;
	
}
