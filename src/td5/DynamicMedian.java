package td5;

import java.util.Comparator;

/**
 * A class for dynamic median finding.
 */
public class DynamicMedian<AnyType extends Comparable<? super AnyType>> {

	private BinaryHeap<AnyType> less;    // to store values less than or equal to the median
	private BinaryHeap<AnyType> greater; // to store values greater than or equal to the median

	/**
	 * Build a DynamicMedian object of maximum capacity n
	 */
	public DynamicMedian(int n) {
		Comparator<AnyType> c = new Comparator<AnyType>() {
			public int compare(AnyType e1, AnyType e2) {
				return e2.compareTo(e1);
			}
		};
		less = new BinaryHeap<>(n);
		greater = new BinaryHeap<>(n,c);
	}

	/**
	 * Return the size (the number of elements
	 * currently in the DynamicMedian object)
	 * Complexity: THETA(1)
	 */
	public int size() {
		return less.size() + greater.size();
	}

	/**
	 * Add a new element e.
	 * Complexity: O(log(size))
	 */
	public void add(AnyType e) throws EmptyHeapException, FullHeapException {
		if (size() == 0) {
			less.add(e);
		}
		else if (less.size() > greater.size()) {
			greater.add(e);
		} else {
			less.add(greater.deleteExtreme());
			greater.add(e);
		}
	}

	/**
	 * Return the current median.
	 * Complexity: THETA(1)
	 */
	public AnyType findMedian() throws EmptyHeapException {
		return less.extreme();
	}

	/**
	 * Return and delete the current median
	 * Complexity: O(log(size))
	 */
	public AnyType deleteMedian() throws EmptyHeapException, FullHeapException {
		if (less.isEmpty())
			throw new EmptyHeapException();
		AnyType res = less.deleteExtreme();
		if (greater.size() > less.size())
			less.add(greater.deleteExtreme());
		return res;
	}
}
