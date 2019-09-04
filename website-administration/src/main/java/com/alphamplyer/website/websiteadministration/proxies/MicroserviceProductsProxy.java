package com.alphamplyer.website.websiteadministration.proxies;

import com.alphamplyer.website.websiteadministration.beans.products.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-products", url = "localhost:10401")
public interface MicroserviceProductsProxy {

    //region Product Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    /**
     * Get a product from its ID
     * @param id ID of the product
     * @param includeNoAvailableToPublic Should the search include items not available to the public ?
     * @param includeNoAvailable Should the search include unavailable items ?
     * @return a product or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/products/id/{id}")
    Product getProductByID(@PathVariable(name = "id") Integer id,
                           @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                           @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    /**
     * Get a product from its code
     * @param code code of the product
     * @param includeNoAvailableToPublic Should the search include items not available to the public ?
     * @param includeNoAvailable Should the search include unavailable items ?
     * @return a product or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/products/code/{code}")
    Product getProductByCode(@PathVariable(name = "code") String code,
                             @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                             @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    /**
     * Get the list of the product depending of parameters
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @param includeNoAvailableToPublic Should the search include items not available to the public ?
     * @param includeNoAvailable Should the search include unavailable items ?
     * @return a list of the product or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/products")
    List<Product> getProducts(@RequestParam(name = "offset", required = false) Integer offset,
                              @RequestParam(name = "limit", required = false) Integer limit,
                              @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                              @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    /**
     * Get the list of the product depending of parameters and the type ID
     * @param id Type ID
     * @param offset from how many results does the selection start?
     * @param limit how many results are expected at the maximum?
     * @param includeNoAvailableToPublic Should the search include items not available to the public ?
     * @param includeNoAvailable Should the search include unavailable items ?
     * @return a list of the product or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/products/type/{id}")
    List<Product> getProductsByType(@PathVariable(value = "id") Integer id,
                                    @RequestParam(name = "offset", required = false) Integer offset,
                                    @RequestParam(name = "limit", required = false) Integer limit,
                                    @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                    @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    /**
     * Add a product
     * @param product product to add
     * @return added product or UnableToInsertException error
     */
    @PostMapping(value = "/products/add")
    Product addProduct(@RequestBody Product product);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    /**
     * Update a product
     * @param product product to update
     */
    @PutMapping(value = "/products/save")
    void saveProduct(@RequestBody Product product);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    /**
     * Delete an product with an ID
     * @param id product ID
     */
    @DeleteMapping(value = "/products/delete/{id}")
    void deleteProduct(@PathVariable(name = "id") Integer id);

    //endregion

    //endregion

    //region ProductType Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    /**
     * Get product type with its ID
     * @param id product type ID
     * @return a product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/id/{id}")
    ProductType getProductTypeByID(@PathVariable(name = "id") Integer id);

    /**
     * Get the product type parent of the product type with the given ID
     * @param id product type ID
     * @return a product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/parent/{id}")
    ProductType getProductTypeParent(@PathVariable(name = "id") Integer id);

    /**
     * Get all child of a product type with its id
     * @param id product type ID
     * @return a list of child product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/childs/{id}")
    List<ProductType> getProductTypeChilds(@PathVariable(name = "id") Integer id);

    /**
     * Get the mains product type (product type with no parent)
     * @return a list of product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/mains")
    List<ProductType> getMainProductType();

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    /**
     * Add a product type
     * @param productType product type to add
     * @return added product type or a UnableToInsertException
     */
    @PostMapping(value = "/productType/add")
    ProductType addProductType(@RequestBody ProductType productType);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    /**
     * Update a product type
     * @param productType product type to update
     */
    @PutMapping(value = "/productType/save")
    void saveProductType(@RequestBody ProductType productType);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    /**
     * Delete a product type
     * @param id product type ID
     */
    @DeleteMapping(value = "/productType/delete/{id}")
    void deleteProductType(@PathVariable(name = "id") Integer id);

    //endregion

    //endregion

    //region Reduction Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    /**
     * Get a reduction from its ID
     * @param id reduction ID
     * @return a reduction or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/reductions/id/{id}")
    Reduction getReductionByID(@PathVariable(name = "id") Integer id);

    /**
     * Return a list of product reduction from the product ID
     * @param id product ID
     * @return a list of product reduction or a NotFoundException error
     */
    @GetMapping(value = "/reductions/product/{id}")
    List<Reduction> getProductReduction(@PathVariable(name = "id") Integer id);

    /**
     * Return a list of product type reduction from product type ID
     * @param id product type ID
     * @return a list of product type reduction or a NotFoundException error
     */
    @GetMapping(value = "/reductions/productType/{id}")
    List<Reduction> getProductByTypeReduction(@PathVariable(name = "id") Integer id);

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    /**
     * Add a reduction
     * @param reduction reduction to add
     * @return added reduction or an UnableToInsertException error
     */
    @PostMapping(value = "/reductions/add")
    Reduction add(@RequestBody Reduction reduction);

    /**
     * Replace the list of product reductions of a product
     * @param productReductionList product reduction list data
     */
    @PostMapping(value = "/reductions/add/product")
    void deleteOldAndSaveNewPReduction(@RequestBody ProductReductionList productReductionList);

    /**
     * Replace the list of product type reductions of a product
     * @param productTypeReductionList product type reduction list data
     */
    @PostMapping(value = "/reductions/add/productType")
    void deleteOldAndSaveNewPTypeReduction(@RequestBody ProductTypeReductionList productTypeReductionList);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    /**
     * Update a reduction
     * @param reduction reduction data
     */
    @PutMapping(value = "/reductions/save")
    void saveReduction(@RequestBody Reduction reduction);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    /**
     * Delete a reduction
     * @param id reduction ID
     */
    @DeleteMapping(value = "/reduction/delete/{id}")
    void deleteReduction(@PathVariable(name = "id") Integer id);
    
    //endregion

    //endregion
}
