package td2;

import td1.StackMin;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

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
    // expected output
    //
    // [1, 1, 1, 2, 1, 4, 6, 8]
    // [1, 1, 1, 2, 1, 4, 6, 8]

    /**
     * Compute and return the span of stocks
     * whose prices are stored in 'prices'
     * Complexity: O(n²) where n = prices.length
     */
    public static int[] naive(int[] prices) {
        int[] span = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            span[i] = 1;
            for (int j = i ; j>=0; j--) {
                if (prices[j] < prices[i])
                    span[i]++;
            }
        }
        return span;
    }

    /**
     * Compute and return the span of stocks
     * whose prices are stored in 'prices'
     * Complexity: O(n) where n = prices.length
     */
    public static int[] smart(int[] prices) throws EmptyStackException {
        int[] res = new int[prices.length];
        Stack<Integer> st = new Stack<>();
        st.push(0);

        res[0] = 1;

        for (int i = 1; i < prices.length; i++) {

            // Pop elements from stack while stack is not
            // empty and top of stack is smaller than
            // price[i]
            while (!st.empty() && prices[st.peek()] <= prices[i])
                st.pop();

            // If stack becomes empty, then price[i] is
            // greater than all elements on left of it, i.e.,
            // price[0], price[1], ..price[i-1]. Else price[i]
            // is greater than elements after top of stack
            res[i] = (st.empty()) ? (i + 1) : (i - st.peek());

            // Push this element to stack
            st.push(i);
        }
        return res;
    }
}