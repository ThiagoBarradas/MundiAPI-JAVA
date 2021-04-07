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
import com.mundipagg.api.models.CreateInvoiceRequest;
import com.mundipagg.api.models.GetInvoiceResponse;
import com.mundipagg.api.models.ListInvoicesResponse;
import com.mundipagg.api.models.UpdateInvoiceStatusRequest;
import com.mundipagg.api.models.UpdateMetadataRequest;

import org.joda.time.DateTime;

public class InvoicesController extends BaseController {
    
    private Configuration Configuration; 

    public InvoicesController(Configuration configuration) {
        this.Configuration = configuration;
    }
    
    /**
     * Cancels an invoice
     * @param    invoiceId    Required parameter: Invoice id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetInvoiceResponse response from the API call 
     */
    public GetInvoiceResponse cancelInvoice(
                final String invoiceId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCancelInvoiceRequest(invoiceId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCancelInvoiceResponse(_context);
    }

    /**
     * Cancels an invoice
     * @param    invoiceId    Required parameter: Invoice id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void cancelInvoiceAsync(
                final String invoiceId,
                final String idempotencyKey,
                final APICallBack<GetInvoiceResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCancelInvoiceRequest(invoiceId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetInvoiceResponse returnValue = _handleCancelInvoiceResponse(_context);
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
     * Builds the HttpRequest object for cancelInvoice
     */
    private HttpRequest _buildCancelInvoiceRequest(
                final String invoiceId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/invoices/{invoice_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("invoice_id", invoiceId);
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
     * Processes the response for cancelInvoice
     * @return An object of type GetInvoiceResponse
     */
    private GetInvoiceResponse _handleCancelInvoiceResponse(HttpContext _context)
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
        GetInvoiceResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetInvoiceResponse>(){});

        return _result;
    }

    /**
     * Gets an invoice
     * @param    invoiceId    Required parameter: Invoice Id
     * @return    Returns the GetInvoiceResponse response from the API call 
     */
    public GetInvoiceResponse getInvoice(
                final String invoiceId
    ) throws Throwable {

        HttpRequest _request = _buildGetInvoiceRequest(invoiceId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetInvoiceResponse(_context);
    }

    /**
     * Gets an invoice
     * @param    invoiceId    Required parameter: Invoice Id
     */
    public void getInvoiceAsync(
                final String invoiceId,
                final APICallBack<GetInvoiceResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetInvoiceRequest(invoiceId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetInvoiceResponse returnValue = _handleGetInvoiceResponse(_context);
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
     * Builds the HttpRequest object for getInvoice
     */
    private HttpRequest _buildGetInvoiceRequest(
                final String invoiceId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/invoices/{invoice_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("invoice_id", invoiceId);
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
     * Processes the response for getInvoice
     * @return An object of type GetInvoiceResponse
     */
    private GetInvoiceResponse _handleGetInvoiceResponse(HttpContext _context)
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
        GetInvoiceResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetInvoiceResponse>(){});

        return _result;
    }

    /**
     * Create an Invoice
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    cycleId    Required parameter: Cycle Id
     * @param    request    Optional parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetInvoiceResponse response from the API call 
     */
    public GetInvoiceResponse createInvoice(
                final String subscriptionId,
                final String cycleId,
                final CreateInvoiceRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateInvoiceRequest(subscriptionId, cycleId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateInvoiceResponse(_context);
    }

    /**
     * Create an Invoice
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    cycleId    Required parameter: Cycle Id
     * @param    request    Optional parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createInvoiceAsync(
                final String subscriptionId,
                final String cycleId,
                final CreateInvoiceRequest request,
                final String idempotencyKey,
                final APICallBack<GetInvoiceResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateInvoiceRequest(subscriptionId, cycleId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetInvoiceResponse returnValue = _handleCreateInvoiceResponse(_context);
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
     * Builds the HttpRequest object for createInvoice
     */
    private HttpRequest _buildCreateInvoiceRequest(
                final String subscriptionId,
                final String cycleId,
                final CreateInvoiceRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/cycles/{cycle_id}/pay");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("cycle_id", cycleId);
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
     * Processes the response for createInvoice
     * @return An object of type GetInvoiceResponse
     */
    private GetInvoiceResponse _handleCreateInvoiceResponse(HttpContext _context)
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
        GetInvoiceResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetInvoiceResponse>(){});

        return _result;
    }

    /**
     * Updates the status from an invoice
     * @param    invoiceId    Required parameter: Invoice Id
     * @param    request    Required parameter: Request for updating an invoice's status
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetInvoiceResponse response from the API call 
     */
    public GetInvoiceResponse updateInvoiceStatus(
                final String invoiceId,
                final UpdateInvoiceStatusRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateInvoiceStatusRequest(invoiceId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateInvoiceStatusResponse(_context);
    }

    /**
     * Updates the status from an invoice
     * @param    invoiceId    Required parameter: Invoice Id
     * @param    request    Required parameter: Request for updating an invoice's status
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateInvoiceStatusAsync(
                final String invoiceId,
                final UpdateInvoiceStatusRequest request,
                final String idempotencyKey,
                final APICallBack<GetInvoiceResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateInvoiceStatusRequest(invoiceId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetInvoiceResponse returnValue = _handleUpdateInvoiceStatusResponse(_context);
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
     * Builds the HttpRequest object for updateInvoiceStatus
     */
    private HttpRequest _buildUpdateInvoiceStatusRequest(
                final String invoiceId,
                final UpdateInvoiceStatusRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/invoices/{invoice_id}/status");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("invoice_id", invoiceId);
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
     * Processes the response for updateInvoiceStatus
     * @return An object of type GetInvoiceResponse
     */
    private GetInvoiceResponse _handleUpdateInvoiceStatusResponse(HttpContext _context)
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
        GetInvoiceResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetInvoiceResponse>(){});

        return _result;
    }

    /**
     * Gets all invoices
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for Invoice's code
     * @param    customerId    Optional parameter: Filter for Invoice's customer id
     * @param    subscriptionId    Optional parameter: Filter for Invoice's subscription id
     * @param    createdSince    Optional parameter: Filter for Invoice's creation date start range
     * @param    createdUntil    Optional parameter: Filter for Invoices creation date end range
     * @param    status    Optional parameter: Filter for Invoice's status
     * @param    dueSince    Optional parameter: Filter for Invoice's due date start range
     * @param    dueUntil    Optional parameter: Filter for Invoice's due date end range
     * @return    Returns the ListInvoicesResponse response from the API call 
     */
    public ListInvoicesResponse getInvoices(
                final Integer page,
                final Integer size,
                final String code,
                final String customerId,
                final String subscriptionId,
                final DateTime createdSince,
                final DateTime createdUntil,
                final String status,
                final DateTime dueSince,
                final DateTime dueUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetInvoicesRequest(page, size, code, customerId, subscriptionId, createdSince, createdUntil, status, dueSince, dueUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetInvoicesResponse(_context);
    }

    /**
     * Gets all invoices
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for Invoice's code
     * @param    customerId    Optional parameter: Filter for Invoice's customer id
     * @param    subscriptionId    Optional parameter: Filter for Invoice's subscription id
     * @param    createdSince    Optional parameter: Filter for Invoice's creation date start range
     * @param    createdUntil    Optional parameter: Filter for Invoices creation date end range
     * @param    status    Optional parameter: Filter for Invoice's status
     * @param    dueSince    Optional parameter: Filter for Invoice's due date start range
     * @param    dueUntil    Optional parameter: Filter for Invoice's due date end range
     */
    public void getInvoicesAsync(
                final Integer page,
                final Integer size,
                final String code,
                final String customerId,
                final String subscriptionId,
                final DateTime createdSince,
                final DateTime createdUntil,
                final String status,
                final DateTime dueSince,
                final DateTime dueUntil,
                final APICallBack<ListInvoicesResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetInvoicesRequest(page, size, code, customerId, subscriptionId, createdSince, createdUntil, status, dueSince, dueUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListInvoicesResponse returnValue = _handleGetInvoicesResponse(_context);
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
     * Builds the HttpRequest object for getInvoices
     */
    private HttpRequest _buildGetInvoicesRequest(
                final Integer page,
                final Integer size,
                final String code,
                final String customerId,
                final String subscriptionId,
                final DateTime createdSince,
                final DateTime createdUntil,
                final String status,
                final DateTime dueSince,
                final DateTime dueUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/invoices");

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
        if (customerId != null) {
            _queryParameters.put("customer_id", customerId);
        }
        if (subscriptionId != null) {
            _queryParameters.put("subscription_id", subscriptionId);
        }
        if (createdSince != null) {
            _queryParameters.put("created_since", DateTimeHelper.toRfc8601DateTime(createdSince));
        }
        if (createdUntil != null) {
            _queryParameters.put("created_until", DateTimeHelper.toRfc8601DateTime(createdUntil));
        }
        if (status != null) {
            _queryParameters.put("status", status);
        }
        if (dueSince != null) {
            _queryParameters.put("due_since", DateTimeHelper.toRfc8601DateTime(dueSince));
        }
        if (dueUntil != null) {
            _queryParameters.put("due_until", DateTimeHelper.toRfc8601DateTime(dueUntil));
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
     * Processes the response for getInvoices
     * @return An object of type ListInvoicesResponse
     */
    private ListInvoicesResponse _handleGetInvoicesResponse(HttpContext _context)
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
        ListInvoicesResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListInvoicesResponse>(){});

        return _result;
    }

    /**
     * Updates the metadata from an invoice
     * @param    invoiceId    Required parameter: The invoice id
     * @param    request    Required parameter: Request for updating the invoice metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetInvoiceResponse response from the API call 
     */
    public GetInvoiceResponse updateInvoiceMetadata(
                final String invoiceId,
                final UpdateMetadataRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateInvoiceMetadataRequest(invoiceId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateInvoiceMetadataResponse(_context);
    }

    /**
     * Updates the metadata from an invoice
     * @param    invoiceId    Required parameter: The invoice id
     * @param    request    Required parameter: Request for updating the invoice metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateInvoiceMetadataAsync(
                final String invoiceId,
                final UpdateMetadataRequest request,
                final String idempotencyKey,
                final APICallBack<GetInvoiceResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateInvoiceMetadataRequest(invoiceId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetInvoiceResponse returnValue = _handleUpdateInvoiceMetadataResponse(_context);
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
     * Builds the HttpRequest object for updateInvoiceMetadata
     */
    private HttpRequest _buildUpdateInvoiceMetadataRequest(
                final String invoiceId,
                final UpdateMetadataRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/invoices/{invoice_id}/metadata");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("invoice_id", invoiceId);
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
     * Processes the response for updateInvoiceMetadata
     * @return An object of type GetInvoiceResponse
     */
    private GetInvoiceResponse _handleUpdateInvoiceMetadataResponse(HttpContext _context)
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
        GetInvoiceResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetInvoiceResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Id
     * @return    Returns the GetInvoiceResponse response from the API call 
     */
    public GetInvoiceResponse getPartialInvoice(
                final String subscriptionId
    ) throws Throwable {

        HttpRequest _request = _buildGetPartialInvoiceRequest(subscriptionId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetPartialInvoiceResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Id
     */
    public void getPartialInvoiceAsync(
                final String subscriptionId,
                final APICallBack<GetInvoiceResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetPartialInvoiceRequest(subscriptionId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetInvoiceResponse returnValue = _handleGetPartialInvoiceResponse(_context);
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
     * Builds the HttpRequest object for getPartialInvoice
     */
    private HttpRequest _buildGetPartialInvoiceRequest(
                final String subscriptionId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/partial-invoice");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for getPartialInvoice
     * @return An object of type GetInvoiceResponse
     */
    private GetInvoiceResponse _handleGetPartialInvoiceResponse(HttpContext _context)
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
        GetInvoiceResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetInvoiceResponse>(){});

        return _result;
    }

}
