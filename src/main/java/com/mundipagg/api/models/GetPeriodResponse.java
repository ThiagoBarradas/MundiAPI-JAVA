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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mundipagg.api.DateTimeHelper;

import org.joda.time.DateTime;

@JsonInclude(Include.ALWAYS)
public class GetPeriodResponse 
        implements java.io.Serializable {
    private static final long serialVersionUID = 4963097508378842617L;
    private DateTime startAt;
    private DateTime endAt;
    private String id;
    private DateTime billingAt;
    private GetSubscriptionResponse subscription;
    private String status;
    private int duration;
    private String createdAt;
    private String updatedAt;
    private int cycle;
    /** GETTER
     * 
     */
    @JsonGetter("start_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getStartAt ( ) { 
        return this.startAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("start_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setStartAt (DateTime value) { 
        this.startAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("end_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getEndAt ( ) { 
        return this.endAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("end_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setEndAt (DateTime value) { 
        this.endAt = value;
    }
 
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
    @JsonGetter("billing_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getBillingAt ( ) { 
        return this.billingAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("billing_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setBillingAt (DateTime value) { 
        this.billingAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("subscription")
    public GetSubscriptionResponse getSubscription ( ) { 
        return this.subscription;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("subscription")
    public void setSubscription (GetSubscriptionResponse value) { 
        this.subscription = value;
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
    @JsonGetter("duration")
    public int getDuration ( ) { 
        return this.duration;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("duration")
    public void setDuration (int value) { 
        this.duration = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("created_at")
    public String getCreatedAt ( ) { 
        return this.createdAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("created_at")
    public void setCreatedAt (String value) { 
        this.createdAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("updated_at")
    public String getUpdatedAt ( ) { 
        return this.updatedAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("updated_at")
    public void setUpdatedAt (String value) { 
        this.updatedAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("cycle")
    public int getCycle ( ) { 
        return this.cycle;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("cycle")
    public void setCycle (int value) { 
        this.cycle = value;
    }
 
}
