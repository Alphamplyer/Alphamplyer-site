package com.alphamplyer.microservice.products.models;

public class ProductReduction {

    private Integer id;
    private Integer productId;
    private Integer reductionId;

    public ProductReduction() { }


    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getReductionId() {
        return reductionId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setReductionId(Integer reductionId) {
        this.reductionId = reductionId;
    }

    @Override
    public String toString() {
        return "ProductReduction{" +
            "id=" + id +
            ", productId=" + productId +
            ", reductionId=" + reductionId +
            '}';
    }
}
