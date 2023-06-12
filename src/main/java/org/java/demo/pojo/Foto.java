package org.java.demo.pojo;

import java.util.List;

import org.java.demo.auth.pojo.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Foto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String titolo;
	private String descrizione;
	private String url;
	private boolean visibile;
	
	//--------------RELAZIONI-----------------
	@ManyToMany
	private List<Categorie> categorie;
	
	public List<Categorie> getCategorie() {
		return categorie;
	}
	public void setCategorie(List<Categorie> categorie) {
		this.categorie = categorie;
	}	
	
	//----
	
	@OneToMany(mappedBy = "foto")
	@JsonManagedReference
	private List<Notifiche> notifiche;
	
	public List<Notifiche> getNotifiche() {
		return notifiche;
	}
	public void setNotifiche(List<Notifiche> notifiche) {
		this.notifiche = notifiche;
	}
	
	//----
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private User user;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//---------------------------------------
	
	
	public Foto() {}
	public Foto(String titolo, String descrizione, String url, boolean visibile, User user) {
		
		setTitolo(titolo);
		setDescrizione(descrizione);
		setUrl(url);
		setVisibile(visibile);
		setUser(user);
	}
	
	//GETTER SETTER
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isVisibile() {
		return visibile;
	}
	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}
	
	@Override
	public String toString() {
		return "ID: " + getId() 
				+ "\nTITOLO: " + getTitolo()
				+ "\nTITOLO: " + getTitolo() 
				+ "\nDESCRIZIONE :" + getDescrizione() 
				+ "\nURL : " + getUrl()
				+ "\nVISIBILE: " + isVisibile() + "\n";
	}
}
