package td1;

import java.util.EmptyStackException;

/**
 * A class for stacks supporting the findMin operation in THETA(1)
 */
public class StackMin<AnyType extends Comparable<AnyType>> {

    ArrayStack<AnyType> arrayStack;
    ArrayStack<AnyType> min;

    /**
     * Build an empty stack
     * Complexity: THETA(1)
     */
    public StackMin() {
        arrayStack = new ArrayStack<>();
        min = new ArrayStack<>();
    }

    /**
     * Check if the stack is empty
     * Complexity: THETA(1)
     */
    public boolean isEmpty() {
        return arrayStack.isEmpty();
    }

    /**
     * Return the next value to be popped from the stack
     * Throw EmptyStackException if the stack is empty
     * Complexity: THETA(1)
     */
    public AnyType peek() throws EmptyStackException{
        return arrayStack.peek();
    }

    /**
     * Push the value x onto the stack.
     * If needed, the underlying array
     * will be enlarged by twice its size
     * Complexity: THETA(1)
     */
    public void push(AnyType x) {
        arrayStack.push(x);
        try {
            if (min.isEmpty() || x.compareTo(min.peek()) <= 0)
                min.push(x);
        } catch (EmptyStackException ignored) {}
    }

    /**
     * Pop the stack and return the value popped
     * Throw EmptyStackException if the stack is empty
     * Complexity: THETA(1)
     */
    public AnyType pop() throws EmptyStackException {
        if (arrayStack.peek().compareTo(min.peek()) == 0)
            min.pop();
        return arrayStack.pop();
    }

    /**
     * Return the minimum value currently in the stack
     * Throw EmptyStackException if the stack is empty
     * Complexity: THETA(1)
     */
    public AnyType findMin() throws EmptyStackException {
        return min.peek();
    }

    @Override
    public String toString() {
        return arrayStack.toString();
    }
}