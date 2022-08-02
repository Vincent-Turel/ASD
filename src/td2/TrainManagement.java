package td2;

import java.util.*;

/**
 * A class to arrange train configuration
 */
public class TrainManagement {
	
	private final int[] from; // the initial ordering
	private final int[] to;   // the final ordering
	
	/**
	 * Build a TrainManagement object
	 * Preconditions:
	 * 'from' and 'to' have the same size N and are
	 * both permutations of [ 0, 1, 2,..., N-1 ]
	 */
	public TrainManagement(int[] from, int[] to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * Output the basic move commands to transfer
	 * the cars from the 'from' order on track U
	 * to the 'to' order on track S
	 */
	public void arrange() throws EmptyStackException {
		Stack<Integer> U = new Stack<>();
		Stack<Integer> S = new Stack<>();
		Stack<Integer> T = new Stack<>();
		for (int x = from.length - 1; x >= 0; x--) {
			U.push(from[x]);
		}
		for (int x = to.length - 1; x >= 0; x--) {
			S.push(to[x]);
		}
		while (S.size() != 0) {
			int sizeS = S.size();
			while (S.size() >= sizeS) {
				display(U.peek(), "track U", "track T");
				T.push(U.pop());
				if (T.peek().equals(S.peek())) {
					display(T.peek(), "track T", "track S");
					S.pop();
					T.pop();
				}
			}
			while (T.size() != 0) {
				if (T.peek().equals(S.peek())) {
					display(T.peek(), "track T", "track S");
					S.pop();
					T.pop();
				}
				else {
					display(T.peek(), "track T", "track U");
					U.push(T.pop());
				}
			}
		}
	}

	/**
	 * Display the basic command (message) for moving
	 * the car 'car' from tack 'from' to track 'to'
	 */
	private void display(int car, String from, String to) {
		System.out.println("move car " + car + " from " + from + " to " + to);
	}

	////////////////////////////////////
	
	/**
	 * Checks if the array 'track' is a legal track
	 * i.e. is a permutation of [ 0, 1, 2,..., N-1 ]
	 * where N = track.length
	 */
	private static boolean notApermutation(int[] track) {
		boolean[] check = new boolean[track.length];
		for (int i = 0; i < track.length; i++)
			if ( track[i] < 0 || track[i] >= track.length || check[track[i]] )
				return true;
			else
				check[track[i]] = true;
		return false;
	}
	
	@SuppressWarnings("resource")
	private static int[] readTrack(Scanner input, String prompt) {
		List<Integer> list = new LinkedList<>();
		System.out.print(prompt);
		Scanner scan = new Scanner(input.nextLine());
		while ( scan.hasNextInt() )
			list.add(scan.nextInt());
		return list.stream().mapToInt(i->i).toArray();
	}
	
    /**
     * An interactive main for testing.
     */	
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Welcome to the Train arrangement program\n");
		while (true) {
			int[] from = readTrack(keyboard,"\nEnter the 'from' track (just enter to exit): ");
			if ( from.length == 0 )
				break;
			if ( notApermutation(from) ) {
				System.out.println("bad track!");
				continue;
			}
			int[] to = readTrack(keyboard,"Enter the 'to' track: ");
			if ( notApermutation(to) ) {
				System.out.println("bad track!");
				continue;
			}
			if ( from.length != to.length ) {
				System.out.println("the 'from' and 'to' tracks don't have the same size!");
				continue;
			}
			try {
				(new TrainManagement(from,to)).arrange();
			}
			catch (EmptyStackException ese) {
				System.out.println("oops! EmptyStackException");
			}
		}
		System.out.println("\nThank you for using the Train arrangement program");
	}
}
