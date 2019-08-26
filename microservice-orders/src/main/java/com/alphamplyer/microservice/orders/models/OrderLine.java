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

    public Integer getId() { return id; }
    public Integer getOrderId() { return orderId; }
    public Integer getProductId() { return productId; }
    public OrderLineStatus getStatus() { return status; }
    public Integer getQuantity() { return quantity; }
    public Double getPrice() { return price; }
    public Double getReductionAmount() { return reductionAmount; }
    public RenewalRate getRenewalRate() { return renewalRate; }

    public void setId(Integer id) { this.id = id; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public void setProductId(Integer productId) { this.productId = productId; }
    public void setStatus(OrderLineStatus status) { this.status = status; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setPrice(Double price) { this.price = price; }
    public void setReductionAmount(Double reductionAmount) { this.reductionAmount = reductionAmount; }
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
