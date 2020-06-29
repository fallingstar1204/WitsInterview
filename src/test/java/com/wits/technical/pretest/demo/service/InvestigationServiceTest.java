package com.wits.technical.pretest.demo.service;

import com.wits.technical.pretest.demo.model.InvestigationResultHolder;
import com.wits.technical.pretest.demo.model.JaxbContextTest;
import com.wits.technical.pretest.demo.model.QueryParam;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import java.io.InputStream;
import java.io.StringWriter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvestigationServiceTest {
    @Autowired
    private InvestigationService investigationService;
    private JAXBContext context;

    @Before
    public void setUp() throws Exception {
        context = JAXBContext.newInstance(InvestigationResultHolder.class);
    }

    @Test
    public void doInvestigation() throws Exception {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        InvestigationResultHolder resultHolder = investigationService.doInvestigation();

        try (StringWriter sw = new StringWriter(); InputStream is = JaxbContextTest.class.getResourceAsStream("/InvestigationResults.xml")) {
            marshaller.marshal(resultHolder, sw);
            String marshalStr = sw.toString();
            String expectedStr = IOUtils.toString(is, "UTF-8");
            Assert.assertEquals(expectedStr, marshalStr);
        }
    }

    @Test
    public void doInvestigationWithQueryParam() throws Exception {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        QueryParam queryParam =
                QueryParam.builder()
                        .withStudyCode("9302")
                        .withExam("T2", "MRI", "Knee")
                        .build();

        InvestigationResultHolder resultHolder = investigationService.doInvestigation(queryParam);

        try (StringWriter sw = new StringWriter(); InputStream is = JaxbContextTest.class.getResourceAsStream("/InvestigationResults.xml")) {
            marshaller.marshal(resultHolder, sw);
            String marshalStr = sw.toString();
            String expectedStr = IOUtils.toString(is, "UTF-8");
            Assert.assertEquals(expectedStr, marshalStr);
        }
    }
}