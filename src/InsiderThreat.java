import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InsiderThreat {
	
	private Tree users;
	
	public static void main (String[] args) {
		InsiderThreat it =  new InsiderThreat();
		 
		it.readUsersFile("ldap.csv");
		it.readLogFile("logon-sumarizado.csv");
		it.readLogFile("http-sumarizado.csv");
		it.readLogFile("device.csv");
		
	}
	
	/*
	 * Construtor
	 */
	public InsiderThreat() {
		this.users = new Tree();
	}
	
	/*
	 * Lê o arquivo de usuários e os adiciona na árvore
	 * Segue o padrão: [ nome_usuario, id_usuario, dominio, email, cargo ]
	 */
	private void readUsersFile(String csvFile) {
		
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader("../files/" + csvFile));
			String line = "";
			
			// Traduz cada linha do arquivo para um objeto User e insere na Tree
			while ((line = br.readLine()) != null) {
				String[] userData = line.split(",");
				User user = new User(userData[0], userData[1], userData[2], userData[3], userData[4]); 
				this.users.insertUser(user);
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
	* Segue o padrão: [ id_acesso, data, DTAA/id_usuario, pc, atividade ]
	*/
	private void readLogFile(String csvFile) {
		
		BufferedReader br = null;
		try {
			
			/* Lê o arquibo linha por linha e armazena em line */
			br = new BufferedReader(new FileReader("../files/" + csvFile));
			String line = "";
			while ((line = br.readLine()) != null) {
				
				/* Separa a linha pela vírgula em um array */
				String[] logData = line.split(",");
				
				/* Separa a string "DTAA/" da id usuários  */
				String userId = logData[2].split("/")[0];
				if (logData[2].split("/").length > 1) {
					userId = logData[2].split("/")[1];
				}
				
				/* Só adiciona as informações ao usuário se tiver sido encontrado na árvore */
				User currentUser = this.users.searchById(userId);
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