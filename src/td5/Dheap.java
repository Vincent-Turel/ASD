package td5;

import java.util.*;
/**
 * A class for binary heap implementation
 */
public class Dheap<AnyType extends Comparable<? super AnyType>> {
    private final AnyType[] A;
    private final int nbChild;// to store the heap
    private int size;    // the number of elements in the heap
    // comparator to choose
    private Comparator<AnyType> c = new Comparator<AnyType>() {
        public int compare(AnyType e1, AnyType e2) {
            return -e1.compareTo(e2);
        }
    };
    ///////////// Constructors
    /**
     * Build a heap of capacity n.
     * The elements are ordered according to the
     * natural order on AnyType.
     * The heap is empty.
     * Complexity: THETA(1)
     */
    @SuppressWarnings("unchecked")
    public Dheap(int n, int d) {
        A = (AnyType[]) new Comparable[n];
        size = 0;
        this.nbChild = d;
    }
    /**
     * Build a heap of capacity n.
     * The elements are ordered according to c.
     * The heap is empty.
     * Complexity: THETA(1)
     */
    @SuppressWarnings("unchecked")
    public Dheap(int n, int d, Comparator<AnyType> c) {
        this.A = (AnyType[]) new Comparable[n];
        this.size = 0;
        this.c = c;
        nbChild = d;
    }
    /**
     * Build a heap based on array A.
     * The elements are ordered according to the
     * natural order on AnyType.
     * The heap is full
     */
    public Dheap(AnyType[] A, int d) {
        this.A = A;
        this.size = A.length;
        this.nbChild = d;
        buildDheap();
    }
    /**
     * Build a heap based on array A.
     * The elements are ordered according to c.
     * The heap is full
     */
    public Dheap(AnyType[] A, int d, Comparator<AnyType> c) {
        this.A = A;
        this.size = A.length;
        this.c = c;
        this.nbChild = d;
        buildDheap();
    }
    ///////////// Private methods
    /**
     * Swap values in the array
     * at indexes i and j.
     * Complexity: THETA(1)
     */
    private void swap(int i, int j) {
        AnyType tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    /**
     * Return the number of the left
     * node of node number n.
     * Complexity: THETA(1)
     */
    private int left(int n) {
        return nbChild*n + 1;
    }

    private int dChild(int n, int d) {
        return nbChild*n + d;
    }
    /**
     * Return the number of the right
     * node of node number n.
     * Complexity: THETA(1)
     */
    private int right(int n) {
        return nbChild*(n + 1);
    }
    /**
     * Return the number of the parent
     * node of node number n.
     * Complexity: THETA(1)
     */
    private int parent(int n) {
        return (n - 1)/nbChild;
    }
    /**
     * Percolate down the element à node number n
     * Complexity: O(log(size))
     */
    private void percolateDown(int n) {
        int k = n;
        for (int i = 1; i<=nbChild; i++) {
            int g = dChild(k, i);
            if ( g < size && c.compare(A[g],A[k]) > 0 )
                k = g;
        }
        if ( k != n ) {
            swap(k,n);
            percolateDown(k);
        }
    }
    /**
     * Percolate up the element à node number n
     * Complexity: O(log(size))
     */
    private void percolateUp(int n) {
        AnyType e = A[n];
        while ( n > 0 && c.compare(e,A[parent(n)]) > 0 ) {
            A[n] = A[parent(n)];
            n = parent(n);
        }
        A[n] = e;
    }
    /**
     * Arrange the elements in A such
     * that it has the heap property.
     * Complexity: O(size)
     */
    private void buildDheap() {
        for ( int i = parent(size - 1); i >= 0; i-- ) {
            percolateDown(i);
        }
    }
    ///////////// Public methods
    /**
     * Return the size of the heap
     * (the number of elements in the heap).
     * Complexity: THETA(1)
     */
    public int size() {
        return size;
    }
    /**
     * Check if the heap is empty.
     * Complexity: THETA(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Return the extreme element.
     * Complexity: THETA(1)
     */
    public AnyType extreme() throws EmptyHeapException {
        if ( size == 0 )
            throw new EmptyHeapException();
        return A[0];
    }
    /**
     * Return and delete the extreme element.
     * Complexity: O(log(size))
     */
    public AnyType deleteExtreme() throws EmptyHeapException {
        if ( size == 0 )
            throw new EmptyHeapException();
        AnyType extreme = A[0];
        A[0] = A[--size];
        if ( size > 0 )
            percolateDown(0);
        return extreme;
    }
    /**
     * Add a new element in the heap
     * Complexity: O(log(size))
     */
    public void add(AnyType e) throws FullHeapException {
        if ( size == A.length )
            throw new FullHeapException();
        A[size++] = e;
        percolateUp(size-1);
    }
    ///////////// Part 3: deleting in the heap
    /**
     * Delete the element e from the heap.
     * Complexity: O(size)
     */
    public void delete(AnyType e) {
        for ( int i = 0; i < size; i++ ) {
            if (A[i].compareTo(e) == 0) {
                A[i] = A[--size];
                percolateUp(i);
                percolateDown(i);
            }
        }
    }
    /**
     * Delete all the elements e from the heap.
     * Complexity: O(size)
     */
    public void deleteAll(AnyType e) {
        int i = 0;
        while ( i < size ) {
            if (A[i].compareTo(e) == 0)
                swap(i, --size);
            else i++;
        }
        buildDheap();
    }

    //toStringAsTab
    public String toStringAsTab() {
        return Arrays.toString(Arrays.copyOf(A, size)).replaceAll("(, |)null","");
    }

    private static List<Integer> read(String inputString) {
        List<Integer> list = new LinkedList<Integer>();
        Scanner input = new Scanner(inputString).useDelimiter(",\\s*");
        while ( input.hasNextInt() )
            list.add(input.nextInt());
        input.close();
        return list;
    }
}