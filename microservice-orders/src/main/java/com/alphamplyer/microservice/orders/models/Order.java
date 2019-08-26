package com.alphamplyer.microservice.orders.models;

import com.alphamplyer.microservice.orders.models.enums.OrderStatus;

import java.sql.Timestamp;

public class Order {

    private Integer id;
    private Integer userId;
    private Integer creatorId;
    private OrderStatus status;
    private Integer paymentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Order() { }

    public Integer getId() { return id; }
    public Integer getUserId() { return userId; }
    public Integer getCreatorId() {
        return creatorId;
    }
    public OrderStatus getStatus() { return status; }
    public Integer getPaymentId() { return paymentId; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setId(Integer id) { this.id = id; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setPaymentId(Integer paymentId) { this.paymentId = paymentId; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", userId=" + userId +
            ", creatorId=" + creatorId +
            ", status='" + status + '\'' +
            ", paymentId=" + paymentId +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
