/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreateCheckoutCardInstallmentOptionRequestBuilder {
    //the instance to build
    private CreateCheckoutCardInstallmentOptionRequest createCheckoutCardInstallmentOptionRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreateCheckoutCardInstallmentOptionRequestBuilder() {
        createCheckoutCardInstallmentOptionRequest = new CreateCheckoutCardInstallmentOptionRequest();
    }

    /**
     * Installment quantity
     */
    public CreateCheckoutCardInstallmentOptionRequestBuilder number(int number) {
        createCheckoutCardInstallmentOptionRequest.setNumber(number);
        return this;
    }

    /**
     * Total amount
     */
    public CreateCheckoutCardInstallmentOptionRequestBuilder total(int total) {
        createCheckoutCardInstallmentOptionRequest.setTotal(total);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreateCheckoutCardInstallmentOptionRequest build() {
        return createCheckoutCardInstallmentOptionRequest;
    }
}