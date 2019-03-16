/**
 * @author irfan
 */
package server;
import java.util.*;
import java.io.*;
import java.text.*;

public class SshLog extends Logs{
	
	private String laDate; //la date
	private String leTemps; // le temps d'execution
	private String nomServer; //le nom du serveur
	private String typeConnection; 
	private String session;
	
	private String lineRead;
	private String line;
	private String[] chaine1, chaine2;
	private String[] splitLine, tabC1;
	
	public SshLog() {
		laDate="";
		leTemps="";
		nomServer="";
		typeConnection="";
		session="";
		
	}
	
//La fonction pour extraire les informations du fichier
public void sshProcess(String file) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			lineRead=reader.readLine();
			while(lineRead !=null) {
				splitLine=lineRead.split("]: ");
				chaine1= splitLine[0].split("\\s+");
				laDate=chaine1[1] +" "+chaine1[0];
				leTemps=chaine1[2];
				nomServer=chaine1[3];
				typeConnection=chaine1[4]+"]";
				session=splitLine[1];
				
				//Ajout dans la table sshLog
				Principale.insertSsh(laDate, leTemps, nomServer, typeConnection, session);
				lineRead= reader.readLine();
			}
			
		}
		
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	//testons notre methode apacheProcess
	public static void main(String[] args) {
		SshLog s1;
		s1 = new SshLog();
		s1.sshProcess("exemple.linux.auth.sshd.log.txt");
	}

}
