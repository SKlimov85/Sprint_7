package ru.qa_scooter_praktikum_services.courier;

public class CourierDelete {
    private String id;

    public CourierDelete(String id) {
        this.id = id;
    }

    public CourierDelete() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
