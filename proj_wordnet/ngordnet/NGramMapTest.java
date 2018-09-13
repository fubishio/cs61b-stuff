package ngordnet;

import static org.junit.Assert.*;

import java.util.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class NGramMapTest {

	@Test
	public void testCount() {
		NGramMap ng = new NGramMap("./ngrams/words_that_start_with_q.csv", "./ngrams/total_counts.csv");
		assertEquals(ng.countInYear("quantity", 1574), 1);
	}
	
	@Test
	public void testgetRecord() {
		NGramMap ng = new NGramMap("./ngrams/words_that_start_with_q.csv", "./ngrams/total_counts.csv");
		YearlyRecord x = ng.getRecord(1683);
		assertEquals(x.words().contains("quiet"), true);
		assertEquals(x.counts().contains(177), true);
		assertEquals(x.words().contains("questioning"), true);
		assertEquals(x.counts().contains(15), true);
		assertEquals(x.words().contains("Quality"), true);
		assertEquals(x.counts().contains(147), true);
		
	}
	
	@Test
	public void testCountYearRestraints() throws FileNotFoundException {
		NGramMap ng = new NGramMap("./ngrams/words_that_start_with_q.csv", "./ngrams/total_counts.csv");
		Integer c = 0;
		for (Integer x : ng.countHistory("quantity", 1600, 1608).values()) {
			c = c + x;
		}
		assertEquals(c, 23, 0.01);
		assertEquals(ng.countHistory("quantity", 1600, 1608).values().contains(3), true);
	}

	@Test
	public void testWeightHistory() throws FileNotFoundException {
		NGramMap ng = new NGramMap("./ngrams/words_that_start_with_q.csv", "./ngrams/total_counts.csv");
		TimeSeries Test = ng.weightHistory("quantity", 1600, 1608);
		
		assertEquals(Test.get(1605) , 3 / (double) 14493);
		/*I had to make sure to cast to double since dividing by a long doesn't work*/
	}
	
	@Test
	public void testSummedWeightHistory() throws FileNotFoundException {
		NGramMap ng = new NGramMap("./ngrams/words_that_start_with_q.csv", "./ngrams/total_counts.csv");
		Collection<String> TestArray = new ArrayList<String>();
		TestArray.add("quantity"); /*in 1605, referenced 3 times*/
		TestArray.add("quit");		/*in 1605, referenced 1 time*/
		
		
		TimeSeries Test = ng.summedWeightHistory(TestArray, 1600, 1608);
		Long c = Long.parseLong("0");
		assertEquals(Test.get(1605) , 4 / (double) 14493);

	}	
	
	@Test
	public void testProcessedHistory() throws FileNotFoundException {
		NGramMap ng = new NGramMap("./ngrams/words_that_start_with_q.csv", "./ngrams/total_counts.csv");
		WordLengthProcessor wlp = new WordLengthProcessor();
		assertEquals(ng.processedHistory(1605, 1615, wlp).get(1610), 5.87, 0.01);
		/*had to find 5.87 manually to double check my methods
		 *  since the right answer is more guaranteed*/
	}
}
