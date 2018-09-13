import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class MyHashMap<K, V> implements Map61B<K, V>{

	private class Entry<K, V> {
		K key;
		V value;
		
		Entry(K key1, V value1) {
			key = key1;
			value = value1;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public void resetValue(V value2) {
			this.value = value2;
		}
		
		public V getValue() {
			return this.value;
		}
	}
	
	int size = 32;
	private Entry<K, V>[] tables = new Entry[size];
	
	@Override
	public void clear() {
		tables = new Entry[size];
		
	}

	@Override
	public boolean containsKey(K key) {
		for (int i = 0; i < size; i = i + 1) {
			if (tables[i] != null) {
				if (tables[i].getKey().equals(key)) {
					return true;
				}
			}
		 }
		return false;
	}

	@Override
	public V get(K key) {
		for (int i = 0; i < size; i = i + 1) {
			if (tables[i] != null) {
				if (tables[i].getKey().equals(key)) {
					return tables[i].getValue();
				}
			}
		 }
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void put(K key, V value) {
		if (containsKey(key)) {
			for (int i = 0; i < size; i = i + 1) {
				if (tables[i].key.equals(key)) {
					tables[i].resetValue(value);
					return;
				}
			}
		} else {
			resize();
			for (int i = 0; i < size; i = i + 1) {
				if (tables[i] != null) {	
					tables[i] = new Entry<K, V>(key, value);
				}
			}
		}
		
	}
	
	private void resize() {
		if (size == tables.length) {
			int resizes = tables.length * 2;
			tables = Arrays.copyOf(tables, resizes);
			this.size = resizes;
		}
	}
	
	@Override
	public V remove(K key) {
		if (containsKey(key)) {
			for (int i = 0; i < size; i = i + 1) {
				if (tables[i].getKey().equals(key)) {
					V tempVal = tables[i].getValue();
					tables[i] = null;
					return tempVal;
				}
			}
		} 
		return null;
		
	}

	@Override
	public V remove(K key, V value) {
		if (containsKey(key)) {
			for (int i = 0; i < size; i = i + 1) {
				if (tables[i].getKey().equals(key)) {
					V tempVal = tables[i].getValue();
					tables[i] = null;
					return tempVal;
				}
			}
		} 
		return null;
	}

	@Override
	public Set<K> keySet() {
		Set<K> retSet = new HashSet<K>();
		for (int i = 0; i < size; i = i + 1) {
			if (tables[i] != null) {
				retSet.add(tables[i].getKey());
			}
		}
		return retSet;
	}

}
