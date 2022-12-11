package ru.qa_scooter_praktikum_services.courier;

public class Creadentials {
    private String login;
    private String password;

    public Creadentials(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public Creadentials() {
    }
    public static Creadentials from(Courier courier) {
        return  new Creadentials(courier.getLogin(), courier.getPassword());
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
