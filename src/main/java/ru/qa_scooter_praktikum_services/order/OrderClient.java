package ru.qa_scooter_praktikum_services.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

public class OrderClient extends ru.qa_scooter_praktikum_services.Client {

    protected final String ROOT = "/orders";

    @Step("Отправка запроса на получение списка заказов")
    public ValidatableResponse orderList() {
        return spec()
                .when()
                .get(ROOT)
                .then();
    }
    @Step("Отправка запроса на создание заказова")
    public ValidatableResponse orderCreate(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ROOT)
                .then();
    }

}
