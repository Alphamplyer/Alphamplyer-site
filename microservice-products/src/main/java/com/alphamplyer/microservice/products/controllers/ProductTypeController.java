package com.alphamplyer.microservice.products.controllers;

import com.alphamplyer.microservice.products.exceptions.*;
import com.alphamplyer.microservice.products.models.ProductType;
import com.alphamplyer.microservice.products.repositories.dao.interf.IProductTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductTypeController {

    private static final Logger logger = LoggerFactory.getLogger(ProductTypeController.class);

    private IProductTypeRepository productTypeRepository;

    @Autowired
    public void setProductTypeRepository(IProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    /**
     * Get product type with its ID
     * @param id product type ID
     * @return a product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/id/{id}")
    ResponseEntity<ProductType> getByID (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Product type ID is null or lower than 0");

        ProductType productType;

        try {
            productType = productTypeRepository.getByID(id);
        } catch (DataAccessException e) {
            logger.error("Product type not found in database", e);
            throw new NotFoundException(String.format("Product type not found (ID = %d)", id));
        }

        return new ResponseEntity<>(productType, HttpStatus.OK);
    }

    /**
     * Get the product type parent of the product type with the given ID
     * @param id product type ID
     * @return a product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/parent/{id}")
    ResponseEntity<ProductType> getParent (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Product type ID is null or lower than 0");

        ProductType productType;

        try {
            productType = productTypeRepository.getParent(id);
        } catch (DataAccessException e) {
            logger.error("Product type parent not found in database", e);
            throw new NotFoundException(String.format("Product type parent not found (ID = %d)", id));
        }

        return new ResponseEntity<>(productType, HttpStatus.OK);
    }

    /**
     * Get all child of a product type with its id
     * @param id product type ID
     * @return a list of child product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/childs/{id}")
    ResponseEntity<List<ProductType>> getChilds (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Product type ID is null or lower than 0");

        List<ProductType> productTypes;

        try {
            productTypes = productTypeRepository.getChilds(id);
        } catch (DataAccessException e) {
            logger.error("Product type childs not found in database", e);
            throw new NotFoundException(String.format("Product type childs not found (ID = %d)", id));
        }

        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }

    /**
     * Get the mains product type (product type with no parent)
     * @return a list of product type or a NotFoundException/BadRequestException error
     */
    @GetMapping(value = "/productType/mains")
    ResponseEntity<List<ProductType>> getMainProductType () {
        List<ProductType> productTypes;

        try {
            productTypes = productTypeRepository.getMainProductType();
        } catch (DataAccessException e) {
            logger.error("Main product type not found in database", e);
            throw new NotFoundException("Main product type not found in database");
        }

        return new ResponseEntity<>(productTypes, HttpStatus.OK);
    }

    /**
     * Add a product type
     * @param productType product type to add
     * @return added product type or a UnableToInsertException
     */
    @PostMapping(value = "/productType/add")
    ResponseEntity<ProductType> add (@RequestBody ProductType productType) {
        ProductType rProductType = null;

        try {
            rProductType = productTypeRepository.add(productType);
        } catch (DataAccessException e) {
            logger.error("Failed to insert product type", e);
        }

        if (rProductType == null) {
            throw new UnableToInsertException("Failed to insert product type !");
        }

        return new ResponseEntity<>(rProductType, HttpStatus.CREATED);
    }

    /**
     * Update a product type
     * @param productType product type to update
     * @return HttpResponse OK or a UnableToUpdateException
     */
    @PutMapping(value = "/productType/save")
    ResponseEntity<Void> save (@RequestBody ProductType productType) {
        try {
            productTypeRepository.save(productType);
        } catch (DataAccessException e) {
            logger.error("Failed to update product type in database", e);
            throw new UnableToUpdateException("Failed to update product type in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete a product type
     * @param id product type ID
     * @return HttpResponse OK or UnableToDeleteException
     */
    @DeleteMapping(value = "/productType/delete/{id}")
    ResponseEntity<Void> delete (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Product type ID is null or lower than 0");

        try {
            productTypeRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete product type in database !", e);
            throw new UnableToDeleteException("Failed to delete product type in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
