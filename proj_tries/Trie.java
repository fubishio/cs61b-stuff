import java.util.ArrayList;

/**
 * Prefix-Trie. Supports linear time find() and insert(). 
 * Should support determining whether a word is a full word in the 
 * Trie or a prefix.
 * I put my citations outside the javadoc comments, I had no idea 
 * if they are supposed to be in or out of the javadoc comments
 * @author John
 */
public class Trie {
    private Node root;
    //most of the underlying data structure was just taken from lecture
    /**
     * This is just the node that stores info. It contains the 
     * letter, the children node, whether or not it is a full word, 
     * and the,  word if it is a full word.
     */
    private static class Node {
        char val;
        boolean isFullWord;
        String word;
        //was considering just making an array of 256 because an array
        //is definitely faster, but to be honest, it'd seem more of
        //a hashmap than an actual tree, I don't think that's what'd you
        //guys were looking for
        ArrayList<Node> children;
        
        /**
         * Root node constructor
         */
        public Node() {
            children = new ArrayList<Node>();
        }
        
        /**
         * basic node constructor with isFullWord set to false
         * @param x The letter you want to store
         */
        public Node(char x) {
            this.val = x;
            this.children = new ArrayList<Node>();
            this.isFullWord = false;
        }
        
        /**
         * Accessor for a child of a node
         * @param x The letter of the child (Node) you want to find
         * @return The child with that character or null if such
         * a child does not exist
         */
        public Node child(char x) {
            if (this.children == null) {
                return null;
            } else {
                for (Node n : children) {
                    if (n.val == x) {
                        return n;
                    }
                }
            }
            return null;
        }
        
    }
    
    /**
     * Initial constructor of the Trie
     */
    public Trie() {
        root = new Node();
    }
    
    /**
     * Searches for word you want to find
     * @param s String you want to find
     * @param isFullWord Boolean of whether or not the string
     * you entered is a full word
     * @return Boolean of whether or not such a string exists in 
     * this Trie
     */
    public boolean find(String s, boolean isFullWord) {
        Node pointer = root;
        if (isFullWord) {
            for (char x : s.toCharArray()) {
                if (pointer.child(x) == null) {
                    return false;
                } else {
                    pointer = pointer.child(x);
                }
            }
            return pointer.isFullWord;
        } else {
            //thanks to http://stackoverflow.com/questions
            ///10006165/converting-string-to-character-array-in-java
            // for syntax regarding word to char array
            for (char x : s.toCharArray()) {
                if (pointer.child(x) == null) {
                    return false;
                } else {
                    pointer = pointer.child(x);
                }
            }
            return true;
        }
    }
    
    //GSI kind of drew out the insertion method for me, well
    //it was more of just a more indepth explanation of Tries
    /**
     * Inserts a String into the Trie
     * @param s The string you want to put into the Trie
     */
    
    public void insert(String s) {
        //error cases
        if (root == null) {
            System.out.println("Must initialize trie first");
            return;
        }
        if (s == null || s.equals("") || s.equals(" ")) {
            throw new IllegalArgumentException("Please give valid arguments");
        }
        if (find(s, true)) {
            System.out.println("Word is already stored");
            return;
        }
        Node pointer = root;
        for (char x : s.toCharArray()) {
            if (pointer.child(x) != null) {
                pointer = pointer.child(x);
            } else {
                pointer.children.add(new Node(x));
                pointer = pointer.child(x);
            }
        }
        pointer.word = s;
        pointer.isFullWord = true;
    }
    
    /**
     * Gives out a sorted listed of strings depending on alphabet
     * more of a wrapper class for the actual recursive one
     * @param alphabet The alphabet of which order you want to use
     * @return An sorted ArrayList of strings
     */
    public ArrayList<String> alphaSort(String alphabet) {
        ArrayList<String> retVal;
        //base case
        if (root.children.size() == 0) {
            retVal = new ArrayList<String>();
        } else {
            char[] alphaChar = alphabet.toCharArray();
            retVal = alphaSort(alphaChar, 0, root);
        }
        return retVal;
    }
    //significant help received from Mingjian Lu in identifying the errors
    //in my recursive base cases
    /**
     * Gives a sorted ArrayList of words according to the provided alphabet
     * @param alpha The alphabet whose order you want to use to sort
     * @param count The int index placeholder in the alphabet
     * @param pointer The Node you are currently on
     * @return The strings of a Node in an ArrayList that is sorted 
     */
    public ArrayList<String> alphaSort(char[] alpha, int count, Node pointer) {
        ArrayList<String> retVal = new ArrayList<String>();
        /*char z = alpha.charAt(count);*/
        /*char[] alphaChar = alpha.toCharArray();*/
        if (count == alpha.length) {
            return retVal;
        }
        /*if (pointer != null) {
            if (pointer.isFullWord) {    
                retVal.add(pointer.word);
            }
        }*/
        if (pointer.child(alpha[count]) != null) {
            if (pointer.child(alpha[count]).isFullWord) {
                retVal.add(pointer.child(alpha[count]).word);
            }
            retVal.addAll(alphaSort(alpha, 0, pointer.child(alpha[count])));
            
        } 
        retVal.addAll(alphaSort(alpha, count + 1, pointer));
        
        return retVal;
    }
    
    
    
    
}
