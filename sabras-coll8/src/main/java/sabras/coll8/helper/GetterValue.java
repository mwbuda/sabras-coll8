package sabras.coll8.helper;

import java.lang.reflect.Method;
import java.util.function.Function;

public final class GetterValue<XT,PV> 
implements Function<XT,PV> {

	private final Method mx ;
	
	@SuppressWarnings("unchecked")
	public GetterValue(Class<XT> xt, String vn, PV exv) {
		this(xt,vn, (Class<PV>) exv.getClass()) ;
	}
	
	@SuppressWarnings("unchecked")
	public GetterValue(XT exx, String vn, PV exv) {
		this((Class<XT>) exx.getClass() ,vn, (Class<PV>) exv.getClass()) ;
	}
	
	@SuppressWarnings("unchecked")
	public GetterValue(XT exx, String vn, Class<PV> vt) {
		this((Class<XT>) exx.getClass() ,vn, (Class<PV>) vt) ;
	}
	
	public GetterValue(Class<XT> xt, String vn, Class<PV> vt) {
		String firstChar = "" + vn.charAt(0) ;
		String pnx = vn.replaceFirst("[" + firstChar +  "]", firstChar.toUpperCase()) ; 
		Method m = null ;
		try {
			m = xt.getMethod("get" + pnx) ;
		}
		catch (NoSuchMethodException nsme) {
			//nothing 
		}
		this.mx = m ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PV apply(XT x) {
		try {
			return (PV) this.mx.invoke(x) ;
		} catch (Throwable e) {
			return null ;
		}
	}
	
}
