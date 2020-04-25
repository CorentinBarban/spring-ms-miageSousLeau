package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.CoursNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.InscriptionException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MauvaisNiveauException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MembreNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;

import java.util.Date;

public interface EnseignantCoursRepository {

    /**
     * Obtenir tous les cours d'un enseignant
     * @param idEnseignant
     * @return
     */

    Enseignant getEnseignantWithCours(Long idEnseignant);

    /**
     * Création d'un cours par un enseignant
     *
     * @param idEnseignant
     * @param idCours
     * @return
     */

    Boolean creerCoursEnseignant(Long idEnseignant, Long idCours, String nom, int niveauCible, String creneau, Date date, long duree) throws MembreNotFoundException, MauvaisNiveauException;

    //Créer un cours (return Cours) : vérifier que l'enseignant existe dans gestion membres, récupérer ses infos et vérifier s'il a la capacité de créer le cours (niveau), et fait appel à la méthode creerCours dans gestionCours
}
