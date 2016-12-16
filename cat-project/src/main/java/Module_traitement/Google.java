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
 * @author
 */
public class Google extends Engine {

	@Override
	void connexion(String query) {
		this.setName("https://www.google.com/search?q=");
		query = this.getName() + query + "&num=10";
		try {
			this.document = Jsoup.connect(query).userAgent("Mozilla").ignoreHttpErrors(true).timeout(0).get();
		} catch (IOException ex) {
			Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	ArrayList<Link> getResult(String query) throws IOException {
		int i = 0;
		this.connexion(query);
		Elements links = this.document.select("div[class=g]");
		if(links != null){
			for (Element link : links) {

				String title = link.select("h3[class=r]").text();

				String url = link.select("h3[class=r] > a").attr("href");
				String lien = "";
				if (url != null && !url.equals("")) {
					lien = url.substring(7, url.indexOf("&"));
					if(lien.startsWith("http")){
						Elements bodies = link.select("span[class=st]");
						String description = bodies.text();			
						this.saveLinksDescTitle(lien, title, description, i);
						i++;
					}

				}
			}
		}

		
		//System.out.println("taille de la liste Google : "+this.linklist.size());


		return this.linklist;

	}

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
}

