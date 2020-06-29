package com.wits.technical.pretest.demo.service;

import com.wits.technical.pretest.demo.model.InvestigationResult;
import com.wits.technical.pretest.demo.model.InvestigationResultHolder;
import com.wits.technical.pretest.demo.model.QueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvestigationService {
    @Autowired
    private TransmittalFormRepository transmittalFormRepository;

    public InvestigationResultHolder doInvestigation() {
        InvestigationResultHolder resultHolder = new InvestigationResultHolder();
        List<InvestigationResult> results = new ArrayList<>();

        transmittalFormRepository.getTransmittalForms().stream()
                .filter(form -> form.getExams().stream()
                        .anyMatch(exam -> "MRI".equals(exam.getType())
                                && "T2".equals(exam.getName())
                                && "Knee".equals(exam.getPosition())))
                .filter(form -> "9302".equals(form.getVisit().getStudy().getCode()))
                .forEach(form -> form.getExams().stream()
                        .forEach(exam -> {
                            InvestigationResult result = new InvestigationResult();
                            result.setExamName(exam.getName());
                            result.setExamPosition(exam.getPosition());
                            result.setExamType(exam.getType());
                            result.setVisitName(form.getVisit().getName());
                            result.setParticipantId(form.getVisit().getParticipant().getId());
                            result.setSiteId(form.getVisit().getSite().getId());
                            results.add(result);
                        }));

        resultHolder.setInvestigationResults(results);

        return resultHolder;
    }

    public InvestigationResultHolder doInvestigation(QueryParam queryParam) {
        InvestigationResultHolder resultHolder = new InvestigationResultHolder();
        List<InvestigationResult> results = new ArrayList<>();

        transmittalFormRepository.getTransmittalForms().stream()
                .filter(form -> StringUtils.isNotBlank(queryParam.getStudyCode())
                        && queryParam.getStudyCode().equals(form.getVisit().getStudy().getCode()))
                .filter(form -> queryParam.getSiteId() == null
                        || queryParam.getSiteId().equals(form.getVisit().getSite().getId()))
                .filter(form -> queryParam.getParticipantId() == null
                        || queryParam.getParticipantId().equals(form.getVisit().getParticipant().getId()))
                .filter(form -> StringUtils.isBlank(queryParam.getVisitName())
                        || queryParam.getVisitName().equals(form.getVisit().getName()))
                .filter(form -> form.getExams().stream()
                        .anyMatch(exam -> (StringUtils.isBlank(queryParam.getExamType())
                                    || queryParam.getExamType().equals(exam.getType()))
                                && (StringUtils.isBlank(queryParam.getExamName())
                                    || queryParam.getExamName().equals(exam.getName()))
                                && (StringUtils.isBlank(queryParam.getExamPosition())
                                    || queryParam.getExamPosition().equals(exam.getPosition()))))
                .forEach(form -> form.getExams().stream()
                        .forEach(exam -> {
                            InvestigationResult result = new InvestigationResult();
                            result.setExamName(exam.getName());
                            result.setExamPosition(exam.getPosition());
                            result.setExamType(exam.getType());
                            result.setVisitName(form.getVisit().getName());
                            result.setParticipantId(form.getVisit().getParticipant().getId());
                            result.setSiteId(form.getVisit().getSite().getId());
                            results.add(result);
                        }));

        resultHolder.setInvestigationResults(results);

        return resultHolder;
    }
}
