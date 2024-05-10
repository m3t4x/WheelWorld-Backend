package com.example.wheelworld.Web;


import com.example.wheelworld.Repository.UserInfoRepository;
import com.example.wheelworld.Service.*;
import com.example.wheelworld.models.*;
import com.example.wheelworld.models.Enumeration.CategorieAnnonce;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
@RestController
@RequestMapping("/WheelWorld")
public class WheelWorldController {
    @Autowired
    private UtilisateurServiceImpl utilisateurService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AnnonceServiceImpl annonceService;
    @Autowired
    private ImageService imageService;

    @Autowired
    private PaytmDetails paytmDetails;
    @Autowired
    private Environment environment;
    @Autowired
    private final EmailSenderService emailSenderService;

    @Autowired
    private UserInfoRepository userInfoRepository;
    public WheelWorldController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @Autowired
    private SMSService smsService;

    /*--------------------------------------------Utilisateur------------------------------------------------*/

    @GetMapping("/welcome")
    public String welcome() {
        return "Bienvenue sur WheelWorld";
    }

    @PostMapping("/Utilisateur/inscription")
    public ResponseEntity addUser(@RequestBody Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);

        return ResponseEntity.ok("Utilisateur ajouté");

    }

    @PostMapping("/Utilisateur/reclamation")

        public void reclamation(@RequestBody Utilisateur utilisateur,@PathVariable String Subject,@PathVariable String body)
    {
         this.emailSenderService.sendReclamation(utilisateur.getEmail(),"rheastia4@gmail.com",Subject,body);
    }

    /*
    header.component.ts:127  TypeError: Converting circular structure to JSON
    --> starting at object with constructor 'FormControl'
    |     property '_parent' -> object with constructor 'FormGroup'
    |     property 'controls' -> object with constructor 'Object'
    --- property 'nomUtilisateur' closes the circle
     */

    @PostMapping("/Utilisateur/Connexion")
    public LOginResponse authenticateAndGetToken(@RequestBody Utilisateur authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getnomUtilisateur(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            Utilisateur utilisateur= userInfoRepository.findByNomUtilisateur(authRequest.getnomUtilisateur()).get();
            var response = new LOginResponse(jwtService.generateToken(utilisateur));
            response.currentUser = utilisateur;
            return response;

        }else{
            throw new UsernameNotFoundException("Informations Incorrectes !");
        }
    }

    @GetMapping("/ListeDesUtilisateurs")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utilisateur> FindAllUsers() {
        return utilisateurService.getUtilisateurs();
    }

    @GetMapping("/Utilisateur/{id}")
    public Utilisateur FindUserById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @DeleteMapping("/SupprimerUtilisateur/{id}")
    public LOginResponse RemoveUser(@PathVariable Long id) {
        utilisateurService.deleteUtilisateurById(id);
        return new LOginResponse("bb");
    }

    @PostMapping("/ModifierUtilisateur")
    public Utilisateur UpdateUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur UtilisateurExist = utilisateurService.getUtilisateurById(utilisateur.getIdUtilisateur());
        System.out.println(utilisateur.getNumTel());
        UtilisateurExist.setNom(utilisateur.getNom());
        UtilisateurExist.setnomUtilisateur(utilisateur.getnomUtilisateur());
        UtilisateurExist.setAdresse(utilisateur.getAdresse());
        UtilisateurExist.setPassword(utilisateur.getPassword());
        UtilisateurExist.setGenre(utilisateur.getGenre());
        UtilisateurExist.setEtatCivil(utilisateur.getEtatCivil());
        UtilisateurExist.setEmail(utilisateur.getEmail());
        UtilisateurExist.setNumTel(utilisateur.getNumTel());
        UtilisateurExist.setDateDeNaissance(utilisateur.getDateDeNaissance());
        Utilisateur savedUtilisateur = utilisateurService.updateUtilisateur(UtilisateurExist);
        return savedUtilisateur;
    }

    @GetMapping("/trierUtilisateurParNomUtilisateur")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utilisateur> FindAllAnnoncesSorterdByNomUtilisateurAsc() {
        return utilisateurService.getAllSortedByNomUtilisateur();
    }
    @GetMapping("/trierUtilisateurParDateDeNaissance")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utilisateur> FindAllAnnoncesSorterdByDatedeNaissanceAsc() {
        return utilisateurService.getAllSortedByDateDeNaissance();
    }

    @GetMapping("/rechercheUtilisateur/{recherche}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utilisateur> SearchByNomUtilisateurEmail(@PathVariable String recherche) {
        return utilisateurService.searchByNomUtilisateurEmail(recherche);
    }


    @PostMapping("/{idUtilisateur}/block")
    public void blockUser(@PathVariable Long idUtilisateur) {
        utilisateurService.disableUser(idUtilisateur);
    }

    @PostMapping("/{idUtilisateur}/unblock")
    public void unblockUser(@PathVariable Long idUtilisateur) {
        utilisateurService.enableUser(idUtilisateur);
    }



    /* ---------------------------------------------Annonce------------------------------------------------------*/

    @PostMapping("/addAnnonce")
    public ResponseEntity addannonce(@RequestBody Annonce annonce) {
        annonceService.SaveAnnonce(annonce);

        return ResponseEntity.ok("Utilisateur ajouté");

    }

    @GetMapping("/ListeDesAnnonces")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Annonce> FindAllAnnonces() {
        return annonceService.getAnnonces();
    }

    @GetMapping("/annonce/{id}")
   // @PreAuthorize("hasAuthority('USER')")
    public Annonce FindAnnonceById(@PathVariable Long id) {
        return annonceService.getAnnonceById(id);
    }

    @DeleteMapping("/supprimerAnnonce/{id}")
