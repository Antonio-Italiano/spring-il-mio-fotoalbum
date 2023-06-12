package org.java.demo;

import java.util.List;
import java.util.Optional;

import org.java.demo.auth.pojo.Role;
import org.java.demo.auth.pojo.User;
import org.java.demo.auth.service.RoleService;
import org.java.demo.auth.service.UserService;
import org.java.demo.pojo.Categorie;
import org.java.demo.pojo.Foto;
import org.java.demo.pojo.Notifiche;
import org.java.demo.service.CategorieService;
import org.java.demo.service.FotoService;
import org.java.demo.service.NotificheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringIlMioFotoalbumApplication implements CommandLineRunner {

	@Autowired
	private FotoService fotoService;
	public static void main(String[] args) {
		SpringApplication.run(SpringIlMioFotoalbumApplication.class, args);
	}
	
	@Autowired
	private CategorieService categorieService;
	
	@Autowired
	private NotificheService notificheService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception{
				
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		final String pws = new BCryptPasswordEncoder().encode("pws");
		
		User userAdmin = new User("user", pws, roleUser);
		User userAdmin1 = new User("user", pws, roleUser);
		User superAdmin = new User("admin", pws, roleAdmin);
				
		userService.save(userAdmin);
		userService.save(userAdmin1);
		userService.save(superAdmin);
		
		//FOTO
		Foto f = new Foto("foto1", "descrizioneFoto1", "https://i0.wp.com/tecnicafotografica.net/wp-content/uploads/2020/05/rbcxo9au-u.jpg?resize=1024%2C683&ssl=1", true, userAdmin);
		Foto f1 = new Foto("foto2", "descrizioneFoto2", "https://i0.wp.com/tecnicafotografica.net/wp-content/uploads/2020/05/rbcxo9au-u.jpg?resize=1024%2C683&ssl=1", true, userAdmin1);
		Foto f2 = new Foto("foto3", "descrizioneFoto3", "https://i0.wp.com/tecnicafotografica.net/wp-content/uploads/2020/05/rbcxo9au-u.jpg?resize=1024%2C683&ssl=1", true, userAdmin);
		
		fotoService.save(f);
		fotoService.save(f1);
		fotoService.save(f2);
		
		List<Foto> fotografie = fotoService.findAll();		
		System.out.println(fotografie);
		
		//CATEGORIE
		Categorie c = new Categorie("Paesaggi naturali");
		Categorie c1 = new Categorie("Architettura");
		Categorie c2 = new Categorie("Animali");
		Categorie c3 = new Categorie("Ritratti");
		Categorie c4 = new Categorie("Eventi");
		Categorie c5 = new Categorie("Viaggi");
		
		categorieService.save(c);
		categorieService.save(c1);
		categorieService.save(c2);
		categorieService.save(c3);
		categorieService.save(c4);
		categorieService.save(c5);	
		
		List<Categorie> categorie = categorieService.findAll();
		System.out.println(categorie);
		
		Notifiche n = new Notifiche("Oggetto 1", "contenuto nuova notifica", f);
		
		notificheService.save(n);
		
		for(Foto foto : fotografie) {
			Optional<Foto> optFoto = fotoService.findByIdWithNotifiche(foto.getId());
			Foto fotoNotifiche = optFoto.get();
			System.out.println("\n"+fotoNotifiche.getNotifiche());
		}
		
	}

}
