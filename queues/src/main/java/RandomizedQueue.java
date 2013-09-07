import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	// Storage
	private Item[] queue;
	private int headIndex;
	private int tailIndex;
	private int length;
	
	static private final int DEFAULT_CAPACITY = 10;
	// For array resizing
	static private final double SCALE_UP_FACTOR = 2.0;
	static private final double SCALE_DOWN_FACTOR = 0.25;
	
	// construct an empty randomized queue
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {		
		queue = (Item[]) new Object[DEFAULT_CAPACITY];
		headIndex = -1;
		tailIndex = -1;
		length = 0;
	}

	// is the queue empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// return the number of items on the queue
	public int size() {
		return length;
	}

	// add the item
	public void enqueue(Item item) {
		if (null == item) {
			throw new NullPointerException();
		}
		
		int newIndex;
		
		if (size() == 0) {
			headIndex = 0;
			tailIndex = 0;
			queue[newIndex] = item;				
		} else {
			// Try to put at end
			newIndex = tailIndex + 1;
			
			// If we're at the end of the array resize
			if(newIndex == queue.length) {
				resize(SCALE_UP_FACTOR);			
			}
			if queue.length;
		}
		
		
			
		length++;
	}

	@SuppressWarnings("unchecked")
	private void resize(double scaleUpFactor) {
		// Create new, bigger array
		Item[] newQueue = (Item[]) new Object[(int) (queue.length * scaleUpFactor)];
		// Copy over old elements
		int i = 0;
		for(Item item : queue) {
			newQueue[i++] = item;
		}
		// Replace
		queue = newQueue;
	}

	// delete and return a random item
	public Item dequeue() {
		return null;
	}

	// return (but do not delete) a random item
	public Item sample() {
		return null;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return null;
	}
}
