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
public class GetPhonesResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5211325186766394320L;
    private GetPhoneResponse homePhone;
    private GetPhoneResponse mobilePhone;
    /** GETTER
     * 
     */
    @JsonGetter("home_phone")
    public GetPhoneResponse getHomePhone ( ) { 
        return this.homePhone;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("home_phone")
    public void setHomePhone (GetPhoneResponse value) { 
        this.homePhone = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("mobile_phone")
    public GetPhoneResponse getMobilePhone ( ) { 
        return this.mobilePhone;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("mobile_phone")
    public void setMobilePhone (GetPhoneResponse value) { 
        this.mobilePhone = value;
    }
 
}
