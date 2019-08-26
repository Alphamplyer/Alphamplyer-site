package com.alphamplyer.website.shop.beans.products;

import java.util.List;

public class ProductTypeReductionList {

    public List<Integer> productTypeIDs;
    public Integer reductionID;

    public ProductTypeReductionList(List<Integer> productTypeIDs, Integer reductionID) {
        this.productTypeIDs = productTypeIDs;
        this.reductionID = reductionID;
    }
}
