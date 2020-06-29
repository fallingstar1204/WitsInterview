package com.wits.technical.pretest.demo.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "transmittalForms")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransmittalFormHolder {
    @XmlElement(name = "transmittalForm")
    private List<TransmittalForm> transmittalForms;

    public List<TransmittalForm> getTransmittalForms() {
        if (transmittalForms == null) {
            transmittalForms = new ArrayList<>();
        }
        return transmittalForms;
    }

    public void setTransmittalForms(List<TransmittalForm> transmittalForms) {
        this.transmittalForms = transmittalForms;
    }
}
