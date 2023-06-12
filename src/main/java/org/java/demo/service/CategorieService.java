package org.java.demo.service;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Categorie;
import org.java.demo.repo.CategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategorieService {

	@Autowired
	private CategorieRepo categorieRepo;
	
	public List<Categorie> findAll(){
		return categorieRepo.findAll();
	}
	
	public Optional<Categorie> findById(int id) {
		return categorieRepo.findById(id);
	}
	
	public Categorie save(Categorie categorie) {
		return categorieRepo.save(categorie);
	}
	
	public void deleteCategorie(Categorie categorie) {
		categorieRepo.delete(categorie);
	}
}
