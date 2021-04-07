package com.mundipagg.api.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mundipagg.api.APIHelper;
import com.mundipagg.api.Configuration;
import com.mundipagg.api.DateTimeHelper;
import com.mundipagg.api.exceptions.APIException;
import com.mundipagg.api.http.client.APICallBack;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.http.request.HttpRequest;
import com.mundipagg.api.http.response.HttpResponse;
import com.mundipagg.api.http.response.HttpStringResponse;
import com.mundipagg.api.models.CreateCancelChargeRequest;
import com.mundipagg.api.models.CreateCaptureChargeRequest;
import com.mundipagg.api.models.CreateChargeRequest;
import com.mundipagg.api.models.CreateConfirmPaymentRequest;
import com.mundipagg.api.models.GetChargeResponse;
import com.mundipagg.api.models.GetChargesSummaryResponse;
import com.mundipagg.api.models.ListChargeTransactionsResponse;
import com.mundipagg.api.models.ListChargesResponse;
import com.mundipagg.api.models.UpdateChargeCardRequest;
import com.mundipagg.api.models.UpdateChargeDueDateRequest;
import com.mundipagg.api.models.UpdateChargePaymentMethodRequest;
import com.mundipagg.api.models.UpdateMetadataRequest;

import org.joda.time.DateTime;

public class ChargesController extends BaseController {
   
    private Configuration Configuration; 

    public ChargesController(Configuration configuration) {
        this.Configuration = configuration;
    }

