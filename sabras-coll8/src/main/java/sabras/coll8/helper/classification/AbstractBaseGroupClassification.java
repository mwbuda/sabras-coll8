package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import sabras.coll8.collection.MultiMap;


public abstract class AbstractBaseGroupClassification<
	X,K,V, 
	C extends Iterable<V>, 
	MM extends MultiMap<K,V,C>
> 
implements GroupClassification<X, K, V, C> {
	
	private static class XPair<PK,PV> {
		private final PK k ;
		private final PV v ;
		
		public PK getK() {
			return this.k ;
		}
		
		public PV getV() {
			return this.v ;
		}
		
		public XPair(PK kv, PV vv) {
			this.k = kv ;
			this.v = vv ;
		}
	}
	
	private final Function<X,K> convertKeys ;
	private final Function<X,V> convertValues ;
	private final Function<X,XPair<K,V>> convertItem ;
	private final Boolean isParallel ;
	
	protected abstract MM createMultiMap() ;
	
	protected AbstractBaseGroupClassification(Function<X,K> ck, Function<X,V> cv, Boolean p) {
		this.convertKeys = ck ;
		this.convertValues = cv ;
		this.convertItem = (x) -> new XPair<>(
			this.convertKeys.apply(x), 
			this.convertValues.apply(x)
		) ;
		this.isParallel = p ;
	}
	
	@Override
	public MM apply(Iterable<X> xs) {
		return this.apply(StreamSupport.stream(xs.spliterator(), this.isParallel) ) ;
	}

	@Override
	public MM apply(Iterator<X> xs) {
		Iterable<X> xsAsIterable = () -> xs ;
		return this.apply(StreamSupport.stream(xsAsIterable.spliterator(), this.isParallel) ) ;
	}

	@Override
	public MM apply(Spliterator<X> xs) {
		return this.apply(StreamSupport.stream(xs, this.isParallel)) ;
	}

	@Override
	public MM apply(Stream<X> xs) {
		MM mm = this.createMultiMap() ;
		
		xs.map(this.convertItem).forEachOrdered(xp -> mm.appendOne(
			xp.getK(), xp.getV()
		)); 
		
		return mm ;
	}

}
