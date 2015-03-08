package sabras.coll8.helper.singleSelector;

import sabras.coll8.helper.CollectConvert;
import sabras.coll8.helper.GetterValue;

/**
 * single selector which returns 1st object found to match the given property value
 * 
 * @param <X>
 * @param <PV>
 */
public class ByPropertyValueSelector<X,PV>
implements SingleSelector<X> {

	private final PV vx ;
	private final GetterValue<X, PV> mx ;
	
	@SafeVarargs
	public static final <X,PV> X doSelectionFor(Class<X> xt, String pn, PV v, X... xs ) {
		return ByPropertyValueSelector.doSelectionFor(xt, pn, v, CollectConvert.newIterable(xs)) ;
	}
	public static final <X,PV> X doSelectionFor(Class<X> xt, String pn, PV v, Iterable<X> xs ) {
		ByPropertyValueSelector<X, PV> finder = ByPropertyValueSelector.selectorFor(xt, pn, v) ;
		return finder.apply(xs) ;
	}
	
	public static final <X, PV> ByPropertyValueSelector<X, PV> selectorFor(Class<X> xt, String pn, PV v) {
		return new ByPropertyValueSelector<X, PV>(xt, pn, v) ;
	}
	
	private ByPropertyValueSelector(Class<X> xt, String pn, PV v) {
		this.vx = v ;
		this.mx = new GetterValue<>(xt, pn, v) ;
	}
	
	@Override
	public X apply(Iterable<X> xs) {
		for (X x : xs) {
			PV xv = this.mx.apply(x) ;
			if (xv.equals(this.vx)) return x ;
		}

		return null;
	}

}
