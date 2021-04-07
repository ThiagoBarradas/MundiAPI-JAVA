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
public class CreateCardPaymentContactlessPOIRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 2379100821590617722L;
    private String systemName;
    private String model;
    private String provider;
    private String serialNumber;
    private String versionNumber;
    /** GETTER
     * system name
     */
    @JsonGetter("system_name")
    public String getSystemName ( ) { 
        return this.systemName;
    }
    
    /** SETTER
     * system name
     */
    @JsonSetter("system_name")
    public void setSystemName (String value) { 
        this.systemName = value;
    }
 
    /** GETTER
     * model
     */
    @JsonGetter("model")
    public String getModel ( ) { 
        return this.model;
    }
    
    /** SETTER
     * model
     */
    @JsonSetter("model")
    public void setModel (String value) { 
        this.model = value;
    }
 
    /** GETTER
     * provider
     */
    @JsonGetter("provider")
    public String getProvider ( ) { 
        return this.provider;
    }
    
    /** SETTER
     * provider
     */
    @JsonSetter("provider")
    public void setProvider (String value) { 
        this.provider = value;
    }
 
    /** GETTER
     * serial number
     */
    @JsonGetter("serial_number")
    public String getSerialNumber ( ) { 
        return this.serialNumber;
    }
    
    /** SETTER
     * serial number
     */
    @JsonSetter("serial_number")
    public void setSerialNumber (String value) { 
        this.serialNumber = value;
    }
 
    /** GETTER
     * version number
     */
    @JsonGetter("version_number")
    public String getVersionNumber ( ) { 
        return this.versionNumber;
    }
    
    /** SETTER
     * version number
     */
    @JsonSetter("version_number")
    public void setVersionNumber (String value) { 
        this.versionNumber = value;
    }
 
}
