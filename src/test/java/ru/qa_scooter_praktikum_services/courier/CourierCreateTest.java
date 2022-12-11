package ru.qa_scooter_praktikum_services.courier;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import ru.qa_scooter_praktikum_services.courier.Courier;
import ru.qa_scooter_praktikum_services.courier.CourierAssertion;
import ru.qa_scooter_praktikum_services.courier.CourierClient;
import ru.qa_scooter_praktikum_services.courier.CourierGenerator;

public class CourierCreateTest {

    private final CourierGenerator courierGenerator = new CourierGenerator();
    private final CourierClient client = new CourierClient();
    private final CourierAssertion check = new CourierAssertion();

    private int courierId;
    private int createCourier;
    Courier courierCreate = courierGenerator.randomDataCourier();


    @After
    public void deleteCourier() {
        if (createCourier == 200) {
            Creadentials courierLogin = Creadentials.from(courierCreate);
            ValidatableResponse login = client.login(courierLogin);
            courierId = check.loginInSusses(login);
        }

        if(courierId > 0) {
            CourierDelete courierDelete = new CourierDelete();
            ValidatableResponse response = client.delete(courierId, courierDelete);
            check.deleteSusses(response);
        }

    }


    @Test
    @DisplayName("Проверка успешного создания курьера")
    public void checkCreateCourier() {
        ValidatableResponse create = client.create(courierCreate);
        createCourier = check.assertCreationSusses(create);

    }

    @Test
    @DisplayName("Проверка невозможности создания дубля курьера")
    public void checkDoubleCourier(){
        ValidatableResponse create = client.create(courierCreate);
        createCourier = check.assertCreationSusses(create);

        ValidatableResponse create2 = client.create(courierCreate);
        check.assertCreationDoubleCourierFailed(create2);

    }

    @Test
    @DisplayName("Проверка сообщения об ошибке при создании курьера с не уникальным логином")
    public void checkCreateCourierDoubleLogin(){
        ValidatableResponse create = client.create(courierCreate);
        createCourier = check.assertCreationSusses(create);

        ValidatableResponse create2 = client.create(courierCreate);
        check.assertCreationDoubleCourierFailedMessage(create2);

    }

    @Test
    @DisplayName("Проверка невозможности создания курьера без логина")
    public void checkCreateCourierNoLogin() {
        courierCreate.setLogin(null);
        ValidatableResponse create = client.create(courierCreate);
        createCourier = check.assertCreationNoLoginOrPassword(create);
    }

    @Test
    @DisplayName("Проверка невозможности создания курьера без пароля")
    public void checkCreateCourierNoPassword() {
        courierCreate.setPassword(null);
        ValidatableResponse create = client.create(courierCreate);
        createCourier = check.assertCreationNoLoginOrPassword(create);
    }

}
