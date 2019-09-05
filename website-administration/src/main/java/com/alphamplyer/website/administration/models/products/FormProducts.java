package com.alphamplyer.website.administration.models.products;

import org.springframework.format.annotation.NumberFormat;

public class FormProducts {

    private Integer id;

    private String code;

    private String name;

    private String description;

    @NumberFormat
    private Double price;

    private String availableFrom;

    private String availableTo;

    public FormProducts() { }

    //region // === Getters ================================================== //

    /**
     * Get the product ID
     * @return product ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the product Code
     * @return product code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get the name of the product
     * @return name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the product
     * @return description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the price of the product
     * @return price of the product
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Get the date and time when the product is available for sale
     * @return the date and time when the product is available for sale.
     */
    public String getAvailableFrom() {
        return availableFrom;
    }

    /**
     * Get the date and time when the product is no longer available for sale
     * @return the date and time when the product is no longer available for sale
     */
    public String getAvailableTo() {
        return availableTo;
    }

    //endregion

    //region // === Setters ================================================== //

    /**
     * Set the product ID
     * @param id the product ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set the product Code
     * @param code the product Code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Set the name of the product
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the description of the product
     * @param description description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the price of the product
     * @param price price of the product
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Set the date and time when the product is available for sale
     * @param availableFrom the date and time when the product is available for sale
     */
    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    /**
     * Set the date and time when the product is no longer available for sale
     * @param availableTo the date and time when the product is no longer available for sale
     */
    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }

    //endregion
}
