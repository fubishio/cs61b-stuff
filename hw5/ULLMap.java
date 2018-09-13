import java.util.Iterator;
import java.util.Set; /* java.util.Set needed only for challenge problem. */

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Supports get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. 
 *
 *  For simplicity, you may assume that nobody ever inserts a null key or value
 *  into your map.
 */ 
public class ULLMap <Key, Value> implements Map61B<Key, Value>, Iterable<Key>{ //FIX ME
    /** Keys and values are stored in a linked list of Entry objects.
      * This variable stores the first pair in this linked list. You may
      * point this at a sentinel node, or use it as a the actual front item
      * of the linked list. 
      */
   /* private Entry front;
    private Entry next;
        
    public ULLMap(Entry front, Entry next) {
    	this.next = next;
    	this.front = front; 
    }*/
    
	private Entry front;
	private int length;
	
	public ULLMap() {
		front = new Entry(null, null, null);
		length = 0;
	}
	
	public ULLMap (Key key, Value val) {
		front = new Entry(key, val, null);
		front = new Entry(null, null, front);
		length = 1;
	}
	
    @Override
    public Value get(Key key) { //FIX ME
    	if (front.get(key) != null) {
    		return front.get(key).val;
    	} else {
    		return null;
    	}
    	
    	/*Entry x = front;
        if (key.equals(x.key)) {
          	return x.val;
        }
        x = x.next;*/
       /* return null;*/
    }

    @Override
    public void put(Key key, Value val) {
    	Entry x = front;
    	if (length == 0) { 
    		front.next = new Entry(key, val, null);
    		length = length + 1;
    		return;
    	}
    	front = front.next; // for personal test, cannot call x.key on sentinel node so list > 1 wont work without this
    	for (x = front; x != null; x = x.next){
    		if (x.key.equals(key)) {
    			x.val = val;
    			return;
    		} else if (x.next == null) {
    			x.next = new Entry(key, val, null);
    			length = length + 1;
    		}
    	}
    	/*if (containsKey(key)){
    		while (x!=null) {
	    		if (key.equals(x.key)) {
	    			x.val = val;
	    			length = length + 1;
	    			return;
	    		}
	    		x = x.next;
	    	}
    	} else {
    		front = new Entry(key, val, front);
    		length = length + 1;
    		return;
    	}*/
    }

    @Override
    public boolean containsKey(Key key) { //FIX ME
    	Entry x = front;
    	if (x.get(key) == null) {
    		return false;
    	} else {
    		return true;
    	}
    }

    @Override
    public int size() {
        return length; 
    }

    @Override
    public void clear() {
    	front = new Entry(null, null, null);
    	length = 0;
    }

    public static <Value, Key> ULLMap<Value, Key> invert(ULLMap<Key,Value> x){ //need to define the switching of generic type
    	ULLMap<Value, Key> retMap = new ULLMap<Value, Key>();
    	for (Key xKey: x){							//*** Syntax for for loop that iterates over data 
    		retMap.put(x.get(xKey), xKey);			//structures is from 
    	}											//http://www.tutorialspoint.com/java/java_loop_control.htm
    	return retMap;
    }
    
    
    
    
    

    /** Represents one node in the linked list that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
    
        /** Stores KEY as the key in this key-value pair, VAL as the value, and
         *  NEXT as the next node in the linked list. */
        public Entry(Key k, Value v, Entry n) { //FIX ME
            key = k;
            val = v;
            next = n;
        }

        /** Returns the Entry in this linked list of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        public Entry get(Key key) { //FIX ME
        	Entry x = this;
        	while (x != null){
        		if(key != null && x!= null && x.key != null) {
	        		if (key.equals(x.key) ) {
	                	return x;
	                }
        	}
                x = x.next;
            }
            return null;
        }

        /** Stores the key of the key-value pair of this node in the list. */
        private Key key; //FIX ME
        /** Stores the value of the key-value pair of this node in the list. */
        private Value val; //FIX ME
        /** Stores the next Entry in the linked list. */
        private Entry next;
    
    }

    /* Methods below are all challenge problems. Will not be graded in any way. 
     * Autograder will not test these. */
    @Override
    public Value remove(Key key) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    @Override
    public Value remove(Key key, Value value) { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }
    
    @Override
    public Set<Key> keySet() { //FIX ME SO I COMPILE
        throw new UnsupportedOperationException();
    }

    
    
    @Override
    public Iterator<Key> iterator() {
    	return new ULLMapIter();
    }
    
    
    
    private class ULLMapIter implements Iterator<Key> {
    	private Entry front1;
    	
    	public ULLMapIter() {
    		if (length==0) {
    			front1 = null;
    		} else {
    			front1 = front.next;
    		}
    	}
    	
    	@Override
    	public boolean hasNext() {
    		if (front1 == null) {
    			return false;
    		} else {
    			return true;
    		}
    	}
    	
    	@Override
    	public Key next() {
    		if (front1 == null) {
    			return null;
    		} else {
    			Key nexvalue = front1.key;
    			front1 = front1.next;
    			return nexvalue;
    		}
    	}
    	
    	@Override
    	public void remove() {
    		throw new UnsupportedOperationException();
    	}
    }
    
    
    
    

}