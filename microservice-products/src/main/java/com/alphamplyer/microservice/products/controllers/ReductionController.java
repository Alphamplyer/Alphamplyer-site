package com.alphamplyer.microservice.products.controllers;

import com.alphamplyer.microservice.products.exceptions.*;
import com.alphamplyer.microservice.products.models.Reduction;
import com.alphamplyer.microservice.products.models.beans.ProductReductionList;
import com.alphamplyer.microservice.products.models.beans.ProductTypeReductionList;
import com.alphamplyer.microservice.products.repositories.dao.interf.IReductionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReductionController {

    private static final Logger logger = LoggerFactory.getLogger(ReductionController.class);

    private IReductionRepository reductionRepository;

    @Autowired
    public void setReductionRepository(IReductionRepository reductionRepository) {
        this.reductionRepository = reductionRepository;
    }

    @GetMapping(value = "/reductions/id/{id}")
    ResponseEntity<Reduction> getByID (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Reduction ID is null or lower than 0");

        Reduction reduction;

        try {
            reduction = reductionRepository.getByID(id);
        } catch (DataAccessException e) {
            logger.error("Reduction not found in database", e);
            throw new NotFoundException(String.format("Reduction not found (ID = %d)", id));
        }

        return new ResponseEntity<>(reduction, HttpStatus.OK);
    }

    @GetMapping(value = "/reductions/product/{id}")
    ResponseEntity<List<Reduction>> getProductReduction (@PathVariable(name = "id") Integer id) {

        List<Reduction> reductions;

        try {
            reductions = reductionRepository.getProductReductions(id);
        } catch (DataAccessException e) {
            logger.error("Reductions not found in database", e);
            throw new NotFoundException("Reductions not found in database");
        }

        return new ResponseEntity<>(reductions, HttpStatus.OK);
    }

    @GetMapping(value = "/reductions/productType/{id}")
    ResponseEntity<List<Reduction>> getProductByTypeReduction(@PathVariable(name = "id") Integer id) {
        List<Reduction> reductions;

        try {
            reductions = reductionRepository.getProductTypeReductions(id);
        } catch (DataAccessException e) {
            logger.error("Reductions not found in database", e);
            throw new NotFoundException("Reductions not found in database");
        }

        return new ResponseEntity<>(reductions, HttpStatus.OK);
    }

    @PostMapping(value = "/reductions/add")
    ResponseEntity<Reduction> add (@RequestBody Reduction reduction) {
        Reduction rReduction = null;

        try {
            rReduction = reductionRepository.add(reduction);
        } catch (DataAccessException e) {
            logger.error("Failed to insert reduction", e);
        }

        if (rReduction == null) {
            throw new UnableToInsertException("Failed to insert reduction !");
        }

        return new ResponseEntity<>(rReduction, HttpStatus.CREATED);
    }
    @PostMapping(value = "/reductions/add/product")
    ResponseEntity<Void> deleteOldAndSaveNewPReduction(@RequestBody ProductReductionList productReductionList) {
        try {
            reductionRepository.deleteOldAndSaveNewPReduction(productReductionList.productIDs, productReductionList.reductionID);
        } catch (DataAccessException e) {
            logger.error("Failed to update product of reduction in database", e);
            throw new UnableToUpdateException("Failed to update product of reduction in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping(value = "/reductions/add/productType")
    ResponseEntity<Void> deleteOldAndSaveNewPTypeReduction(@RequestBody ProductTypeReductionList productTypeReductionList) {
        try {
            reductionRepository.deleteOldAndSaveNewPReduction(productTypeReductionList.productTypeIDs, productTypeReductionList.reductionID);
        } catch (DataAccessException e) {
            logger.error("Failed to update product types of reduction in database", e);
            throw new UnableToUpdateException("Failed to update product types of reduction in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/reductions/save")
    ResponseEntity<Void> save (@RequestBody Reduction reduction) {
        try {
            reductionRepository.save(reduction);
        } catch (DataAccessException e) {
            logger.error("Failed to update reduction in database", e);
            throw new UnableToUpdateException("Failed to update reduction in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "/reduction/delete/{id}")
    ResponseEntity<Void> delete (@PathVariable(name = "id") Integer id) {
        if (id == null || id < 0)
            throw new BadRequestException("Reduction ID is null or lower than 0");

        try {
            reductionRepository.delete(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete reduction in database", e);
            throw new UnableToDeleteException("Failed to delete reduction in database !");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
