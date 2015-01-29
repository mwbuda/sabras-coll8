package sabras.coll8;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * a helper class to convert maps into collections/iterables/streams (eg 'zip' them up)
 *
 * @param <K>
 * @param <V>
 * @param <X>
 */
public final class MapZip<K,V,X> {

	private static final class TakeMap<K,V,X>
	implements BiFunction<Map<K,V>, Collector<X,?,? extends Collection<X>>, Collection<X>> {
		private final MapZip<K,V,X> zip ;
		
		public TakeMap(MapZip<K,V,X> mz) {
			this.zip = mz ;
		}
		
		@Override
		public Collection<X> apply(Map<K, V> in, Collector<X, ?, ? extends Collection<X>> collect) {
			return this.zip.apply(in, collect) ;
		}
	}
	
	private static final class TakeIterator<K,V,X>
	implements BiFunction<Iterator<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> {
		private final MapZip<K,V,X> zip ;
		
		public TakeIterator(MapZip<K,V,X> mz) {
			this.zip = mz ;
		}
		
		@Override
		public Collection<X> apply(Iterator<Map.Entry<K, V>> in, Collector<X, ?, ? extends Collection<X>> collect) {
			return this.zip.apply(in, collect) ;
		}
	}
	
	private static final class TakeIterable<K,V,X>
	implements BiFunction<Iterable<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> {
		private final MapZip<K,V,X> zip ;
		
		public TakeIterable(MapZip<K,V,X> mz) {
			this.zip = mz ;
		}
		
		@Override
		public Collection<X> apply(Iterable<Map.Entry<K, V>> in, Collector<X, ?, ? extends Collection<X>> collect) {
			return this.zip.apply(in, collect) ;
		}
	}
	
	private static final class TakeStream<K,V,X>
	implements BiFunction<Stream<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> {
		private final MapZip<K,V,X> zip ;
		
		public TakeStream(MapZip<K,V,X> mz) {
			this.zip = mz ;
		}
		
		@Override
		public Collection<X> apply(Stream<Map.Entry<K, V>> in, Collector<X, ?, ? extends Collection<X>> collect) {
			return this.zip.apply(in, collect) ;
		}
	}
	
	private static final class TakeSpliterator<K,V,X>
	implements BiFunction<Spliterator<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> {
		private final MapZip<K,V,X> zip ;
		
		public TakeSpliterator(MapZip<K,V,X> mz) {
			this.zip = mz ;
		}
		
		@Override
		public Collection<X> apply(Spliterator<Map.Entry<K, V>> in, Collector<X, ?, ? extends Collection<X>> collect) {
			return this.zip.apply(in, collect) ;
		}
	}
	
	private final BiFunction<K,V,X> converter ;
	private final Boolean isParallel ;
	
	private TakeMap<K,V,X> takeMap ;
	private TakeIterator<K,V,X> takeIterator ;
	private TakeIterable<K,V,X> takeIterable ;
	private TakeStream<K,V,X> takeStream ;
	private TakeSpliterator<K,V,X> takeSpliterator ; 
	
	public static <K,V,X> MapZip<K,V,X> parallelOf(BiFunction<K,V,X> cv) {
		return new MapZip<>(cv,true) ;
	}
	
	public static <K,V,X> MapZip<K,V,X> sequentialOf(BiFunction<K,V,X> cv) {
		return new MapZip<>(cv,false) ;
	}
	
	public MapZip<K,V,X> asParallel() {
		return this.flipParallelFlag(true) ;
	}
	
	public MapZip<K,V,X> asSequential() {
		return this.flipParallelFlag(false) ;
	}
	
	private MapZip<K,V,X> flipParallelFlag(Boolean flag) {
		MapZip<K,V,X> mz = (flag == this.isParallel) ? this : new MapZip<>(this.converter,flag) ;
		if (mz != this) {
			mz.takeIterable = this.takeIterable ;
			mz.takeIterator = this.takeIterator ;
			mz.takeMap = this.takeMap ;
			mz.takeSpliterator = this.takeSpliterator ;
			mz.takeStream = this.takeStream ;
		}
		return mz ;
	}
	
	private MapZip(BiFunction<K,V,X> cv, Boolean isp) {
		this.converter = cv ;
		this.isParallel = isp ;
	}
	
	public Boolean isParallel() {
		return this.isParallel ;
	}
	
	public Collection<X> apply(Map<K, V> in, Collector<X,?,? extends Collection<X>> collect) {
		return this.apply(
			this.isParallel ? in.entrySet().parallelStream() : in.entrySet().stream(), 
			collect
		) ;
	}
	
	public Collection<X> apply(Iterator<Map.Entry<K, V>> in, Collector<X,?,? extends Collection<X>> collect) {
		Iterable<Map.Entry<K, V>> inAsIterable = () -> in ;
		return this.apply(
			StreamSupport.stream(inAsIterable.spliterator(), this.isParallel), 
			collect
		) ; 
	}
	
	public Collection<X> apply(Iterable<Map.Entry<K, V>> in, Collector<X,?,? extends Collection<X>> collect) {
		return this.apply(
			StreamSupport.stream(in.spliterator(), this.isParallel), 
			collect
		) ;  
	}
	
	public Collection<X> apply(Stream<Map.Entry<K, V>> in, Collector<X,?,? extends Collection<X>> collect) {
		return in.map(e -> this.converter.apply(e.getKey(), e.getValue())).collect(collect) ; 
	}
	
	public Collection<X> apply(Spliterator<Map.Entry<K, V>> in, Collector<X,?,? extends Collection<X>> collect) {
		return this.apply(StreamSupport.stream(in, this.isParallel), collect) ;
	}

	public BiFunction<Map<K,V>, Collector<X,?,? extends Collection<X>>, Collection<X>> asTakeMap() {
		if (this.takeMap == null) this.takeMap = new TakeMap<>(this) ;
		return this.takeMap ;
	}
	
	public BiFunction<Iterator<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> asTakeIterator() {
		if (this.takeIterator == null) this.takeIterator = new TakeIterator<>(this) ;
		return this.takeIterator ;
	}
	
	public BiFunction<Iterable<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> asTakeIterable() {
		if (this.takeIterable == null) this.takeIterable = new TakeIterable<>(this) ;
		return this.takeIterable ;
	}
	
	public BiFunction<Stream<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> asTakeStream() {
		if (this.takeStream == null) this.takeStream = new TakeStream<>(this) ;
		return this.takeStream ;
	}
	
	public BiFunction<Spliterator<Map.Entry<K,V>>, Collector<X,?,? extends Collection<X>>, Collection<X>> asTakeSpliterator() {
		if (this.takeSpliterator == null) this.takeSpliterator = new TakeSpliterator<>(this) ;
		return this.takeSpliterator ;
	}
	
}
