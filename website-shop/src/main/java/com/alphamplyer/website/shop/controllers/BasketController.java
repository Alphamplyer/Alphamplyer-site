package com.alphamplyer.website.shop.controllers;

import com.alphamplyer.website.shop.beans.orders.Order;
import com.alphamplyer.website.shop.beans.products.Product;
import com.alphamplyer.website.shop.models.ProductWithNumber;
import com.alphamplyer.website.shop.proxies.MicroserviceOrdersProxy;
import com.alphamplyer.website.shop.proxies.MicroserviceProductsProxy;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BasketController {

    private MicroserviceOrdersProxy microserviceOrdersProxy;
    private MicroserviceProductsProxy microserviceProductsProxy;

    @Autowired
    public void setMicroserviceOrdersProxy(MicroserviceOrdersProxy microserviceOrdersProxy) {
        this.microserviceOrdersProxy = microserviceOrdersProxy;
    }

    @Autowired
    public void setMicroserviceProductsProxy(MicroserviceProductsProxy microserviceProductsProxy) {
        this.microserviceProductsProxy = microserviceProductsProxy;
    }

    /**
     * Display basket page or redirect to login page if not logged
     */
    @RequestMapping(value = "/basket")
    public String displayUserBasket (Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/login";

        List<ProductWithNumber> productWithNumbers;

        if (session.getAttribute("basket") == null) {
            model.addAttribute("products", null);
            return "basket";
        }

        productWithNumbers = (List<ProductWithNumber>) session.getAttribute("basket");

        Double[] totals = calculateTotals(productWithNumbers);

        model.addAttribute("products", productWithNumbers);
        model.addAttribute("total", totals[0]);
        model.addAttribute("totalTVA", totals[1]);

        System.out.println("PAGE BASKET : " + (productWithNumbers.size() > 0 ? "TRUE" : "FALSE"));

        return "basket";
    }

    /**
     * Add Product to basket
     * @param id product ID
     */
    @RequestMapping(value = "/basket/add/{id}")
    public String addProduct(@PathVariable(name = "id") Integer id, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/login";

        Product product;

        try {
            product = microserviceProductsProxy.getProductByID(id, false, false);
        } catch (FeignException e) {
            System.out.println("product not found");
            product = null;
        }

        if (product == null)
            return "error";

        List<ProductWithNumber> productWithNumbers;

        if (session.getAttribute("basket") == null) {
            productWithNumbers = new ArrayList<>();
            productWithNumbers.add(new ProductWithNumber(product, 1));
            session.setAttribute("basket", productWithNumbers);
        }
        else {
            productWithNumbers = (List<ProductWithNumber>) session.getAttribute("basket");

            Integer productExitsIndex = containProduct (productWithNumbers, product);

            if (productExitsIndex != -1) {
                productWithNumbers.get(productExitsIndex).add();
            }
            else {
                productWithNumbers.add(new ProductWithNumber(product, 1));
            }

            session.removeAttribute("basket");

            session.setAttribute("basket", productWithNumbers);
        }

        System.out.println("session set : " + (productWithNumbers.size() > 0 ? "TRUE" : "FALSE"));

        return "redirect:/basket";
    }


    /**
     * Display command page
     */
    @RequestMapping(value = "/command")
    public String createCommand(Model model, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/login";

        List<ProductWithNumber> productWithNumbers;

        if (session.getAttribute("basket") == null) {
            model.addAttribute("products", null);
            return "basket";
        }

        productWithNumbers = (List<ProductWithNumber>) session.getAttribute("basket");

        Double[] totals = calculateTotals(productWithNumbers);

        model.addAttribute("products", productWithNumbers);
        model.addAttribute("total", totals[0]);
        model.addAttribute("totalTVA", totals[1]);

        return "payment";
    }


    /**
     * Calculate total price and VAT (TVA in french)
     * @param productWithNumbers basket products
     * @return a double array. Index 0 = total price, Index 1 = total VAT (TVA in french)
     */
    private Double[] calculateTotals(List<ProductWithNumber> productWithNumbers) {
        Double[] totals = new Double[] {0.0, 0.0};

        for (ProductWithNumber pwn : productWithNumbers) {
            totals[0] += pwn.getProduct().getPrice() * pwn.getNumber();
        }

        totals[1] = totals[0] * 0.05;

        return totals;
    }

    /**
     * Check if the product is on the list and return index where the product is founded
     * @param productWithNumbers basket products
     * @param product product to search
     * @return return index of the founded product. return -1 if not founded.
     */
    private int containProduct(List<ProductWithNumber> productWithNumbers, Product product) {
        int listId = -1;

        for (int i = 0; i < productWithNumbers.size(); i++) {
            if (productWithNumbers.get(i).getProduct().getId().equals(product.getId()))
                listId = i;
        }

        return  listId;
    }
}
