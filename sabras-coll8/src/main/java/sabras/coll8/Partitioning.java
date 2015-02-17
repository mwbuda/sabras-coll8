package sabras.coll8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * breaks an input collection into multiple lists of equal size (partitions)
 * intended to break a larger sequence into manageable sub-chunks for processing
 * 
 * @author mbuda
 *
 * @param <X>
 * @param <C>
 */
public interface Partitioning<X, C extends Collection<X>>
extends Function<Collection<X>, List<C>>
{
	public abstract class AbstractIntoMax<X,C extends Collection<X>>
	implements Partitioning<X,C> {
		private final Integer maxPartitonSize ;
		
		public AbstractIntoMax(Integer max) {
			this.maxPartitonSize = max ;
		}
		
		protected Collection<X> preprocessInput(Collection<X> rawXs) {
			return rawXs ;
		}
		
		protected abstract C createPartition() ;
		
		@Override
		public final List<C> apply(Collection<X> xs) {
			Collection<X> cleanXs = this.preprocessInput(xs) ; 
			int realElementCount = cleanXs.size() ;
			int partitionCount = realElementCount / this.maxPartitonSize ;
			int derivedElementCount = partitionCount * this.maxPartitonSize ;
			if (realElementCount > derivedElementCount) partitionCount += 1 ;
			int movedCount = 0 ;
			
			List<C> results = new LinkedList<>() ;
			C currentPartition = this.createPartition() ;
			results.add(currentPartition) ;
			for (X x : cleanXs) {
				if (movedCount == this.maxPartitonSize) {
					currentPartition = this.createPartition() ;
					results.add(currentPartition) ;
					movedCount = 0 ;
				}
				
				currentPartition.add(x) ;
				movedCount += 1 ;
			}
			
			return results;
		}
	}
	
	public class IntoSetsOfMax<X>
	extends AbstractIntoMax<X,Set<X>> {

		public IntoSetsOfMax(Integer max) {
			super(max) ;
		}
		
		@Override
		protected Collection<X> preprocessInput(Collection<X> rawXs) {
			return new HashSet<>(rawXs) ;
		}
		
		@Override
		protected Set<X> createPartition() {
			return new HashSet<>();
		}
	}

	public class IntoLinkedListsOfMax<X>
	extends AbstractIntoMax<X,List<X>> {

		public IntoLinkedListsOfMax(Integer max) {
			super(max) ;
		}
		
		@Override
		protected List<X> createPartition() {
			return new LinkedList<>();
		}
	}
	
	public class IntoArrayListsOfMax<X>
	extends AbstractIntoMax<X,List<X>> {

		public IntoArrayListsOfMax(Integer max) {
			super(max) ;
		}
		
		@Override
		protected List<X> createPartition() {
			return new ArrayList<>();
		}
	}
	
}
