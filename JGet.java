import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;
/*
 * A simple Java class to provide functionality similar to Wget.
 *
 * Note: Could also strip out all of the html w/ jtidy.
 */

import java.io.*;
import java.net.*;

public class JGet
{

  public static void main(String[] args)
  {

    if ( (args.length != 1) )
    {
      System.err.println( "\nUsage: java JGet [urlToGet]" );
      System.exit(1);
    }

    String url = args[0];

    URL u;
    InputStream is = null;
    DataInputStream dis;
    String s;

    try
    {
      u = new URL(url);
      is = u.openStream();
      dis = new DataInputStream(new BufferedInputStream(is));
        File ff=new File("pukimak.txt"); // définir l'arborescence
		ff.createNewFile();
		FileWriter ffw=new FileWriter(ff);
      while ((s = dis.readLine()) != null)
      {
      
		ffw.write(s);  // écrire une ligne dans le fichier resultat.txt
		ffw.write("\n"); // forcer le passage à la ligne
		
      }
      ffw.close(); // fermer le fichier à la fin des traitements
    }
    catch (MalformedURLException mue)
    {
      System.err.println("Ouch - a MalformedURLException happened.");
      mue.printStackTrace();
      System.exit(2);
    }
    catch (IOException ioe)
    {
      System.err.println("Oops- an IOException happened.");
      ioe.printStackTrace();
      System.exit(3);
    }
    finally
    {
      try
      {
        is.close();
      }
      catch (IOException ioe)
      {
      }
    }

  }

}
