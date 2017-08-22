/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreateCardTokenRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 4637718096274979745L;
    private String number;
    private String holderName;
    private int expMonth;
    private int expYear;
    private String cvv;
    private String brand;
    /** GETTER
     * Credit card number
     */
    @JsonGetter("number")
    public String getNumber ( ) { 
        return this.number;
    }
    
    /** SETTER
     * Credit card number
     */
    @JsonSetter("number")
    public void setNumber (String value) { 
        this.number = value;
    }
 
    /** GETTER
     * Holder name, as written on the card
     */
    @JsonGetter("holder_name")
    public String getHolderName ( ) { 
        return this.holderName;
    }
    
    /** SETTER
     * Holder name, as written on the card
     */
    @JsonSetter("holder_name")
    public void setHolderName (String value) { 
        this.holderName = value;
    }
 
    /** GETTER
     * The expiration month
     */
    @JsonGetter("exp_month")
    public int getExpMonth ( ) { 
        return this.expMonth;
    }
    
    /** SETTER
     * The expiration month
     */
    @JsonSetter("exp_month")
    public void setExpMonth (int value) { 
        this.expMonth = value;
    }
 
    /** GETTER
     * The expiration year, that can be informed with 2 or 4 digits
     */
    @JsonGetter("exp_year")
    public int getExpYear ( ) { 
        return this.expYear;
    }
    
    /** SETTER
     * The expiration year, that can be informed with 2 or 4 digits
     */
    @JsonSetter("exp_year")
    public void setExpYear (int value) { 
        this.expYear = value;
    }
 
    /** GETTER
     * The card's security code
     */
    @JsonGetter("cvv")
    public String getCvv ( ) { 
        return this.cvv;
    }
    
    /** SETTER
     * The card's security code
     */
    @JsonSetter("cvv")
    public void setCvv (String value) { 
        this.cvv = value;
    }
 
    /** GETTER
     * Card brand
     */
    @JsonGetter("brand")
    public String getBrand ( ) { 
        return this.brand;
    }
    
    /** SETTER
     * Card brand
     */
    @JsonSetter("brand")
    public void setBrand (String value) { 
        this.brand = value;
    }
 
}
 