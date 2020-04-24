package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.rest;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo.EnseignantCoursRepository;
import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {

    @Autowired
    EnseignantCoursRepository enseignantCoursRepository;

    @GetMapping("/{id}")
    public Enseignant getEnseignant(@PathVariable("id") Long id) {
        return enseignantCoursRepository.getEnseignantWithCours(id);
    }
}
