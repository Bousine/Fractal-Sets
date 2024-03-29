package code;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Class which contains Julia Tests
 * 
 * @author Mark Kayutkin
 * @author Asif Hasan
 * @author Xiangshuai Gao
 */
public class JuliaTest {
	private JuliaSet obj;
	
	@Before
	public void setup(){
		obj = new JuliaSet(2, 255, 0, 512);		
	}
	
	/**
	 * Test for translating row to x
	 */
	@Test
	public void RowToXTest() {
		int row1 = 0;		
		int row2 = 255;
		int row3 = 511;
		int rows = 512;
		double start = -1.7;
		double end = 1.7;
	
		assertEquals(-1.7, obj.arrayToCoordinate(row1, start, end, rows), 0.001);
		assertEquals(-0.0066, obj.arrayToCoordinate(row2, start, end, rows), 0.001);
		assertEquals(1.693, obj.arrayToCoordinate(row3, start, end, rows), 0.001);
	}
	
	/**
	 * Test for translating column to y
	 */
	@Test
	public void ColtoYTest() {
		
		int row1 = 0;		
		int row2 = 255;
		int row3 = 511;
		
		int cols = 512;
		double start = -1.0;
		double end = 1.0;
	
		assertEquals(-1.0, obj.arrayToCoordinate(row1, start, end, cols), 0.001);
		assertEquals(-0.0039, obj.arrayToCoordinate(row2, start, end, cols), 0.001);
		assertEquals(0.996, obj.arrayToCoordinate(row3, start, end, cols), 0.001);
	}	

	/**
	 * Test for the escape time for a coordinate whose distance from the origin never exceeds the escape distance 
	 */
	@Test
	public void distanceNotPassedTest(){
		//Test for the escape time for a coordinate whose distance from the origin never exceeds the escape distance 
		int result = obj.escapeTime(1.0492187499999897, -0.234375);
		assertEquals(255, result);
	}
	
	/**
	 * Test for the escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop
	 */
	@Test
	public void distancePassedTest(){
		//Test for the escape time for a coordinate whose distance from the origin never exceeds the escape distance 
		int result = obj.escapeTime(1.6933593749999853, 0.9765625);
		assertEquals(1, result);
	}
	
	/**
	 * Test that the fractal returns a 2-d array
	 */
	@Test
	public void fractalArrayReturnCheck(){
		//Test that the fractal returns a 2-d array
		int[][] result = obj.fractalCalc();
		assertEquals(512, result.length);
		assertEquals(2048, result[0].length);
	}
	/**
	 * Tests the change for new escape distance
	 */
	@Test
	public void escapeDistanceChange(){
		obj = new JuliaSet(3, 255, 0, 512);
		assertEquals(obj.getEscapeDistance(), 3, 0.0);
		obj = new JuliaSet(10.55, 255, 0, 512);
		assertEquals(obj.getEscapeDistance(), 10.55, 0.0);
	}
	/**
	 * Tests the escape distance for new escape distance
	 */
	@Test
	public void escapeTimeforNewEscDist(){
		obj = new JuliaSet(3, 255, 0, 512);
		int result = obj.escapeTime(1.4538160469667272, -0.13502935420743645);
		assertEquals(result, 10);
	}
	/**
	 * Tests the new escape distance and escape distance
	 */
	@Test
	public void escapeTimeforNewEscDistAndTime(){
		obj = new JuliaSet(2, 135, 0, 512);
		int result = obj.escapeTime(1.0492187499999897, -0.234375);
		assertEquals(result, 135);
		
	}
	
	
}


