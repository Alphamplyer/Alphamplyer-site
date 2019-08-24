package com.alphamplyer.microservice.products.repositories.dao.interf;

import com.alphamplyer.microservice.products.models.Product;

import java.util.List;

public interface IProductRepository {

    Product getByID (Integer id, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable);
    Product getByCode (String code, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable);

    List<Product> getAll (Integer offset, Integer limit,
                          Boolean includeNoAvailableToPublic,
                          Boolean includeNoAvailable);

    List<Product> getAllByType (Integer type_id, Integer offset, Integer limit,
                                Boolean includeNoAvailableToPublic,
                                Boolean includeNoAvailable);

    Product add (Product product);
    void save (Product product);
    void delete (Integer id);
}
