package com.example.wheelworld.Service;

import java.util.List;

import com.example.wheelworld.models.Utilisateur;

public interface UtilisateurService {
	Utilisateur SaveUtilisateur (Utilisateur utilisateur);

	String addUtilisateur(Utilisateur utilisateur);
	Utilisateur updateUtilisateur (Utilisateur utilisateur);
	void deleteUtilisateur (Utilisateur utilisateur);
	void deleteUtilisateurById (Long idUtilisateur);
	Utilisateur getUtilisateurById (Long idUtilisateur);
	List<Utilisateur> getUtilisateurs();
	List<Utilisateur> getAllSortedByNomUtilisateur();
	List<Utilisateur> getAllSortedByDateDeNaissance();
	List<Utilisateur> searchByNomUtilisateurEmail(String recherche);
//	List<Utilisateur> filterByTypeUtilisateur(String typeUtilisateur);


}