    /**
     * Updates the card from a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Required parameter: Request for updating a charge's card
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse updateChargeCard(
                final String chargeId,
                final UpdateChargeCardRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateChargeCardRequest(chargeId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateChargeCardResponse(_context);
    }

    /**
     * Updates the card from a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Required parameter: Request for updating a charge's card
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateChargeCardAsync(
                final String chargeId,
                final UpdateChargeCardRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateChargeCardRequest(chargeId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleUpdateChargeCardResponse(_context);
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
     * Builds the HttpRequest object for updateChargeCard
     */
    private HttpRequest _buildUpdateChargeCardRequest(
                final String chargeId,
                final UpdateChargeCardRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}/card");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for updateChargeCard
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleUpdateChargeCardResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Updates a charge's payment method
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Required parameter: Request for updating the payment method from a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse updateChargePaymentMethod(
                final String chargeId,
                final UpdateChargePaymentMethodRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateChargePaymentMethodRequest(chargeId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateChargePaymentMethodResponse(_context);
    }

    /**
     * Updates a charge's payment method
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Required parameter: Request for updating the payment method from a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateChargePaymentMethodAsync(
                final String chargeId,
                final UpdateChargePaymentMethodRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateChargePaymentMethodRequest(chargeId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleUpdateChargePaymentMethodResponse(_context);
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
     * Builds the HttpRequest object for updateChargePaymentMethod
     */
    private HttpRequest _buildUpdateChargePaymentMethodRequest(
                final String chargeId,
                final UpdateChargePaymentMethodRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}/payment-method");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for updateChargePaymentMethod
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleUpdateChargePaymentMethodResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Creates a new charge
     * @param    request    Required parameter: Request for creating a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse createCharge(
                final CreateChargeRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateChargeRequest(request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateChargeResponse(_context);
    }

    /**
     * Creates a new charge
     * @param    request    Required parameter: Request for creating a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createChargeAsync(
                final CreateChargeRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateChargeRequest(request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleCreateChargeResponse(_context);
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
     * Builds the HttpRequest object for createCharge
     */
    private HttpRequest _buildCreateChargeRequest(
                final CreateChargeRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/Charges");
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
     * Processes the response for createCharge
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleCreateChargeResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Get a charge from its id
     * @param    chargeId    Required parameter: Charge id
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse getCharge(
                final String chargeId
    ) throws Throwable {

        HttpRequest _request = _buildGetChargeRequest(chargeId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetChargeResponse(_context);
    }

    /**
     * Get a charge from its id
     * @param    chargeId    Required parameter: Charge id
     */
    public void getChargeAsync(
                final String chargeId,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetChargeRequest(chargeId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleGetChargeResponse(_context);
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
     * Builds the HttpRequest object for getCharge
     */
    private HttpRequest _buildGetChargeRequest(
                final String chargeId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for getCharge
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleGetChargeResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Retries a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse retryCharge(
                final String chargeId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildRetryChargeRequest(chargeId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleRetryChargeResponse(_context);
    }

    /**
     * Retries a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void retryChargeAsync(
                final String chargeId,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildRetryChargeRequest(chargeId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleRetryChargeResponse(_context);
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
     * Builds the HttpRequest object for retryCharge
     */
    private HttpRequest _buildRetryChargeRequest(
                final String chargeId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}/retry");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for retryCharge
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleRetryChargeResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Lists all charges
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for charge's code
     * @param    status    Optional parameter: Filter for charge's status
     * @param    paymentMethod    Optional parameter: Filter for charge's payment method
     * @param    customerId    Optional parameter: Filter for charge's customer id
     * @param    orderId    Optional parameter: Filter for charge's order id
     * @param    createdSince    Optional parameter: Filter for the beginning of the range for charge's creation
     * @param    createdUntil    Optional parameter: Filter for the end of the range for charge's creation
     * @return    Returns the ListChargesResponse response from the API call 
     */
    public ListChargesResponse getCharges(
                final Integer page,
                final Integer size,
                final String code,
                final String status,
                final String paymentMethod,
                final String customerId,
                final String orderId,
                final DateTime createdSince,
                final DateTime createdUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetChargesRequest(page, size, code, status, paymentMethod, customerId, orderId, createdSince, createdUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetChargesResponse(_context);
    }

    /**
     * Lists all charges
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for charge's code
     * @param    status    Optional parameter: Filter for charge's status
     * @param    paymentMethod    Optional parameter: Filter for charge's payment method
     * @param    customerId    Optional parameter: Filter for charge's customer id
     * @param    orderId    Optional parameter: Filter for charge's order id
     * @param    createdSince    Optional parameter: Filter for the beginning of the range for charge's creation
     * @param    createdUntil    Optional parameter: Filter for the end of the range for charge's creation
     */
    public void getChargesAsync(
                final Integer page,
                final Integer size,
                final String code,
                final String status,
                final String paymentMethod,
                final String customerId,
                final String orderId,
                final DateTime createdSince,
                final DateTime createdUntil,
                final APICallBack<ListChargesResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetChargesRequest(page, size, code, status, paymentMethod, customerId, orderId, createdSince, createdUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListChargesResponse returnValue = _handleGetChargesResponse(_context);
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
     * Builds the HttpRequest object for getCharges
     */
    private HttpRequest _buildGetChargesRequest(
                final Integer page,
                final Integer size,
                final String code,
                final String status,
                final String paymentMethod,
                final String customerId,
                final String orderId,
                final DateTime createdSince,
                final DateTime createdUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges");

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        if (code != null) {
            _queryParameters.put("code", code);
        }
        if (status != null) {
            _queryParameters.put("status", status);
        }
        if (paymentMethod != null) {
            _queryParameters.put("payment_method", paymentMethod);
        }
        if (customerId != null) {
            _queryParameters.put("customer_id", customerId);
        }
        if (orderId != null) {
            _queryParameters.put("order_id", orderId);
        }
        if (createdSince != null) {
            _queryParameters.put("created_since", DateTimeHelper.toRfc8601DateTime(createdSince));
        }
        if (createdUntil != null) {
            _queryParameters.put("created_until", DateTimeHelper.toRfc8601DateTime(createdUntil));
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
     * Processes the response for getCharges
     * @return An object of type ListChargesResponse
     */
    private ListChargesResponse _handleGetChargesResponse(HttpContext _context)
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
        ListChargesResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListChargesResponse>(){});

        return _result;
    }

    /**
     * Updates the metadata from a charge
     * @param    chargeId    Required parameter: The charge id
     * @param    request    Required parameter: Request for updating the charge metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse updateChargeMetadata(
                final String chargeId,
                final UpdateMetadataRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateChargeMetadataRequest(chargeId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateChargeMetadataResponse(_context);
    }

    /**
     * Updates the metadata from a charge
     * @param    chargeId    Required parameter: The charge id
     * @param    request    Required parameter: Request for updating the charge metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateChargeMetadataAsync(
                final String chargeId,
                final UpdateMetadataRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateChargeMetadataRequest(chargeId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleUpdateChargeMetadataResponse(_context);
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
     * Builds the HttpRequest object for updateChargeMetadata
     */
    private HttpRequest _buildUpdateChargeMetadataRequest(
                final String chargeId,
                final UpdateMetadataRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/Charges/{charge_id}/metadata");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for updateChargeMetadata
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleUpdateChargeMetadataResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Cancel a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Optional parameter: Request for cancelling a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse cancelCharge(
                final String chargeId,
                final CreateCancelChargeRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCancelChargeRequest(chargeId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCancelChargeResponse(_context);
    }

    /**
     * Cancel a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Optional parameter: Request for cancelling a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void cancelChargeAsync(
                final String chargeId,
                final CreateCancelChargeRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCancelChargeRequest(chargeId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleCancelChargeResponse(_context);
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
     * Builds the HttpRequest object for cancelCharge
     */
    private HttpRequest _buildCancelChargeRequest(
                final String chargeId,
                final CreateCancelChargeRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
        HttpRequest _request = getClientInstance().deleteBody(_queryUrl, _headers, APIHelper.serialize(request),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for cancelCharge
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleCancelChargeResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Captures a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Optional parameter: Request for capturing a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse captureCharge(
                final String chargeId,
                final CreateCaptureChargeRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCaptureChargeRequest(chargeId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCaptureChargeResponse(_context);
    }

    /**
     * Captures a charge
     * @param    chargeId    Required parameter: Charge id
     * @param    request    Optional parameter: Request for capturing a charge
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void captureChargeAsync(
                final String chargeId,
                final CreateCaptureChargeRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCaptureChargeRequest(chargeId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleCaptureChargeResponse(_context);
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
     * Builds the HttpRequest object for captureCharge
     */
    private HttpRequest _buildCaptureChargeRequest(
                final String chargeId,
                final CreateCaptureChargeRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}/capture");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for captureCharge
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleCaptureChargeResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * Updates the due date from a charge
     * @param    chargeId    Required parameter: Charge Id
     * @param    request    Required parameter: Request for updating the due date
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse updateChargeDueDate(
                final String chargeId,
                final UpdateChargeDueDateRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateChargeDueDateRequest(chargeId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateChargeDueDateResponse(_context);
    }

    /**
     * Updates the due date from a charge
     * @param    chargeId    Required parameter: Charge Id
     * @param    request    Required parameter: Request for updating the due date
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateChargeDueDateAsync(
                final String chargeId,
                final UpdateChargeDueDateRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateChargeDueDateRequest(chargeId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleUpdateChargeDueDateResponse(_context);
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
     * Builds the HttpRequest object for updateChargeDueDate
     */
    private HttpRequest _buildUpdateChargeDueDateRequest(
                final String chargeId,
                final UpdateChargeDueDateRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/Charges/{charge_id}/due-date");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for updateChargeDueDate
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleUpdateChargeDueDateResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    chargeId    Required parameter: Example: 
     * @param    request    Optional parameter: Request for confirm payment
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetChargeResponse response from the API call 
     */
    public GetChargeResponse confirmPayment(
                final String chargeId,
                final CreateConfirmPaymentRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildConfirmPaymentRequest(chargeId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleConfirmPaymentResponse(_context);
    }

    /**
     * 
     * @param    chargeId    Required parameter: Example: 
     * @param    request    Optional parameter: Request for confirm payment
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void confirmPaymentAsync(
                final String chargeId,
                final CreateConfirmPaymentRequest request,
                final String idempotencyKey,
                final APICallBack<GetChargeResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildConfirmPaymentRequest(chargeId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargeResponse returnValue = _handleConfirmPaymentResponse(_context);
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
     * Builds the HttpRequest object for confirmPayment
     */
    private HttpRequest _buildConfirmPaymentRequest(
                final String chargeId,
                final CreateConfirmPaymentRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}/confirm-payment");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for confirmPayment
     * @return An object of type GetChargeResponse
     */
    private GetChargeResponse _handleConfirmPaymentResponse(HttpContext _context)
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
        GetChargeResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargeResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    chargeId    Required parameter: Charge Id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @return    Returns the ListChargeTransactionsResponse response from the API call 
     */
    public ListChargeTransactionsResponse getChargeTransactions(
                final String chargeId,
                final Integer page,
                final Integer size
    ) throws Throwable {

        HttpRequest _request = _buildGetChargeTransactionsRequest(chargeId, page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetChargeTransactionsResponse(_context);
    }

    /**
     * 
     * @param    chargeId    Required parameter: Charge Id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     */
    public void getChargeTransactionsAsync(
                final String chargeId,
                final Integer page,
                final Integer size,
                final APICallBack<ListChargeTransactionsResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetChargeTransactionsRequest(chargeId, page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListChargeTransactionsResponse returnValue = _handleGetChargeTransactionsResponse(_context);
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
     * Builds the HttpRequest object for getChargeTransactions
     */
    private HttpRequest _buildGetChargeTransactionsRequest(
                final String chargeId,
                final Integer page,
                final Integer size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/{charge_id}/transactions");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("charge_id", chargeId);
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
     * Processes the response for getChargeTransactions
     * @return An object of type ListChargeTransactionsResponse
     */
    private ListChargeTransactionsResponse _handleGetChargeTransactionsResponse(HttpContext _context)
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
        ListChargeTransactionsResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListChargeTransactionsResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    status    Required parameter: Example: 
     * @param    createdSince    Optional parameter: Example: 
     * @param    createdUntil    Optional parameter: Example: 
     * @return    Returns the GetChargesSummaryResponse response from the API call 
     */
    public GetChargesSummaryResponse getChargesSummary(
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetChargesSummaryRequest(status, createdSince, createdUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetChargesSummaryResponse(_context);
    }

    /**
     * 
     * @param    status    Required parameter: Example: 
     * @param    createdSince    Optional parameter: Example: 
     * @param    createdUntil    Optional parameter: Example: 
     */
    public void getChargesSummaryAsync(
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil,
                final APICallBack<GetChargesSummaryResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetChargesSummaryRequest(status, createdSince, createdUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetChargesSummaryResponse returnValue = _handleGetChargesSummaryResponse(_context);
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
     * Builds the HttpRequest object for getChargesSummary
     */
    private HttpRequest _buildGetChargesSummaryRequest(
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/charges/summary");

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        _queryParameters.put("status", status);
        if (createdSince != null) {
            _queryParameters.put("created_since", DateTimeHelper.toRfc8601DateTime(createdSince));
        }
        if (createdUntil != null) {
            _queryParameters.put("created_until", DateTimeHelper.toRfc8601DateTime(createdUntil));
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
     * Processes the response for getChargesSummary
     * @return An object of type GetChargesSummaryResponse
     */
    private GetChargesSummaryResponse _handleGetChargesSummaryResponse(HttpContext _context)
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
        GetChargesSummaryResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetChargesSummaryResponse>(){});

        return _result;
    }

}
