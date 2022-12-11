package ru.qa_scooter_praktikum_services.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.notNullValue;


public class OrderAssertion {
    @Step("Получение успешного ответа со списком заказов")
    public void checkOrderList(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("orders", notNullValue());
    }
    @Step("Получение успешного ответа об успешном создании заказа с номером отслеживания")
    public void checkOrderCreate(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("track", notNullValue());
    }

}
