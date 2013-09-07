import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DequeTest {

	@Test
	public void addTailTest() {
		Deque<String> d = new Deque<String>();
		d.addLast("");
		assertTrue(d.size() == 1);
		assertTrue(d.removeFirst().equals(""));
	}

	@Test
	public void addRemoveFirstOneTest() {
		Deque<String> d = new Deque<String>();
		// System.out.println(d);
		assertTrue(d.size() == 0);
		d.addFirst("1");
		// System.out.println(d);
		assertTrue(d.removeFirst().equals("1"));
		// System.out.println(d);
	}

	@Test
	public void addRemoveLastOneTest() {
		Deque<String> d = new Deque<String>();
		// System.out.println(d);
		assertTrue(d.size() == 0);
		d.addLast("1");
		// System.out.println(d);
		assertTrue(d.removeLast().equals("1"));
		// System.out.println(d);
	}

	@Test
	public void addRemoveFirstLastTest() {
		Deque<String> d = new Deque<String>();
		// System.out.println(d);
		assertTrue(d.size() == 0);
		d.addFirst("1");
		// System.out.println(d);
		assertTrue(d.removeLast().equals("1"));
		// System.out.println(d);
	}

	@Test
	public void addRemoveLastFirstTest() {
		Deque<String> d = new Deque<String>();
		// System.out.println(d);
		assertTrue(d.size() == 0);
		d.addLast("1");
		// System.out.println(d);
		assertTrue(d.removeFirst().equals("1"));
		// System.out.println(d);
	}

	@Test
	public void addRemoveFirstOrderedTest() {
		System.out.println("addRemoveFirstOrderedTest");

		Deque<Integer> d = new Deque<Integer>();
		int n = 10;
		for (int i = 0; i < n; i++) {
			System.out.println("Adding " + i);
			d.addFirst(i);
		}

		// System.out.println(d);

		int removed;
		for (int i = n - 1; i >= 0; i--) {
			removed = d.removeFirst();
			System.out.println("Removing " + removed);
			assertTrue(removed == i);
		}
	}

	@Test
	public void addTwoFirstTest() {
		Deque<Integer> d = new Deque<Integer>();
		d.addFirst(1);
		d.addFirst(2);

		// System.out.println(d);
	}

	@Test
	public void addTwoLastTest() {
		Deque<Integer> d = new Deque<Integer>();
		d.addLast(1);
		d.addLast(2);

		// System.out.println(d);
	}

	// @Test
	public void addRemoveLastOrderedTest() {
		System.out.println("addRemoveLastOrderedTest");

		Deque<Integer> d = new Deque<Integer>();
		int n = 10;
		for (int i = 0; i < n; i++) {
			System.out.println("Adding " + i);
			d.addLast(i);
		}

		// System.out.println(d);

		int removed;
		for (int i = n - 1; i >= 0; i--) {
			removed = d.removeLast();
			System.out.println("Removing " + removed);
			assertTrue(removed == i);
		}
	}

	@Test
	public void zeroLengthTest() {
		Deque<String> d = new Deque<String>();
		assertTrue(d.size() == 0);
		d.addFirst("");
		d.removeFirst();
		assertTrue(d.size() == 0);
	}

	@Test
	public void addLengthTest() {
		Deque<String> d = new Deque<String>();
		assertTrue(d.size() == 0);
		d.addFirst("");
		assertTrue(d.size() == 1);
		d.addFirst("");
		assertTrue(d.size() == 2);
	}

	@Test
	public void addTest() {
		Deque<String> d = new Deque<String>();
		d.addFirst("");
		assertTrue(d.size() == 1);
		assertTrue(d.removeFirst().equals(""));
	}

	@Test
	public void addTenFrontTest() {
		int n = 10;

		Deque<String> d = new Deque<String>();
		for (int i = 0; i < n; i++) {
			d.addFirst("");
		}
		assertTrue(d.size() == n);
	}

	@Test
	public void addTenBackTest() {
		int n = 10;

		Deque<String> d = new Deque<String>();
		for (int i = 0; i < n; i++) {
			d.addLast("");
		}
		assertTrue(d.size() == n);
	}

	@Test
	public void addTenFirstIterator() {
		int n = 10;

		Deque<Integer> d = new Deque<Integer>();
		for (int i = 0; i < n; i++) {
			d.addLast(i);
		}

		int i = 0;
		for (Integer item : d) {
			System.out.println("Iterator has " + item);
			assertTrue(item == i++);
		}

	}

	@Test(expected = NoSuchElementException.class)
	public void zeroLengthRemoveFirstException() {
		Deque<String> d = new Deque<String>();
		d.removeFirst();
	}

	@Test(expected = NoSuchElementException.class)
	public void zeroLengthRemoveLastException() {
		Deque<String> d = new Deque<String>();
		d.removeLast();
	}

	@Test(expected = NullPointerException.class)
	public void nullAddFirstException() {
		Deque<String> d = new Deque<String>();
		d.addFirst(null);
	}

	@Test(expected = NullPointerException.class)
	public void nullAddLastException() {
		Deque<String> d = new Deque<String>();
		d.addLast(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeException() {
		Deque<String> d = new Deque<String>();
		d.iterator().remove();
	}

	@Test(expected = NoSuchElementException.class)
	public void nextException() {
		Deque<String> d = new Deque<String>();
		d.iterator().next();
	}

	@Test(expected = NoSuchElementException.class)
	public void nextNextException() {
		Deque<String> d = new Deque<String>();
		d.addFirst("");
		Iterator<String> i = d.iterator();
		i.next();
		i.next();
	}
}