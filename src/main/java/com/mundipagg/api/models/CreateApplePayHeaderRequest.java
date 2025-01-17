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
public class CreateApplePayHeaderRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 4320631120401577871L;
    private String publicKeyHash;
    private String ephemeralPublicKey;
    private String transactionId;
    /** GETTER
     * SHA–256 hash, Base64 string codified
     */
    @JsonGetter("public_key_hash")
    public String getPublicKeyHash ( ) { 
        return this.publicKeyHash;
    }
    
    /** SETTER
     * SHA–256 hash, Base64 string codified
     */
    @JsonSetter("public_key_hash")
    public void setPublicKeyHash (String value) { 
        this.publicKeyHash = value;
    }
 
    /** GETTER
     * X.509 encoded key bytes, Base64 encoded as a string
     */
    @JsonGetter("ephemeral_public_key")
    public String getEphemeralPublicKey ( ) { 
        return this.ephemeralPublicKey;
    }
    
    /** SETTER
     * X.509 encoded key bytes, Base64 encoded as a string
     */
    @JsonSetter("ephemeral_public_key")
    public void setEphemeralPublicKey (String value) { 
        this.ephemeralPublicKey = value;
    }
 
    /** GETTER
     * Transaction identifier, generated on Device
     */
    @JsonGetter("transaction_id")
    public String getTransactionId ( ) { 
        return this.transactionId;
    }
    
    /** SETTER
     * Transaction identifier, generated on Device
     */
    @JsonSetter("transaction_id")
    public void setTransactionId (String value) { 
        this.transactionId = value;
    }
 
}
