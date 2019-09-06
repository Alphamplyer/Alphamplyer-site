package com.alphamplyer.microservice.products.models.beans;

import java.util.List;

/**
 * Object for controller transaction of list of product type reduction
 */
public class ProductTypeReductionList {

    private List<Integer> productTypeIDs;
    private Integer reductionID;

    public ProductTypeReductionList(List<Integer> productTypeIDs, Integer reductionID) {
        this.productTypeIDs = productTypeIDs;
        this.reductionID = reductionID;
    }

    public List<Integer> getProductTypeIDs() {
        return productTypeIDs;
    }

    public Integer getReductionID() {
        return reductionID;
    }

    public void setProductTypeIDs(List<Integer> productTypeIDs) {
        this.productTypeIDs = productTypeIDs;
    }

    public void setReductionID(Integer reductionID) {
        this.reductionID = reductionID;
    }
}
