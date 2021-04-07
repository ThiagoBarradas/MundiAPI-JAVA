/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class CreateCancelChargeRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = -1205350500177026052L;
    private Integer amount;
    private List<CreateCancelChargeSplitRulesRequest> splitRules;
    private List<CreateSplitRequest> split;
    private String operationReference;
    /** GETTER
     * The amount that will be canceled.
     */
    @JsonGetter("amount")
    public Integer getAmount ( ) { 
        return this.amount;
    }
    
    /** SETTER
     * The amount that will be canceled.
     */
    @JsonSetter("amount")
    public void setAmount (Integer value) { 
        this.amount = value;
    }
 
    /** GETTER
     * The split rules request
     */
    @JsonGetter("split_rules")
    public List<CreateCancelChargeSplitRulesRequest> getSplitRules ( ) { 
        return this.splitRules;
    }
    
    /** SETTER
     * The split rules request
     */
    @JsonSetter("split_rules")
    public void setSplitRules (List<CreateCancelChargeSplitRulesRequest> value) { 
        this.splitRules = value;
    }
 
    /** GETTER
     * Splits
     */
    @JsonGetter("split")
    public List<CreateSplitRequest> getSplit ( ) { 
        return this.split;
    }
    
    /** SETTER
     * Splits
     */
    @JsonSetter("split")
    public void setSplit (List<CreateSplitRequest> value) { 
        this.split = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("operation_reference")
    public String getOperationReference ( ) { 
        return this.operationReference;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("operation_reference")
    public void setOperationReference (String value) { 
        this.operationReference = value;
    }
 
}
