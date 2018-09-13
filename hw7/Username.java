<<<<<<< HEAD
import java.util.Random;

=======
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97

    // Instance Variables (remember, they should be private!)
<<<<<<< HEAD
    private String usernam;
    private String strCons = "0123456789abcdefghijklmnopqrstuvwxyz";
	private Random rnd = new Random();
	/*http://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html*/	
	public Username() {
    	int len;
		if (rnd.nextInt(1) == 0) {
    		len = 2;
    	} else {
    		len = 3;
    	}
		StringBuilder str = new StringBuilder(len);
    	for (int i = 0; i < len; i = i + 1) {
    		str.append(strCons.charAt(rnd.nextInt(36)));
    	}
    	this.usernam = str.toString();
    }

    public Username(String reqName) {
        char[] charCheck = reqName.toCharArray();
        for (int i = 0; i < charCheck.length; i = i + 1) {
        	for (int j = 0; j < 36; j = j + 1) {
        		if (charCheck[i] == (strCons.toCharArray()[j])) {
        			throw new IllegalArgumentException();
        		}
        	}
        }
        if (reqName.length() > 3 || reqName.length() < 2) {
        	throw new IllegalArgumentException();
        }
        usernam = reqName;
=======
    // YOUR CODE HERE

    public Username() {
        // YOUR CODE HERE
    }

    public Username(String reqName) {
        // YOUR CODE HERE
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
    }

    @Override
    public boolean equals(Object o) {
<<<<<<< HEAD
        if (o instanceof Username) {
        	if (((Username) o).usernam.toLowerCase() != this.usernam.toLowerCase()) {
            	return false;
            }
            return true;
        }
        return false;
    }

    /*https://computinglife.wordpress.com/2008/11/20/why-do-hash-functions-use-prime-numbers*/    
    @Override
    public int hashCode() { 
        if (usernam.isEmpty()) {
        	return 0;
        } else {
	    	String usernam1 = usernam.toLowerCase();
        	char[] strArray = usernam1.toCharArray();
	    	int hash = 0;
	        for (int i = 0; i < usernam1.length(); i = i + 1) {
	        	hash = 31 * hash + strArray[i];
	        }
	        return hash;
        }
=======
        // YOUR CODE HERE
        return false;
    }

    @Override
    public int hashCode() { 
        // YOUR CODE HERE
        return 0;
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
    }

    public static void main(String[] args) {
        // You can put some simple testing here.
    }
}