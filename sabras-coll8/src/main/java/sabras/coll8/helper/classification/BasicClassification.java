package sabras.coll8.helper.classification;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * a helper class to convert collections/iterables/etc into maps ('collate' them)
 * @author user
 *
 */
public class BasicClassification<X,K,V> 
implements Classification<X, K, V> {

	private final Function<X,K> convertKeys ;
	private final Function<X,V> convertValues ;
	private final Collector<X, ?, Map<K, V>> collector ;
	private final Boolean isParallel ;
	
	public static <X,K,V> Classification<X, K, V> parrallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new BasicClassification<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> Classification<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new BasicClassification<X,K,V>(ck,cv,false) ;
	}
	
	public static <K,V> Classification<K, K, V> parallelOfValues(Function<K,V> cv) {
		return BasicClassification.parrallelOf(k -> k, cv) ;
	}
	public static <K,V> Classification<K, K, V> sequentialOfValues(Function<K,V> cv) {
		return BasicClassification.sequentialOf(k -> k, cv) ;
	}
	
	public static <K,V> Classification<V, K, V> parallelOfKeys(Function<V,K> ck) {
		return BasicClassification.parrallelOf(ck, v -> v) ;
	}
	public static <K,V> Classification<V, K, V> sequentialOfKeys(Function<V,K> ck) {
		return BasicClassification.sequentialOf(ck, v -> v) ;
	}
	
	public static <K,V> Map<K,V> parallelClassificationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BasicClassification.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> parallelClassificationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BasicClassification.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> parallelClassificationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BasicClassification.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> parallelClassificationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BasicClassification.parallelOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> Map<K,V> sequentialClassificationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BasicClassification.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> sequentialClassificationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BasicClassification.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> sequentialClassificationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BasicClassification.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> sequentialClassificationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BasicClassification.sequentialOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> Map<K,V> parallelClassificationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BasicClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> parallelClassificationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BasicClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> parallelClassificationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BasicClassification.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> parallelClassificationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BasicClassification.parallelOfKeys(cv).apply(vs) ;
	}
	
	public static <K,V> Map<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BasicClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BasicClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BasicClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> sequentialClassificationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BasicClassification.sequentialOfKeys(cv).apply(vs) ;
	}
	
	private BasicClassification(Function<X,K> ck, Function<X,V> cv, Boolean p) {
		this.convertKeys = ck ;
		this.convertValues = cv ;
		this.collector = Collectors.toMap(this.convertKeys, this.convertValues) ;
		this.isParallel = p ;
	}
	
	public Classification<X, K, V> asParallel() {
		return this.flipParallelFlag(true) ;
	}
	
	public Classification<X, K, V> asSequential() {
		return this.flipParallelFlag(false) ;
	}
	
	private Classification<X, K, V> flipParallelFlag(Boolean flag) {
		Classification<X, K, V> cc = new BasicClassification<>(this.convertKeys, this.convertValues, flag) ;
		return cc ;
	}
	
	public Boolean isParallel() {
		return this.isParallel ;
	}
	
	@Override
	public Map<K,V> apply(Iterable<X> xs) {
		return this.apply(StreamSupport.stream(xs.spliterator(), this.isParallel) ) ;
	}

	@Override
	public Map<K,V> apply(Iterator<X> xs) {
		Iterable<X> xsAsIterable = () -> xs ;
		return this.apply(StreamSupport.stream(xsAsIterable.spliterator(), this.isParallel) ) ;
	}
	@Override
	public Map<K,V> apply(Spliterator<X> xs) {
		return this.apply(StreamSupport.stream(xs, this.isParallel)) ;
	}
	@Override
	public Map<K,V> apply(Stream<X> xs) {
		return xs.collect(this.collector) ;
	}
}
