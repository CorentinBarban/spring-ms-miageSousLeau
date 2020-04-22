package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.repo;

import com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj.Enseignant;

public interface EnseignantCoursRepository {

    Enseignant getEnseignantWithCours(Long idEnseignant);
}
