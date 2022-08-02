package td6;

/**
 * A class for the quicksort algorithm
 */
public class QuickSort {
	
	private static final int CUTOFF = 10;
	
	/**
	 * Sort the array in place using the quicksort algorithm
	 */
	public static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array) {
		sort(array,0,array.length-1);
	}

	/**
	 * Sort the portion array[lo,hi] in place using the quicksort algorithm
	 */	
	private static <AnyType extends Comparable<AnyType>> void sort(AnyType[] array, int lo, int hi) {
		if (hi-lo < CUTOFF) {
			insertion(array, lo, hi);
		} else {
			int pivot = partition(array, lo, hi);
			sort(array, lo, pivot-1);
			sort(array, pivot+1, hi);
		}
	}

	/**
	 * Partition the portion array[lo,hi] and return the index of the pivot
	 */
	private static <AnyType extends Comparable<AnyType>> int partition(AnyType[] array, int lo, int hi) {
		int pivot = median(array, lo, (lo+hi)/2, hi);
		swap(array, pivot, lo);
		int indexMax = lo;
		for (int i = lo+1; i<=hi;i++) {
			if (array[i].compareTo(array[lo]) < 0) {
				swap(array, i, indexMax + 1);
				indexMax++;
			}
		}
		swap(array, lo, indexMax);
		return indexMax;
	}

	/**
	 * Return the index of the median of { array[lo], array[mid], array[hi] }
	 */
	private static <AnyType extends Comparable<AnyType>> int median(AnyType[] array, int lo, int mid, int hi) {
		if (array[lo].compareTo(array[mid]) < 0 && array[lo].compareTo(array[hi]) > 0 ||
				array[lo].compareTo(array[hi]) < 0 && array[lo].compareTo(array[mid]) > 0)
			return lo;
		if (array[hi].compareTo(array[lo]) < 0 && array[hi].compareTo(array[mid]) > 0 ||
				array[hi].compareTo(array[mid]) < 0 && array[hi].compareTo(array[lo]) > 0)
			return hi;
		return mid;
	}
	
	/**
	 * Sort array[lo, hi] in place using the insertion sort algorithm
	 */
	private static <AnyType extends Comparable<AnyType>> void insertion(AnyType[] array, int lo, int hi) {
		for (int i = lo; i<=hi; i++) {
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
}
