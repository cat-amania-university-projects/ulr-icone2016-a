package fr.univlr.incone2016.account;



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
import static javafx.scene.Cursor.cursor;

 public  class Connexion{
	static public DB db;
 
 public void connecter(){
	 try{	
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );	
         db = mongoClient.getDB( "Mabase" );
         System.out.println("Connect to database successfully");
     } catch (Exception e) {System.out.println(e);}
   }
 public void getURL(String collection){
	 
     DBCollection coll = db.getCollection(collection);
     System.out.println("-------URL------------");
     
     /*BasicDBObject andQuery = new BasicDBObject();
     List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
     obj.add(new BasicDBObject("Pertinence", 5));
     obj.add(new BasicDBObject("MotClé", "PHP"));
     andQuery.put("$and", obj); */

    // System.out.println(andQuery.toString());
     BasicDBObject whereQuery = new BasicDBObject();
     whereQuery.put("MotClé", "PHP");
     
     BasicDBObject query = new BasicDBObject();
     query.put("Pertinence",-1);
     
     int i=1;
     DBCursor cursor = coll.find(whereQuery).sort(query);
     while (cursor.hasNext()) {
           System.out.println(cursor.next().get("url")); 
            i++;
	}
       
 }
 public void getDescription(String collection){
	 
     DBCollection coll = db.getCollection(collection);
     System.out.println("--------Description---------");
     
    /* BasicDBObject andQuery = new BasicDBObject();
     List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
     obj.add(new BasicDBObject("Pertinence", 5));
     obj.add(new BasicDBObject("MotClé", "PHP"));
     andQuery.put("$and", obj); */
     
     BasicDBObject whereQuery = new BasicDBObject();
     whereQuery.put("MotClé", "PHP");
     
     BasicDBObject query = new BasicDBObject();
     query.put("Pertinence",-1);

    // System.out.println(andQuery.toString());
     int i=1;
     DBCursor cursor = coll.find(whereQuery).sort(query);
     while (cursor.hasNext()) {
           System.out.println(cursor.next().get("description")); 
            i++;
	}
       
 }
  public void getTitre(String collection){
	 
     DBCollection coll = db.getCollection(collection);
     System.out.println("------Titre--------");
     
   /*  BasicDBObject andQuery = new BasicDBObject();
     List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
     obj.add(new BasicDBObject("Pertinence", 5));
     obj.add(new BasicDBObject("MotClé", "PHP"));
     andQuery.put("$and", obj); */

    // System.out.println(andQuery.toString());
     BasicDBObject whereQuery = new BasicDBObject();
     whereQuery.put("MotClé", "PHP");
     
     BasicDBObject query = new BasicDBObject();
     query.put("Pertinence",-1);
     
     int i=1;
     DBCursor cursor = coll.find(whereQuery).sort(query);
     while (cursor.hasNext()) {
           System.out.println(cursor.next().get("Titre")); 
            i++;
	}
       
 }
   public void getTout(String collection){
	 
     DBCollection coll = db.getCollection(collection);
     System.out.println("------Titre--------");
     
   /*  BasicDBObject andQuery = new BasicDBObject();
     List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
     obj.add(new BasicDBObject("Pertinence", 5));
     obj.add(new BasicDBObject("MotClé", "PHP"));
     andQuery.put("$and", obj); */

    // System.out.println(andQuery.toString());
     BasicDBObject whereQuery = new BasicDBObject();
     whereQuery.put("MotClé", "PHP");
     
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
      Connexion ms=new Connexion();
      ms.connecter();
      String mycol="mycol";
      ms.getTout(mycol);
     
    }
 
}

