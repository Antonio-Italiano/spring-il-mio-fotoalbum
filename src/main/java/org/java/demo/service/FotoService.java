package org.java.demo.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.demo.auth.pojo.User;
import org.java.demo.pojo.Foto;
import org.java.demo.repo.FotoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepo fotoRepo;
	
	public List<Foto> findAll() {
		return fotoRepo.findAll();
	}
	
	public Foto save(Foto foto) {
		return fotoRepo.save(foto);
	}
	
	public Optional<Foto> findById(int id) {
		return fotoRepo.findById(id);
	}
	
	public List<Foto> findByTitolo(String titolo){
		return fotoRepo.findByTitoloContaining(titolo);
	}
	
	public void deleteFoto(Foto foto) {
		fotoRepo.delete(foto);
	}
	
	public List<Foto> findByUser(User user){
		return fotoRepo.findByUser(user);
	}
	
	public List<Foto> findByVisibileEquals(Boolean visibile) {
		return fotoRepo.findByVisibileEquals(visibile);
	}
	
	public List<Foto> findByTitoloAndUser(String titolo,User user) {
		return fotoRepo.findByTitoloContainingAndUser(titolo,user);
	}
	
	public List<Foto> findByTitoloAndVisibileEquals(String nome,boolean visibile) {	
		return fotoRepo.findByTitoloContainingAndVisibileEquals(nome,visibile);
	}
	
	@Transactional
	public Optional<Foto> findByIdWithNotifiche(int id){
		Optional<Foto> optFoto = fotoRepo.findById(id);
		Hibernate.initialize(optFoto.get().getNotifiche());
		return optFoto;
	}
}
