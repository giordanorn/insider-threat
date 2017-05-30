import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InsiderThreat {

    public static void main (String[] args) {
        InsiderThreat teste =  new InsiderThreat();
    }
    
    public void readFile() {
        BufferedReader br = null;
        try {
            String csvFile = "ldap.csv";
            String line = "";
            br = new BufferedReader(new FileReader("/files/" + csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] user = line.split(",");
                /*
                 * [0] = nome
                 * [1] = id
                 * [2] = dominio
                 * [3] = email
                 * [4] = cargo
                 */
                System.out.println(user[0] + " " + user[1] + " " + user[2] + " " + user[3] + " " + user[4]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void createProfiles() {
        
    }
    
    public void viewProfiles() {
        
    }
    
    public void detectAnomaly() {
        
    }
    
    public void compareProfiles() {
        
    }
}