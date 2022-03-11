import java.io.BufferedWriter;
import java.io.IOException;

public class HashNode {
	private String key;
	private Name name;
	private Heap<Frecuency> maxHeap;
	private int status; // insert: 1, delete: 2, empty: 0
 
	HashNode(Name value, int status, int size) {
		 this.key = value.getName()+value.getGender();
		 this.name = value;
		 this.status = status;
		 this.maxHeap = new Heap<Frecuency>(size);
	} 
	
	public String getKey() {
		return key;
	}
 
	public Name getName(){
		return name;
	}
 
	public int getStatus() {
		return status;
	} 
 
	public void setStatus(int status) {
		this.status= status;
	} 
	
	public void setDeleteStatus() {
		setStatus(2);
	}

	public Heap<Frecuency> getMaxHeap() {
		return maxHeap;
	}

	public void setMaxHeap(Heap<Frecuency> maxHeap) {
		this.maxHeap = maxHeap;
	}

	@Override
	public String toString() { 
		System.out.println("key=" + key + ", name=" + name + ", status=" + status + ", maxHeap="   ); 
		 maxHeap.print(); 
		 return ""; 
	}

	public int compareTo(HashNode hashNode) {  
		return this.getMaxHeap().getHeapTable()[1].getFrecuency()-hashNode.getMaxHeap().getHeapTable()[1].getFrecuency();
	} 
	
	public boolean write(BufferedWriter out,Frecuency frec){ 
	    try {
	    	if(this != null&& this.getMaxHeap().search(frec) != null) {
	    		out.write(this.getName().getName()+","+ this.getName().getGender() +","+ this.getMaxHeap().search(frec).getFrecuency()  +"\n");
	    		return true;
	    		}
	    	} catch (IOException e) { 
			e.printStackTrace();
		} 
	    return false;
	}
}