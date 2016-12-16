/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module_traitement;
import java.io.IOException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
/**
 *
 * @author Alberto
 */

public class IndexDoc {
	protected String mot_cle;
	
   /* public IndexDoc(String mot_cle) {
    mot_cle= new String(mot_cle);
		
	}*/

	public void recovery( String mot,ArrayList<Link>  arr) throws IOException, JSONException{

     //verifier si tableau est vide
		
    	
         
		    Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		    TransportClient client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
		    
		    for(int i=0; i<arr.size(); i++)
		    {
		    	
		    	// System.out.println(arr.get(i).getDesc().toString().replaceAll("\"", "_"));
		    	 IndexResponse response = client.prepareIndex("test", "pageweb",""+(i+1)+"")
		    			    .setSource(putJsonDocument( arr.get(i).getTitle(),arr.get(i).getDesc(),arr.get(i).getUrlString(),""
		    			    		)).execute().actionGet();
		    }
		
		     
		      System.out.println("Documents indexés");
		      
		      Handling filter1 = new Handling();
		      filter1.filter(mot);
		
    }
    
    // Fonction pour creer un hashmap    
    public static Map<String, Object> putJsonDocument(String titre,String description, String url, String code_source)
    {
            Map<String, Object> jsonDocument = new HashMap<String, Object>();
            jsonDocument.put("titre", titre);
            jsonDocument.put("description", description);
            jsonDocument.put("url", url);
            jsonDocument.put("code_source",code_source );
  
            System.out.println(jsonDocument);
            
            return jsonDocument;
    }
}
