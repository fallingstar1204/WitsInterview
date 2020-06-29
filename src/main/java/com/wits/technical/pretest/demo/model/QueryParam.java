package com.wits.technical.pretest.demo.model;

public class QueryParam {
    private String studyCode;
    private Long siteId;
    private Long participantId;
    private String visitName;
    private String examName;
    private String examType;
    private String examPosition;

    public String getStudyCode() {
        return studyCode;
    }

    public Long getSiteId() {
        return siteId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public String getVisitName() {
        return visitName;
    }

    public String getExamName() {
        return examName;
    }

    public String getExamType() {
        return examType;
    }

    public String getExamPosition() {
        return examPosition;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String studyCode;
        private Long siteId;
        private Long participantId;
        private String visitName;
        private String examName;
        private String examType;
        private String examPosition;

        public Builder withStudyCode(String studyCode) {
            this.studyCode = studyCode;
            return this;
        }

        public Builder withSiteId(Long siteId) {
            this.siteId = siteId;
            return this;
        }

        public Builder withParticipantId(Long participantId) {
            this.participantId = participantId;
            return this;
        }

        public Builder withVisitName(String visitName) {
            this.visitName = visitName;
            return this;
        }

        public Builder withExam(String examName, String examType, String examPosition) {
            this.examName = examName;
            this.examPosition = examPosition;
            this.examType = examType;
            return this;
        }

        public QueryParam build() {
            QueryParam queryParam = new QueryParam();
            queryParam.examName = examName;
            queryParam.examPosition = examPosition;
            queryParam.examType = examType;
            queryParam.visitName = visitName;
            queryParam.participantId = participantId;
            queryParam.siteId = siteId;
            queryParam.studyCode = studyCode;

            return queryParam;
        }
    }
}
