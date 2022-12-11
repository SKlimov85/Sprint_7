package ru.qa_scooter_praktikum_services.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private final OrderClient orderClient = new OrderClient();
    private final OrderAssertion orderAssertion = new OrderAssertion();

    @Parameterized.Parameter()
    public String firstName;
    @Parameterized.Parameter(1)
    public String lastName;
    @Parameterized.Parameter(2)
    public String address;
    @Parameterized.Parameter(3)
    public String metroStation;
    @Parameterized.Parameter(4)
    public String phone;
    @Parameterized.Parameter(5)
    public int rentTime;
    @Parameterized.Parameter(6)
    public String deliveryDate;
    @Parameterized.Parameter(7)
    public String comment;
    @Parameterized.Parameter(8)
    public List<String> color;


    @Parameterized.Parameters(name = "firstName: {0}, lastName: {1}, address: {2}, metroStation: {3}, phone: {4}, rentTime: {5}, deliveryDate: {6}, comment: {7}, color: {8}")
    public static Object[][] data() {
        return new Object[][] {
                {"Гавр", "Евлампочич", "Суздаль", "Бабушкинская", "+7 333 322 11 11", 5, "2022-01-01", "БЫСТРЕЕ!", null},
                {"Гавр", "Евлампочич", "Суздаль", "Бабушкинская", "+7 333 322 11 11", 5, "2022-01-01", "БЫСТРЕЕ", List.of("BLACK")},
                {"Гавр", "Евлампочич", "Суздаль", "Бабушкинская", "+7 333 322 11 11", 5, "2022-01-01", "БЫСТРЕЕ", List.of("GREY")},
                {"Гавр", "Евлампочич", "Суздаль", "Бабушкинская", "+7 333 322 11 11", 5, "2022-01-01", "БЫСТРЕЕ", List.of("BLACK", "GREY")},
        };
    }


    @Test
    @DisplayName("Проверка успешного создания заказа с разными значениями цветов")
    public void checkOrderCreate() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse orderCreate = orderClient.orderCreate(order);
        orderAssertion.checkOrderCreate(orderCreate);
    }
}
