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
public class PixAdditionalInformation 
        implements java.io.Serializable {
    private static final long serialVersionUID = 1835378327434428890L;
    private String name;
    private String value;
    /** GETTER
     * 
     */
    @JsonGetter("Name")
    public String getName ( ) { 
        return this.name;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("Name")
    public void setName (String value) { 
        this.name = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("Value")
    public String getValue ( ) { 
        return this.value;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("Value")
    public void setValue (String value) { 
        this.value = value;
    }
 
}
