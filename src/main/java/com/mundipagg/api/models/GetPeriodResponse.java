/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GetPeriodResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 4890892257837443911L;
    private Date startAt;
    private Date endAt;
    private String id;
    private Date billingAt;
    private GetSubscriptionResponse subscription;
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("start_at")
    public Date getStartAt ( ) { 
        return this.startAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("start_at")
    public void setStartAt (Date value) { 
        this.startAt = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("end_at")
    public Date getEndAt ( ) { 
        return this.endAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("end_at")
    public void setEndAt (Date value) { 
        this.endAt = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("id")
    public String getId ( ) { 
        return this.id;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("id")
    public void setId (String value) { 
        this.id = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("billing_at")
    public Date getBillingAt ( ) { 
        return this.billingAt;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("billing_at")
    public void setBillingAt (Date value) { 
        this.billingAt = value;
    }
 
    /** GETTER
     * TODO: Write general description for this method
     */
    @JsonGetter("subscription")
    public GetSubscriptionResponse getSubscription ( ) { 
        return this.subscription;
    }
    
    /** SETTER
     * TODO: Write general description for this method
     */
    @JsonSetter("subscription")
    public void setSubscription (GetSubscriptionResponse value) { 
        this.subscription = value;
    }
 
}
 