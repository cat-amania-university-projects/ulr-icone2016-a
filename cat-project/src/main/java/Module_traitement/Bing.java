package Module_traitement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
public class Bing extends Engine{


    @Override
    void connexion(String query) {
            this.setName("https://bing.com/search?q=");
            query=this.getName()+query;
        try {
           
            this.document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
             
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    ArrayList getResult(String query) {
            int i=0;
            this.connexion(query);
             Elements links = this.document.select("div[class=sb_add_sb_adTA]");
            for (Element link : links) 
            {
                
                Elements urlstrings = link.select("cite");
                Elements titles = link.select("h2");
                Elements descriptions = link.select("p[class=sb_addsec]>a");
                //map= new HashMap<Lien, ArrayList<String>>();
                
                String urlstring = urlstrings.text();
                String title = titles.text();
                String description = descriptions.text();
               
              
                this.saveLinksDescTitle(urlstring,title,description,i); // on enregistre le lien dans this HashMap
                /*try {
                    //this.printHashMapContent();
                    this.printLinkListContent();
                } catch (IOException ex) {
                    Logger.getLogger(Startpage.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
            }

        return this.getLinklist();  // le resultat a retourner doit etre un HashMap, pour le moment je retourne null.
           
    }
  
            
        
String getUrlContent(String query){
     String content=null;
        try {
            
            Document _document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
             Elements body = _document.select("body");
              content=body.html();
        } catch (IOException ex) {
            Logger.getLogger(Startpage.class.getName()).log(Level.SEVERE, null, ex);
        }
return content;
}
private String formerUrl(String url){
    if(url.indexOf("http")==-1){
      url="http://"+url;
    }
        return url;
}

 



    }
    
    
    

