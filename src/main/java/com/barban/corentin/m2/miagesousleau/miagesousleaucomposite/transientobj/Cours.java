package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cours {

    public Long idCours;

    private String nom;

    public int niveauCible;

    public Date date;

    private String creneau;

    private long duree;

    private Long idEnseignant;

    //Piscine ?
}
