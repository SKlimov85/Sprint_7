package ru.qa_scooter_praktikum_services.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;

public class CourierAssertion {
    @Step("Получение успешного ответа на запрос по созданию курьера")
    public int assertCreationSusses(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(201)
                .body("ok", is(true))
                .extract().statusCode();
    }
    @Step("Получение ответа на запрос по созданию курьера без указания пароля/логина")
    public int assertCreationNoLoginOrPassword(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .extract().statusCode();
    }
    @Step("Получение сообщения об ошибке на запрос по созданию дубля курьера")
    public void assertCreationDoubleCourierFailedMessage(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
    }
    @Step("Получение ответа на запрос по созданию дубля курьера")
    public void assertCreationDoubleCourierFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409);
    }

    @Step("Получение ответа на запрос логин курьера в системе")
    public int loginInSusses(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .extract().path("id");
    }
    @Step("Получение ответа на запрос логин курьера в системе с несуществующими данными")
    public void loginInFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));

    }
    @Step("Получение ответа на запрос логин курьера в системе без указания логина/пароля")
    public void loginInNotValueLoginOrPassword(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    public String creationFailed(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(400)
                .body("message", notNullValue())
                .extract()
                .path("message");
    }

    @Step("Получение успешного ответа на запрос по удалению курьера")
    public void deleteSusses(ValidatableResponse response) {
        response.assertThat()
                .statusCode(200)
                .body("ok", is(true));
    }
}

