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

    public Date dateCertificat;

    public int niveauPlonge;

    public String etatAptitude;

    public String numLicence;

}
