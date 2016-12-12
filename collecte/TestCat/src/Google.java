
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//test
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
	HashMap<String, URL> getResults(String query) {
		this.connexion(query);

		Elements results = this.document.select("h3.r > a , span.st");

		for (Element result : results) {
			String linkHref = result.attr("href");
			String linkText = result.text();
			// hmap.put(linkText, linkHref.substring(0, linkHref.indexOf("&")));
			if (linkHref != null && linkHref != "")
				System.out.println(linkText + "\t\t" + linkHref.substring(7, linkHref.indexOf("&")));
			else
				System.out.println(linkText);
		}

		return null; // le resultat a retourner doit etre un HashMap, pour le
						// moment je retourne null.
	}

}