package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.rest;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.PresidentCoursRepository;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.StatistiquesMiageSousLeau;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presidents")
public class PresidentController {

    @Autowired
    PresidentCoursRepository presidentCoursRepository;

    /**
     * Obtenir tous les statistiques de l'association
     *
     * @return
     */
    @PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_PRESIDENT')")
    @GetMapping("/statistiques")
    public StatistiquesMiageSousLeau getStatistiques() {
        return this.presidentCoursRepository.obtenirStatistiques();
    }

}


