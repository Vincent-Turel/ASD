package solution.td2;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * A class to provide two implementations
 * of the stock span algorithm
 */
public class StockSpan {
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws EmptyStackException {
		int[] prices = {100,80,60,70,60,75,85,120};
		
		System.out.println(Arrays.toString(naive(prices)));
		System.out.println(Arrays.toString(smart(prices)));
	}
	
	/**
	 * Compute and return the span of stocks
	 * whose prices are stored in 'prices'
	 * Complexity: O(n²) where n = prices.length
	 */
	public static int[] naive(int[] prices) {
		int[] span = new int[prices.length];

		for ( int i = 0; i < prices.length; i++ ) {
			int j;
			for ( j = i; j >= 0 && prices[i] >= prices[j]; j-- );
			span[i] = i - j;
		}
		return span;
	}

	/**
	 * Compute and return the span of stocks
	 * whose prices are stored in 'prices'
	 * Complexity: O(n) where n = prices.length
	 */
	public static int[] smart(int[] prices) throws EmptyStackException {
		int[] span = new int[prices.length];
		span[0] = 1;
		
		ArrayStack<Integer> stack = new ArrayStack<>();
		stack.push(0);
		for ( int i = 1; i < prices.length; i++ ) {
			while ( ! stack.isEmpty() && prices[i] >= prices[stack.peek()] )
				stack.pop();
			span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
			stack.push(i);
		}
		return span;
	}
}
