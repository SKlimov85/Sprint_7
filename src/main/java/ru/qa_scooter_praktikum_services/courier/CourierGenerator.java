package ru.qa_scooter_praktikum_services.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier generic() {
        return new Courier("Pitons8", "Rdfgdfg","Джамшут");
    }
    public Courier randomDataCourier() {
        return new Courier(RandomStringUtils.randomAlphabetic(12), "Rdfgdfg","Джамшут");
    }
}
