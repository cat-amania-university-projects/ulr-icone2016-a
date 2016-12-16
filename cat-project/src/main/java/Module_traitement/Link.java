package Module_traitement;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author abdoul
 */
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.*;

public class Link {
    // donnees et methodes pour l'URL elle-meme (en tant qu'"addresse web")
	protected int reference = 0;
	protected String urlString = null;
	protected URL url = null;
	protected boolean isAllowedToVisit;
	protected boolean isCheckedForPermission = false;
	protected boolean isVisited = false;

	public Link(String urlString) {
		this.urlString = urlString;
		this.computeURL();
	}

	private void computeURL() {
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// petit probleme
		}
	}

	public URL getURL() {
		return this.url;
	}

	public int getDepth() {
		return this.reference;
	}

	public boolean isAllowedToVisit() {
		return isAllowedToVisit;
	}

	public void setAllowedToVisit(boolean isAllowedToVisit) {
		this.isAllowedToVisit = isAllowedToVisit;
		this.isCheckedForPermission = true;
	}

	public boolean isCheckedForPermission() {
		return isCheckedForPermission;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setIsVisited() {
		this.isVisited = true;
	}

	public String getUrlString() {
		return this.urlString;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public boolean isIsAllowedToVisit() {
		return isAllowedToVisit;
	}

	public void setIsAllowedToVisit(boolean isAllowedToVisit) {
		this.isAllowedToVisit = isAllowedToVisit;
	}

	public boolean isIsCheckedForPermission() {
		return isCheckedForPermission;
	}

	public void setIsCheckedForPermission(boolean isCheckedForPermission) {
		this.isCheckedForPermission = isCheckedForPermission;
	}

	public boolean isIsVisited() {
		return isVisited;
	}

	public void setIsVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

	public Document getHtmlJsoupDoc() {
		return htmlJsoupDoc;
	}

	public void setHtmlJsoupDoc(Document htmlJsoupDoc) {
		this.htmlJsoupDoc = htmlJsoupDoc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getLinkList() {
		return linkList;
	}

	public void setLinkList(List<String> linkList) {
		this.linkList = linkList;
	}

	@Override
	public String toString() {
		return this.urlString + " [reference=" + reference + " visit=" + this.isAllowedToVisit + " check="
				+ this.isCheckedForPermission + "]";
	}

	@Override
	public int hashCode() {
		return this.urlString.hashCode();
	}



	// donnees et methodes concernant le contenu telecharge depuis l'URL

	private String htmlText;
	private Document htmlJsoupDoc;
	private String niceText; // contient le contenu du body de l'url parsée
	private String title;
	private String desc;

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	public void setNiceText(String niceText) {
		this.niceText = niceText;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	private List<String> linkList;

	public String getNiceText() {
		return (niceText);
	}

	public String getTitle() {
		return (title);
	}

	public List<String> getLinks() {
		return (linkList);
	}

	/*public void setUrlContent(String htmlText) {
		this.htmlText = htmlText;
		this.htmlJsoupDoc = Jsoup.parse(htmlText);
		niceText = htmlJsoupDoc.body().text();
	}*/

	/*public String getContent() throws IOException {
		String text = new String();
		text = "";
		// this.verifieValiditeUrl(); // ici on vrifie la validité de l'url
		// if (this.isAllowedToVisit == false){return " chaine vide ";}
		if (this.urlString.startsWith("http://") || this.urlString.startsWith("https://")) {
			text = getHTML(urlString);
		} else {
			this.setUrlString("https://" + urlString);
		}
		setUrlContent(text);
		return this.niceText;

	}
*/
	/*public String getHTML(String urlToRead) {
		URL url; // l'url a lire
		HttpURLConnection conn; // la connexion a la page web
		BufferedReader rd; // utilisé pour afficher le contenu de la page web
		String line; // une ligne de la page web
		String result = ""; // la chaine qui contient le contenu
		try {
			url = new URL(urlToRead);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
*/
	/*
	 * Cette methode permet de tester la validité d'une URL si celle-ci est
	 * valide on met sa variable isAllowToVisit à true
	 */
	
	@Override
	public boolean equals(Object obj){
		Boolean equal = false;
		if (getClass()!=obj.getClass()){
			equal= false;
		}else {
			Link l1=(Link)obj;
			if(this.getUrlString()==l1.getUrlString()){
				
				equal= true;
			}
			
		}
		
		return equal;
	}

}