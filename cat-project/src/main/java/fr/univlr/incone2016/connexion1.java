package fr.univlr.incone2016;


/**
 *
 * @author Hamadou
 */

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.ServerAddress;
import java.util.Arrays;



import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Scanner;
import static javafx.scene.Cursor.cursor;

 public  class connexion1{
	static public DB db;
 
 public void connecter(){
	 try{	
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
         
         // Connexion a la base de données Mabase
         db = mongoClient.getDB( "Mabase" );
         System.out.println("Connexion a la base reussie");
     } catch (Exception e) {System.out.println(e);}
   }
   public void getInformation(String mot){
	 
     DBCollection coll = db.getCollection("mycol");

   
     BasicDBObject whereQuery = new BasicDBObject();
     whereQuery.put("MotClé", mot);
     
     BasicDBObject query = new BasicDBObject();
     query.put("Pertinence",-1);
     
     int i=1;
     DBCursor cursor = coll.find(whereQuery).sort(query);
     while (cursor.hasNext()) {
         BasicDBObject obj = (BasicDBObject) cursor.next();
         
           System.out.println(obj.getString("Titre")); 
           System.out.println(obj.getString("description")); 
           System.out.println(obj.getString("url"));
           System.out.println("\n------------------------ \n\n");
            i++;
	}
       
 }
 public static void main(String[] args) {
      connexion1 ms=new connexion1();
      ms.connecter();
      System.out.println("Veuillez saisire le mot clé");
      Scanner sc = new Scanner(System.in);
      String motClé = sc.nextLine();
      String mycol="mycol";
      ms.getInformation(motClé);
     
    }
 
}

