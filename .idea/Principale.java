import java.lang.String;

public class Principale {
    //attribut « valeur » de type double
    public String url;
    public String serverName;

    //constructeur sans paramètre
    public Principale() {
        this.url = "";
        this.serverName = "";
    }
    //constructeur avec paramètres
    public Principale(String url, String serverName){
        this.url = link;
        this.serverName = server;
    }

    //Accesseurs
    //GET

    public String getUrl() {
        return url;
    }

    public String getServerName() {
        return serverName;
    }
    //SET


    public void setUrl(String url) {
        this.url = url;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    //méthode « affiche », affiche la valeur de la température suivie de « échelle inconnue ».
    public static void choix(){

    }

    public static void Ajout(){

    }

    public static void Suppression(){

    }
}
