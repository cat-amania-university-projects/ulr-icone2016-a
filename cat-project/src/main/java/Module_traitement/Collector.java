package Module_traitement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author abdoul
 */

public class Collector {
	protected ArrayList<Link> linklistglob;
	public ArrayList<Link> getLinklistglob() {
		return linklistglob;
	}

	public void setLinklistglob(ArrayList<Link> linklistglob) {
		this.linklistglob = linklistglob;
	}


	protected ArrayList<Link> startpageResults;
	protected ArrayList<Link> googleResults;
	protected ArrayList<Link> yahooResults;;

	public Collector(String query) throws IOException {
		this.initResults(query);
		
		//this.computeLinkLists(this.googleResults);
		this.computeLinkLists(this.startpageResults);
		//this.computeLinkLists(this.yahooResults);

	}
	/*
	 * Cette methode initialise les attributs resultats des moteurs de recherche
	 * de la classe courante
	 */

	public void initResults(String query) throws IOException {
		
		this.linklistglob = new ArrayList<>();
		this.startpageResults = new ArrayList<>();
		this.googleResults = new ArrayList<>();
		this.yahooResults = new ArrayList<>();
		Startpage startpage = new Startpage();
		Google google = new Google();
		Yahoo yahoo = new Yahoo();
	
		ArrayList<Link> result1 = startpage.getResult(query);
	
		this.startpageResults = result1;
		ArrayList result2 = google.getResult(query);
		this.googleResults = result2;
		
		ArrayList result3 = google.getResult(query);
		this.googleResults = result3;
	}
	
	
	
	public void computeLinkLists(ArrayList<Link> links) {
		
		for (int i = this.linklistglob.size(),j=0; i < this.linklistglob.size() + links.size() && j < links.size(); i++,j++) {
			// verification de l'existence avant l'insertion
			if (contains(links.get(j))!=true) {
				this.linklistglob.add(i, links.get(j));
			}
			
		}

	}

	/*
	 * Cette methode insere les resultats de recherche dans la collection
	 * linklistglob en eliminant les doublons
	 */
	public void computeLinkList(ArrayList<Link> links) {
		
			for (int i = this.linklistglob.size(),j=0; i < this.linklistglob.size() + links.size() && j < links.size(); i++,j++) {
				  if (!this.linklistglob.isEmpty()) 
				  {
					 
					  if( linkFormNo3w(links.get(j)) == true )
					  {
						  set3w(links.get(j));
						  if(linkFormNoProtocol(links.get(j))==true)
						  {
							setProtocol(links.get(j));
						  }
						  
					  }
					  if(linkFormed(links.get(j))==true)
					  {
						  //setProtocol3w(links.get(j));
						  
					  }
						  this.linklistglob.add(i, links.get(j));
					  //System.out.println(links.get(j));
					 
				  }
				  else {
					  
					 this.linklistglob.add(i, links.get(j));
				  	}
				   
				 
				 
			
			}

		}
	protected boolean linkFormed(Link link) {
		if(!link.getUrlString().startsWith("http://www.") && !link.getUrlString().startsWith("https://www.")){
			return true;
		}
		
		return false;
		
	}
	protected void setProtocol3w(Link link) {
		// pour le moment on ajoute http sans tester
		if(!link.getUrlString().startsWith("http://www")){
			if (!link.getUrlString().startsWith("http:") || !link.getUrlString().startsWith("https:")) {
				
				if (!link.getUrlString().startsWith("http:")) {
					String linkwithprotocol="http://www."+link.getUrlString();
					link.setUrlString(linkwithprotocol);
				}
				
				
				
			}
		
		}
	}
	protected void setProtocol(Link link) {
		// pour le moment on ajoute http sans tester
		String linkwithprotocol="http://"+link.getUrlString();
		link.setUrlString(linkwithprotocol);
	}
	protected boolean linkFormNoProtocol(Link link) {
		if(!link.getUrlString().startsWith("http") && !link.getUrlString().startsWith("https")){
			return true;
		}
		
		return false;
		
	}
	protected void set3w(Link link) {
		// pour le moment on ajoute http sans tester
		if(!link.getUrlString().startsWith("www")){
		String linkwithprotocol="www."+link.getUrlString();
		link.setUrlString(linkwithprotocol);
		}
	}
	protected boolean linkFormNo3w(Link link) {
		if(!link.getUrlString().startsWith("www.") && !link.getUrlString().startsWith("http://") && !link.getUrlString().startsWith("https://")){
			return true;
		}
		else if (link.getUrlString().startsWith("www.")) {
			return true;
		}
		return false;
		
	}
	protected boolean linkcontained(Link link) {
		
		for (int i = 0; i < this.linklistglob.size(); i++) {
			
			if (this.linklistglob.get(i).getDesc().contains(link.getDesc()) || link.getDesc().contains(this.linklistglob.get(i).getDesc()) )
			{
				
				return true;
			}
		}
		
		
		return false;

	}
	
	protected boolean contains(Link link) {
		for(int i = 0 ; i < this.linklistglob.size() ; i++){
			
			if(this.linklistglob.get(i).getUrlString().equals(link.getUrlString()))
			{
				return true;
			}
		}
	
		return false;
	}
	
	protected boolean startwith(Link link,int i) {
		
		if(this.linklistglob.get(i).getUrlString().startsWith("http") && link.getUrlString().startsWith("http"))
		{
			return true;
		}
		else if(this.linklistglob.get(i).getUrlString().startsWith("http") || link.getUrlString().startsWith("http")){
				if (this.linklistglob.get(i).getUrlString().startsWith("http")) 
				{
					if (this.linklistglob.get(i).getUrlString().contains(link.urlString) || link.urlString.contains(this.linklistglob.get(i).getUrlString())){return true;}
					else return false;
				}
				
				if (link.urlString.contains(this.linklistglob.get(i).getUrlString())){return true;}
				else return false;
			
		}
		return false;
	}
	
	protected boolean contain(Link link,int i) {
		if (this.linklistglob.get(i).getUrlString().contains(link.urlString) || link.urlString.contains(this.linklistglob.get(i).getUrlString())){
			if (this.linklistglob.get(i).getUrlString().length() < link.getUrlString().length()) {
				this.linklistglob.get(i).setUrlString(link.urlString);
			}
			return true;
			
		}
		return false;
	}

	
	protected void eliminateDoubl()
	{
	        Set set = new HashSet<Link>() ;
	        set.addAll(this.linklistglob) ;
	        this.linklistglob = new ArrayList(set) ;

	}
	

	protected void printLinkListglobContent() {

		for (int i = 0; i < this.linklistglob.size(); i++) {
			String lien = linklistglob.get(i).getUrlString();
			String titre = linklistglob.get(i).getTitle();
			String desc = linklistglob.get(i).getDesc();
			// String contenu=linklistglob.get(i).getContent();
			String contenu = " vide ";
			System.out
					.println(lien + "==> Titre: " + titre + " \tDescription: " + desc + "\tcontenu: " + contenu + "\n");
		}
	}

}
