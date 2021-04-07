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
import com.mundipagg.api.models.CreateAccessTokenRequest;
import com.mundipagg.api.models.CreateAddressRequest;
import com.mundipagg.api.models.CreateCardRequest;
import com.mundipagg.api.models.CreateCustomerRequest;
import com.mundipagg.api.models.GetAccessTokenResponse;
import com.mundipagg.api.models.GetAddressResponse;
import com.mundipagg.api.models.GetCardResponse;
import com.mundipagg.api.models.GetCustomerResponse;
import com.mundipagg.api.models.ListAccessTokensResponse;
import com.mundipagg.api.models.ListAddressesResponse;
import com.mundipagg.api.models.ListCardsResponse;
import com.mundipagg.api.models.ListCustomersResponse;
import com.mundipagg.api.models.UpdateAddressRequest;
import com.mundipagg.api.models.UpdateCardRequest;
import com.mundipagg.api.models.UpdateCustomerRequest;
import com.mundipagg.api.models.UpdateMetadataRequest;

public class CustomersController extends BaseController {

    private Configuration Configuration; 

    public CustomersController(Configuration configuration) {
        this.Configuration = configuration;
    }
    
    /**
     * Updates a card
     * @param    customerId    Required parameter: Customer Id
     * @param    cardId    Required parameter: Card id
     * @param    request    Required parameter: Request for updating a card
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetCardResponse response from the API call 
     */
    public GetCardResponse updateCard(
                final String customerId,
                final String cardId,
                final UpdateCardRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateCardRequest(customerId, cardId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateCardResponse(_context);
    }

    /**
     * Updates a card
     * @param    customerId    Required parameter: Customer Id
     * @param    cardId    Required parameter: Card id
     * @param    request    Required parameter: Request for updating a card
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateCardAsync(
                final String customerId,
                final String cardId,
                final UpdateCardRequest request,
                final String idempotencyKey,
                final APICallBack<GetCardResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateCardRequest(customerId, cardId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCardResponse returnValue = _handleUpdateCardResponse(_context);
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
     * Builds the HttpRequest object for updateCard
     */
    private HttpRequest _buildUpdateCardRequest(
                final String customerId,
                final String cardId,
                final UpdateCardRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/cards/{card_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("card_id", cardId);
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
        HttpRequest _request = getClientInstance().putBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for updateCard
     * @return An object of type GetCardResponse
     */
    private GetCardResponse _handleUpdateCardResponse(HttpContext _context)
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
        GetCardResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCardResponse>(){});

        return _result;
    }

    /**
     * Updates an address
     * @param    customerId    Required parameter: Customer Id
     * @param    addressId    Required parameter: Address Id
     * @param    request    Required parameter: Request for updating an address
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetAddressResponse response from the API call 
     */
    public GetAddressResponse updateAddress(
                final String customerId,
                final String addressId,
                final UpdateAddressRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateAddressRequest(customerId, addressId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateAddressResponse(_context);
    }

    /**
     * Updates an address
     * @param    customerId    Required parameter: Customer Id
     * @param    addressId    Required parameter: Address Id
     * @param    request    Required parameter: Request for updating an address
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateAddressAsync(
                final String customerId,
                final String addressId,
                final UpdateAddressRequest request,
                final String idempotencyKey,
                final APICallBack<GetAddressResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateAddressRequest(customerId, addressId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAddressResponse returnValue = _handleUpdateAddressResponse(_context);
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
     * Builds the HttpRequest object for updateAddress
     */
    private HttpRequest _buildUpdateAddressRequest(
                final String customerId,
                final String addressId,
                final UpdateAddressRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/addresses/{address_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("address_id", addressId);
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
        HttpRequest _request = getClientInstance().putBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for updateAddress
     * @return An object of type GetAddressResponse
     */
    private GetAddressResponse _handleUpdateAddressResponse(HttpContext _context)
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
        GetAddressResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAddressResponse>(){});

        return _result;
    }

    /**
     * Get a customer
     * @param    customerId    Required parameter: Customer Id
     * @return    Returns the GetCustomerResponse response from the API call 
     */
    public GetCustomerResponse getCustomer(
                final String customerId
    ) throws Throwable {

        HttpRequest _request = _buildGetCustomerRequest(customerId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetCustomerResponse(_context);
    }

    /**
     * Get a customer
     * @param    customerId    Required parameter: Customer Id
     */
    public void getCustomerAsync(
                final String customerId,
                final APICallBack<GetCustomerResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetCustomerRequest(customerId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCustomerResponse returnValue = _handleGetCustomerResponse(_context);
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
     * Builds the HttpRequest object for getCustomer
     */
    private HttpRequest _buildGetCustomerRequest(
                final String customerId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
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
     * Processes the response for getCustomer
     * @return An object of type GetCustomerResponse
     */
    private GetCustomerResponse _handleGetCustomerResponse(HttpContext _context)
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
        GetCustomerResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCustomerResponse>(){});

        return _result;
    }

    /**
     * Get all access tokens from a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @return    Returns the ListAccessTokensResponse response from the API call 
     */
    public ListAccessTokensResponse getAccessTokens(
                final String customerId,
                final Integer page,
                final Integer size
    ) throws Throwable {

        HttpRequest _request = _buildGetAccessTokensRequest(customerId, page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetAccessTokensResponse(_context);
    }

    /**
     * Get all access tokens from a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     */
    public void getAccessTokensAsync(
                final String customerId,
                final Integer page,
                final Integer size,
                final APICallBack<ListAccessTokensResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetAccessTokensRequest(customerId, page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListAccessTokensResponse returnValue = _handleGetAccessTokensResponse(_context);
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
     * Builds the HttpRequest object for getAccessTokens
     */
    private HttpRequest _buildGetAccessTokensRequest(
                final String customerId,
                final Integer page,
                final Integer size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/access-tokens");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        APIHelper.appendUrlWithQueryParameters(_queryBuilder, _queryParameters);
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
     * Processes the response for getAccessTokens
     * @return An object of type ListAccessTokensResponse
     */
    private ListAccessTokensResponse _handleGetAccessTokensResponse(HttpContext _context)
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
        ListAccessTokensResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListAccessTokensResponse>(){});

        return _result;
    }

    /**
     * Gets all adressess from a customer
     * @param    customerId    Required parameter: Customer id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @return    Returns the ListAddressesResponse response from the API call 
     */
    public ListAddressesResponse getAddresses(
                final String customerId,
                final Integer page,
                final Integer size
    ) throws Throwable {

        HttpRequest _request = _buildGetAddressesRequest(customerId, page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetAddressesResponse(_context);
    }

    /**
     * Gets all adressess from a customer
     * @param    customerId    Required parameter: Customer id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     */
    public void getAddressesAsync(
                final String customerId,
                final Integer page,
                final Integer size,
                final APICallBack<ListAddressesResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetAddressesRequest(customerId, page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListAddressesResponse returnValue = _handleGetAddressesResponse(_context);
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
     * Builds the HttpRequest object for getAddresses
     */
    private HttpRequest _buildGetAddressesRequest(
                final String customerId,
                final Integer page,
                final Integer size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/addresses");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        APIHelper.appendUrlWithQueryParameters(_queryBuilder, _queryParameters);
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
     * Processes the response for getAddresses
     * @return An object of type ListAddressesResponse
     */
    private ListAddressesResponse _handleGetAddressesResponse(HttpContext _context)
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
        ListAddressesResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListAddressesResponse>(){});

        return _result;
    }

    /**
     * Get all cards from a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @return    Returns the ListCardsResponse response from the API call 
     */
    public ListCardsResponse getCards(
                final String customerId,
                final Integer page,
                final Integer size
    ) throws Throwable {

        HttpRequest _request = _buildGetCardsRequest(customerId, page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetCardsResponse(_context);
    }

    /**
     * Get all cards from a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     */
    public void getCardsAsync(
                final String customerId,
                final Integer page,
                final Integer size,
                final APICallBack<ListCardsResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetCardsRequest(customerId, page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListCardsResponse returnValue = _handleGetCardsResponse(_context);
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
     * Builds the HttpRequest object for getCards
     */
    private HttpRequest _buildGetCardsRequest(
                final String customerId,
                final Integer page,
                final Integer size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/cards");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        APIHelper.appendUrlWithQueryParameters(_queryBuilder, _queryParameters);
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
     * Processes the response for getCards
     * @return An object of type ListCardsResponse
     */
    private ListCardsResponse _handleGetCardsResponse(HttpContext _context)
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
        ListCardsResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListCardsResponse>(){});

        return _result;
    }

    /**
     * Delete a Customer's access tokens
     * @param    customerId    Required parameter: Customer Id
     * @return    Returns the ListAccessTokensResponse response from the API call 
     */
    public ListAccessTokensResponse deleteAccessTokens(
                final String customerId
    ) throws Throwable {

        HttpRequest _request = _buildDeleteAccessTokensRequest(customerId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteAccessTokensResponse(_context);
    }

    /**
     * Delete a Customer's access tokens
     * @param    customerId    Required parameter: Customer Id
     */
    public void deleteAccessTokensAsync(
                final String customerId,
                final APICallBack<ListAccessTokensResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteAccessTokensRequest(customerId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListAccessTokensResponse returnValue = _handleDeleteAccessTokensResponse(_context);
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
     * Builds the HttpRequest object for deleteAccessTokens
     */
    private HttpRequest _buildDeleteAccessTokensRequest(
                final String customerId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/access-tokens/");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
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
     * Processes the response for deleteAccessTokens
     * @return An object of type ListAccessTokensResponse
     */
    private ListAccessTokensResponse _handleDeleteAccessTokensResponse(HttpContext _context)
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
        ListAccessTokensResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListAccessTokensResponse>(){});

        return _result;
    }

    /**
     * Get a Customer's access token
     * @param    customerId    Required parameter: Customer Id
     * @param    tokenId    Required parameter: Token Id
     * @return    Returns the GetAccessTokenResponse response from the API call 
     */
    public GetAccessTokenResponse getAccessToken(
                final String customerId,
                final String tokenId
    ) throws Throwable {

        HttpRequest _request = _buildGetAccessTokenRequest(customerId, tokenId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetAccessTokenResponse(_context);
    }

    /**
     * Get a Customer's access token
     * @param    customerId    Required parameter: Customer Id
     * @param    tokenId    Required parameter: Token Id
     */
    public void getAccessTokenAsync(
                final String customerId,
                final String tokenId,
                final APICallBack<GetAccessTokenResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetAccessTokenRequest(customerId, tokenId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAccessTokenResponse returnValue = _handleGetAccessTokenResponse(_context);
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
     * Builds the HttpRequest object for getAccessToken
     */
    private HttpRequest _buildGetAccessTokenRequest(
                final String customerId,
                final String tokenId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/access-tokens/{token_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("token_id", tokenId);
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
     * Processes the response for getAccessToken
     * @return An object of type GetAccessTokenResponse
     */
    private GetAccessTokenResponse _handleGetAccessTokenResponse(HttpContext _context)
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
        GetAccessTokenResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAccessTokenResponse>(){});

        return _result;
    }

    /**
     * Creates a access token for a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    request    Required parameter: Request for creating a access token
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetAccessTokenResponse response from the API call 
     */
    public GetAccessTokenResponse createAccessToken(
                final String customerId,
                final CreateAccessTokenRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateAccessTokenRequest(customerId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateAccessTokenResponse(_context);
    }

    /**
     * Creates a access token for a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    request    Required parameter: Request for creating a access token
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createAccessTokenAsync(
                final String customerId,
                final CreateAccessTokenRequest request,
                final String idempotencyKey,
                final APICallBack<GetAccessTokenResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateAccessTokenRequest(customerId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAccessTokenResponse returnValue = _handleCreateAccessTokenResponse(_context);
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
     * Builds the HttpRequest object for createAccessToken
     */
    private HttpRequest _buildCreateAccessTokenRequest(
                final String customerId,
                final CreateAccessTokenRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/access-tokens");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
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
        HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for createAccessToken
     * @return An object of type GetAccessTokenResponse
     */
    private GetAccessTokenResponse _handleCreateAccessTokenResponse(HttpContext _context)
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
        GetAccessTokenResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAccessTokenResponse>(){});

        return _result;
    }

    /**
     * Delete a customer's access token
     * @param    customerId    Required parameter: Customer Id
     * @param    tokenId    Required parameter: Token Id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetAccessTokenResponse response from the API call 
     */
    public GetAccessTokenResponse deleteAccessToken(
                final String customerId,
                final String tokenId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteAccessTokenRequest(customerId, tokenId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteAccessTokenResponse(_context);
    }

    /**
     * Delete a customer's access token
     * @param    customerId    Required parameter: Customer Id
     * @param    tokenId    Required parameter: Token Id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteAccessTokenAsync(
                final String customerId,
                final String tokenId,
                final String idempotencyKey,
                final APICallBack<GetAccessTokenResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteAccessTokenRequest(customerId, tokenId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAccessTokenResponse returnValue = _handleDeleteAccessTokenResponse(_context);
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
     * Builds the HttpRequest object for deleteAccessToken
     */
    private HttpRequest _buildDeleteAccessTokenRequest(
                final String customerId,
                final String tokenId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/access-tokens/{token_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("token_id", tokenId);
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


        //prepare and invoke the API call request to fetch the response
        HttpRequest _request = getClientInstance().delete(_queryUrl, _headers, null,
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for deleteAccessToken
     * @return An object of type GetAccessTokenResponse
     */
    private GetAccessTokenResponse _handleDeleteAccessTokenResponse(HttpContext _context)
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
        GetAccessTokenResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAccessTokenResponse>(){});

        return _result;
    }

    /**
     * Updates the metadata a customer
     * @param    customerId    Required parameter: The customer id
     * @param    request    Required parameter: Request for updating the customer metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetCustomerResponse response from the API call 
     */
    public GetCustomerResponse updateCustomerMetadata(
                final String customerId,
                final UpdateMetadataRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateCustomerMetadataRequest(customerId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateCustomerMetadataResponse(_context);
    }

    /**
     * Updates the metadata a customer
     * @param    customerId    Required parameter: The customer id
     * @param    request    Required parameter: Request for updating the customer metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateCustomerMetadataAsync(
                final String customerId,
                final UpdateMetadataRequest request,
                final String idempotencyKey,
                final APICallBack<GetCustomerResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateCustomerMetadataRequest(customerId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCustomerResponse returnValue = _handleUpdateCustomerMetadataResponse(_context);
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
     * Builds the HttpRequest object for updateCustomerMetadata
     */
    private HttpRequest _buildUpdateCustomerMetadataRequest(
                final String customerId,
                final UpdateMetadataRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/Customers/{customer_id}/metadata");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
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
        HttpRequest _request = getClientInstance().patchBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for updateCustomerMetadata
     * @return An object of type GetCustomerResponse
     */
    private GetCustomerResponse _handleUpdateCustomerMetadataResponse(HttpContext _context)
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
        GetCustomerResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCustomerResponse>(){});

        return _result;
    }

    /**
     * Updates a customer
     * @param    customerId    Required parameter: Customer id
     * @param    request    Required parameter: Request for updating a customer
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetCustomerResponse response from the API call 
     */
    public GetCustomerResponse updateCustomer(
                final String customerId,
                final UpdateCustomerRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateCustomerRequest(customerId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateCustomerResponse(_context);
    }

    /**
     * Updates a customer
     * @param    customerId    Required parameter: Customer id
     * @param    request    Required parameter: Request for updating a customer
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateCustomerAsync(
                final String customerId,
                final UpdateCustomerRequest request,
                final String idempotencyKey,
                final APICallBack<GetCustomerResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateCustomerRequest(customerId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCustomerResponse returnValue = _handleUpdateCustomerResponse(_context);
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
     * Builds the HttpRequest object for updateCustomer
     */
    private HttpRequest _buildUpdateCustomerRequest(
                final String customerId,
                final UpdateCustomerRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
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
        HttpRequest _request = getClientInstance().putBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for updateCustomer
     * @return An object of type GetCustomerResponse
     */
    private GetCustomerResponse _handleUpdateCustomerResponse(HttpContext _context)
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
        GetCustomerResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCustomerResponse>(){});

        return _result;
    }

    /**
     * Get a customer's address
     * @param    customerId    Required parameter: Customer id
     * @param    addressId    Required parameter: Address Id
     * @return    Returns the GetAddressResponse response from the API call 
     */
    public GetAddressResponse getAddress(
                final String customerId,
                final String addressId
    ) throws Throwable {

        HttpRequest _request = _buildGetAddressRequest(customerId, addressId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetAddressResponse(_context);
    }

    /**
     * Get a customer's address
     * @param    customerId    Required parameter: Customer id
     * @param    addressId    Required parameter: Address Id
     */
    public void getAddressAsync(
                final String customerId,
                final String addressId,
                final APICallBack<GetAddressResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetAddressRequest(customerId, addressId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAddressResponse returnValue = _handleGetAddressResponse(_context);
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
     * Builds the HttpRequest object for getAddress
     */
    private HttpRequest _buildGetAddressRequest(
                final String customerId,
                final String addressId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/addresses/{address_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("address_id", addressId);
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
     * Processes the response for getAddress
     * @return An object of type GetAddressResponse
     */
    private GetAddressResponse _handleGetAddressResponse(HttpContext _context)
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
        GetAddressResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAddressResponse>(){});

        return _result;
    }

    /**
     * Delete a Customer's address
     * @param    customerId    Required parameter: Customer Id
     * @param    addressId    Required parameter: Address Id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetAddressResponse response from the API call 
     */
    public GetAddressResponse deleteAddress(
                final String customerId,
                final String addressId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteAddressRequest(customerId, addressId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteAddressResponse(_context);
    }

    /**
     * Delete a Customer's address
     * @param    customerId    Required parameter: Customer Id
     * @param    addressId    Required parameter: Address Id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteAddressAsync(
                final String customerId,
                final String addressId,
                final String idempotencyKey,
                final APICallBack<GetAddressResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteAddressRequest(customerId, addressId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAddressResponse returnValue = _handleDeleteAddressResponse(_context);
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
     * Builds the HttpRequest object for deleteAddress
     */
    private HttpRequest _buildDeleteAddressRequest(
                final String customerId,
                final String addressId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/addresses/{address_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("address_id", addressId);
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


        //prepare and invoke the API call request to fetch the response
        HttpRequest _request = getClientInstance().delete(_queryUrl, _headers, null,
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for deleteAddress
     * @return An object of type GetAddressResponse
     */
    private GetAddressResponse _handleDeleteAddressResponse(HttpContext _context)
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
        GetAddressResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAddressResponse>(){});

        return _result;
    }

    /**
     * Delete a customer's card
     * @param    customerId    Required parameter: Customer Id
     * @param    cardId    Required parameter: Card Id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetCardResponse response from the API call 
     */
    public GetCardResponse deleteCard(
                final String customerId,
                final String cardId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteCardRequest(customerId, cardId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteCardResponse(_context);
    }

    /**
     * Delete a customer's card
     * @param    customerId    Required parameter: Customer Id
     * @param    cardId    Required parameter: Card Id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteCardAsync(
                final String customerId,
                final String cardId,
                final String idempotencyKey,
                final APICallBack<GetCardResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteCardRequest(customerId, cardId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCardResponse returnValue = _handleDeleteCardResponse(_context);
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
     * Builds the HttpRequest object for deleteCard
     */
    private HttpRequest _buildDeleteCardRequest(
                final String customerId,
                final String cardId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/cards/{card_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("card_id", cardId);
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


        //prepare and invoke the API call request to fetch the response
        HttpRequest _request = getClientInstance().delete(_queryUrl, _headers, null,
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for deleteCard
     * @return An object of type GetCardResponse
     */
    private GetCardResponse _handleDeleteCardResponse(HttpContext _context)
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
        GetCardResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCardResponse>(){});

        return _result;
    }

    /**
     * Creates a new address for a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    request    Required parameter: Request for creating an address
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetAddressResponse response from the API call 
     */
    public GetAddressResponse createAddress(
                final String customerId,
                final CreateAddressRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateAddressRequest(customerId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateAddressResponse(_context);
    }

    /**
     * Creates a new address for a customer
     * @param    customerId    Required parameter: Customer Id
     * @param    request    Required parameter: Request for creating an address
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createAddressAsync(
                final String customerId,
                final CreateAddressRequest request,
                final String idempotencyKey,
                final APICallBack<GetAddressResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateAddressRequest(customerId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAddressResponse returnValue = _handleCreateAddressResponse(_context);
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
     * Builds the HttpRequest object for createAddress
     */
    private HttpRequest _buildCreateAddressRequest(
                final String customerId,
                final CreateAddressRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/addresses");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
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
        HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for createAddress
     * @return An object of type GetAddressResponse
     */
    private GetAddressResponse _handleCreateAddressResponse(HttpContext _context)
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
        GetAddressResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAddressResponse>(){});

        return _result;
    }

    /**
     * Get a customer's card
     * @param    customerId    Required parameter: Customer id
     * @param    cardId    Required parameter: Card id
     * @return    Returns the GetCardResponse response from the API call 
     */
    public GetCardResponse getCard(
                final String customerId,
                final String cardId
    ) throws Throwable {

        HttpRequest _request = _buildGetCardRequest(customerId, cardId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetCardResponse(_context);
    }

    /**
     * Get a customer's card
     * @param    customerId    Required parameter: Customer id
     * @param    cardId    Required parameter: Card id
     */
    public void getCardAsync(
                final String customerId,
                final String cardId,
                final APICallBack<GetCardResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetCardRequest(customerId, cardId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCardResponse returnValue = _handleGetCardResponse(_context);
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
     * Builds the HttpRequest object for getCard
     */
    private HttpRequest _buildGetCardRequest(
                final String customerId,
                final String cardId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/cards/{card_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("card_id", cardId);
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
     * Processes the response for getCard
     * @return An object of type GetCardResponse
     */
    private GetCardResponse _handleGetCardResponse(HttpContext _context)
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
        GetCardResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCardResponse>(){});

        return _result;
    }

    /**
     * Creates a new card for a customer
     * @param    customerId    Required parameter: Customer id
     * @param    request    Required parameter: Request for creating a card
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetCardResponse response from the API call 
     */
    public GetCardResponse createCard(
                final String customerId,
                final CreateCardRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateCardRequest(customerId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateCardResponse(_context);
    }

    /**
     * Creates a new card for a customer
     * @param    customerId    Required parameter: Customer id
     * @param    request    Required parameter: Request for creating a card
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createCardAsync(
                final String customerId,
                final CreateCardRequest request,
                final String idempotencyKey,
                final APICallBack<GetCardResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateCardRequest(customerId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCardResponse returnValue = _handleCreateCardResponse(_context);
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
     * Builds the HttpRequest object for createCard
     */
    private HttpRequest _buildCreateCardRequest(
                final String customerId,
                final CreateCardRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/cards");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
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
        HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for createCard
     * @return An object of type GetCardResponse
     */
    private GetCardResponse _handleCreateCardResponse(HttpContext _context)
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
        GetCardResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCardResponse>(){});

        return _result;
    }

    /**
     * Get all Customers
     * @param    name    Optional parameter: Name of the Customer
     * @param    document    Optional parameter: Document of the Customer
     * @param    page    Optional parameter: Current page the the search
     * @param    size    Optional parameter: Quantity pages of the search
     * @param    email    Optional parameter: Customer's email
     * @param    code    Optional parameter: Customer's code
     * @return    Returns the ListCustomersResponse response from the API call 
     */
    public ListCustomersResponse getCustomers(
                final String name,
                final String document,
                final Integer page,
                final Integer size,
                final String email,
                final String code
    ) throws Throwable {

        HttpRequest _request = _buildGetCustomersRequest(name, document, page, size, email, code);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetCustomersResponse(_context);
    }

    /**
     * Get all Customers
     * @param    name    Optional parameter: Name of the Customer
     * @param    document    Optional parameter: Document of the Customer
     * @param    page    Optional parameter: Current page the the search
     * @param    size    Optional parameter: Quantity pages of the search
     * @param    email    Optional parameter: Customer's email
     * @param    code    Optional parameter: Customer's code
     */
    public void getCustomersAsync(
                final String name,
                final String document,
                final Integer page,
                final Integer size,
                final String email,
                final String code,
                final APICallBack<ListCustomersResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetCustomersRequest(name, document, page, size, email, code);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListCustomersResponse returnValue = _handleGetCustomersResponse(_context);
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
     * Builds the HttpRequest object for getCustomers
     */
    private HttpRequest _buildGetCustomersRequest(
                final String name,
                final String document,
                final Integer page,
                final Integer size,
                final String email,
                final String code) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers");

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (name != null) {
            _queryParameters.put("name", name);
        }
        if (document != null) {
            _queryParameters.put("document", document);
        }
        if (page != null) {
            _queryParameters.put("page", (page != null) ? page : 1);
        }
        if (size != null) {
            _queryParameters.put("size", (size != null) ? size : 10);
        }
        if (email != null) {
            _queryParameters.put("email", email);
        }
        if (code != null) {
            _queryParameters.put("Code", code);
        }
        APIHelper.appendUrlWithQueryParameters(_queryBuilder, _queryParameters);
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
     * Processes the response for getCustomers
     * @return An object of type ListCustomersResponse
     */
    private ListCustomersResponse _handleGetCustomersResponse(HttpContext _context)
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
        ListCustomersResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListCustomersResponse>(){});

        return _result;
    }

    /**
     * Renew a card
     * @param    customerId    Required parameter: Customer id
     * @param    cardId    Required parameter: Card Id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetCardResponse response from the API call 
     */
    public GetCardResponse renewCard(
                final String customerId,
                final String cardId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildRenewCardRequest(customerId, cardId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleRenewCardResponse(_context);
    }

    /**
     * Renew a card
     * @param    customerId    Required parameter: Customer id
     * @param    cardId    Required parameter: Card Id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void renewCardAsync(
                final String customerId,
                final String cardId,
                final String idempotencyKey,
                final APICallBack<GetCardResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildRenewCardRequest(customerId, cardId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCardResponse returnValue = _handleRenewCardResponse(_context);
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
     * Builds the HttpRequest object for renewCard
     */
    private HttpRequest _buildRenewCardRequest(
                final String customerId,
                final String cardId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers/{customer_id}/cards/{card_id}/renew");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("customer_id", customerId);
        _templateParameters.put("card_id", cardId);
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


        //prepare and invoke the API call request to fetch the response
        HttpRequest _request = getClientInstance().post(_queryUrl, _headers, null,
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for renewCard
     * @return An object of type GetCardResponse
     */
    private GetCardResponse _handleRenewCardResponse(HttpContext _context)
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
        GetCardResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCardResponse>(){});

        return _result;
    }

    /**
     * Creates a new customer
     * @param    request    Required parameter: Request for creating a customer
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetCustomerResponse response from the API call 
     */
    public GetCustomerResponse createCustomer(
                final CreateCustomerRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateCustomerRequest(request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateCustomerResponse(_context);
    }

    /**
     * Creates a new customer
     * @param    request    Required parameter: Request for creating a customer
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createCustomerAsync(
                final CreateCustomerRequest request,
                final String idempotencyKey,
                final APICallBack<GetCustomerResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateCustomerRequest(request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetCustomerResponse returnValue = _handleCreateCustomerResponse(_context);
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
     * Builds the HttpRequest object for createCustomer
     */
    private HttpRequest _buildCreateCustomerRequest(
                final CreateCustomerRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/customers");
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
        HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for createCustomer
     * @return An object of type GetCustomerResponse
     */
    private GetCustomerResponse _handleCreateCustomerResponse(HttpContext _context)
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
        GetCustomerResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetCustomerResponse>(){});

        return _result;
    }

}
