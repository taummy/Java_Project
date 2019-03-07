/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.util.*;
import java.io.*;
import java.text.*;
/**
 *
 * @author freddy
 */
public class SquidLogs extends Logs {
    public String time;//La valeur correspondant au timestamp est une valeur en secondes appelee epoch qui est le nombre de secondes ecoulles depuis January 1, 1970, 00:00:00 GMT
    public String dateExacte;//Ce timestamp doit etre converti en une date plus lisible
    public String duration;//Correspond a la duree de la connexion en ms
    public String remoteHost;//correspond a l'@IP interne qui emet la connexion
    public String codeStatus; //code: la maniere dont la requete a ete satisfaite| status: le code du resultat HTTP
    public String code;
    public String status;
    public String bytes;//correspond au nombre d'octets echanges durant la connexion
    public String requestMethod;//correspond a la methode HTTP utilisee
    public String url;//correspond a l'url de l'objet demande
    public String peerStatusPeerHost;
    public String peerStatus;//La maniere dont la requete a ete geree. i.e si elle a ete traitee par le serveur demande ou forwardee vers un autre serveur
    public String peerHost;//l'adresse IP du serveur demande ou du serveur vers lequel la requete a ete forwardee
    public String contentType;//Le type de contenu echange
    public String lineRead;//variable qui prendra comme valeur chaque ligne parcourue
    public String[] splitLine;//Tableau de chaines de caracteres qui le resultat du decoupage d'une ligne
    public String line;
    
    public SquidLogs(){
        
        time="";
        dateExacte="";
        duration="";
        remoteHost="";
        codeStatus="";
        code="";
        status="";
        bytes="";
        requestMethod="";
        url="";
        peerStatusPeerHost="";
        peerStatus="";
        peerHost="";
        contentType="";
        lineRead="";
        line="";
    }
    
        public void squidProcess(String file){

            try(BufferedReader reader = new BufferedReader(new FileReader(file))){//on cree un objet BufferedReader appele reader pour lire un fichier de log qui sera passe en parametre

                lineRead= reader.readLine();
                while (lineRead != null){
                    splitLine=lineRead.split("\\s+");
                    time=splitLine[0];
                    double time1= Double.parseDouble(time);//Le timestamp doit etre converti en double puis en Long
                    long time2=(new Double(time1)).longValue();
                    Date date = new Date(time2*1000L);//Java mesure le temps en millisecondes donc on multiplie le nombre de secondes par 1000
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'à' HH:mm:ss z");//on precise le format de temps souhaite
                    dateExacte = sdf.format(date);

                    duration=splitLine[1];
                    remoteHost=splitLine[2];
                    codeStatus=splitLine[3];//On divise codeStatus en deux parties : code et status
                    code=(codeStatus.split("/"))[0];
                    status=(codeStatus.split("/"))[1];
                    bytes=splitLine[4];
                    requestMethod=splitLine[5];
                    url=splitLine[6];
                    //Le splitLine[7] n'est pas pris en compte car ce n'est qu'un tiret
                    peerStatusPeerHost=splitLine[8];//On divise peerStatusPeerHost en deux parties peerStatus et peerHost
                    peerStatus=(peerStatusPeerHost.split("/"))[0];
                    peerHost=(peerStatusPeerHost.split("/"))[1];
                    contentType=splitLine[9];
                    //stockage des informations dans un fichier .processed.txt
                    /*line ="Un utilisateur s'est connecté avec l'adresse IP interne "+remoteHost+" le "+dateExacte+" et a demandé l'objet "+url+" hébergé par un serveur à l'adresse IP "+peerHost+". "+bytes+" octets de type "+contentType+" ont été échangés pendant une durée de "+duration+" ms. La méthode HTTP utilisée est "+requestMethod+" et le code HTTP retourné est "+status+".";
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(file+".processed.txt",true))){//on cree un objet BufferedWriter appele writer pour ecrire dans un nouveau fichier nomme a partir du fichier de log passe en parametre+ l'extension .processed.txt
                        writer.write(line+"\n");
                    }*/

                    //Ajout des informations dans la base de donnees
                    Principale.insertSquid(remoteHost,dateExacte,url,peerHost,bytes,contentType,duration,requestMethod,status);

                    //System.out.println(line);
                    lineRead= reader.readLine();       
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

    public static void main(String[] args) {//testons notre methode squidProcess
        SquidLogs sl;
        sl = new SquidLogs();
        //squidProcess("exemple.squid.access.log.txt");
        sl.squidProcess("exemple.squid.access.log.txt");
    }
    
}
