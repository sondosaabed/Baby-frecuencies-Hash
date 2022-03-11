import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat; 

public class Frecuency implements Comparable<Frecuency>{
	SimpleDateFormat f = new SimpleDateFormat("yyy");
	private java.util.Date year;
	private int frecuency; 
	
	public Frecuency() {
		
	} 
	
	public Frecuency(int frecuency, String year) { 
		this.frecuency = frecuency;
		setYear(year);
	} 
	
	public java.util.Date getYear() {
		return year;
	}

	public void setYear(String year) {
		try {
			this.year = f.parse(year);
		} catch (ParseException e) { 
			e.printStackTrace();
		} 
	}

	public int getFrecuency() {
		return frecuency;
	}

	public void setFrecuency(int frecuency) {
		this.frecuency = frecuency;
	} 
 
	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("yyy");
		return "Frecuency [year=" + format.format(year.getTime()) + ", frecuency=" + frecuency + "]";
	}

	@Override
	public int compareTo(Frecuency o) { 
		return this.getFrecuency()-o.getFrecuency();
	}
}
