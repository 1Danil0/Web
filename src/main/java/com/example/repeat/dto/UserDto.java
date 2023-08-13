package com.example.repeat.dto;

public class UserDto {
    private String login;
    private String password;
    private String email;
    private String nameSurname;

    public UserDto() {
    }

    public UserDto(String login, String password, String email, String nameSurname) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.nameSurname = nameSurname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }
}
