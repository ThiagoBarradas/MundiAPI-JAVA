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
public class GetAnticipationLimitResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 577876872064850112L;
    private int amount;
    private int anticipationFee;
    /** GETTER
     * Amount
     */
    @JsonGetter("amount")
    public int getAmount ( ) { 
        return this.amount;
    }
    
    /** SETTER
     * Amount
     */
    @JsonSetter("amount")
    public void setAmount (int value) { 
        this.amount = value;
    }
 
    /** GETTER
     * Anticipation fee
     */
    @JsonGetter("anticipation_fee")
    public int getAnticipationFee ( ) { 
        return this.anticipationFee;
    }
    
    /** SETTER
     * Anticipation fee
     */
    @JsonSetter("anticipation_fee")
    public void setAnticipationFee (int value) { 
        this.anticipationFee = value;
    }
 
}
