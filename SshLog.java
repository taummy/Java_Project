/**
 * @author irfan
 */
import java.util.*;
import java.io.*;
import java.text.*;

public class SshLog extends Logs{
	
	public static String laDate; //la date
	public static String leTemps; // le temps d'execution
	public static String nomServer; //le nom du serveur
	public static String numeroPort; //le numero du port
	public static String typeConnection; 
	public static String session;
	public static String lineRead;
	public static String line;
	public static String[] chaine1, chaine2;
	public static String[] splitLine, tabC1;
	
public static void apacheProcess(String file) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			lineRead=reader.readLine();
			while(lineRead !=null) {
				splitLine=lineRead.split(": ");
				chaine1= splitLine[0].split("\\s+");
				laDate=chaine1[0] + chaine1[1];
				leTemps=chaine1[2];
				nomServer=chaine1[3];
				typeConnection=chaine1[4];
				session=splitLine[1];
				
				//System.out.println(splitLine[1]);
				//System.out.println(chaine1[4]);
				lineRead= reader.readLine();
			}
			
		}
		
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	//testons notre methode sshProcess
	public static void main(String[] args) {
		sshProcess("exemple.linux.auth.sshd.log.txt");
	}

}
