import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Ternary Search Tree data structure I used in Autocomplete.java
 * @author John
 *
 */

public class SearchTree {
    /**
     * The node of my Ternary Search Tree stores the letter, the 3 
     * branches whether or not it is a word,the word itself it is 
     * a word and also the weight of the word
     */
    class SNode {
        
        char letter;
        SNode left, mid, right;
        double weight;
        double maxWeight;
        boolean isWord;
        String word;
        /**
         * Basic constructor for a Node in the TST
         * @param y The character you want to store in the node
         */
        public SNode(char y) {
            this.letter = y;
            this.left = null;
            this.mid = null;
            this.right = null;
            //base case 
            this.weight = 0;
            this.isWord = false;
            this.word = null;
            this.maxWeight = 0;
        }
    }
    
    private SNode root;
    private boolean hasInitialized = false;
    
    /**
     * Initializes the SearchTree with the first character of
     * the first word you put into the Tree
     * @param x The first character of the first word entered into the tree
     */
    public SearchTree(char x) {
        root = new SNode(x);
        hasInitialized = true;
    }
    
    /**
     * Wrapper of the recursive insert function
     * @param term The string you want to put into the tree
     * @param weights The weight of the word you want to put in the tree
     * @return the Node of the word
     */
    public SNode insert(String term, double weights) {
        if (weights < 0) {
            throw new IllegalArgumentException();
        }
        if (term == null || term.isEmpty() || term.equals("")) {
            throw new IllegalArgumentException();
        }
        return insert(root, term, 0, weights);
    }
    
    //the implementation of TST's and the find weight are almost 
    //identical to my implementation in tries.java
    /**
     * recursive insert function
     * @param pointer The node one is currently on, starts at root always
     * @param word The string you want to put into the tree
     * @param count The index placeholder of your position in the word
     * @param weight The weight of the word you want to put in the tree
     * @return the Node the pointer is currently on, since its redirected recursively
     */
    public SNode insert(SNode pointer, String word, int count, double weight) {
        //suggested by Mingjian Lu to speed up, it helped alot
        char x = word.charAt(count);
        if (pointer == null) {
            pointer = new SNode(x);
        }

        if (weight > pointer.maxWeight) {
            pointer.maxWeight = weight;
        }
        /*char[] characters = word.toCharArray();*/
        
        if (x > pointer.letter) {
            pointer.right = insert(pointer.right, word, count, weight);
        } else if (x < pointer.letter) {
            pointer.left = insert(pointer.left, word, count, weight);
        } else if (x == pointer.letter) {
            if (count + 1 < word.length()) {
                pointer.mid = insert(pointer.mid, word, count + 1, weight);
            } else {
                //kind of identical to my Trie.java implementation
                //data retrieval wise
                pointer.isWord = true;
                pointer.word = word;
                pointer.weight = weight;
            }
        }
        return pointer;
    }
    
    /**
     * Wrapper of the recursive findWeight function
     * @param word The word who's weight you want to find
     * @return the weight of the word or 0 if it doesnt exist
     */
    public double findWeight(String word) {
        return findWeight(root, word, 0);
    }
    
    /**
     * recursive implementation of finding weight
     * @param pointer The node you are currently on
     * @param word The string who's weight you want to find
     * @param count The index placeholder of your position in the word
     * @return The weight of the word you wanted to find
     */
    public double findWeight(SNode pointer, String word, int count) {
        if (word == null) {
            return 0;
        }
        if (word.length() == 0 || word.equals("")) {
            return 0;
        }
        char x = word.charAt(count);
        /*char[] characters = word.toCharArray();*/
        if (!hasInitialized) {
            System.out.println("Tree has not been intialized");
            return 0;
        }
        if (pointer == null) {
            /*System.out.println(word + " is not in the dictionary");*/
            return 0;
        }
        if (pointer.isWord) {
            if (pointer.word.equals(word)) {
                return pointer.weight;
            }
        }
        if (count <= word.length()) {
            if (x > pointer.letter) {
                return findWeight(pointer.right, word, count);
            } else if (x < pointer.letter) {
                return findWeight(pointer.left, word, count);
            } else if (x == pointer.letter) {
                if (count == word.length() - 1) {
                    return pointer.weight;
                }
                return findWeight(pointer.mid, word, count + 1);
            }
        }
        // took out pointer, not the same type of recursion, i guess you could say
        // here for compiler's sake
        return 0;
    }
    
