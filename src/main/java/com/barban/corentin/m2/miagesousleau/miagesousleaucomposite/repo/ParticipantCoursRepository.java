package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Participant;

public interface ParticipantCoursRepository {
    /**
     * Obtenir tous les cours d'un participant
     * @param idParticipant
     * @return
     */
    Participant getParticipantWithCours(Long idParticipant);

    //S'inscrire à un cours
}
