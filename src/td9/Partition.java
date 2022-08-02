package td9;

import java.util.*;

/**
 * A class to implement disjoint sets using up-tree
 */
public class Partition<AnyType> {
	
	// the number of trees in the partition
	private int nbTrees; 
	// to map each AnyType value with its tree
	private final Map<AnyType,InnerTree> map;
	
	/**
	 * Return an empty partition with no tree
	 */
	public Partition() {
		nbTrees = 0;
		map = new HashMap<>();
	}
	
	/**
	 * Returns the number of tree in the partition
	 */
	public int nbTrees() {
		return nbTrees;
	}
	
	/**
	 * Add a new one-element tree with
	 * element 'e' in the partition
	 */
	public void newTree(AnyType e) {
		map.put(e,new InnerTree(this));
		nbTrees++;
	}
	
	/**
	 * Returns the root tree the
	 * element 'e' belongs to
	 */
	public Tree find(AnyType e) {
		return find(map.get(e));
	}
	
	/**
	 * Merge the two root trees 'x' and 'y'
	 * Precondition: 'x' != 'y'
	 */
	@SuppressWarnings("unchecked")
	public void union(Tree x, Tree y) {
		union((InnerTree) x, (InnerTree) y);
	}
	
	/**
	 * Merge the two root InnerTree 'x' and 'y'
	 * Precondition: 'x' != 'y'
	 */	
	private void union(InnerTree x, InnerTree y) {
		if ( x.size <= y.size ) {
			x.parent = y;
			y.size += x.size;
		}
		else {
			y.parent = x;
			x.size += y.size;
		}
		nbTrees--;
	}
	
	/**
	 * Returns the root InnerTree the
	 * (sub-tree) InnerTree 'n' belongs to
	 * doing path compression
	 */
	private InnerTree find(InnerTree n) {
		if ( n == n.parent )
			return n;
		InnerTree root = find(n.parent);
		n.parent = root;
		return root;
	}
	
	/**
	 * If the InnerTree 'n' does not belong
	 * to this Partition, throw a BadTreeException
	 * exception
	 */
	private void check(InnerTree n) {
		if ( n.host != this )
			throw new BadTreeException();
	}
	
	/**
	 * A public interface to provide the Partition.Tree type
	 * to the client code. There is NO operation available
	 * on Partition.Tree, except reference comparison == and !=
	 */
	public interface Tree {}
	
	/**
	 * A (private) class for InnerTree
	 */
	private class InnerTree implements Tree {
		
		// the InnerTree parent
		InnerTree parent;
		// the number of elements inside this InnerTree
		int size;
		// the Partition object this InnerTree belongs to
		Partition<AnyType> host;
		
		/**
		 * Returns a single-element InnerTree
		 */
		InnerTree(Partition<AnyType> host) {
			this.parent = this;
			this.size = 1;
			this.host = host;
		}
	}
	
	public static void main(String[] s) {
		Partition<String> p = new Partition<>();
		p.newTree("A");
		p.newTree("B");
		p.newTree("C");
		p.newTree("D");
		Partition.Tree t1 = p.find("A");
		Partition.Tree t2 = p.find("B");
		p.union(t1, t2);
		t1 = p.find("B");
		t2 = p.find("C");
		p.union(t1, t2);
		t1 = p.find("B");
		t2 = p.find("C");
		System.out.println(t1 == t2);
		System.out.println(p.nbTrees());
		// should display:
		// true
        // 2
	}	
}
