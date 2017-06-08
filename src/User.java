import java.util.ArrayList;

public class User {
    
    private String name;
    private String id;
    private String domain;
    private String email;
    private String role;
    private ArrayList<Histogram> histogram;
    
    /*
     * não definido
     */
    public Note noteDay;
    public Note noteBef;
    public Note noteSusp;
    
    public User(String name, String id, String domain, String email, String role) {
        this.name = name;
        this.id = id;
        this.domain = domain;
        this.email = email;
        this.role = role;
        
        this.histogram = new ArrayList();
    }
    
    public void addHistogram(String[] data) {
        Histogram h = new Histogram(data[0], data[1], data[3], data[4]);
        this.histogram.add(h);
    }
    
    public void printHistogram() {
        for(Histogram h : this.histogram) {
            h.print();
        }
    }
    
    public void printUser() {
        System.out.println("--------------User-------------");
        System.out.println("Nome: " + this.getName() );
        System.out.println("ID: " + this.getId() );
        System.out.println("Dominio: " + this.getDomain() );
        System.out.println("Email: " + this.getEmail() );
        System.out.println("Cargo: " + this.getRole() );
        System.out.println("Histograma:");
        this.printHistogram();
    }
    
    public String getName() {   return this.name;}
    public String getId() {     return this.id;}
    public String getDomain() { return this.domain;}
    public String getEmail() {  return this.email;}
    public String getRole() {   return this.role;}
    
}