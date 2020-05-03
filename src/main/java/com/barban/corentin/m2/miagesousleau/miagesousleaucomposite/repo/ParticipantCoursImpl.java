package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.CoursNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.InscriptionCoursException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MembreNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Cours;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Participant;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.ParticipantWithCours;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class ParticipantCoursImpl implements ParticipantCoursRepository {

    Logger logger = LoggerFactory.getLogger(EnseignantCoursImpl.class);

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplateUser;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplateCours;

    protected String serviceUrlUser;

    protected String serviceUrlCours;

    public ParticipantCoursImpl(String serviceUser, String serviceCours) {
        this.serviceUrlUser = serviceUser;
        this.serviceUrlCours = serviceCours;
    }

    @Override
    public Participant getParticipantWithCours(Long idParticipant) {
        logger.info("Envoi de la demande au service enseignant");
        Participant p = restTemplateUser.getForObject(this.serviceUrlUser + "membres/{id}", Participant.class, idParticipant);
        logger.info("Réponse enseignant reçue : {}", p);

        logger.info("Envoi de la demande au service cours");
        Cours[] listeCours = restTemplateCours.getForObject(this.serviceUrlCours + "/cours/participant?participant={id}", Cours[].class, Long.toString(idParticipant));

        ParticipantWithCours pwc = new ParticipantWithCours();
        pwc.setIdParticipant(p.idParticipant);
        pwc.setNom(p.nom);
        pwc.setPrenom(p.prenom);
        pwc.setDateCertificat(p.getDateCertificat());
        pwc.setNiveauPlonge(p.getNiveauPlonge());
        pwc.setNumLicence(p.getNumLicence());
        pwc.setListeCoursParticipant(new ArrayList<Cours>(Arrays.asList(listeCours)));
        return pwc;
    }

    /**
     * Inscription d'un membre à un cours
     *
     * @param idParticipant
     * @param idCours
     * @return
     */
    @Override
    public Boolean inscriptionCoursParticipant(Long idParticipant, Long idCours) throws MembreNotFoundException, CoursNotFoundException, InscriptionCoursException {
        Participant participant;
        Cours cours;
        Boolean isPossible;

        logger.info("Envoi de la demande d'existence du participant");
        try {
            participant = restTemplateUser.getForObject(this.serviceUrlUser + "membres/{id}", Participant.class, idParticipant);
            logger.info("Réponse participant reçue : {}", participant);
        } catch (HttpClientErrorException e) {
            throw new MembreNotFoundException("Le membre n'existe pas");
        }
        try {
            logger.info("Envoi de la demande d'existence du cours");
            cours = restTemplateCours.getForObject(this.serviceUrlCours + "cours/{id}", Cours.class, idCours);
            logger.info("Réponse cours reçue : {}", cours);
        } catch (HttpClientErrorException e) {
            throw new CoursNotFoundException("Le cours n'existe pas");
        }
        //Verification des conditions d'inscription
        if ((participant.getNiveauPlonge() >= cours.getNiveauCible()) && participant.getEtatAptitude().equals("APTE")) {
            logger.info("Participation OK");
            restTemplateCours.put(this.serviceUrlCours + "cours/{idCours}/inscriptions?participant={id}", Cours.class, idCours, idParticipant);

        } else {
            throw new InscriptionCoursException("Les conditions d'inscription ne sont pas respectées !");
        }

        return true;
    }
}

