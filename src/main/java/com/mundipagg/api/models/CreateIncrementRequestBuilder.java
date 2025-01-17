/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreateIncrementRequestBuilder {
    //the instance to build
    private CreateIncrementRequest createIncrementRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreateIncrementRequestBuilder() {
        createIncrementRequest = new CreateIncrementRequest();
    }

    /**
     * The increment value
     */
    public CreateIncrementRequestBuilder value(double value) {
        createIncrementRequest.setValue(value);
        return this;
    }

    /**
     * Increment type. Can be either flat or percentage.
     */
    public CreateIncrementRequestBuilder incrementType(String incrementType) {
        createIncrementRequest.setIncrementType(incrementType);
        return this;
    }

    /**
     * The item where the increment will be applied
     */
    public CreateIncrementRequestBuilder itemId(String itemId) {
        createIncrementRequest.setItemId(itemId);
        return this;
    }

    /**
     * Number of cycles that the increment will be applied
     */
    public CreateIncrementRequestBuilder cycles(Integer cycles) {
        createIncrementRequest.setCycles(cycles);
        return this;
    }

    /**
     * Description
     */
    public CreateIncrementRequestBuilder description(String description) {
        createIncrementRequest.setDescription(description);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreateIncrementRequest build() {
        return createIncrementRequest;
    }
}