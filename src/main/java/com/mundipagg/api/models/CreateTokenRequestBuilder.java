/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreateTokenRequestBuilder {
    //the instance to build
    private CreateTokenRequest createTokenRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreateTokenRequestBuilder() {
        createTokenRequest = new CreateTokenRequest();
    }

    /**
     * Token type
     */
    public CreateTokenRequestBuilder type(String type) {
        createTokenRequest.setType(type);
        return this;
    }

    /**
     * Card data
     */
    public CreateTokenRequestBuilder card(CreateCardTokenRequest card) {
        createTokenRequest.setCard(card);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreateTokenRequest build() {
        return createTokenRequest;
    }
}