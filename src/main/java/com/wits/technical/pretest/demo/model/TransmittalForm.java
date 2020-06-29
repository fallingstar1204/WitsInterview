package com.wits.technical.pretest.demo.model;

import com.wits.technical.pretest.demo.util.LocalDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransmittalForm {
    private String id;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate deliverDate;
    private Visit visit;
    @XmlElementWrapper(name = "exams")
    @XmlElement(name = "exam")
    private List<Exam> exams;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(LocalDate deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public List<Exam> getExams() {
        if (exams == null) {
            exams = new ArrayList<>();
        }
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public static class Builder {

    }
}
