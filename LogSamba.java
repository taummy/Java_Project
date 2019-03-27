/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogSamba extends Logs {
	
        //----------------------------------------------------------//
        //  Start creating a few objects and variables we'll need.  //
        //--------------------------------------------------------- //
	public String leDate ;
	public String lheure;
	public String IPConnectee ;
	public String service ;
	public String user;
	public String salle;
	public String state;

	
        private Principale princ;
        public LogSamba(Principale p){
            princ=p;
        }

	
	
	
	
        //-----------------------------------------------------//
        //  Creating constructor by default and with factors   //
        //---------------------------------------------------- //
	
	public LogSamba(){
		//super();
		this.leDate="";
		this.lheure="";
		this.IPConnectee="";
		this.service="";
		this.user="";
		this.salle="";
		this.state="";
	

	}
	
	public LogSamba(String leDate,String lheure,String IPConnectee,String service,String user,String salle,String state){
		this.leDate=leDate;
		this.lheure=lheure;
		this.IPConnectee=IPConnectee;
		this.service=service;
		this.user=user;
		this.salle=salle;
		this.state=state;
	}
	
	    //--------------------------------------------------------------//
        //  Creating accessors for each variables, will be used in GUI  //
        //--------------------------------------------------------------//
	public String getDate(){
		return leDate;
	}
    public String getHeure(){
        return lheure;
    }
    public String getUser(){
        return user;
    }
	public String getIP(){
		return IPConnectee;	
	}
	public String getService(){
		return service;	
	}
	public String getSalle(){
		return salle;	
	}
	public String getState(){
		return state;
	}
	
	    //----------------------------------------------------------------------------//
      	//  Function for data parsing and the name of file as factor of the function  //
      	//--------------------------------------------------------------------------- //
	

	        	
	 public static  void sambaProcess(String file){
	        
	        try(BufferedReader reader = new BufferedReader(new FileReader(file))){//on cree un objet BufferedReader appele reader pour lire un fichier de log qui sera passe en parametre
	            
	        	String[] splitLine;
	        	String[] splitLine1;
	        	String properService;
	        	String properUser;
	        	String properSalle;
	        	String properState;
	        //	PrintWriter writer = new PrintWriter("/home/rtel/etu/rt2017/bh617745/M4210/JavaProject/src/test", "UTF-8");
	        	boolean wordFinderResult;
	        	
	            String lineRead= reader.readLine();
	            String exampleword;
	            while (lineRead != null){
	            	splitLine=lineRead.split("\\s+");//Starting from this line, you need to see the log file to fully understand the code.
	            	if(splitLine.length>2 && splitLine.length<8){ // In this line, I chose lines having between 2 or 8 words so that the line that i take are the ones that are important
	           
	            		wordFinderResult=wordFinder("0.?",splitLine[2]); // In this line i only chose lines which have output 1 as their exit code as only those lines have important data for me
	            			if(wordFinderResult){
	            				lineRead= reader.readLine(); // If exit code is 0, we skip the line as it is not important

	            			}
	            			else{
	                		

// In the next part

	            			
	            				if(wordFinder("\\[.+/.+/.+\\]",(lineRead))){//In this line, Only lines with exit code 1 and also have the format "[xx/xx/xxxx/]" will be taken in
	            					StringBuilder sb = new StringBuilder(splitLine[0]); // We use string builder to clean up the string that we have (removing brackets)
	            					StringBuilder sb1 = new StringBuilder(splitLine[1]);
	            					System.out.println(splitLine[0]);
	            					System.out.println(splitLine[1]);

	            					sb.deleteCharAt(0); //delete brackets
	            					sb1.deleteCharAt(8);
	            					String properYear = sb.toString();
	            					String properTime = sb1.toString();
		                		
			                
	            					lineRead= reader.readLine();
	            					
	        	            		wordFinderResult=wordFinder("rt[1-9].+",lineRead); // Find out if the line starts with rt to find out if it containts the room/salle

	            					if(wordFinderResult){
	            						splitLine=lineRead.split("\\s+"); //seperate by space
	            						properSalle=splitLine[1];
	            			
		        	            		wordFinderResult=wordFinder("closed",splitLine[3]); //now we have two types of line to pick, lines with "closed" and connect, each of them have different array indexes in case i want to take them as a variable

	    	    	            		splitLine1=splitLine[2].split(":"); //take the ip address
	    	    	                	String properIP = splitLine1[3].substring(0, splitLine1[3].length() - 1); //clean up the form of ip address



	    	    	                	if(wordFinderResult){//if line contains the word "closed", array indexes 3 and 7 is important
	    	    	                		properState=splitLine[3];
	    	    	                		properUser="";
	    	    	                		properService=splitLine[7];
		    		              
	    	    	       
	    	    	                	}
	    	    	                	else{ //if not (meaning it contains connect) 3,6,10 is taken
	    	    	                		properState=splitLine[3];
	    	    	                		properUser=splitLine[6];
	    	    	                		properService=splitLine[10];
		    		                	
	    	    	            		
	    	    	            	}
		    	                	LogSamba firstlog = new LogSamba(properYear,properTime,properIP,properService,properUser,properSalle,properState);
		    		              //  System.out.println("The user "+firstlog.user+" / "+firstlog.IPConnectee+" "+firstlog.state+" son connection au service "+ firstlog.service+" au salle "+firstlog.salle+" au date "+" "+firstlog.leDate+" "+firstlog.lheure);
		    	            	//	writer.println("The user "+firstlog.user+" / "+firstlog.IPConnectee+" "+firstlog.state+" son connection au service "+ firstlog.service+" au salle "+firstlog.salle+" au date "+" "+firstlog.leDate+" "+firstlog.lheure);
	    	                		}
	    	                }
	            	
	                	}
	            	}
	            	else{
		                lineRead= reader.readLine();

	            	}
	                lineRead= reader.readLine();
	                }
	            
	                  
	            	 

		           
		          
        		//writer.close();
  
	            
	        }
	        catch(IOException e){
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public static boolean wordFinder(String pattern,String word){
		 Pattern p = Pattern.compile(pattern);
		 Matcher m = p.matcher(word);
		 if(m.find()){
			 return true;
		 }
		 else{
			 return false;
		 }
		 
	 }
	    public static void main(String[] args) {//testons notre methode squidProcess
                Principale p;
                p= new Principale();
	        LogSamba samba;
                samba=new LogSamba(p);
                samba.sambaProcess("exemple.samba.host.log.txt");
	}
}


