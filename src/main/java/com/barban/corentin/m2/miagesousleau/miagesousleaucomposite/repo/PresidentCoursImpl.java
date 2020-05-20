package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.StatistiquesCours;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.StatistiquesMembre;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.StatistiquesMiageSousLeau;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

public class PresidentCoursImpl implements PresidentCoursRepository {

    Logger logger = LoggerFactory.getLogger(EnseignantCoursImpl.class);

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplateUser;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplateCours;


    protected String serviceUrlUser;

    protected String serviceUrlCours;


    public PresidentCoursImpl(String serviceUser, String serviceCours) {
        this.serviceUrlUser = serviceUser;
        this.serviceUrlCours = serviceCours;
    }

    /**
     * Obtenir tous les statistiques pour le président
     *
     * @return Transient Object StatistiquesMiageSousLeau
     */
    @Override
    public StatistiquesMiageSousLeau obtenirStatistiques() {
        logger.info("Obtention des statistiques globales");
        StatistiquesMembre statMembre = restTemplateUser.getForObject(this.serviceUrlUser + "membres/presidents/statistiques", StatistiquesMembre.class);
        StatistiquesCours statCours = restTemplateCours.getForObject(this.serviceUrlCours + "cours/statistiques", StatistiquesCours.class);
        return new StatistiquesMiageSousLeau(statMembre, statCours);
    }
}
