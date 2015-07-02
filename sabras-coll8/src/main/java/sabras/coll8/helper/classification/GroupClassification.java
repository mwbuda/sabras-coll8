package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.MultiMap;

public interface GroupClassification<X,K,V,C extends Iterable<V>>
extends Classification<X, K, C> {

	public static <X,K,V,C extends Iterable<V>> GroupClassification<X,K,V,C> ofKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck,
		Function<X,V> cv
	) {
		return factory.apply(ck,cv) ;
	}
	public static <X,K,C extends Iterable<X>> GroupClassification<X,K,X,C> ofKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck
	) {
		return GroupClassification.ofKeysAndValues(factory, ck, v -> v) ;
	}
	
	public static <X,K,V,C extends Iterable<V>> MultiMap<K,V,C> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Iterable<X> xs
	) {
		return GroupClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V,C extends Iterable<V>> MultiMap<K,V,C> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Iterator<X> xs
	) {
		return GroupClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V,C extends Iterable<V>> MultiMap<K,V,C> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Spliterator<X> xs
	) {
		return GroupClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	public static <X,K,V,C extends Iterable<V>> MultiMap<K,V,C> applyForKeysAndValues(
		BiFunction<Function<X,K>, Function<X,V>, GroupClassification<X,K,V,C>> factory,
		Function<X,K> ck,
		Function<X,V> cv,
		Stream<X> xs
	) {
		return GroupClassification.ofKeysAndValues(factory, ck, cv).apply(xs) ;
	}
	
	public static <X,K,C extends Iterable<X>> MultiMap<K,X,C> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck,
		Iterable<X> xs
	) {
		return GroupClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K,C extends Iterable<X>> MultiMap<K,X,C> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck,
		Iterator<X> xs
	) {
		return GroupClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K,C extends Iterable<X>> MultiMap<K,X,C> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck,
		Spliterator<X> xs
	) {
		return GroupClassification.ofKeys(factory, ck).apply(xs) ;
	}
	public static <X,K,C extends Iterable<X>> MultiMap<K,X,C> applyForKeys(
		BiFunction<Function<X,K>, Function<X,X>, GroupClassification<X,K,X,C>> factory,
		Function<X,K> ck,
		Stream<X> xs
	) {
		return GroupClassification.ofKeys(factory, ck).apply(xs) ;
	}
	
	public abstract MultiMap<K, V, C> apply(Iterable<X> xs);
	public abstract MultiMap<K, V, C> apply(Iterator<X> xs);
	public abstract MultiMap<K, V, C> apply(Spliterator<X> xs);
	public abstract MultiMap<K, V, C> apply(Stream<X> xs);
	
}
