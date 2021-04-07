/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreateSplitRequestBuilder {
    //the instance to build
    private CreateSplitRequest createSplitRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreateSplitRequestBuilder() {
        createSplitRequest = new CreateSplitRequest();
    }

    /**
     * Split type
     */
    public CreateSplitRequestBuilder type(String type) {
        createSplitRequest.setType(type);
        return this;
    }

    /**
     * Amount
     */
    public CreateSplitRequestBuilder amount(int amount) {
        createSplitRequest.setAmount(amount);
        return this;
    }

    /**
     * Recipient id
     */
    public CreateSplitRequestBuilder recipientId(String recipientId) {
        createSplitRequest.setRecipientId(recipientId);
        return this;
    }

    /**
     * The split options request
     */
    public CreateSplitRequestBuilder options(CreateSplitOptionsRequest options) {
        createSplitRequest.setOptions(options);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreateSplitRequest build() {
        return createSplitRequest;
    }
}