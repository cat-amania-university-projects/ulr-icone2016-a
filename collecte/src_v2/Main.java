/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.cat.amania;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 *
 * @author abdoul
 */
public class Main {
    public static void main(String[] argv ) throws IOException{
    Startpage startpage=new Startpage();
    startpage.getResults("php"); // le mot clé rentré est "php"
        //HashMap<String, URL> results = startpage.getResults("php");
        //System.out.print(results);
        //System.out.print(startpage.getTitle());
    
    }
}
