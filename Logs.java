/**
 * @author irfan
 */

import java.io.*;
import java.net.*;
import java.util.*;


public class Logs {
	public String addrIP;
	public static final String ST="http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/";
	
	 //constructeur par defaut 
	public Logs() {
		this.addrIP="unknown";
	}
	//constructeur avec tous les parametres
	public Logs(String leAddrIP) {
		this.addrIP=leAddrIP;
	}
	
	//accesseurs en lecture et ecriture
	public void setAddrIP(String leAddrIP) {
		this.addrIP=leAddrIP;
	}
	
	public String getAddrIP() {
		return addrIP;
	}
	
	public static void createLogsFiles (String url) {
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
		      u = new URL(ST+url);
		      is = u.openStream();
		      dis = new DataInputStream(new BufferedInputStream(is));
		        File ff=new File(url+".txt"); // définir l'arborescence
				ff.createNewFile();
				FileWriter ffw=new FileWriter(ff);
		      while ((s = dis.readLine()) != null)
		      {
		      
				ffw.write(s);  // écrire une ligne dans le fichier resultat.txt
				ffw.write("\n"); // forcer le passage à la ligne
				
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
	//String url="exemple.apache2.access.log";
	Scanner input = new Scanner(System.in);
        System.out.println("Please enter the log file you want to save: ");
        String url=input.nextLine();
	createLogsFiles(url);
	}

}

