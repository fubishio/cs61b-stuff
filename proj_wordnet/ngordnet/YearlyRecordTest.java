package ngordnet;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class YearlyRecordTest {

	@Test
	public void testYearlyRecordHashMapOfStringInteger() {
		HashMap<String, Integer> hashCopy = new HashMap<String, Integer>();
        hashCopy.put("banana", 880);        
        hashCopy.put("grapple", 1016);
        hashCopy.put("kool-aid", 1542); 
        YearlyRecord copy = new YearlyRecord(hashCopy);
        assertEquals(copy.words().contains("kool-aid"), true);
        assertEquals(copy.words().contains("Pineapple"), false);
	}

	@Test
	public void testCount() {
		YearlyRecord yR = new YearlyRecord();
        yR.put("banana", 880);        
        yR.put("grapple", 1016);
        yR.put("kool-aid", 1542); 
        assertEquals(yR.count("banana"), 880, 0.01);
	}

	@Test(expected = NullPointerException.class)
	public void testNullAccess() {
		YearlyRecord yR = new YearlyRecord();
        yR.put("banana", 880);        
        yR.put("grapple", 1016);
        yR.put("kool-aid", 1542);
		assertEquals(yR.count("Apple"), 0, 0.01);
	}
	
	@Test
	public void testCounts() {
		YearlyRecord yR = new YearlyRecord();    
        yR.put("grapple", 1016);
        yR.put("kool-aid", 1542); 
        yR.put("banana", 880);    
        Number[] holder = new Number[3];
        int i = 0;
        for (Number x : yR.counts()) {
        	holder[i] = x;
        	i = i + 1;
        }
        assertEquals(holder[0].intValue(), 880, 0.01);
        assertEquals(holder[1].intValue(), 1016, 0.01);
        assertEquals(holder[2].intValue(), 1542, 0.01);
	}

	@Test
	public void testRank() {
		YearlyRecord yR = new YearlyRecord();    
        yR.put("grapple", 1016);
        yR.put("kool-aid", 1542); 
        yR.put("banana", 880);    
        yR.put("cookie", 1542);
        assertEquals(yR.rank("grapple"), 3, 0.01);
        assertEquals(yR.rank("kool-aid"), 1, 0.01);
        assertEquals(yR.rank("cookie"), 2, 0.01);
	}

	@Test
	public void testSize() {
		YearlyRecord yR = new YearlyRecord();    
        yR.put("grapple", 1016);
        yR.put("kool-aid", 1542); 
        yR.put("banana", 880);    
        yR.put("cookie", 1542);
        assertEquals(yR.size(), 4, 0.01);
	}

	@Test
	public void testWords() {
		YearlyRecord yR = new YearlyRecord();    
        yR.put("grapple", 1016);
        yR.put("kool-aid", 1542); 
        yR.put("banana", 880);    
        String[] holder = new String[3];
        int i = 0;
        for (String x : yR.words()) {
        	holder[i] = x;
        	i = i + 1;
        }
        assertEquals(holder[0], "banana");
        assertEquals(holder[1], "grapple");
        assertEquals(holder[2], "kool-aid");
	}

}
