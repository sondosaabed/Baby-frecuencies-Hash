
public class Name implements Comparable<Name> {
	
	private String name;
	private char gender;

	//private 
	
	public Name() {
		
	}

	public Name(String line ){
		//Sussan,F,6  
		int cuma1 = line.indexOf(','); 
		name =  (line.substring(0,cuma1));
		gender = line.charAt(cuma1+1 );   
	}

	//Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	} 

	public void setGender(char gender) {
		this.gender = gender;
	} 

	@Override
	public String toString() {
		return "Name [name=" + name + ", gender=" + gender + "]";
	}  
	
	@Override
	  public int compareTo(Name o) {
	    int i = this.name.compareToIgnoreCase( o.name);
	    if (i != 0)
	      return i;  
	    if ( getGender()!=o.getGender())
	      return i;
		return i;  
	  }
}
