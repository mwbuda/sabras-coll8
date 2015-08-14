package sabras.coll8.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * convience class designed to quickly instantiate various kinds of iterables from a source input
 * 	you want to transparently convert/construct.
 * 
 * also supports simple straightforward concatenations of iterables.
 *  this is somewhat redundant with StreamFlatten, 
 *  however the 2 options differ in that IterMaker is fairly simple & straightforward to use, 
 *  while StreamFlatten is much more robust & extensible.
 *
 * all result iterables from IterMaker are fully alterable.
 * Generally, source inputs will be treated as a list internally, to presverve any ordering. 
 *
 */
public final class IterMaker {

	public IterMaker() {
		throw new UnsupportedOperationException() ;
	}
	
	//iterable
	public static <T> Iterable<T> newIterable(Iterable<T> ts) {
		return IterMaker.newList(ts) ;
	}
	public static <T> Iterable<T> newIterable(Iterator<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return () -> ts ;
	}
	@SafeVarargs
	public static <T> Iterable<T> newIterable(T... ts) {
		return IterMaker.newList(ts) ;
	}
	public static <T> Iterable<T> newIterable(Stream<T> ts) {
		return IterMaker.newList(ts) ;
	}
	public static <T> Iterable<T> newIterable(Spliterator<T> ts) {
		return IterMaker.newList(ts) ;
	}
	
	//collection
	public static <T> Collection<T> newCollection(Iterable<T> ts) {
		return IterMaker.newList(ts) ;
	}
	public static <T> Collection<T> newCollection(Iterator<T> ts) {
		return IterMaker.newList(ts) ;
	}
	@SafeVarargs
	public static <T> Collection<T> newCollection(T... ts) {
		return IterMaker.newList(ts) ;
	}
	public static <T> Collection<T> newCollection(Stream<T> ts) {
		return IterMaker.newList(ts) ;
	}
	public static <T> Collection<T> newCollection(Spliterator<T> ts) {
		return IterMaker.newList(ts) ;
	}
	
	//set
	public static <T> Set<T> newSet(Iterable<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return IterMaker.newSet(ts.spliterator()) ;
	}
	public static <T> Set<T> newSet(Iterator<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return IterMaker.newSet( () -> ts ) ;
	}
	@SafeVarargs
	public static <T> Set<T> newSet(T... ts) {
		if (ts == null) return new HashSet<>() ;
		return new HashSet<>(Arrays.asList(ts)) ;
	}
	public static <T> Set<T> newSet(Stream<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return ts.collect(Collectors.toSet()) ;
	}
	public static <T> Set<T> newSet(Spliterator<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return IterMaker.newSet(StreamSupport.stream(ts, false)) ; 
	}
	
	//sorted set
	public static <T> SortedSet<T> newSortedSet(Iterable<T> ts) {
		return IterMaker.newNavigableSet(ts) ;
	}
	public static <T> SortedSet<T> newSortedSet(Iterator<T> ts) {
		return IterMaker.newNavigableSet(ts) ;
	}
	@SafeVarargs
	public static <T> SortedSet<T> newSortedSet(T... ts) {
		return IterMaker.newNavigableSet(ts) ;
	}
	public static <T> SortedSet<T> newSortedSet(Stream<T> ts) {
		return IterMaker.newNavigableSet(ts) ;
	}
	public static <T> SortedSet<T> newSortedSet(Spliterator<T> ts) {
		return IterMaker.newNavigableSet(ts) ;
	}
	
	//navigable set
	public static <T> NavigableSet<T> newNavigableSet(Iterable<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return IterMaker.newNavigableSet(ts.spliterator()	) ;
	}
	public static <T> NavigableSet<T> newNavigableSet(Iterator<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return IterMaker.newNavigableSet( () -> ts ) ;
	}
	@SafeVarargs
	public static <T> NavigableSet<T> newNavigableSet(T... ts) {
		if (ts == null) return new TreeSet<>() ;
		return new TreeSet<>(Arrays.asList(ts)) ;
	}
	public static <T> NavigableSet<T> newNavigableSet(Stream<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return new TreeSet<>( ts.collect(Collectors.toSet()) ) ;
	}
	public static <T> NavigableSet<T> newNavigableSet(Spliterator<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return IterMaker.newNavigableSet(StreamSupport.stream(ts, false)) ; 
	}
	
