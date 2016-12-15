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
public class Yahoo extends Engine {

	@Override
	void connexion(String query) {
		this.setName("https://search.yahoo.com/search?p=");
		query = this.getName() + query;
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
		Element results = this.document.select("div#main").first();

		results = results.select("ol").first();
		Elements links = results.select("li[id]");

		for (Element link : links) {
			Element elt = link.select("h3 > a").first();
			String title = elt.text();
			String url = elt.attr("href");
			String description = link.select("div.compText.aAbs").text();

			this.saveLinksDescTitle(url, title, description, i);
			i++;
		}

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
