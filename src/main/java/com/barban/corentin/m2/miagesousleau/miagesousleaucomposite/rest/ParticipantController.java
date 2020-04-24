package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.rest;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.CoursNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.MembreNotFoundException;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.ParticipantCoursRepository;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Cours;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    ParticipantCoursRepository participantCoursRepository;

    /**
     * Obtenir tous les cours d'un participant
     *
     * @param idParticipant
     * @return
     */
    @GetMapping("/{id}")
    public Participant getParticipant(@PathVariable("id") Long idParticipant) {
        return this.participantCoursRepository.getParticipantWithCours(idParticipant);
    }


    @PostMapping("/{idParticipant}/inscription/{idCours}")
    public Cours inscriptionCoursParticipant(@PathVariable("idParticipant") Long idParticipant,@PathVariable("idCours") Long idCours) {
        try {
            return this.participantCoursRepository.inscriptionCoursParticipant(idParticipant,idCours);
        } catch (MembreNotFoundException | CoursNotFoundException | InscriptionException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
