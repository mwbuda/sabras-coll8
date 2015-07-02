package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.stream.Stream;

import sabras.coll8.collection.SetMultiMap;

public interface GroupSetClassification<X,K,V> 
extends GroupClassification<X, K, V, Set<V>> {

	public abstract SetMultiMap<K, V> apply(Iterable<X> xs);

	public abstract SetMultiMap<K, V> apply(Iterator<X> xs);

	public abstract SetMultiMap<K, V> apply(Spliterator<X> xs);

	public abstract SetMultiMap<K, V> apply(Stream<X> xs);
	
}
