package com.barban.corentin.m2.miagesousleau.miagesousleaucomposite.transientobj;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParticipantWithCours extends Participant {

    public List<Cours> listeCoursParticipant;

}
