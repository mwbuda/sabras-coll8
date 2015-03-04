package sabras.coll8.collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class HashSetMultiMap<K,V>
extends DefaultValueHashMap<K,Set<V>>
implements SetMultiMap<K, V> 
{

	private static final long serialVersionUID = 1216897108027067333L;

	@SafeVarargs
	final public Set<V> append(K k, V... vs) {
		return this.append(k, Arrays.asList(vs)) ;
	}
	
	public HashSetMultiMap() {
		super(k -> new HashSet<V>());
	}

}
