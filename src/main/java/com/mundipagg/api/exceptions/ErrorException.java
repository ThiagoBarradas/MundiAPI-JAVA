package com.mundipagg.api.exceptions;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.mundipagg.api.http.client.HttpContext;

public class ErrorException 
        extends APIException {
    private static final long serialVersionUID = 7330768666678869749L;
    private String message;
    private Object errors;
    private Object request;
    /** GETTER
     * 
     */
    @JsonGetter("message")
    public String getMessage ( ) { 
        return this.message;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("message")
    private void setMessage (String value) { 
        this.message = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("errors")
    public Object getErrors ( ) { 
        return this.errors;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("errors")
    private void setErrors (Object value) { 
        this.errors = value;
    }
 
    /** GETTER
     * 
     */
    @JsonGetter("request")
    public Object getRequest ( ) { 
        return this.request;
    }
    
    /** SETTER
     * 
     */
    @JsonSetter("request")
    private void setRequest (Object value) { 
        this.request = value;
    }
 
    /**
     * Initialization constructor
     * @param   reason  The reason for throwing exception
     * @param   context The http context of the API exception
     */
    public ErrorException(String reason, HttpContext context) {
        super(reason, context);
    }
}
