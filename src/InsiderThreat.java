import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InsiderThreat {
	
	private Tree users;
	
	public static void main (String[] args) {
		InsiderThreat it =  new InsiderThreat();
		/*
		 * Especificações dos arquivos:
		 * "ldap.csv"               [ nome_usuario, id_usuario, dominio, email, cargo ]
		 * "http-sumarizado.csv"    [ id_acesso, data, DTAA/id_usuario, pc, site ]
		 * "device.csv"             [ id_acesso, data, DTAA/id_usuario, pc, atividade ] atividade = (Connect or Disconnect)
		 * "logon-sumarizado.csv"   [ id_acesso, data, DTAA/id_usuario, pc, atividade ] atividade = (Logon or Logoff)
		 * "logon-completo.csv"     [ id_acesso, data, DTAA/id_usuario, pc, atividade ] atividade = (Logon or Logoff)
		 */
		 
		it.readUsersFile("ldap.csv");
		it.readLogFile("http-sumarizado.csv");
		it.readLogFile("device.csv");
		it.readLogFile("http-sumarizado.csv");
		it.readLogFile("logon-sumarizado.csv");
		it.readLogFile("logon-completo.csv");
		
		
		/*
		 * cobaias
		 * AAR0508	http
		 * ABB0272	device
		 */

		User cobaia = it.users.search("ABB0272");
		cobaia.printUser();
		
		
	}
	
	
	public InsiderThreat() {
		this.users = new Tree();
	}
	
	/*
	 * adiciona um usuario na arvore
	 */
	private void createProfile(User user) {
		this.users.insertUser(user);
	}
	
	/*
	 * Lê o arquivo de usuários(ldap.csv) e os adiciona na árvore
	 */
	private void readUsersFile(String csvFile) {
		
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader("../files/" + csvFile));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] userData = line.split(",");
				User user = new User(userData[0], userData[1], userData[2], userData[3], userData[4]); 
				this.createProfile(user);
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
	
   /*
	* Lê o arquivo log e joga as informações no usuário correspondente
	*/
	private void readLogFile(String csvFile) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("../files/" + csvFile));
			String line = "";
			while ((line = br.readLine()) != null) {
				
				String[] logData = line.split(",");
				
				String userId = logData[2].split("/")[0];
				
				/* gambiarra */
				if (logData[2].split("/").length > 1) {
					userId = logData[2].split("/")[1];
				}
				
				User currentUser = this.users.search(userId);
				
				if (currentUser != null) {
					currentUser.addHistogram(logData);
				}
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
	
	private void viewProfiles() {}
	
	private void detectAnomaly() {}
	
	private void compareProfiles() {}
}