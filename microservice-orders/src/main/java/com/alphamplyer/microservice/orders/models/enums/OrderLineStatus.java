package com.alphamplyer.microservice.orders.models.enums;

/**
 * Enum to describe a status of an order line
 */
public enum OrderLineStatus {
    DELIVERED,
    DELIVERED_IN_GAME,
    IN_PREPARATION,
    MISSING_IN_STOCK,
    PREPARED
}