import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Implements autocomplete on prefixes for a given dictionary of terms and weights.
 * Uses my implementation of a Ternary Search Tree
 * for more details checkout SearchTree.java
 * @author John
 */
public class Autocomplete {
    SearchTree data;
    /**
     * Initializes required data structures from parallel arrays.
     * @param terms Array of terms.
     * @param weights Array of weights.
     */
    public Autocomplete(String[] terms, double[] weights) {
        if (terms.length != weights.length) {
            throw new IllegalArgumentException("Not the same amount of terms and weights");
        }
        HashSet<String> checker = new HashSet<String>();
        for (String x : terms) {
            checker.add(x);
        }
        if (checker.size() != terms.length) {
            throw new IllegalArgumentException("cannot contain duplicates");
        }
        data = new SearchTree(terms[0].toCharArray()[0]);
        for (int i = 0; i < terms.length; i = i + 1) {
            if (weights[i] < 0) {
                throw new IllegalArgumentException("Weights cannot be negative");
            }
            data.insert(terms[i], weights[i]);
        }
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param term The string which weight you want
     * @return the weight of the String you wanted
     */
    public double weightOf(String term) {
        return data.findWeight(term);
    }

    /**
     * Return the top match for given prefix, or null if there is no matching term.
     * @param prefix Input prefix to match against.
     * @return Best (highest weight) matching string in the dictionary.
     */
    public String topMatch(String prefix) {
        /*Map<String, Double> pointer = data.prefixes(prefix);
        if (pointer != null) {*/
        SearchTree.SNode x = data.prefixNode(data.root(), prefix, 0);
        if (x == null) {
            return null;
        }
        if (prefix.equals("")) {
            return data.greatest(data.root(), data.root().maxWeight);
        }
        if (x.isWord) {
            if (x.weight == x.maxWeight) {
                return x.word;
            }
        }
        return data.greatest(x.mid, x.mid.maxWeight);
        /*return (String) pointer.keySet().toArray()[0];*/
    }

    /**
     * Returns the top k matching terms (in descending order of weight) as an iterable.
     * If there are less than k matches, return all the matching terms.
     * @param prefix The prefix or few letters you want to precede the words you want to search
     * @param k The amount of matches you want returned
     * @return The top k matching terms
     */
    public Iterable<String> topMatches(String prefix, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k cannot be negative");
        }    
        SearchTree.SNode z = data.prefixNode(data.root(), prefix, 0);
        if (z == null) {
            return new ArrayList<String>();
        }
        if (k == 1) {
            ArrayList<String> retVal = new ArrayList<String>();
            retVal.add(data.greatest(z, z.maxWeight));
            return retVal;
        }
        if (prefix.equals("")) {
            return data.topMatches(z, k, z, false);
        }
        Iterable<String> retVal = data.topMatches(z.mid, k, z, false);
        data.counterClear();
        return retVal;
    }

    /**
     * Returns the highest weighted matches within k edit distance of the word.
     * If the word is in the dictionary, then return an empty list.
     * @param word The word to spell-check
     * @param dist Maximum edit distance to search
     * @param k    Number of results to return 
     * @return Iterable in descending weight order of the matches
     */
    public Iterable<String> spellCheck(String word, int dist, int k) {
        LinkedList<String> results = new LinkedList<String>();  
        /* YOUR CODE HERE; LEAVE BLANK IF NOT PURSUING BONUS */
        return results;
    }
    /**
     * Test client. Reads the data from the file, then repeatedly reads autocomplete 
     * queries from standard input and prints out the top k matching terms.
     * @param args takes the name of an input file and an integer k as command-line arguments
     */
    public static void main(String[] args) {
        // initialize autocomplete data structure
        In in = new In(args[0]);
        int N = in.readInt();
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }

        Autocomplete autocomplete = new Autocomplete(terms, weights);

        // process queries from standard input
        int k = Integer.parseInt(args[1]);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            for (String term : autocomplete.topMatches(prefix, k)) {
                StdOut.printf("%14.1f  %s\n", autocomplete.weightOf(term), term);
            }
        }
    }
}
