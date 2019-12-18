/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;

public class GetWithdrawSourceResponseBuilder {
    //the instance to build
    private GetWithdrawSourceResponse getWithdrawSourceResponse;

    /**
     * Default constructor to initialize the instance
     */
    public GetWithdrawSourceResponseBuilder() {
        getWithdrawSourceResponse = new GetWithdrawSourceResponse();
    }

    public GetWithdrawSourceResponseBuilder sourceId(String sourceId) {
        getWithdrawSourceResponse.setSourceId(sourceId);
        return this;
    }

    public GetWithdrawSourceResponseBuilder type(String type) {
        getWithdrawSourceResponse.setType(type);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public GetWithdrawSourceResponse build() {
        return getWithdrawSourceResponse;
    }
}