package sabras.coll8;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
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
 * convience class designed to quickly instantiate iterables & collections from a source input
 * 	you want to transparently convert.
 *
 */
public class CollectConvert {

	//from iterable
	public CollectConvert() {
		throw new UnsupportedOperationException() ;
	}
	
	//iterable
	public static <T> Iterable<T> newIterable(Iterable<T> ts) {
		return CollectConvert.newList(ts) ;
	}
	public static <T> Iterable<T> newIterable(Iterator<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return () -> ts ;
	}
	public static <T> Iterable<T> newIterable(@SuppressWarnings("unchecked") T... ts) {
		return CollectConvert.newList(ts) ;
	}
	public static <T> Iterable<T> newIterable(Stream<T> ts) {
		return CollectConvert.newList(ts) ;
	}
	public static <T> Iterable<T> newIterable(Spliterator<T> ts) {
		return CollectConvert.newList(ts) ;
	}
	
	//collection
	public static <T> Collection<T> newCollection(Iterable<T> ts) {
		return CollectConvert.newList(ts) ;
	}
	public static <T> Collection<T> newCollection(Iterator<T> ts) {
		return CollectConvert.newList(ts) ;
	}
	public static <T> Collection<T> newCollection(@SuppressWarnings("unchecked") T... ts) {
		return CollectConvert.newList(ts) ;
	}
	public static <T> Collection<T> newCollection(Stream<T> ts) {
		return CollectConvert.newList(ts) ;
	}
	public static <T> Collection<T> newCollection(Spliterator<T> ts) {
		return CollectConvert.newList(ts) ;
	}
	
	//set
	public static <T> Set<T> newSet(Iterable<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return CollectConvert.newSet(ts.spliterator()) ;
	}
	public static <T> Set<T> newSet(Iterator<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return CollectConvert.newSet( () -> ts ) ;
	}
	public static <T> Set<T> newSet(@SuppressWarnings("unchecked") T... ts) {
		if (ts == null) return new HashSet<>() ;
		return new HashSet<>(Arrays.asList(ts)) ;
	}
	public static <T> Set<T> newSet(Stream<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return ts.collect(Collectors.toSet()) ;
	}
	public static <T> Set<T> newSet(Spliterator<T> ts) {
		if (ts == null) return new HashSet<>() ;
		return CollectConvert.newSet(StreamSupport.stream(ts, false)) ; 
	}
	
	//sorted set
	public static <T> SortedSet<T> newSortedSet(Iterable<T> ts) {
		return CollectConvert.newNavigableSet(ts) ;
	}
	public static <T> SortedSet<T> newSortedSet(Iterator<T> ts) {
		return CollectConvert.newNavigableSet(ts) ;
	}
	public static <T> SortedSet<T> newSortedSet(@SuppressWarnings("unchecked") T... ts) {
		return CollectConvert.newNavigableSet(ts) ;
	}
	public static <T> SortedSet<T> newSortedSet(Stream<T> ts) {
		return CollectConvert.newNavigableSet(ts) ;
	}
	public static <T> SortedSet<T> newSortedSet(Spliterator<T> ts) {
		return CollectConvert.newNavigableSet(ts) ;
	}
	
	//navigable set
	public static <T> NavigableSet<T> newNavigableSet(Iterable<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return CollectConvert.newNavigableSet(ts.spliterator()	) ;
	}
	public static <T> NavigableSet<T> newNavigableSet(Iterator<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return CollectConvert.newNavigableSet( () -> ts ) ;
	}
	public static <T> NavigableSet<T> newNavigableSet(@SuppressWarnings("unchecked") T... ts) {
		if (ts == null) return new TreeSet<>() ;
		return new TreeSet<>(Arrays.asList(ts)) ;
	}
	public static <T> NavigableSet<T> newNavigableSet(Stream<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return new TreeSet<>( ts.collect(Collectors.toSet()) ) ;
	}
	public static <T> NavigableSet<T> newNavigableSet(Spliterator<T> ts) {
		if (ts == null) return new TreeSet<>() ;
		return CollectConvert.newNavigableSet(StreamSupport.stream(ts, false)) ; 
	}
	
	//list
	public static <T> List<T> newList(Iterable<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return CollectConvert.newList(ts.spliterator()) ;
	}
	public static <T> List<T> newList(Iterator<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return CollectConvert.newList( () -> ts ) ;
	}
	public static <T> List<T> newList(@SuppressWarnings("unchecked") T... ts) {
		if (ts == null) return Arrays.asList() ;
		return Arrays.asList(ts) ;
	}
	public static <T> List<T> newList(Stream<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return ts.collect(Collectors.toList()) ;
	}
	public static <T> List<T> newList(Spliterator<T> ts) {
		if (ts == null) return Arrays.asList() ;
		return CollectConvert.newList(StreamSupport.stream(ts, false)) ; 
	}	
}
