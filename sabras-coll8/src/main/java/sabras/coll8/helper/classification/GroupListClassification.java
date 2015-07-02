package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.ListMultiMap;

public interface GroupListClassification<X,K,V> 
extends GroupClassification<X, K, V, List<V>> {
	
	public static <X,K,V> GroupListClassification<X,K,V> ofKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupListClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv
	) {
		return factory.apply(ck, cv) ;
	}
	public static <X,K> GroupListClassification<X,K,X> ofKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupListClassification<X,K,X>> factory,
		Function<X,K> ck
	) {
		return GroupListClassification.ofKeysAndValues(factory, ck, v -> v) ;
	}
	
	public static <X,K,V> ListMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupListClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Iterable<X> xs
	) {
		return GroupListClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V> ListMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupListClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Iterator<X> xs
	) {
		return GroupListClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V> ListMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupListClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Spliterator<X> xs
	) {
		return GroupListClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V> ListMultiMap<K,V> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupListClassification<X,K,V>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Stream<X> xs
	) {
		return GroupListClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	
	public static <X,K extends Iterable<X>> ListMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupListClassification<X,K,X>> factory,
		Function<X,K> ck,
		Iterable<X> xs
	) {
		return GroupListClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K extends Iterable<X>> ListMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupListClassification<X,K,X>> factory,
		Function<X,K> ck,
		Iterator<X> xs
	) {
		return GroupListClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K extends Iterable<X>> ListMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupListClassification<X,K,X>> factory,
		Function<X,K> ck,
		Spliterator<X> xs
	) {
		return GroupListClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K extends Iterable<X>> ListMultiMap<K,X> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupListClassification<X,K,X>> factory,
		Function<X,K> ck,
		Stream<X> xs
	) {
		return GroupListClassification.ofKeys(factory, ck).apply(xs) ;
	}

	public abstract ListMultiMap<K, V> apply(Iterable<X> xs);
	public abstract ListMultiMap<K, V> apply(Iterator<X> xs);
	public abstract ListMultiMap<K, V> apply(Spliterator<X> xs);
	public abstract ListMultiMap<K, V> apply(Stream<X> xs);
	
}
