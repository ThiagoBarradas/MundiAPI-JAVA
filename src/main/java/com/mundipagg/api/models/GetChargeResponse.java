/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GetChargeResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5752018292488766934L;
    private String id;
    private String code;
    private String gatewayId;
    private int amount;
    private String status;
    private String currency;
    private String paymentMethod;
    private Date dueAt;
    private Date createdAt;
    private Date updatedAt;
    private GetTransactionResponse lastTransaction;
    private GetInvoiceResponse invoice;
    private GetOrderResponse order;
    private GetCustomerResponse customer;
    private LinkedHashMap<String, String> metadata;
    private Date paidAt;
    private Date canceledAt;
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("id")
    public String getId ( ) { 
        return this.id;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("id")
    public void setId (String value) { 
        this.id = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("code")
    public String getCode ( ) { 
        return this.code;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("code")
    public void setCode (String value) { 
        this.code = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("gateway_id")
    public String getGatewayId ( ) { 
        return this.gatewayId;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("gateway_id")
    public void setGatewayId (String value) { 
        this.gatewayId = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("amount")
    public int getAmount ( ) { 
        return this.amount;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("amount")
    public void setAmount (int value) { 
        this.amount = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("status")
    public String getStatus ( ) { 
        return this.status;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("status")
    public void setStatus (String value) { 
        this.status = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("currency")
    public String getCurrency ( ) { 
        return this.currency;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("currency")
    public void setCurrency (String value) { 
        this.currency = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("payment_method")
    public String getPaymentMethod ( ) { 
        return this.paymentMethod;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("payment_method")
    public void setPaymentMethod (String value) { 
        this.paymentMethod = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("due_at")
    public Date getDueAt ( ) { 
        return this.dueAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("due_at")
    public void setDueAt (Date value) { 
        this.dueAt = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("created_at")
    public Date getCreatedAt ( ) { 
        return this.createdAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("created_at")
    public void setCreatedAt (Date value) { 
        this.createdAt = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("updated_at")
    public Date getUpdatedAt ( ) { 
        return this.updatedAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("updated_at")
    public void setUpdatedAt (Date value) { 
        this.updatedAt = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("last_transaction")
    public GetTransactionResponse getLastTransaction ( ) { 
        return this.lastTransaction;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("last_transaction")
    public void setLastTransaction (GetTransactionResponse value) { 
        this.lastTransaction = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("invoice")
    public GetInvoiceResponse getInvoice ( ) { 
        return this.invoice;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("invoice")
    public void setInvoice (GetInvoiceResponse value) { 
        this.invoice = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("order")
    public GetOrderResponse getOrder ( ) { 
        return this.order;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("order")
    public void setOrder (GetOrderResponse value) { 
        this.order = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("customer")
    public GetCustomerResponse getCustomer ( ) { 
        return this.customer;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("customer")
    public void setCustomer (GetCustomerResponse value) { 
        this.customer = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("metadata")
    public LinkedHashMap<String, String> getMetadata ( ) { 
        return this.metadata;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("metadata")
    public void setMetadata (LinkedHashMap<String, String> value) { 
        this.metadata = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("paid_at")
    public Date getPaidAt ( ) { 
        return this.paidAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("paid_at")
    public void setPaidAt (Date value) { 
        this.paidAt = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("canceled_at")
    public Date getCanceledAt ( ) { 
        return this.canceledAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("canceled_at")
    public void setCanceledAt (Date value) { 
        this.canceledAt = value;
    }
 
}
 