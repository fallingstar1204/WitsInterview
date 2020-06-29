package com.wits.technical.pretest.demo.model;

public class ParticipantOM {
    public static Participant newParticipant(Long id, Sex sex) {
        Participant newParticipant = new Participant();
        newParticipant.setId(id);
        newParticipant.setSex(sex);
        return newParticipant;
    }
}
