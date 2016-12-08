/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.amania.project;

/**
 *
 * @author abdoul
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.*;
/*import lobobrowser.cobra.api.ua;
import org.lobobrowser.html.UserAgentContext;
import org.lobobrowser.html.parser.HtmlParser;*/
import org.lobobrowser.html.test.SimpleUserAgentContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public abstract class MoteurRecherche {
    protected UserAgentContext uacontext;
    protected DocumentBuilderFactory factory;
    protected DocumentBuilder builder;
    protected Document document;
    protected HtmlParser parser;
    protected XPath xpath;
    protected Node node;
    protected URL searchURL;
    protected InputStream in;
    protected Reader reader;
     
      //Initialisation
    public void connexion(String query){
        uacontext = new SimpleUserAgentContext();
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
 
            document = builder.newDocument();
            parser = new HtmlParser(uacontext, document);
            xpath = XPathFactory.newInstance().newXPath();
           
 
            System.setProperty ("jsse.enableSNIExtension", "false");
            searchURL = new URL(query.replaceAll(" ","%20"));
            in = searchURL.openConnection().getInputStream();
            reader = new InputStreamReader(in, "UTF-8");
            parser.parse(reader);
 
        } catch (ParserConfigurationException | IOException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
  
    
 
}
