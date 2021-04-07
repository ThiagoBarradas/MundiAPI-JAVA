package com.mundipagg.api.controllers;


import com.mundipagg.api.exceptions.*;
import com.mundipagg.api.http.client.HttpClient;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.http.client.HttpCallBack;
import com.mundipagg.api.http.client.OkClient;
import com.mundipagg.api.http.response.HttpResponse;

public abstract class BaseController {

    private HttpClient clientInstance = null;
    protected static String userAgent = "MundiSDK - Java 0.16.22 - Multithread";

    protected HttpCallBack httpCallBack = null;
    
    public HttpCallBack getHttpCallBack() {
        return httpCallBack;
    }

    public void setHttpCallBack(HttpCallBack httpCallBack) {
        this.httpCallBack = httpCallBack;
    }

    public HttpClient getClientInstance() {
        if (this.clientInstance == null) {
            this.clientInstance = OkClient.getSharedInstance();
        }

        return this.clientInstance;
    }

    public void setClientInstance(HttpClient client) {
        this.clientInstance = client;
    }

    protected void validateResponse(HttpResponse _response, HttpContext context) 
            throws APIException {

        int responseCode = _response.getStatusCode();
        if (responseCode == 400) {
            throw new MErrorException("Invalid request", context);
        }

        if (responseCode == 401) {
            throw new MErrorException("Invalid API key", context);
        }

        if (responseCode == 404) {
            throw new MErrorException("An informed resource was not found", context);
        }

        if (responseCode == 412) {
            throw new MErrorException("Business validation error", context);
        }

        if (responseCode == 422) {
            throw new MErrorException("Contract validation error", context);
        }

        if (responseCode == 500) {
            throw new MErrorException("Internal server error", context);
        }

        if ((responseCode < 200) || (responseCode > 208)) { //[200,208] = HTTP OK
            throw new APIException("HTTP Response Not OK", context);
        }
    }
}
