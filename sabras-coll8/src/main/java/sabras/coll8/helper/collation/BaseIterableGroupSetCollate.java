package sabras.coll8.helper.collation;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.HashSetMultiMap;
import sabras.coll8.collection.SetMultiMap;

public final class BaseIterableGroupSetCollate<X,K,V> 
extends AbstractBaseIterableGroupCollate<X,K,V,Set<V>,SetMultiMap<K,V>>
implements IterableGroupSetCollate<X, K, V>  {
	
	public static <X,K,V> IterableGroupSetCollate<X, K, V> parrallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new BaseIterableGroupSetCollate<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> IterableGroupSetCollate<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new BaseIterableGroupSetCollate<X,K,V>(ck,cv,false) ;
	}
	
	public static <K,V> IterableGroupSetCollate<K, K, V> parallelOfValues(Function<K,V> cv) {
		return BaseIterableGroupSetCollate.parrallelOf(k -> k, cv) ;
	}
	public static <K,V> IterableGroupSetCollate<K, K, V> sequentialOfValues(Function<K,V> cv) {
		return BaseIterableGroupSetCollate.sequentialOf(k -> k, cv) ;
	}
	
	public static <K,V> IterableGroupSetCollate<V, K, V> parallelOfKeys(Function<V,K> ck) {
		return BaseIterableGroupSetCollate.parrallelOf(ck, v -> v) ;
	}
	public static <K,V> IterableGroupSetCollate<V, K, V> sequentialOfKeys(Function<V,K> ck) {
		return BaseIterableGroupSetCollate.sequentialOf(ck, v -> v) ;
	}
	
	public static <K,V> SetMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BaseIterableGroupSetCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BaseIterableGroupSetCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BaseIterableGroupSetCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BaseIterableGroupSetCollate.parallelOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BaseIterableGroupSetCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BaseIterableGroupSetCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BaseIterableGroupSetCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BaseIterableGroupSetCollate.sequentialOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> SetMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BaseIterableGroupSetCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BaseIterableGroupSetCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BaseIterableGroupSetCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BaseIterableGroupSetCollate.parallelOfKeys(cv).apply(vs) ;
	}
	
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BaseIterableGroupSetCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BaseIterableGroupSetCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BaseIterableGroupSetCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BaseIterableGroupSetCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	
	private BaseIterableGroupSetCollate(Function<X,K> ck, Function<X,V> cv, Boolean p ) {
		super(ck,cv,p) ;
	}

	@Override
	protected SetMultiMap<K, V> createMultiMap() {
		return new HashSetMultiMap<>() ;
	}
}
