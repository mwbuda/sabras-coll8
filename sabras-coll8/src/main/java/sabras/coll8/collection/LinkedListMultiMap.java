package sabras.coll8.collection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListMultiMap<K,V>
extends DefaultValueHashMap<K,List<V>>
implements ListMultiMap<K, V> {

	private static final long serialVersionUID = 8476046627260122790L;

	@SafeVarargs
	final public List<V> append(K k, V... vs) {
		return this.append(k, Arrays.asList(vs)) ;
	}
	
	public LinkedListMultiMap() {
		super(k -> new LinkedList<V>());
	}

}
