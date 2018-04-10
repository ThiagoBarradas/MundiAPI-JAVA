/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreateBankTransferPaymentRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5355735114274437557L;
    private String bank;
    private int retries;
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
     * Number of retries
     */
    @JsonGetter("retries")
    public int getRetries ( ) { 
        return this.retries;
    }
    
    /** SETTER
     * Number of retries
     */
    @JsonSetter("retries")
    public void setRetries (int value) { 
        this.retries = value;
    }
 
}
 