package com.wits.technical.pretest.demo.model;

import com.wits.technical.pretest.demo.util.LocalDateAdapter;

import java.time.LocalDate;
import java.util.List;

public class TransmittalFormOM {
    public static TransmittalForm newTransmittalForm(String id, String deliverDateStr, Visit visit, List<Exam> exams) {
        TransmittalForm newTransmittalForm = new TransmittalForm();
        newTransmittalForm.setDeliverDate(LocalDate.parse(deliverDateStr, LocalDateAdapter.formatter));
        newTransmittalForm.setExams(exams);
        newTransmittalForm.setId(id);
        newTransmittalForm.setVisit(visit);

        return newTransmittalForm;
    }
}
