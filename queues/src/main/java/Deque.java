import java.util.Iterator;
import java.util.NoSuchElementException;

class Deque<Item> implements Iterable<Item> {

	// Sentinels
	private Node sentinelHead;
	private Node sentinelTail;
	// Head/tail pointers
	private Node head;
	private Node tail;
	
	private int length;	

	private class Node {
		Item i;
		boolean isSentinel;
		Node prev, next;

		Node() {
			this.isSentinel = true;
		}
		
		Node(Item i) {
			this.i = i;
			isSentinel = false;
		}
	}

	// construct an empty deque
	public Deque() {
		sentinelHead = new Node();
		sentinelTail = new Node();
		length = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return length == 0;
	}

	// return the number of items on the deque
	public int size() {
		return length;
	}

	// insert the item at the front
	public void addFirst(Item item) {
		if (null == item) {
			throw new NullPointerException();		
		}
		
		Node newNode = new Node(item);
		
		if ( length == 0 ) {
			newEmptyNode(newNode);
		} else {
			// Point sentinel head to new node
			sentinelHead.next = newNode;
			// Point new node to the old head node and the head sentinel node  
			newNode.next = head;			
			newNode.prev = sentinelHead;
			// Point the old head back to the new node
			head.prev = newNode;
			// Make head point to the new node
			head = newNode;
		}		
		length++;
	}

	// insert the item at the end
	public void addLast(Item item) {
		if (null == item) {
			throw new NullPointerException();
		}
		
		Node newNode = new Node(item);
		
		if ( length == 0 ) {
			newEmptyNode(newNode);
		} else {
			// Point sentinel tail to new node
			sentinelTail.prev = newNode;
			// Point new node to the old tail node and the tail sentinel node  
			newNode.next = tail;			
			newNode.prev = sentinelTail;
			// Point the old tail back to the new node
			tail.next = newNode;
			// Make tail point to the new node
			tail = newNode;
		}		
	}
	
	private void newEmptyNode(Node newNode) {		
		// Point head and tail to the new node
		head = newNode;
		tail = newNode;
		// Point the sentinels to the new node
		sentinelHead.next = newNode;
		sentinelTail.prev = newNode;
		// Point the new node to the sentinels
		newNode.prev = sentinelHead;
		newNode.next = sentinelTail;
	}

	// delete and return the item at the front
	public Item removeFirst() {
		if ( length == 0) {
			throw new NoSuchElementException();
		}
				
		Node removedNode = head;
		
		// Repoint pointers
		head.next.prev = sentinelHead;
		sentinelHead.next = head.next;
		head = sentinelHead.next;
		
		// Extract item
		Item i = removedNode.i;
		
		// Let the object be garbage collected
		removedNode = null;
		
		// Decrease length
		length--;
		
		return i;
		
		
	}

	// delete and return the item at the end
	public Item removeLast() {
		if ( length == 0) {
			throw new NoSuchElementException();
		}
		
		Node removedNode = tail;
		
		// Repoint pointers
		tail.prev.next = sentinelTail;
		sentinelTail.prev = tail.prev;
		tail = sentinelTail.prev;
		
		// Extract item
		Item i = removedNode.i;
		
		// Let the object be garbage collected
		removedNode = null;
		
		// Decrease length
		length--;
		
		return i;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return null;
	}
	
	public String toString() {
		Node current = head;
		
		String s = new String();
		
		while(!current.isSentinel) {
			s += current.i.toString() + ", ";
			current = current.next;
		}
		
		return s;
	}

}