import static org.junit.Assert.*;

import org.junit.Test;


public class AlphabetSortTest {

    //for tests regarding the sorting algorithm, I put in the test for tries
    //its very primitive but thats because the algorithm is literally a
    //algorithm for iterating over the whole trie, there isn't much to test
    @Test
    public void testUnique() {
        assertEquals(AlphabetSort.unique("abcdefghijklmnopqrstuvwxyz"), true);
        assertEquals(AlphabetSort.unique("banananana"), false);
    }

}
