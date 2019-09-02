package com.alphamplyer.microservice.orders.models;

import com.alphamplyer.microservice.orders.models.enums.OrderLineStatus;
import com.alphamplyer.microservice.orders.models.enums.RenewalRate;

public class OrderLine {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private OrderLineStatus status;
    private Integer quantity;
    private Double price;
    private Double reductionAmount;
    private RenewalRate renewalRate;

    public OrderLine () { }

    /**
     * Get ID of the order line
     * @return Id of the order line
     */
    public Integer getId() { return id; }

    /**
     * Get order Id of the order line
     * @return oder Id of the order line
     */
    public Integer getOrderId() { return orderId; }

    /**
     * Get production ID of the order line
     * @return production ID of the order line
     */
    public Integer getProductId() { return productId; }

    /**
     * Get status of the order line
     * @return status of the order line
     */
    public OrderLineStatus getStatus() { return status; }

    /**
     * Get quantity of the order line
     * @return quantity of the order line
     */
    public Integer getQuantity() { return quantity; }

    /**
     * get price of the order line
     * @return price of the order line
     */
    public Double getPrice() { return price; }

    /**
     * Get reduction amount of the order line
     * @return reduction amount of the order line
     */
    public Double getReductionAmount() { return reductionAmount; }

    /**
     * Get renewal rate of the order line
     * @return renewal rate of the order line
     */
    public RenewalRate getRenewalRate() { return renewalRate; }


    /**
     * Define ID of the order line
     * @param id ID of the order line
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Define order ID of the order line
     * @param orderId order ID of the order line
     */
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    /**
     * Define product ID of the order line
     * @param productId product ID of the order line
     */
    public void setProductId(Integer productId) { this.productId = productId; }

    /**
     * Define status of the order line
     * @param status status of the order line
     */
    public void setStatus(OrderLineStatus status) { this.status = status; }

    /**
     * Define quantity of the order line
     * @param quantity quantity of the order line
     */
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    /**
     * Define price of the order line
     * @param price price of the order line
     */
    public void setPrice(Double price) { this.price = price; }

    /**
     * Define reduction amount of the order line
     * @param reductionAmount reduction amount of the order line
     */
    public void setReductionAmount(Double reductionAmount) { this.reductionAmount = reductionAmount; }

    /**
     * Define renewal rate of the order line
     * @param renewalRate renewal rate of the order line
     */
    public void setRenewalRate(RenewalRate renewalRate) { this.renewalRate = renewalRate; }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + id +
            ", orderId=" + orderId +
            ", productId=" + productId +
            ", status='" + status + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            ", ReductionAmount=" + reductionAmount +
            ", renewalRate='" + renewalRate + '\'' +
            '}';
    }
}
