package WildCodeSchoolProject.GiftMeFive.controller;

import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.DocumentException;
import WildCodeSchoolProject.GiftMeFive.util.WebPageToPdf;

import WildCodeSchoolProject.GiftMeFive.entity.Article;
import WildCodeSchoolProject.GiftMeFive.repository.WishRepository;
import WildCodeSchoolProject.GiftMeFive.util.Encode;


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

	@RequestMapping("/test")
	public String test2() {
		return "test";
	}

	@RequestMapping("/getimage/{id}")
	public ResponseEntity<byte[]> getimage(@PathVariable int id) {

		byte[] image = repository.getImage(1, id);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(image);
	}

	@RequestMapping("/home")
	public String home() {
		return "index";
	}

	@RequestMapping("/wishlistoutput")
	public String wishlistoutput(Model model, @RequestParam String titlename, @RequestParam Long wishlistId) {

		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("topimagelink", repository.getWishlistImage(wishlistId));
		
		model.addAttribute("wishlist", repository.showWishlist(wishlistId));
		String wishlistCsv = repository.makeCsv (model.getAttribute("wishlist"));
		model.addAttribute("wishlistCsv", wishlistCsv);
		return "wishlistoutput";
	}

	@GetMapping("/wishform_list")
	public String wishform_list(Model model, @RequestParam String titlename, @RequestParam Long wishlistId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("imagelink", "/getimage/1");
		model.addAttribute("topimagelink", "/getimage/24");

		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));
		String wishlistCsv = repository.makeCsv (model.getAttribute("wishlist"));
		model.addAttribute("wishlistCsv", wishlistCsv);
		return "wishform_list";
	}

	@GetMapping("/wishlistSaved")
	public String wishformSaved(Model model, @RequestParam String titlename, @RequestParam Long wishlistId,
			@RequestParam String userId, @RequestParam String friendsId) {
		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("userId", userId);
		model.addAttribute("friendsId", friendsId);
		model.addAttribute("topimagelink", repository.getWishlistImage(wishlistId));
		
		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));
		String wishlistCsv = repository.makeCsv (model.getAttribute("wishlist"));
		model.addAttribute("wishlistCsv", wishlistCsv);		
		return "wishlistSaved";
	}

	@PostMapping("/addWish")
	public String addWish(RedirectAttributes redirect, Model model, @RequestParam String articlename,
			@RequestParam String description, @RequestParam Long wishlistId, @RequestParam String titlename,
			@RequestParam String CategoryImage, @RequestParam Long articleId, @RequestParam String productlink) {
//userimage.equals("")
		if (articleId != null) {
			
			String  splitArticleName[] = articlename.split(" ");
			productlink = "https://www.amazon.de/s?k=";
			for(int i=0;i<splitArticleName.length;i++) {
				productlink = productlink+"+"+splitArticleName[i];
			}
			
			repository.editWish(articleId, articlename, description, CategoryImage, productlink, wishlistId);

		} else {
			// Funktion die aus dem eingegebenen Namen ein Amazonsuchlink macht		
			String  splitArticleName[] = articlename.split(" ");
			productlink = "https://www.amazon.de/s?k=";
			for(int i=0;i<splitArticleName.length;i++) {
				productlink = productlink+"+"+splitArticleName[i];
			}
			
			repository.addWish(articlename, description, CategoryImage, productlink, wishlistId);
		}

		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/removeWish")
	public String removeWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String titlename) {

		repository.removeWish(articleId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/reserveWish")
	public String reserveWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String reservationname) {

		repository.reserveWish(articleId, reservationname);

		String titlename = repository.getWishlistname(wishlistId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		model.addAttribute("topimagelink", repository.getWishlistImage(wishlistId));
		
		return "redirect:/wishlistoutput";
	}

	@GetMapping("/unreserveWish")
	public String unreservWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam String articlename, @RequestParam String reservationname, @RequestParam Long wishlistId) {

		repository.unreserveWish(articleId);

		redirect.addAttribute("reservationname", reservationname);

		
		return "redirect:/reservationoutput";
	}

	@GetMapping("/findWishlist")
	public String findWishlist(RedirectAttributes redirect, Model model, @RequestParam String userId) {

		Long wishlistId;
		String[] viewId = userId.split("_");
		wishlistId = Long.parseLong(viewId[1]);

		if (viewId.length == 2) {
			redirect.addAttribute("userId", userId);
			redirect.addAttribute("friendsId", userId + "_friends");
			redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
			redirect.addAttribute("wishlistId", wishlistId);
			return "redirect:/wishlistSaved";
		}

		redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistoutput";
	}

	@GetMapping("/reservationoutput")
	public String resevationoutput(Model model, @RequestParam String reservationname) {

		List<Article> article = repository.showReservations(reservationname);
		if(!(article.size()==0)) {
			Encode en = new Encode();		
			String topimagelink= repository.getWishlistImage(en.encode(article.get(0).getWishlistId()));	

			model.addAttribute("topimagelink", topimagelink);
		}else {
			return "/index";
		}
		
		
		model.addAttribute("wishlist", article);
		model.addAttribute("reservationname", reservationname);
		String wishlistCsv = repository.makeCsv (model.getAttribute("wishlist"));
		model.addAttribute("wishlistCsv", wishlistCsv);		

		return "reservationoutput";
	}

	@GetMapping("/createWishlist")
	public String createWishlist(Model model, @RequestParam String titlename, @RequestParam String enddate,
			RedirectAttributes redirectAttributes) {

		Long wishlistId = repository.createWishlist(titlename, enddate);
		redirectAttributes.addAttribute("titlename", titlename);
		redirectAttributes.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/editWishlist")
	public String editWishlist(RedirectAttributes redirect, Model model, @RequestParam String userId) {

		Long wishlistId;
		String[] viewId = userId.split("_");
		wishlistId = Long.parseLong(viewId[1]);
		redirect.addAttribute("wishlistId", wishlistId);
		redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
		return "redirect:/wishform_list";
	}

	@GetMapping("/editWish")
	public String editWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam String articlename, @RequestParam String description, @RequestParam String imagelink,
			@RequestParam String productlink, @RequestParam String userimage, @RequestParam String categoryImage,
			@RequestParam Long wishlistId) {

		if (userimage.equals("")) {
			repository.editWish(articleId, articlename, description, categoryImage, productlink, wishlistId);
		} else {
			repository.editWish(articleId, articlename, description, userimage, productlink, wishlistId);
		}

		redirect.addAttribute("titlename", repository.getWishlistname(wishlistId));
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishform_list";
	}

	@GetMapping("/loadWish")
	public String loadWish(RedirectAttributes redirect, Model model, @RequestParam Long articleId,
			@RequestParam Long wishlistId, @RequestParam String articlename, @RequestParam String description,
			@RequestParam String imagelink, @RequestParam String productlink) {
		model.addAttribute("articleId", articleId);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("articlename", articlename);
		model.addAttribute("description", description);
		model.addAttribute("imagelink", imagelink);
		model.addAttribute("productlink", productlink);
		model.addAttribute("titlename", repository.getWishlistname(wishlistId));
		model.addAttribute("wishlist", repository.showWishlist(wishlistId));
		return "wishform_list";
	}

	@GetMapping("/saveWishlist")
	public String saveWishlist(RedirectAttributes redirect, @RequestParam String titlename,
			@RequestParam Long wishlistId, @RequestParam int topimage) {

		repository.saveWishListImage(topimage, wishlistId);
		
		titlename = titlename.replaceAll("_", "");
		String userId = titlename + "_" + wishlistId;
		String friendsId = titlename + "_" + wishlistId + "_friends";

		redirect.addAttribute("userId", userId);
		redirect.addAttribute("friendsId", friendsId);
		redirect.addAttribute("titlename", titlename);
		redirect.addAttribute("wishlistId", wishlistId);
		return "redirect:/wishlistSaved";
	}

	@PostMapping("/saveimage")
	public String saveImage(@RequestParam("userimage") MultipartFile file, Model model, @RequestParam String titlename,
			@RequestParam Long wishlistId, @RequestParam String articlename, @RequestParam String description) {
		long imageid = 0;

		try {
			byte[] tmp = file.getBytes();
			imageid = repository.addImage(tmp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("titlename", titlename);
		model.addAttribute("wishlistId", wishlistId);
		model.addAttribute("articlename", articlename);
		model.addAttribute("description", description);
		model.addAttribute("imagelink", "/getimage/" + imageid);
		model.addAttribute("wishlist", repository.showWishlistForm(wishlistId));

		return "wishform_list";
	}

	@RequestMapping("/toHTML")
	public String toPDF (Model model, @RequestParam String titlename, @RequestParam Long wishlistId, @RequestParam String sourceName) throws Exception, DocumentException {
			String pageName = null;
			String userId = titlename + "_" + "wishlistId";
			String friendsId = titlename + "_" + wishlistId + "_friends";
			model.addAttribute("titlename", titlename);
			model.addAttribute("wishlistId", wishlistId);
			
			model.addAttribute("wishlist", repository.showWishlist(wishlistId));
			model.addAttribute("userId", userId);
			model.addAttribute("friendsId", friendsId);
			if (sourceName.equals("wishlistSaved")) {
			pageName = "http://localhost:8080/" + "wishlistSaved?userId=" + userId + "&friendsId=" + friendsId + "&wishlistId=" + wishlistId + "&titlename=" + titlename;
			} else {
			pageName = 	"http://localhost:8080/"+ sourceName + "?wishlistId=" + wishlistId + "&titlename=" + titlename;
			}
			String fileName = sourceName + "_saved.html";
			WebPageToPdf.saveHTML(pageName, fileName);
			
			return sourceName;
	
	}
	@RequestMapping("/toCsv")
	public String toCsv () {
			return "Hallo CSV";
	}
	
}
