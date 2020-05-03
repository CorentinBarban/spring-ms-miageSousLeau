package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.CoursNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.InscriptionCoursException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MembreNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Participant;

public interface ParticipantCoursRepository {
    /**
     * Obtenir tous les cours d'un participant
     *
     * @param idParticipant
     * @return
     */
    Participant getParticipantWithCours(Long idParticipant);

    /**
     * Inscription d'un membre à un cours
     *
     * @param idParticipant
     * @param idCours
     * @return
     */
    Boolean inscriptionCoursParticipant(Long idParticipant, Long idCours) throws MembreNotFoundException, CoursNotFoundException, InscriptionCoursException;
}
