/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import org.joda.time.DateTime;

public class GetPeriodResponseBuilder {
    //the instance to build
    private GetPeriodResponse getPeriodResponse;

    /**
     * Default constructor to initialize the instance
     */
    public GetPeriodResponseBuilder() {
        getPeriodResponse = new GetPeriodResponse();
    }

    public GetPeriodResponseBuilder startAt(DateTime startAt) {
        getPeriodResponse.setStartAt(startAt);
        return this;
    }

    public GetPeriodResponseBuilder endAt(DateTime endAt) {
        getPeriodResponse.setEndAt(endAt);
        return this;
    }

    public GetPeriodResponseBuilder id(String id) {
        getPeriodResponse.setId(id);
        return this;
    }

    public GetPeriodResponseBuilder billingAt(DateTime billingAt) {
        getPeriodResponse.setBillingAt(billingAt);
        return this;
    }

    public GetPeriodResponseBuilder subscription(GetSubscriptionResponse subscription) {
        getPeriodResponse.setSubscription(subscription);
        return this;
    }

    public GetPeriodResponseBuilder status(String status) {
        getPeriodResponse.setStatus(status);
        return this;
    }

    public GetPeriodResponseBuilder duration(int duration) {
        getPeriodResponse.setDuration(duration);
        return this;
    }

    public GetPeriodResponseBuilder createdAt(String createdAt) {
        getPeriodResponse.setCreatedAt(createdAt);
        return this;
    }

    public GetPeriodResponseBuilder updatedAt(String updatedAt) {
        getPeriodResponse.setUpdatedAt(updatedAt);
        return this;
    }

    public GetPeriodResponseBuilder cycle(int cycle) {
        getPeriodResponse.setCycle(cycle);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public GetPeriodResponse build() {
        return getPeriodResponse;
    }
}