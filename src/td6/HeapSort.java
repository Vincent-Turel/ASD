package td6;

import solution.td5.BinaryHeap;
import td5.EmptyHeapException;

import java.util.Arrays;

/**
 * A class for the heap sort algorithm.
 */
public class HeapSort {
	
	/**
	 * Sort the array in place using the heapsort algorithm
	 * Complexity: THETA( n.log(n) ) where n is the size of the array
	 */	
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		BinaryHeap<AnyType> heap = new BinaryHeap<>(array);
		int n = array.length;
		for (int i = n-1; i >=0;i--) { // i = n; i >= 0
			try {
				array[i] = heap.deleteExtreme();
			} catch (EmptyHeapException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Integer[] tab = new Integer[] {30, 7, 14, 29, 10};
		sort(tab);
		System.out.println(Arrays.toString(tab));
	}
}
