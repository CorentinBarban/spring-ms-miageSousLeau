package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Participant implements Serializable {

    public Long idParticipant;

    public String nom;

    public String prenom;

    private String adresseMail;

    private String login;

    private String villeResidence;

    private String paysResidence;

    private Date dateCertificat;

    private int niveauPlonge;

    private String numLicence;

}
