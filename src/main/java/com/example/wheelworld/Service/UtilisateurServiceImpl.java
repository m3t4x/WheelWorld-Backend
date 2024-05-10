package com.example.wheelworld.Service;

import java.util.List;
import java.util.Optional;

import com.example.wheelworld.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.wheelworld.models.Utilisateur;
//import com.example.WheelWorldback.web.dto.UtilisateurRegistrationDTO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
	@Autowired
	private UserInfoRepository utilisateurRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Utilisateur SaveUtilisateur(Utilisateur utilisateur) {

		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public String addUtilisateur(Utilisateur utilisateur) {
		System.out.println(utilisateur.toString());
		utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
		utilisateurRepository.save(utilisateur);
		return "Utilisateur ajouté avec succées";
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur utilisateur) {

		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public void deleteUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.delete(utilisateur);
		
	}

	@Override
	public void deleteUtilisateurById(Long idUtilisateur) {
		utilisateurRepository.deleteById(idUtilisateur);
		
	}

	@Override
	public Utilisateur getUtilisateurById(Long idUtilisateur) {
		return utilisateurRepository.getById(idUtilisateur);
	}

	@Override
	public List<Utilisateur> getUtilisateurs() {
		
		return (List<Utilisateur>)utilisateurRepository.findAll();
	}

	@Override
	public List<Utilisateur> getAllSortedByNomUtilisateur() {
		return utilisateurRepository.findAllOrdredByUsername();
	}

	@Override
	public List<Utilisateur> getAllSortedByDateDeNaissance() {
		return utilisateurRepository.findAllOrdredByDateDeNaissance();
	}

	@Override
	public List<Utilisateur> searchByNomUtilisateurEmail(String recherche) {
		return utilisateurRepository.searchUtilisateurByNomUtilisateurAndEmail(recherche);
	}

//	@Override
//	public List<Utilisateur> filterByTypeUtilisateur(String typeUtilisateur) {
//		return utilisateurRepository.filterByTypeUtilisateur(typeUtilisateur);
//	}

	public void disableUser(Long idUtilisateur) {
		Optional<Utilisateur> optionalUser = utilisateurRepository.findById(idUtilisateur);
		if (optionalUser.isPresent()) {
			Utilisateur user = optionalUser.get();
			if (user.getActive()) {
				user.setActive(false);
				utilisateurRepository.save(user);
			}
		}
	}
	public void enableUser(Long idUtilisateur) {
		Optional<Utilisateur> optionalUser = utilisateurRepository.findById(idUtilisateur);
		if (optionalUser.isPresent()) {
			Utilisateur user = optionalUser.get();
			if (!user.getActive()) {
				user.setActive(true);
				utilisateurRepository.save(user);
			}
		}
	}

	/* -------------------------------------------------------------------------------------------------------------------------------------- */
}
