package sabras.coll8.collection;

import java.util.LinkedList;
import java.util.List;

public class LinkedListMultiMap<K,V>
extends DefaultValueHashMap<K,List<V>>
implements ListMultiMap<K, V> {

	private static final long serialVersionUID = 8476046627260122790L;

	public LinkedListMultiMap() {
		super(k -> new LinkedList<V>());
	}

}
