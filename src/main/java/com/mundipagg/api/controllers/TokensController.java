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
import com.mundipagg.api.models.CreateTokenRequest;
import com.mundipagg.api.models.GetTokenResponse;

public class TokensController extends BaseController {
   
    private Configuration Configuration; 

    public TokensController(Configuration configuration) {
        this.Configuration = configuration;
    }
    
    /**
     * Gets a token from its id
     * @param    id    Required parameter: Token id
     * @param    publicKey    Required parameter: Public key
     * @return    Returns the GetTokenResponse response from the API call 
     */
    public GetTokenResponse getToken(
                final String id,
                final String publicKey
    ) throws Throwable {

        HttpRequest _request = _buildGetTokenRequest(id, publicKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetTokenResponse(_context);
    }

    /**
     * Gets a token from its id
     * @param    id    Required parameter: Token id
     * @param    publicKey    Required parameter: Public key
     */
    public void getTokenAsync(
                final String id,
                final String publicKey,
                final APICallBack<GetTokenResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetTokenRequest(id, publicKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetTokenResponse returnValue = _handleGetTokenResponse(_context);
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
     * Builds the HttpRequest object for getToken
     */
    private HttpRequest _buildGetTokenRequest(
                final String id,
                final String publicKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/tokens/{id}?appId={public_key}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("id", id);
        _templateParameters.put("public_key", publicKey);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>();
        _headers.put("user-agent", BaseController.userAgent);
        _headers.put("accept", "application/json");


        //prepare and invoke the API call request to fetch the response
        HttpRequest _request = getClientInstance().get(_queryUrl, _headers, null);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for getToken
     * @return An object of type GetTokenResponse
     */
    private GetTokenResponse _handleGetTokenResponse(HttpContext _context)
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
        GetTokenResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetTokenResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    publicKey    Required parameter: Public key
     * @param    request    Required parameter: Request for creating a token
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetTokenResponse response from the API call 
     */
    public GetTokenResponse createToken(
                final String publicKey,
                final CreateTokenRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateTokenRequest(publicKey, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateTokenResponse(_context);
    }

    /**
     * 
     * @param    publicKey    Required parameter: Public key
     * @param    request    Required parameter: Request for creating a token
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createTokenAsync(
                final String publicKey,
                final CreateTokenRequest request,
                final String idempotencyKey,
                final APICallBack<GetTokenResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateTokenRequest(publicKey, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetTokenResponse returnValue = _handleCreateTokenResponse(_context);
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
     * Builds the HttpRequest object for createToken
     */
    private HttpRequest _buildCreateTokenRequest(
                final String publicKey,
                final CreateTokenRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/tokens?appId={public_key}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("public_key", publicKey);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>();
        if (idempotencyKey != null) {
            _headers.put("idempotency-key", idempotencyKey);
        }
        _headers.put("user-agent", BaseController.userAgent);
        _headers.put("accept", "application/json");
        _headers.put("content-type", "application/json");


        //prepare and invoke the API call request to fetch the response
        HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(request));

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for createToken
     * @return An object of type GetTokenResponse
     */
    private GetTokenResponse _handleCreateTokenResponse(HttpContext _context)
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
        GetTokenResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetTokenResponse>(){});

        return _result;
    }

}
