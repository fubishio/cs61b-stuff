import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class AutocompleteTest {

/*	@Test
	public void test() {
		String[] x = {"banana", "toast", "cereal"};
		double[] t = {2.0, 3.3, 80.0};
		Autocomplete testing = new Autocomplete(x, t);
		assertEquals(testing.weightOf("banana"), 2.0, 0);
		assertEquals(testing.weightOf("toast"), 3.3, 0);
		assertEquals(testing.weightOf("cereal"), 80.0, 0);
		assertEquals(testing.weightOf("pancakes"), 0.0, 0);
	}
	
	@Test 
	public void testTopMatch() {
		String[] x = {"banana", "toast", "cereal"};
		double[] t = {2.0, 3.3, 80.0};
		Autocomplete testing1 = new Autocomplete(x, t);
		assertEquals(testing1.topMatch("ba"), "banana");
		String[] x1 = {"banana", "bagel", "boris", "banapple", "bagelette"};
		double[] t2 = {1.0, 2.0, 4.0, 3.0, 5.0};
		Autocomplete testing2 = new Autocomplete(x1, t2);
		assertEquals(testing2.topMatch("ba"), "bagelette");
	}
	
	@Test
	public void testTopMatches() {
		String[] x = {"banana", "bagel", "boris", "banapple", "bagelette"};
		double[] t = {1.0, 2.0, 4.0, 3.0, 5.0};
		Autocomplete testing2 = new Autocomplete(x, t);
		ArrayList<String> compare = new ArrayList<String>();
		compare.add("bagelette");
		compare.add("bagel");
		ArrayList<String> compare2 = new ArrayList<String>();
		compare2.add("bagelette");
		compare2.add("boris");
		compare2.add("banapple");
		compare2.add("bagel");
		compare2.add("banana");
		ArrayList<String> compare3 = new ArrayList<String>();
		compare3.add("bagelette");
		
		assertEquals(testing2.topMatches("bag", 2).toString(), compare.toString());
		assertEquals(testing2.topMatches("b", 7).toString(), compare2.toString());
		assertEquals(testing2.topMatches("b", 1).toString(), compare3.toString()); 
		assertEquals(testing2.topMatches("", 1).toString(), compare3.toString());
		assertEquals(testing2.topMatches((""), 7).toString(), compare2.toString());
	}
	
	@Test
	public void TestTopMatch() {
		String[] x = {"smog", "buck", "sad", "spite", "spit", "spy"};
		double[] t = {5, 10, 12, 20, 15, 7};
		Autocomplete testing3 = new Autocomplete(x, t);
		assertEquals(testing3.topMatch("s"), "spite");
		assertEquals(testing3.topMatch("spite"), "spite");
		assertEquals(testing3.topMatch(" "), "spite");
		
		In in = new In("tiny.txt");
        int N = Integer.parseInt(in.readLine());
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }
        Autocomplete student = new Autocomplete(terms, weights);
        assertEquals(student.topMatch(" "), "spite");
        assertEquals(student.topMatch("sp"), "spite");
        assertEquals(student.topMatch("sm"), "smog");
        assertEquals(student.topMatch(""), "spite");
        
	}
	
	@Test 
	public void TestTopMatch1() {
		In in = new In("cities.txt");
        int N = Integer.parseInt(in.readLine());
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }
        Autocomplete student = new Autocomplete(terms, weights);
        assertEquals(student.topMatch("Arsa"), "Arsago Seprio, Italy");
        ArrayList<String> compare4 = new ArrayList<String>();
        compare4.add("Arsago Seprio, Italy");
        assertEquals(student.topMatches("Arsa", 1).toString(), compare4.toString());
        assertEquals(student.topMatches("Renmark", 7), "banana");
	}
	
	@Test
	public void TestTopMatch2() {
		In in = new In("fortune1000-randomly-ordered.txt");
        int N = Integer.parseInt(in.readLine());
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }
        Autocomplete student = new Autocomplete(terms, weights);
        assertEquals(student.topMatch(""), "Wal-Mart Stores");
	}
	
	@Test
	public void TestTopMatches() {
		String[] x = {"banana", "bagel", "boris", "banapple", "bagelette", "apple"};
		double[] t = {1.0, 2.0, 4.0, 3.0, 5.0, 7.0};
		Autocomplete testing2 = new Autocomplete(x, t);
		ArrayList<String> compare = new ArrayList<String>();
		compare.add("bagelette");
		compare.add("bagel");
		ArrayList<String> compare2 = new ArrayList<String>();
		compare2.add("bagelette");
		compare2.add("boris");
		compare2.add("banapple");
		compare2.add("bagel");
		compare2.add("banana");
		ArrayList<String> compare3 = new ArrayList<String>();
		compare3.add("apple");
		ArrayList<String> compare4 = new ArrayList<String>();
		compare4.add("apple");
		compare4.add("bagelette");
		compare4.add("boris");
		compare4.add("banapple");
		compare4.add("bagel");
		compare4.add("banana");
		
		assertEquals(testing2.topMatches("bag", 2).toString(), compare.toString());
		assertEquals(testing2.topMatches("b", 7).toString(), compare2.toString());
		assertEquals(testing2.topMatches("b", 1).toString(), compare3.toString()); 
		assertEquals(testing2.topMatches("", 1).toString(), compare3.toString());
		assertEquals(testing2.topMatches((""), 7).toString(), compare4.toString());

	}*/

	@Test 
	public void TestTopMatch1() {
		In in = new In("cities.txt");
        int N = Integer.parseInt(in.readLine());
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }
        Autocomplete student = new Autocomplete(terms, weights);
        assertEquals(student.topMatches("Renmark", 7), "banana");
	}
}
