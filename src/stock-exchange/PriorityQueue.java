import java.util.*;

// A priority queue.
public class PriorityQueue<E> {
	private ArrayList<E> heap = new ArrayList<E>();//pointer to heap object
	private HashMap<E, Integer> hmap = new HashMap<E, Integer>();
	private Comparator<E> comparator;


	public PriorityQueue(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// Returns the size of the priority queue.
	public int size() {
		return heap.size();
	}
	
	/** 
	 * Adds an item to the priority queue.
	 * Time complexity: O(log(n))
	 * @param x: the element to be added.
	 */
	public void add(E x)
	{
		assert invariant() : showHeap();
		heap.add(x);
		hmap.put(x,heap.size()-1);
		siftUp(heap.size()-1);
		assert invariant() : showHeap();
	}
	/**
	 * Makes sure the invariant if the priority queue is intact.
	 * Time complexity: O(n)
	 * @return true/false
	 */
	private boolean invariant(){
		E invValue, invParentValue;
		int invParent;
		if (heap.size()<2)
			return true;
		else{
			for(int i =heap.size()-1;i>1;i--){
				invParent=parent(i);
				invValue=heap.get(i);
				invParentValue=heap.get(invParent);
				if(comparator.compare(invValue,invParentValue)<=0)
					return false;
		}
		}
		return true;
	}
	/**
	 * Shows the heap as distinct elements, with name and bid from start to end.
	 * Orienting the heap is done using the equations in helper functions.
	 * Time complexity: O(n)
	 * @return the heap.
	 */
	private String showHeap() {
		StringBuilder sbHeapShow = new StringBuilder();
		for (int i=0;i<heap.size(); i++) {
			if (i==0) {
				sbHeapShow.append(heap.get(i).toString());
			}
			else sbHeapShow.append(", " + heap.get(i).toString());
		}
		String heapShow = sbHeapShow.toString();
		System.out.println(heapShow);
		return heapShow;
		
		
	}
	/**
	 * Returns the index of an element in the hash map.
	 * @param x: element whose index is returned.
	 * @return index
	 */
	public int mapIndex(E x){
		return hmap.get(x);
	}

	// Returns the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.
	// Time complexity: O(1)
	public E minimum() {
		if (size() == 0)
			throw new NoSuchElementException();

		return heap.get(0);
	}
	public E get(int index){
		return heap.get(index);
	}
	// Removes the smallest item in the priority queue.
	// Throws NoSuchElementException if empty.
	// Time complexity: O(log(n))
	public void deleteMinimum() {
		if (size() == 0)
			throw new NoSuchElementException();
		
		hmap.remove(heap.get(0));
		heap.set(0, heap.get(heap.size()-1));
		heap.remove(heap.size()-1);
		if (heap.size() > 0) siftDown(0);
	}
	/**
	 * Replaces an old index with a new one in the heap.
	 * Time complexity O(log(n)) 
	 * @param ind: index of bid to be replaced
	 * @param newBid: bid to replace the old bid
	 */
	public void pq_replace(int ind, E newBid){
		heap.remove(ind);
		heap.add(ind, newBid);
		siftDown(ind);
		siftUp(ind);
		
	}	
	/**
	 * Sifts a node up in the heap and fixes the invariant if the element at 'index' may
	 * be less than its parent, but all other elements are correct.
	 * Time complexity: O(log(n))
	 * @param index: index of element to be sifted up
	 */
		private void siftUp(int index) {
		E value = heap.get(index);
		while (parent(index) >= 0) {
			int parentIndex = parent(index);
			
			E parentValue = heap.get(parent(index));
			if(comparator.compare(parentValue,value) > 0) {
				E tmpValue = parentValue;
				heap.set(parent(index), value);
				heap.set(index, tmpValue);
				hmap.put(value,parentIndex);
				hmap.put(tmpValue,index);
				index = parentIndex;
			} else break;
		}
		
	}
	/**
	 * Sifts a node down in the heap and fixes the invariant if the element at 'index' may
	 * be greater than its children, but all other elements are correct.
	 * Time complexity: O(log(n))
	 * @param index: index of element to be sifted 
	 */
	private void siftDown(int index) {
		E value = heap.get(index);

		// Stop when the node is a leaf.
		while (leftChild(index) < heap.size()) {
			int left    = leftChild(index);
			int right   = rightChild(index);

			// Work out whether the left or right child is smaller.
			// Start out by assuming the left child is smaller...
			int child = left;
			E childValue = heap.get(left);

			// ...but then check in case the right child is smaller.
			// (We do it like this because maybe there's no right child.)
			if (right < heap.size()) {
				E rightValue = heap.get(right);
				if (comparator.compare(childValue, rightValue) > 0) {
					child = right;
					childValue = rightValue;
				}
			}

			// If the child is smaller than the parent,
			// carry on downwards.
			if (comparator.compare(value, childValue) > 0) {
				heap.set(index, childValue);
				hmap.put(childValue,index);
				index = child;
			} else break;
		}

		heap.set(index, value);
		hmap.put(value,index);
	}

	/**
	 * Calculates the index of the left child of a node using it's index.
	 * Time complexity: O(1)
	 * @param index: index of parent node
	 * @return index of left child of node
	 */
	private final int leftChild(int index) {
		return 2*index+1;
	}
	/**
	 * Calculates the index of the right child of a node using it's index.
	 * Time complexity: O(1)
	 * @param index: index of parent node
	 * @return index of right child of node
	 */
	private final int rightChild(int index) {
		return 2*index+2;
	}
	/**
	 * Calculates the index of the parent of a node using it's index.
	 * Time complexity: O(1)
	 * @param index: index of child node
	 * @return index of parent node
	 */
	private final int parent(int index) {
		return (index-1)/2;
	}
}