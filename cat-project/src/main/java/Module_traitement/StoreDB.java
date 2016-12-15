package Module_traitement;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;





public class StoreDB {
	 private DB db;
	 public MongoClient mongoClient = null;
	    public StoreDB()
	    {
	         // Connexion a la base de donnee
	         
	        mongoClient = new MongoClient( "localhost" , 27017 );
	        db = mongoClient.getDB( "testbd" ); // Declaration de la base donnée
	        
	        // Fin connexion        
	    }
	    
	    
	    public void insertion(String name_collection,DBObject docObject)
	    {
	        DBCollection collection = db.getCollection(name_collection); 
	        
	        collection.insert(docObject);
	        System.out.println("Insertion reuissie !");
	    }
	    
	    //  Methode "update"
	    
	  /*  public void update(Object url,String name_collection,DBObject docObject)
	    {
	        DBCollection collection = db.getCollection(name_collection); 
        	BasicDBObject updateDocument = new BasicDBObject();
 	  	    updateDocument.append("$set", docObject);
 	  	    BasicDBObject searchQuery2 = new BasicDBObject().append("url", url);
 	  	    collection.update(searchQuery2, updateDocument);
 	        System.out.println("Mise à jour reuissie !");
	        
	    }*/
	  /*  public void request(Object url,String name_collection,DBObject docObject)
	    {
	        DBCollection collection = db.getCollection(name_collection); 
	        BasicDBObject whereQuery = new BasicDBObject();
	        whereQuery.put("url", url);
	        if(collection.find(whereQuery)!=null){
	        	update(url,name_collection,docObject);
	        }else{
	        	insertion(name_collection, docObject);
	        }
	        
	        mongoClient.close();
	    }
	    */
}

