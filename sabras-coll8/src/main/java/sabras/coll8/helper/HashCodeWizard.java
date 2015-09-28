package sabras.coll8.helper;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * 
 * a helper class intended to streamline generation of custom hashcodes, 
 * 	streamlined for the use case where the overwritten hashCode is being used alongside
 * 	custom compareTo & equals methods to establish a custom solution for determining object 'identity'
 * 
 *
 */
public class HashCodeWizard<X>
implements Function<X, Integer> {

	final Integer prime ;
	final List<Function<X,?>> selectors ;
	
	protected static final Integer generatePrime() {
		Random random = new Random() ;
		return BigInteger.probablePrime(32, random).intValue() ;
	}
	
	@SafeVarargs
	public static <X> HashCodeWizard<X> forProperties(
		Class<X> klass, Function<X,?>... ss
	) {
		int prime = HashCodeWizard.generatePrime() ;
		return new HashCodeWizard<>(prime, IterMaker.newList(ss)) ;
	}
	
	public static <X> HashCodeWizard<X> forProperties(
			Class<X> klass, List<Function<X,?>> ss
	) {
		int prime = HashCodeWizard.generatePrime() ;
		return new HashCodeWizard<>(prime, ss) ;
	}
	
	@SafeVarargs 
	public static <X> HashCodeWizard<X> forPropertiesWithPrime(
		Class<X> klass, Integer prime, Function<X,?>... ss
	) {
		return new HashCodeWizard<>(prime, IterMaker.newList(ss)) ;
	}
	
	public static <X> HashCodeWizard<X> forPropertiesWithPrime(
		Class<X> klass, Integer prime, List<Function<X,?>> ss
	) {
		return new HashCodeWizard<>(prime, ss) ;
	}
	
	private HashCodeWizard(int p, List<Function<X,?>> ss) {
		this.prime = p ;
		this.selectors = Collections.unmodifiableList( IterMaker.newList(
			(List<Function<X,?>>) ss 
		)) ;
	}
	
	@Override
	public Integer apply(X x) {
		int hc = 1 ;
		
		for (Function<X,?> selector : this.selectors) {
			Object sv = selector.apply(x) ;
			int hcx = (sv == null) ? 0 : sv.hashCode() ;
			hc = (hc * this.prime) + hcx ;
		}
		
		return hc ;
	}
	
}
