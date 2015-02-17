package sabras.coll8.collection;

import java.util.HashMap;
import java.util.function.Function;

public class DefaultValueHashMap<K,V> 
extends HashMap<K,V>
implements DefaultValueMap<K, V> {

	private static final long serialVersionUID = 5812399030462404988L;
	
	private final Function<K,V> defaultValue ;
	
	public DefaultValueHashMap(V dv) {
		this(k -> dv) ;
	}
	public DefaultValueHashMap(Function<K,V> dvx) {
		super() ;
		this.defaultValue = dvx ;
	}
	
	@Override
	public V get(Object key) {
		if (!this.containsKey(key)) {
			@SuppressWarnings("unchecked") K kk = (K)key ;
			this.put(kk, this.defaultValue(kk)) ;
		}
		return super.get(key);
	}

	@Override
	public V defaultValue(K key) {
		return this.defaultValue.apply(key) ;
	}
	
}
