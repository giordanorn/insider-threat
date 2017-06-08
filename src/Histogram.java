public class Histogram {
    
    public String id;
    public String date;
    public String pc;
    public String action;
    
    public Histogram(String id, String date, String pc, String action) {
        this.id = id;
        this.date = date;
        this.pc = pc;
        this.action = action;
    }
    
    public void print() {
        System.out.println(this.date + " - " + this.id + " - " + this.pc + " - " + this.action);
    }
}