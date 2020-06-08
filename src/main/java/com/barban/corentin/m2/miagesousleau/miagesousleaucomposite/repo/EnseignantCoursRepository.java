package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.*;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Cours;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;

public interface EnseignantCoursRepository {

    /**
     * Obtenir tous les cours d'un enseignant
     *
     * @param idEnseignant
     * @return
     */
    Enseignant getEnseignantWithCours(Long idEnseignant) throws PiscineNotFoundException;

    /**
     * Création d'un cours par un enseignant
     *
     * @param cours
     * @return
     * @throws MembreNotFoundException
     * @throws CreationCoursException
     * @throws PiscineNotFoundException
     */
    Boolean creerCoursEnseignant(Cours cours) throws MembreNotFoundException, CreationCoursException, PiscineNotFoundException;

}
