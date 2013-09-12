import java.util.Iterator;
import java.util.NoSuchElementException;

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
			newIndex = 0;
			queue[newIndex] = item;
		} else {
			// If we're at the end of the array resize
			if(tailIndex + 1 >= queue.length) {
				resize(SCALE_UP_FACTOR);
			}
			// Try to put at end			
			newIndex = tailIndex + 1;
			queue[newIndex] = item;
			tailIndex++;
		}					
		length++;
	}

	@SuppressWarnings("unchecked")
	private void resize(double resizeFactor) {
		// System.out.println("Before resize: " + arrayToString(queue));
		// Create new, bigger array
		Item[] newQueue = (Item[]) new Object[(int) (queue.length * resizeFactor)];
		// Copy over old elements
		int j = 0; // Index in new array
		for(int i = headIndex; i <= tailIndex; i++) {	
			// Squeeze out nulls
			if(null != queue[i]) {
				newQueue[j++] = queue[i];
			}
		}
		// Reset head/tails
		headIndex = 0;
		tailIndex = j - 1;
		// Replace
		queue = newQueue;
		
		// System.out.println("After resize: " + arrayToString(queue));
	}

	// ALways mark a free spot when dequeuing?
	// delete and return a random item
	public Item dequeue() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		Item item = null;
		int randIndex = 0;
		
		int attempts = 0;
		// Keep picking random spots until we've found an item
		// System.out.println("Haven't found a random spot yet");
		while(null == item) {
			randIndex = StdRandom.uniform(headIndex, tailIndex + 1);
			item = queue[randIndex];
			attempts++;
		}
		
		// Clear the spot
		queue[randIndex] = null;
		// System.out.println("Found and cleared random element with value '" + item + "' after " + attempts + " attempts");
		
		// Move end indices if we drew an element from the ends
		if (randIndex == headIndex) {
			headIndex++;
		}
		if (randIndex == tailIndex) {
			tailIndex--;
		}
		// Decrease length
		length--;
		
		// If the array is too empty, resize it down
		if ( length > DEFAULT_CAPACITY && length < queue.length * SCALE_DOWN_FACTOR) {
			resize(SCALE_DOWN_FACTOR);
		} else {
			// If we aren't going to resize, then store this spot for future
			// enqueues
		}
		
		return item;
	} 
	
	// return (but do not delete) a random item
	public Item sample() {
		if (size() == 0) {
			throw new NoSuchElementException(); 
		}
		
		Item item = null;

		while(null == item) {			
			int randIndex = StdRandom.uniform(headIndex, tailIndex + 1);
			// System.out.println("Looking at random index " + randIndex + " between indexes " 
			// + headIndex + " and " + (tailIndex + 1));
			// System.out.println("Queue is: " + arrayToString(queue));
			item = queue[randIndex];
		}
		return item;
	}
	
	private String arrayToString(Item[] itemArray) {
		StringBuilder b = new StringBuilder();
		b.append("[");
		for(Item item : itemArray) {
			b.append(item).append(", ");			
		}
		b.append("]");
		return b.toString();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {
		
		int currentIndex; 
		Item[] iteratorArray;	
		
		@SuppressWarnings("unchecked")
		public RandomizedQueueIterator() {
			currentIndex = 0;
			
			if ( size() == 0 ) {
				return;
			}
				
			iteratorArray = (Item []) new Object[size()];
			
			// Copy
			int j = 0;
			for(int i = headIndex; i <= tailIndex; i++) {
				if (null != queue[i]) {
					iteratorArray[j++] = queue[i];
				}
			}
			
			// Shuffle
			StdRandom.shuffle(iteratorArray, 0, j - 1);
		}

		public boolean hasNext() {
			return (currentIndex < size() && null != iteratorArray[currentIndex]);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return iteratorArray[currentIndex++];
		}
	}	
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
}
