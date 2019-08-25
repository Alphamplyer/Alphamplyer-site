package com.alphamplyer.website.shop.proxies;

import com.alphamplyer.website.shop.beans.products.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-products", url = "localhost:10401")
public interface MicroserviceProductsProxy {

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    @GetMapping(value = "/products/id/{id}")
    Product getByID(@PathVariable(name = "id") Integer id,
                                    @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                    @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    @GetMapping(value = "/products/code/{code}")
    Product getByCode(@PathVariable(name = "code") String code,
                                      @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                      @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    @GetMapping(value = "/products")
    List<Product> getAll(@RequestParam(name = "offset", required = false) Integer offset,
                                         @RequestParam(name = "limit", required = false) Integer limit,
                                         @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                         @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    @GetMapping(value = "/products/type/{id}")
    List<Product> getAllByType(@PathVariable(value = "id") Integer id,
                                               @RequestParam(name = "offset", required = false) Integer offset,
                                               @RequestParam(name = "limit", required = false) Integer limit,
                                               @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                               @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable);

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    @PostMapping(value = "/products/add")
    Product add (@RequestBody Product product);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    @PutMapping(value = "/products/save")
    void save(@RequestBody Product product);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    @DeleteMapping(value = "/products/delete/{id}")
    void delete(@PathVariable(name = "id") Integer id);

    //endregion
}
