/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GetPhoneResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5585618159855401941L;
    private String countryCode;
    private String number;
    private String areaCode;
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("country_code")
    public String getCountryCode ( ) { 
        return this.countryCode;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("country_code")
    public void setCountryCode (String value) { 
        this.countryCode = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("number")
    public String getNumber ( ) { 
        return this.number;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("number")
    public void setNumber (String value) { 
        this.number = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("area_code")
    public String getAreaCode ( ) { 
        return this.areaCode;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("area_code")
    public void setAreaCode (String value) { 
        this.areaCode = value;
    }
 
}
 