/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class CreateClearSaleRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = -124699077007290772L;
    private int customSla;
    /** GETTER
     * 
     */
    @JsonGetter("custom_sla")
    public int getCustomSla ( ) { 
        return this.customSla;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("custom_sla")
    public void setCustomSla (int value) { 
        this.customSla = value;
    }
 
}
