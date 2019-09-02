package com.alphamplyer.website.shop.models;

import com.alphamplyer.website.shop.beans.products.Product;

public class ProductWithNumber {

    private Product product;
    private Integer number;

    public ProductWithNumber(Product product, Integer number) {
        this.product = product;
        this.number = number;
    }

    /**
     * Get product
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Get number of product
     * @return number of product
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Define product
     * @param product product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Define number of product
     * @param number number of product
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Add a product
     */
    public void add () {
        this.number++;
    }

    /**
     * Remove one product
     */
    public void remove() {
        this.number--;
    }

    @Override
    public String toString() {
        return "ProductWithNumber{" +
            "product=" + product.toString() +
            ", number=" + number +
            '}';
    }
}
