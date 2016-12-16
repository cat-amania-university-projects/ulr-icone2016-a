package Module_traitement;



import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class StoreDB {
	 protected DB db;
	 protected MongoClient mongoClient = new MongoClient();
	
	    public StoreDB()
	    {
		   
	    	 mongoClient = new MongoClient( "localhost" , 27017 ); 
	    	 db = mongoClient.getDB( "testbd" ); // Declaration de la base donnée
	    	 System.out.println("connexion reussie !");
	        // Fin connexion        
	    }
	    
	  
	    /*public void insertion(DBObject docObject)
	    {
	        DBCollection collection = db.getCollection("recherche");  
			collection.insert(docObject);
			 System.out.println("insertion reussie !");
	    }
	    */
	   
	    public void updateORinsert(DBObject docObject)
	    {
	        DBCollection collection = db.getCollection("recherche"); 
	        DBObject modifiedObject =new BasicDBObject();
	        modifiedObject.put("$inc", new BasicDBObject().append("hits", 1));   		
	        collection.update(docObject, modifiedObject,true,false);
	      
	       
	    }
	  //  System.out.println("insertion reussie!!!");
}

