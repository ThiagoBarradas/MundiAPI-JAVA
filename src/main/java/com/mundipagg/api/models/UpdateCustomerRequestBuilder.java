/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;

public class UpdateCustomerRequestBuilder {
    //the instance to build
    private UpdateCustomerRequest updateCustomerRequest;

    /**
     * Default constructor to initialize the instance
     */
    public UpdateCustomerRequestBuilder() {
        updateCustomerRequest = new UpdateCustomerRequest();
    }

    /**
     * Name
     */
    public UpdateCustomerRequestBuilder name(String name) {
        updateCustomerRequest.setName(name);
        return this;
    }

    /**
     * Email
     */
    public UpdateCustomerRequestBuilder email(String email) {
        updateCustomerRequest.setEmail(email);
        return this;
    }

    /**
     * Document number
     */
    public UpdateCustomerRequestBuilder document(String document) {
        updateCustomerRequest.setDocument(document);
        return this;
    }

    /**
     * Person type
     */
    public UpdateCustomerRequestBuilder personType(String personType) {
        updateCustomerRequest.setPersonType(personType);
        return this;
    }

    /**
     * Address
     */
    public UpdateCustomerRequestBuilder address(CreateAddressRequest address) {
        updateCustomerRequest.setAddress(address);
        return this;
    }

    /**
     * Metadata
     */
    public UpdateCustomerRequestBuilder metadata(LinkedHashMap<String, String> metadata) {
        updateCustomerRequest.setMetadata(metadata);
        return this;
    }

    public UpdateCustomerRequestBuilder phones(CreatePhonesRequest phones) {
        updateCustomerRequest.setPhones(phones);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public UpdateCustomerRequest build() {
        return updateCustomerRequest;
    }
}