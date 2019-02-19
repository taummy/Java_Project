/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.util.*;
import java.io.*;
/**
 *
 * @author freddy
 */
public class SquidLogs extends Logs {
    public static String time;
    public static String duration;
    public static  String remoteHost;
    public static String codeStatus; //code: la maniere dont la requete a ete satisfaite| status: le code du resultat HTTP
    public static String code;
    public static String status;
    public static String bytes;
    public static String requestMethod;
    public static String url;
    public static String peerStatusPeerHost;
    public static String peerStatus;
    public static String peerHost;
    public static String contentType;
    public static String lineRead;
    public static String[] splitLine;
    public static String line;
    public static void squidProcess(String file){
        
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                       
            lineRead= reader.readLine();
            while (lineRead != null){
                splitLine=lineRead.split("\\s+");
                time=splitLine[0];
                duration=splitLine[1];
                remoteHost=splitLine[2];
                codeStatus=splitLine[3];
                code=(codeStatus.split("/"))[0];
                status=(codeStatus.split("/"))[1];
                bytes=splitLine[4];
                requestMethod=splitLine[5];
                url=splitLine[6];
                //Le splitLine[7] n'est pas pris en compte car ce n'est qu'un tiret
                peerStatusPeerHost=splitLine[8];
                peerStatus=(peerStatusPeerHost.split("/"))[0];
                peerHost=(peerStatusPeerHost.split("/"))[1];
                contentType=splitLine[9];
                //affichage des informations
                line ="Un utilisateur s'est connecté avec l'adresse IP interne "+remoteHost+" à l'instant "+time+" et a demandé l'objet "+url+" hébergée par un serveur à l'adresse IP "+peerHost+". "+bytes+" octets de type "+contentType+" ont été échangés pendant une durée de "+duration+" ms. La méthode HTTP utilisée est "+requestMethod+" et le code HTTP retourné est "+status;
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file+".processed.txt",true))){
                    writer.write(line+"\n");
                }
                //System.out.println(line);
                lineRead= reader.readLine();
                
                
                
                    
                    
                    
                    //System.out.println(splitLine[i]);
                
                
                
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        squidProcess("exemple.squid.access.log.txt");
    }
    
}
