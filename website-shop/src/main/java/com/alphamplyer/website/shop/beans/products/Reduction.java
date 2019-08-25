package com.alphamplyer.website.shop.beans.products;

import java.sql.Timestamp;

public class Reduction {

    private Integer id;

    private Double amount;
    private String activationCode;

    private Timestamp beginDateTime;
    private Timestamp endDateTime;

    public Reduction () { }

    public Integer getId() {
        return id;
    }
    public Double getAmount() {
        return amount;
    }
    public String getActivationCode() {
        return activationCode;
    }
    public Timestamp getBeginDateTime() {
        return beginDateTime;
    }
    public Timestamp getEndDateTime() {
        return endDateTime;
    }


    public void setId(Integer id) {
        this.id = id;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
    public void setBeginDateTime(Timestamp beginDateTime) {
        this.beginDateTime = beginDateTime;
    }
    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "Ruduction{" +
            "id=" + id +
            ", amount=" + amount +
            ", activationCode='" + activationCode + '\'' +
            ", beginDateTime=" + beginDateTime +
            ", endDateTime=" + endDateTime +
            '}';
    }
}