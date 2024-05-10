package com.example.wheelworld.Service;

import java.util.List;

import com.example.wheelworld.models.Annonce;
import com.example.wheelworld.models.Enumeration.CategorieAnnonce;


public interface AnnonceService {
	
	Annonce SaveAnnonce (Annonce annonce);
	Annonce updateAnnonce (Annonce annonce);
	void deleteAnnonce (Annonce annonce);
	void deleteAnnonceById (Long idAnnonce);
	Annonce getAnnonceById (Long idAnnonce);
	List<Annonce> getAnnonces();
	List<Annonce> filterByPriceAnnonce(float minPrice, float maxPrice);
	List<Annonce> filterByPriceAnnonceL(float minPrice, float maxPrice);
	List<Annonce> getVenteAnnonce();
	List<Annonce> getLocationAnnonce();

	List<Annonce> filterVByPriceASC();
	List<Annonce> filterVByPriceDESC();

	List<Annonce> filterLByPriceASC();
	List<Annonce> filterLByPriceDESC();
	List<Annonce> getAnnonce (CategorieAnnonce category, String typeVehicule,Long nbCylindre , String boiteVitesse, String marque, Long minPrice, Long maxPrice);

}
