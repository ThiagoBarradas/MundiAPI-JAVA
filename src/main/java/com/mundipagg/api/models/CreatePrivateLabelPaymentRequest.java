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
public class CreatePrivateLabelPaymentRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 1980068059191129928L;
    private Integer installments = 1;
    private String statementDescriptor;
    private CreateCardRequest card;
    private String cardId;
    private String cardToken;
    private Boolean recurrence;
    private Boolean capture = true;
    private Boolean extendedLimitEnabled;
    private String extendedLimitCode;
    /** GETTER
     * Number of installments
     */
    @JsonGetter("installments")
    public Integer getInstallments ( ) { 
        return this.installments;
    }
    
    /** SETTER
     * Number of installments
     */
    @JsonSetter("installments")
    public void setInstallments (Integer value) { 
        this.installments = value;
    }
 
    /** GETTER
     * The text that will be shown on the private label's statement
     */
    @JsonGetter("statement_descriptor")
    public String getStatementDescriptor ( ) { 
        return this.statementDescriptor;
    }
    
    /** SETTER
     * The text that will be shown on the private label's statement
     */
    @JsonSetter("statement_descriptor")
    public void setStatementDescriptor (String value) { 
        this.statementDescriptor = value;
    }
 
    /** GETTER
     * Card data
     */
    @JsonGetter("card")
    public CreateCardRequest getCard ( ) { 
        return this.card;
    }
    
    /** SETTER
     * Card data
     */
    @JsonSetter("card")
    public void setCard (CreateCardRequest value) { 
        this.card = value;
    }
 
    /** GETTER
     * The Card id
     */
    @JsonGetter("card_id")
    public String getCardId ( ) { 
        return this.cardId;
    }
    
    /** SETTER
     * The Card id
     */
    @JsonSetter("card_id")
    public void setCardId (String value) { 
        this.cardId = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("card_token")
    public String getCardToken ( ) { 
        return this.cardToken;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("card_token")
    public void setCardToken (String value) { 
        this.cardToken = value;
    }
 
    /** GETTER
     * Indicates a recurrence
     */
    @JsonGetter("recurrence")
    public Boolean getRecurrence ( ) { 
        return this.recurrence;
    }
    
    /** SETTER
     * Indicates a recurrence
     */
    @JsonSetter("recurrence")
    public void setRecurrence (Boolean value) { 
        this.recurrence = value;
    }
 
    /** GETTER
     * Indicates if the operation should be only authorization or auth and capture.
     */
    @JsonGetter("capture")
    public Boolean getCapture ( ) { 
        return this.capture;
    }
    
    /** SETTER
     * Indicates if the operation should be only authorization or auth and capture.
     */
    @JsonSetter("capture")
    public void setCapture (Boolean value) { 
        this.capture = value;
    }
 
    /** GETTER
     * Indicates whether the extended label (private label) is enabled
     */
    @JsonGetter("extended_limit_enabled")
    public Boolean getExtendedLimitEnabled ( ) { 
        return this.extendedLimitEnabled;
    }
    
    /** SETTER
     * Indicates whether the extended label (private label) is enabled
     */
    @JsonSetter("extended_limit_enabled")
    public void setExtendedLimitEnabled (Boolean value) { 
        this.extendedLimitEnabled = value;
    }
 
    /** GETTER
     * Extended Limit Code
     */
    @JsonGetter("extended_limit_code")
    public String getExtendedLimitCode ( ) { 
        return this.extendedLimitCode;
    }
    
    /** SETTER
     * Extended Limit Code
     */
    @JsonSetter("extended_limit_code")
    public void setExtendedLimitCode (String value) { 
        this.extendedLimitCode = value;
    }
 
}
