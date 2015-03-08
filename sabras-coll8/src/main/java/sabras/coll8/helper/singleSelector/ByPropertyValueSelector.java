package sabras.coll8.helper.singleSelector;

import java.lang.reflect.Method;

import sabras.coll8.helper.CollectConvert;

/**
 * single selector which returns 1st object found to match the given property value
 * 
 * @param <X>
 * @param <PV>
 */
public class ByPropertyValueSelector<X,PV>
implements SingleSelector<X> {

	private final PV vx ;
	private final Method mx ;
	
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
		String firstChar = "" + pn.charAt(0) ;
		String pnx = pn.replaceFirst("[" + firstChar +  "]", firstChar.toUpperCase()) ; 
		Method m = null ;
		try {
			m = xt.getMethod("get" + pnx) ;
		}
		catch (NoSuchMethodException nsme) {
			//nothing 
		}
		this.mx = m ;
	}
	
	@Override
	public X apply(Iterable<X> xs) {
		for (X x : xs) {
			try {
				PV xv = (PV) this.mx.invoke(x) ;
				if (xv.equals(this.vx)) return x ;
			} catch (Throwable e) {
				continue ;
			}
		}

		return null;
	}

}
