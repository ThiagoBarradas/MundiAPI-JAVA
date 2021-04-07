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
public class CreateEmvDataDukptDecryptRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = -92122963077223530L;
    private String ksn;
    /** GETTER
     * Key serial number
     */
    @JsonGetter("ksn")
    public String getKsn ( ) { 
        return this.ksn;
    }
    
    /** SETTER
     * Key serial number
     */
    @JsonSetter("ksn")
    public void setKsn (String value) { 
        this.ksn = value;
    }
 
}
