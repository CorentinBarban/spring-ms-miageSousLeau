package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;

public interface EnseignantCoursRepository {

    /**
     * Obtenir tous les cours d'un enseignant
     * @param idEnseignant
     * @return
     */

    Enseignant getEnseignantWithCours(Long idEnseignant);

    //Créer un cours (return Cours) : vérifier que l'enseignant existe dans gestion membres, récupérer ses infos et vérifier s'il a la capacité de créer le cours (niveau), et fait appel à la méthode creerCours dans gestionCours
}
