package sabras.coll8.helper.collation;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Stream;

import sabras.coll8.collection.SetMultiMap;

public interface IterableGroupSetCollate<X,K,V> 
extends IterableGroupCollate<X, K, V, Set<V>> {

	public abstract SetMultiMap<K, V> apply(Iterable<X> xs);

	public abstract SetMultiMap<K, V> apply(Iterator<X> xs);

	public abstract SetMultiMap<K, V> apply(Spliterator<X> xs);

	public abstract SetMultiMap<K, V> apply(Stream<X> xs);
	
}
