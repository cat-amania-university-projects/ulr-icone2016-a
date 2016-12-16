package Module_traitement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author abdoul
 */

public class Collector {
	protected ArrayList<Link> linklistglob;
	protected ArrayList<Link> startpageResults;
	protected ArrayList<Link> googleResults;
	protected ArrayList<Link> yahooResults;
	protected ArrayList<Link> bingResults;;


	public Collector(String query) throws IOException {
		this.initResults(query);
		
		this.computeLinkLists(this.googleResults);
		this.computeLinkLists(this.startpageResults);
		this.computeLinkLists(this.yahooResults);
		this.computeLinkLists(this.bingResults);


	}
	/*
	 * Cette methode initialise les attributs resultats des moteurs de recherche
	 * de la classe courante
	 */

	public void initResults(String query) throws IOException {
		
		this.linklistglob = new ArrayList<>();
		//Google
		
		this.googleResults = new ArrayList<>();
		Google google = new Google();
		ArrayList<Link> result2 = google.getResult(query);
		this.googleResults = result2;
		
		//startPage
		
		this.startpageResults = new ArrayList<>();
		Startpage startpage = new Startpage();
		ArrayList<Link> result1 = startpage.getResult(query);
		this.startpageResults = result1;

		//Yahoo
		
		this.yahooResults = new ArrayList<>();
		Yahoo yahoo = new Yahoo();
		ArrayList<Link> result3 = yahoo.getResult(query);
		this.yahooResults = result3;
		
		
		//Bing
		
		this.bingResults = new ArrayList<>();
		Bing bing = new Bing();
		@SuppressWarnings("unchecked")
		ArrayList<Link> result4 = bing.getResult(query);
		this.bingResults = result4;
		

	}
	
	
	
	public void computeLinkLists(ArrayList<Link> links) {
		
		for (int i = this.linklistglob.size(),j=0; i < this.linklistglob.size() + links.size() && j < links.size(); i++,j++) {
			// verification de l'existence avant l'insertion
			if (!contains(links.get(j))) {
				this.linklistglob.add(i, links.get(j));		
			}	
		}
	}

	protected boolean contains(Link link) {
		for(Link Link : this.linklistglob){		
			if(Link.equals(link.getUrlString())){
				return true;
			}
		}
		
		for(int i = 0 ; i < this.linklistglob.size() ; i++){	
			if(this.linklistglob.get(i).equals(link.getUrlString())){
				return true;
			}
		}
	
		return false;
	}
	
	
	/**************************************************
	 * fct qui permet de recuperer les contenus des pages web de la liste finale
	 * @author EL BAZ
	 * @param link
	 */
	void setTextContentPages() {
		Document doc;
		String textContentPage;

		for (Link link : this.linklistglob) {
			 doc = null;
			 textContentPage = "";
			try {
				doc = Jsoup.connect(link.getUrlString()).timeout(1000).get();
				Element body = doc.select("body").first();
				//titlePage = doc.title();
				textContentPage = body.text();
				link.setNiceText(textContentPage);

			} catch (IOException e) {
				System.err.println("probleme de connexion");
			}
		}

	}
	


	public ArrayList<Link> getLinklistglob() {
		return linklistglob;
	}

	public void setLinklistglob(ArrayList<Link> linklistglob) {
		this.linklistglob = linklistglob;
	}

	protected void printLinkListglobContent() {

		for (int i = 0; i < this.linklistglob.size(); i++) {
			String Link = linklistglob.get(i).getUrlString();
			String titre = linklistglob.get(i).getTitle();
			String desc = linklistglob.get(i).getDesc();
			String contenu = " ";
			contenu=linklistglob.get(i).getNiceText();
	
			System.out
					.println(Link + "==> Titre: " + titre + " \tDescription: " + desc + "\tcontenu: " + contenu + "\n");
			System.out.println("**********************************************************************************************");
		}
		
		System.out.println("taille de la liste finale : "+this.linklistglob.size());

	}
}
