/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;

public class ApplePayHeaderRequestBuilder {
    //the instance to build
    private ApplePayHeaderRequest applePayHeaderRequest;

    /**
     * Default constructor to initialize the instance
     */
    public ApplePayHeaderRequestBuilder() {
        applePayHeaderRequest = new ApplePayHeaderRequest();
    }

    /**
     * X.509 encoded key bytes, Base64 encoded as a string
     */
    public ApplePayHeaderRequestBuilder ephemeralPublicKey(String ephemeralPublicKey) {
        applePayHeaderRequest.setEphemeralPublicKey(ephemeralPublicKey);
        return this;
    }

    /**
     * SHA–256 hash, Base64 string codified
     */
    public ApplePayHeaderRequestBuilder publicKeyHash(String publicKeyHash) {
        applePayHeaderRequest.setPublicKeyHash(publicKeyHash);
        return this;
    }

    /**
     * Transaction identifier, generated on Device
     */
    public ApplePayHeaderRequestBuilder transactionId(String transactionId) {
        applePayHeaderRequest.setTransactionId(transactionId);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public ApplePayHeaderRequest build() {
        return applePayHeaderRequest;
    }
}