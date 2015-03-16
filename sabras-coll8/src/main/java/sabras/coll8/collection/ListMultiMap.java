package sabras.coll8.collection;

import java.util.List;

import sabras.coll8.helper.CollectConvert;

public interface ListMultiMap<K,V> extends MultiMap<K, V, List<V>> {
	@Override
	public default List<V> put(K k, Iterable<V> vs) {
		return this.put(k, CollectConvert.newList(vs)) ;
	}
}
