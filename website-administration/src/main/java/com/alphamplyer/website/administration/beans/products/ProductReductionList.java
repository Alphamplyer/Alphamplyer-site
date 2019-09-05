package com.alphamplyer.website.administration.beans.products;

import java.util.List;

/**
 * Object for controller transaction of list of product reduction
 */
public class ProductReductionList {

    public List<Integer> productIDs;
    public Integer reductionID;

    public ProductReductionList(List<Integer> productIDs, Integer reductionID) {
        this.productIDs = productIDs;
        this.reductionID = reductionID;
    }
}
