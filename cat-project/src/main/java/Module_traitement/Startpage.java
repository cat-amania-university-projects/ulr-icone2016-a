/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Startpage extends Engine {

	@Override
	void connexion(String query) {
		this.setName("https://startpage.com/do/search?q=");
		query = this.getName() + query;
		try {

			this.document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();

		} catch (IOException ex) {
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	
	public ArrayList getResult(String query) {
		int i = 0;
		this.connexion(query);
		Elements links = this.document.select("ol.web_regular_results > li > div.result ");
		for (Element link : links) {

			String urlstring = link.select("a").first().attr("href");
			String title = link.select("span").first().text();
			String description = link.select("p.desc.clk").first().text();
			// on enregistre le lien dans this HashMap
			this.saveLinksDescTitle(urlstring, title, description, i); 
			try {
				this.printLinkListContent();
			} catch (IOException ex) {
				Logger.getLogger(Startpage.class.getName()).log(Level.SEVERE, null, ex);
			}

		}

		return this.getLinklist(); // le resultat a retourner doit etre un
									// HashMap, pour le moment je retourne null.

	}

	/*
	  String getUrlContent(String query) {
	 
		String content = null;
		try {
			Document _document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
			Elements body = _document.select("body");
			content = body.html();
		} catch (IOException ex) {
			Logger.getLogger(Startpage.class.getName()).log(Level.SEVERE, null, ex);
		}
		return content;
	}
	*/

}