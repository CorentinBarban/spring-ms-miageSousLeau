package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.rest;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.exceptions.*;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.EnseignantCoursRepository;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Cours;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {

    @Autowired
    EnseignantCoursRepository enseignantCoursRepository;

    /**
     * Obtenir tous les cours dispensé par un enseignant
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Enseignant getEnseignant(@PathVariable("id") Long id) {
        try {
            return enseignantCoursRepository.getEnseignantWithCours(id);
        } catch (PiscineNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_ENSEIGNANT')")
    @PostMapping("/cours")
    public Boolean creationCoursEnseignant(@RequestBody Cours cours) {
        try {
            return this.enseignantCoursRepository.creerCoursEnseignant(cours);
        } catch (MembreNotFoundException | CreationCoursException | PiscineNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
