package Module_traitement;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.xpath.XPath;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;


/**
 *
 * @author abdoul
 */

abstract class Engine {


    
  protected Document document;
  protected XPath xpath;
  protected Node node;
  protected String name;
  protected HashMap<Link, ArrayList<String>> map;
  protected ArrayList<Link> linklist= new ArrayList<>();
  protected String link;
  protected String title;
    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Link> getLinklist() {
        return linklist;
    }

    public void setLinklist(ArrayList<Link> linklist) {
        this.linklist = linklist;
    }

    public HashMap getMap() {
        return map;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }
     public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    protected void saveLinksDescTitle(String lien,String title,String desc,int i){
     
     Link url=new Link(lien);
     url.setUrlString(lien);
     
     url.setTitle(title);
     url.setDesc(desc);
     
     /* ici on enregistre le lien Lien cr�� precedemment*/
    
     this.linklist.add(i,url);
     
    }
    protected void printHashMapContent(){
		for (HashMap.Entry entry : map.entrySet()) {
			   System.out.println(entry.getKey() + " \t" + entry.getValue());
			}
	}
     protected void printLinkListContent() throws IOException{
		for (int i=0; i<this.linklist.size();i++)
	{
		String lien =  linklist.get(i).getUrlString();
		String titre= linklist.get(i).getTitle();
                String desc= linklist.get(i).getDesc();
                //String contenu=linklist.get(i).getContent();
                String contenu = "vide";
                    //System.out.println(lien+"==> Titre: "+titre+" \tDescription: "+desc+"\tcontenu: "+contenu+"\n");   
        }
	}
     
  abstract void connexion(String query); // cette methode permet d'etablir une connexion avec un moteur de recherche
  abstract ArrayList getResult(String query) throws IOException;

        
}
