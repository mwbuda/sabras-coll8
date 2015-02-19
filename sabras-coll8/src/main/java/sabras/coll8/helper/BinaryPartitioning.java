package sabras.coll8.helper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public interface BinaryPartitioning<X>
extends Function<X,List<byte[]>>{
	
	public class IntoMax<X>
	implements BinaryPartitioning<X> {
		
		public static IntoMax<Object> simple(Integer max) {
			return new IntoMax<>(max, x -> x.toString().getBytes()) ;
		}
		
		public static IntoMax<byte[]> fromByteArray(Integer max) {
			return new IntoMax<>(max, x -> x) ;
		}
		
		private final Integer maxPartitonSize ;
		private final Function<X,byte[]> transform ;
		
		public IntoMax(Integer max, Function<X,byte[]> t) {
			this.maxPartitonSize = max ;
			this.transform = t ;
			
		}
		
		protected byte[] createPartition() {
			return new byte[this.maxPartitonSize] ;
		}
		
		@Override
		public final List<byte[]> apply(X x) {
			byte[] rawConvert = this.transform.apply(x) ;
			int totalByteCount = rawConvert.length ;
			int partitionCount = totalByteCount / this.maxPartitonSize ;
			int derivedByteCount = partitionCount * this.maxPartitonSize ;
			if (totalByteCount > derivedByteCount) partitionCount += 1 ;
			
			List<byte[]> results = new LinkedList<>() ;
			
			if (totalByteCount < this.maxPartitonSize) {
				List<byte[]> result = new ArrayList<>() ;
				byte[] partition = this.createPartition() ;
				System.arraycopy(rawConvert, 0, partition, 0, rawConvert.length);
				result.add(partition) ;
				return result ;
			}
			
			int s = 0 ;
			int l = this.maxPartitonSize ;
			int copied = 0 ;
			while (results.size() < partitionCount) { 
				byte[] currentPartition = this.createPartition() ;
				System.arraycopy(rawConvert, s, currentPartition, 0, l);
				results.add(currentPartition) ;
				s += this.maxPartitonSize ;
				copied = s ;
				if ((copied + l) > totalByteCount) l = (totalByteCount - copied) ;
			}
			
			return results;
		}
	}
	
}
