package com.alphamplyer.microservice.products.models.beans;

import java.util.List;

/**
 * Object for controller transaction of list of product reduction
 */
public class ProductReductionList {

    private List<Integer> productIDs;
    private Integer reductionID;

    public ProductReductionList(List<Integer> productIDs, Integer reductionID) {
        this.productIDs = productIDs;
        this.reductionID = reductionID;
    }

    public List<Integer> getProductIDs() {
        return productIDs;
    }

    public Integer getReductionID() {
        return reductionID;
    }

    public void setProductIDs(List<Integer> productIDs) {
        this.productIDs = productIDs;
    }

    public void setReductionID(Integer reductionID) {
        this.reductionID = reductionID;
    }
}
