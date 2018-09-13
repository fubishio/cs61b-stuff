import static org.junit.Assert.*;

import org.junit.Test;


public class SearchTreeTest {

    @Test
    public void testBasicTreeFunctionality() {
        SearchTree testing = new SearchTree('a');
        testing.insert("apple", 2.0);
        assertEquals(testing.root() == null, false);
        assertEquals(testing.root().letter, 'a');
        assertEquals(testing.root().mid.letter, 'p');
    }
    
    @Test
    public void testFindWeight() {
        SearchTree testing1 = new SearchTree('a');
        testing1.insert("anaheim", 4.00);
        testing1.insert("appletini", 2.00);
        assertEquals(testing1.findWeight(testing1.root(), "anaheim", 0), 4.0, 0);
        assertEquals(testing1.findWeight(testing1.root(), "appletini", 0), 2.0, 0);
        assertEquals(testing1.findWeight("anaheim"), 4.0, 0);
        assertEquals(testing1.findWeight("appletini"), 2.0, 0);
/*        assertEquals(testing1.findWeight("banana"), 0.0, 0);*/
    }

    @Test
    public void testPrefixes() {
        SearchTree testing2 = new SearchTree('a');
        testing2.insert("anaheim", 2.0);
        testing2.insert("antsy", 3.0);
        testing2.insert("ants", 2.5);
        testing2.insert("agoraphobia", 90.0);
        assertEquals(testing2.prefixNode(testing2.root(), "an", 0).letter, 'n');
        assertEquals(testing2.prefixes("an").keySet().toArray()[0], "antsy");
        assertEquals(testing2.prefixes("an").keySet().toArray()[1], "ants");
        assertEquals(testing2.prefixes("an").keySet().toArray()[2], "anaheim");
        assertEquals(testing2.prefixNode(testing2.root(), "a", 0).letter, 'a');
        assertEquals(testing2.prefixes("a").keySet().toArray()[0], "agoraphobia");
        assertEquals(testing2.prefixes("a").keySet().toArray()[1], "antsy");
        assertEquals(testing2.prefixes("a").keySet().toArray()[2], "ants");
        assertEquals(testing2.prefixes("a").keySet().toArray()[3], "anaheim");
        
    }
    
    @Test
    public void testFindAllWords() {
        SearchTree testing3 = new SearchTree('a');
        testing3.insert("a", 2.0);
        assertEquals(SearchTree.sortByValues(testing3.findAllWords(testing3.root())).keySet().toArray()[0], "a");
        testing3.insert("apple", 90.0);
        assertEquals(SearchTree.sortByValues(testing3.findAllWords(testing3.root())).keySet().toArray()[0], "apple");
        testing3.insert("banana", 900.0);
        assertEquals(SearchTree.sortByValues(testing3.findAllWords(testing3.root())).keySet().toArray()[0], "banana");
    }
    
    @Test 
    public void testPrefixes1() {
    	SearchTree testing4 = new SearchTree('s');
    	testing4.insert("smog", 5);
    	testing4.insert("buck", 10);
    	testing4.insert("sad", 12);
    	testing4.insert("spite", 20);
    	testing4.insert("spit", 15);
    	testing4.insert("spy", 7);
    	System.out.println(testing4.prefixes("spite").keySet().toArray()[0]);
    	/*System.out.println(testing4.prefixes("spite").keySet().toArray()[1]);*/
    	
    }
    
    @Test
    public void testGreatest() {
    	SearchTree testing4 = new SearchTree('s');
    	testing4.insert("smog", 5);
    	testing4.insert("buck", 10);
    	testing4.insert("sad", 12);
    	testing4.insert("spite", 20);
    	testing4.insert("spit", 15);
    	testing4.insert("spy", 7);
    	assertEquals(testing4.greatest(testing4.root(), testing4.root().maxWeight), "spite");
    	assertEquals(testing4.findWeight("spite"), 20.0, 0);
    }
    
}
