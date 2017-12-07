/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreateCheckoutCardPaymentRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 4938799233166418219L;
    private String statementDescriptor;
    private List<CreateCheckoutCardInstallmentOptionRequest> installments;
    /** GETTER
     * Card invoice text descriptor
     */
    @JsonGetter("statement_descriptor")
    public String getStatementDescriptor ( ) { 
        return this.statementDescriptor;
    }
    
    /** SETTER
     * Card invoice text descriptor
     */
    @JsonSetter("statement_descriptor")
    public void setStatementDescriptor (String value) { 
        this.statementDescriptor = value;
    }
 
    /** GETTER
     * Payment installment options
     */
    @JsonGetter("installments")
    public List<CreateCheckoutCardInstallmentOptionRequest> getInstallments ( ) { 
        return this.installments;
    }
    
    /** SETTER
     * Payment installment options
     */
    @JsonSetter("installments")
    public void setInstallments (List<CreateCheckoutCardInstallmentOptionRequest> value) { 
        this.installments = value;
    }
 
}
 