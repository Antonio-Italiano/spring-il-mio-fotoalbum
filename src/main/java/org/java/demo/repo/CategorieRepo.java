package org.java.demo.repo;

import org.java.demo.pojo.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie, Integer>{

}
