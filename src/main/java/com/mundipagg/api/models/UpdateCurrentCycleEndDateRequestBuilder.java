/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import org.joda.time.DateTime;

public class UpdateCurrentCycleEndDateRequestBuilder {
    //the instance to build
    private UpdateCurrentCycleEndDateRequest updateCurrentCycleEndDateRequest;

    /**
     * Default constructor to initialize the instance
     */
    public UpdateCurrentCycleEndDateRequestBuilder() {
        updateCurrentCycleEndDateRequest = new UpdateCurrentCycleEndDateRequest();
    }

    /**
     * Current cycle end date
     */
    public UpdateCurrentCycleEndDateRequestBuilder endAt(DateTime endAt) {
        updateCurrentCycleEndDateRequest.setEndAt(endAt);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public UpdateCurrentCycleEndDateRequest build() {
        return updateCurrentCycleEndDateRequest;
    }
}