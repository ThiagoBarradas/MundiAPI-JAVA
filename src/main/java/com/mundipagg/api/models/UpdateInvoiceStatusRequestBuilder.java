/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;

public class UpdateInvoiceStatusRequestBuilder {
    //the instance to build
    private UpdateInvoiceStatusRequest updateInvoiceStatusRequest;

    /**
     * Default constructor to initialize the instance
     */
    public UpdateInvoiceStatusRequestBuilder() {
        updateInvoiceStatusRequest = new UpdateInvoiceStatusRequest();
    }

    /**
     * Status
     */
    public UpdateInvoiceStatusRequestBuilder status(String status) {
        updateInvoiceStatusRequest.setStatus(status);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public UpdateInvoiceStatusRequest build() {
        return updateInvoiceStatusRequest;
    }
}