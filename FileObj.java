import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FileObj {
	SimpleDateFormat f = new SimpleDateFormat("yyy");
	private java.util.Date year;
	private File file; 
	
	public FileObj() {
		
	}
	
	public FileObj(File file,  String year) {
		this.setFile(file);
		setYear(year);
	}

	public void setYear(String year) {
		try {
			this.year = f.parse(year);
		} catch (ParseException e) { 
			e.printStackTrace();
		} 
	}

	public java.util.Date getYear() {
		return year;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}  
	
	//To string
	@Override
	public String toString() { 
		return "FileObj [year=" + f.format(year.getTime()) + ", file="+file.getName() + "]";
	}

	public boolean contains(String year2) {
		boolean answer = false;
		try {
			java.util.Date yearrr = this.f.parse(year2); 
		if(this.getYear().equals(yearrr))
			answer= true;
		else
			answer= false;
		} catch (ParseException e) { 
			e.printStackTrace();
		}
		return answer;
	}
}
