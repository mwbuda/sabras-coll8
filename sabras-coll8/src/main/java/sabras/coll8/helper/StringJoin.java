package sabras.coll8.helper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class StringJoin {

	private final String separator ;
	
	public static StringJoin with(String sep) {
		return new StringJoin(sep) ;
	}
	
	public static StringJoin simple() {
		return new StringJoin("") ;
	}
	
	public static StringJoin withCommas() {
		return new StringJoin(",") ;
	}
	
	public static StringJoin withSpaces() {
		return new StringJoin(" ") ;
	}
	
	private StringJoin(String sep) {
		this.separator = sep ;
	}
	
	public String join(Object... strs) {
		return this.join(
			Arrays.asList(strs).stream().map(o -> o.toString())
		) ;
	}
	public String join(Stream<?> strs) {
		@SuppressWarnings("unchecked")
		Stream<Object> objs = (Stream<Object>) strs ;
		Iterable<Object> asIterable =  objs.collect(Collectors.toList()) ;
		return this.join(asIterable) ;
	}
	public <X> String join(Iterator<X> strs) {
		Iterable<X> asIterable = () -> strs ;
		return this.join(asIterable) ;
	}
	public String join(Spliterator<? extends Object> strs) {
		return this.join( StreamSupport.stream(strs, false)) ;
	}
	public String join(Iterable<? extends Object> strs) {
		StringBuilder builder = new StringBuilder() ;
		for (Object str : strs) builder.append(str.toString()).append(this.separator) ;
		//- (1 for end + sep length)
		Integer len = builder.length() - (this.separator.length()) ;
		return builder.substring(0, len) ;
	}
}
