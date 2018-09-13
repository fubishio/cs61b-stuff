package ngordnet;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordLengthProcessorTest {

	@Test
	public void test() {
		YearlyRecord yr = new YearlyRecord();
        yr.put("prism", 300);
        yr.put("cat", 300);
        yr.put("catz", 300);
        WordLengthProcessor wlp = new WordLengthProcessor();
        assertEquals(wlp.process(yr), 4.0, 0.01);
        
        YearlyRecord yr1 = new YearlyRecord();
        yr1.put("sixes", 200);
        yr1.put("darn", 300);
        yr1.put("a", 800);
        assertEquals(wlp.process(yr1), 2.307, 0.01);
	}

}
