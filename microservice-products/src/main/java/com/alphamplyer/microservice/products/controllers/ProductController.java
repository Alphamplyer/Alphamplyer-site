package com.alphamplyer.microservice.products.controllers;

import com.alphamplyer.microservice.products.exceptions.*;
import com.alphamplyer.microservice.products.models.Product;
import com.alphamplyer.microservice.products.repositories.dao.interf.IProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private IProductRepository productRepository;

    @Autowired
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/products/id/{id}")
    public ResponseEntity<Product> getByID (@PathVariable(name = "id") Integer id,
                                            @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                            @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable) {
        if (id == null || id < 0)
            throw new BadRequestException("Product ID is null or lower than 0");

        Product product;

        try {
            product = productRepository.getByID(id, includeNoAvailableToPublic, includeNoAvailable);
        } catch (DataAccessException e) {
            logger.error("Product not found in database", e);
            throw new NotFoundException(String.format("Product not found (ID = %d)", id));
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/products/code/{code}")
    public ResponseEntity<Product> getByCode (@PathVariable(name = "code") String code,
                                              @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                              @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable) {

        if (code == null || code.isEmpty())
            throw new BadRequestException("Product code is null or empty");

        Product product;

        try {
            product = productRepository.getByCode(code, includeNoAvailableToPublic, includeNoAvailable);
        } catch (DataAccessException e) {
            logger.error("Product not found in database", e);
            throw new NotFoundException(String.format("Product not found (code = %s)", code));
        }

        return new ResponseEntity<>(product, HttpStatus.OK);

    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAll (@RequestParam(name = "offset", required = false) Integer offset,
                                                 @RequestParam(name = "limit", required = false) Integer limit,
                                                 @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                                 @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable) {
        if (offset != null && offset < 0)
            throw new BadRequestException("Offset can't be negative");
        if (limit != null && limit < 0)
            throw new BadRequestException("Limit can't be negative");

        List<Product> products;

        try {
            products = productRepository.getAll(offset, limit, includeNoAvailableToPublic, includeNoAvailable);
        } catch (DataAccessException e) {
            logger.error("Products not found in database", e);
            throw new NotFoundException(String.format("Products not found (Offset = %d, Limit = %d) [null or 0 = No restriction, no offset, no limit]", offset, limit));
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/products/type/{id}")
    public ResponseEntity<List<Product>> getAllByType (@PathVariable(value = "id") Integer id,
                                                       @RequestParam(name = "offset", required = false) Integer offset,
                                                       @RequestParam(name = "limit", required = false) Integer limit,
                                                       @RequestParam(name = "includeNoAvailableToPublic") Boolean includeNoAvailableToPublic,
                                                       @RequestParam(name = "includeNoAvailable") Boolean includeNoAvailable) {
        if (offset != null && offset < 0)
            throw new BadRequestException("Offset can't be negative");
        if (limit != null && limit < 0)
            throw new BadRequestException("Limit can't be negative");

        List<Product> products;

        try {
            products = productRepository.getAllByType(id, offset, limit, includeNoAvailableToPublic, includeNoAvailable);
        } catch (DataAccessException e) {
            logger.error("Products not found in database", e);
            throw new NotFoundException(String.format("Products not found (Offset = %d, Limit = %d) [null or 0 = No restriction, no offset, no limit]", offset, limit));
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/products/add")
    public ResponseEntity<Product> add (@RequestBody Product product) {
        Product rProduct = null;

        try {
            rProduct = productRepository.add(product);
        } catch (DataAccessException e) {
            logger.error("Failed to insert product", e);
        }

        if (rProduct == null) {
            throw new UnableToInsertException("Failed to insert product !");
        }

        return new ResponseEntity<>(rProduct, HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/save")
    public ResponseEntity<Void> save (@RequestBody Product product) {
        try {
            productRepository.save(product);
        } catch (DataAccessException e) {
            logger.error("Failed to update product in database", e);
            throw new UnableToUpdateException("Failed to update product in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Product ID is null or lower than 0");

        try {
            productRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete product in database", e);
            throw new UnableToDeleteException("Failed to delete product in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
