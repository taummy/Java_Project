/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 * @author irfan
 * @author tom
 */
import java.util.*;
import java.io.*;
import java.text.*;

public class SshLog extends Logs{
	
	    //----------------------------------------------------------//
      	//  Start creating a few objects and variables we'll need.  //
      	//--------------------------------------------------------- //
	public String laDate; //Date 
	public String leTemps; //Running time
	public String nomServer; //Server name
	public String typeConnection; //Type of connection sshd["port number"]
	public String session; //Activity while connected to server
	public String lineRead;
	public String line;
	public String[] chaine1, chaine2;
	public String[] splitLine, tabC1;
	
	//Creation of variable of class Princiaple
        public Principale princ;
        public SshLog(Principale p){
            princ=p;
        }
	
        //-------------------------------------------------------//
      	//  Creating constructor by default and with factors	//
      	//---------------------------------------------------- //
        public SshLog(){
            laDate="";
            leTemps="";
            nomServer="";
            numeroPort="";
            typeConnection="";
            session="";
            lineRead="";
            line="";
        }
	
	//Constructor with factors
        public SshLog(String laDate,String leTemps,String nomServer, String typeConnection,String session){
            this.laDate=laDate;
            this.leTemps=leTemps;
            this.nomServer=nomServer;
            this.typeConnection=typeConnection;
            this.session=session;
        }
	
        //--------------------------------------------------------------//
      	//  Creating accessors for each variables, will be used in GUI  //
      	//--------------------------------------------------------------//
        public String getDate(){
            return laDate;
        }
        public String getTemps(){
            return leTemps;
        }
        public String getNomServer(){
            return nomServer;
        }
        public String getTypeConnection(){
            return typeConnection;
        }
        public String getSession(){
            return session;
        }
        
        
	    //----------------------------------------------------------------------------//
      	//  Function for data parsing and the name of file as factor of the function  //
      	//--------------------------------------------------------------------------- //
public void sshProcess(String file) {
	
		//use BufferedReader to read file
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			lineRead=reader.readLine();
			while(lineRead !=null) {
				splitLine=lineRead.split("]: "); //split the line into two parts, for example: "Apr 13 06:47:17 kheops sshd[4866]: " and  "reverse mapping checking getaddrinfo for n37-137.berlin.snafu.de [195.21.37.137] failed - POSSIBLE BREAK-IN ATTEMPT!"
				chaine1= splitLine[0].split("\\s+"); //split 1st part of the line 
				laDate=chaine1[0] + chaine1[1]; //Extraction of the date of connection
				leTemps=chaine1[2];		//Running time
				nomServer=chaine1[3];		//Server name
				typeConnection=chaine1[4]+"]";	//Type of connection with port number
				session=splitLine[1];		//Activity while connection
				
				//Called a function in Principale.java using an object class Principale. Take all the data in variables as factors in insertSsh.
				princ.insertSsh(laDate, leTemps, nomServer, typeConnection, session);
				lineRead= reader.readLine();
			}
			
		}
		//show error message
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	//Try  sshProcess
	public static void main(String[] args) {
            Principale p;
        p= new Principale();
            SshLog ssh;
            ssh=new SshLog(p);
            ssh.sshProcess("exemple.linux.auth.sshd.log.txt");
	}

}
