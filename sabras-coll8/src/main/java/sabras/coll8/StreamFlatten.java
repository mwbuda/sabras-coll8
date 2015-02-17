package sabras.coll8;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * 
 * collector/function which takes in a stream of iterables, and flattens them into a single level collection
 * 
 * @author mbuda
 *
 * @param <X>
 * @param <C>
 */
public interface StreamFlatten<X, C extends Collection<X>> 
extends Function<Stream<? extends Iterable<X>>, C>, Collector<Iterable<X>, C, C> {

	public static abstract class AbstractStreamFlatten<X,C extends Collection<X>>
	implements StreamFlatten<X,C> {
		
		protected abstract C accumType() ;

		@Override
		public final Set<Characteristics> characteristics() {
			Set<Characteristics> cs = new HashSet<>() ;
			cs.add(Characteristics.IDENTITY_FINISH) ;
			cs.addAll(this.extendedCharacteristics()) ;
			return cs ;
		}
		
		protected abstract Set<Characteristics> extendedCharacteristics() ;
		
		@Override
		public C apply(Stream<? extends Iterable<X>> xxs) {
			C accum = this.accumType() ;
			xxs.forEach(xs -> accum.addAll(CollectConvert.newCollection(xs)));
			return accum ;
		}

		@Override
		public Supplier<C> supplier() {
			return () -> this.accumType() ;
		}

		@Override
		public BiConsumer<C, Iterable<X>> accumulator() {
			return (xs,xxs) -> xs.addAll(CollectConvert.newCollection(xxs)) ;
		}

		@Override
		public BinaryOperator<C> combiner() {
			return new BinaryOperator<C>() {
				public C apply(C xs1, C xs2) {
					xs1.addAll(xs2) ;
					return xs1 ;
				}
			};
		}

		@Override
		public Function<C, C> finisher() {
			return xs -> xs ;
		}
	}
	
	public static final class ToSet<X>
	extends AbstractStreamFlatten<X, Set<X>> {

		@Override
		protected Set<X> accumType() {
			return new HashSet<>() ;
		}

		@Override
		protected Set<Characteristics> extendedCharacteristics() {
			return CollectConvert.newSet(
				Characteristics.CONCURRENT,
				Characteristics.UNORDERED
			) ;
		}
	}
	

	public static final class ToList<X>
	extends AbstractStreamFlatten<X, List<X>> {

		@Override
		protected List<X> accumType() {
			return new LinkedList<X>() ;
		}

		@Override
		protected Set<Characteristics> extendedCharacteristics() {
			return Collections.emptySet() ;
		}
	}
	
	public static final class toSortedSet<X>
	extends AbstractStreamFlatten<X, SortedSet<X>> {

		@Override
		protected SortedSet<X> accumType() {
			return new TreeSet<>() ;
		}

		@Override
		protected Set<Characteristics> extendedCharacteristics() {
			return CollectConvert.newSet(
				Characteristics.CONCURRENT,
				Characteristics.UNORDERED
			) ;
		}
	}
	
	
}
