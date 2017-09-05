/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GetBankTransferTransactionResponse 
        extends GetTransactionResponse {
    private static final long serialVersionUID = 5085904691899753270L;
    private String url;
    private String bankTid;
    private String bank;
    private Date paidAt;
    private Integer paidAmount;
    /** GETTER
     * Payment url
     */
    @JsonGetter("url")
    public String getUrl ( ) { 
        return this.url;
    }
    
    /** SETTER
     * Payment url
     */
    @JsonSetter("url")
    public void setUrl (String value) { 
        this.url = value;
    }
 
    /** GETTER
     * Transaction identifier for the bank
     */
    @JsonGetter("bank_tid")
    public String getBankTid ( ) { 
        return this.bankTid;
    }
    
    /** SETTER
     * Transaction identifier for the bank
     */
    @JsonSetter("bank_tid")
    public void setBankTid (String value) { 
        this.bankTid = value;
    }
 
    /** GETTER
     * Bank
     */
    @JsonGetter("bank")
    public String getBank ( ) { 
        return this.bank;
    }
    
    /** SETTER
     * Bank
     */
    @JsonSetter("bank")
    public void setBank (String value) { 
        this.bank = value;
    }
 
    /** GETTER
     * Payment date
     */
    @JsonGetter("paid_at")
    public Date getPaidAt ( ) { 
        return this.paidAt;
    }
    
    /** SETTER
     * Payment date
     */
    @JsonSetter("paid_at")
    public void setPaidAt (Date value) { 
        this.paidAt = value;
    }
 
    /** GETTER
     * Paid amount
     */
    @JsonGetter("paid_amount")
    public Integer getPaidAmount ( ) { 
        return this.paidAmount;
    }
    
    /** SETTER
     * Paid amount
     */
    @JsonSetter("paid_amount")
    public void setPaidAmount (Integer value) { 
        this.paidAmount = value;
    }
 
}
 