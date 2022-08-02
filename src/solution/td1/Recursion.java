package solution.td1;

/**
 * A class for practicing recursion
 */
public class Recursion {
	
	///// printing binary words

    /**
     * Print out all binary words of length k
     * Complexity: THETAT(2^k)
     */
    public static void binary(int k) {
        binary("",k);
    }

    /**
     * Print out all binary words of length d
     * prefixed by s
	 * Complexity: THETA(2^d)
     */
    private static void binary(String s, int d) {
        if (d == 0 ) {
            System.out.print(s + " ");
        }
        else {
            binary(s + "0",d-1);
            binary(s + "1",d-1);
        }
    }
    
    ///// printing A-B words
    
    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     * Complexity: THETA( C(x,x+y) = C(y,x+y) )
     */
    public static void words(int x, int y) {
        words(x,y,"");
    }
 
    /**
     * Print out all words made of
     * x letters 'A' and y letters 'B'
     * prefixed by s
     * Complexity: THETA( C(x,x+y) = C(y,x+y) )
     */
    private static void words(int x, int y, String s) {
        if ( x == 0 && y == 0 ) {
        	System.out.print(s + " ");
        }
        if ( x > 0 ){
        	words(x-1,y,s + 'A');
        }
        if ( y > 0 ){
            words(x,y-1,s + 'B');
        }
    }

    ///// printing all permutations of { 0, 1, 2, ..., n - 1 }
    
    /**
     * Print out all permutations of
     * (1, 2, 3, ..., n)
     * Complexity: THETA( n! )
     */
    public static void permutations(int n) {
        int[] p = new int[n];
        for( int i = 0; i < p.length; i++ )
            p[i] = i+1;
        permutations(p,0);
    }

    /**
     * Print out the array p for all
     * permutations of (i, ..., n).
     * This method must NOT change the array p
     * (i.e. after the call, p is the same as 
     * before the call).
     * Complexity: THETA( (n - i + 1)! )
     * where n = p.length
     */
    private static void permutations(int[] p, int i) {
        if ( i == p.length-1 )
            print(p);
        else {
            for( int k = i; k < p.length; k++ ) {
                int x = p[i]; p[i] = p[k]; p[k] = x;
                permutations(p,i+1);
            }
            int x = p[i];
            for( int k = i+1; k < p.length; k++ )
                p[k-1] = p[k];
            p[p.length-1] = x;
        }
    }

    /**
     * Prints out the content of the
     * array in the form of a permutation
     */
    private static void print(int[] p) {
        System.out.print('(');
        System.out.print(p[0]);
        for( int i = 1; i < p.length; i++ )
            System.out.print("," + p[i]);
        System.out.print(") ");
    }
    
    ///// checks if a given value can be found by summing up items in an array
    
    /**
     * Check if N can be found by adding
     * some values taken inside A
     * Complexity: O( 2^n ) where n = A.length
     */
    public static boolean sum(int[] A, int N) {
    	int max = 0;
        for ( int i = 0; i < A.length; i++ )
            max = max + A[i];
        if ( N < 0 || N > max )
            return false;
        return sum(A,N,A.length);
    }

    /**
     * Check if N can be found by adding
     * some values taken inside A[0..k-1]
     * Complexity: O( 2^k )
     */
    private static boolean sum(int[] A, int N, int k) {
        if ( N == 0 )
            return true;
        if ( k == 0 )
            return false;
        if ( A[k-1] <= N )
            return sum(A,N,k - 1) || sum(A,N - A[k-1],k - 1);
        return sum(A,N,k - 1);
    }
}
