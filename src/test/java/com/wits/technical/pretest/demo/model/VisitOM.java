package com.wits.technical.pretest.demo.model;

import com.wits.technical.pretest.demo.util.LocalDateAdapter;

import java.time.LocalDate;

public class VisitOM {
    public static Visit newVisit(String name, String dateStr, Study study, Participant participant, Site site) {
        Visit newVisit = new Visit();
        newVisit.setDate(LocalDate.parse(dateStr, LocalDateAdapter.formatter));
        newVisit.setName(name);
        newVisit.setParticipant(participant);
        newVisit.setSite(site);
        newVisit.setStudy(study);
        return newVisit;
    }
}
