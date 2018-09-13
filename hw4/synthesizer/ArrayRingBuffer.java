package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
	private int first; 
	private int last;
	private double[] rb;
	public ArrayRingBuffer(int capacity) {
	    rb = new double[capacity];
	    first = 0;
	    last = 0;
	    this.fillCount = 0;
	    this.capacity = capacity;
	}
	public void enqueue(double x) {
	    if (this.isFull()) {
	    	throw new RuntimeException("Ring buffer overflow");
	    }
	    rb[last] = x;
	    last = last + 1;
	    this.fillCount = this.fillCount + 1;
	    if (last == capacity) {
	    	last = 0;
	    }
	    
	  }
	public double dequeue() {
	    if (this.isEmpty()) {
	    	throw new RuntimeException("Ring buffer underflow");
	    }
	    double value = rb[first];
	    first = first + 1;
	    this.fillCount = this.fillCount - 1;
	    if (first == capacity) {
	    	first = 0;
	    }
	    return value;
	}
	
	public double peek() {
		if (this.isEmpty()) {
			throw new RuntimeException("Ring buffer underflow");
		} else {
		return rb[first];
		}

	}
}