
package ma.dxc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

/**
 * Contact est la classe qui représente un contact.
 * @author dchaa
 *
 */
@Entity
public class Contact implements Serializable {
	
	@Id @GeneratedValue
	private Long id;
	
	@NotNull(message = "Veuillez saisir votre nom !")
	@Size(min=4,max=16,message="votre nom doit etre compris entre 4 et 16 caractères")
	private String nom;
	
	@NotNull(message = "Veuillez saisir votre prénom !")
	@Size(min=4,max=16,message="votre prénom doit etre compris entre 4 et 16 caractères")
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Veuillez saisir votre date de naissance !")
	private Date dateNaissance;
	
	@NotNull(message = "Veuillez saisir votre email !")
	@Email
	private String email;
	
	@Size(min=10,max=10,message="votre numero de téléphone doit contenir 10 caractères")
	@NotNull(message = "Veuillez saisir votre numero de telephone !")
	private String tel;
	
	@NotNull
	private String photo;
	
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Contact(String nom, String prenom, Date dateNaissance, String email, String tel, String photo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.tel = tel;
		this.photo = photo;
	}
	
	@Override
	public String toString() {
		return "Nom :"+this.nom+", Prenom:"+this.prenom+" email:"+this.email+"telephone:"+this.tel;
	}
	
	
	

}
