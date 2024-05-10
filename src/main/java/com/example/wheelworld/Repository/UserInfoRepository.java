package com.example.wheelworld.Repository;

import com.example.wheelworld.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByNomUtilisateur(String username);
    @Query("select u from Utilisateur u order by u.nomUtilisateur")
    List<Utilisateur> findAllOrdredByUsername();
    @Query("select u from Utilisateur u order by u.dateDeNaissance")
    List<Utilisateur> findAllOrdredByDateDeNaissance();
    @Query("SELECT u from Utilisateur u where u.nomUtilisateur like %?1% or u.email like %?1%")
    List<Utilisateur> searchUtilisateurByNomUtilisateurAndEmail(String recherche);
//    @Query("SELECT u from Utilisateur u where u.type_utilisateur like %?1%")
//    List<Utilisateur> filterByTypeUtilisateur(String typeUtilisateur);

}
