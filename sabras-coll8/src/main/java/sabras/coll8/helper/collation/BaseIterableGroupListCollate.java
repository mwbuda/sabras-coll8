package sabras.coll8.helper.collation;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.LinkedListMultiMap;
import sabras.coll8.collection.ListMultiMap;

public final class BaseIterableGroupListCollate<X,K,V> 
extends AbstractBaseIterableGroupCollate<X,K,V,List<V>,ListMultiMap<K,V>>
implements IterableGroupListCollate<X, K, V>  {
	
	public static <X,K,V> IterableGroupListCollate<X, K, V> parrallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new BaseIterableGroupListCollate<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> IterableGroupListCollate<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new BaseIterableGroupListCollate<X,K,V>(ck,cv,false) ;
	}
	
	public static <K,V> IterableGroupListCollate<K, K, V> parallelOfValues(Function<K,V> cv) {
		return BaseIterableGroupListCollate.parrallelOf(k -> k, cv) ;
	}
	public static <K,V> IterableGroupListCollate<K, K, V> sequentialOfValues(Function<K,V> cv) {
		return BaseIterableGroupListCollate.sequentialOf(k -> k, cv) ;
	}
	
	public static <K,V> IterableGroupListCollate<V, K, V> parallelOfKeys(Function<V,K> ck) {
		return BaseIterableGroupListCollate.parrallelOf(ck, v -> v) ;
	}
	public static <K,V> IterableGroupListCollate<V, K, V> sequentialOfKeys(Function<V,K> ck) {
		return BaseIterableGroupListCollate.sequentialOf(ck, v -> v) ;
	}
	
	public static <K,V> ListMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BaseIterableGroupListCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BaseIterableGroupListCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BaseIterableGroupListCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelCollationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BaseIterableGroupListCollate.parallelOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BaseIterableGroupListCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BaseIterableGroupListCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BaseIterableGroupListCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BaseIterableGroupListCollate.sequentialOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> ListMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BaseIterableGroupListCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BaseIterableGroupListCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BaseIterableGroupListCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelCollationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BaseIterableGroupListCollate.parallelOfKeys(cv).apply(vs) ;
	}
	
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BaseIterableGroupListCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BaseIterableGroupListCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BaseIterableGroupListCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialCollationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BaseIterableGroupListCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	
	private BaseIterableGroupListCollate(Function<X,K> ck, Function<X,V> cv, Boolean p ) {
		super(ck,cv,p) ;
	}

	@Override
	protected ListMultiMap<K, V> createMultiMap() {
		return new LinkedListMultiMap<>() ;
	}
}
