package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

import sabras.coll8.collection.ListMultiMap;

public interface GroupListClassification<X,K,V> 
extends GroupClassification<X, K, V, List<V>> {

	public abstract ListMultiMap<K, V> apply(Iterable<X> xs);

	public abstract ListMultiMap<K, V> apply(Iterator<X> xs);

	public abstract ListMultiMap<K, V> apply(Spliterator<X> xs);

	public abstract ListMultiMap<K, V> apply(Stream<X> xs);
	
}
