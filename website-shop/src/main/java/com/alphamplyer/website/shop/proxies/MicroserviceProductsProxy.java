package com.alphamplyer.website.shop.proxies;

import com.alphamplyer.website.shop.beans.products.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-products", url = "localhost:10401")
public interface MicroserviceProductsProxy {

    //region Product Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    @GetMapping(value = "/products/id/{id}")
    Product getProductByID(@PathVariable(name = "id") Integer id,
                                    @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                    @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    @GetMapping(value = "/products/code/{code}")
    Product getProductByCode(@PathVariable(name = "code") String code,
                                      @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                      @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    @GetMapping(value = "/products")
    List<Product> getProducts(@RequestParam(name = "offset", required = false) Integer offset,
                                         @RequestParam(name = "limit", required = false) Integer limit,
                                         @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                         @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    @GetMapping(value = "/products/type/{id}")
    List<Product> getProductsByType(@PathVariable(value = "id") Integer id,
                                               @RequestParam(name = "offset", required = false) Integer offset,
                                               @RequestParam(name = "limit", required = false) Integer limit,
                                               @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                               @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    @PostMapping(value = "/products/add")
    Product addProduct (@RequestBody Product product);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    @PutMapping(value = "/products/save")
    void saveProduct(@RequestBody Product product);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    @DeleteMapping(value = "/products/delete/{id}")
    void deleteProduct(@PathVariable(name = "id") Integer id);

    //endregion

    //endregion

    //region ProductType Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    @GetMapping(value = "/productType/id/{id}")
    ProductType getProductTypeByID (@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/productType/parent/{id}")
    ProductType getProductTypeParent (@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/productType/childs/{id}")
    List<ProductType> getProductTypeChilds (@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/productType/mains")
    List<ProductType> getMainProductType ();

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    @PostMapping(value = "/productType/add")
    ProductType addProductType (@RequestBody ProductType productType);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    @PutMapping(value = "/productType/save")
    void saveProductType (@RequestBody ProductType productType);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    @DeleteMapping(value = "/productType/delete/{id}")
    void deleteProductType (@PathVariable(name = "id") Integer id);

    //endregion

    //endregion

    //region Reduction Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    @GetMapping(value = "/reductions/id/{id}")
    Reduction getReductionByID (@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/reductions/product/{id}")
    List<Reduction> getProductReduction (@PathVariable(name = "id") Integer id);

    @GetMapping(value = "/reductions/productType/{id}")
    List<Reduction> getProductByTypeReduction(@PathVariable(name = "id") Integer id);
    
    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    @PostMapping(value = "/reductions/add")
    Reduction add (@RequestBody Reduction reduction);

    @PostMapping(value = "/reductions/add/product")
    void deleteOldAndSaveNewPReduction(@RequestBody ProductReductionList productReductionList);

    @PostMapping(value = "/reductions/add/productType")
    void deleteOldAndSaveNewPTypeReduction(@RequestBody ProductTypeReductionList productTypeReductionList);
    
    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    @PutMapping(value = "/reductions/save")
    void saveReduction (@RequestBody Reduction reduction);
    
    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    @DeleteMapping(value = "/reduction/delete/{id}")
    void deleteReduction (@PathVariable(name = "id") Integer id);
    
    //endregion

    //endregion
}
