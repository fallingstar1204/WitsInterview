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
import java.util.List;

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
        Study study = StudyOM.newStudy("9901");
        Site site = SiteOM.newSite(101L, "Taipei, Taiwan");
        Participant participant = ParticipantOM.newParticipant(101001L,Sex.F);
        Visit visit = VisitOM.newVisit("Baseline", LocalDate.parse("2020/Oct/10", LocalDateAdapter.formatter), study, participant, site);
        List<Exam> exams = Arrays.asList(ExamOM.newExam("MRI", "Brain", "T1"), ExamOM.newExam("MRI", "Brain", "T2"));
        TransmittalForm newTransmittalForm = TransmittalFormOM.newTransmittalForm("T1005", LocalDate.parse("2020/Oct/20", LocalDateAdapter.formatter), visit, exams);
        TransmittalFormHolder transmittalFormHolder = new TransmittalFormHolder();
        transmittalFormHolder.getTransmittalForms().add(newTransmittalForm);

        try (StringWriter sw = new StringWriter(); InputStream is = JaxbContextTest.class.getResourceAsStream("/TransmittalFormExample.xml")) {
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
