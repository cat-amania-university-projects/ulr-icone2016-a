package Module_traitement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.util.ArrayList;
import org.elasticsearch.action.search.*;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import com.mongodb.DBObject;
public class Handling {
	
	public void filter(String mot_cle){
       
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
        
        

        SearchResponse response = client.prepareSearch("test")
                .setQuery(QueryBuilders.multiMatchQuery(mot_cle, "titre", "description","code_source" ))
                .execute()
                .actionGet();
        
        
        String json = "";
     
        StoreDB stock =new StoreDB();
        
        ArrayList concurrent = new ArrayList() ;
        String fichier ="concurrents.txt";
       
	      //lecture du fichier texte et stockage dans un tableau
	     try{
	         InputStream ips=new FileInputStream(fichier);
	         InputStreamReader ipsr=new InputStreamReader(ips);
	         BufferedReader br=new BufferedReader(ipsr);
	         String ligne;
	         while ((ligne=br.readLine())!=null){
	            concurrent.add(ligne);
	         }
	         br.close();
	      }catch (Exception e){
	          System.out.println(e.toString());
	      }
	     
	   
	     Boolean found;// Pour la recherche des concurrents
	     
        for(SearchHit hit : response.getHits())
        {
        	String nom_concurrent="";
	        	for(int i=0; i<concurrent.size(); i++)
	        	{
	        		String src=hit.getSource().get("code_source").toString(); // Recuperation du code source 
	            	found = src.contains(concurrent.get(i).toString()); // verifier si un concurrent existe dans le code source
	        
	            	if(found) // Si oui on declare son nom
	            	{
	            		nom_concurrent=nom_concurrent+", "+concurrent.get(i).toString();
	            	}
	        	}
        	json="{\n" +
        			"	 \"titre\":\""+hit.getSource().get("titre")+"\",\n" +
        			"	 \"description\":\""+hit.getSource().get("description")+"\",\n" +
        			"	 \"url\":\""+hit.getSource().get("url")+"\",\n" ;
        			if(nom_concurrent!=""){
        				json+="\"concurrent\":\""+nom_concurrent+"\",\n";
        				json+="\"pertinence\":\""+(hit.getScore()+1)+"\",\n" ;
        			}else
        			{
        				json+="	\"pertinence\":\""+hit.getScore()+"\",\n";
        			}
        				json+="	 \"mot_cle\":\""+mot_cle+"\"\n" +
        			"}";
        	Object o = com.mongodb.util.JSON.parse(json);
            DBObject dbObj = (DBObject) o;
            
            stock.insertion("recherche",dbObj);
        	 System.out.println(json);
        }
        
                
     }
         
         
 }
     
 
         
       
    
    
    

