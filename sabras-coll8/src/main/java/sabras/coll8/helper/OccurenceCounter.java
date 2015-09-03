package sabras.coll8.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import sabras.coll8.collection.HashSetMultiMap;
import sabras.coll8.collection.SetMultiMap;

/**
 * 
 * utility/helper class for keeping counts of occurences per item, for a given set of items
 * 
 * typical use for this is to track allocation of items/item types when looping.
 * 	for those familiar with Google Guava, this is the Coll8 equivalent of the Guava MultiSet.
 * 	(with, in the authors opinion, a bit a better, more obvious/utilitarian name)
 * 
 * Cross-set occurences are tracked appropriately; for example if you track an occurence 
 * 
 * TODO: pet, animal, dinosaur, herbivore example
 *
 */
public class OccurenceCounter<K> {

	public static final class LockedSchemaError
	extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public final Schema<?> schema ;
		
		public <K> LockedSchemaError(Schema<K> sch) {
			super("schema is locked") ;
			this.schema = sch ;
		}
	}
	
	public static final class SubSetDefineError
	extends RuntimeException {
		private static final long serialVersionUID = 1L;
		public final Schema<?> schema ;
		public final Object superKey ;
		public final Object subKey ;
		
		public <K> SubSetDefineError(Schema<K> sch, K spk, K sbk) {
			super(
				new StringBuilder()
					.append("cannot define ")
						.append("key(").append(sbk.toString()).append(")")
					.append(" as subset of ")
					.append("key(").append(spk.toString()).append(")")
				.toString()
			) ;
			this.schema = sch ;
			this.subKey = sbk ;
			this.superKey = spk ;
		}
	}
	
	public static final class Schema<K> 
	implements Cloneable {
		private final SetMultiMap<K, K> subset = new HashSetMultiMap<>() ;
		private final Map<K, K> superset = new HashMap<>() ;
		private boolean locked = false ;
		
		public static <K> Schema<K> forType(Class<K> ktype) {
			return new Schema<K>() ;
		}
		
		private Schema() {
			
		}
		
		@SafeVarargs
		public final Schema<K> defineSubSets(K k, K... sks) {
			return this.defineSubSets(k, IterMaker.newIterable(sks));
		}
		public final Schema<K> defineSubSets(K k, Iterable<K> nsbks) {
			if (this.locked) throw new LockedSchemaError(this.lockedCopy()) ;
			Set<K> unsbks = IterMaker.newSet(nsbks) ;
			Set<K> spks = this.getSuperSet(k) ;
			Set<K> sbks = this.getSubSets(k) ;
			unsbks.removeAll(sbks) ;
			for (K sbk : unsbks) {
				if (spks.contains(sbk)) throw new SubSetDefineError(this.lockedCopy() , k, sbk) ;
				if (this.superset.get(sbk) != null) throw new SubSetDefineError(this.lockedCopy(), k, sbk) ;
				this.subset.appendOne(k, sbk) ;
				this.superset.put(sbk, k) ;
			}
			return this ;
		}
		
		public Set<K> getSuperSet(K k) {
			K bsk = this.superset.get(k) ;
			K csk = bsk ;
			Set<K> sks = IterMaker.newSet() ;
			while (csk != null) {
				sks.add(csk) ;
				csk = this.superset.get(csk) ;
			}
			return sks ;
		}
		
		public Set<K> getSubSets(K k) {
			Set<K> bsks = this.subset.get(k) ;
			Set<K> sks = IterMaker.newSet(bsks) ;
			for (K sk : bsks) sks.addAll(this.getSubSets(sk)) ;
			return sks ;
		}
		
		public Schema<K> lock() {
			this.locked = true ;
			return this ;
		}
		
		@Override
		protected Schema<K> clone() 
		throws CloneNotSupportedException {
			Schema<K> dup = new Schema<K>() ;
			for (K k : this.subset.keySet()) dup.subset.append(k, this.subset.get(k)) ;
			for (K k : this.superset.keySet()) dup.superset.put(k, this.superset.get(k)) ;
			dup.locked = this.locked ;
			return dup ;
		}
		
		public Schema<K> lockedCopy() {
			try {
				Schema<K> copy = this.clone() ;
				copy.locked = true ;
				return copy ;
			}
			catch (Throwable e) {
				throw new RuntimeException(e) ;
			}
		}
		
		public Schema<K> unlockedCopy() {
			try {
				Schema<K> copy = this.clone() ;
				copy.locked = false ;
				return copy ;
			}
			catch (Throwable e) {
				throw new RuntimeException(e) ;
			}
		}
	}
	
	private final Schema<K> schema ;
	private final SetMultiMap<K, UUID> track = new HashSetMultiMap<>() ;
	
	public static <K> OccurenceCounter<K> forType(Class<K> ktype) {
		return new OccurenceCounter<>(Schema.forType(ktype)) ;
	}
	public static <K> OccurenceCounter<K> fromSchema(Schema<K> schema) {
		return new OccurenceCounter<>(schema) ;
	}
	
	private OccurenceCounter(Schema<K> schm) {
		this.schema = schm.lockedCopy() ;
	}
	
	public Schema<K> getSchema() {
		return this.schema ;
	}
	
	@SafeVarargs
	public final Integer count(K... ks) {
		return this.count(IterMaker.newSet(ks)) ;
	}
	public final Integer count(Iterable<K> ks) {
		Set<K> cleanKs = this.cleanKeys(ks) ;
		Set<Set<UUID>> auids = IterMaker.newSet() ;
		for (K k : cleanKs)  {
			Set<UUID> uids = IterMaker.newSet(this.track.get(k)) ;
			for (K sk : this.schema.getSubSets(k)) uids.addAll(this.track.get(sk)) ;
			auids.add(uids) ;
		}
		
		Set<UUID> counting = IterMaker.newSet() ;
		boolean first = true ;
		for (Set<UUID> uids : auids) {
			if (!first) {
				counting.retainAll(uids) ;
				continue ;
			}
			counting.addAll(uids) ;
			first = false ;
			continue ;	
		}
		
		return counting.size() ;
	}
	
	private Set<K> cleanKeys(Iterable<K> ks) {
		Set<K> cleanKs = IterMaker.newSet(ks) ;
		Set<K> dropKs = IterMaker.newSet() ;
		for (K k : cleanKs)  dropKs.addAll(this.schema.getSuperSet(k)) ;
		cleanKs.removeAll(dropKs) ;
		return cleanKs ;
	}
	
	@SafeVarargs
	public final OccurenceCounter<K> track(K... ks) {
		return this.track(IterMaker.newSet(ks)) ;
	}
	public final OccurenceCounter<K> track(Iterable<K> ks) {
		Set<K> cleanKs = this.cleanKeys(ks) ;
		UUID uid = UUID.randomUUID() ;
		for (K k : cleanKs) this.track.appendOne(k, uid) ;
		return this ;
	}
	
}
