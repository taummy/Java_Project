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

    	//-------------------------------------------------------//
      	//  Creating constructor by default and with factors	//
      	//---------------------------------------------------- //
	public Logs() {
		this.addrIP="";
                this.serv="";
	}

	public Logs(String leServeur) {
		this.serv=leServeur;
	}
        
  	    //--------------------------------------------------------------//
      	//  Creating accessors for each variables, will be used in GUI  //
      	//--------------------------------------------------------------//
	
	//Writing accessors 
	public void setAddrIP(String leAddrIP) {
		this.addrIP=leAddrIP;
	}
	//Reading accessors
	public String getAddrIP() {
		return addrIP;
	}

  	    //--------------------------------------------------------------//
      	//  Function used to create a local file of the log (in .txt)  	//
      	//--------------------------------------------------------------//
	
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
		        File ff=new File(url+".txt"); // Define name of the file 
				ff.createNewFile();
				FileWriter ffw=new FileWriter(ff);
		      while ((s = dis.readLine()) != null)
		      {
		      
				ffw.write(s);  // ffw will write a line in the text file as long as the s variable is not empty
				ffw.write("\n"); // next line in text
				
		      }
		      ffw.close(); // close the buffer so that we flush out all remaining text in the buffer
		    }
		    catch (MalformedURLException mue)
		    {
		      System.err.println("Ouch - a MalformedURLException happened."); // error if no internet connection/wrong url
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
