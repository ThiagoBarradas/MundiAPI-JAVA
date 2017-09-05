/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreateDiscountRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5282425484902664451L;
    private double value;
    private String discountType;
    private String itemId;
    private Integer cycles;
    /** GETTER
     * The discount value
     */
    @JsonGetter("value")
    public double getValue ( ) { 
        return this.value;
    }
    
    /** SETTER
     * The discount value
     */
    @JsonSetter("value")
    public void setValue (double value) { 
        this.value = value;
    }
 
    /** GETTER
     * Discount type. Can be either flat or percentage.
     */
    @JsonGetter("discount_type")
    public String getDiscountType ( ) { 
        return this.discountType;
    }
    
    /** SETTER
     * Discount type. Can be either flat or percentage.
     */
    @JsonSetter("discount_type")
    public void setDiscountType (String value) { 
        this.discountType = value;
    }
 
    /** GETTER
     * The item where the discount will be applied
     */
    @JsonGetter("item_id")
    public String getItemId ( ) { 
        return this.itemId;
    }
    
    /** SETTER
     * The item where the discount will be applied
     */
    @JsonSetter("item_id")
    public void setItemId (String value) { 
        this.itemId = value;
    }
 
    /** GETTER
     * Number of cycles that the discount will be applied
     */
    @JsonGetter("cycles")
    public Integer getCycles ( ) { 
        return this.cycles;
    }
    
    /** SETTER
     * Number of cycles that the discount will be applied
     */
    @JsonSetter("cycles")
    public void setCycles (Integer value) { 
        this.cycles = value;
    }
 
}
 