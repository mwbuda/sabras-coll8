package sabras.coll8.helper.classification;

import java.util.Set;
import java.util.function.Function;

import sabras.coll8.collection.HashSetMultiMap;
import sabras.coll8.collection.SetMultiMap;

public final class HashSetGroupClassification<X,K,V> 
extends AbstractBaseGroupClassification<X,K,V,Set<V>,SetMultiMap<K,V>>
implements GroupSetClassification<X, K, V>  {
	
	public static <X,K,V> GroupSetClassification<X,K,V> create(Function<X,K> ck, Function<X,V> cv) {
		return HashSetGroupClassification.parrallelOf(ck, cv) ;
	}
	public static <X,K,V> GroupSetClassification<X, K, V> parrallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new HashSetGroupClassification<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> GroupSetClassification<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new HashSetGroupClassification<X,K,V>(ck,cv,false) ;
	}
	
	private HashSetGroupClassification(Function<X,K> ck, Function<X,V> cv, Boolean p ) {
		super(ck,cv,p) ;
	}

	@Override
	protected SetMultiMap<K, V> createMultiMap() {
		return new HashSetMultiMap<>() ;
	}
}
