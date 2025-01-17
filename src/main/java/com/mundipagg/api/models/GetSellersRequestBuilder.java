/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class GetSellersRequestBuilder {
    //the instance to build
    private GetSellersRequest getSellersRequest;

    /**
     * Default constructor to initialize the instance
     */
    public GetSellersRequestBuilder() {
        getSellersRequest = new GetSellersRequest();
    }

    public GetSellersRequestBuilder name(String name) {
        getSellersRequest.setName(name);
        return this;
    }

    public GetSellersRequestBuilder document(String document) {
        getSellersRequest.setDocument(document);
        return this;
    }

    public GetSellersRequestBuilder code(String code) {
        getSellersRequest.setCode(code);
        return this;
    }

    public GetSellersRequestBuilder status(String status) {
        getSellersRequest.setStatus(status);
        return this;
    }

    public GetSellersRequestBuilder type(String type) {
        getSellersRequest.setType(type);
        return this;
    }

    public GetSellersRequestBuilder createdSince(String createdSince) {
        getSellersRequest.setCreatedSince(createdSince);
        return this;
    }

    public GetSellersRequestBuilder createdUntil(String createdUntil) {
        getSellersRequest.setCreatedUntil(createdUntil);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public GetSellersRequest build() {
        return getSellersRequest;
    }
}