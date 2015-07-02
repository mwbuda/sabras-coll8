package sabras.coll8.helper.classification;

import java.util.List;
import java.util.function.Function;

import sabras.coll8.collection.LinkedListMultiMap;
import sabras.coll8.collection.ListMultiMap;

public final class LinkedListGroupClassification<X,K,V> 
extends AbstractBaseGroupClassification<X,K,V,List<V>,ListMultiMap<K,V>>
implements GroupListClassification<X, K, V>  {
	
	public static <X,K,V> GroupListClassification<X,K,V> create(Function<X,K> ck, Function<X,V> cv) {
		return LinkedListGroupClassification.parallelOf(ck, cv) ;
	}
	public static <X,K,V> GroupListClassification<X, K, V> parallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new LinkedListGroupClassification<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> GroupListClassification<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new LinkedListGroupClassification<X,K,V>(ck,cv,false) ;
	}
	
	public static <X,K> GroupListClassification<X,K,X> ofKeys(Function<X,K> ck) {
		return LinkedListGroupClassification.create(ck, x -> x) ;
	}
	public static <X,K> GroupListClassification<X,K,X> parallelOfKeys(Function<X,K> ck) {
		return LinkedListGroupClassification.parallelOf(ck, x -> x) ;
	}
	public static <X,K> GroupListClassification<X,K,X> sequentialOfKeys(Function<X,K> ck) {
		return LinkedListGroupClassification.sequentialOf(ck, x -> x) ;
	}
	
	private LinkedListGroupClassification(Function<X,K> ck, Function<X,V> cv, Boolean p ) {
		super(ck,cv,p) ;
	}

	@Override
	protected ListMultiMap<K, V> createMultiMap() {
		return new LinkedListMultiMap<>() ;
	}
}