//    @PreAuthorize("hasAuthority('USER')")
    public void RemoveAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonceById(id);
    }

    @DeleteMapping("/supprimerToutesLesAnnonces")
    @PreAuthorize("hasAuthority('USER')")
    public String RemoveAllAnnonce(Annonce annonce) {
        annonceService.deleteAnnonce(annonce);
        return "Toutes les annonces ont été supprimées";
    }

    @PutMapping("/modifierAnnonce/{id}")
    //@PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> UpdateAnnonce(@PathVariable Long id, @RequestBody Annonce annonce) {
        Annonce AnnonceExist = annonceService.getAnnonceById(id);
        System.out.println(AnnonceExist.getIdAnnonce());
        AnnonceExist.setTitre(annonce.getTitre());
        AnnonceExist.setCategorie(annonce.getCategorie());
        AnnonceExist.setNbChevaux(annonce.getNbChevaux());
        AnnonceExist.setNbCylindres(annonce.getNbCylindres());
        AnnonceExist.setNbPorte(annonce.getNbPorte());
        AnnonceExist.setBoiteVitesse(annonce.getBoiteVitesse());
        AnnonceExist.setTypeVehicule(annonce.getTypeVehicule());
        AnnonceExist.setKilometrage(annonce.getKilometrage());
        AnnonceExist.setMarque(annonce.getMarque());
        AnnonceExist.setEtat(annonce.getEtat());
        AnnonceExist.setImages(annonce.getImages());
        AnnonceExist.setDescription(annonce.getDescription());
        AnnonceExist.setPrix(annonce.getPrix());

        Annonce savedAnnonce = annonceService.SaveAnnonce(AnnonceExist);
        return ResponseEntity.ok().body(savedAnnonce);
    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/Vente")
//    @PreAuthorize("hasAuthority('USER')")
   public List<Annonce> getVenteAnnonce(
                                        @Nullable @RequestParam(required = false) String typeVehicule,
                                        @Nullable @RequestParam(required = false) Long nbCylindre,
                                        @Nullable @RequestParam(required = false) String boiteVitesse,
                                        @Nullable @RequestParam(required = false) String marque,
                                        @Nullable @RequestParam(required = false) Long minPrice,
                                        @Nullable @RequestParam(required = false) Long maxPrice){

        return annonceService.getAnnonce(CategorieAnnonce.VENTE,typeVehicule,nbCylindre,boiteVitesse,marque,minPrice,maxPrice);
    }

    @GetMapping("/Location")
//    @PreAuthorize("hasAuthority('USER')")
    public List<Annonce> getLocationAnnonce (
            @Nullable @RequestParam(required = false) String typeVehicule,
            @Nullable @RequestParam(required = false) Long nbCylindre,
            @Nullable @RequestParam(required = false) String boiteVitesse,
            @Nullable @RequestParam(required = false) String marque,
            @Nullable @RequestParam(required = false) Long minPrice,
            @Nullable @RequestParam(required = false) Long maxPrice){

       return annonceService.getAnnonce(CategorieAnnonce.LOCATION,typeVehicule,nbCylindre,boiteVitesse,marque,minPrice,maxPrice);
    }

}
