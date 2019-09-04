package com.alphamplyer.website.websiteadministration.controllers;

import com.alphamplyer.website.websiteadministration.beans.products.Product;
import com.alphamplyer.website.websiteadministration.proxies.MicroserviceProductsProxy;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
    public String displayShopAdminPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) { return "redirect:/login"; }

        return "shop_section";
    }

    /**
     * display list of products
     */
    @RequestMapping(value = "/shop/products")
    public String displayShopProductPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) { return "redirect:/login"; }

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
    public String displayShopProductEditPage(@PathVariable(name = "id") Integer id, Model model, HttpSession session) {
        if (session.getAttribute("user") == null) { return "redirect:/login"; }

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

        model.addAttribute("product", product);

        return "shop_section_products_edit";
    }

    /**
     * display add page of product
     */
    @RequestMapping(value = "/shop/products/new", method = RequestMethod.GET)
    public String displayShopProductAddPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        model.addAttribute("product", new Product());

        return "shop_section_products_add";
    }

    /**
     * edit product
     */
    @RequestMapping(value = "/shop/products/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editProduct(HttpSession session) {
        if (session.getAttribute("user") == null) { return new ModelAndView("redirect:/login"); }

        return new ModelAndView("redirect:/shop/products");
    }

    @RequestMapping(value = "/shop/products/new", method = RequestMethod.POST)
    public ModelAndView addProduct(HttpSession session) {
        if (session.getAttribute("user") == null) { return new ModelAndView("redirect:/login"); }

        return new ModelAndView("redirect:/shop/products");
    }

    @RequestMapping(value = "/shop/reductions", method = RequestMethod.GET)
    public String displayReductionPage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) { return "redirect:/login"; }

        return "shop_section_reductions";
    }

    @RequestMapping(value = "/shop/productTypes")
    public String displayShopProductTypePage(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) { return "redirect:/login"; }

        return "shop_section_product-types";
    }
}
