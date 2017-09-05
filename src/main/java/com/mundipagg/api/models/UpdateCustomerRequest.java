/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class UpdateCustomerRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5689758990936388062L;
    private String name;
    private String email;
    private String document;
    private String personType;
    private CreateAddressRequest address;
    private LinkedHashMap<String, String> metadata;
    private CreatePhonesRequest phones;
    /** GETTER
     * Name
     */
    @JsonGetter("name")
    public String getName ( ) { 
        return this.name;
    }
    
    /** SETTER
     * Name
     */
    @JsonSetter("name")
    public void setName (String value) { 
        this.name = value;
    }
 
    /** GETTER
     * Email
     */
    @JsonGetter("email")
    public String getEmail ( ) { 
        return this.email;
    }
    
    /** SETTER
     * Email
     */
    @JsonSetter("email")
    public void setEmail (String value) { 
        this.email = value;
    }
 
    /** GETTER
     * Document number
     */
    @JsonGetter("document")
    public String getDocument ( ) { 
        return this.document;
    }
    
    /** SETTER
     * Document number
     */
    @JsonSetter("document")
    public void setDocument (String value) { 
        this.document = value;
    }
 
    /** GETTER
     * Person type
     */
    @JsonGetter("person_type")
    public String getPersonType ( ) { 
        return this.personType;
    }
    
    /** SETTER
     * Person type
     */
    @JsonSetter("person_type")
    public void setPersonType (String value) { 
        this.personType = value;
    }
 
    /** GETTER
     * Address
     */
    @JsonGetter("address")
    public CreateAddressRequest getAddress ( ) { 
        return this.address;
    }
    
    /** SETTER
     * Address
     */
    @JsonSetter("address")
    public void setAddress (CreateAddressRequest value) { 
        this.address = value;
    }
 
    /** GETTER
     * Metadata
     */
    @JsonGetter("metadata")
    public LinkedHashMap<String, String> getMetadata ( ) { 
        return this.metadata;
    }
    
    /** SETTER
     * Metadata
     */
    @JsonSetter("metadata")
    public void setMetadata (LinkedHashMap<String, String> value) { 
        this.metadata = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("phones")
    public CreatePhonesRequest getPhones ( ) { 
        return this.phones;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("phones")
    public void setPhones (CreatePhonesRequest value) { 
        this.phones = value;
    }
 
}
 