    /*public HashMap<Double, String> prefixes(String str) {
        return prefixes(root, str, 0);
    }*/
    
    /**
     * Finds the word with the greatest weight under a given node
     * @param n The node you want to start on and recurse through
     * @param theMaxWeight the maxweight of the node beneath node n
     * @return the string of the heaviest word
     */
    public String greatest(SNode n, double theMaxWeight) {
        double templeft = 0;
        double tempmid = 0;
        double tempright = 0;
        if (n != null) {
            /*System.out.println(n.letter);*/
            /*System.out.println(n.letter);
            System.out.println(n.weight);
            System.out.println(theMaxWeight);
            System.out.println(n.isWord);*/
            if (n.isWord && n.weight == theMaxWeight) {
                /*System.out.println(n.word);*/
                return n.word;
            }
            if (n.left != null) {
                templeft = n.left.maxWeight;
            }
            if (n.mid != null) {
                tempmid = n.mid.maxWeight;
            }
            if (n.right != null) {
                tempright = n.right.maxWeight;
            }
            
            if (templeft > tempright && templeft > tempmid) {
                return greatest(n.left, theMaxWeight);
            }
            if (tempmid > templeft && tempmid > tempright) {
                return greatest(n.mid, theMaxWeight);
            }
            if (tempright > templeft && tempright > tempmid) {
                return greatest(n.right, theMaxWeight);
            }
            if (templeft == tempmid && templeft != 0) {
                return greatest(n.left, theMaxWeight);
            }
            if (templeft == tempright && templeft != 0) {
                return greatest(n.left, theMaxWeight);
            }
            if (tempmid == tempright && tempmid != 0) {
                return greatest(n.mid, theMaxWeight);
            }
        }
        return null;
    }
    
    /**
     * Wrapper of the functions for finding prefixes
     * @param str The prefix that you want in the words to be returned
     * @return words stored in the trie with the prefix
     */
    public Map<String, Double> prefixes(String str) {
        SNode pointer = prefixNode(root, str, 0);
        if (str.length() == 0 || str.equals("") || str.equals(" ")) {
            return sortByValues(findAllWords(root));
        }
        if (str.equals("")) {
            return sortByValues(findAllWords(pointer));
        }
        if (pointer.mid != null) {
            HashMap<String, Double> retVal = new HashMap<String, Double>();
            retVal.putAll(findAllWords(pointer.mid));
            if (pointer.isWord) {
                retVal.put(pointer.word, pointer.weight);
            }
            return sortByValues(retVal);
        } else {
            HashMap<String, Double> retVal = new HashMap<String, Double>();
            if (pointer.isWord) {
                retVal.put(pointer.word, pointer.weight);
            }
            return retVal;
        }
    }    
    
    /**
     * Finding the node where everything under has the prefix specified
     * @param pointer The node you are currently on
     * @param str the prefix whose node you want to find
     * @param counter The index placeholder in the prefix
     * @return The node where everything under has the specified prefix
     */
    public SNode prefixNode(SNode pointer, String str, int counter) {
        /*char[] characters = str.toCharArray();*/
        if (str.length() == 0 || str.equals("") || str.equals(" ")) {
            return pointer;
        }
        char x = str.charAt(counter);
        if (x > pointer.letter) {
            if (pointer.right == null) {
                return null;
            }
            return prefixNode(pointer.right, str, counter);
        } else if (x < pointer.letter) {
            if (pointer.left == null) {
                return null;
            }
            return prefixNode(pointer.left, str, counter);
        } else if (x == pointer.letter) {
            if (counter == str.length() - 1) {
                return pointer;
            }
            if (pointer.mid == null) {
                return null;
            }
            pointer = prefixNode(pointer.mid, str, counter + 1);
        }
        return pointer;
    }
    
    /**
     * finds all the words under a specified pointer and is sorted based on its 
     * weight in a descending order using the Reversed comparator
     * @param pointer Node you are currently on
     * @return all the words under the pointer Node with its corresponding weights
     */
    public HashMap<String, Double> findAllWords(SNode pointer) {
        HashMap<String, Double> retVal = new HashMap<String, Double>();
        if (pointer != null) {
            if (pointer.isWord) {
                retVal.put(pointer.word, pointer.weight);
            }
            if (pointer.right != null) {
                retVal.putAll(findAllWords(pointer.right));
            }
            if (pointer.left != null) {
                retVal.putAll(findAllWords(pointer.left));
            }
            if (pointer.mid != null) {
                retVal.putAll(findAllWords(pointer.mid));
            }
        }
        return retVal;
    }
    
