/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import classes needed
package server;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author freddy
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    
    //Creation of variables 
    //public String addIP;
    public String url;		//url of the server		
    public String serverName;	//name of server determined by user
	
    //Creation of variable of class Princiaple
    private Principale princ; 
    public Server(Principale p){
        princ=p;
    }
	
    	//-------------------------------------------------------//
      	//  Creating constructor by default and with factors	//
      	//---------------------------------------------------- //
    public Server(){
        
        url="";
        serverName="";
    }
	
    //Constructor with factors
    public Server(String link, String name){
        url=link;
        serverName=name;
    }
	
  	 //--------------------------------------------------------------//
      	//  Creating accessors for each variables, will be used in GUI  //
      	//--------------------------------------------------------------//
    //Writing accessors
    public void setUrl(String newUrl){
        url=newUrl;
        
    }
    public void setServerName(String newName){
        serverName=newName;
    }
	
    //Reading accessors
    public String getUrl(){
        return url;
    }
    public String getServerName(){
        return serverName;
    }
       //Creating a String list
     public ArrayList<String> listlogs (String url) {
      ArrayList<String> logList = new ArrayList<String>();

        

      //-----------------------------------------------------//
      //  Step 1:  Start creating a few objects we'll need.  //
      //-----------------------------------------------------//

      URL u;
      InputStream is = null;
      DataInputStream dis;
      String s;

      try {

         //------------------------------------------------------------//
         // Step 2:  Create the URL.                                   //
         //------------------------------------------------------------//
         // Note: Put your real URL here, or better yet, read it as a  //
         // command-line arg, or read it from a file.                  //
         //------------------------------------------------------------//

         u = new URL(url);

         //----------------------------------------------//
         // Step 3:  Open an input stream from the url.  //
         //----------------------------------------------//

         is = u.openStream();         // throws an IOException

         //-------------------------------------------------------------//
         // Step 4:                                                     //
         //-------------------------------------------------------------//
         // Convert the InputStream to a buffered DataInputStream.      //
         // Buffering the stream makes the reading faster; the          //
         // readLine() method of the DataInputStream makes the reading  //
         // easier.                                                     //
         //-------------------------------------------------------------//

         dis = new DataInputStream(new BufferedInputStream(is));

         //------------------------------------------------------------//
         // Step 5:                                                    //
         //------------------------------------------------------------//
         // Now just read each record of the input stream, and print   //
         // it out.  Note that it's assumed that this problem is run   //
         // from a command-line, not from an application or applet.    //
         //------------------------------------------------------------//
         
         while ((s = dis.readLine()) != null) {
            
                    Boolean found = s.contains("log");
	            Boolean foundexemple = s.contains("exemple");
                    if(found && foundexemple){
                        String cutline[] = s.split("\"");
                        //System.out.println("\n");
                        //System.out.println(cutline[5]);
                        logList.add(cutline[5]);
                    }
                }
         }
	     
       //Show error message
       catch (MalformedURLException mue) {

         System.out.println("Ouch - a MalformedURLException happened.");
         mue.printStackTrace();
         System.exit(1);

      } catch (IOException ioe) {

         System.out.println("Oops- an IOException happened.");
         ioe.printStackTrace();
         System.exit(1);

      } finally {

         //---------------------------------//
         // Step 6:  Close the InputStream  //
         //---------------------------------//

         try {
            is.close();
         } catch (IOException ioe) {
            // just going to ignore this one
         }

      } // end of 'finally' clause
      return logList;
    
}
        
    
    
      
    public static void main(String[] args) {
        // TODO code application logic here
        /*Principale p;
        p= new Principale();
        p.insertServer("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","serveur web" );
*/
        //Server web = new Server("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","serveur web");
        Server srv;
        srv = new Server();
        srv.listlogs("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/");
        ArrayList<String> l = new ArrayList<String>(srv.listlogs("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/"));
        for (String log : l){
            System.out.println(log);
        }
    }
}
    

