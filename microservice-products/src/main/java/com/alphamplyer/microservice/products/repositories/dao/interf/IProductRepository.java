package com.alphamplyer.microservice.products.repositories.dao.interf;

import com.alphamplyer.microservice.products.models.Product;

import java.util.List;

public interface IProductRepository {

    /**
     * Get product by ID
     * @param id product ID
     * @param includeNoAvailableToPublic true if include, false else
     * @param includeNoAvailable true if include, false else
     * @return product or null
     */
    Product getByID (Integer id, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable);

    /**
     * Get product by code
     * @param code product code
     * @param includeNoAvailableToPublic true if include, false else
     * @param includeNoAvailable true if include, false else
     * @return product or null
     */
    Product getByCode (String code, Boolean includeNoAvailableToPublic, Boolean includeNoAvailable);

    /**
     * Get list of product
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @param includeNoAvailableToPublic true if include, false else
     * @param includeNoAvailable true if include, false else
     * @return list of product or null
     */
    List<Product> getAll (Integer offset, Integer limit,
                          Boolean includeNoAvailableToPublic,
                          Boolean includeNoAvailable);

    /**
     * Get list of product from its product type ID
     * @param type_id get product type ID
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @param includeNoAvailableToPublic true if include, false else
     * @param includeNoAvailable true if include, false else
     * @return list of product or null
     */
    List<Product> getAllByType (Integer type_id, Integer offset, Integer limit,
                                Boolean includeNoAvailableToPublic,
                                Boolean includeNoAvailable);

    /**
     * Add product
     * @param product product to add
     * @return added product or null
     */
    Product add (Product product);

    /**
     * Save product
     * @param product product to update
     */
    void save (Product product);

    /**
     * Delete a product
     * @param id product ID to delete
     */
    void delete (Integer id);
}
