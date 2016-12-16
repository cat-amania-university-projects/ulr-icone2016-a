package Module_traitement;


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
	ArrayList<Link> getResult(String query) throws IOException {
		int i = 0;
		this.connexion(query);
		// Elements links = this.document.select("div[class=result]");
		Elements links = this.document.select("ol.web_regular_results > li > div.result ");
		if(links != null){
			for (Element link : links) {
				String urlstring = link.select("a").first().attr("href");
				String title = link.select("span").first().text();
				String description = link.select("p.desc.clk").first().text();
				this.saveLinksDescTitle(urlstring, title, description, i);
				i++;
			}
		}


		//System.out.println("taille de la liste startpage : "+this.linklist.size());


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
