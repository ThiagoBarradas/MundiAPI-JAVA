/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(Include.ALWAYS)
public class GetCheckoutDebitCardPaymentResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = -674702094035242615L;
    private String statementDescriptor;
    private GetPaymentAuthenticationResponse authentication;
    /** GETTER
     * Descrição na fatura
     */
    @JsonGetter("statement_descriptor")
    public String getStatementDescriptor ( ) { 
        return this.statementDescriptor;
    }
    
    /** SETTER
     * Descrição na fatura
     */
    @JsonSetter("statement_descriptor")
    public void setStatementDescriptor (String value) { 
        this.statementDescriptor = value;
    }
 
    /** GETTER
     * Payment Authentication response object data
     */
    @JsonGetter("authentication")
    public GetPaymentAuthenticationResponse getAuthentication ( ) { 
        return this.authentication;
    }
    
    /** SETTER
     * Payment Authentication response object data
     */
    @JsonSetter("authentication")
    public void setAuthentication (GetPaymentAuthenticationResponse value) { 
        this.authentication = value;
    }
 
}
