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
	
	public String laDate; //la date
	public String leTemps; // le temps d'execution
	public String nomServer; //le nom du serveur
	public String numeroPort; //le numero du port
	public String typeConnection; 
	public String session;
	public String lineRead;
	public String line;
	public String[] chaine1, chaine2;
	public String[] splitLine, tabC1;
        public Principale princ;
        public SshLog(Principale p){
            princ=p;
        }
        
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
	
public void sshProcess(String file) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))){
			lineRead=reader.readLine();
			while(lineRead !=null) {
				splitLine=lineRead.split("]: ");
				chaine1= splitLine[0].split("\\s+");
				laDate=chaine1[0] + chaine1[1];
				leTemps=chaine1[2];
				nomServer=chaine1[3];
				typeConnection=chaine1[4]+"]";
				session=splitLine[1];
				
				//Ajout dans la table sshLog
				princ.insertSsh(laDate, leTemps, nomServer, typeConnection, session);
				lineRead= reader.readLine();
			}
			
		}
		
		catch(IOException e){
            System.out.println(e.getMessage());
        }
	}
	
	//testons notre methode sshProcess
	public static void main(String[] args) {
            Principale p;
        p= new Principale();
            SshLog ssh;
            ssh=new SshLog(p);
            ssh.sshProcess("exemple.linux.auth.sshd.log.txt");
	}

}