/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreateUsageRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5118180291582530365L;
    private int quantity;
    private String description;
    private Date usedAt;
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("quantity")
    public int getQuantity ( ) { 
        return this.quantity;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("quantity")
    public void setQuantity (int value) { 
        this.quantity = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("description")
    public String getDescription ( ) { 
        return this.description;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("description")
    public void setDescription (String value) { 
        this.description = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("used_at")
    public Date getUsedAt ( ) { 
        return this.usedAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("used_at")
    public void setUsedAt (Date value) { 
        this.usedAt = value;
    }
 
}
 