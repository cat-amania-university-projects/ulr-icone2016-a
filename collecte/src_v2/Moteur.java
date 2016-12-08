/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.cat.amania;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import javax.xml.xpath.XPath;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/**
 *
 * @author abdoul
 */

abstract class Moteur {
  protected Document document;
  protected XPath xpath;
  protected Node node;
  protected String name;
  protected List<URL> linklist;
  protected HashMap map;
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

    public List<URL> getLinklist() {
        return linklist;
    }

    public void setLinklist(List<URL> linklist) {
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
  abstract void connexion(String query); // cette methode permet d'etablir une connexion avec un moteur de recherche
  abstract HashMap<String,URL> getResults(String query); // cette methode permet de recuperer les liens,les titres et les descriptions
        

        
}
