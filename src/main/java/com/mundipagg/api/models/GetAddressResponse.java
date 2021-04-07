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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mundipagg.api.DateTimeHelper;
import org.joda.time.DateTime;

@JsonInclude(Include.ALWAYS)
public class GetAddressResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = -3319179416415317563L;
    private String id;
    private String street;
    private String number;
    private String complement;
    private String zipCode;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String status;
    private DateTime createdAt;
    private DateTime updatedAt;
    private GetCustomerResponse customer;
    private LinkedHashMap<String, String> metadata;
    private String line1;
    private String line2;
    private DateTime deletedAt;
    /** GETTER
     * 
     */
    @JsonGetter("id")
    public String getId ( ) { 
        return this.id;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("id")
    public void setId (String value) { 
        this.id = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("street")
    public String getStreet ( ) { 
        return this.street;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("street")
    public void setStreet (String value) { 
        this.street = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("number")
    public String getNumber ( ) { 
        return this.number;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("number")
    public void setNumber (String value) { 
        this.number = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("complement")
    public String getComplement ( ) { 
        return this.complement;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("complement")
    public void setComplement (String value) { 
        this.complement = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("zip_code")
    public String getZipCode ( ) { 
        return this.zipCode;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("zip_code")
    public void setZipCode (String value) { 
        this.zipCode = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("neighborhood")
    public String getNeighborhood ( ) { 
        return this.neighborhood;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("neighborhood")
    public void setNeighborhood (String value) { 
        this.neighborhood = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("city")
    public String getCity ( ) { 
        return this.city;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("city")
    public void setCity (String value) { 
        this.city = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("state")
    public String getState ( ) { 
        return this.state;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("state")
    public void setState (String value) { 
        this.state = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("country")
    public String getCountry ( ) { 
        return this.country;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("country")
    public void setCountry (String value) { 
        this.country = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("status")
    public String getStatus ( ) { 
        return this.status;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("status")
    public void setStatus (String value) { 
        this.status = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("created_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getCreatedAt ( ) { 
        return this.createdAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("created_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setCreatedAt (DateTime value) { 
        this.createdAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("updated_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getUpdatedAt ( ) { 
        return this.updatedAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("updated_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setUpdatedAt (DateTime value) { 
        this.updatedAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("customer")
    public GetCustomerResponse getCustomer ( ) { 
        return this.customer;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("customer")
    public void setCustomer (GetCustomerResponse value) { 
        this.customer = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("metadata")
    public LinkedHashMap<String, String> getMetadata ( ) { 
        return this.metadata;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("metadata")
    public void setMetadata (LinkedHashMap<String, String> value) { 
        this.metadata = value;
    }
 
    /** GETTER
     * Line 1 for address
     */
    @JsonGetter("line_1")
    public String getLine1 ( ) { 
        return this.line1;
    }
    
    /** SETTER
     * Line 1 for address
     */
    @JsonSetter("line_1")
    public void setLine1 (String value) { 
        this.line1 = value;
    }
 
    /** GETTER
     * Line 2 for address
     */
    @JsonGetter("line_2")
    public String getLine2 ( ) { 
        return this.line2;
    }
    
    /** SETTER
     * Line 2 for address
     */
    @JsonSetter("line_2")
    public void setLine2 (String value) { 
        this.line2 = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("deleted_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getDeletedAt ( ) { 
        return this.deletedAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("deleted_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setDeletedAt (DateTime value) { 
        this.deletedAt = value;
    }
 
}
