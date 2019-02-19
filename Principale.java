/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.lang.String;
import java.util.*;


/**
 *
 * @author freddy
 */
public class Principale {
    //public String url;
    //public String serverName;

    //constructeur sans paramètre
    //public Principale() {
    //    this.url = "";
    //    this.serverName = "";
    //}
    //constructeur avec paramètres
    //public Principale(String link, String name){
    //    this.url = link;
    //    this.serverName = name;
    //}

    //Accesseurs
    //GET

    //public String getUrl() {
    //    return url;
    //}

    //public String getServerName() {
    //    return serverName;
    //}
    //SET


    //public void setUrl(String link) {
    //    this.url = link;
    //}

    //public void setServerName(String name) {
    //    this.serverName = name;
    //}

    
    //public static void choix(String link){
        

    //}
    
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
        //Server web = new Server("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","serveur web");
        //Server.listlogs(web.url);
        Scanner input = new Scanner(System.in);
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
        
        

        
        

    }
    
    
}
