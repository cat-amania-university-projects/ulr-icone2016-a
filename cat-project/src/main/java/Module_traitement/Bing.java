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
		query = this.getName() + query;
		try {

			this.document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();

		} catch (IOException ex) {
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	ArrayList getResult(String query) {
		int i = 0;
		this.connexion(query);
		
		Elements links = this.document.select("li[class^=b_algo]");
		if(links != null){
			for (Element link : links) {
				Element a = link.select(" h2 > a").first();
				
				String url = a.attr("href");
				String title = a.text();
				String desc = link.select("div > p").first().text();
				
				this.saveLinksDescTitle(url, title, desc, i); 
				//System.out.println(url + "\t" + title + "\t" + desc);

			}
		}
	
		//System.out.println("taille de la liste Bing : "+this.linklist.size());


		return this.getLinklist(); // le resultat a retourner doit etre un
									// HashMap, pour le moment je retourne null.

	}


    }
    
    
    

