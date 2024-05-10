package com.example.wheelworld.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.wheelworld.models.Annonce;

import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Long>{



    @Query("SELECT a from Annonce a where a.prix between :minPrice AND :maxPrice and a.categorie ='VENTE'")
    List<Annonce> filterByPriceAnnonce(@Param("minPrice") float minPrice, @Param("maxPrice") float maxPrice);

    @Query("SELECT a from Annonce a where a.prix between :minPrice AND :maxPrice and a.categorie ='LOCATION'")
    List<Annonce> filterByPriceAnnonceL(@Param("minPrice") float minPrice, @Param("maxPrice") float maxPrice);

    @Query("SELECT a from Annonce a where a.categorie ='VENTE'")
    List<Annonce> getVenteAnnonce();

    @Query("SELECT a from Annonce a where a.categorie ='LOCATION'")
    List<Annonce> getLocationAnnonce();

    @Query("SELECT a from Annonce a where a.categorie ='VENTE' order by a.prix ASC")
    List<Annonce> filterVByPriceASC();

    @Query("SELECT a from Annonce a where a.categorie ='VENTE' order by a.prix desc")
    List<Annonce> filterVByPriceDESC();

    @Query("SELECT a from Annonce a where a.categorie ='LOCATION' order by a.prix ASC")
    List<Annonce> filterLByPriceASC();

    @Query("SELECT a from Annonce a where a.categorie ='LOCATION' order by a.prix desc")
    List<Annonce> filterLByPriceDESC();

}

