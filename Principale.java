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
    //Our database will hold 5 tables : 1 for Servers, and 4  for the different Log child classes
public void createNewDatabase(String dbName) {
    
	//Creation of the database\\
    String link="jdbc:sqlite:"+dbName;         
    try(Connection connex = DriverManager.getConnection(link)){
       if(connex != null){
           DatabaseMetaData meta = connex.getMetaData();
           System.out.println("The driver name is "+meta.getDriverName());
           System.out.println("Database successfully created !");
       }
       
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
}
	//Connection to the Database\\
public void connectToDatabase(){
        Connection conn = null;
        try {
            
            String url = "jdbc:sqlite:test.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection established !");
            
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

public Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:test.db";
        Connection connex = null;
        try {
            connex = DriverManager.getConnection(url); //"DriverManager" will load the driver classes "dbc:sqlite:test.db"
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connex;
    }
	
	//Creation of new tables in the database\\
public void createNewTableServer(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS servers (\n"
                + "	id integer PRIMARY KEY NOT NULL,\n"
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
	//Add of new server to the database\\
public  void insertServer(String url, String serverName){
        String link= "jdbc:sqlite:test.db";
        String sql ="INSERT OR IGNORE INTO servers(url, serverName) VALUES(?,?)";
        try (Connection connex = connect();PreparedStatement pst = connex.prepareStatement(sql)) {
            pst.setString(1,url);
            pst.setString(2,serverName);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         
 }

	//Creation of the table for the Apache log with all the good rows\\
 public void createNewTableApache(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS apacheLog (\n"
                + "	id integer PRIMARY KEY NOT NULL,\n"
                + "	date text NOT NULL,\n"
                + "	temps text NOT NULL,\n"
                + "	username text NOT NULL,\n"
                + "	identity text NOT NULL,\n"
                + "	requestType text NOT NULL,\n"
                + "	codeStatus text NOT NULL,\n"
                + "	sizeResponse text NOT NULL,\n"
                + "	refererUrl text NOT NULL,\n"
                + "	userAgent text NOT NULL\n"
                + ");";
        try (Connection connex = DriverManager.getConnection(url);
                Statement st = connex.createStatement()) {
                st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}

//Function to insert information to Apache Log table
 public void insertApache(String date, String temps, String username, String identity, String requestType, String codeStatus, String sizeResponse, String refererUrl, String userAgent) {
	 String link= "jdbc:sqlite:test.db";
	 String sql = "INSERT OR IGNORE INTO apacheLog(date, temps, username, identity, requestType, codeStatus, sizeResponse, refererUrl, userAgent) VALUES(?,?,?,?,?,?,?,?,?)";
	 try(Connection connex = connect();PreparedStatement pst = connex.prepareStatement(sql)){
		 pst.setString(1, date);
		 pst.setString(2, temps);
		 pst.setString(3, username);
		 pst.setString(4, identity);
		 pst.setString(5, requestType);
		 pst.setString(6, codeStatus);
		 pst.setString(7, sizeResponse);
		 pst.setString(8, refererUrl);
		 pst.setString(9, userAgent);
                 pst.executeUpdate();
		 
	 }
	 catch (SQLException e) {
         System.out.println(e.getMessage());
     }
 }
   	//Creation of the table for the Squid log with all the good rows\\
  public void createNewTableSquid(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS squidLog (\n"
                + "	id integer PRIMARY KEY NOT NULL,\n"
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

//Function to insert information into Squid table
 public void insertSquid(String remoteHost, String dateExacte, String url,String peerHost, String bytes,String contentType, String duration,String requestMethod, String status){
        String link= "jdbc:sqlite:test.db";
        String sql ="INSERT OR IGNORE INTO squidLog(remoteHost,dateExacte,url,peerHost,bytes,contentType,duration,requestMethod,status) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection connex = connect();PreparedStatement pst = connex.prepareStatement(sql)) {
            pst.setString(1,remoteHost);
            pst.setString(2,dateExacte);
            pst.setString(3,url);
            pst.setString(4,peerHost);
            pst.setString(5,bytes);
            pst.setString(6,contentType);
            pst.setString(7,duration);
            pst.setString(8,requestMethod);
            pst.setString(9,status);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
         
 }
	//Creation of the table for the Samba log with all the good rows\\
 public void createNewTableSamba(){
	String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS sambaLog (\n"
              + " id integer PRIMARY KEY NOT NULL,\n"
              + " leDate text NOT NULL,\n"
              + " lheure text NOT NULL,\n"
              + " IPConnectee text NOT NULL,\n"
              + " service text NOT NULL,\n"
              + " user text NOT NULL,\n"
              + " salle text NOT NULL,\n"
              + " state text NOT NULL\n"
              + ");";
      try (Connection connex = DriverManager.getConnection(url);
              Statement st = connex.createStatement()) {
              st.execute(sql);
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
     
}
 	//Function to insert information into Samba table
 public void insertSamba(String leDate, String lheure,String IPConnectee, String service, String user,String salle, String state){
      String link= "jdbc:sqlite:test.db";
      String sql ="INSERT OR IGNORE INTO sambaLog(leDate,lheure,IPConnectee,service,user,salle,state) VALUES(?,?,?,?,?,?,?)";
      try (Connection connex = connect();PreparedStatement pst = connex.prepareStatement(sql)) {
          pst.setString(1,leDate);
          pst.setString(2,lheure);
          pst.setString(3,IPConnectee);
          pst.setString(4,service);
          pst.setString(5,user);
          pst.setString(6,salle);
          pst.setString(7,state);
          pst.executeUpdate();
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
       
}

 	//Creation of the table for the SSH log with all the good rows\\
  public void createNewTableSSH(){
        String url = "jdbc:sqlite:test.db";
        String sql = "CREATE TABLE IF NOT EXISTS sshLog (\n"
                + "	id integer PRIMARY KEY NOT NULL,\n"
                + "	date text NOT NULL,\n"
                + "	temps text NOT NULL,\n"
                + "	server text NOT NULL,\n"
                + "	typeConnection text NOT NULL,\n"
                + "	session text NOT NULL\n"
                + ");";
        try (Connection connex = DriverManager.getConnection(url);
                Statement st = connex.createStatement()) {
                st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}
   
  //Function to insert information into ssh table
  public void insertSsh(String date, String temps, String server, String typeConnection, String session) {
		 String link= "jdbc:sqlite:test.db";
		 String sql = "INSERT OR IGNORE INTO sshLog(date, temps, server, typeConnection, session) VALUES(?,?,?,?,?)";
		 try(Connection connex = connect();PreparedStatement pst = connex.prepareStatement(sql)){
			 pst.setString(1, date);
			 pst.setString(2, temps);
			 pst.setString(3, server);
			 pst.setString(4, typeConnection);
			 pst.setString(5, session);
                         pst.executeUpdate();
			 
		 }
		 catch (SQLException e) {
	         System.out.println(e.getMessage());
	     }
	 }



    
    
        

    //}

   /*
     public  void deleteServer(){   
        }*/
    
     //WE CAN ALSO TRY THE toArray() METHOD IF THIS ISN'T WORKING
    
    //}
	//\\
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Principale pl;
        pl= new Principale();
        pl.createNewDatabase("test.db");
        //System.out.println("Which database would you like to connect to ?");
        //String db = input.nextLine();
        pl.connectToDatabase();
        pl.createNewTableServer();
        pl.createNewTableSquid();
        pl.createNewTableApache();
        pl.createNewTableSamba();
        pl.createNewTableSSH();
        SquidLogs sl;
        sl=new SquidLogs(pl);
        LogApache2 apache;
        apache=new LogApache2(pl);
        LogSamba smb;
        smb=new LogSamba(pl);
        SshLog ssh;
        ssh=new SshLog(pl);
        //sl.squidProcess("exemple.squid.access.log.txt");
        smb.sambaProcess("exemple.samba.host.log.txt");
        
        
        // SquidLog sl;
        // sl = new SquidLog(this);
        // 
        
              
        
        /*
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
        
*/
        
        
        

        
        

    }
    
    
}
