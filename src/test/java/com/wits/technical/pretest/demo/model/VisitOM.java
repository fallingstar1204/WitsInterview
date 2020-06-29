package com.wits.technical.pretest.demo.model;

import java.time.LocalDate;

public class VisitOM {
    public static Visit newVisit(String name, LocalDate date, Study study, Participant participant, Site site) {
        Visit newVisit = new Visit();
        newVisit.setDate(date);
        newVisit.setName(name);
        newVisit.setParticipant(participant);
        newVisit.setSite(site);
        newVisit.setStudy(study);
        return newVisit;
    }
}
