package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;

import sabras.coll8.collection.MultiMap;

public interface GroupClassification<X,K,V,C extends Iterable<V>>
extends Classification<X, K, C> {

	public abstract MultiMap<K, V, C> apply(Iterable<X> xs);

	public abstract MultiMap<K, V, C> apply(Iterator<X> xs);

	public abstract MultiMap<K, V, C> apply(Spliterator<X> xs);

	public abstract MultiMap<K, V, C> apply(Stream<X> xs);
	
}
