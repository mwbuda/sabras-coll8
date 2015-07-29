package sabras.coll8.helper.classification;

import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.MultiMap;

public interface GroupClassification<X,K,V,C extends Collection<V>>
extends Classification<X, K, C> {
	
	public static <X,K,V,C extends Collection<V>> MultiMap<K,V,C> classify(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck, Function<X,V> cv, Iterable<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	public static <X,K,V,C extends Collection<V>> MultiMap<K,V,C> classify(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck, Function<X,V> cv, Iterator<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	public static <X,K,V,C extends Collection<V>> MultiMap<K,V,C> classify(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck, Function<X,V> cv, Spliterator<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	public static <X,K,V,C extends Collection<V>> MultiMap<K,V,C> classify(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck, Function<X,V> cv, Stream<X> xs
	) {
		return factory.apply(ck,cv).apply(xs) ;
	}
	
	public static <X,K,C extends Collection<X>> MultiMap<K,X,C> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck, Iterable<X> xs
	) {
		return factory.apply(ck, x -> x).apply(xs) ;
	}
	public static <X,K,C extends Collection<X>> MultiMap<K,X,C> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck,
		Iterator<X> xs
	) {
		return factory.apply(ck, x -> x).apply(xs) ;
	}
	public static <X,K,C extends Collection<X>> MultiMap<K,X,C> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck,
		Spliterator<X> xs
	) {
		return factory.apply(ck, x -> x).apply(xs) ;
	}
	public static <X,K,C extends Collection<X>> MultiMap<K,X,C> classifyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck,
		Stream<X> xs
	) {
		return factory.apply(ck, x -> x).apply(xs) ;
	}
	
	public abstract MultiMap<K, V, C> apply(Iterable<X> xs);
	public abstract MultiMap<K, V, C> apply(Iterator<X> xs);
	public abstract MultiMap<K, V, C> apply(Spliterator<X> xs);
	public abstract MultiMap<K, V, C> apply(Stream<X> xs);
	
}
