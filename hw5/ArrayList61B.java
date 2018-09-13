import java.util.AbstractList;

public class ArrayList61B<ETYPE> extends AbstractList<ETYPE> {
	private int size;
	private int length;
	private ETYPE[] aList;
	
	
	
	

	public ArrayList61B(int iSize) {
		if (iSize <= 0) {
			throw new IllegalArgumentException("must have at least 1 element");
		} else {
			size = iSize;
			length = 0;
			aList = (ETYPE[]) new Object[size];
		}
	}	
	
	public ArrayList61B() { //default aList construction
		size = 1;
		length = 0;
		aList = (ETYPE[]) new Object[size];
	}
	
	public int size() {
		return length;
	}
	
	public ETYPE get(int x) {
		if (x >= length || x < 0) { 	//conditions make sure it is zeroth indexed
			throw new IllegalArgumentException();
		} else {
			return aList[x];
		}
	}
	

	public boolean add(ETYPE x) {
		length = length + 1;
		if (length > size) {
			size = size*2;
			ETYPE[] doubledlist= (ETYPE[]) new Object[size];
			for (int i = 0; i < length-1; i++) {
				doubledlist[i] = aList[i];
			}
			/*System.arraycopy(aList, 0, doubledlist, 0, length-1);*/
			aList = doubledlist;
			aList[length-1] = x;
		} else {
			aList[length-1] = x;
		}
		
		return true;
	}
	
	
	
}
