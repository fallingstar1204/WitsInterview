package com.wits.technical.pretest.demo.service;

import com.wits.technical.pretest.demo.model.InvestigationResult;
import com.wits.technical.pretest.demo.model.InvestigationResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestigationService {
    @Autowired
    private TransmittalFormRepository transmittalFormRepository;

    public InvestigationResultHolder doInvestigation() {
        InvestigationResultHolder resultHolder = new InvestigationResultHolder();
        List<InvestigationResult> results =
                transmittalFormRepository.getTransmittalForms().stream()
                        .filter(form -> form.getExams().stream()
                                .anyMatch(exam -> "MRI".equals(exam.getType())
                                        && "T2".equals(exam.getName())
                                        && "Knee".equals(exam.getPosition())))
                        .filter(form -> "9302".equals(form.getVisit().getStudy().getCode()))
                        .map(form -> {
                            InvestigationResult result = new InvestigationResult();
                            result.setExamName("T2");
                            result.setExamPosition("Knee");
                            result.setExamType("MRI");
                            result.setVisitName(form.getVisit().getName());
                            result.setParticipantId(form.getVisit().getParticipant().getId());
                            result.setSiteId(form.getVisit().getSite().getId());
                            return result;
                        })
                        .collect(Collectors.toList());

        resultHolder.setInvestigationResults(results);

        return resultHolder;
    }
}
