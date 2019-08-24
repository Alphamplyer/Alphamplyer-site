package com.alphamplyer.microservice.products.models;

public class ProductTypeReduction {

    private Integer id;
    private Integer productTypeId;
    private Integer reductionId;

    public ProductTypeReduction() { }


    public Integer getId() {
        return id;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public Integer getReductionId() {
        return reductionId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public void setReductionId(Integer reductionId) {
        this.reductionId = reductionId;
    }

    @Override
    public String toString() {
        return "ProductTypeReduction{" +
            "id=" + id +
            ", productTypeId=" + productTypeId +
            ", reductionId=" + reductionId +
            '}';
    }
}