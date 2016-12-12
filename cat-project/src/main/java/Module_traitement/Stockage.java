package Module_traitement;


import com.mongodb.DB;
import com.mongodb.DBCollection;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;





public class Stockage {
	 private DB db;
	    
	    public Stockage()
	    {
	         // Connexion a la base de donnee
	        MongoClient mongoClient = null;
	       
	       
	            mongoClient = new MongoClient( "localhost" , 27017 );
	       
	       
	        db = mongoClient.getDB( "testbd" ); // Declaration de la base donnée
	        
	        // Fin connexion        
	    }
	    
	    
	     // Medthode de récupération des données d'une collection
	    /*public List<DBObject> selection(String name_collection)
	    {
	        DBCollection collection = db.getCollection(name_collection); 
	        List<DBObject> lst= collection.find().toArray(); // Stocker toutes les resultats dans une liste
	        return lst; // retourne une liste d'enregistrement de la collection indiquée
	    }*/
	    
	    // Methode "insertion" prend en paramètre la collection et l'objet document a inseré
	    public void insertion(String name_collection,DBObject docObject)
	    {
	        DBCollection collection = db.getCollection(name_collection); 
	        collection.insert(docObject);
	        System.out.println("Insertion reuissi !");
	    }
	    
	    //  Methode "update"
	    
}

