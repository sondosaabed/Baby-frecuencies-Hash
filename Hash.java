import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.Collections; 

//hash = (hash + i * i) % tableSize; // Quadratic
// hash = (hash + 1) % tableSize; // Linear
// hash = (hash + i * F2) % tableSize; // Double
public class Hash {
	private int tableSize = 200;
	private HashNode[] table;
	private int currentSize = 0;
	public static ArrayList<Integer> maxList = new ArrayList<>();
	
	public Hash() {
		
	}
	
	public Hash(int size) {  
		table = new HashNode[ nextPrime(2 *size)]; 
		for (int i = 0; i < size; i++)
			table[i] = null;
		tableSize =  nextPrime(2 *size) ;
		currentSize = 0;
	}

	public int insert(Name name, Frecuency frecuency, int HeapSize) {
		String key= name.getName()+name.getGender();
		return insert(key,name,frecuency,HeapSize);
	}
	
	public int update(Name name, Frecuency frecuency, int HeapSize) {
		String key= name.getName()+name.getGender();
		return update(key,name,frecuency,HeapSize);
	}
	
	public void insert(HashNode node) { 
		for(int  i = 0; i < node.getMaxHeap().getSize(); i++)
		insert(node.getKey(),node.getName(),node.getMaxHeap().insert(node.getMaxHeap().getHeapTable()[i]),node.getMaxHeap().getSize() );
	}
	
	private int insert(String key, Name name, Frecuency frecuency, int HeapSize) { 
		int answer =0;
		if (currentSize >= tableSize / 2)
			rehash();
		
		int hash = getHash(key);
		int i = 1;
		while ((table[hash] != null) && (table[hash].getStatus() != 0) && (table[hash].getStatus() != 2) && !(table[hash].getKey().equals(key)) &&i<0) {
		  hash = (hash + i * i) % tableSize; // Quadratic 
		  i++;
		}   
		HashNode found = find(key); 
		if(found!= null) { 
			if(found.getMaxHeap().search(frecuency )==(null) ){ 
				found.getMaxHeap().insert(frecuency);
				answer = 1;
			} else if(found.getMaxHeap().search(frecuency)!=(null)){
				int u=found.getMaxHeap().search(frecuency ).getFrecuency();
        	   found.getMaxHeap().search(frecuency ).setFrecuency(u +frecuency.getFrecuency()); 
        	   answer = -1;
			} 
		}else if(found== null || this.getCurrentSize()==0) {
			table[hash] = new HashNode( name, 1,HeapSize);
			table[hash].getMaxHeap().insert(frecuency ); 
			currentSize++;
			answer = 0;
		}  
		return answer;
	} 
	
	private int update(String key, Name name, Frecuency frecuency, int heapSize) {
		int answer =0; 
		HashNode found = find(key); 
		if(found!= null) { 
			if(found.getMaxHeap().search(frecuency )==(null) ){ 
				answer = 1;
			} else if( found.getMaxHeap().search(frecuency )!=(null)){ 
        	   found.getMaxHeap().search(frecuency ).setFrecuency( frecuency.getFrecuency());  
        	   answer = 0;
			} 
		}else if(found== null || this.getCurrentSize()==0) { 
			answer = -1;
		}  
		return answer;
	}
	
	public HashNode find(String key) {
		int i = 1;
		int hash = getHash(key);
		while (((table[hash] != null) && (table[hash].getStatus() != 0) && (table[hash].getKey() != key) && (table[hash].getStatus() == 2))) {
			hash = (hash + i * i) % tableSize;  
			i++;
		}
		if ((table[hash] == null) || (table[hash].getStatus() == 0))
			return null;
		else
			return table[hash];
	}
	  
	public boolean contains(String key) {
		return get(key) != null;
	}

	public Name get(String key) {
		int i = 1;
		int location = getHash(key);
		while ((table[location] != null) && (table[location].getStatus() != 0)) {
			if (table[location].getKey() == key)
				return table[location].getName();
			location = (location + i * i) % tableSize; // Quadratic 
			i++;
		}
		return null;
	}

	public int remove(String key) { 
		int i = 1;
  		if ( find(key) == null) {
  			return -1;
  		} else {
  			int hash = getHash(key);
  			while (( (table[hash] != null) &&  (table[hash].getStatus() != 0) &&  (table[hash].getKey() != key) && (table[hash].getStatus() == 2))) {
  				hash = (hash + i * i) % tableSize;  
  				i++;
  			}  
  			table[hash].setDeleteStatus();  
  			currentSize--;
  			return 0; 
  		}   
	} 
	
	public int getHash (String s){
		int hash = 0; 
		for (int i = 0 ; i < s.length() ; i++) {
			hash = ((hash << 5)  + s.charAt(i)) % table.length;
		}
		return hash; 
	}

	private void rehash() {
		Hash newList;
		newList = new Hash(nextPrime(2 * table.length)); 
		for (int i = 0; i < table.length; i++)
			if ((table[i] != null) && (table[i].getStatus() == 1)) {
				newList.insert(table[i]); 
			}   
		table = newList.table; 
		tableSize = newList.tableSize;
	}

	private int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		while(!isPrime(n)) {
			n += 2;
		}
		return n;
	}

	private boolean isPrime(int num) {
		for (int i = 2 ; i < (num / 2) + 1 ; i++) {
			if (num % i == 0)
				return false;
		}
		return true; 
	}

	public void printHashTable() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null)
				continue;
			else if (table[i].getStatus() == 1) {
				System.out.println(i);
				System.out.println(table[i].toString()  );
			}
		}
	}
	
	public void makeEmpty() {
		for (int i = 0; i < table.length; i++)
			table[i] = null;

		currentSize = 0;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public int getTableSize() {
		return tableSize;
	}
 
	public HashNode getMax() {   
		HashNode max = null; 
		for(int i = 0;  i < table.length;i++) {  
			if(table[i] != null)
				maxList.add(table[i].getMaxHeap().getHeapTable()[1].getFrecuency());  
 
			if(table[i] != null&&table[i].getMaxHeap().getHeapTable()[1].getFrecuency()==Collections.max(maxList) ) {
				 max = table[i]; 
			}  
		}   
		return max;
	}
	
	public void write(BufferedWriter out,Frecuency frec){ 
		try {
		    for(int i = 0;  i < table.length;i++)  {  
				if(table[i]!=null && table[i].getStatus()==1)
					table[i].write(out, frec); 
			}   
			out.close();
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}