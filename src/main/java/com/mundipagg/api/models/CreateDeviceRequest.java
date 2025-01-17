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
public class CreateDeviceRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = -111966291935292874L;
    private String platform;
    /** GETTER
     * Device's platform
     */
    @JsonGetter("platform")
    public String getPlatform ( ) { 
        return this.platform;
    }
    
    /** SETTER
     * Device's platform
     */
    @JsonSetter("platform")
    public void setPlatform (String value) { 
        this.platform = value;
    }
 
}
