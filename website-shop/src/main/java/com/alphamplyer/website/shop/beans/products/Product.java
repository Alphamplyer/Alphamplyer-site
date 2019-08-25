package com.alphamplyer.website.shop.beans.products;

import java.sql.Timestamp;

/**
 * Model class who represent Shop product
 */
public class Product {

    private Integer id;
    private Integer typeId;
    private String code;

    private String name;
    private String description;

    private Double price;

    private Integer unitByUser;
    private Boolean renewal;
    private Boolean gameContent;
    private ProductStatus status;

    private Integer image_id;

    private Timestamp availableFrom;
    private Timestamp availableTo;

    public Product() { }

    //region // === Getters ================================================== //

    /**
     * Get the product ID
     * @return product ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get the product type ID
     * @return product type ID
     */
    public Integer getTypeId() {
        return typeId;
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
     * Get the maximum number of products that a user can buy.
     * @return the maximum number of products that a user can buy
     */
    public Integer getUnitByUser() {
        return unitByUser;
    }

    /**
     * Get if the product is renewable
     * @return true if the product is renewable, else false
     */
    public Boolean getRenewal() {
        return renewal;
    }

    /**
     * Get if the product is a game content
     * @return true if the product is a game content, else false
     */
    public Boolean getGameContent() {
        return gameContent;
    }

    /**
     * Get the actual product status
     * @return the actual product status
     */
    public ProductStatus getStatus() {
        return status;
    }

    /**
     * Get the product's image ID
     * @return the product's image ID
     */
    public Integer getImage_id() {
        return image_id;
    }

    /**
     * Get the date and time when the product is available for sale
     * @return the date and time when the product is available for sale.
     */
    public Timestamp getAvailableFrom() {
        return availableFrom;
    }

    /**
     * Get the date and time when the product is no longer available for sale
     * @return the date and time when the product is no longer available for sale
     */
    public Timestamp getAvailableTo() {
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
     * Set the product type ID
     * @param typeId the product type ID
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
     * Set the maximum number of products that a user can buy.
     * @param unitByUser the maximum number of products that a user can buy
     */
    public void setUnitByUser(Integer unitByUser) {
        this.unitByUser = unitByUser;
    }

    /**
     * Set if the product is renewable
     * @param renewal true if the product is renewable, else false
     */
    public void setRenewal(Boolean renewal) {
        this.renewal = renewal;
    }

    /**
     * Set if the product is a game content
     * @param gameContent true if the product is a game content, else false
     */
    public void setGameContent(Boolean gameContent) {
        this.gameContent = gameContent;
    }

    /**
     * Set the actual product status
     * @param status the actual product status
     */
    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    /**
     * Set the product's image ID
     * @param image_id the product's image ID
     */
    public void setImage_id(Integer image_id) {
        this.image_id = image_id;
    }

    /**
     * Set the date and time when the product is available for sale
     * @param availableFrom the date and time when the product is available for sale
     */
    public void setAvailableFrom(Timestamp availableFrom) {
        this.availableFrom = availableFrom;
    }

    /**
     * Set the date and time when the product is no longer available for sale
     * @param availableTo the date and time when the product is no longer available for sale
     */
    public void setAvailableTo(Timestamp availableTo) {
        this.availableTo = availableTo;
    }

    //endregion

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", typeId=" + typeId +
            ", code='" + code + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", unitByUser=" + unitByUser +
            ", renewal=" + renewal +
            ", gameContent=" + gameContent +
            ", status=" + status.toString() +
            ", image_id=" + image_id +
            ", availableFrom=" + availableFrom +
            ", availableTo=" + availableTo +
            '}';
    }
}
