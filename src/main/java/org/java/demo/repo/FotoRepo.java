package org.java.demo.repo;

import java.util.List;

import org.java.demo.auth.pojo.User;
import org.java.demo.pojo.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepo extends JpaRepository<Foto, Integer>{

	public List<Foto> findByTitoloContaining(String titolo);
	List<Foto> findByVisibileEquals(Boolean visibile);
	List<Foto> findByUser(User user);
	List<Foto> findByTitoloContainingAndUser(String titolo, User user);
	List<Foto> findByTitoloContainingAndVisibileEquals(String nome, boolean visibile);
}
