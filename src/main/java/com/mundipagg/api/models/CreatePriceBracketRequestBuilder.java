/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreatePriceBracketRequestBuilder {
    //the instance to build
    private CreatePriceBracketRequest createPriceBracketRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreatePriceBracketRequestBuilder() {
        createPriceBracketRequest = new CreatePriceBracketRequest();
    }

    /**
     * Start quantity
     */
    public CreatePriceBracketRequestBuilder startQuantity(int startQuantity) {
        createPriceBracketRequest.setStartQuantity(startQuantity);
        return this;
    }

    /**
     * Price
     */
    public CreatePriceBracketRequestBuilder price(int price) {
        createPriceBracketRequest.setPrice(price);
        return this;
    }

    /**
     * End quantity
     */
    public CreatePriceBracketRequestBuilder endQuantity(Integer endQuantity) {
        createPriceBracketRequest.setEndQuantity(endQuantity);
        return this;
    }

    /**
     * Overage price
     */
    public CreatePriceBracketRequestBuilder overagePrice(Integer overagePrice) {
        createPriceBracketRequest.setOveragePrice(overagePrice);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreatePriceBracketRequest build() {
        return createPriceBracketRequest;
    }
}