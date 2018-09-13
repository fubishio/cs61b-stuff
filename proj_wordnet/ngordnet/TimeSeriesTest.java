package ngordnet;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeSeriesTest {

	@Test
	public void testConstructor() {
		/*Testing my copy constructor implementation*/
		TimeSeries<Double> toCopy = new TimeSeries<Double>();
		toCopy.put(1992, 3.6);
		TimeSeries<Double> copy = new TimeSeries<Double>(toCopy);
		assertEquals(copy.get(1992), 3.6, 0.01);
	}
	
	@Test
	public void testBasicFunctionality() {
		TimeSeries<Double> testTS = new TimeSeries<Double>();
		testTS.put(1501, 6.0);
		testTS.put(-155, 3.2);
		testTS.put(0, 11.23);
		assertEquals(testTS.get(1501), 6.0, 0.01);
		assertEquals(testTS.get(-155), 3.2, 0.01);
		assertEquals(testTS.get(0), 11.23, 0.01);
		
		/*Test Year & Data Function*/
		assertEquals(testTS.years().contains(-155), true);
		assertEquals(testTS.data().contains(6.0), true);
	}
	
	@Test
	public void testMathTSPlusOperator() {
		TimeSeries<Double> testTS = new TimeSeries<Double>();
		testTS.put(1501, 6.0);
		testTS.put(-155, 3.2);
		testTS.put(0, 11.23);
		assertEquals(testTS.get(1501), 6.0, 0.01);
		assertEquals(testTS.get(-155), 3.2, 0.01);
		assertEquals(testTS.get(0), 11.23, 0.01);
		TimeSeries<Integer> testOperator = new TimeSeries<Integer>();
		testOperator.put(1501, 3);
		testOperator.put(-155, 1);
		testOperator.put(0, 2);
		TimeSeries<Double> resultant = testTS.plus(testOperator);
		assertEquals(resultant.get(1501), 9, 0.01);
		assertEquals(resultant.get(-155), 4.2, 0.01);
		assertEquals(resultant.get(0), 13.23, 0.01);
 	}
	
	@Test
	public void testMathTSDivisionOperator() {
		TimeSeries<Double> testTS = new TimeSeries<Double>();
		testTS.put(1501, 6.0);
		testTS.put(-155, 3.2);
		testTS.put(0, 11.23);
		assertEquals(testTS.get(1501), 6.0, 0.01);
		assertEquals(testTS.get(-155), 3.2, 0.01);
		assertEquals(testTS.get(0), 11.23, 0.01);
		TimeSeries<Double> testOperator = new TimeSeries<Double>();
		testOperator.put(1501, 3.0);
		testOperator.put(-155, 1.6);
		testOperator.put(0, 22.46);
		TimeSeries<Double> resultant = testTS.dividedBy(testOperator);
		assertEquals(resultant.get(1501), 2, 0.01);
		assertEquals(resultant.get(-155), 2, 0.01);
		assertEquals(resultant.get(0), 0.5, 0.01);
 	}
	
	
	
}
