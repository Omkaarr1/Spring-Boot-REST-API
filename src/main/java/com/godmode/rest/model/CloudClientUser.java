package com.godmode.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cloud_client_cred")
public class CloudClientUser {

    @Id
    private String user_id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    public CloudClientUser(String username, String password, String user_id, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public CloudClientUser() {
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }  

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format(
            "{\n  \"username\": \"%s\",\n  \"email\": \"%s\",\n  \"password\": \"%s\",\n  \"user_id\": \"%s\",\n  \"phoneNumber\": \"%s\"\n}",
            username, email, password, user_id, phoneNumber
        );
    }


}
