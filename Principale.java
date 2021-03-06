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
/*
Welcome to the Principale class ! This class is the playmaker of the project because it contains methods for:
*database connection
*tables creation and suppression
*adding information into specific tables
*perform some requests on the tables


*/
public class Principale {
    
    
    
    //Let's implement the database
    
    //Creation of the database and connection
    //Our database will hold 5 tables : 1 for Servers, and 4  for the different Log child classes
    
    //To interact with our database via Java we use the JavaDataBaseConnection in SQLite
public void createNewDatabase(String dbName) {
    
    String link="jdbc:sqlite:"+dbName;         
    try(Connection connex = DriverManager.getConnection(link)){ //create a connection object that we'll be using later
       if(connex != null){
           DatabaseMetaData meta = connex.getMetaData();
           System.out.println("The driver name is "+meta.getDriverName());
           System.out.println("Database successfully created !");
       }
       
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
}
public void connectToDatabase(){
        Connection conn = null;
        try {
            
            String url = "jdbc:sqlite:test.db";//We precise the path of a database that already exists
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
        String url = "jdbc:sqlite:test.db";//We precise the path of a database that already exists
        Connection connex = null;
        try {
            connex = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connex;
    }
public void disconnect(Connection c){//this function takes a connection object as a parameter and closes the connection
    try{
        c.close();
    }catch(SQLException e){
        System.out.println(e.getMessage());

    }
}

/*
The following function is to create a servers table if it doesn't exist yet
We'll have 3 fields : id, url and serverName
Since it is a request, we have to prepare it first before executing it
*/
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

/*
Now we create a function to add a new server to the table with a insert into request
*/
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

/*
Now we create a function to remove a server from the table with a delete from request
*/
public void deleteServer(String url) {
        String sql = "DELETE FROM servers WHERE url = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, url);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

/*
This function will be used in our firstview to display the available servers
We save them in an arraylist of server objects and we'll display elements from that arraylist in the firstview
*/
public ArrayList<Server> printServers(){
        ArrayList<Server> serversList = new ArrayList<>();
        String sql = "SELECT url, serverName FROM servers";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set and add each element to the arraylist using the server class constructor
            while (rs.next()) {
                serversList.add(new Server(rs.getString("url"), rs.getString("serverName")));
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return serversList;
    }
/*
Next we are creating functions for every type of log child class
One to create the table and another one to insert data into it


*/
//Function to create a table for apache log
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

//Fonction permettant d'inserer des informations dans la table du Log Squid
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

 	//Function to create ssh log table
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
  
  
  /*public void view3requestMethodOnSquid(String param, String valeur){
      String sql = ("SELECT * FROM squidLog WHERE "+param+" = '"+valeur+"'");
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
             System.out.println("remoteHost \t\t dateExacte \t\t\t\t\t\t url \t\t\t\t\t\t\t\t\t peerHost \t\t bytes \t\t contentType \t\t\t duration \t\t requestMethod \t\t status");
             System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
             
            // loop through the result set
            while (rs.next()) {
                System.out.print(rs.getString("remoteHost")+"\t\t");
                System.out.print(rs.getString("dateExacte")+"\t\t");
                System.out.print(rs.getString("url")+"\t\t");
                System.out.print(rs.getString("peerHost")+"\t\t");
                System.out.print(rs.getString("bytes")+"\t\t");
                System.out.print(rs.getString("contentType")+"\t\t");
                System.out.print(rs.getString("duration")+"\t\t");
                System.out.print(rs.getString("requestMethod")+"\t\t\t");
                System.out.print(rs.getString("status")+"\t\t");
                System.out.println();
                
                
                
                
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
  }*/
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Principale pl;
        pl= new Principale();
        pl.createNewDatabase("test.db");
        //System.out.println("Which database would you like to connect to ?");
        //String db = input.nextLine();
        pl.connectToDatabase();
        /*pl.createNewTableServer();
        pl.createNewTableSquid();
        pl.createNewTableApache();
        pl.createNewTableSamba();
        pl.createNewTableSSH();
        */
        //pl.insertServer("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","serveur web");
        //pl.view3requestMethodOnSquid("requestMethod", "HEAD");
        /*ArrayList<Server> l = new ArrayList<Server>(pl.printServers());
        for (Server srv : l){
            System.out.println(srv.serverName);
        }*:
        /*SquidLogs sl;
        sl=new SquidLogs(pl);
        LogApache2 apache;
        apache=new LogApache2(pl);
        LogSamba smb;
        smb=new LogSamba(pl);
        SshLog ssh;
        ssh=new SshLog(pl);
        //sl.squidProcess("exemple.squid.access.log.txt");
        smb.sambaProcess("exemple.samba.host.log.txt");*/
        
        
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
