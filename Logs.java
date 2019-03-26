/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author freddy
 */
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class Logs {
    
        public String addrIP;
	public String serv;//="http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/";
	private Principale princ;
        public Logs(Principale p){
        princ=p;
        }  
	 //Default constructor
	public Logs() {
		this.addrIP="";
                this.serv="";
	}
	//Constructor with parameters
	public Logs(String leServeur) {
		this.serv=leServeur;
	}
        
	//Accesseurs\\
    
    //Setteurs
	public void setAddrIP(String leAddrIP) {
		this.addrIP=leAddrIP;
	}
	//Getteurs
	public String getAddrIP() {
		return addrIP;
	}
	//Creation of a .txt file 
	public  void createLogsFiles (String url) {
		 if ( (url==null) )
		    {
		      System.err.println( "\nUsage: java JGet [urlToGet]" );
		      System.exit(1);
		    }

		    URL u;
		    InputStream is = null;
		    DataInputStream dis;
		    String s;
		    try
		    {
		      u = new URL(this.serv+url);
		      is = u.openStream();
		      dis = new DataInputStream(new BufferedInputStream(is));
		        File ff=new File(url+".txt"); // define the arborescence
				ff.createNewFile();
				FileWriter ffw=new FileWriter(ff);
		      while ((s = dis.readLine()) != null)
		      {
		      
				ffw.write(s);  // Write a line in the resultat.txt file
				ffw.write("\n"); // force the line return 
				
		      }
		      ffw.close(); // fermer le fichier à la fin des traitements
		    }
		    catch (MalformedURLException mue)
		    {
		      System.err.println("Ouch - a MalformedURLException happened.");
		      mue.printStackTrace();
		      System.exit(2);
		    }
		    catch (IOException ioe)
		    {
		      System.err.println("Oops- an IOException happened.");
		      ioe.printStackTrace();
		      System.exit(3);
		    }
		    finally
		    {
		      try{
		        is.close();
		      }
		      catch (IOException ioe){
		      }
		    }
	}
        




        public static void main(String[] args) {
    // TODO code application logic here
            /*
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter the log file you want to save: ");
            String log=input.nextLine();
            //String url="exemple.apache2.access.log";
*/          Logs lg;
            lg= new Logs("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/");
            lg.createLogsFiles("exemple.apache2.access.log");
            //lg.createLogsFiles("exemple.linux.auth.sshd.log");
            //lg.createLogsFiles("exemple.samba.host.log");
            //lg.createLogsFiles("exemple.squid.access.log");



        }

    
}
