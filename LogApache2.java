/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author irfan
 */
import java.util.*;
import java.io.*;
import java.text.*;

public class LogApache2 extends Logs{
	
	//variable privee
	public String remoteHost; // @IP of client
	public String laDate;
	public String leTemps; // the date and time the request was received.
	public String username; // user name determined by HTTP authentication
	public String identity; // the identity f the user determined by identd
	public String requestType; // request line from the client (GET or HEAD / HTTP/1.0)
	public String codeStatus; // the status code sent from the server to the client (200, 206, 401, 403, 404)
	public String sizeResponse; // the size of the response to the client (in bytes)
	public String refererUrl; // the URL of the page from which this request was initiated) if any is present, and "-" otherwise
	public String userAgent; // the browser identification string
	public String lineRead;
	public String line;
	public String[] chaine1, chaine2;
	public String[] splitLine, tabC1;
	private Principale princ;
        public LogApache2(Principale p){
        princ=p;
        }
        
        public LogApache2(){
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
            lineRead="";
        }
	
	
	public void apacheProcess(String file) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){ //On utilise BufferedReader afin de lire le fichier
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
				codeStatus=chaine2[2].split("\\s+")[1];
				sizeResponse=chaine2[2].split("\\s+")[2];
				refererUrl=chaine2[3];
				userAgent=chaine2[5].split("\\s+")[0];
				
				//Add into apacheLog table in database
				princ.insertApache(laDate, leTemps, username, identity, requestType, codeStatus, sizeResponse, refererUrl, userAgent);
				lineRead= reader.readLine();
			}
			
					
		}
		
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	//testons notre methode apacheProcess
	public static void main(String[] args) {
        Principale p;
        p= new Principale();
        LogApache2 apach;
        apach=new LogApache2(p);
        apach.apacheProcess("exemple.apache2.access.log.txt");
    }
	
	
}

