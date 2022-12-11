package ru.qa_scooter_praktikum_services.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class OrderListTest {
    private final OrderClient orderClient = new OrderClient();
    private final OrderAssertion orderAssertion = new OrderAssertion();

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void checkReturnOrderListSuccess() {
        ValidatableResponse orderList = orderClient.orderList();
        orderAssertion.checkOrderList(orderList);
    }
}
