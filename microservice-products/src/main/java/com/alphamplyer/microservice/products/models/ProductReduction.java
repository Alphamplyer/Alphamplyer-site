package com.alphamplyer.microservice.products.models;

/**
 * Represent Product reduction data
 */
public class ProductReduction {

    private Integer id;
    private Integer productId;
    private Integer reductionId;

    public ProductReduction() { }


    /**
     * Get product reduction ID
     * @return product reduction ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get Product ID
     * @return product ID
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Get reduction ID
     * @return reduction ID
     */
    public Integer getReductionId() {
        return reductionId;
    }

    /**
     * Define product reduction ID
     * @param id product reduction ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Define product ID
     * @param productId product ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
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
        return "ProductReduction{" +
            "id=" + id +
            ", productId=" + productId +
            ", reductionId=" + reductionId +
            '}';
    }
}
