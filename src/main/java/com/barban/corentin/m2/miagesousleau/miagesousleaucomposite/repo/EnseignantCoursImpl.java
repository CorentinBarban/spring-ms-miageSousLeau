package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.CoursNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MauvaisNiveauException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MembreNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Cours;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.EnseignantWithCours;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EnseignantCoursImpl implements EnseignantCoursRepository {

    Logger logger = LoggerFactory.getLogger(EnseignantCoursImpl.class);

    @Autowired
    protected RestTemplate restTemplateUser;

    @Autowired
    protected RestTemplate restTemplateCours;

    protected String serviceUrlUser;

    protected String serviceUrlCours;

    public EnseignantCoursImpl(String serviceUser, String serviceCours) {
        this.serviceUrlUser = serviceUser;
        this.serviceUrlCours = serviceCours;
    }

    @Override
    public Enseignant getEnseignantWithCours(Long idEnseignant) {
        logger.info("Envoi de la demande au service enseignant");
        Enseignant e = restTemplateUser.getForObject(this.serviceUrlUser + "/{id}", Enseignant.class, idEnseignant);
        logger.info("Réponse enseignant reçue : {}", e);

        logger.info("Envoi de la demande au service cours");
        Cours[] listeCours = restTemplateCours.getForObject(this.serviceUrlCours + "?enseignant={id}", Cours[].class, idEnseignant);

        EnseignantWithCours ewc = new EnseignantWithCours();
        ewc.setIdEnseignant(e.idEnseignant);
        ewc.setNom(e.nom);
        ewc.setPrenom(e.prenom);
        ewc.setDateCertificat(e.getDateCertificat());
        ewc.setNiveauPlonge(e.getNiveauPlonge());
        ewc.setNumLicence(e.getNumLicence());
        ewc.setListeCoursEnseignant(new ArrayList<Cours>(Arrays.asList(listeCours)));
        return ewc;
    }

    /**
     * Création d'un cours par un enseignant
     *
     * @param idEnseignant
     * @param idCours
     * @return
     */
    @Override
    public Boolean creerCoursEnseignant(Long idEnseignant, Long idCours, String nom, int niveauCible, String creneau, Date date, long duree) throws MembreNotFoundException, MauvaisNiveauException {
        Enseignant enseignant;
        Cours cours;

        logger.info("Envoi de la demande d'existence de l'enseignant");
        try {
            enseignant = restTemplateUser.getForObject(this.serviceUrlUser + "/membres/enseignants/{id}", Enseignant.class, idEnseignant);
            logger.info("Réponse enseignant reçue : {}", enseignant);
        } catch (HttpClientErrorException e) {
            throw new MembreNotFoundException("L'enseignant n'existe pas");
        }

        if ((enseignant.getNiveauPlonge() >= niveauCible)) {
            logger.info("Niveau cible OK : Création du cours ");
            cours = new Cours();
            cours.setIdCours(idCours);
            cours.setNom(nom);
            cours.setNiveauCible(niveauCible);
            cours.setDate(date);
            cours.setDuree(duree);
            cours.setIdEnseignant(enseignant.getIdEnseignant());
            //Il manque la sélection de la piscine
            restTemplateCours.postForObject(this.serviceUrlCours + "/cours", cours, Cours.class);
        } else {
            throw new MauvaisNiveauException("Les conditions de création ne sont pas respectées : le niveau du cours est supérieur au niveau de l'enseignant");
        }

        return true;
    }
}
