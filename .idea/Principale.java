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
    public Principale(String link, String name){
        this.url = link;
        this.serverName = name;
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


    public void setUrl(String link) {
        this.url = link;
    }

    public void setServerName(String name) {
        this.serverName = name;
    }

    //méthode « affiche », affiche la valeur de la température suivie de « échelle inconnue ».
    public static void choix(String link){
        

    }

    public static void Ajout(String link String name){
        Principale server+name = new Principale(link,name);
        
        

    }

    public static void Suppression(String link String name){
        

    }
}
