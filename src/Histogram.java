import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Histogram {
	
	public String id;
	public Date date;
	public String pc;
	public String action;
	
	public Histogram(String id, String date, String pc, String action) {
		this.id = id;
		this.pc = pc;
		this.action = action;
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			this.date = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void print() {
		/* Formato que a data será printada */
		SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		
		System.out.println(dateFormat.format(this.date) + " - " + this.id + " - " + this.pc + " - " + this.action);
	}
}