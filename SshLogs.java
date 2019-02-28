/**
 *
 * Tom. Récupération de : IP, Port et User
 */
package server;
import java.util.*;
import java.io.*;
import java.text.*;

public class SshLogs extends Logs {
    /**public static String time;//La valeur correspondant au timestamp est une valeur en secondes appelee epoch qui est le nombre de secondes ecoulles depuis January 1, 1970, 00:00:00 GMT
    public static String dateExacte;//Ce timestamp doit etre converti en une date plus lisible
    public static String duration;//Correspond a la duree de la connexion en ms
    public static String remoteHost;//correspond a l'@IP interne qui emet la connexion
    public static String codeStatus; //code: la maniere dont la requete a ete satisfaite| status: le code du resultat HTTP
    public static String code;
    public static String status;
    public static String bytes;//correspond au nombre d'octets echanges durant la connexion
    public static String requestMethod;//correspond a la methode HTTP utilisee
    public static String url;//correspond a l'url de l'objet demande
    public static String peerStatusPeerHost;
    public static String peerStatus;//La maniere dont la requete a ete geree. i.e si elle a ete traitee par le serveur demande ou forwardee vers un autre serveur
    public static String peerHost;//l'adresse IP du serveur demande ou du serveur vers lequel la requete a ete forwardee
    public static String contentType;//Le type de contenu echange
    public static String lineRead;//variable qui prendra comme valeur chaque ligne parcourue
    public static String[] splitLine;//Tableau de chaines de caracteres qui le resultat du decoupage d'une ligne
    public static String line;

     **/
    public static String connexionIP;   //IP de l'user qui se connecte
    public static String nbPort;         //Port de connexion
    public static String nomUser;       //Nom de l'utilisateur qui se connecte
    public static String date;          //Date de la connexion et heure
    public static String lineRead;      //Variable qui prendra comme valeur chaque ligne parcourue
    public static String[] splitLine;   //Tableau de chaines de caracteres qui le resultat du decoupage d'une ligne
    public static String line;


    public static void sshRecup(String file){
        //try(BufferedReader reader = new BufferedReader(new FileReader(file))){

            //lineRead= reader.readLine();
            //while (lineRead != null){
        String line = "Apr 13 06:47:17 kheops sshd[4866]: reverse mapping checking getaddrinfo for n37-137.berlin.snafu.de [195.21.37.137] failed - POSSIBLE BREAK-IN ATTEMPT!";
        splitLine=line.split(" ");
        System.out.println("Voici la date et l'heure" + splitLine);

            }

        }

        //public static void main(String[] args) {//test de notre methode sshRecup
            //sshRecup("exemple.ssh.log.txt");
        //}
    //}
/*
    public static void squidProcess(String file){
        
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
                //affichage des informations
                line ="Un utilisateur s'est connecté avec l'adresse IP interne "+remoteHost+" le "+dateExacte+" et a demandé l'objet "+url+" hébergé par un serveur à l'adresse IP "+peerHost+". "+bytes+" octets de type "+contentType+" ont été échangés pendant une durée de "+duration+" ms. La méthode HTTP utilisée est "+requestMethod+" et le code HTTP retourné est "+status+".";
                try(BufferedWriter writer = new BufferedWriter(new FileWriter(file+".processed.txt",true))){//on cree un objet BufferedWriter appele writer pour ecrire dans un nouveau fichier nomme a partir du fichier de log passe en parametre+ l'extension .processed.txt
                    writer.write(line+"\n");
                }
                //System.out.println(line);
                lineRead= reader.readLine();       
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {//test de notre methode sshRecup
        squidProcess("exemple.ssh.log.txt");
    }
    */
//}
