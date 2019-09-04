package com.alphamplyer.website.websiteadministration.beans.orders.enums;

/**
 * Enum to describe a status of an order
 */
public enum OrderStatus {
    CANCELED,
    CLOSED,
    COMPLETE,
    ON_HOLD,
    OPEN,
    PAYMENT_REVIEW,
    PAYPAL_CANCELED_REVERSAL,
    PAYPAL_REVERSED,
    PENDING,
    PENDING_PAYMENT,
    PENDING_PAYPAL,
    PROCESSING,
    SUSPECTED_FRAUD
}
