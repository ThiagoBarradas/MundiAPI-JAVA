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
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mundipagg.api.DateTimeHelper;

import org.joda.time.DateTime;

@JsonTypeInfo(
          use = JsonTypeInfo.Id.NAME,
          include = JsonTypeInfo.As.EXISTING_PROPERTY,
          property = "transaction_type",
          defaultImpl = GetBoletoTransactionResponse.class,
          visible = true)
@JsonInclude(Include.ALWAYS)
public class GetBoletoTransactionResponse 
        extends GetTransactionResponse {
    private static final long serialVersionUID = -107344441087808346L;
    private String url;
    private String barcode;
    private String nossoNumero;
    private String bank;
    private String documentNumber;
    private String instructions;
    private GetBillingAddressResponse billingAddress;
    private DateTime dueAt;
    private String qrCode;
    private String line;
    private String pdfPassword;
    private String pdf;
    private DateTime paidAt;
    private String paidAmount;
    private String type;
    private DateTime creditAt;
    /** GETTER
     * 
     */
    @JsonGetter("url")
    public String getUrl ( ) { 
        return this.url;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("url")
    public void setUrl (String value) { 
        this.url = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("barcode")
    public String getBarcode ( ) { 
        return this.barcode;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("barcode")
    public void setBarcode (String value) { 
        this.barcode = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("nosso_numero")
    public String getNossoNumero ( ) { 
        return this.nossoNumero;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("nosso_numero")
    public void setNossoNumero (String value) { 
        this.nossoNumero = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("bank")
    public String getBank ( ) { 
        return this.bank;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("bank")
    public void setBank (String value) { 
        this.bank = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("document_number")
    public String getDocumentNumber ( ) { 
        return this.documentNumber;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("document_number")
    public void setDocumentNumber (String value) { 
        this.documentNumber = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("instructions")
    public String getInstructions ( ) { 
        return this.instructions;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("instructions")
    public void setInstructions (String value) { 
        this.instructions = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("billing_address")
    public GetBillingAddressResponse getBillingAddress ( ) { 
        return this.billingAddress;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("billing_address")
    public void setBillingAddress (GetBillingAddressResponse value) { 
        this.billingAddress = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("due_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getDueAt ( ) { 
        return this.dueAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("due_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setDueAt (DateTime value) { 
        this.dueAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("qr_code")
    public String getQrCode ( ) { 
        return this.qrCode;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("qr_code")
    public void setQrCode (String value) { 
        this.qrCode = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("line")
    public String getLine ( ) { 
        return this.line;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("line")
    public void setLine (String value) { 
        this.line = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("pdf_password")
    public String getPdfPassword ( ) { 
        return this.pdfPassword;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("pdf_password")
    public void setPdfPassword (String value) { 
        this.pdfPassword = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("pdf")
    public String getPdf ( ) { 
        return this.pdf;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("pdf")
    public void setPdf (String value) { 
        this.pdf = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("paid_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getPaidAt ( ) { 
        return this.paidAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("paid_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setPaidAt (DateTime value) { 
        this.paidAt = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("paid_amount")
    public String getPaidAmount ( ) { 
        return this.paidAmount;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("paid_amount")
    public void setPaidAmount (String value) { 
        this.paidAmount = value;
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
 
    /** GETTER
     * 
     */
    @JsonGetter("credit_at")
    @JsonSerialize(using=DateTimeHelper.Rfc8601DateTimeSerializer.class)
    public DateTime getCreditAt ( ) { 
        return this.creditAt;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("credit_at")
    @JsonDeserialize(using=DateTimeHelper.Rfc8601DateTimeDeserializer.class)
    public void setCreditAt (DateTime value) { 
        this.creditAt = value;
    }
 
}
