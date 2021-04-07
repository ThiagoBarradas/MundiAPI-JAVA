package com.mundipagg.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonInclude(Include.ALWAYS)
public class CreateCaptureChargeRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5178838949840272818L;
    private String code;
    private Integer amount;
    private List<CreateSplitRequest> split;
    private String operationReference;
    /** GETTER
     * Code for the charge. Sending this field will update the code send on the charge and order creation.
     */
    @JsonGetter("code")
    public String getCode ( ) { 
        return this.code;
    }
    
    /** SETTER
     * Code for the charge. Sending this field will update the code send on the charge and order creation.
     */
    @JsonSetter("code")
    public void setCode (String value) { 
        this.code = value;
    }
 
    /** GETTER
     * The amount that will be captured
     */
    @JsonGetter("amount")
    public Integer getAmount ( ) { 
        return this.amount;
    }
    
    /** SETTER
     * The amount that will be captured
     */
    @JsonSetter("amount")
    public void setAmount (Integer value) { 
        this.amount = value;
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
