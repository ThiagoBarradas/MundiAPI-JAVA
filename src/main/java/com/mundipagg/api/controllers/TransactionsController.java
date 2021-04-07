package com.mundipagg.api.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mundipagg.api.APIHelper;
import com.mundipagg.api.Configuration;
import com.mundipagg.api.exceptions.APIException;
import com.mundipagg.api.http.client.APICallBack;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.http.request.HttpRequest;
import com.mundipagg.api.http.response.HttpResponse;
import com.mundipagg.api.http.response.HttpStringResponse;
import com.mundipagg.api.models.GetTransactionResponse;

public class TransactionsController extends BaseController {
    
    private Configuration Configuration; 

    public TransactionsController(Configuration configuration) {
        this.Configuration = configuration;
    }

    /**
     * 
     * @param    transactionId    Required parameter: Example: 
     * @return    Returns the GetTransactionResponse response from the API call 
     */
    public GetTransactionResponse getTransaction(
                final String transactionId
    ) throws Throwable {

        HttpRequest _request = _buildGetTransactionRequest(transactionId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetTransactionResponse(_context);
    }

    /**
     * 
     * @param    transactionId    Required parameter: Example: 
     */
    public void getTransactionAsync(
                final String transactionId,
                final APICallBack<GetTransactionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetTransactionRequest(transactionId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetTransactionResponse returnValue = _handleGetTransactionResponse(_context);
                            callBack.onSuccess(_context, returnValue);
                        } catch (Exception e) {
                            callBack.onFailure(_context, e);
                        }
                    }

                    public void onFailure(HttpContext _context, Throwable _exception) {
                        // Let the caller know of the failure
                        callBack.onFailure(_context, _exception);
                    }
                });
            }
        };

        // Execute async using thread pool
        APIHelper.getScheduler().execute(_responseTask);
    }

    /**
     * Builds the HttpRequest object for getTransaction
     */
    private HttpRequest _buildGetTransactionRequest(
                final String transactionId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/transactions/{transaction_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("transaction_id", transactionId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>();
        _headers.put("user-agent", BaseController.userAgent);
        _headers.put("accept", "application/json");


        //prepare and invoke the API call request to fetch the response
        HttpRequest _request = getClientInstance().get(_queryUrl, _headers, null,
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for getTransaction
     * @return An object of type GetTransactionResponse
     */
    private GetTransactionResponse _handleGetTransactionResponse(HttpContext _context)
            throws APIException, IOException {
        HttpResponse _response = _context.getResponse();

        //invoke the callback after response if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnAfterResponse(_context);
        }

        //handle errors defined at the API level
        validateResponse(_response, _context);

        //extract result from the http response
        String _responseBody = ((HttpStringResponse)_response).getBody();
        GetTransactionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetTransactionResponse>(){});

        return _result;
    }

}
