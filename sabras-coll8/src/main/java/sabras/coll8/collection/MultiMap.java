package sabras.coll8.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sabras.coll8.helper.CollectConvert;

public interface MultiMap<K,V,C extends Iterable<V>> extends Map<K, C> {

	@SuppressWarnings("unchecked")
	default public C appendOne(K k, V v) {
		return this.append(k, v) ;
	}
	@SuppressWarnings("unchecked")
	default public C append(K k, V... vs) {
		return this.append(k, CollectConvert.newIterable(vs)) ;
	}
	default public C append(K k, Iterable<V> vs) {
		C ovs = this.get(k) ;
		List<V> nvs = CollectConvert.newList(ovs) ;
		List<V> avs = CollectConvert.newList(vs) ;
		nvs.addAll(avs) ;
		return this.put(k, nvs) ;
	}
	
	@SuppressWarnings("unchecked")
	default public C put(K k, V... vs) {
		return this.put(k, CollectConvert.newIterable(vs)) ;
	}
	public C put(K k, Iterable<V> vs) ;
	
	default public Iterable<V> allValues() {
		Collection<V> allvs = CollectConvert.<V>newCollection() ;
		for (C vs : this.values()) allvs.addAll(CollectConvert.newCollection(vs)) ;
		return allvs ;
	}
	
	default public Set<V> allUniqueValues() {
		Set<V> allvs = CollectConvert.newSet() ;
		for (C vs : this.values()) allvs.addAll(CollectConvert.newCollection(vs)) ;
		return allvs ;
	}
}
