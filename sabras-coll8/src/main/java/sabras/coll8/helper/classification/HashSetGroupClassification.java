package sabras.coll8.helper.classification;

import java.util.Set;
import java.util.function.Function;

import sabras.coll8.collection.HashSetMultiMap;
import sabras.coll8.collection.SetMultiMap;

public final class HashSetGroupClassification<X,K,V> 
extends AbstractBaseGroupClassification<X,K,V,Set<V>,SetMultiMap<K,V>>
implements GroupSetClassification<X, K, V>  {
	
	public static <X,K,V> GroupSetClassification<X,K,V> create(Function<X,K> ck, Function<X,V> cv) {
		return HashSetGroupClassification.parallelOf(ck, cv) ;
	}
	public static <X,K,V> GroupSetClassification<X,K,V> parallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new HashSetGroupClassification<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> GroupSetClassification<X,K,V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new HashSetGroupClassification<X,K,V>(ck,cv,false) ;
	}
	
	public static <X,K> GroupSetClassification<X,K,X> ofKeys(Function<X,K> ck) {
		return HashSetGroupClassification.create(ck, x -> x) ;
	}
	public static <X,K> GroupSetClassification<X,K,X> parallelOfKeys(Function<X,K> ck) {
		return HashSetGroupClassification.parallelOf(ck, x -> x) ;
	}
	public static <X,K> GroupSetClassification<X,K,X> sequentialOfKeys(Function<X,K> ck) {
		return HashSetGroupClassification.sequentialOf(ck, x -> x) ;
	}
	
	private HashSetGroupClassification(Function<X,K> ck, Function<X,V> cv, Boolean p ) {
		super(ck,cv,p) ;
	}

	@Override
	protected SetMultiMap<K, V> createMultiMap() {
		return new HashSetMultiMap<>() ;
	}
}
