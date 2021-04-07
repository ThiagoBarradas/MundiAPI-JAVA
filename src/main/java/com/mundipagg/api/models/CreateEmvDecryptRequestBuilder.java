/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class CreateEmvDecryptRequestBuilder {
    //the instance to build
    private CreateEmvDecryptRequest createEmvDecryptRequest;

    /**
     * Default constructor to initialize the instance
     */
    public CreateEmvDecryptRequestBuilder() {
        createEmvDecryptRequest = new CreateEmvDecryptRequest();
    }

    public CreateEmvDecryptRequestBuilder iccData(String iccData) {
        createEmvDecryptRequest.setIccData(iccData);
        return this;
    }

    public CreateEmvDecryptRequestBuilder cardSequenceNumber(String cardSequenceNumber) {
        createEmvDecryptRequest.setCardSequenceNumber(cardSequenceNumber);
        return this;
    }

    public CreateEmvDecryptRequestBuilder data(CreateEmvDataDecryptRequest data) {
        createEmvDecryptRequest.setData(data);
        return this;
    }

    public CreateEmvDecryptRequestBuilder poi(CreateCardPaymentContactlessPOIRequest poi) {
        createEmvDecryptRequest.setPoi(poi);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public CreateEmvDecryptRequest build() {
        return createEmvDecryptRequest;
    }
}