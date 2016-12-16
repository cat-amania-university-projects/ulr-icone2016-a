package fr.univlr.incone2016.home;
import  Module_traitement.Startpage;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.ElasticsearchException;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Exploitation.Connexion;
import Exploitation.ObjetResultat;
import Module_traitement.Collector;
import Module_traitement.IndexDoc;
import Module_traitement.Link;
import Module_traitement.Startpage;


@Controller
class HomeController {

	private static final String SEARCH_VIEW_NAME = "home/homeSearch";
	private String searchPhrase;
	List<ObjetResultat> listResultat = new ArrayList<ObjetResultat>();
	@ModelAttribute("module")
	String module() {
		return "home";
	}

	@GetMapping("/")
	String index(Model model, Principal principal) {
		this.searchPhrase = "";
		model.addAttribute("searchPhrase", searchPhrase);
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	String search(@ModelAttribute("searchPhrase") String searchPhrase, BindingResult result, Model model) throws ElasticsearchException, IOException, JSONException {
		
		// Appels de services pour traiter la requete
		// @@@
		// Injecter les résultats pour la vue (sous forme d'objets)
		
		
		this.searchPhrase = searchPhrase;
		Collector collector = null;
		try {
			collector = new Collector(searchPhrase);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Startpage startpage=new Startpage();
	    ArrayList<Link> arr = collector.getLinklistglob();
		//arr = collector.getLinklistglob();
	  
	    IndexDoc doc= new IndexDoc();
	    doc.recovery(searchPhrase,arr);
	    
		
	    Connexion con=new Connexion();
		con.connecter();
		listResultat = con.getInformation(searchPhrase);
		//model.addAttribute("searchPhrase", searchPhrase);
		model.addAttribute("liste", listResultat);
		
		return SEARCH_VIEW_NAME;
	}

	@GetMapping("/search")
	String search(Model model) {
		return SEARCH_VIEW_NAME;
	}
}
