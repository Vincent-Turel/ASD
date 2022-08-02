package solution.td2;


import td2.EmptyQueueException;
import td2.QueueInterface;

import java.util.EmptyStackException;

/**
 * A class for stack-based queue
 */
public class StackQueue<AnyType> implements QueueInterface<AnyType> {
	
	private ArrayStack<AnyType> in;
	private ArrayStack<AnyType> out;

	/**
	 * Build an empty queue
	 * Complexity: THETA(1)
	 */
	public StackQueue() {
		in  = new ArrayStack<AnyType>();
		out = new ArrayStack<AnyType>();
	}
	
	/**
	 * Return the number of elements
	 * currently in the queue
	 * Complexity: THETA(1)
	 */
	public int size() {
		return in.size() + out.size();
	}
	
	/**
	 * Return the next value to be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: amortized O(1)
	 */
	public AnyType peek() throws EmptyQueueException {
		try {
			if ( out.isEmpty() )
				transfer();
			return out.peek();
		}
		catch (EmptyStackException e) {
			throw new EmptyQueueException();
		}
	}
	
	/**
	 * Enqueue x in the queue
	 * Complexity: THETA(1)
	 */
	public void enqueue(AnyType x) {
		in.push(x);
	}
	
	/**
	 * Dequeue and return the next element to
	 * be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: amortized O(1)
	 */
	public AnyType dequeue() throws EmptyQueueException {
		try {
			if ( out.isEmpty() )
				transfer();
			return out.pop();
		}
		catch (EmptyStackException e) {
			throw new EmptyQueueException();
		}
	}
	
	/**
	 * Return a string representation of the queue
	 * in the form of "<- A B C <-" where A is the
	 * front and C the tail of the queue
	 * Complexity: THETA(n) where n is the current
	 * size of the queue
	 */	
	public String toString() {
		String s = "<- ";
		ArrayStack<AnyType> tmp = new ArrayStack<AnyType>();
		try {
			while ( ! out.isEmpty() ) {
				AnyType x = out.pop();
				s += x + " ";
				tmp.push(x);
			}
			while ( ! tmp.isEmpty() )
				out.push(tmp.pop());
			while ( ! in.isEmpty() )
				tmp.push(in.pop());
			while ( ! tmp.isEmpty() ) {
				AnyType x = tmp.pop();
				s += x + " ";
				in.push(x);
			}
		}
		catch (Exception e){}
		return s + "<-";
	}
	
	//////////////////////////////////////////////
	
	/**
	 * Transfer all the items from the 'in' stack
	 * to the 'out' stack
	 * Complexity: O(n) where n is the current
	 * size of the queue
	 */
	private void transfer() throws EmptyStackException {
		while (! in.isEmpty() )
			out.push(in.pop());
	}
}
















