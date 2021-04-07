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
public class CreateSubMerchantRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 3393544641583123737L;
    private String paymentFacilitatorCode;
    private String code;
    private String name;
    private String merchantCategoryCode;
    private String document;
    private String type;
    private CreatePhoneRequest phone;
    private CreateAddressRequest address;
    /** GETTER
     * Payment Facilitator Code
     */
    @JsonGetter("payment_facilitator_code")
    public String getPaymentFacilitatorCode ( ) { 
        return this.paymentFacilitatorCode;
    }
    
    /** SETTER
     * Payment Facilitator Code
     */
    @JsonSetter("payment_facilitator_code")
    public void setPaymentFacilitatorCode (String value) { 
        this.paymentFacilitatorCode = value;
    }
 
    /** GETTER
     * Code
     */
    @JsonGetter("code")
    public String getCode ( ) { 
        return this.code;
    }
    
    /** SETTER
     * Code
     */
    @JsonSetter("code")
    public void setCode (String value) { 
        this.code = value;
    }
 
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
     * Merchant Category Code
     */
    @JsonGetter("merchant_category_code")
    public String getMerchantCategoryCode ( ) { 
        return this.merchantCategoryCode;
    }
    
    /** SETTER
     * Merchant Category Code
     */
    @JsonSetter("merchant_category_code")
    public void setMerchantCategoryCode (String value) { 
        this.merchantCategoryCode = value;
    }
 
    /** GETTER
     * Document number. Only numbers, no special characters.
     */
    @JsonGetter("document")
    public String getDocument ( ) { 
        return this.document;
    }
    
    /** SETTER
     * Document number. Only numbers, no special characters.
     */
    @JsonSetter("document")
    public void setDocument (String value) { 
        this.document = value;
    }
 
    /** GETTER
     * Document type. Can be either 'individual' or 'company'
     */
    @JsonGetter("type")
    public String getType ( ) { 
        return this.type;
    }
    
    /** SETTER
     * Document type. Can be either 'individual' or 'company'
     */
    @JsonSetter("type")
    public void setType (String value) { 
        this.type = value;
    }
 
    /** GETTER
     * Phone
     */
    @JsonGetter("phone")
    public CreatePhoneRequest getPhone ( ) { 
        return this.phone;
    }
    
    /** SETTER
     * Phone
     */
    @JsonSetter("phone")
    public void setPhone (CreatePhoneRequest value) { 
        this.phone = value;
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
 
}
