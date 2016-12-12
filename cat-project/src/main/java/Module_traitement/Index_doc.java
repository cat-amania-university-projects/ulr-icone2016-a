/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module_traitement;
import java.io.IOException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.elasticsearch.common.xcontent.XContentBuilder;
import static org.elasticsearch.common.xcontent.XContentFactory.*;

import org.elasticsearch.action.search.*;
import org.elasticsearch.action.get.*;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import org.elasticsearch.search.SearchHit;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
/**
 *
 * @author Alberto
 */

public class Index_doc {
    public static void main(String[] args){

     //creation d'un tableau d'obj
    	 
    	 
    	 
    	 ArrayList <Insert_doc> arr = new ArrayList<Insert_doc>();
    	 Insert_doc eng;
    	 eng = new Insert_doc("Elasticsearch PHP",
                 "Get started with the documentation for Elasticsearch, Kibana, Logstash, Beats, X-Pack, Elastic Cloud, Elasticsearch for Apache Hadoop, and our language clients.",
                 "https://www.elastic.co/guide/en/elasticsearch/client/php-api/current/index.html",
                 "This is the official PHP client for Elasticsearch. It is designed to be a very low-level client that does not stray from the REST API."
                ); 
    	 
    	 Insert_doc eng2;
    	 eng2 = new Insert_doc("Framework Symfony PHP",
                 "Symfony est un framework MVC libre écrit en PHP (compatible avec PHP 7). Il fournit des fonctionnalités modulables et adaptables qui permettent de faciliter et d’accélérer le développement d'un site web.",
                 "https://fr.wikipedia.org/wiki/Symfony",
                 "L'agence web française SensioLabs est à l'origine du framework Sensio Framework3. À force de toujours recréer les mêmes fonctionnalités de gestion d'utilisateurs, gestion ORM, etc., elle a développé ce framework pour ses propres besoins. Comme ces problématiques étaient souvent les mêmes pour d'autres développeurs, le code a été par la suite partagé avec la communauté des développeurs PHP."
                ); 
    	 
    	 
    	 arr.add(eng);
    	 arr.add(eng2);
    	 
    	 //System.out.print(arr.get(0).titre);
    	    
    	    
    	
        
    Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
    TransportClient client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
    
    for(int i=0; i<arr.size(); i++)
    {
    	 IndexResponse response = client.prepareIndex("test", "pageweb",""+(i+1)+"")
    			    .setSource(putJsonDocument( arr.get(i).titre,arr.get(i).description,arr.get(i).url,arr.get(i).contenu
    			    		)).execute().actionGet();
    }

     
      System.out.println("Documents indexés");
      
      Handling filtre = new Handling();
      filtre.filtrage();
      
    }
    
    // Fonction pour creer un hashmap    
    public static Map<String, Object> putJsonDocument(String titre,String description, String url, String code_source)
    {
            Map<String, Object> jsonDocument = new HashMap<String, Object>();
            jsonDocument.put("titre", titre);
            jsonDocument.put("description", description);
            jsonDocument.put("url", url);
            jsonDocument.put("code_source",code_source );
  
            //System.out.println(jsonDocument);
            
            return jsonDocument;
    }
}
