package sabras.coll8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
}