	//list
	public static <T> List<T> newList(Iterable<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return IterMaker.newList(ts.spliterator()) ;
	}
	public static <T> List<T> newList(Iterator<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return IterMaker.newList( () -> ts ) ;
	}
	@SafeVarargs
	public static <T> List<T> newList(T... ts) {
		List<T> results = new LinkedList<>() ;
		if (ts != null) results.addAll(Arrays.asList(ts));
		return results ;
	}
	public static <T> List<T> newList(Stream<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return ts.collect(Collectors.toList()) ;
	}
	public static <T> List<T> newList(Spliterator<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return IterMaker.newList(StreamSupport.stream(ts, false)) ; 
	}	
	
	//concatenations
	@SafeVarargs
	public static <T> Iterable<T> concatIterable(Iterable<T>... tss) {
		return IterMaker.concatList(tss) ;
	}
	public static <T> Iterable<T> concatIterable(Iterable<Iterable<T>> tss) {
		return IterMaker.concatList(tss) ;
	}
	
	@SafeVarargs
	public static <T> Collection<T> concatCollection(Iterable<T>... tss) {
		return IterMaker.concatList(tss) ;
	}
	public static <T> Collection<T> concatCollection(Iterable<Iterable<T>> tss) {
		return IterMaker.concatList(tss) ;
	}
	
	@SafeVarargs
	public static <T> Set<T> concatSet(Iterable<T>... tss) {
		return IterMaker.concatSet( IterMaker.newList(tss) ) ;
	}
	public static <T> Set<T> concatSet(Iterable<Iterable<T>> tss) {
		Stream<T> stream = new ArrayList<T>().stream() ;
		for (Iterable<T> ts: tss) {
			Stream<T> oldstr = stream ;
			Stream<T> newstr = IterMaker.newCollection(ts).stream() ;
			stream = Stream.concat(oldstr, newstr) ;
		}
		
		return IterMaker.newSet(stream) ;
	}
	
	@SafeVarargs
	public static <T> SortedSet<T> concatSortedSet(Iterable<T>... tss) {
		return IterMaker.concatNavigableSet(tss) ;
	}
	public static <T> SortedSet<T> concatSortedSet(Iterable<Iterable<T>> tss) {
		return IterMaker.concatNavigableSet(tss) ;
	}
	
	@SafeVarargs
	public static <T> NavigableSet<T> concatNavigableSet(Iterable<T>... tss) {
		return IterMaker.concatNavigableSet( IterMaker.newIterable(tss) ) ;
	}
	public static <T> NavigableSet<T> concatNavigableSet(Iterable<Iterable<T>> tss) {
		Stream<T> stream = new ArrayList<T>().stream() ;
		for (Iterable<T> ts: tss) {
			Stream<T> oldstr = stream ;
			Stream<T> newstr = IterMaker.newCollection(ts).stream() ;
			stream = Stream.concat(oldstr, newstr) ;
		}
		
		return IterMaker.newNavigableSet(stream) ;
	}
	
	@SafeVarargs
	public static <T> List<T> concatList(Iterable<T>... tss) {
		return IterMaker.concatList( IterMaker.newIterable(tss)) ;
	}
	public static <T> List<T> concatList(Iterable<Iterable<T>> tss) {
		Stream<T> stream = new ArrayList<T>().stream() ;
		for (Iterable<T> ts: tss) {
			Stream<T> oldstr = stream ;
			Stream<T> newstr = IterMaker.newCollection(ts).stream() ;
			stream = Stream.concat(oldstr, newstr) ;
		}
		
		return IterMaker.newList(stream) ;
	}
}
