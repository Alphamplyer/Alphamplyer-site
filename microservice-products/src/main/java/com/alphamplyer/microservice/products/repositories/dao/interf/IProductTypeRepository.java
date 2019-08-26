package com.alphamplyer.microservice.products.repositories.dao.interf;

import com.alphamplyer.microservice.products.models.ProductType;

import java.util.List;

public interface IProductTypeRepository {

    ProductType getByID (Integer id);
    ProductType getParent (Integer id);

    List<ProductType> getChilds(Integer id);
    List<ProductType> getMainProductType ();

    ProductType add (ProductType productType);
    void save (ProductType productType);
    void delete (Integer id);
}
