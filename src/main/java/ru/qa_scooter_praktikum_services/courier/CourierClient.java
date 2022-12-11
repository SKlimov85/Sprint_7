package ru.qa_scooter_praktikum_services.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;


public class CourierClient extends ru.qa_scooter_praktikum_services.Client {

    protected final String ROOT = "/courier";
    @Step("Отправить запрос на создание курьера")
    public ValidatableResponse create(Courier courierCreate) {
        return spec()
                .body(courierCreate)
                .when()
                .post(ROOT)
                .then().log().all();

    }
    @Step("Отправить запрос на вход под логином курьера")
    public ValidatableResponse login(Creadentials courierLogin) {
        return spec()
                .body(courierLogin)
                .when()
                .post(ROOT + "/login")
                .then().log().all();
    }
    @Step("Отправить запрос на удаление курьера")
    public ValidatableResponse delete(int courierId, CourierDelete delete) {
        return spec()
                .body(delete)
                .when()
                .delete(ROOT + "/" + courierId)
                .then().log().all();
    }


}


