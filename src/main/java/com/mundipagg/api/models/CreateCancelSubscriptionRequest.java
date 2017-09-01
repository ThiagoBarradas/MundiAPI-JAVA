/*
 * MundiAPILib
 *
 * This file was automatically generated by APIMATIC v2.0 ( https://apimatic.io ).
 */
package com.mundipagg.api.models;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CreateCancelSubscriptionRequest 
        implements java.io.Serializable {
    private static final long serialVersionUID = 5494510432422561386L;
    private boolean cancelPendingInvoices = true;
    /** GETTER
     * Indicates if the pending invoices must also be canceled.
     */
    @JsonGetter("cancel_pending_invoices")
    public boolean getCancelPendingInvoices ( ) { 
        return this.cancelPendingInvoices;
    }
    
    /** SETTER
     * Indicates if the pending invoices must also be canceled.
     */
    @JsonSetter("cancel_pending_invoices")
    public void setCancelPendingInvoices (boolean value) { 
        this.cancelPendingInvoices = value;
    }
 
}
 