package org.java.demo.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
			
	//--------------RELAZIONI----------
	@ManyToMany(mappedBy = "categorie")
	@JsonBackReference
	private List<Foto> fotografie;
	
	public List<Foto> getFotografie() {
		return fotografie;
	}
	public void setFotografie(List<Foto> fotografie) {
		this.fotografie = fotografie;
	}
	//---------------------------------
	public Categorie() {}
	public Categorie(String nome) {
		setNome(nome);
	}
	
	//GETTER SETTER
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "\nID: " + getId() 
				+ ",  NOME: " + getNome() + "";
	}
}
