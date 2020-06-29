package com.wits.technical.pretest.demo.model;

public class StudyOM {
    public static Study newStudy(String code) {
        Study newStudy = new Study();
        newStudy.setCode(code);
        return newStudy;
    }
}
