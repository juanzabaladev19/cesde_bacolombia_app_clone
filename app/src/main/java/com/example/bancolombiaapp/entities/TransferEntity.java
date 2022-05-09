package com.example.bancolombiaapp.entities;

import java.io.Serializable;
import java.util.Date;

public class TransferEntity implements Serializable {
    private String type;
    private double amount;
    private Date date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
