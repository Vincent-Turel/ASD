package td2;

/**
 * A class for list-based queue
 */
public class ListQueue<AnyType> implements QueueInterface<AnyType> {
	
	ListNode tale;
	ListNode head;
	int size;

	/**
	 * Build an empty queue
	 * Complexity: THETA(1)
	 */
	public ListQueue() {
		this.tale = null;
		this.head = null;
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
		if (head.data == null) {
			throw new EmptyQueueException();
		}
		return head.data;
	}
	
	/**
	 * Enqueue x in the queue
	 * Complexity: THETA(1)
	 */
	public void enqueue(AnyType x) {
		ListNode newNode = new ListNode(x);
		newNode.next = null;
		if (size == 0) {
			head = newNode;
		}
		else {
			tale.next = newNode;
		}
		tale = newNode;
		size++;
	}
	
	/**
	 * Dequeue and return the next element to
	 * be dequeued
	 * If the queue is empty throws EmptyQueueException
	 * Complexity: THETA(1)
	 */
	public AnyType dequeue() throws EmptyQueueException {
		if (head == null) {
			throw new EmptyQueueException();
		}
		AnyType data = head.data;
		head = head.next;
		size--;
		return data;
	}
	
	/**
	 * Return a string representation of the queue
	 * in the form of "<- A B C <-" where A is the
	 * front and C the tail of the queue
	 * Complexity: THETA(n) where n is the number
	 * of items currently in the queue
	 */	
	public String toString() {
		StringBuilder res = new StringBuilder("<-");
		ListNode first = this.head;
		while (first != null) {
			res.append(" ").append(first.data.toString());
			first = first.next;
		}
		res.append(" <-");
		return res.toString();
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
