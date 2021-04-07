/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

public class UpdateRecipientBankAccountRequestBuilder {
    //the instance to build
    private UpdateRecipientBankAccountRequest updateRecipientBankAccountRequest;

    /**
     * Default constructor to initialize the instance
     */
    public UpdateRecipientBankAccountRequestBuilder() {
        updateRecipientBankAccountRequest = new UpdateRecipientBankAccountRequest();
    }

    /**
     * Bank account
     */
    public UpdateRecipientBankAccountRequestBuilder bankAccount(CreateBankAccountRequest bankAccount) {
        updateRecipientBankAccountRequest.setBankAccount(bankAccount);
        return this;
    }
    /**
     * Build the instance with the given values
     */
    public UpdateRecipientBankAccountRequest build() {
        return updateRecipientBankAccountRequest;
    }
}