package com.alphamplyer.website.shop.proxies;

import com.alphamplyer.website.shop.beans.orders.Order;
import com.alphamplyer.website.shop.beans.orders.OrderLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-orders", url = "localhost:10501")
public interface MicroserviceOrdersProxy {

    //region Order Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    /**
     * Get Order from its ID
     * @param id order ID
     * @return order or 404 error if not founded
     */
    @GetMapping(value = "/orders/id/{id}")
    Order getOrderByID(@PathVariable(name = "id") Integer id);

    /**
     * Get list of user order
     * @param id user ID
     * @return list of user order
     */
    @GetMapping(value = "/orders/user/{id}")
    List<Order> getUserOrders (@PathVariable(name = "id") Integer id);

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    /**
     * Add order
     * @param order order data
     * @return saved order or error 500
     */
    @PostMapping(value = "/orders/add")
    Order addOrder (Order order);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    /**
     * Update order
     * @param order order data
     */
    @PutMapping(value = "/orders/save")
    void saveOrder (Order order);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    /**
     * Delete order
     * @param id order ID
     */
    @DeleteMapping(value = "/orders/delete/{id}")
    void deleteOrder (@PathVariable(name = "id") Integer id);

    //endregion

    //endregion

    //region Order Line Controller

    //region // --- < GET MAPPING > -------------------------------------------------------------------------------//

    /**
     * Get Order line from its ID
     * @param id Order line ID
     * @return an order line or 404 error if not founded
     */
    @GetMapping(value = "orders/lines/{id}")
    OrderLine getByID(@PathVariable(name = "id") Integer id);

    /**
     * Get order lines of an order
     * @param id order ID
     * @return list of order line or 404 error if not founded
     */
    @GetMapping(value = "orders/{id}/lines")
    List<OrderLine> getOrderLines(@PathVariable(name = "id") Integer id);

    //endregion

    //region // --- < POST MAPPING > ------------------------------------------------------------------------------//

    /**
     * Add order line to an order
     * @param orderLine order line data
     * @return added order line or error 500
     */
    @PostMapping(value = "orders/line/add")
    OrderLine add (@RequestBody OrderLine orderLine);

    /**
     * Add an order line to an order
     * @param orderLines order lines data
     * @return added order line or error 500
     */
    @PostMapping(value = "orders/lines/add")
    Void add(@RequestBody List<OrderLine> orderLines);

    //endregion

    //region // --- < PUT MAPPING > -------------------------------------------------------------------------------//

    /**
     * Save an order line to an order
     * @param orderLine order lines data
     */
    @PutMapping(value = "orders/line/save")
    void save (@RequestBody OrderLine orderLine);

    //endregion

    //region // --- < DELETE MAPPING > ----------------------------------------------------------------------------//

    /**
     * Delete an order line from its ID
     * @param id order lines ID
     */
    @DeleteMapping(value = "orders/line/{id}/delete")
    void delete (@PathVariable(name = "id") Integer id);

    //endregion

    //endregion

}
