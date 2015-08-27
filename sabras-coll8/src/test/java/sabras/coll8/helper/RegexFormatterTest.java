package sabras.coll8.helper;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

public class RegexFormatterTest {

	@Test
	public void testUuid() 
	throws Throwable {
		UUID rand = UUID.randomUUID() ;
		
		RegexFormatter formatter = RegexFormatter.ForUUID ;
		
		String res1 = formatter.apply(rand.toString()) ;
		assertEquals(rand.toString(), res1) ;
		assertEquals(rand, UUID.fromString(res1)) ;
		
		String res2 = formatter.apply(rand.toString().replaceAll("[-]", "")) ;
		assertEquals(rand.toString(), res2) ;
		assertEquals(rand, UUID.fromString(res2)) ;
	}
	
	@Test
	public void testBadMatch()
	throws Throwable {
		RegexFormatter formatter = RegexFormatter.of("%1$s-%3$s", "^(aaa)-(bbb)-(ccc)$") ;
		String input = "ccc" ;
		String output = formatter.apply(input) ;
		assertNull(output) ;
		
	}
	
	@Test
	public void testNullArg()
	throws Throwable {
		RegexFormatter formatter = RegexFormatter.of("%1$s-%3$s", "^(aaa)-(bbb)-(ccc)$") ;
		String input = null ;
		String output = formatter.apply(input) ;
		assertNull(output) ;
	}

}
