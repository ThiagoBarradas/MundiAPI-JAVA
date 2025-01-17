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
public class GetOrderItemResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 1248361567937746779L;
    private String id;
    private int amount;
    private String description;
    private int quantity;
    private GetSellerResponse getSellerResponse;
    private String category;
    private String code;
    /** GETTER
     * Id
     */
    @JsonGetter("id")
    public String getId ( ) { 
        return this.id;
    }
    
    /** SETTER
     * Id
     */
    @JsonSetter("id")
    public void setId (String value) { 
        this.id = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("amount")
    public int getAmount ( ) { 
        return this.amount;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("amount")
    public void setAmount (int value) { 
        this.amount = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("description")
    public String getDescription ( ) { 
        return this.description;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("description")
    public void setDescription (String value) { 
        this.description = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("quantity")
    public int getQuantity ( ) { 
        return this.quantity;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("quantity")
    public void setQuantity (int value) { 
        this.quantity = value;
    }
 
    /** GETTER
     * Seller data
     */
    @JsonGetter("GetSellerResponse")
    public GetSellerResponse getGetSellerResponse ( ) { 
        return this.getSellerResponse;
    }
    
    /** SETTER
     * Seller data
     */
    @JsonSetter("GetSellerResponse")
    public void setGetSellerResponse (GetSellerResponse value) { 
        this.getSellerResponse = value;
    }
 
    /** GETTER
     * Category
     */
    @JsonGetter("category")
    public String getCategory ( ) { 
        return this.category;
    }
    
    /** SETTER
     * Category
     */
    @JsonSetter("category")
    public void setCategory (String value) { 
        this.category = value;
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
 
}
