/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.cat.amania;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 *
 * @author abdoul
 */
public class Startpage extends Moteur{


    @Override
    void connexion(String query) {
            this.setName("https://startpage.com/do/search?q=");
            query=this.getName()+query;
        try {
           
            this.document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
             
        } catch (IOException ex) {
            Logger.getLogger(Moteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    HashMap<String,URL> getResults(String query) {
          
            this.connexion(query);
           // ol[class=web_regular_results]
             Elements links = this.document.select("ol[class=web_regular_results]");
            //Elements links = this.document.select("a[href]");
            for (Element link : links) 
            {
                Elements liens = link.select("span[class=url]");
                Elements titles = link.select("h3[class=clk]");
                Elements descriptions = link.select("p[class=desc clk]");
               // afficher les liens
                    for(Element lien : liens){ 
                        String url = lien.text();
                        System.out.println(url+"\n");
                    }
               // afficher les titres
                    for(Element title : titles){ 
                        String titre = title.text();
                        System.out.println(titre+"\n");
                    }
               // afficher les descriptions
               
                    for(Element description : descriptions){ 
                        String desc = description.text();
                        System.out.println(desc+"\n");
                    }
        
          
            }
        return null;  // le resultat a retourner doit etre un HashMap, pour le moment je retourne null.
           
    }
            
        
private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
    }
    
    
    

