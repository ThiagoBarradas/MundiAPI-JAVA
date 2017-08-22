/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ListAddressesResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 4940792574111932533L;
    private List<GetAddressResponse> data;
    private PagingResponse paging;
    /** GETTER
     * The address objects
     */
    @JsonGetter("data")
    public List<GetAddressResponse> getData ( ) { 
        return this.data;
    }
    
    /** SETTER
     * The address objects
     */
    @JsonSetter("data")
    public void setData (List<GetAddressResponse> value) { 
        this.data = value;
    }
 
    /** GETTER
     * Paging object
     */
    @JsonGetter("paging")
    public PagingResponse getPaging ( ) { 
        return this.paging;
    }
    
    /** SETTER
     * Paging object
     */
    @JsonSetter("paging")
    public void setPaging (PagingResponse value) { 
        this.paging = value;
    }
 
}
 