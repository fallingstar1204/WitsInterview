package com.wits.technical.pretest.demo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "investigationResults")
@XmlAccessorType(XmlAccessType.FIELD)
public class InvestigationResultHolder {
    @XmlElement(name = "investigationResult")
    private List<InvestigationResult> investigationResults;

    public List<InvestigationResult> getInvestigationResults() {
        if (investigationResults == null) {
            investigationResults = new ArrayList<>();
        }
        return investigationResults;
    }

    public void setInvestigationResults(List<InvestigationResult> investigationResults) {
        this.investigationResults = investigationResults;
    }
}
