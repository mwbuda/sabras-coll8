package sabras.coll8.collection;

import java.util.ArrayList;
import java.util.Arrays;
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
	default public C append(K k, @SuppressWarnings("unchecked") V... vs) {
		return this.append(k, Arrays.asList(vs)) ;
	}
	default public C append(K k, Collection<V> vs) {
		C ovs = this.get(k) ;
		List<V> nvs = new ArrayList<>(ovs) ;
		nvs.addAll(vs) ;
		return this.put(k, nvs) ;
	}
	
	public C put(K k,Collection<V> vs) ;
	
	default public Collection<V> allValues() {
		Collection<V> allvs = CollectConvert.<V>newCollection() ;
		for (C vs : this.values()) allvs.addAll (vs) ;
		return allvs ;
	}
	
	default public Set<V> allUniqueValues() {
		Set<V> allvs = CollectConvert.newSet() ;
		for (C vs : this.values()) allvs.addAll(vs) ;
		return allvs ;
	}
}
