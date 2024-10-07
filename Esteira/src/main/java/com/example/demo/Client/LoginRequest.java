package com.example.demo.Client;

import feign.form.FormProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


public class LoginRequest {

    public LoginRequest() {
    }


    public LoginRequest(String grantType, String clientId, String clientSecret) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
    @FormProperty("grant_type")
    private String grantType = "application/x-www-form-urlencoded";

    @FormProperty("client_id")
    private String clientId = "c6ded4fe45494a83ad63bb28a654897b";

    @FormProperty("client_secret")
    private String clientSecret = "8f40feea75f24b2aa67513af23faed15";



    public String getContentType() {
        return grantType;
    }

    public void setContentType(String contentType) {
        this.grantType = contentType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }



}
