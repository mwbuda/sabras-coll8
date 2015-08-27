package sabras.coll8.helper;

import java.util.Formatter;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFormatter 
implements Function<String, String> {

	//TODO
	public static final RegexFormatter ForUUID = RegexFormatter.of(
		"%1$s-%2$s-%3$s-%4$s-%5$s", 
		Pattern.compile(
			"(^[a-f0-9]{8,8})[-]?([a-f0-9]{4,4})[-]?([a-f0-9]{4,4})[-]?([a-f0-9]{4,4})[-]?([a-f0-9]{12,12})$", 
			Pattern.CASE_INSENSITIVE
		)
	)  ;
	
	final Pattern pattern ;
	final String format ;
	final Boolean ignoreFirst ;
	
	public static final RegexFormatter of(String f, String p) {
		return new RegexFormatter(f, Pattern.compile(p), true) ;
	}
	public static final RegexFormatter of(String f, Pattern p) {
		return new RegexFormatter(f,p,true) ;
	}
	
	public static final RegexFormatter withWholeMatch(String f, String p) {
		return new RegexFormatter(f,Pattern.compile(p), false) ;
	}
	public static final RegexFormatter withWholeMatch(String f, Pattern p) {
		return new RegexFormatter(f, p, false) ;
	}
	
	
	private RegexFormatter(String f, Pattern p, boolean ignoreWholeMatch) {
		this.pattern = p ;
		this.format = f ;
		this.ignoreFirst = ignoreWholeMatch ;
	}
	
	@Override
	public String apply(String s) {
		if (s == null) return null ;
		Matcher match = this.pattern.matcher(s) ;
		if (!match.matches()) return null ;
		
		StringBuilder buffer = new StringBuilder() ;
		Formatter formatter = new Formatter(buffer) ;
		
		int matchCount = match.groupCount() + 1 ;
		int offset = this.ignoreFirst ? 1 : 0 ;
		Object[] matches = new String[this.ignoreFirst ? matchCount - offset : matchCount] ;
		for (int i = 0 ; (i + offset) < matchCount ; i++) matches[i] = match.group(i + offset) ;
		
		formatter.format(this.format, matches) ;
		formatter.close();
		return buffer.toString() ;
	}

}
