/**
 * @author irfan
 */
import java.util.*;
import java.io.*;
import java.text.*;

public class LogApache2 extends Logs{
	
	//variable privee
	private String remoteHost; // @IP of client
	private String laDate; //the date request was received
	private String leTemps; // the date and time the request was received.
	private String username; // user name determined by HTTP authentication
	private String identity; // the identity f the user determined by identd
	private String requestType; // request line from the client (GET or HEAD / HTTP/1.0)
	private String codeStatus; // the status code sent from the server to the client (200, 206, 401, 403, 404)
	private String sizeResponse; // the size of the response to the client (in bytes)
	private String refererUrl; // the URL of the page from which this request was initiated) if any is present, and "-" otherwise
	private String userAgent; // the browser identification string

	public LogApache2() {
		remoteHost="";
		laDate="";
		leTemps="";
		username="";
		identity="";
		requestType="";
		codeStatus="";
		sizeResponse="";
		refererUrl="";
		userAgent="";
	}
	
	//On creer une fonction pour extraire les informations du fichier
	public void apacheProcess(String file) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			String lineRead;
			String[] chaine1, chaine2;
			String[] splitLine;
			
			//variable pour recuperer les informations
			String remote, date, temps, user, id, req, code, size, referer, agent;
			
			lineRead=reader.readLine();
			while(lineRead !=null) {
				splitLine=lineRead.split("]");
				chaine1=splitLine[0].split("\\s+");
				
				remote=chaine1[0]; //@IP du client
				id=chaine1[1]; //identity
				user=chaine1[2];
				date=((chaine1[3].split(":"))[0]).substring(1); //la date
				temps=((chaine1[3].split("/"))[2]).substring(5) + " " + chaine1[4]; //le temps avec +UTC
				chaine2=splitLine[1].split("\"");
				req=chaine2[1];
				code=chaine2[2].split("\\s+")[1];
				size=chaine2[2].split("\\s+")[2];
				referer=chaine2[3];
				agent=chaine2[5].split("\\s+")[0];
				System.out.println(remote);
								
				//Add into apacheLog table in database
				Principale.insertApache(laDate, leTemps, username, identity, requestType, codeStatus, sizeResponse, refererUrl, userAgent);
				lineRead= reader.readLine();
			}					
		}
		
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	//testons notre methode apacheProcess
	public static void main(String[] args) {
		LogApache2 a1;
		a1= new LogApache2();
        a1.apacheProcess("exemple.apache2.access.log.txt");
        
    }
	
	
}
