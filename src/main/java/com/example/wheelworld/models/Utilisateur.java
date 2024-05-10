package com.example.wheelworld.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.wheelworld.models.Enumeration.EtatCivil;
import com.example.wheelworld.models.Enumeration.Genre;
import com.example.wheelworld.models.Enumeration.TypeUtilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "utilisateur", uniqueConstraints = @UniqueConstraint(columnNames = {"nomUtilisateur","email"}))
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "type_utilisateur", discriminatorType = DiscriminatorType.STRING)
public class Utilisateur implements Serializable{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idUtilisateur ; 
	
	@Column(name = "NomEtPrenom")
	private String nom;
	
	@Column(name = "nomUtilisateur")
	private String nomUtilisateur;
	
	@Column
	private String adresse;
	
	@Column
	private String password;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Genre genre;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EtatCivil etatCivil;
	
	@Column
	private String email;
	
	@Column
	private Long numTel;
	
	@Column
	private Date dateDeNaissance;

	@Column
	private String roles;
	@Column
	@Enumerated(EnumType.STRING)
	private TypeUtilisateur type_utilisateur;

	@Column
	private Boolean isActive;
	
	
	@OneToMany(mappedBy = "utilisateur")
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JsonIgnore
	private List<Annonce> annonces;


	public Utilisateur() {
		super();
		this.isActive = true;
		// TODO Auto-generated constructor stub
	}


	public Utilisateur(Long idUtilisateur, String nom, String nomUtilisateur, String adresse, String password, Genre genre,
			EtatCivil etatCivil, String email, Long numTel, Date dateDeNaissance, String roles, TypeUtilisateur type_utilisateur, boolean isActive,
			List<Annonce> annonces) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.nomUtilisateur = nomUtilisateur;
		this.adresse = adresse;
		this.password = password;
		this.genre = genre;
		this.etatCivil = etatCivil;
		this.email = email;
		this.numTel = numTel;
		this.dateDeNaissance = dateDeNaissance;
		this.roles = roles;
		this.type_utilisateur = type_utilisateur;
		this.annonces = annonces;
		this.isActive = isActive;
	}

	public Utilisateur(String nom2, String prenom2, String email2, Date dateDeNaissance2, String password2, String roles, String type_utilisateur) {
		// TODO Auto-generated constructor stub
	}


	public Long getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getnomUtilisateur() {
		return nomUtilisateur;
	}


	public void setnomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public EtatCivil getEtatCivil() {
		return etatCivil;
	}


	public void setEtatCivil(EtatCivil etatCivil) {
		this.etatCivil = etatCivil;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getNumTel() {
		return numTel;
	}


	public void setNumTel(Long numTel) {
		this.numTel = numTel;
	}


	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}


	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}

	public TypeUtilisateur getType_utilisateur() {
		return type_utilisateur;
	}

	public void setType_utilisateur(TypeUtilisateur type_utilisateur) {
		this.type_utilisateur = type_utilisateur;
	}

	public List<Annonce> getAnnonces() {
		return annonces;
	}


	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", nomUtilisateur=" + nomUtilisateur + ", adresse="
				+ adresse + ", password=" + password + ", genre=" + genre + ", etatCivil=" + etatCivil + ", email="
				+ email + ", numTel=" + numTel + ", dateDeNaissance=" + dateDeNaissance + ", roles=" + roles + ", typeUtilisateur=" + type_utilisateur
				+ ", annonces=" + annonces + "]";
	}

}
