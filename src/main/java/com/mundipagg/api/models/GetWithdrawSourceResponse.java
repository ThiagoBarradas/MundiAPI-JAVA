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
public class GetWithdrawSourceResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = -289533809934252682L;
    private String sourceId;
    private String type;
    /** GETTER
     * 
     */
    @JsonGetter("source_id")
    public String getSourceId ( ) { 
        return this.sourceId;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("source_id")
    public void setSourceId (String value) { 
        this.sourceId = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("type")
    public String getType ( ) { 
        return this.type;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("type")
    public void setType (String value) { 
        this.type = value;
    }
 
}
