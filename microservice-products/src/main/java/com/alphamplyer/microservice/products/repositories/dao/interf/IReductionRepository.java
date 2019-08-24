package com.alphamplyer.microservice.products.repositories.dao.interf;

import com.alphamplyer.microservice.products.models.Reduction;

import java.util.List;

public interface IReductionRepository {

    Reduction getByID (Integer id);

    List<Reduction> getProductReduction (Integer productId);
    List<Reduction> getProductByTypeReduction(Integer type_id);

    Reduction add (Reduction reduction);
    void deleteOldAndSaveNewPReduction(List<Integer> ProductIDs, Integer reductionID);
    void deleteOldAndSaveNewPTypeReduction(List<Integer> ProductTypeIDs, Integer reductionID);


    void save (Reduction reduction);
    void delete (Integer id);

}
