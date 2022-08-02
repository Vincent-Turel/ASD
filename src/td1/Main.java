package td1;

import java.util.EmptyStackException;

public class Main {
    public static void main(String[] args) throws EmptyStackException {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for ( int i = 1; i <= 100; i++ )
            stack.push(i);
        int i = 0;
        while ( ! stack.isEmpty() ) {
            if ( i++ % 25 == 0 )
                System.out.println();
            System.out.print(stack.pop() + " ");
        }

        System.out.println();

        StackMin<Integer> s = new StackMin<>();
        s.push(3); s.push(1); s.push(2);
        System.out.println(s.findMin());
        s.pop(); s.pop(); s.push(5);
        System.out.println(s.findMin());
        s.push(2); s.push(4); s.push(6);
        System.out.println(s.findMin());
    }
//    public static void main(String[] args) {
//        binary(3);
//        System.out.print("\n");
//        words(2,1,"");
//        System.out.print("\n");
//        sans00(3);
//        System.out.print("\n");
//        System.out.print(sum(new int[]{3, 5, 7, 11},21));
//        System.out.print("\n");
//        System.out.print(sum(new int[]{11, 5, 7, 3},21));
//        System.out.print("\n");
//        System.out.print(sum(new int[]{3, 5, 7, 11},13));
//        System.out.print("\n");
//        System.out.print(sum(new int[]{3, 5},0));
//        System.out.print("\n");
//        permutations(3);
//        System.out.print("\n");
//        permutations(2);
//        System.out.print("\n");
//        permutations(1);
//    }
    /**
     * Print out all binary words of length k
     * Complexity: THETAT(2^k)
     */
    public static void binary(int n){
        binary(" ",n);
    }

    private static void binary(String m, int d)
    {
        if (d == 0)
            return;
        if (d == 1)
        {
            System.out.print(m + "0 ");
            System.out.print(m + "1 ");
        }
        binary(m + "0", d - 1);
        binary(m + "1", d - 1);
    }

    private static void words(int x, int y, String s) {
        if (x == 0 && y == 0)
        {
            System.out.print(s + " ");
        }
        if(x > 0)
            words( x - 1, y,  s + "A");

        if (y > 0)
            words(x, y - 1, s + "B");
    }

    public static void sans00(int x) {
        sans00(x, "");
    }

    /**
     * Print out all binary words of length x
     * without '00'
     * Complexity: THETA( C(x) = C(x-1) + C(x-2) )
     */
    private static void sans00(int x, String s) {
        if (x == 0)
            return;
        boolean b = s.length() == 0 || s.charAt(s.length() - 1) != '0';
        if (x == 1)
        {
            if (b){
                System.out.print(s + "0 ");
            }
            System.out.print(s + "1 ");
        }
        if (b){
            sans00(x - 1, s + "0");
        }
        sans00(x - 1, s + "1");
    }

    public static boolean sum(int[] A, int n) {
        return sum(A, n, A.length);
    }

    /**
     * Check if n can be found by adding
     * some values taken inside A[0..k-1]
     * Complexity: THETA( 2^k )
     */
    private static boolean sum(int[] A, int n, int k) {
        return (n == 0 || (k != 0 && (sum(A, n, k-1) ||A[k-1] <= n && sum(A, n-A[k-1], k-1))));
    }

    /**
     * Print out all permutations of
     * (1, 2, 3, ..., n)
     * Complexity: THETA( n! )
     */
    public static void permutations(int n) {
        int[] p = new int[n];
        for (int i = 0; i < p.length; i++)
            p[i] = i + 1;
        permutations(p, 0);
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
        
    }

    /**
     * Prints out the content of the
     * array in the form of a permutation
     */
    private static void print(int[] p) {
        System.out.print('(');
        System.out.print(p[0]);
        for (int i = 1; i < p.length; i++)
            System.out.print("," + p[i]);
        System.out.print(") ");
    }

}
