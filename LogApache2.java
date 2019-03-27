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
	
	    //----------------------------------------------------------//
        //  Start creating a few objects and variables we'll need.  //
        //--------------------------------------------------------- //
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
	public String[] chaine1, chaine2;
	public String[] splitLine, tabC1;
	
	//Creation of variable of class Princiapale
	private Principale princ;
        public LogApache2(Principale p){
        princ=p;
        }
        
	    //-------------------------------------------------------//
      	//  Creating constructor by default and with factors	//
      	//---------------------------------------------------- //
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
        
	
        public LogApache2(String date, String temps, String username, String identity, String requestType, String codeStatus, String sizeResponse, String refererUrl, String userAgent){
            this.laDate=date;
            this.leTemps=temps;
            this.username=username;
            this.identity=identity;
            this.requestType=requestType;
            this.codeStatus=codeStatus;
            this.sizeResponse=sizeResponse;
            this.refererUrl=refererUrl;
            this.userAgent=userAgent;
            
        }
	
	    //--------------------------------------------------------------//
      	//  Creating accessors for each variables, will be used in GUI  //
      	//--------------------------------------------------------------//
        public String getLaDate(){
            return laDate;
        }
        public String getLeTemps(){
            return leTemps;
        }
        public String getUsername(){
            return username;
        }
        public String getIdentity(){
            return identity;
        }
        public String getRequestType(){
            return requestType;
        }
        public String getCodeStatus(){
            return codeStatus;
        }
        public String getSizeResponse(){
            return sizeResponse;
        }
        public String getRefererUrl(){
            return refererUrl;
        }
        public String getUserAgent(){
            return userAgent;
        }
        
	    //----------------------------------------------------------------------------//
      	//  Function for data parsing and the name of file as factor of the function  //
      	//--------------------------------------------------------------------------- //
	
	public void apacheProcess(String file) {
		
		//Use of BufferedReader to read the file
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			lineRead=reader.readLine();
			while(lineRead !=null) {
				splitLine=lineRead.split("]");
				chaine1=splitLine[0].split("\\s+");
				identity=chaine1[1]; //identity NO GOOD IDENTITY OF WHAT ???????
				username=chaine1[2]; //Retrival of the username
				laDate=((chaine1[3].split(":"))[0]).substring(1); //Retrival of the date
				leTemps=((chaine1[3].split("/"))[2]).substring(5) + " " + chaine1[4]; //Retrival of the time with the good time zone (+UTC)
				chaine2=splitLine[1].split("\"");
				requestType=chaine2[1]; //type of request 
				codeStatus=chaine2[2].split("\\s+")[1]; //status of code
				sizeResponse=chaine2[2].split("\\s+")[2]; //size of response
				refererUrl=chaine2[3]; 			//referer url if it exists
				userAgent=chaine2[5].split("\\s+")[0];	//type of browser used by the user
				
				//Add into apacheLog table in database
				//Called a function in Principale.java using an object class Principale. Take all the data in variables as factors in insertApache.
				princ.insertApache(laDate, leTemps, username, identity, requestType, codeStatus, sizeResponse, refererUrl, userAgent);
				lineRead= reader.readLine();
			}
			
					
		}
		//show error message
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	//Try apacheProcess
	public static void main(String[] args) {
        Principale p;
        p= new Principale();
        LogApache2 apach;
        apach=new LogApache2(p);
        apach.apacheProcess("exemple.apache2.access.log.txt");
    }
	
	
}

