package sabras.coll8.collection;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sabras.coll8.helper.CollectConvert;

public interface MultiMap<K,V,C extends Collection<V>> extends Map<K, C> {

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
	default C put(K k, C vs) {
		return this.put(k, CollectConvert.newIterable(vs)) ;
	}
	public C put(K k, Iterable<V> vs) ;
	
	default public Iterable<V> allValues() {
		List<V> allvs = CollectConvert.newList() ;
		for (C vs : this.values()) if (vs != null) allvs.addAll(CollectConvert.newCollection(vs)) ;
		return allvs ;
	}
	
	default public C removeAll(K k) {
		return this.remove(k) ;
	}
	default public C removeOne(K k, V v) {
		return this.removeSome(k, CollectConvert.newIterable(v)) ;
	}
	@SuppressWarnings("unchecked")
	default public C removeSome(K k, V... vs) {
		return this.removeSome(k, CollectConvert.newIterable(vs)) ;
	}
	default public C removeSome(K k, Iterable<V> vs) {
		Set<V> uvs = CollectConvert.newSet(vs) ;
		C xvs = this.get(k) ;
		List<V> nvs = CollectConvert.newList(xvs) ;
		boolean didChange = nvs.removeAll(uvs) ;
		return didChange ? this.put(k, nvs) : xvs ;
	}
	
	default public Set<V> allUniqueValues() {
		return CollectConvert.newSet(CollectConvert.newCollection( this.allValues() )) ;
	}
	
	default public Long valuesCount() {
		Long vc = 0l ;
		for (C vs : this.values()) vc += vs.size() ;
		return vc ;
	}
}
