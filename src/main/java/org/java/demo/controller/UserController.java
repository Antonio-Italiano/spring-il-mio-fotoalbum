package org.java.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.auth.pojo.User;
import org.java.demo.auth.service.UserService;
import org.java.demo.pojo.Categorie;
import org.java.demo.pojo.Foto;
import org.java.demo.service.CategorieService;
import org.java.demo.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	FotoService fotoService;
	
	@Autowired
	CategorieService categorieService;

	@Autowired
	UserService userService;
	
	@GetMapping("/foto")
	public String index(Model model,Authentication authentication) {
		 User user = null;
		    if (authentication != null) {
		        Object principal = authentication.getPrincipal();
		        if (principal instanceof User) {
		            user = (User) principal;
		        }
		    }		
		
		List<Foto> foto = fotoService.findByUser(user);
		
		model.addAttribute("foto", foto);
		return "fotoIndex";
	}
	
	@GetMapping("/foto/filtro")
	public String indexFiltro(Model model,Authentication authentication,@RequestParam(required = false) String titolo) {
		
		 User user = null;
		    if (authentication != null) {
		        Object principal = authentication.getPrincipal();
		        if (principal instanceof User) {
		            user = (User) principal;
		        }
		    }
		
		List<Foto> foto = fotoService.findByTitoloAndUser(titolo,user);
		
		model.addAttribute("foto", foto);
		model.addAttribute("titolo",titolo);
		
		return "fotoIndex";
	}
	
	@GetMapping("/foto/{id}")
	public String show(
			Model model,
			@PathVariable("id") int id
	) {
		
		Optional<Foto> optFoto = fotoService.findById(id);
		Foto foto = optFoto.get();	
		
		List<Categorie> categorie = foto.getCategorie();
		
		model.addAttribute("categorie",categorie);
		model.addAttribute("foto", foto);
		
		return "fotoShow";
	}
	
	@GetMapping("/foto/create")
	public String create(Model model) {		
		
		List<Categorie> categorie = categorieService.findAll();
		
		model.addAttribute("categorie",categorie);
		model.addAttribute("foto",new Foto());	
		
		return "createForm";
	}
	
	@PostMapping("/foto/create")
	public String store(@Valid @ModelAttribute Foto foto, BindingResult bindingResult, Model model,Authentication authentication) {
		 User user = null;
		    if (authentication != null) {
		        Object principal = authentication.getPrincipal();
		        if (principal instanceof User) {
		            user = (User) principal;
		        }
		    }
		
		if(bindingResult.hasErrors()) {
			
			List<Categorie> categorie = categorieService.findAll();
			model.addAttribute("categorie", categorie);
		      
			model.addAttribute("foto",foto);
			model.addAttribute("errori",bindingResult);
			
			return "createForm";
		}
		foto.setUser(user);
		
		fotoService.save(foto);
		
		return "redirect:/admin/foto";
	}
	
	@GetMapping("/foto/delete/{id}")
	public String delete(
			@PathVariable int id
		) {
		
		Optional<Foto> fotoOpt = fotoService.findById(id);
		Foto foto = fotoOpt.get();		

		fotoService.deleteFoto(foto);
		
		return "redirect:/admin/foto";
	}	

	@GetMapping("/foto/update/{id}")
	public String edit(
			Model model,Authentication authentication,
			@PathVariable int id
		) {
		User user = null;
	    if (authentication != null) {
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof User) {
	            user = (User) principal;
	        }
	    }
	
	 model.addAttribute("user",user);	
		
		Optional<Foto> fotoOpt = fotoService.findById(id);
		Foto foto = fotoOpt.get();
		model.addAttribute("foto", foto);
		
		List<Categorie> categorie = categorieService.findAll();
		
		model.addAttribute("categorie",categorie);
		
		return "updateForm";
	}
	
	@PostMapping("/foto/update/{id}")
	public String update(
			  @Valid
		      @ModelAttribute Foto foto,
		      BindingResult bindingResult,
		      @PathVariable int id,
		      Model model,
		      Authentication authentication			
			
		) {
		 User user = null;
		    if (authentication != null) {
		        Object principal = authentication.getPrincipal();
		        if (principal instanceof User) {
		            user = (User) principal;
		        }
		    }
		
		if(bindingResult.hasErrors()) {			
			
			model.addAttribute("foto",foto);
			model.addAttribute("errori",bindingResult);
			
			return "updateForm";
		}
		
		foto.setUser(user);		
		fotoService.save(foto);		
		return "redirect:/admin/foto";
	}	
}
