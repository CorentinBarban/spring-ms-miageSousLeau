package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StatistiquesMembre {

    private Integer nbMembres;

    private Integer nbEnseignants;

    private Double cotisationsPrevues;

    private Double cotisationsReglees;

}
