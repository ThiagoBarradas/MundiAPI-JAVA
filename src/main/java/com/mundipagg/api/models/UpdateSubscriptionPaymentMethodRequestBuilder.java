/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;

public class UpdateSubscriptionPaymentMethodRequestBuilder {
    //the instance to build
    private UpdateSubscriptionPaymentMethodRequest updateSubscriptionPaymentMethodRequest;

    /**
     * Default constructor to initialize the instance
     */
    public UpdateSubscriptionPaymentMethodRequestBuilder() {
        updateSubscriptionPaymentMethodRequest = new UpdateSubscriptionPaymentMethodRequest();
    }

    /**
     * The new payment method
     */
    public UpdateSubscriptionPaymentMethodRequestBuilder paymentMethod(String paymentMethod) {
        updateSubscriptionPaymentMethodRequest.setPaymentMethod(paymentMethod);
        return this;
    }

    /**
     * Card id
     */
    public UpdateSubscriptionPaymentMethodRequestBuilder cardId(String cardId) {
        updateSubscriptionPaymentMethodRequest.setCardId(cardId);
        return this;
    }

    /**
     * Card data
     */
    public UpdateSubscriptionPaymentMethodRequestBuilder card(CreateCardRequest card) {
        updateSubscriptionPaymentMethodRequest.setCard(card);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public UpdateSubscriptionPaymentMethodRequest build() {
        return updateSubscriptionPaymentMethodRequest;
    }
}