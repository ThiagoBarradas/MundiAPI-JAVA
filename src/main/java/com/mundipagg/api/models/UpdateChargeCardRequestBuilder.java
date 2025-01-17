/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class UpdateChargeCardRequestBuilder {
    //the instance to build
    private UpdateChargeCardRequest updateChargeCardRequest;

    /**
     * Default constructor to initialize the instance
     */
    public UpdateChargeCardRequestBuilder() {
        updateChargeCardRequest = new UpdateChargeCardRequest();
    }

    /**
     * Indicates if the subscriptions using this card must also be updated
     */
    public UpdateChargeCardRequestBuilder updateSubscription(boolean updateSubscription) {
        updateChargeCardRequest.setUpdateSubscription(updateSubscription);
        return this;
    }

    /**
     * Card id
     */
    public UpdateChargeCardRequestBuilder cardId(String cardId) {
        updateChargeCardRequest.setCardId(cardId);
        return this;
    }

    /**
     * Card data
     */
    public UpdateChargeCardRequestBuilder card(CreateCardRequest card) {
        updateChargeCardRequest.setCard(card);
        return this;
    }

    /**
     * Indicates a recurrence
     */
    public UpdateChargeCardRequestBuilder recurrence(boolean recurrence) {
        updateChargeCardRequest.setRecurrence(recurrence);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public UpdateChargeCardRequest build() {
        return updateChargeCardRequest;
    }
}