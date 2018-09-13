import java.util.HashMap; // Import Java's HashMap so we can use it

<<<<<<< HEAD

=======
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
public class FibonacciMemo {

    /**
     * The classic recursive implementation with no memoization. Don't care
     * about graceful error catching, we're only interested in performance.
     * 
     * @param n
     * @return The nth fibonacci number
     */
    public static int fibNoMemo(int n) {
        if (n <= 1) {
            return n;
        }
        return fibNoMemo(n - 2) + fibNoMemo(n - 1);
    }

    /**
     * Your optimized recursive implementation with memoization. 
     * You may assume that n is non-negative.
     * 
     * @param n
     * @return The nth fibonacci number
     */
<<<<<<< HEAD
    static HashMap<Integer, Integer> memoized = new HashMap<Integer, Integer>();
    public static int fibMemo(int n) {
        if (n < 1) {
        	return 0;
        }
        if (n == 1) {
        	return 1;
        }
        if (memoized.containsKey(n) == false) {
        	memoized.put(n, fibMemo(n - 1) + (fibMemo(n - 2)));
        }
        return memoized.get(n);
=======
    public static int fibMemo(int n) {
        // YOUR CODE HERE
        return 0;
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
    }

    /**
     * Answer the following question as a returned String in this method:
     * Why does even a correctly implemented fibMemo not return 2,971,215,073
     * as the 47th Fibonacci number?
     */
    public static String why47() {
        String answer = "potatoes";
<<<<<<< HEAD
        answer += ", " + "the value is an int so it rolls over when it reaches the max value, basically an overflow in integer data type" + " and tapioca";
=======
        answer += ", " + answer + " and tapioca";
>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
        return answer;
    }

    public static void main(String[] args) {
        // Optional testing here        
        String m = "Fibonacci's real name was Leonardo Pisano Bigollo.";
        m += "\n" + "He was the son of a wealthy merchant.\n";
        System.out.println(m);
        System.out.println("0: " + FibonacciMemo.fibMemo(0));
        System.out.println("1: " + FibonacciMemo.fibNoMemo(1));
        System.out.println("2: " + FibonacciMemo.fibNoMemo(2));
        System.out.println("3: " + FibonacciMemo.fibNoMemo(3));
        System.out.println("4: " + FibonacciMemo.fibNoMemo(4));
<<<<<<< HEAD
        System.out.println("0: " + FibonacciMemo.fibMemo(0));
        System.out.println("1: " + FibonacciMemo.fibMemo(1));
        System.out.println("2: " + FibonacciMemo.fibMemo(2));
        System.out.println("3: " + FibonacciMemo.fibMemo(3));
        System.out.println("4: " + FibonacciMemo.fibMemo(4));
        System.out.println("5: " + FibonacciMemo.fibMemo(5));
        System.out.println("6: " + FibonacciMemo.fibMemo(6));
        System.out.println("7: " + FibonacciMemo.fibMemo(7));
        System.out.println("8: " + FibonacciMemo.fibMemo(8));
        System.out.println("46: " + FibonacciMemo.fibMemo(46));
        System.out.println("47: " + FibonacciMemo.fibMemo(47));
=======

>>>>>>> 8f6f408a5fd4236ae5ef3fa608c34848a757227e
        // 46th Fibonacci = 1,836,311,903
        // 47th Fibonacci = 2,971,215,073
    }
}
