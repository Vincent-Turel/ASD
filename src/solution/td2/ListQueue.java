package solution.td2;

import td2.EmptyQueueException;
import td2.QueueInterface;

/**
 * A class for list-based queue
 */
public class ListQueue<AnyType> implements QueueInterface<AnyType> {
	
	private ListNode head;
	private ListNode tail;
	private int size;

	/**
	 * Build an empty queue
	 * Complexity: THETA(1)
	 */
	public ListQueue() {
		head = tail = null;
		size = 0;
	}
		
	/**
	 * Return the number of elements
	 * currently in the queue
	 * Complexity: THETA(1)
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Return the next value to be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: THETA(1)
	 */
	public AnyType peek() throws EmptyQueueException {
		if ( isEmpty() )
			throw new EmptyQueueException();
		return head.data;
	}
	
	/**
	 * Enqueue x in the queue
	 * Complexity: THETA(1)
	 */
	public void enqueue(AnyType x) {
		if ( head == null ) {
			head = new ListNode(x);
			tail = head;
		}
		else {
			tail.next = new ListNode(x);
			tail = tail.next;
		}
		size++;
	}
	
	/**
	 * Dequeue and return the next element to
	 * be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: THETA(1)
	 */
	public AnyType dequeue() throws EmptyQueueException {
		if ( isEmpty() )
			throw new EmptyQueueException();
		AnyType tmp = head.data;
		head = head.next;
		if ( head == null )
			tail = null;
		size--;
		return tmp;
	}
	
	/**
	 * Return a string representation of the queue
	 * in the form of "<- A B C <-" where A is the
	 * front and C the tail of the queue
	 * Complexity: THETA(n) where n is the number
	 * of items currently in the queue
	 */	
	public String toString() {
		String s = "<- ";
		ListNode tmp = head;
		while ( tmp != null ) {
			s += tmp.data + " ";
			tmp = tmp.next;
		}
		return s + "<-";
	}
	
	//////////////////////////////////////////////
	
	/**
	 * A private class for list node
	 */
	private class ListNode {
		AnyType data;
		ListNode next;
		
		ListNode(AnyType data) {
			this.data = data;
			this.next = null;
		}
	}
}