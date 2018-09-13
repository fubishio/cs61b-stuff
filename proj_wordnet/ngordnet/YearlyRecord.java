package ngordnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;


/*idea of conditions for updating adapted from Adapted 
 * from Professor Josh Hugs' YearlyRecordWeird.java  */
public class YearlyRecord {
    private TreeMap<String, Integer> yR;
    private TreeMap<String, Integer> yRSorted;
    private List<String> yRSortedList = new ArrayList<String>();
    private TreeMap<String, Integer> rankRef = new TreeMap<String, Integer>();
    
    public YearlyRecord() {
        yR = new TreeMap<String, Integer>();
    }
    
    public YearlyRecord(java.util.HashMap<java.lang.String, java.lang.Integer> otherCountMap) {
        yR = new TreeMap<String, Integer>();
        for (String x : otherCountMap.keySet()) {
            yR.put(x, otherCountMap.get(x));
        }
    }
    
    /*Adapted from Professor Josh Hugs' YearlyRecordWeird.java file zcomparator method */
    private class CountComparator implements Comparator<String> {
        public int compare(String x, String y) {
            return count(x) - count(y);
        }
    }
    
    /*Adapted from Professor Josh Hugs' YearlyRecordWeird.java file fewerZ's method */
    private void updateMap() {
        yRSorted = new TreeMap<String, Integer>();
        yRSortedList.clear();
        String[] words = new String[yR.size()];
        int c = 0;
        for (String word : yR.keySet()) {
            words[c] = word;
            c += 1;
        }

        Arrays.sort(words, new CountComparator());
        rankRef = new TreeMap<String, Integer>();
        int i = 0;
        for (String a : words) {
            rankRef.put(a, words.length - i);
            i = i + 1;
        }
        for (String x : words) {
            yRSortedList.add(x);
            yRSorted.put(x, yR.get(x));
        }
        yR = yRSorted;

    }
    
    public int count(java.lang.String word) {
        return yR.get(word);
    }
    
    public Collection<Number> counts() {
        Collection<Number> retCol = new ArrayList<Number>();
        if (yRSortedList.isEmpty()) {
            updateMap();
        }
        for (String x : yRSortedList) {
            retCol.add(yR.get(x));
        }
        /*for (Integer x : yR.values()) {
            retCol.add(x);
        }*/
        return retCol;
    }
    
    public void put(java.lang.String word, int count) {
        yR.put(word, count);
        yRSortedList.clear();
        rankRef.clear();
    }
    
    public int rank(java.lang.String word) {
        if (rankRef.isEmpty()) {
            updateMap();
        }
        return rankRef.get(word);
    }
    
    public int size() {
        return yR.keySet().size();
    }
    
    public java.util.Collection<java.lang.String> words() {
        Collection<String> retCol = new ArrayList<String>();
        if (yRSortedList.isEmpty()) {
            updateMap();
        }
        for (String x : yRSortedList) {
            retCol.add(x);
        }
        return retCol;
    }
    
    
    
}
