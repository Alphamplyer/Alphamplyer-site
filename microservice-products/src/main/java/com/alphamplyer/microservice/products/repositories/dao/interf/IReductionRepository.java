package com.alphamplyer.microservice.products.repositories.dao.interf;

import com.alphamplyer.microservice.products.models.Reduction;

import java.util.List;

public interface IReductionRepository {

    /**
     * Get reduction from its ID
     * @param id reduction ID
     * @return a reduction or null
     */
    Reduction getByID (Integer id);

    /**
     * Get list of product reduction from product ID
     * @param productId product ID
     * @return list of product reduction or null
     */
    List<Reduction> getProductReductions(Integer productId);

    /**
     * Get list of product type reduction from type Id
     * @param type_id product type ID
     * @return list of product type reduction or null
     */
    List<Reduction> getProductTypeReductions(Integer type_id);

    /**
     * Add a reduction
     * @param reduction reduction to add
     * @return added reduction or null
     */
    Reduction add (Reduction reduction);

    /**
     * Replace product reduction of a list of product
     * @param ProductIDs list of product ID
     * @param reductionID reduction ID
     */
    void deleteOldAndSaveNewPReduction(List<Integer> ProductIDs, Integer reductionID);

    /**
     * Replace product reduction type of a list of product
     * @param ProductTypeIDs list of product type IDs
     * @param reductionID reduction ID
     */
    void deleteOldAndSaveNewPTypeReduction(List<Integer> ProductTypeIDs, Integer reductionID);

    /**
     * Update reduction
     * @param reduction reduction to update
     */
    void save (Reduction reduction);

    /**
     * Delete a reduction
     * @param id reduction ID to delete
     */
    void delete (Integer id);

}
