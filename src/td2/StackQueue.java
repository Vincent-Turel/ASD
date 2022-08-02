package td2;


import java.util.Stack;

/**
 * A class for stack-based queue
 */
public class StackQueue<AnyType> implements QueueInterface<AnyType> {
	
	Stack<AnyType> stack1;
	Stack<AnyType> stack2;

	/**
	 * Build an empty queue
	 * Complexity: THETA(1)
	 */
	public StackQueue() {
		stack1 = new Stack<>();
		stack2 = new Stack<>();
	}
	
	/**
	 * Return the number of elements
	 * currently in the queue
	 * Complexity: THETA(1)
	 */
	public int size() {
		return stack1.size() + stack2.size();
	}
	
	/**
	 * Return the next value to be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: amortized O(1)
	 */
	public AnyType peek() throws EmptyQueueException {
        if (stack1.size() == 0 && stack2.size() == 0)
        	throw new EmptyQueueException();

        if (stack2.size() == 0) {
        	while (stack1.size() != 0) {
        		stack2.push(stack1.pop());
			}
		}
        return stack2.peek();
	}
	
	/**
	 * Enqueue x in the queue
	 * Complexity: THETA(1)
	 */
	public void enqueue(AnyType x) {
		stack1.push(x);
	}
	
	/**
	 * Dequeue and return the next element to
	 * be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: amortized O(1)
	 */
	public AnyType dequeue() throws EmptyQueueException {
		if (stack1.size() == 0 && stack2.size() == 0)
			throw new EmptyQueueException();

		if (stack2.isEmpty()) {
			while (stack1.size() != 0) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
	
	/**
	 * Return a string representation of the queue
	 * in the form of "<- A B C <-" where A is the
	 * front and C the tail of the queue
	 * Complexity: THETA(n) where n is the current
	 * size of the queue
	 */	
	public String toString() {
		StringBuilder res = new StringBuilder("<-");
		Object[] values = stack2.toArray();
		for (int i = values.length - 1; i >= 0; i--) {
			res.append(" ").append(values[i]);
		}
		values = stack1.toArray();
		for (int i = 0; i < values.length; i++) {
			res.append(" ").append(values[i]);
		}
		res.append(" <-");
		return res.toString();
	}
}



