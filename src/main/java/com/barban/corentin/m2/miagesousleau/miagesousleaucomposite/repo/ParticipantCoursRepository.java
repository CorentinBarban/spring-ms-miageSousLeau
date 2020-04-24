package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Participant;

public interface ParticipantCoursRepository {

    Participant getParticipantWithCours(Long idParticipant);
}
