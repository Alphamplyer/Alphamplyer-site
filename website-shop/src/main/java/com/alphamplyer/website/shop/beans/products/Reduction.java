package com.alphamplyer.website.shop.beans.products;

import java.sql.Timestamp;

public class Reduction {

    private Integer id;

    private Double amount;
    private String activationCode;

    private Timestamp beginDateTime;
    private Timestamp endDateTime;

    public Reduction () { }

    /**
     * Get reduction ID
     * @return reduction ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get reduction amount
     * @return reduction amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Get reduction activation code
     * @return reduction activation code
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * Get reduction begin date time
     * @return reduction begin date time
     */
    public Timestamp getBeginDateTime() {
        return beginDateTime;
    }

    /**
     * Get reduction end date time
     * @return reduction end date time
     */
    public Timestamp getEndDateTime() {
        return endDateTime;
    }


    /**
     * Define reduction ID
     * @param id reduction ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Define reduction amount
     * @param amount reduction amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Define reduction activation code
     * @param activationCode reduction activation code
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * Define reduction begin date time
     * @param beginDateTime reduction begin date time
     */
    public void setBeginDateTime(Timestamp beginDateTime) {
        this.beginDateTime = beginDateTime;
    }

    /**
     * Define reduction end date time
     * @param endDateTime reduction end date time
     */
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