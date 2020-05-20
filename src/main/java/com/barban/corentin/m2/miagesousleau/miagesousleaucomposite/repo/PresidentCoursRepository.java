package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.StatistiquesMiageSousLeau;

public interface PresidentCoursRepository {

    /**
     * Obtenir tous les statistiques pour le président
     *
     * @return Transient Object StatistiquesMiageSousLeau
     */
    StatistiquesMiageSousLeau obtenirStatistiques();
}
