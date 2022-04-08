package com.example.bancolombiaapp.requests;

public class PinRequest {
    private int pin;
    private String username;

    public int getPin(){
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }
    public  String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
