package com.alphamplyer.website.administration.controllers;

import com.alphamplyer.website.administration.beans.products.Product;
import com.alphamplyer.website.administration.beans.products.ProductStatus;
import com.alphamplyer.website.administration.models.products.FormProducts;
import com.alphamplyer.website.administration.proxies.MicroserviceProductsProxy;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import utils.validation.date.TimestampUtils;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ShopController {

    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

    private MicroserviceProductsProxy microserviceProductsProxy;

    @Autowired
    public void setMicroserviceProductsProxy(MicroserviceProductsProxy microserviceProductsProxy) {
        this.microserviceProductsProxy = microserviceProductsProxy;
    }

    /**
     * Display shop main page
     */
    @RequestMapping(value = "/shop")
    public String displayShopAdminPage(Model model) {
        return "shop_section";
    }

    /**
     * display list of products
     */
    @RequestMapping(value = "/shop/products")
    public String displayShopProductPage(Model model) {

        List<Product> products = null;

        try {
            products = microserviceProductsProxy.getProducts(0, 30, true, true);
        } catch (FeignException e) {
            logger.error("failed to get products", e);
            return "error";
        }

        if (products == null) {
            logger.error("products list is empty or null");
            return "error";
        }

        model.addAttribute("products", products);

        return "shop_section_products";
    }

    /**
     * display edit page of product
     */
    @RequestMapping(value = "/shop/products/edit/{id}", method = RequestMethod.GET)
    public String displayShopProductEditPage(@PathVariable(name = "id") Integer id, Model model) {

        Product product = null;

        try {
            product = microserviceProductsProxy.getProductByID(id, true, true);
        } catch (FeignException e) {
            logger.error("failed to get product", e);
            return "error";
        }

        if (product == null) {
            logger.error("product is null");
            return "error";
        }

        FormProducts formProducts = new FormProducts();

        formProducts.setId(product.getId());
        formProducts.setCode(product.getCode());
        formProducts.setName(product.getName());
        formProducts.setPrice(product.getPrice());
        formProducts.setDescription(product.getDescription());
        formProducts.setAvailableFrom(product.getAvailableFrom().toString());

        if (product.getAvailableTo() != null)
            formProducts.setAvailableTo(product.getAvailableTo().toString());

        model.addAttribute("formProducts", formProducts);

        return "shop_section_products_edit";
    }

    /**
     * display add page of product
     */
    @RequestMapping(value = "/shop/products/new", method = RequestMethod.GET)
    public String displayShopProductAddPage(Model model) {

        model.addAttribute("formProducts", new FormProducts());

        return "shop_section_products_add";
    }

    /**
     * edit product
     */
    @RequestMapping(value = "/shop/products/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editProduct(@ModelAttribute("formProducts") @Valid FormProducts formProducts,
                                    BindingResult result,
                                    @PathVariable(name = "id") Integer id) {

        if (result.hasErrors()) {
            return new ModelAndView("shop_section_products_edit", "formProducts", formProducts);
        }

        Product product;

        try {
            product = microserviceProductsProxy.getProductByID(id, true, true);
        } catch (FeignException e) {
            logger.error("failed to get product", e);
            product = null;
        }

        if (product == null) {
            return new ModelAndView("error");
        }

        product.setCode(formProducts.getCode());
        product.setName(formProducts.getName());
        product.setDescription(formProducts.getDescription());
        product.setPrice(formProducts.getPrice());
        product.setAvailableFrom(TimestampUtils.isValid(formProducts.getAvailableFrom()).y);
        product.setAvailableTo(TimestampUtils.isValid(formProducts.getAvailableTo()).y);

        try {
            microserviceProductsProxy.saveProduct(product);
        } catch (FeignException e) {
            logger.error("Failed to update product", e);
            return new ModelAndView("error");
        }

        return new ModelAndView("redirect:/shop/products");
    }

    /**
     * add a product
     */
    @RequestMapping(value = "/shop/products/new", method = RequestMethod.POST)
    public ModelAndView addProduct(@ModelAttribute("formProducts") @Valid FormProducts formProducts,
                                   BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("shop_section_products_new", "formProducts", formProducts);
        }

        Product product = new Product();

        product.setCode(formProducts.getCode());
        product.setName(formProducts.getName());
        product.setDescription(formProducts.getDescription());
        product.setPrice(formProducts.getPrice());
        product.setUnitByUser(-1);
        product.setRenewal(false);
        product.setGameContent(true);
        product.setStatus(ProductStatus.AVAILABLE);
        product.setImage_id(1);
        product.setAvailableFrom(TimestampUtils.isValid(formProducts.getAvailableFrom()).y);
        product.setAvailableTo(TimestampUtils.isValid(formProducts.getAvailableTo() ).y);
        product.setTypeId(1);

        try {
            microserviceProductsProxy.addProduct(product);
        } catch (FeignException e) {
            return new ModelAndView("error");
        }

        return new ModelAndView("redirect:/shop/products");
    }

    /**
     * display reductions page
     */
    @RequestMapping(value = "/shop/reductions", method = RequestMethod.GET)
    public String displayReductionPage(Model model) {

        return "shop_section_reductions";
    }

    /**
     * display product type page
     */
    @RequestMapping(value = "/shop/productTypes")
    public String displayShopProductTypePage(Model model) {

        return "shop_section_product-types";
    }
}
