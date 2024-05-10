package com.example.wheelworld.models;

import com.example.wheelworld.models.Enumeration.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "ANNONCE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Annonce implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAnnonce;
	
	@Column(name = "titre")
	private String titre;
	
	@Column(name = "categorie")
	@Enumerated(EnumType.STRING)
	private CategorieAnnonce categorie;

	@Column(name = "nbChevaux")
	private int nbChevaux;

	@Column(name = "nbCylindres")
	private Long nbCylindres;

	@Column(name = "nbPorte")
	private int nbPorte;

	@Column(name = "boiteVitesse")
	@Enumerated(EnumType.STRING)
	private BoiteVitesse boiteVitesse;

	@Column(name = "typeVehicule")
	@Enumerated(EnumType.STRING)
	private TypeVehicule typeVehicule;

	@Column(name = "kilometrage")
	private Long kilometrage;

	@Column(name = "marque")
	private String marque;

	@Column(name = "description")
	private String description;
	@Column(name = "prix")
	private float prix;

	@Column(name = "etat")
	private Etat etat;

	@Column
	private String images;

	
	@ManyToOne()
	@JoinColumn(name = "UtilisateurID", referencedColumnName = "idUtilisateur")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Utilisateur utilisateur;

	

	public Annonce() {
	}

	public Annonce(Long idAnnonce, String titre, CategorieAnnonce categorie, int nbChevaux, Long nbCylindres, int nbPorte, BoiteVitesse boiteVitesse, TypeVehicule typeVehicule, Long kilometrage, String marque, String description, float prix, Etat etat, String images, Utilisateur utilisateur) {
		this.idAnnonce = idAnnonce;
		this.titre = titre;
		this.categorie = categorie;
		this.nbChevaux = nbChevaux;
		this.nbCylindres = nbCylindres;
		this.nbPorte = nbPorte;
		this.boiteVitesse = boiteVitesse;
		this.typeVehicule = typeVehicule;
		this.kilometrage = kilometrage;
		this.marque = marque;
		this.description = description;
		this.prix = prix;
		this.etat = etat;
		this.images = images;
		this.utilisateur = utilisateur;
	}

	public Long getIdAnnonce() {
		return idAnnonce;
	}

	public void setIdAnnonce(Long idAnnonce) {
		this.idAnnonce = idAnnonce;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public CategorieAnnonce getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieAnnonce categorie) {
		this.categorie = categorie;
	}

	public int getNbChevaux() {
		return nbChevaux;
	}

	public void setNbChevaux(int nbChevaux) {
		this.nbChevaux = nbChevaux;
	}

	public Long getNbCylindres() {
		return nbCylindres;
	}

	public void setNbCylindres(Long nbCylindres) {
		this.nbCylindres = nbCylindres;
	}

	public int getNbPorte() {
		return nbPorte;
	}

	public void setNbPorte(int nbPorte) {
		this.nbPorte = nbPorte;
	}

	public BoiteVitesse getBoiteVitesse() {
		return boiteVitesse;
	}

	public void setBoiteVitesse(BoiteVitesse boiteVitesse) {
		this.boiteVitesse = boiteVitesse;
	}

	public TypeVehicule getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(TypeVehicule typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

	public Long getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(Long kilometrage) {
		this.kilometrage = kilometrage;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return "Annonce{" +
				"idAnnonce=" + idAnnonce +
				", titre='" + titre + '\'' +
				", categorie=" + categorie +
				", nbChevaux=" + nbChevaux +
				", nbCylindres=" + nbCylindres +
				", nbPorte=" + nbPorte +
				", boiteVitesse=" + boiteVitesse +
				", typeVehicule=" + typeVehicule +
				", kilometrage=" + kilometrage +
				", marque='" + marque + '\'' +
				", description='" + description + '\'' +
				", prix=" + prix +
				", etat=" + etat +
				", images='" + images + '\'' +
				", utilisateur=" + utilisateur +
				'}';
	}
}
