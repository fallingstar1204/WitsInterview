package com.wits.technical.pretest.demo.service;

import com.wits.technical.pretest.demo.model.TransmittalForm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransmittalFormRepository {
    private final List<TransmittalForm> transmittalForms = Collections.synchronizedList(new ArrayList<>());

    public List<TransmittalForm> getTransmittalForms() {
        return transmittalForms;
    }
}
