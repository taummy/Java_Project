/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.lang.String;
import java.util.*;
import java.sql.*;



/**
 *
 * @author freddy
 */
public class Principale {
    
    //Let's implement the database
    
    //Creation of the database and connection
public static void createNewDatabase(String dbName) {
    
    String link="jdbc:sqlite:"+dbName;         
    try(Connection connex = DriverManager.getConnection(link)){
       if(connex != null){
           DatabaseMetaData meta = connex.getMetaData();
           System.out.println("The driver name is "+meta.getDriverName());
           System.out.println("Base de donnees cree avec succes !");
       }
       
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
}
public static void connectToDatabase(){
        Connection conn = null;
        try {
            
            String url = "jdbc:sqlite:test.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection a la base de donnees etablie !");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } /*finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
}
public static void createNewTableServer(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS servers (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	url text NOT NULL,\n"
                + "	serverName text NOT NULL\n"
                + ");";
        try (Connection connex = DriverManager.getConnection(url);
                Statement st = connex.createStatement()) {
                st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}

 /*public static void createNewTableApache(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS servers (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	url text NOT NULL,\n"
                + "	serverName text NOT NULL\n"
                + ");";
        try (Connection connex = DriverManager.getConnection(url);
                Statement st = connex.createStatement()) {
                st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
*/
   
  public static void createNewTableSquid(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS squidLog (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	remoteHost text NOT NULL,\n"
                + "	dateExacte text NOT NULL,\n"
                + "     url text NOT NULL,\n"
                + "     peerHost text NOT NULL,\n"
                + "     bytes text NOT NULL,\n"
                + "     contentType text NOT NULL,\n"
                + "     duration text NOT NULL,\n"
                + "     requestMethod text NOT NULL,\n"
                + "     status text NOT NULL\n"
                + ");";
        try (Connection connex = DriverManager.getConnection(url);
                Statement st = connex.createStatement()) {
                st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
 /* public static void createNewTableSamba(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS servers (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	url text NOT NULL,\n"
                + "	serverName text NOT NULL\n"
                + ");";
        try (Connection connex = DriverManager.getConnection(url);
                Statement st = connex.createStatement()) {
                st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}*/
  /*
  public static void createNewTableSSH(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS servers (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	url text NOT NULL,\n"
                + "	serverName text NOT NULL\n"
                + ");";
        try (Connection connex = DriverManager.getConnection(url);
                Statement st = connex.createStatement()) {
                st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}*/
    public static int  i=0;
    
    public static List<Server> servs = new ArrayList<Server>();
    
    public static  void Ajout(String link, String name){
        
        Server serveur = new Server(link,name);
        servs.add(serveur);
        System.out.println("Your new server "+name+" has been added successfully !");
        
        
    }
        

    //}

   
     public static  void Suppression(String para){
        for (int i=0;i<servs.size();i++){
            if(((servs.get(i)).url)==para || ((servs.get(i)).serverName)==para){
                servs.remove(i);
                System.out.println("Your server "+(servs.get(i)).serverName+" has been successfully removed");
            }
            return;
        
            
        }
    }
     //WE CAN ALSO TRY THE toArray() METHOD IF THIS ISN'T WORKING
    
    //}
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //createNewDatabase("test.db");
        //System.out.println("Which database would you like to connect to ?");
        //String db = input.nextLine();
        connectToDatabase();
        createNewTableServer();
        createNewTableSquid();
        
        //Server web = new Server("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","serveur web");
        //Server.listlogs(web.url);
        
        /*
        Ajout("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","web");
        System.out.println("Your available servers are :\n");
        for (Server s: servs ){
            System.out.println(s.serverName+" : "+s.url);
            
        }
        System.out.println("Please enter the url of the chosen server :\n");
        String url=input.nextLine();
        System.out.println("These are the logs available on the chosen server :\n");
        Server.listlogs(url);
        System.out.println(" Please choose the log you want to save and inspect");
        String log=input.nextLine();
        Logs.createLogsFiles(log);
        
        System.out.println("Let's test the delete method");
        System.out.println("Please enter the url or the name of the server you want to delete :");
        String para=input.nextLine();
        Suppression(para);
        System.out.println("Here is the new server list : ");
        for (Server s: servs ){
            System.out.println(s.serverName+" : "+s.url);
        }
*/
        
        
        

        
        

    }
    
    
}
