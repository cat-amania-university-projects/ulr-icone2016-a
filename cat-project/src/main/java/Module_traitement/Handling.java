package Module_traitement;

import java.io.IOException;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.net.*;
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
import org.elasticsearch.index.query.QueryBuilders;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import org.elasticsearch.search.SearchHit;

import com.mongodb.DBObject;

import java.io.IOException;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

public class Handling {
    

     public void filtrage(){
       
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
        
        String mot_cle="php mvc";
        SearchResponse response = client.prepareSearch("test")
                .setQuery(QueryBuilders.multiMatchQuery(mot_cle, "titre", "description","code_source" ))
                .execute()
                .actionGet();
        String json = "";
        Store_db stock =new Store_db();
        for(SearchHit hit : response.getHits())
        {
        	//System.out.println(hit.getScore()+", "+hit.getSource().get("titre"));
        	
        	json="{\n" +
        			"	 \"titre\":\""+hit.getSource().get("titre")+"\",\n" +
        			"	 \"description\":\""+hit.getSource().get("description")+"\",\n" +
        			"	 \"url\":\""+hit.getSource().get("url")+"\",\n" +
        			"	 \"pertinence\":\""+hit.getScore()+"\",\n" +
        			"	 \"mot_cle\":\""+mot_cle+"\"\n" +
        			"}";
        	Object o = com.mongodb.util.JSON.parse(json);
            DBObject dbObj = (DBObject) o;
            
            stock.insertion("recherche", (DBObject) dbObj);
            
        	 //System.out.println(json);
        }
        
        
       
       // System.out.println(response.getHits().getAt(0).getScore());
       // System.out.println(response);
                
     }
         
         
 }
     
 
         
       
    
    
    

