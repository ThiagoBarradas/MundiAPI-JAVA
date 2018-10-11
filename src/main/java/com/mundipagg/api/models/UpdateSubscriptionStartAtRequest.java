/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mundipagg.api.DateTimeHelper;
import org.joda.time.DateTime;

public class UpdateSubscriptionStartAtRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 77039637047135870L;
    private DateTime startAt;
    /** GETTER
     * The date when the subscription periods will start
     */
    @JsonGetter("start_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getStartAt ( ) { 
        return this.startAt;
    }
    
    /** SETTER
     * The date when the subscription periods will start
     */
    @JsonSetter("start_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setStartAt (DateTime value) { 
        this.startAt = value;
    }
 
}
 