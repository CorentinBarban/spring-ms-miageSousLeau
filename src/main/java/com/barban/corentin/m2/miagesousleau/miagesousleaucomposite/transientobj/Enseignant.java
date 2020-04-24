package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Enseignant implements Serializable {

    public Long idEnseignant;

    public String nom;

    public String prenom;

    private Date dateCertificat;

    private int niveauPlonge;

    private String numLicence;

}
