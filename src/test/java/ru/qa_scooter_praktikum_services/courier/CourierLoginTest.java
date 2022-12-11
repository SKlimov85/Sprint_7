package ru.qa_scooter_praktikum_services.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourierLoginTest {
    private final CourierGenerator courierGenerator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertion check = new CourierAssertion();
    private final CourierDelete courierDelete = new CourierDelete();
    private int courierId;
    private int createCourier;
    Courier courierCreate = courierGenerator.randomDataCourier();

    @Before
    public void createCouriers() {
        ValidatableResponse create = client.create(courierCreate);
        createCourier = check.assertCreationSusses(create);
    }

    @After
    public void deleteCourier() {
        Creadentials courierLogin = Creadentials.from(courierCreate);
        ValidatableResponse login = client.login(courierLogin);
        courierId = check.loginInSusses(login);

        if(courierId > 0) {
            ValidatableResponse response = client.delete(courierId, courierDelete);
            check.deleteSusses(response);
        }
    }

    @Test
    @DisplayName("Проверка успешного входа под существующими логином и паролем курьера")
    public void checkLoginSusses() {
        Creadentials courierLogin = Creadentials.from(courierCreate);
        ValidatableResponse login = client.login(courierLogin);
        courierId = check.loginInSusses(login);
    }

    @Test
    @DisplayName("Проверка невозможности авторизироваться курьеру с неверным логином")
    public void checkLoginDoesNotMatch() {
        Creadentials courierLogin = Creadentials.from(courierCreate);
        courierLogin.setLogin(courierCreate.getLogin() + "1");
        ValidatableResponse login = client.login(courierLogin);
        check.loginInFailed(login);
    }
    @Test
    @DisplayName("Проверка невозможности авторизироваться курьеру с неверным паролем")
    public void checkPasswordDoesNotMatch() {
        Creadentials courierLogin = Creadentials.from(courierCreate);
        courierLogin.setPassword(courierCreate.getPassword() + "1");
        ValidatableResponse login = client.login(courierLogin);
        check.loginInFailed(login);
    }

    @Test
    @DisplayName("Проверка невозможности авторизироваться курьеру с без пароля")
    public void checkLoginCourierNoLoginValue() {
        Creadentials courierLogin = Creadentials.from(courierCreate);
        courierLogin.setLogin(null);
        ValidatableResponse login = client.login(courierLogin);
        check.loginInNotValueLoginOrPassword(login);
    }

    @Test
    @DisplayName("Проверка невозможности авторизироваться курьеру с без логина")
    public void checkLoginCourierNoPasswordValue() {
        Creadentials courierLogin = Creadentials.from(courierCreate);
        courierLogin.setPassword(null);
        ValidatableResponse login = client.login(courierLogin);
        check.loginInNotValueLoginOrPassword(login);
    }

}
