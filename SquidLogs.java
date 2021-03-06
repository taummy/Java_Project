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

/*
Welcome to the squidLogs class. This one contains the method to to process the squid log so as to have
a simpler version
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
    private Principale princ;
    
    //We create a Principale object to acces the insertSquid method
    public SquidLogs(Principale p){
        princ=p;
    }
    
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
    public SquidLogs(String remoteHost, String dateExacte, String url, String peerHost, String bytes, String contentType, String duration, String requestMethod, String status){
        this.remoteHost=remoteHost;
        this.dateExacte=dateExacte;
        this.url=url;
        this.peerHost=peerHost;
        this.bytes=bytes;
        this.contentType=contentType;
        this.duration=duration;
        this.requestMethod=requestMethod;
        this.status=status;
    }
    public String getRemoteHost(){
        return remoteHost;
    }
    public String getDateExacte(){
        return dateExacte;
    }
    public String getUrl(){
        return url;
    }
    public String getPeerHost(){
        return peerHost;
    }
    public String getBytes(){
        return bytes;
    }
    public String getContentType(){
        return contentType;
    }
    public String getDuration(){
        return duration;
    }
    public String getRequestMethod(){
        return requestMethod;
    }
    public String getStatus(){
        return status;
    }
    
        public void squidProcess(String file){

            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
//we create a BufferedReader object called reader to read the logfile given as a parameter
//Now we'll process our reader line by line
                lineRead= reader.readLine();
                while (lineRead != null){
                    splitLine=lineRead.split("\\s+");//we split the line by the space without caring about multiple spaces
                    time=splitLine[0];
                    double time1= Double.parseDouble(time);//The timestamp must be casted into double then into long
                    long time2=(new Double(time1)).longValue();
                    Date date = new Date(time2*1000L);//Java uses millisecond as time unit so we multiply the secons by 1000
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy 'à' HH:mm:ss z");//we percise the time format we want
                    dateExacte = sdf.format(date);

                    duration=splitLine[1];
                    remoteHost=splitLine[2];
                    codeStatus=splitLine[3];//we divide codeStatus into two parts : code and status
                    code=(codeStatus.split("/"))[0];
                    status=(codeStatus.split("/"))[1];
                    bytes=splitLine[4];
                    requestMethod=splitLine[5];
                    url=splitLine[6];
                    //the splitLine[7] is omitted because it's a simple "-"
                    peerStatusPeerHost=splitLine[8];//we divide peerStatusPeerHost into two parts peerStatus and peerHost
                    peerStatus=(peerStatusPeerHost.split("/"))[0];
                    peerHost=(peerStatusPeerHost.split("/"))[1];
                    contentType=splitLine[9];
                    //saving information into a  .processed.txt file
                    /*line ="Un utilisateur s'est connecté avec l'adresse IP interne "+remoteHost+" le "+dateExacte+" et a demandé l'objet "+url+" hébergé par un serveur à l'adresse IP "+peerHost+". "+bytes+" octets de type "+contentType+" ont été échangés pendant une durée de "+duration+" ms. La méthode HTTP utilisée est "+requestMethod+" et le code HTTP retourné est "+status+".";
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(file+".processed.txt",true))){//on cree un objet BufferedWriter appele writer pour ecrire dans un nouveau fichier nomme a partir du fichier de log passe en parametre+ l'extension .processed.txt
                        writer.write(line+"\n");
                    }*/

                    //Adding the information to the squid table using insertSquid
                    princ.insertSquid(remoteHost,dateExacte,url,peerHost,bytes,contentType,duration,requestMethod,status);

                    //System.out.println(line);
                    lineRead= reader.readLine();       
                }
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }

    public static void main(String[] args) {//testons notre methode squidProcess
        Principale p;
        p= new Principale();
        SquidLogs sl;
        sl = new SquidLogs(p);
        //squidProcess("exemple.squid.access.log.txt");
        sl.squidProcess("exemple.squid.access.log.txt");
    }
    
}
