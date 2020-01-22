package WildCodeSchoolProject.GiftMeFive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import WildCodeSchoolProject.GiftMeFive.repository.WishRepository;

@Controller
public class WishController {
	
	WishRepository repository = new WishRepository();

	@RequestMapping ("/list")
	public String list () {
		return "list";
	}
	
	@RequestMapping ("/list2")
	public String list2 () {
		return "list2";
	}
	
	@GetMapping ("/addWish")
	public String wishform_list (Model model, @RequestParam String name, @RequestParam String beschreibung, @RequestParam String datum,
            @RequestParam String bildlink, @RequestParam String produktlink, @RequestParam String preis, @RequestParam Long wunschliste_id, @RequestParam String titelname) {
		/*Vermutlich redundant:
		model.addAttribute("name", name);
		model.addAttribute("beschreibung", beschreibung);
		model.addAttribute("datum", datum);
		model.addAttribute("bildlink", bildlink);
		model.addAttribute("produktlink", produktlink);
		model.addAttribute("preis", preis);
		model.addAttribute("id", wunschliste_id);
		model.addAttribute("titelname", titelname);*/
		
		System.out.println("Contr: " + wunschliste_id + name + beschreibung + datum + bildlink + produktlink + preis);
		model.addAttribute("artikel", repository.addWish(name, beschreibung, datum, bildlink, produktlink, preis, wunschliste_id, titelname));
		
		model.addAttribute("wishlist", repository.showWishlist(wunschliste_id));
		return "wishform_list";
	}
	
	@GetMapping ("/removeWish")
	public String wishform_list (Model model, @RequestParam Long wunschliste_id, @RequestParam Long id) {
		/*Vermutlich redundant:
		 *model.addAttribute("id", id);
		 */
		repository.removeWish(id); 
		System.out.println("Contr: Wunsch " + id + " gelöscht aus Liste " + wunschliste_id);
		model.addAttribute("wishlist", repository.showWishlist(wunschliste_id));
		return "wishform_list";
	}
	
	
	
	@GetMapping("/findWishlist")
	public String show(Model model, @RequestParam Long id) {

	  model.addAttribute("wishlist", repository.showWishlist(id));
	  return "wishlistoutput";
	}
	
	@GetMapping("/createWishlist")
	public String create(Model model, @RequestParam String name, @RequestParam String datum) {
		
	  model.addAttribute("name", name);
	  model.addAttribute("id", repository.erstellen(name, datum));
	  return "wishform_list";
	}
}
