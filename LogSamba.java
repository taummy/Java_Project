import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogSamba extends Logs {
	
	private String leDate ;
	private String lheure;
	private String IPConnectee ;
	private String service ;
	private String user;
	private String salle;
	private String state;

	
	
	
	
	
	
	public LogSamba(){
		super();
		this.leDate=DEFAULTSTRING;
		this.lheure=DEFAULTSTRING;
		this.IPConnectee=DEFAULTSTRING;
		this.service=DEFAULTSTRING;
		this.user=DEFAULTSTRING;
		this.salle=DEFAULTSTRING;
		this.state=DEFAULTSTRING;
	

	}
	
	public LogSamba(String leDate,String lheure,String IPConnectee,String service,String user,String salle,String state){
		this.leDate=leDate;
		this.lheure=lheure;
		this.IPConnectee=IPConnectee;
		this.service=service;
		this.user=user;
		this.salle=salle;
		this.state=state;
	}
	
	public String getDate(){
		return leDate;
		
	}
	
	public String getIP(){
		return IPConnectee;
		
	}
	public String getService(){
		return service;
		
	}
	public String getSalle(){
		return salle;
		
	}
	public String getState(){
		return state;
		
	}
	
	
	
	 public static  void sambaProcess(String file){
	        
	        try(BufferedReader reader = new BufferedReader(new FileReader(file))){//on cree un objet BufferedReader appele reader pour lire un fichier de log qui sera passe en parametre
	            
	        	String[] splitLine;
	        	String[] splitLine1;
	        	String properService;
	        	String properUser;
	        	String properSalle;
	        	String properState;
	        	PrintWriter writer = new PrintWriter("/home/rtel/etu/rt2017/bh617745/M4210/JavaProject/src/test", "UTF-8");
	        	
	        	
	            String lineRead= reader.readLine();
	            String exampleword;
	            while (lineRead != null){
	            	splitLine=lineRead.split("\\s+");
	            	if(splitLine.length>2 && splitLine.length<8){
	            		Pattern p = Pattern.compile("0.?");
	            		Matcher m = p.matcher(splitLine[2]);
	            			if(m.find()){
	            				lineRead= reader.readLine();

	            			}
	            			else{
	                		



	            				p = Pattern.compile("\\[.+/.+/.+\\]");
	            				m = p.matcher(lineRead);
	            				if(m.find()){
	            					StringBuilder sb = new StringBuilder(splitLine[0]);
	            					StringBuilder sb1 = new StringBuilder(splitLine[1]);
	            					System.out.println(splitLine[0]);
	            					System.out.println(splitLine[1]);

	            					sb.deleteCharAt(0);
	            					sb1.deleteCharAt(8);
	            					String properYear = sb.toString();
	            					String properTime = sb1.toString();
		                		
			                
	            					lineRead= reader.readLine();
	            					p = Pattern.compile("rt[1-9].+");
	            					m = p.matcher(lineRead);
	            					if(m.find()){
	            						splitLine=lineRead.split("\\s+");
	            						properSalle=splitLine[1];
	            						p = Pattern.compile("closed");
	            						m = p.matcher(splitLine[3]);
	    	    	            		splitLine1=splitLine[2].split(":");
	    	    	                	String properIP = splitLine1[3].substring(0, splitLine1[3].length() - 1);



	    	    	            	if(m.find()){
	    	    	            		properState=splitLine[3];
	    	    	            		properUser="";
	    	    	            		properService=splitLine[7];
		    		              
	    	    	       
	    	    	            	}
	    	    	            	else{
	    	    	            		properState=splitLine[3];
	    	    	            		properUser=splitLine[6];
	    	    	            		properService=splitLine[10];
		    		                	
	    	    	            		
	    	    	            	}
		    	                	LogSamba firstlog = new LogSamba(properYear,properTime,properIP,properService,properUser,properSalle,properState);
		    		                System.out.println("The user "+firstlog.user+" / "+firstlog.IPConnectee+" "+firstlog.state+" son connection au service "+ firstlog.service+" au salle "+firstlog.salle+" au date "+" "+firstlog.leDate+" "+firstlog.lheure);
		    	            		writer.println("The user "+firstlog.user+" / "+firstlog.IPConnectee+" "+firstlog.state+" son connection au service "+ firstlog.service+" au salle "+firstlog.salle+" au date "+" "+firstlog.leDate+" "+firstlog.lheure);
						Principale.insertSamba(firstlog.leDate,firstlog.lheure,firstlog.IPConnectee,firstlog.service,firstlog.user,firstlog.salle,firstlog.state)
	    	                		}
	    	                }
	            	
	                	}
	            	}
	            	else{
		                lineRead= reader.readLine();

	            	}
	                lineRead= reader.readLine();
	                }
	            
	                  
	            	 

		           
		          
	                
	            
	        }
	        catch(IOException e){
	            System.out.println(e.getMessage());
	        }
	    }
	    public static void main(String[] args) {//testons notre methode squidProcess
	        sambaProcess("/home/rtel/etu/rt2017/bh617745/M4210/JavaProject/src/exemple.samba.access.log.txt");
	}
}
