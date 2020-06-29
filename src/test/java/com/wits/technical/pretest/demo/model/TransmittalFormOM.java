package com.wits.technical.pretest.demo.model;

import java.time.LocalDate;
import java.util.List;

public class TransmittalFormOM {
    public static TransmittalForm newTransmittalForm(String id, LocalDate deliverDate, Visit visit, List<Exam> exams) {
        TransmittalForm newTransmittalForm = new TransmittalForm();
        newTransmittalForm.setDeliverDate(deliverDate);
        newTransmittalForm.setExams(exams);
        newTransmittalForm.setId(id);
        newTransmittalForm.setVisit(visit);

        return newTransmittalForm;
    }
}
