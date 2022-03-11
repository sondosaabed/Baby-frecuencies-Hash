  
public class Heap<T extends Comparable<Frecuency>> {
	private Frecuency[] HeapTable;
	private int size;

	private static final int ROOT = 1;

	public Heap(int MaxSize) { 
		this.size = 0;
		setHeapTable(new Frecuency[MaxSize + 1]);
	}

	public Frecuency insert(Frecuency element) { 
		getHeapTable()[++size] = element;
		swim();
		return element;
	}

	private void swim() {
		int curr = size;
		while (curr > 1 && less(curr / 2, curr)) {
			swap(curr, curr / 2);
			curr = curr / 2;
		}
	}

	private void swap(int curr, int parent) {
		Frecuency temp;
		temp = getHeapTable()[curr];
		getHeapTable()[curr] = getHeapTable()[parent];
		getHeapTable()[parent] = temp;
	}

	private boolean less(int i, int j) {
		// (-) <0 the parent is smaller
		// (+) >0 or =0 the parent is larger
		return getHeapTable()[i].compareTo(getHeapTable()[j]) < 0;
	} 
	
	public Frecuency search(Frecuency frec) {   
		String year=frec.f.format(frec.getYear());
		for (int i = 1; i <=size ; i++) {
			if(HeapTable[i].f.format(HeapTable[i].getYear()).equals(year)){    
                return HeapTable[i];    
            } 
		}
		return null;
	}
	
	public Frecuency remove() {
		Frecuency removedElement = getHeapTable()[ROOT];
		getHeapTable()[ROOT] = getHeapTable()[size--];
		sink(ROOT,size);
		getHeapTable()[size + 1] = null;
		return removedElement;
	}

	public void sort() {
//		 for (int i = size ; i >= 1; i--) {
//		 	sinkSort(1, i);
//		 }
		int n = size;
		while (n > 1) {
			swap(ROOT, n);
			n--;
			sink(ROOT,n);

		}
	}

	private void sink(int pos,int lastPos) {
		// left pos*2 =j
		// right pos*2+1 =j+1
		if (pos * 2 <= lastPos && (pos * 2) + 1 <= lastPos) // is not Leaf
			if (less(pos, pos * 2) || less(pos, (pos * 2) + 1)) {
				if (less(pos * 2, (pos * 2) + 1)) {
					swap(pos, (pos * 2) + 1);
					sink((pos * 2) + 1,lastPos);
				} else {
					swap(pos, pos * 2);
					sink(pos * 2,lastPos);
				}
			}
	}

	public String print() {
		for (int i = 1; i <= size  ; i++) {
			System.out.print(getHeapTable()[i]);
//			System.out.print(" PARENT : " + HeapTable[i] + "----> LEFT CHILD : " + HeapTable[2 * i] + " RIGHT CHILD :"
//					+ HeapTable[2 * i + 1]);
			System.out.println();
		} 
		return "";
	} 
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Frecuency[] getHeapTable() {
		return HeapTable;
	}

	public void setHeapTable(Frecuency[] heapTable) {
		HeapTable = heapTable;
	}

	
}
