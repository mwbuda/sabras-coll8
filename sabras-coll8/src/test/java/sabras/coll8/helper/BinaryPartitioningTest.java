package sabras.coll8.helper;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;

public class BinaryPartitioningTest {

	private Random random = new Random() ;
	
	private byte[] createBytes(Integer size) {
		byte[] res = new byte[size] ;
		this.random.nextBytes(res);
		return res ;
	}
	
	@Test
	public void testIntoMax_EvenDivision() 
	throws Throwable {
		
		byte[] input = this.createBytes(32) ;
		BinaryPartitioning<byte[]> partioner = BinaryPartitioning.IntoMax.fromByteArray(8) ;
		
		List<byte[]> partitions = partioner.apply(input) ;
		
		assertEquals(4, partitions.size()) ;
		for (byte[] piece : partitions) assertEquals(8, piece.length) ;
		
	}
	
	@Test
	public void testIntoMax_SmallUnEvenDivision() 
	throws Throwable {
		
		byte[] input = this.createBytes(32) ;
		BinaryPartitioning<byte[]> partioner = BinaryPartitioning.IntoMax.fromByteArray(7) ;
		
		List<byte[]> partitions = partioner.apply(input) ;
		
		assertEquals(5, partitions.size()) ;
		for (byte[] piece : partitions) assertEquals(7, piece.length) ;
	}
	
	@Test
	public void testIntoMax_LargeUnEvenDivision() 
	throws Throwable {
		
		byte[] input = this.createBytes(32) ;
		BinaryPartitioning<byte[]> partioner = BinaryPartitioning.IntoMax.fromByteArray(9) ;
		
		List<byte[]> partitions = partioner.apply(input) ;
		
		assertEquals(4, partitions.size()) ;
		for (byte[] piece : partitions) assertEquals(9, piece.length) ;
	}
	
	@Test
	public void testIntoMax_TooLargePartitionSize()
	throws Throwable {
		byte[] input = this.createBytes(32) ;
		BinaryPartitioning<byte[]> partioner = BinaryPartitioning.IntoMax.fromByteArray(33) ;
		
		List<byte[]> partitions = partioner.apply(input) ;
		
		assertEquals(1, partitions.size()) ;
		for (byte[] piece : partitions) assertEquals(33, piece.length) ;
	}

}
