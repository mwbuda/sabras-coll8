package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.LinkedListMultiMap;
import sabras.coll8.collection.ListMultiMap;

public final class LinkedListGroupClassification<X,K,V> 
extends AbstractBaseGroupClassification<X,K,V,List<V>,ListMultiMap<K,V>>
implements GroupListClassification<X, K, V>  {
	
	public static <X,K,V> GroupListClassification<X, K, V> parrallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new LinkedListGroupClassification<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> GroupListClassification<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new LinkedListGroupClassification<X,K,V>(ck,cv,false) ;
	}
	
	public static <K,V> GroupListClassification<V, K, V> parallelOfKeys(Function<V,K> ck) {
		return LinkedListGroupClassification.parrallelOf(ck, v -> v) ;
	}
	public static <K,V> GroupListClassification<V, K, V> sequentialOfKeys(Function<V,K> ck) {
		return LinkedListGroupClassification.sequentialOf(ck, v -> v) ;
	}
	
	public static <K,V> ListMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return LinkedListGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return LinkedListGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return LinkedListGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return LinkedListGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	
	public static <K,V> ListMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return LinkedListGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return LinkedListGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return LinkedListGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> ListMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return LinkedListGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	
	private LinkedListGroupClassification(Function<X,K> ck, Function<X,V> cv, Boolean p ) {
		super(ck,cv,p) ;
	}

	@Override
	protected ListMultiMap<K, V> createMultiMap() {
		return new LinkedListMultiMap<>() ;
	}
}
