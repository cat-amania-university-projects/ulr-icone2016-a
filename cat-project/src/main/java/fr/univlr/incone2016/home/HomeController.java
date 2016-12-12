package fr.univlr.incone2016.home;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class HomeController {

	private static final String SEARCH_VIEW_NAME = "home/homeSearch";
	private String searchPhrase;

	@ModelAttribute("module")
	String module() {
		return "home";
	}

	@GetMapping("/")
	String index(Model model, Principal principal) {
		this.searchPhrase = "Votre recherche";
		model.addAttribute("searchPhrase", searchPhrase);
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	String search(@ModelAttribute("searchPhrase") String searchPhrase, BindingResult result, Model model) {
		// Appels de services pour traiter la requete
		// @@@
		// Injecter les résultats pour la vue (sous forme d'objets)
		this.searchPhrase = searchPhrase;
		model.addAttribute("searchPhrase", searchPhrase);
		System.out.println("Recherche : " + searchPhrase);
		return SEARCH_VIEW_NAME;
	}

	@GetMapping("/search")
	String search(Model model) {
		return SEARCH_VIEW_NAME;
	}
}
