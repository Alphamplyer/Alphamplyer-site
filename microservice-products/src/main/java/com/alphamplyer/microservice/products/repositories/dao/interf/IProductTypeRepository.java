package com.alphamplyer.microservice.products.repositories.dao.interf;

import com.alphamplyer.microservice.products.models.ProductType;

import java.util.List;

public interface IProductTypeRepository {

    /**
     * Get product type from its ID
     * @param id product type ID
     * @return product type or null
     */
    ProductType getByID (Integer id);

    /**
     * Get product type parent
     * @param id product type ID
     * @return product type or null
     */
    ProductType getParent (Integer id);


    /**
     * Get list of child product type
     * @param id parent product type ID
     * @return a list of child product type or null
     */
    List<ProductType> getChilds(Integer id);

    /**
     * Get list of main product type (without parent ID)
     * @return list of main product type
     */
    List<ProductType> getMainProductType ();

    /**
     * Add a product type
     * @param productType product type to add
     * @return added product type or null
     */
    ProductType add (ProductType productType);

    /**
     * Save product type
     * @param productType product type to update
     */
    void save (ProductType productType);

    /**
     * Delete product type
     * @param id product type ID to delete
     */
    void delete (Integer id);
}
