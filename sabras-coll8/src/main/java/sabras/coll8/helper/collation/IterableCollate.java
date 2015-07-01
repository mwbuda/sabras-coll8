package sabras.coll8.helper.collation;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;

public interface IterableCollate<X, K, V> {

	public abstract Map<K, V> apply(Iterable<X> xs);

	public abstract Map<K, V> apply(Iterator<X> xs);

	public abstract Map<K, V> apply(Spliterator<X> xs);

	public abstract Map<K, V> apply(Stream<X> xs);

	default public Function<Iterable<X>, Map<K, V>> asTakeIterable() {
		return iterable -> this.apply(iterable) ;
	}

	default public Function<Iterator<X>, Map<K, V>> asTakeIterator() {
		return iterator -> this.apply(iterator) ;
	}

	default public Function<Spliterator<X>, Map<K, V>> asTakeSpliterator() {
		return spliterator -> this.apply(spliterator) ;
	}

	default public Function<Stream<X>, Map<K, V>> asTakeStream() {
		return stream -> this.apply(stream) ;
	}

}