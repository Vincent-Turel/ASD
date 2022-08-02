package td6;

import java.util.Arrays;

/**
 * A class for simple sorting methods
 */
public class SimpleSorting {

	/**
	 * Sort the array in place using the selection sort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void selection(AnyType[] array) {
		int n = array.length;
		for (int i=0;i<3;i++) {// < n
			int smallest = i;
			for (int j = i+1; j<n;j++) {
				if (array[smallest].compareTo(array[j]) > 0) {
					smallest = j;
				}
			}
			swap(array, i, smallest);
		}
	}
	
	/**
	 * Sort the array in place using the insertion sort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array) {
		int n = array.length;
		for (int i = 1; i<5; i++) { // < n
			for (int j = i; j>0; j--) {
				if (array[j-1].compareTo(array[j]) > 0) {
					swap(array, j, j-1);
				} else {
					break;
				}
			}
		}
	}
	
	/**
	 * Swap array[i] and array[j]
	 */
	private static <AnyType> void swap(AnyType[] array, int i, int j) {
		AnyType tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void main(String[] args) {
		Integer[] tab = new Integer[] {4, 17, 90, 65, 28, 12, 18};
		selection(tab);
		System.out.println(Arrays.toString(tab));
	}
}
