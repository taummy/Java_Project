/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.lang.String;
import server.Server;
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
        System.out.println("Serveur ajoute!");
        
        
    }
        

    //}

    //public  void Suppression(String link, String name){
        

    //}
    public static void main(String[] args) {
        //Server web = new Server("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","serveur web");
        //Server.listlogs(web.url);
        Ajout("http://iutsa.unice.fr/~mgautero/ext/dut/M4210/logs/","web");
        for (Server s: servs ){
            System.out.println(s.serverName);
            System.out.println(s.url);

        }
        //System.out.println(Arrays.toString(servs));
        //System.out.println(x);

    }
    
    
}
