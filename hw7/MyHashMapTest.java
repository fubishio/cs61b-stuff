import static org.junit.Assert.*;

import org.junit.Test;


public class MyHashMapTest {

	@Test
	public void test() {
		MyHashMap<Integer, String> a = new MyHashMap<Integer, String>();
		a.put(80, "banana");
		a.put(78, "Berk");
		a.put(2, "three");
		System.out.println(a.keySet());
		a.remove(80);
		System.out.println(a.keySet());
	}

}
