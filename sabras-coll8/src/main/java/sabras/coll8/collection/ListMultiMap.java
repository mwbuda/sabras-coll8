package sabras.coll8.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface ListMultiMap<K,V> extends MultiMap<K, V, List<V>> {
	@Override
	public default List<V> put(K k, Collection<V> vs) {
		return this.put(k, new ArrayList<>(vs)) ;
	}
}
