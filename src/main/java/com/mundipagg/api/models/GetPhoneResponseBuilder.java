/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class GetPhoneResponseBuilder {
    //the instance to build
    private GetPhoneResponse getPhoneResponse;

    /**
     * Default constructor to initialize the instance
     */
    public GetPhoneResponseBuilder() {
        getPhoneResponse = new GetPhoneResponse();
    }

    public GetPhoneResponseBuilder countryCode(String countryCode) {
        getPhoneResponse.setCountryCode(countryCode);
        return this;
    }

    public GetPhoneResponseBuilder number(String number) {
        getPhoneResponse.setNumber(number);
        return this;
    }

    public GetPhoneResponseBuilder areaCode(String areaCode) {
        getPhoneResponse.setAreaCode(areaCode);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public GetPhoneResponse build() {
        return getPhoneResponse;
    }
}