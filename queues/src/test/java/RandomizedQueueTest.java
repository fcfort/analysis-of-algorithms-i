import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

public class RandomizedQueueTest {

	private static final int SMALL = 100;
	private static final int MEDIUM = 10000;
	private static final int LARGE = 1000000;
	
	private static final String TEST_STRING = "XYZ";

	private enum QueueOperation {
		ENQUEUE, DEQUEUE, SAMPLE
	}
	
	@Test
	public void randomOperationTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();

		int size = 0;

		for (int i = 0; i < MEDIUM; i++) {			
			int randOperationIndex = StdRandom.uniform(3);
			QueueOperation randOperation = QueueOperation.values()[randOperationIndex];
			System.out.println("On iteration " + i + " performing operation " + randOperation);
			switch (randOperation) {
			case ENQUEUE:
				d.enqueue(TEST_STRING);
				size++;
				break;
			case DEQUEUE:
				if (!d.isEmpty()) {
					assertTrue(d.dequeue().equals(TEST_STRING));
					size--;
				}
				break;
			case SAMPLE:
				if (d.size() > 0 ) {
					d.sample();
				}
				assertTrue(d.size() == size);
				break;
			}
		}
	}

	@Test
	public void iteratorLargeTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		for (int i = 0; i < LARGE; i++) {
			d.enqueue(TEST_STRING);
		}
		for (String s : d) {
			assertTrue(s.equals(TEST_STRING));
		}
	}

	@Test
	public void addRemoveLargeTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		for (int i = 0; i < LARGE; i++) {
			d.enqueue(TEST_STRING);
		}
		for (int i = 0; i < LARGE; i++) {
			assertTrue(d.dequeue().equals(TEST_STRING));
		}
	}

	@Test
	public void addRemoveLarge2x() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		for (int i = 0; i < LARGE * 2; i++) {
			d.enqueue(TEST_STRING);
		}
		for (int i = 0; i < LARGE * 2; i++) {
			assertTrue(d.dequeue().equals(TEST_STRING));
		}
	}

	@Test
	public void addRemoveLarge4x() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		for (int i = 0; i < LARGE * 4; i++) {
			d.enqueue(TEST_STRING);
		}
		for (int i = 0; i < LARGE * 4; i++) {
			assertTrue(d.dequeue().equals(TEST_STRING));
		}
	}

	@Test
	public void enqueueOneTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.enqueue(TEST_STRING);
		assertTrue(d.size() == 1);
		// assertTrue(d.dequeue().equals(""));
	}

	@Test
	public void enqueueDequeueOneTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		// System.out.println(d);
		assertTrue(d.size() == 0);
		d.enqueue(TEST_STRING);
		// System.out.println(d);
		assertTrue(d.dequeue().equals(TEST_STRING));
		// System.out.println(d);
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
		d.enqueue(TEST_STRING);
		d.dequeue();
		assertTrue(d.size() == 0);
	}

	@Test
	public void addLengthTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		assertTrue(d.size() == 0);
		d.enqueue(TEST_STRING);
		assertTrue(d.size() == 1);
		d.enqueue(TEST_STRING);
		assertTrue(d.size() == 2);
	}

	@Test
	public void sampleTest() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.enqueue(TEST_STRING);
		assertTrue(d.sample().equals(TEST_STRING));
		assertTrue(d.size() == 1);
		assertTrue(d.sample().equals(TEST_STRING));
		assertTrue(d.size() == 1);
	}

	@Test
	public void enqueueTenTest() {
		int n = 10;

		RandomizedQueue<String> d = new RandomizedQueue<String>();
		for (int i = 0; i < n; i++) {
			d.enqueue(TEST_STRING);
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
		for (Integer item : s) {
			assertTrue(item == i++);
		}

	}

	@Test(expected = NoSuchElementException.class)
	public void zeroLengthDequeueException() {
		RandomizedQueue<String> d = new RandomizedQueue<String>();
		d.dequeue();
	}

	@Test(expected = NullPointerException.class)
	public void nullEnqueueException() {
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
		d.enqueue(TEST_STRING);
		Iterator<String> i = d.iterator();
		i.next();
		i.next();
	}
}
