package com.alphamplyer.microservice.products.models.beans;

import java.util.List;

/**
 * Object for controller transaction of list of product type reduction
 */
public class ProductTypeReductionList {

    public List<Integer> productTypeIDs;
    public Integer reductionID;

    public ProductTypeReductionList(List<Integer> productTypeIDs, Integer reductionID) {
        this.productTypeIDs = productTypeIDs;
        this.reductionID = reductionID;
    }
}
