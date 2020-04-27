package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MauvaisNiveauException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MembreNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.PiscineNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnseignantCoursImpl implements EnseignantCoursRepository {

    Logger logger = LoggerFactory.getLogger(EnseignantCoursImpl.class);

    @Autowired
    protected RestTemplate restTemplateUser;

    @Autowired
    protected RestTemplate restTemplateCours;

    @Autowired
    protected RestTemplate restTemplatePiscine;

    protected String serviceUrlUser;

    protected String serviceUrlCours;

    protected String serviceUrlPiscine;


    public EnseignantCoursImpl(String serviceUser, String serviceCours, String servicePiscine) {
        this.serviceUrlUser = serviceUser;
        this.serviceUrlCours = serviceCours;
        this.serviceUrlPiscine = servicePiscine;
    }

    @Override
    public Enseignant getEnseignantWithCours(Long idEnseignant) throws PiscineNotFoundException {
        logger.info("Envoi de la demande au service enseignant {}", idEnseignant);
        Enseignant e = restTemplateUser.getForObject(this.serviceUrlUser + "membres/{id}", Enseignant.class, idEnseignant);
        logger.info("Réponse enseignant reçue : {}", e);

        logger.info("Envoi de la demande au service cours");
        CoursWithPiscine[] listeCours = restTemplateCours.getForObject(this.serviceUrlCours + "/cours/enseignant?enseignant={id}", CoursWithPiscine[].class, Long.toString(idEnseignant));
        for (CoursWithPiscine cours : listeCours) {
            try {
                JSONObject mapiscine = new JSONObject(restTemplatePiscine.getForObject(this.serviceUrlPiscine + "/records/{id}", String.class, cours.getIdPiscine()));
                Piscine p = new Piscine();
                p.setRecordid(mapiscine.getString("recordid"));
                p.setAdresse(mapiscine.getJSONObject("fields").getString("adresse"));
                p.setNom_complet(mapiscine.getJSONObject("fields").getString("nom_complet"));
                p.setTelephone(mapiscine.getJSONObject("fields").getString("telephone"));
                cours.setPiscine(p);
            } catch (JSONException excp) {
                throw new PiscineNotFoundException("La piscine n'existe pas");
            }
        }

        EnseignantWithCours ewc = new EnseignantWithCours();
        ewc.setIdEnseignant(e.idEnseignant);
        ewc.setNom(e.nom);
        ewc.setPrenom(e.prenom);
        ewc.setDateCertificat(e.getDateCertificat());
        ewc.setNiveauPlonge(e.getNiveauPlonge());
        ewc.setNumLicence(e.getNumLicence());
        ewc.setListeCoursEnseignant(new ArrayList<>(Arrays.asList(listeCours)));
        return ewc;
    }

    /**
     * Création d'un cours par un enseignant
     *
     * @param cours
     * @return
     */
    @Override
    public Boolean creerCoursEnseignant(Cours cours) throws MembreNotFoundException, MauvaisNiveauException, PiscineNotFoundException {
        Enseignant enseignant;
        logger.info("Envoi de la demande d'existence de l'enseignant");
        try {
            enseignant = restTemplateUser.getForObject(this.serviceUrlUser + "membres/enseignants/{id}", Enseignant.class, cours.getIdEnseignant());
            logger.info("Réponse enseignant reçue : {}", enseignant);
        } catch (HttpClientErrorException e) {
            throw new MembreNotFoundException("L'enseignant n'existe pas");
        }
        logger.info("Envoi de la demande d'existence de la piscine");
        try {
            JSONObject mapiscine = new JSONObject(restTemplatePiscine.getForObject(this.serviceUrlPiscine + "records/{id}", String.class, cours.getIdPiscine()));
            logger.info("piscine Info {}", mapiscine.getJSONObject("fields").getString("nom_complet"));
        } catch (JSONException e) {
            throw new PiscineNotFoundException("La piscine n'existe pas");
        }
        if (enseignant.getNiveauPlonge() >= cours.getNiveauCible()) {
            logger.info("Niveau cible OK : Création du cours ");
            restTemplateCours.postForObject(this.serviceUrlCours + "cours", cours, Cours.class);
        } else {
            throw new MauvaisNiveauException("Les conditions de création ne sont pas respectées : le niveau du cours est supérieur au niveau de l'enseignant");
        }

        return true;
    }
}
