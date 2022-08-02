package td1;

import java.util.EmptyStackException;

/**
 * An array-based stack class
 */
public class ArrayStack<AnyType> {

    private static final int DEFAULT_CAPACITY = 32;

    private AnyType[] array;
    private int size;

    /**
     * Build an empty stack
     * Complexity: THETA(1)
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Build an empty stack of
     * a given capacity
     * Complexity: THETA(1)
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        array = (AnyType[]) new Object[capacity];
        size = 0;
    }

    /**
     * Check if the stack is empty
     * Complexity: THETA(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the number of items
     * currently in the stack
     * Complexity: THETA(1)
     */
    public int size() {
        return size;
    }

    /**
     * Return the next value to be popped from the stack
     * Throw EmptyStackException if the stack is empty
     * Complexity: THETA(1)
     */
    public AnyType peek() throws EmptyStackException {
        if (isEmpty())
            throw new EmptyStackException();
        return array[size-1];
    }

    /**
     * Push the value x onto the stack.
     * If needed, the underlying array
     * will be enlarged by twice its size
     * Complexity: amortized O(1)
     */
    @SuppressWarnings("unchecked")
    public void push(AnyType x) {
        if (!isEmpty() && size % DEFAULT_CAPACITY == 0) {
            AnyType[] temp = (AnyType[]) new Object[size*2];
            for (int i = 0; i < size; i++){
                temp[i] = array[i];
            }
            array = temp;
        }
        array[size] = x;
        size++;
    }

    /**
     * Pop the stack and return the value popped
     * Throw EmptyStackException if the stack is empty
     * Complexity: THETA(1)
     */
    public AnyType pop() throws EmptyStackException {
        if ( isEmpty() )
            throw new EmptyStackException();
        size--;
        return array[size];
    }

    /**
     * Remove all elements in the stack
     * Complexity: THETA(1) where n is the
     * size of the stack
     */
    public void clear() {
        size = 0;
    }

    /**
     * Return a string representation of the stack
     * in the form of "[ A B C ->" where A is the
     * bottom and C the top of the stack
     */
    public String toString() {
        StringBuilder res = new StringBuilder("[ ");
        for (int i = 0; i < size; i++) {
            res.append(array[i].toString()).append(" ");
        }
        res.append("->");
        return res.toString();
    }
}