package com.example.wheelworld.Service;

import java.util.List;

import com.example.wheelworld.models.Enumeration.CategorieAnnonce;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wheelworld.Repository.AnnonceRepository;
import com.example.wheelworld.models.Annonce;

@Service
public class AnnonceServiceImpl implements AnnonceService{
	
	@Autowired
	private AnnonceRepository annonceRepository;

	@PersistenceContext
	private EntityManager em;
	public List<Annonce> getAnnonce (CategorieAnnonce category,
									 String typeVehicule,
									 Long nbCylindre,
									 String boiteVitesse,
									 String marque,
									 Long minPrice,
									 Long maxPrice){
		var cb = em.getCriteriaBuilder();
		var cq = cb.createQuery(Annonce.class);
		var root = cq.from(Annonce.class);
		Predicate condition = cb.equal(root.get("categorie"), category);
		if(typeVehicule != null){
			condition = condition == null ? cb.equal(root.get("typeVehicule"), typeVehicule) : cb.and(condition, cb.equal(root.get("typeVehicule"), typeVehicule));
		}
		if(nbCylindre != null){
			condition = condition == null ? cb.equal(root.get("nbCylindre"), nbCylindre) : cb.and(condition, cb.equal(root.get("nbCylindre"), nbCylindre));
		}
		if(boiteVitesse != null){
			condition = condition == null ? cb.equal(root.get("boiteVitesse"), boiteVitesse) : cb.and(condition, cb.equal(root.get("boiteVitesse"), boiteVitesse));
		}
		if(marque != null){
			condition = condition == null ? cb.equal(root.get("marque"), marque) : cb.and(condition, cb.equal(root.get("marque"), marque));
		}
		if(minPrice != null){
			condition = condition == null ? cb.greaterThanOrEqualTo(root.get("prix"), minPrice) : cb.and(condition, cb.greaterThanOrEqualTo(root.get("prix"), minPrice));
		}
		if(maxPrice != null){
			condition = condition == null ? cb.lessThanOrEqualTo(root.get("prix"), maxPrice) : cb.and(condition, cb.lessThanOrEqualTo(root.get("prix"), maxPrice));
		}
		if(condition != null){
			cq.where(condition);
		}
		var tq = em.createQuery(cq);
		System.out.println(cq);
		return tq.getResultList();

	}

	@Override
	public Annonce SaveAnnonce(Annonce annonce) {
		return annonceRepository.save(annonce);
	}

	@Override
	public Annonce updateAnnonce(Annonce annonce) {
		return annonceRepository.save(annonce);
	}

	@Override
	public void deleteAnnonce(Annonce annonce) {
		annonceRepository.delete(annonce);
		
	}

	@Override
	public void deleteAnnonceById(Long idAnnonce) {
		annonceRepository.deleteById(idAnnonce);
		
	}

	@Override
	public Annonce getAnnonceById(Long idAnnonce) {
		
		return annonceRepository.getById(idAnnonce);
	}

	@Override
	public List<Annonce> getAnnonces() {
		return (List<Annonce>)annonceRepository.findAll();
	}

	@Override
	public List<Annonce> filterByPriceAnnonce(float minPrice, float maxPrice) {
		return annonceRepository.filterByPriceAnnonce(minPrice, maxPrice);
	}

	@Override
	public List<Annonce> filterByPriceAnnonceL(float minPrice, float maxPrice) {
		return annonceRepository.filterByPriceAnnonceL(minPrice, maxPrice);
	}

	@Override
	public List<Annonce> getVenteAnnonce() {
		return annonceRepository.getVenteAnnonce();
	}

	@Override
	public List<Annonce> getLocationAnnonce() {
		return annonceRepository.getLocationAnnonce();
	}

	@Override
	public List<Annonce> filterVByPriceASC() {
		return annonceRepository.filterVByPriceASC();
	}

	@Override
	public List<Annonce> filterVByPriceDESC() {
		return annonceRepository.filterVByPriceDESC();
	}

	@Override
	public List<Annonce> filterLByPriceASC() {
		return annonceRepository.filterLByPriceASC();
	}

	@Override
	public List<Annonce> filterLByPriceDESC() {
		return annonceRepository.filterLByPriceDESC();
	}



}
