package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;

import sabras.coll8.collection.HashSetMultiMap;
import sabras.coll8.collection.SetMultiMap;

public final class HashSetGroupClassification<X,K,V> 
extends AbstractBaseGroupClassification<X,K,V,Set<V>,SetMultiMap<K,V>>
implements GroupSetClassification<X, K, V>  {
	
	public static <X,K,V> GroupSetClassification<X, K, V> parrallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new HashSetGroupClassification<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> GroupSetClassification<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new HashSetGroupClassification<X,K,V>(ck,cv,false) ;
	}
	
	public static <K,V> GroupSetClassification<V, K, V> parallelOfKeys(Function<V,K> ck) {
		return HashSetGroupClassification.parrallelOf(ck, v -> v) ;
	}
	public static <K,V> GroupSetClassification<V, K, V> sequentialOfKeys(Function<V,K> ck) {
		return HashSetGroupClassification.sequentialOf(ck, v -> v) ;
	}
	
	public static <K,V> SetMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return HashSetGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return HashSetGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return HashSetGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> parallelClassificationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return HashSetGroupClassification.parallelOfKeys(cv).apply(vs) ;
	}
	
	public static <K,V> SetMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return HashSetGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return HashSetGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return HashSetGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> SetMultiMap<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return HashSetGroupClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	
	private HashSetGroupClassification(Function<X,K> ck, Function<X,V> cv, Boolean p ) {
		super(ck,cv,p) ;
	}

	@Override
	protected SetMultiMap<K, V> createMultiMap() {
		return new HashSetMultiMap<>() ;
	}
}
