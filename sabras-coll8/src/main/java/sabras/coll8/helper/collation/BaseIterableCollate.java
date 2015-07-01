package sabras.coll8.helper.collation;

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
public class BaseIterableCollate<X,K,V> 
implements IterableCollate<X, K, V> {

	private final Function<X,K> convertKeys ;
	private final Function<X,V> convertValues ;
	private final Collector<X, ?, Map<K, V>> collector ;
	private final Boolean isParallel ;
	
	public static <X,K,V> IterableCollate<X, K, V> parrallelOf(Function<X,K> ck, Function<X,V> cv) {
		return new BaseIterableCollate<X,K,V>(ck,cv,true) ;
	}
	public static <X,K,V> IterableCollate<X, K, V> sequentialOf(Function<X,K> ck, Function<X,V> cv) {
		return new BaseIterableCollate<X,K,V>(ck,cv,false) ;
	}
	
	public static <K,V> IterableCollate<K, K, V> parallelOfValues(Function<K,V> cv) {
		return BaseIterableCollate.parrallelOf(k -> k, cv) ;
	}
	public static <K,V> IterableCollate<K, K, V> sequentialOfValues(Function<K,V> cv) {
		return BaseIterableCollate.sequentialOf(k -> k, cv) ;
	}
	
	public static <K,V> IterableCollate<V, K, V> parallelOfKeys(Function<V,K> ck) {
		return BaseIterableCollate.parrallelOf(ck, v -> v) ;
	}
	public static <K,V> IterableCollate<V, K, V> sequentialOfKeys(Function<V,K> ck) {
		return BaseIterableCollate.sequentialOf(ck, v -> v) ;
	}
	
	public static <K,V> Map<K,V> parallelCollationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BaseIterableCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> parallelCollationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BaseIterableCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> parallelCollationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BaseIterableCollate.parallelOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> parallelCollationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BaseIterableCollate.parallelOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> Map<K,V> sequentialCollationOfValues(Function<K,V> cv, Iterable<K> ks) {
		return BaseIterableCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> sequentialCollationOfValues(Function<K,V> cv, Iterator<K> ks) {
		return BaseIterableCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> sequentialCollationOfValues(Function<K,V> cv, Spliterator<K> ks) {
		return BaseIterableCollate.sequentialOfValues(cv).apply(ks) ;
	}
	public static <K,V> Map<K,V> sequentialCollationOfValues(Function<K,V> cv, Stream<K> ks) {
		return BaseIterableCollate.sequentialOfValues(cv).apply(ks) ;
	}
	
	public static <K,V> Map<K,V> parallelCollationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BaseIterableCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> parallelCollationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BaseIterableCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> parallelCollationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BaseIterableCollate.parallelOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> parallelCollationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BaseIterableCollate.parallelOfKeys(cv).apply(vs) ;
	}
	
	public static <K,V> Map<K,V> sequentialCollationOfKeys(Function<V,K> cv, Iterable<V> vs) {
		return BaseIterableCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> sequentialCollationOfKeys(Function<V,K> cv, Iterator<V> vs) {
		return BaseIterableCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> sequentialCollationOfKeys(Function<V,K> cv, Spliterator<V> vs) {
		return BaseIterableCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	public static <K,V> Map<K,V> sequentialCollationOfKeys(Function<V,K> cv, Stream<V> vs) {
		return BaseIterableCollate.sequentialOfKeys(cv).apply(vs) ;
	}
	
	private BaseIterableCollate(Function<X,K> ck, Function<X,V> cv, Boolean p) {
		this.convertKeys = ck ;
		this.convertValues = cv ;
		this.collector = Collectors.toMap(this.convertKeys, this.convertValues) ;
		this.isParallel = p ;
	}
	
	public IterableCollate<X, K, V> asParallel() {
		return this.flipParallelFlag(true) ;
	}
	
	public IterableCollate<X, K, V> asSequential() {
		return this.flipParallelFlag(false) ;
	}
	
	private IterableCollate<X, K, V> flipParallelFlag(Boolean flag) {
		IterableCollate<X, K, V> cc = new BaseIterableCollate<>(this.convertKeys, this.convertValues, flag) ;
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
