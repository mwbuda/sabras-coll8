package sabras.coll8;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public interface SetMultiMap<K,V> extends MultiMap<K, V, Set<V>> {
	@Override
	public default Set<V> put(K k, Collection<V> vs) {
		return this.put(k, new HashSet<>(vs)) ;
	}
}
