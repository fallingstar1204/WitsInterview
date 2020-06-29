package com.wits.technical.pretest.demo;

import com.wits.technical.pretest.demo.model.TransmittalFormHolder;
import com.wits.technical.pretest.demo.service.TransmittalFormRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public TransmittalFormRepository transmittalFormRepository() throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(TransmittalFormHolder.class);
		TransmittalFormRepository transmittalFormRepository = new TransmittalFormRepository();
		Unmarshaller unmarshaller = context.createUnmarshaller();
		try (InputStream is = DemoApplication.class.getResourceAsStream("/AppendixTransmittalForms.xml")) {
			TransmittalFormHolder transmittalFormHolder = (TransmittalFormHolder) unmarshaller.unmarshal(is);
			transmittalFormRepository.getTransmittalForms().addAll(transmittalFormHolder.getTransmittalForms());
		}

		return transmittalFormRepository;
	}
}
