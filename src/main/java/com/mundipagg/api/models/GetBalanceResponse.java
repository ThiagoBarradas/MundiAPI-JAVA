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
public class GetBalanceResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 4290020402308774883L;
    private String currency;
    private int availableAmount;
    private GetRecipientResponse recipient;
    private int waitingFundsAmount;
    private int transferredAmount;
    /** GETTER
     * Currency
     */
    @JsonGetter("currency")
    public String getCurrency ( ) { 
        return this.currency;
    }
    
    /** SETTER
     * Currency
     */
    @JsonSetter("currency")
    public void setCurrency (String value) { 
        this.currency = value;
    }
 
    /** GETTER
     * Amount available for transferring
     */
    @JsonGetter("available_amount")
    public int getAvailableAmount ( ) { 
        return this.availableAmount;
    }
    
    /** SETTER
     * Amount available for transferring
     */
    @JsonSetter("available_amount")
    public void setAvailableAmount (int value) { 
        this.availableAmount = value;
    }
 
    /** GETTER
     * Recipient
     */
    @JsonGetter("recipient")
    public GetRecipientResponse getRecipient ( ) { 
        return this.recipient;
    }
    
    /** SETTER
     * Recipient
     */
    @JsonSetter("recipient")
    public void setRecipient (GetRecipientResponse value) { 
        this.recipient = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("waiting_funds_amount")
    public int getWaitingFundsAmount ( ) { 
        return this.waitingFundsAmount;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("waiting_funds_amount")
    public void setWaitingFundsAmount (int value) { 
        this.waitingFundsAmount = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("transferred_amount")
    public int getTransferredAmount ( ) { 
        return this.transferredAmount;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("transferred_amount")
    public void setTransferredAmount (int value) { 
        this.transferredAmount = value;
    }
 
}
