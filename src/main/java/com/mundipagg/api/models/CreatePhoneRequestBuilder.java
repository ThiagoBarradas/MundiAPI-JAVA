/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreatePhoneRequestBuilder {
    //the instance to build
    private CreatePhoneRequest createPhoneRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreatePhoneRequestBuilder() {
        createPhoneRequest = new CreatePhoneRequest();
    }

    public CreatePhoneRequestBuilder countryCode(String countryCode) {
        createPhoneRequest.setCountryCode(countryCode);
        return this;
    }

    public CreatePhoneRequestBuilder number(String number) {
        createPhoneRequest.setNumber(number);
        return this;
    }

    public CreatePhoneRequestBuilder areaCode(String areaCode) {
        createPhoneRequest.setAreaCode(areaCode);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreatePhoneRequest build() {
        return createPhoneRequest;
    }
}