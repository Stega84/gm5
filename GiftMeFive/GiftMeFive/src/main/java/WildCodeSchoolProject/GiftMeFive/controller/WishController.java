package WildCodeSchoolProject.GiftMeFive.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import WildCodeSchoolProject.GiftMeFive.entity.User;
import WildCodeSchoolProject.GiftMeFive.repository.WishRepository;

@Controller
public class WishController {

	WishRepository repository = new WishRepository();

	@RequestMapping("/list")
	public String list() {
		return "list";
	}

	@RequestMapping("/list2")
	public String list2() {
		return "list2";
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@GetMapping("wishform_list")
	public String wishform_list(Model model, @RequestParam String name, @RequestParam Long id) {
		model.addAttribute("name", name);
		model.addAttribute("id", id);

		model.addAttribute("wishlist", repository.showWishlist(id));
		return "wishform_list";
	}

	@GetMapping("/addWish")
	public String wishform_list(RedirectAttributes redirect, Model model, @RequestParam String name,
			@RequestParam String beschreibung, @RequestParam String bildlink, @RequestParam String preis,
			@RequestParam Long wunschliste_id, @RequestParam String titelname, @RequestParam String kategorie) {

		String produktlink = "https://www.amazon.de/s?k=play+Station";
		
		System.out.println(kategorie);
		/* Vermutlich redundant: */
		model.addAttribute("name", name);
		model.addAttribute("beschreibung", beschreibung);
		model.addAttribute("bildlink", bildlink);
		model.addAttribute("produktlink", produktlink);
		model.addAttribute("preis", preis);
		model.addAttribute("id", wunschliste_id);
		model.addAttribute("titelname", titelname);

		System.out.println("Contr: " + wunschliste_id + name + beschreibung + bildlink + produktlink + preis);

		if (bildlink.equals("")) {
			model.addAttribute("artikel", repository.addWish(name, beschreibung, "image/lp.jpg", produktlink,
					preis, wunschliste_id, titelname));
		} else {
			model.addAttribute("artikel",
					repository.addWish(name, beschreibung, bildlink, produktlink, preis, wunschliste_id, titelname));
		}

		model.addAttribute("wishlist", repository.showWishlist(wunschliste_id));
		redirect.addAttribute("name", titelname);
		redirect.addAttribute("id", wunschliste_id);
		return "redirect:/wishform_list";
	}

	@GetMapping("/removeWish")
	public String removewish_fromform(RedirectAttributes redirect, Model model, @RequestParam Long id,
			@RequestParam Long wunschliste_id, @RequestParam String name) {
		/*
		 * Vermutlich redundant: model.addAttribute("id", id);
		 */
		repository.removeWish(id);
		System.out.println("Contr: Wunsch " + id + " gelöscht aus " + wunschliste_id);
		redirect.addAttribute("name", name);
		redirect.addAttribute("id", wunschliste_id);
		return "redirect:/wishform_list";
	}
	
	@GetMapping("/reserveWish")
	public String reservewishDo(RedirectAttributes redirect, Model model, @RequestParam Long id,  @RequestParam Long wunschliste_id, @RequestParam String name) {
		System.out.println("Contr: Prüfe Reservierung Wunsch " + id + " in Liste " + wunschliste_id + " für " + name);
		repository.reserveWish(id, name);
		System.out.println("Contr: Wunsch " + id + " in Liste " + wunschliste_id + " reserviert für " + name);
		//redirect.addAttribute("name", name);
		redirect.addAttribute("id", wunschliste_id);
		return "redirect:/findWishlist";
		//return "redirect:/wishlistoutput";
	}

	@GetMapping("/findWishlist")
	public String show(Model model, @RequestParam Long id) {

		model.addAttribute("wishlist", repository.showWishlist(id));
		return "wishlistoutput";
	}

	@GetMapping("/createWishlist")
	public String create(Model model, @RequestParam String name, @RequestParam String datum,
			RedirectAttributes redirectAttributes) {

		Long id = repository.erstellen(name, datum);
		redirectAttributes.addAttribute("name", name);
		redirectAttributes.addAttribute("id", id);
		return "redirect:/wishform_list";
	}

//	@PostMapping("/createWishlist")
//	public String create(@ModelAttribute User user, Model model) {
//		System.out.println(user.getName());
//	  model.addAttribute("name", user.getName());
//	  model.addAttribute("id", repository.erstellen(user.getName(), user.getDatum()));
//	  return "wishlist_form";
//	}
}
