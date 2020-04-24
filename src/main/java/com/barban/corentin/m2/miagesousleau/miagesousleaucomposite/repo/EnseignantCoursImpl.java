package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Cours;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.EnseignantWithCours;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class EnseignantCoursImpl implements EnseignantCoursRepository{

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
        Enseignant e = restTemplateUser.getForObject(this.serviceUrlUser+"/{id}",Enseignant.class, idEnseignant);
        logger.info("Réponse enseignant reçue : {}", e);

        logger.info("Envoi de la demande au service cours");
        Cours[] listeCours = restTemplateCours.getForObject(this.serviceUrlCours+"?enseignant={id}", Cours[].class, idEnseignant);

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
}
