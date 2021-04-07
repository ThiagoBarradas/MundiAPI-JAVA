/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreateCancelChargeSplitRulesRequestBuilder {
    //the instance to build
    private CreateCancelChargeSplitRulesRequest createCancelChargeSplitRulesRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreateCancelChargeSplitRulesRequestBuilder() {
        createCancelChargeSplitRulesRequest = new CreateCancelChargeSplitRulesRequest();
    }

    /**
     * The split rule gateway id
     */
    public CreateCancelChargeSplitRulesRequestBuilder id(String id) {
        createCancelChargeSplitRulesRequest.setId(id);
        return this;
    }

    /**
     * The split rule amount
     */
    public CreateCancelChargeSplitRulesRequestBuilder amount(int amount) {
        createCancelChargeSplitRulesRequest.setAmount(amount);
        return this;
    }

    /**
     * The amount type (flat ou percentage)
     */
    public CreateCancelChargeSplitRulesRequestBuilder type(String type) {
        createCancelChargeSplitRulesRequest.setType(type);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreateCancelChargeSplitRulesRequest build() {
        return createCancelChargeSplitRulesRequest;
    }
}