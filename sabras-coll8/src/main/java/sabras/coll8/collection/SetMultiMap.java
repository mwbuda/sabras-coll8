package sabras.coll8.collection;

import java.util.Set;

import sabras.coll8.helper.IterMaker;

public interface SetMultiMap<K,V> extends MultiMap<K, V, Set<V>> {
	@Override
	public default Set<V> put(K k, Iterable<V> vs) {
		return this.put(k, IterMaker.newSet(vs)) ;
	}
}
