package org.java.demo.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notifiche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String email;
	private String messaggio;
	private LocalDate data;
	
	//-----------RELAZIONI---------------
	@ManyToOne
	@JsonBackReference
	private Foto foto;
	
	public Foto getFoto() {
		return foto;
	}
	public void setFoto(Foto foto) {
		this.foto = foto;
	}
	//-----------------------------------
	
	
	public Notifiche() {
		data = LocalDate.now();
	}
	public Notifiche(String email, String messaggio, Foto foto) {
		
		setEmail(email);
		setMessaggio(messaggio);
		data = LocalDate.now();
		setFoto(foto);
	}
	
	//GETTER SETTER
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "[" + getId() + "]" + getEmail() 
		+ " , DATA: " + getData() 
		+ " , CONTENUTO: " + getMessaggio();
	}

}
