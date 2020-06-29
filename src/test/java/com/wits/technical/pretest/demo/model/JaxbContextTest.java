package com.wits.technical.pretest.demo.model;

import com.wits.technical.pretest.demo.util.LocalDateAdapter;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.Arrays;

@RunWith(JUnit4.class)
public class JaxbContextTest {
    private JAXBContext context;

    @Before
    public void setUp() throws Exception {
        context = JAXBContext.newInstance(TransmittalFormHolder.class);
    }

    @Test
    public void testJaxbMarshal() throws Exception {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        Study study9302 = StudyOM.newStudy("9302");
        Study study9901 = StudyOM.newStudy("9901");

        Site site101SC = SiteOM.newSite(101L, "Santa Clara, US");
        Site site102A = SiteOM.newSite(102L, "Austin, US");
        Site site101T = SiteOM.newSite(101L, "Taipei, Taiwan");
        Site site102SC = SiteOM.newSite(102L, "Santa Clara, US");

        Participant participant101001M = ParticipantOM.newParticipant(101001L, Sex.M);
        Participant participant101001F = ParticipantOM.newParticipant(101001L, Sex.F);
        Participant participant102001M = ParticipantOM.newParticipant(102001L, Sex.M);

        Visit visit_t1 = VisitOM.newVisit("Baseline", "2020/Feb/24", study9302, participant101001M, site101SC);
        Visit visit_t2 = VisitOM.newVisit("Baseline", "2020/Feb/23", study9302, participant102001M, site102A);
        Visit visit_t3 = VisitOM.newVisit("Followup1", "2020/Aug/20", study9302, participant101001M, site101SC);
        Visit visit_t4 = VisitOM.newVisit("Followup1", "2020/Aug/23", study9302, participant102001M, site102A);
        Visit visit_t5 = VisitOM.newVisit("Baseline", "2020/Oct/10", study9901, participant101001F, site101T);
        Visit visit_t6 = VisitOM.newVisit("Followup1", "2020/Oct/23", study9901, participant102001M, site102SC);

        Exam exam1 = ExamOM.newExam("MRI", "Brain", "T1");
        Exam exam2 = ExamOM.newExam("MRI", "Brain", "T2");
        Exam exam3 = ExamOM.newExam("PET", "Brain", "T2");
        Exam exam4 = ExamOM.newExam("MRI", "Knee", "T2");

        TransmittalForm t1 = TransmittalFormOM.newTransmittalForm("T1001", "2020/Feb/25", visit_t1, Arrays.asList(exam1, exam2));
        TransmittalForm t2 = TransmittalFormOM.newTransmittalForm("T1002", "2020/Feb/26", visit_t2, Arrays.asList(exam1, exam2));
        TransmittalForm t3 = TransmittalFormOM.newTransmittalForm("T1003", "2020/Aug/29", visit_t3, Arrays.asList(exam1, exam2, exam3));
        TransmittalForm t4 = TransmittalFormOM.newTransmittalForm("T1004", "2020/Aug/26", visit_t4, Arrays.asList(exam1, exam4));
        TransmittalForm t5 = TransmittalFormOM.newTransmittalForm("T1005", "2020/Oct/20", visit_t5, Arrays.asList(exam1, exam2));
        TransmittalForm t6 = TransmittalFormOM.newTransmittalForm("T1006", "2020/Oct/26", visit_t6, Arrays.asList(exam1, exam4));

        TransmittalFormHolder transmittalFormHolder = new TransmittalFormHolder();
        transmittalFormHolder.setTransmittalForms(Arrays.asList(t1, t2, t3, t4, t5, t6));

        try (StringWriter sw = new StringWriter(); InputStream is = JaxbContextTest.class.getResourceAsStream("/AppendixTransmittalForms.xml")) {
            marshaller.marshal(transmittalFormHolder, sw);
            String marshalStr = sw.toString();
            String expectedStr = IOUtils.toString(is, "UTF-8");
            Assert.assertEquals(expectedStr, marshalStr);
        }
    }

    @Test
    public void testJaxbUnMarshal() throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TransmittalFormHolder transmittalFormHolder;
        TransmittalForm transmittalForm;
        try (InputStream is = JaxbContextTest.class.getResourceAsStream("/TransmittalFormExample.xml")) {
            transmittalFormHolder = (TransmittalFormHolder) unmarshaller.unmarshal(is);
            transmittalForm = transmittalFormHolder.getTransmittalForms().get(0);
            Visit visit = transmittalForm.getVisit();
            Study study = visit.getStudy();
            Site site = visit.getSite();
            Participant participant = visit.getParticipant();
            Exam exam1 = transmittalForm.getExams().get(0);
            Exam exam2 = transmittalForm.getExams().get(1);

            Assert.assertEquals("Baseline", visit.getName());
            Assert.assertEquals(LocalDate.parse("2020/Oct/10", LocalDateAdapter.formatter), visit.getDate());
            Assert.assertEquals("9901", study.getCode());
            Assert.assertEquals(101L, site.getId().longValue());
            Assert.assertEquals("Taipei, Taiwan", site.getLocation());
            Assert.assertEquals(101001L, participant.getId().longValue());
            Assert.assertEquals(Sex.F, participant.getSex());
            Assert.assertEquals("T1005", transmittalForm.getId());
            Assert.assertEquals(LocalDate.parse("2020/Oct/20", LocalDateAdapter.formatter), transmittalForm.getDeliverDate());
            Assert.assertEquals("MRI", exam1.getType());
            Assert.assertEquals("Brain", exam1.getPosition());
            Assert.assertEquals("T1", exam1.getName());
            Assert.assertEquals("MRI", exam2.getType());
            Assert.assertEquals("Brain", exam2.getPosition());
            Assert.assertEquals("T2", exam2.getName());
        }
    }
}
