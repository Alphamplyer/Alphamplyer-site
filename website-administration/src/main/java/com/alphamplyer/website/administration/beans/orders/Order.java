package com.alphamplyer.website.administration.beans.orders;

import com.alphamplyer.website.administration.beans.orders.enums.OrderStatus;

import java.sql.Timestamp;

/**
 * Represent Order data
 */
public class Order {

    private Integer id;
    private Integer userId;
    private Integer creatorId;
    private OrderStatus status;
    private Integer paymentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Order() { }

    /**
     * Get ID of the order
     * @return ID of the order
     */
    public Integer getId() { return id; }

    /**
     * Get user ID of the order
     * @return user ID of the order
     */
    public Integer getUserId() { return userId; }

    /**
     * Get creator ID of the order
     * @return creator ID of the order
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * Get status of the order
     * @return status of the order
     */
    public OrderStatus getStatus() { return status; }

    /**
     * Get payment ID of the order
     * @return payment ID of the order
     */
    public Integer getPaymentId() { return paymentId; }

    /**
     * Get creation date time of the order
     * @return creation date time of the order
     */
    public Timestamp getCreatedAt() { return createdAt; }

    /**
     * Get last update date time of the order
     * @return creation date time of the order
     */
    public Timestamp getUpdatedAt() { return updatedAt; }

    /**
     * Define ID of the order
     * @param id ID of the order
     */
    public void setId(Integer id) { this.id = id; }

    /**
     * Define user ID of the order
     * @param userId user ID of the order
     */
    public void setUserId(Integer userId) { this.userId = userId; }

    /**
     * Define creator Id of the order
     * @param creatorId creator ID of the order
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * Define status of the order
     * @param status status of the order
     */
    public void setStatus(OrderStatus status) { this.status = status; }

    /**
     * Define payment ID of the order
     * @param paymentId payment of the order
     */
    public void setPaymentId(Integer paymentId) { this.paymentId = paymentId; }

    /**
     * Define creation date time of the order
     * @param createdAt creation date time of the order
     */
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    /**
     * Define last update date time of the order
     * @param updatedAt last update date time of the order
     */
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
