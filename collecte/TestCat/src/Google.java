
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author EL BAZ
 */
public class Google extends Moteur {
	// static HashMap<String, String> hmap = new HashMap<String, String>();

	@Override
	void connexion(String query) {
		this.setName("https://www.google.com/search?q=");
		query = this.getName() + query;
		try {

			this.document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();

		} catch (IOException ex) {
			Logger.getLogger(Moteur.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	ArrayList getResult(String query) {
	
		int i = 0;
		this.connexion(query);
		
		//Elements links = this.document.select("h3.r > a , span.st");
		Elements links = this.document.select("div.rc");

		
		for (Element link : links) {
			String title = link.select("h3[class=r]").text();
			String urlString = link.select("h3[class=r]").attr("href");
			urlString = urlString.substring(7, urlString.indexOf("&"));
			String description = link.select("span[class=st]").text();
			
			//String description = descriptions.text();
			/*
			if (urlstring != null && urlstring != "")
				System.out.println(linkText + "\t\t" + urlstring.substring(7, urlstring.indexOf("&")));
			else
				System.out.println(linkText);
			*/


			this.saveLinksDescTitle(urlString, title, description, i);
			try {
				// this.printHashMapContent();
				this.printLinkListContent();
			} catch (IOException ex) {
				Logger.getLogger(Startpage.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

		return this.getLinklist(); 
	
	}

}