/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreateChargeRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5741491609507209726L;
    private String code;
    private int amount;
    private String customerId;
    private CreateCustomerRequest customer;
    private CreatePaymentRequest payment;
    private LinkedHashMap<String, String> metadata;
    private Date dueAt;
    /** GETTER
     * Code
     */
    @JsonGetter("code")
    public String getCode ( ) { 
        return this.code;
    }
    
    /** SETTER
     * Code
     */
    @JsonSetter("code")
    public void setCode (String value) { 
        this.code = value;
    }
 
    /** GETTER
     * The amount of the charge, in cents
     */
    @JsonGetter("amount")
    public int getAmount ( ) { 
        return this.amount;
    }
    
    /** SETTER
     * The amount of the charge, in cents
     */
    @JsonSetter("amount")
    public void setAmount (int value) { 
        this.amount = value;
    }
 
    /** GETTER
     * The customer's id
     */
    @JsonGetter("customer_id")
    public String getCustomerId ( ) { 
        return this.customerId;
    }
    
    /** SETTER
     * The customer's id
     */
    @JsonSetter("customer_id")
    public void setCustomerId (String value) { 
        this.customerId = value;
    }
 
    /** GETTER
     * Customer data
     */
    @JsonGetter("customer")
    public CreateCustomerRequest getCustomer ( ) { 
        return this.customer;
    }
    
    /** SETTER
     * Customer data
     */
    @JsonSetter("customer")
    public void setCustomer (CreateCustomerRequest value) { 
        this.customer = value;
    }
 
    /** GETTER
     * Payment data
     */
    @JsonGetter("payment")
    public CreatePaymentRequest getPayment ( ) { 
        return this.payment;
    }
    
    /** SETTER
     * Payment data
     */
    @JsonSetter("payment")
    public void setPayment (CreatePaymentRequest value) { 
        this.payment = value;
    }
 
    /** GETTER
     * Metadata
     */
    @JsonGetter("metadata")
    public LinkedHashMap<String, String> getMetadata ( ) { 
        return this.metadata;
    }
    
    /** SETTER
     * Metadata
     */
    @JsonSetter("metadata")
    public void setMetadata (LinkedHashMap<String, String> value) { 
        this.metadata = value;
    }
 
    /** GETTER
     * The charge due date
     */
    @JsonGetter("due_at")
    public Date getDueAt ( ) { 
        return this.dueAt;
    }
    
    /** SETTER
     * The charge due date
     */
    @JsonSetter("due_at")
    public void setDueAt (Date value) { 
        this.dueAt = value;
    }
 
}
 