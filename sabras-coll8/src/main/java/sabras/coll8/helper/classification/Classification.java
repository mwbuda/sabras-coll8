package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Classification<X, K, V> {
	
	public static <X,K,V> Map<K,V> classify(
		BiFunction<Function<X,K>, Function<X,V>, Classification<X,K,V>> factory,
		Function<X,K> ck, Function<X,V> cv, Iterable<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	public static <X,K,V> Map<K,V> classify(
		BiFunction<Function<X,K>, Function<X,V>, Classification<X,K,V>> factory,
		Function<X,K> ck, Function<X,V> cv, Iterator<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	public static <X,K,V> Map<K,V> classify(
		BiFunction<Function<X,K>, Function<X,V>, Classification<X,K,V>> factory,
		Function<X,K> ck, Function<X,V> cv, Spliterator<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	public static <X,K,V> Map<K,V> classify(
		BiFunction<Function<X,K>, Function<X,V>, Classification<X,K,V>> factory,
		Function<X,K> ck, Function<X,V> cv, Stream<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	
	public static <X,K> Map<K,X> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, Classification<X,K,X>> factory,
		Function<X,K> ck, Iterable<X> xs
	) {
		return factory.apply(ck,x -> x).apply(xs) ;
	}
	public static <X,K> Map<K,X> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, Classification<X,K,X>> factory,
		Function<X,K> ck, Iterator<X> xs
	) {
		return factory.apply(ck,x -> x).apply(xs) ;
	}
	public static <X,K> Map<K,X> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, Classification<X,K,X>> factory,
		Function<X,K> ck, Spliterator<X> xs
	) {
		return factory.apply(ck,x -> x).apply(xs) ;
	}
	public static <X,K> Map<K,X> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, Classification<X,K,X>> factory,
		Function<X,K> ck, Stream<X> xs
	) {
		return factory.apply(ck,x -> x).apply(xs) ;
	}
		
	public static <X,V> Map<X,V> classifyForValues(
		BiFunction<Function<X,X>, Function<X,V>, Classification<X,X,V>> factory,
		Function<X,V> cv, Iterable<X> xs
	) {
		return factory.apply(x -> x,cv).apply(xs) ;
	}
	public static <X,V> Map<X,V> classifyForValues(
		BiFunction<Function<X,X>, Function<X,V>, Classification<X,X,V>> factory,
		Function<X,V> cv, Iterator<X> xs
	) {
		return factory.apply(x -> x,cv).apply(xs) ;
	}
	public static <X,V> Map<X,V> classifyForValues(
		BiFunction<Function<X,X>, Function<X,V>, Classification<X,X,V>> factory,
		Function<X,V> cv, Spliterator<X> xs
	) {
		return factory.apply(x -> x,cv).apply(xs) ;
	}
	public static <X,V> Map<X,V> classifyForValues(
		BiFunction<Function<X,X>, Function<X,V>, Classification<X,X,V>> factory,
		Function<X,V> cv, Stream<X> xs
	) {
		return factory.apply(x -> x,cv).apply(xs) ;
	}
	
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