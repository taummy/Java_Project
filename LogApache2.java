/**
 * @author irfan
 */
import java.util.*;
import java.io.*;
import java.text.*;

public class LogApache2 extends Logs{
	
	//variable privee
	public static String remoteHost; // @IP of client
	public static String laDate;
	public static String leTemps; // the date and time the request was received.
	public static String username; // user name determined by HTTP authentication
	public static String identity; // the identity f the user determined by identd
	public static String requestType; // request line from the client (GET or HEAD / HTTP/1.0)
	public static String requestFile; 
	public static String codeStatus; // the status code sent from the server to the client (200, 206, 401, 403, 404)
	public static String sizeResponse; // the size of the response to the client (in bytes)
	public static String refererUrl; // the URL of the page from which this request was initiated) if any is present, and "-" otherwise
	public static String userAgent; // the browser identification string
	public static String lineRead;
	public static String line;
	public static String[] chaine1, chaine2;
	public static String[] splitLine, tabC1;
	
	
	
	public static void apacheProcess(String file) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			lineRead=reader.readLine();
			while(lineRead !=null) {
				splitLine=lineRead.split("]");
				chaine1=splitLine[0].split("\\s+");
				remoteHost=chaine1[0]; //@IP du client
				identity=chaine1[1]; //identity
				username=chaine1[2];
				laDate=((chaine1[3].split(":"))[0]).substring(1); //la date
				leTemps=((chaine1[3].split("/"))[2]).substring(5) + " " + chaine1[4]; //le temps avec +UTC
				chaine2=splitLine[1].split("\"");
				requestType=chaine2[1];
				codeStatus=chaine2[2].split("\\s+")[0];
				sizeResponse=chaine2[2].split("\\s+")[1];
				refererUrl=chaine2[3];
				//System.out.println(leTemps);
				System.out.println(chaine2[3]);
				lineRead= reader.readLine();
			}
			
					
		}
		
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	public static void main(String[] args) {//testons notre methode apacheProcess
        apacheProcess("exemple.apache2.access.log.txt");
    }
	
	
}
