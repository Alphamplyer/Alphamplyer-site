package com.alphamplyer.microservice.products.models.beans;

import java.util.List;

public class ProductTypeReductionList {

    public List<Integer> productTypeIDs;
    public Integer reductionID;

    public ProductTypeReductionList(List<Integer> productTypeIDs, Integer reductionID) {
        this.productTypeIDs = productTypeIDs;
        this.reductionID = reductionID;
    }
}
