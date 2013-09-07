import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

public class RandomizedQueueTest {

	@Test
	public void enqueueOneTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.enqueue("");
		assertTrue(d.size() == 1);
		assertTrue(d.dequeue().equals(""));
	}

	@Test
	public void enqueueDequeueOneTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		// System.out.println(d);
		assertTrue(d.size() == 0);
		d.enqueue("1");
		// System.out.println(d);
		assertTrue(d.dequeue().equals("1"));
		// System.out.println(d);
	}

	@Test
	public void adddequeueOrderedTest() {
		System.out.println("adddequeueOrderedTest");

		RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
		int n = 10;
		for (int i = 0; i < n; i++) {
			System.out.println("Adding " + i);
			d.enqueue(i);
		}

		// System.out.println(d);

		int removed;
		for (int i = n - 1; i >= 0; i--) {
			removed = d.dequeue();
			System.out.println("Removing " + removed);
			assertTrue(removed == i);
		}
	}

	@Test
	public void addTwoFirstTest() {
		RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
		d.enqueue(1);
		d.enqueue(2);

		// System.out.println(d);
	}

	@Test
	public void zeroLengthTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		assertTrue(d.size() == 0);
		d.enqueue("");
		d.dequeue();
		assertTrue(d.size() == 0);
	}

	@Test
	public void addLengthTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		assertTrue(d.size() == 0);
		d.enqueue("");
		assertTrue(d.size() == 1);
		d.enqueue("");
		assertTrue(d.size() == 2);
	}

	@Test
	public void enqueueTenTest() {
		int n = 10;

		RandomizedQueue<String> d = new RandomizedQueue<String>();
		for (int i = 0; i < n; i++) {
			d.enqueue("");
		}
		assertTrue(d.size() == n);
	}

	@Test
	public void addTenFirstIterator() {
		int n = 10;

		RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
		for (int i = 0; i < n; i++) {
			d.enqueue(i);
		}

		Set<Integer> s = new HashSet<Integer>();
		
		for (Integer item : d) {
			s.add(item);
			System.out.println("Iterator has " + item);
		}
		
		int i = 0;
		for (Integer item: s) {
			assertTrue(item == i++);
		}

	}

	@Test(expected = NoSuchElementException.class)
	public void zeroLengthdequeueException() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.dequeue();
	}

	@Test(expected = NullPointerException.class)
	public void nullenqueueException() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.enqueue(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeException() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.iterator().remove();
	}

	@Test(expected = NoSuchElementException.class)
	public void nextException() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.iterator().next();
	}

	@Test(expected = NoSuchElementException.class)
	public void nextNextException() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.enqueue("");
		Iterator<String> i = d.iterator();
		i.next();
		i.next();
	}
}
