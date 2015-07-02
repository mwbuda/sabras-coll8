package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.SetMultiMap;

public interface GroupSetClassification<X,K,V> 
extends GroupClassification<X, K, V, Set<V>> {

	public static <X,K,V> GroupSetClassification<X,K,V> ofKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupSetClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv
	) {
		return factory.apply(ck, cv) ;
	}
	public static <X,K> GroupSetClassification<X,K,X> ofKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupSetClassification<X,K,X>> factory,
		Function<X,K> ck
	) {
		return GroupSetClassification.ofKeysAndValues(factory, ck, v -> v) ;
	}
	
	public static <X,K,V> SetMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupSetClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Iterable<X> xs
	) {
		return GroupSetClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V> SetMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupSetClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Iterator<X> xs
	) {
		return GroupSetClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V> SetMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupSetClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Spliterator<X> xs
	) {
		return GroupSetClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V> SetMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupSetClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Stream<X> xs
	) {
		return GroupSetClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	
	public static <X,K extends Iterable<X>> SetMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupSetClassification<X,K,X>> factory,
		Function<X,K> ck,
		Iterable<X> xs
	) {
		return GroupSetClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K extends Iterable<X>> SetMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupSetClassification<X,K,X>> factory,
		Function<X,K> ck,
		Iterator<X> xs
	) {
		return GroupSetClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K extends Iterable<X>> SetMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupSetClassification<X,K,X>> factory,
		Function<X,K> ck,
		Spliterator<X> xs
	) {
		return GroupSetClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K extends Iterable<X>> SetMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupSetClassification<X,K,X>> factory,
		Function<X,K> ck,
		Stream<X> xs
	) {
		return GroupSetClassification.ofKeys(factory, ck).apply(xs) ;
	}
	
	public abstract SetMultiMap<K, V> apply(Iterable<X> xs);
	public abstract SetMultiMap<K, V> apply(Iterator<X> xs);
	public abstract SetMultiMap<K, V> apply(Spliterator<X> xs);
	public abstract SetMultiMap<K, V> apply(Stream<X> xs);
	
}
