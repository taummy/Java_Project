/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.net.*;
import java.io.*;
/**
 *
 * @author freddy
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    //public String addIP;
    public String url;
    public String serverName;
    //constructeur par defaut 
    public Server(){
        
        url="unknown";
        serverName="Unknown";
    }
    //constructeur avec tous les parametres
    public Server(String link, String name){
        url=link;
        serverName=name;
    }
    //accessseurs
    public void setIpAdd(String newurl){
        url=newurl;
        
    }
    public void setServerName(String newName){
        serverName=newName;
    }
    public String getUrl(){
        return url;
    }
    public String getServerName(){
        return serverName;
    }
       
     public static void listlogs (String url) {
        

      //-----------------------------------------------------//
      //  Step 1:  Start creating a few objects we'll need.
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
            System.out.println(s);
         }

      } catch (MalformedURLException mue) {

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

    
}
        
    
    
        
    public static void main(String[] args) {
        // TODO code application logic here
        listlogs("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/");
    }
    
}
