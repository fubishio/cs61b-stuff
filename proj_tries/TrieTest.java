import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class TrieTest {

    @Test
    public void test() {
        Trie testing = new Trie();
        testing.insert("hello");
        testing.insert("hey");
        testing.insert("goodbye");
        assertEquals(testing.find("hell", false), true);
        assertEquals(testing.find("hello", true), true);
        assertEquals(testing.find("good", false), true);
        assertEquals(testing.find("bye", false), false);
        assertEquals(testing.find("heyy", false), false);
        assertEquals(testing.find("hell", true), false); 
    }
    
    @Test
    public void alphaSortTest() {
        Trie testing1 = new Trie();
        testing1.insert("hello");
        testing1.insert("goodbye");
        testing1.insert("goodday");
        testing1.insert("death");
        testing1.insert("deathly");
        ArrayList<String> printing = testing1.alphaSort("agdbecfhijklmnopqrsty");
        assertEquals(printing.get(0), "goodday");
        assertEquals(printing.get(1), "goodbye");
        assertEquals(printing.get(2), "death");
        assertEquals(printing.get(3), "deathly");
        assertEquals(printing.get(4), "hello");
    }
    
}
