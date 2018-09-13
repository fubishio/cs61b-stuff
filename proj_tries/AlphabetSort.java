import java.util.HashSet;
import java.util.Scanner;

/**
 * AlphabetSort. Organizes strings in an input file
 * where the first string is the alphabet used to sort
 * and the rest are words to sort.
 * @author John
 */

public class AlphabetSort {
    static String customAlpha;
    
    /**
     * This reads in the input file and creates a new Trie.
     * Checks if the alphabet is valid and words are given
     * Then uses the sorting code in Tries to sort it, for
     * more details on how its sorted, check out Trie.java
     * Then systematically prints out the sorted words.
     * @param args The strings from the input file
     */
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        if (reader.hasNextLine()) {
            customAlpha = reader.nextLine();
        }
        
        if (!unique(customAlpha)) {
            reader.close();
            throw new IllegalArgumentException();
        }
        
        Trie customTrie = new Trie();
        
        if (!reader.hasNext()) {
            reader.close();
            throw new IllegalArgumentException();
        }
        
        while (reader.hasNextLine()) {
            customTrie.insert(reader.nextLine());
        }
        for (String x : customTrie.alphaSort(customAlpha)) {
            System.out.println(x);
        }
        
        reader.close();
    }
    
    /** 
     * checks if a string has duplicate characters
     * @param s The alphabet you want to check for duplicates
     * @return Boolean of whether or not the alphabet does not
     * have duplicates
     */
    public static boolean unique(String s) {
        char[] checking = s.toCharArray();
        HashSet<Character> recorded = new HashSet<Character>();
        for (char x : checking) {
            if (recorded.contains(x)) {
                return false;
            } else {
                recorded.add(x);
            }
        }
        
        return true;
    }
    
    
    
}
