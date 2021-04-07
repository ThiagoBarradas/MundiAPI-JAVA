/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import org.joda.time.DateTime;

public class GetDiscountResponseBuilder {
    //the instance to build
    private GetDiscountResponse getDiscountResponse;

    /**
     * Default constructor to initialize the instance
     */
    public GetDiscountResponseBuilder() {
        getDiscountResponse = new GetDiscountResponse();
    }

    public GetDiscountResponseBuilder id(String id) {
        getDiscountResponse.setId(id);
        return this;
    }

    public GetDiscountResponseBuilder value(double value) {
        getDiscountResponse.setValue(value);
        return this;
    }

    public GetDiscountResponseBuilder discountType(String discountType) {
        getDiscountResponse.setDiscountType(discountType);
        return this;
    }

    public GetDiscountResponseBuilder status(String status) {
        getDiscountResponse.setStatus(status);
        return this;
    }

    public GetDiscountResponseBuilder createdAt(DateTime createdAt) {
        getDiscountResponse.setCreatedAt(createdAt);
        return this;
    }

    public GetDiscountResponseBuilder cycles(Integer cycles) {
        getDiscountResponse.setCycles(cycles);
        return this;
    }

    public GetDiscountResponseBuilder deletedAt(DateTime deletedAt) {
        getDiscountResponse.setDeletedAt(deletedAt);
        return this;
    }

    public GetDiscountResponseBuilder description(String description) {
        getDiscountResponse.setDescription(description);
        return this;
    }

    public GetDiscountResponseBuilder subscription(GetSubscriptionResponse subscription) {
        getDiscountResponse.setSubscription(subscription);
        return this;
    }

    /**
     * The subscription item
     */
    public GetDiscountResponseBuilder subscriptionItem(GetSubscriptionItemResponse subscriptionItem) {
        getDiscountResponse.setSubscriptionItem(subscriptionItem);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public GetDiscountResponse build() {
        return getDiscountResponse;
    }
}