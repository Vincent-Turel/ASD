package td2;

import java.io.*;
import java.util.*;

/**
 * A class to find pairs (x,y) of integers inside an increasing
 * sequence matching y = x + n for a given n.
 */
public class Pairing {
	
	/**
	 * Display all the pairs (x,y), x and y  in the input, such that y = x + n
	 * The input contains an entirely increasing (strict) sequence of integers
	 * Running time complexity: THETA(S) where S is the size of the input
	 * Extra memory usage: O(n)
	 */
	public static void showPairs(int n, Scanner input) throws EmptyQueueException {
		ListQueue<Integer> first = new ListQueue<>();
		ListQueue<Integer> second;
		ListQueue<Integer> save = new ListQueue<>();
		System.out.println("Show pairs : ");

		while (input.hasNextInt()){
			var nb = input.nextInt();
			first.enqueue(nb);
			save.enqueue(nb);
		}

		while (!first.isEmpty()) {
			second = save;
			while (!second.isEmpty() && first.peek() >= second.peek() + n) {
				if (first.peek() == second.peek() + n) {
					System.out.print(" (" + second.peek() + "," + first.peek() + "), ");
				}
				second.dequeue();
			}
			first.dequeue();
		}
	}
	
    /**
     * A short main for quick testing
     */
	public static void main(String[] args) throws FileNotFoundException, EmptyQueueException {
		// put the right path here
		String filepath = ".\\src\\td2\\big-file.txt";
		Scanner input = new Scanner(new File(filepath));
		showPairs(1273, input);
		input.close();
	}
}