    /**
     * reverse Comparator that sorts things from greatest to least
     * had to use code from stack overflow, my wasn't working and modified it a bit
     * http://stackoverflow.com/questions/1448369/how-to-sort-a-treemap-based-on-its-values
     */
    /**
     * Sorts the map based on its value, in this case, the doubles
     * @param <String> the type of data the key is
     * @param <Double> the type of data the value is
     * @param map The map you want to sort
     * @return a new sorted map
     */
    public static <String, Double extends Comparable<Double>> TreeMap<String, Double> 
        sortByValues(final Map<String, Double> map) {
        Comparator<String> valueComparator =  new Comparator<String>() {
            public int compare(String s1, String s2) {
                int compare = map.get(s2).compareTo(map.get(s1));
                if (compare == 0) {
                    return 1;
                } else {
                    return compare;
                }
            }
        };
        TreeMap<String, Double> sortedByValues = new TreeMap<String, Double>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
    /*class Reversed implements Comparator<Double> {
        
        @Override
        public int compare(String a, String b) {
            if (a > b) {
                return -1;
            } else if (a < b) {
                return 1;
            } else {
                return 0;
            }
        }
        
    }*/
    /**
     * getter method for root node
     * @return the root node
     */
    public SNode root() {
        return this.root;
    }
    
    //taken from 
    //http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
    class ValueSorter implements Comparator<String> {

        Map<String, Double> base;
        public ValueSorter(Map<String, Double> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
    
    private Map<String, Double> hasSeen = new TreeMap<String, Double>();
    /**
     * Finds the words under a node that are the heaviest
     * @param pointer the Node you start on
     * @param k The amount of matches you want to be returned
     * @return list of sorted strings from heaviest and down
     */
    public Iterable<String> topMatches(SNode pointer, int k, SNode top, boolean addedTop) {
        TreeMap<String, Double> sorted2 = sortByValues(hasSeen);
        System.out.println(pointer.letter);
        if (!addedTop) {
            addedTop = true;
            if (top.isWord) {
                hasSeen.put(top.word, top.weight); 
             }
         }
         if (pointer != null) {
            /*TreeMap<String, Double> sorted2 = sortByValues(hasSeen);*/
            if (pointer.isWord) {
                if (sortByValues(hasSeen).values().size() >= k) {
                    if (pointer.weight > (Double) sorted2.values().toArray()[k - 1]) {
                        hasSeen.put(pointer.word, pointer.weight);
                        hasSeen.remove(sorted2.keySet().toArray()[k - 1]);
                        
                    }
                } else {
                    hasSeen.put(pointer.word, pointer.weight);
                }
                
            }
            double templeft = 0;
            double tempmid = 0;
            double tempright = 0;
            
            MaxPQ<Double> searching = new MaxPQ<Double>();
            if (pointer.left != null) {
                templeft = pointer.left.maxWeight;
                searching.insert(templeft);
            }
            if (pointer.mid != null) {
                tempmid = pointer.mid.maxWeight;
                searching.insert(tempmid);
            }
            if (pointer.right != null) {
                tempright = pointer.right.maxWeight;
                searching.insert(tempright);
            }
            for (Double a : searching) {
                TreeMap<String, Double> sorted = sortByValues(hasSeen);
                if (sorted.keySet().toArray().length >= k) {
                    if (a > (Double) sorted.values().toArray()[k - 1]) {
                        if (templeft == a) {
                            topMatches(pointer.left, k, top, addedTop);
                        } else if (tempmid == a) {
                            topMatches(pointer.mid, k, top, addedTop);
                        } else if (tempright == a) {
                            topMatches(pointer.right, k, top, addedTop);
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (templeft == a) {
                        topMatches(pointer.left, k, top, addedTop);
                    } else if (tempmid == a) {
                        topMatches(pointer.mid, k, top, addedTop);
                    } else if (tempright == a) {
                        topMatches(pointer.right, k, top, addedTop);
                    }
                }
            }
                
        }
        return sortByValues(hasSeen).keySet();
    }
    
    /**
     * resets the map everytime you use the topmatches function
     */
    public void counterClear() {
        this.hasSeen = new TreeMap<String, Double>();
    }
    
}
