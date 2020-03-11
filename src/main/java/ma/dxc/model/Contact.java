
package ma.dxc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;


/**
 * Contact est la classe qui représente un contact.
 * @author dchaa
 *
 */
@Entity
@Where(clause = "deleted = 0")
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	@Size
	private String nom;
	
	@NotNull
	@Size
	private String prenom;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateNaissance;
	
	@NotNull
	@Email
	private String email;
	
	@Size
	@NotNull
	private String tel;
	
	@NotNull
	private String photo;
	
	private boolean deleted = false;

	
	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


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
