package com.wits.technical.pretest.demo.model;

public class ExamOM {
    public static Exam newExam(String type, String position, String name) {
        Exam newExam = new Exam();
        newExam.setName(name);
        newExam.setPosition(position);
        newExam.setType(type);
        return newExam;
    }
}
