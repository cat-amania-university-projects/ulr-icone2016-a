package Module_traitement;
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
public class Google extends Engine {

	@Override
	void connexion(String query) {
		this.setName("https://www.google.com/search?q=");
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

		Elements links = this.document.select("div[class=g]");

		for (Element link : links) {
			
			String title = link.select("h3[class=r]").text();

			String url = link.select("h3[class=r] > a").attr("href");
			String lien = url.substring(7, url.indexOf("&"));

			Elements bodies = link.select("span[class=st]");
			String description = bodies.text();

			this.saveLinksDescTitle(lien, title, description, i);
			try {
				this.printLinkListContent();
			} catch (IOException ex) {
				Logger.getLogger(Startpage.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
		return this.getLinklist();
	}
	
	

}