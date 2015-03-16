package sabras.coll8.collection;

import java.util.Collection;
import java.util.function.Function;

import sabras.coll8.helper.CollectConvert;

public abstract class AbstractMultiMap<K,V,C extends Collection<V>>
extends DefaultValueHashMap<K,C>
implements MultiMap<K, V, C> {
	
	private static final long serialVersionUID = 4623635612300217706L;

	public AbstractMultiMap(Function<K,C> dvx) {
		super(dvx) ;
	}
	
	@SafeVarargs
	final public C append(K k, V... vs) {
		return this.append(k, CollectConvert.newIterable(vs)) ;
	}
	
	@SafeVarargs
	final public C put(K k, V... vs) {
		return this.put(k, CollectConvert.newIterable(vs)) ;
	}
}
