package com.alphamplyer.microservice.products.models;

/**
 * Represent product type reduction data
 */
public class ProductTypeReduction {

    private Integer id;
    private Integer productTypeId;
    private Integer reductionId;

    public ProductTypeReduction() { }

    /**
     * Get product type reduction ID
     * @return product type reduction ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get product type ID
     * @return product type ID
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }


    /**
     * get reduction ID
     * @return reduction ID
     */
    public Integer getReductionId() {
        return reductionId;
    }

    /**
     * Define product type reduction ID
     * @param id product type reduction ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Define product type ID
     * @param productTypeId product type ID
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * Define reduction ID
     * @param reductionId reduction ID
     */
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