/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import org.joda.time.DateTime;

public class GetAccessTokenResponseBuilder {
    //the instance to build
    private GetAccessTokenResponse getAccessTokenResponse;

    /**
     * Default constructor to initialize the instance
     */
    public GetAccessTokenResponseBuilder() {
        getAccessTokenResponse = new GetAccessTokenResponse();
    }

    public GetAccessTokenResponseBuilder id(String id) {
        getAccessTokenResponse.setId(id);
        return this;
    }

    public GetAccessTokenResponseBuilder code(String code) {
        getAccessTokenResponse.setCode(code);
        return this;
    }

    public GetAccessTokenResponseBuilder status(String status) {
        getAccessTokenResponse.setStatus(status);
        return this;
    }

    public GetAccessTokenResponseBuilder createdAt(DateTime createdAt) {
        getAccessTokenResponse.setCreatedAt(createdAt);
        return this;
    }

    public GetAccessTokenResponseBuilder customer(GetCustomerResponse customer) {
        getAccessTokenResponse.setCustomer(customer);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public GetAccessTokenResponse build() {
        return getAccessTokenResponse;
    }
}