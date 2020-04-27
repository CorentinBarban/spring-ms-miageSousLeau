package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cours {

    public Long idCours;

    public String nom;

    public int niveauCible;

    public Date date;

    public String creneau;

    public Long duree;

    public Long idEnseignant;

    public String idPiscine;

    public List<Long> listeParticipants;
}
