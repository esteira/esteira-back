package com.example.demo.Model;

public class CallbackResponse {

    private String code;
    private String state;

    public CallbackResponse(String code, String state) {
        this.code = code;
        this.state = state;
    }

    public CallbackResponse() {
    }

    public String getCode() {
        return code;
    }

    public String getState() {
        return state;
    }
}
