package com.alphamplyer.website.shop.controllers;

import com.alphamplyer.website.shop.beans.products.Product;
import com.alphamplyer.website.shop.proxies.MicroserviceProductsProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@Controller
public class HomeController {

    private MicroserviceProductsProxy microserviceProductsProxy;

    @Autowired
    public void setMicroserviceProductsProxy(MicroserviceProductsProxy microserviceProductsProxy) {
        this.microserviceProductsProxy = microserviceProductsProxy;
    }

    @RequestMapping(value = {"/", "/index", "/home"})
    public String index (Model model) {

        List<Product> products = null;

        try {
            products = microserviceProductsProxy.getAll(null, 10, false, false);
        } catch (FeignException e) {
            return "error";
        }

        model.addAttribute("products", products);

        return "index";
    }
}
