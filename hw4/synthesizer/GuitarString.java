package synthesizer;

public class GuitarString {    
   private static final int SR = 44100;      // Sampling Rate
   private static final double DECAY = .996; // energy decay factor
   
   private BoundedQueue buffer;
   
   public GuitarString(double frequency) {
       buffer = new ArrayRingBuffer((int) Math.round(SR/frequency));
	   while (!buffer.isFull()) {
		   buffer.enqueue(0.0);
	   }
	   // TODO: Create a buffer with capacity = SR / frequency. You'll need to
       //       cast the result of this divsion operation into an int. For better
       //       accuracy, use the Math.round() function before casting.
       //       Your buffer should be initially filled with zeros.
   }
   
   public void pluck() {
       while (!buffer.isEmpty()) {
    	   buffer.dequeue();
       }
       
       double rando;
       
       while (!buffer.isFull()) {
    	   rando = Math.random() - 0.5;
    	   buffer.enqueue(rando);
       }
	   // TODO: Dequeue everything in the buffer, and replace it with random numbers
       //       between -0.5 and 0.5. You can get such a number by using:
       //       double r = Math.random() - 0.5;
       //
       //       Make sure that your random numbers are different from each other.
   }
   
   public void tic() {
       double num;
       num = buffer.dequeue();
       buffer.enqueue(((num + buffer.peek())/2)*DECAY);
	   
	   // TODO: Dequeue the front sample and enqueue a new sample that is
       //       the average of the two multiplied by the DECAY factor.
       //       Do not call StdAudio.play().
   }
   
   public double sample() {
       return 0;
   }
}
