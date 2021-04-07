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
import com.mundipagg.api.models.CreateAnticipationRequest;
import com.mundipagg.api.models.CreateRecipientRequest;
import com.mundipagg.api.models.CreateTransferRequest;
import com.mundipagg.api.models.CreateWithdrawRequest;
import com.mundipagg.api.models.GetAnticipationLimitResponse;
import com.mundipagg.api.models.GetAnticipationResponse;
import com.mundipagg.api.models.GetBalanceResponse;
import com.mundipagg.api.models.GetRecipientResponse;
import com.mundipagg.api.models.GetTransferResponse;
import com.mundipagg.api.models.GetWithdrawResponse;
import com.mundipagg.api.models.ListAnticipationResponse;
import com.mundipagg.api.models.ListRecipientResponse;
import com.mundipagg.api.models.ListTransferResponse;
import com.mundipagg.api.models.ListWithdrawals;
import com.mundipagg.api.models.UpdateAutomaticAnticipationSettingsRequest;
import com.mundipagg.api.models.UpdateMetadataRequest;
import com.mundipagg.api.models.UpdateRecipientBankAccountRequest;
import com.mundipagg.api.models.UpdateRecipientRequest;
import com.mundipagg.api.models.UpdateTransferSettingsRequest;

import org.joda.time.DateTime;

public class RecipientsController extends BaseController {

    private Configuration Configuration; 

    public RecipientsController(Configuration configuration) {
        this.Configuration = configuration;
    }
    
    /**
     * Updates recipient metadata
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetRecipientResponse response from the API call 
     */
    public GetRecipientResponse updateRecipientMetadata(
                final String recipientId,
                final UpdateMetadataRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateRecipientMetadataRequest(recipientId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateRecipientMetadataResponse(_context);
    }

    /**
     * Updates recipient metadata
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateRecipientMetadataAsync(
                final String recipientId,
                final UpdateMetadataRequest request,
                final String idempotencyKey,
                final APICallBack<GetRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateRecipientMetadataRequest(recipientId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetRecipientResponse returnValue = _handleUpdateRecipientMetadataResponse(_context);
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
     * Builds the HttpRequest object for updateRecipientMetadata
     */
    private HttpRequest _buildUpdateRecipientMetadataRequest(
                final String recipientId,
                final UpdateMetadataRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/metadata");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for updateRecipientMetadata
     * @return An object of type GetRecipientResponse
     */
    private GetRecipientResponse _handleUpdateRecipientMetadataResponse(HttpContext _context)
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
        GetRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetRecipientResponse>(){});

        return _result;
    }

    /**
     * Gets a transfer
     * @param    recipientId    Required parameter: Recipient id
     * @param    transferId    Required parameter: Transfer id
     * @return    Returns the GetTransferResponse response from the API call 
     */
    public GetTransferResponse getTransfer(
                final String recipientId,
                final String transferId
    ) throws Throwable {

        HttpRequest _request = _buildGetTransferRequest(recipientId, transferId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetTransferResponse(_context);
    }

    /**
     * Gets a transfer
     * @param    recipientId    Required parameter: Recipient id
     * @param    transferId    Required parameter: Transfer id
     */
    public void getTransferAsync(
                final String recipientId,
                final String transferId,
                final APICallBack<GetTransferResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetTransferRequest(recipientId, transferId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetTransferResponse returnValue = _handleGetTransferResponse(_context);
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
     * Builds the HttpRequest object for getTransfer
     */
    private HttpRequest _buildGetTransferRequest(
                final String recipientId,
                final String transferId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/transfers/{transfer_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        _templateParameters.put("transfer_id", transferId);
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
     * Processes the response for getTransfer
     * @return An object of type GetTransferResponse
     */
    private GetTransferResponse _handleGetTransferResponse(HttpContext _context)
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
        GetTransferResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetTransferResponse>(){});

        return _result;
    }

    /**
     * Gets a paginated list of transfers for the recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    status    Optional parameter: Filter for transfer status
     * @param    createdSince    Optional parameter: Filter for start range of transfer creation date
     * @param    createdUntil    Optional parameter: Filter for end range of transfer creation date
     * @return    Returns the ListTransferResponse response from the API call 
     */
    public ListTransferResponse getTransfers(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetTransfersRequest(recipientId, page, size, status, createdSince, createdUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetTransfersResponse(_context);
    }

    /**
     * Gets a paginated list of transfers for the recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    status    Optional parameter: Filter for transfer status
     * @param    createdSince    Optional parameter: Filter for start range of transfer creation date
     * @param    createdUntil    Optional parameter: Filter for end range of transfer creation date
     */
    public void getTransfersAsync(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil,
                final APICallBack<ListTransferResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetTransfersRequest(recipientId, page, size, status, createdSince, createdUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListTransferResponse returnValue = _handleGetTransfersResponse(_context);
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
     * Builds the HttpRequest object for getTransfers
     */
    private HttpRequest _buildGetTransfersRequest(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/transfers");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        if (status != null) {
            _queryParameters.put("status", status);
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
     * Processes the response for getTransfers
     * @return An object of type ListTransferResponse
     */
    private ListTransferResponse _handleGetTransfersResponse(HttpContext _context)
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
        ListTransferResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListTransferResponse>(){});

        return _result;
    }

    /**
     * Creates an anticipation
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Anticipation data
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetAnticipationResponse response from the API call 
     */
    public GetAnticipationResponse createAnticipation(
                final String recipientId,
                final CreateAnticipationRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateAnticipationRequest(recipientId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateAnticipationResponse(_context);
    }

    /**
     * Creates an anticipation
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Anticipation data
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createAnticipationAsync(
                final String recipientId,
                final CreateAnticipationRequest request,
                final String idempotencyKey,
                final APICallBack<GetAnticipationResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateAnticipationRequest(recipientId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAnticipationResponse returnValue = _handleCreateAnticipationResponse(_context);
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
     * Builds the HttpRequest object for createAnticipation
     */
    private HttpRequest _buildCreateAnticipationRequest(
                final String recipientId,
                final CreateAnticipationRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/anticipations");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for createAnticipation
     * @return An object of type GetAnticipationResponse
     */
    private GetAnticipationResponse _handleCreateAnticipationResponse(HttpContext _context)
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
        GetAnticipationResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAnticipationResponse>(){});

        return _result;
    }

    /**
     * Gets an anticipation
     * @param    recipientId    Required parameter: Recipient id
     * @param    anticipationId    Required parameter: Anticipation id
     * @return    Returns the GetAnticipationResponse response from the API call 
     */
    public GetAnticipationResponse getAnticipation(
                final String recipientId,
                final String anticipationId
    ) throws Throwable {

        HttpRequest _request = _buildGetAnticipationRequest(recipientId, anticipationId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetAnticipationResponse(_context);
    }

    /**
     * Gets an anticipation
     * @param    recipientId    Required parameter: Recipient id
     * @param    anticipationId    Required parameter: Anticipation id
     */
    public void getAnticipationAsync(
                final String recipientId,
                final String anticipationId,
                final APICallBack<GetAnticipationResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetAnticipationRequest(recipientId, anticipationId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAnticipationResponse returnValue = _handleGetAnticipationResponse(_context);
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
     * Builds the HttpRequest object for getAnticipation
     */
    private HttpRequest _buildGetAnticipationRequest(
                final String recipientId,
                final String anticipationId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/anticipations/{anticipation_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        _templateParameters.put("anticipation_id", anticipationId);
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
     * Processes the response for getAnticipation
     * @return An object of type GetAnticipationResponse
     */
    private GetAnticipationResponse _handleGetAnticipationResponse(HttpContext _context)
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
        GetAnticipationResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAnticipationResponse>(){});

        return _result;
    }

    /**
     * Gets the anticipation limits for a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    timeframe    Required parameter: Timeframe
     * @param    paymentDate    Required parameter: Anticipation payment date
     * @return    Returns the GetAnticipationLimitResponse response from the API call 
     */
    public GetAnticipationLimitResponse getAnticipationLimits(
                final String recipientId,
                final String timeframe,
                final DateTime paymentDate
    ) throws Throwable {

        HttpRequest _request = _buildGetAnticipationLimitsRequest(recipientId, timeframe, paymentDate);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetAnticipationLimitsResponse(_context);
    }

    /**
     * Gets the anticipation limits for a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    timeframe    Required parameter: Timeframe
     * @param    paymentDate    Required parameter: Anticipation payment date
     */
    public void getAnticipationLimitsAsync(
                final String recipientId,
                final String timeframe,
                final DateTime paymentDate,
                final APICallBack<GetAnticipationLimitResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetAnticipationLimitsRequest(recipientId, timeframe, paymentDate);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetAnticipationLimitResponse returnValue = _handleGetAnticipationLimitsResponse(_context);
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
     * Builds the HttpRequest object for getAnticipationLimits
     */
    private HttpRequest _buildGetAnticipationLimitsRequest(
                final String recipientId,
                final String timeframe,
                final DateTime paymentDate) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/anticipation_limits");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        _queryParameters.put("timeframe", timeframe);
        _queryParameters.put("payment_date", DateTimeHelper.toRfc8601DateTime(paymentDate));
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
     * Processes the response for getAnticipationLimits
     * @return An object of type GetAnticipationLimitResponse
     */
    private GetAnticipationLimitResponse _handleGetAnticipationLimitsResponse(HttpContext _context)
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
        GetAnticipationLimitResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetAnticipationLimitResponse>(){});

        return _result;
    }

    /**
     * Retrieves a paginated list of anticipations from a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    status    Optional parameter: Filter for anticipation status
     * @param    timeframe    Optional parameter: Filter for anticipation timeframe
     * @param    paymentDateSince    Optional parameter: Filter for start range for anticipation payment date
     * @param    paymentDateUntil    Optional parameter: Filter for end range for anticipation payment date
     * @param    createdSince    Optional parameter: Filter for start range for anticipation creation date
     * @param    createdUntil    Optional parameter: Filter for end range for anticipation creation date
     * @return    Returns the ListAnticipationResponse response from the API call 
     */
    public ListAnticipationResponse getAnticipations(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final String timeframe,
                final DateTime paymentDateSince,
                final DateTime paymentDateUntil,
                final DateTime createdSince,
                final DateTime createdUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetAnticipationsRequest(recipientId, page, size, status, timeframe, paymentDateSince, paymentDateUntil, createdSince, createdUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetAnticipationsResponse(_context);
    }

    /**
     * Retrieves a paginated list of anticipations from a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    status    Optional parameter: Filter for anticipation status
     * @param    timeframe    Optional parameter: Filter for anticipation timeframe
     * @param    paymentDateSince    Optional parameter: Filter for start range for anticipation payment date
     * @param    paymentDateUntil    Optional parameter: Filter for end range for anticipation payment date
     * @param    createdSince    Optional parameter: Filter for start range for anticipation creation date
     * @param    createdUntil    Optional parameter: Filter for end range for anticipation creation date
     */
    public void getAnticipationsAsync(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final String timeframe,
                final DateTime paymentDateSince,
                final DateTime paymentDateUntil,
                final DateTime createdSince,
                final DateTime createdUntil,
                final APICallBack<ListAnticipationResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetAnticipationsRequest(recipientId, page, size, status, timeframe, paymentDateSince, paymentDateUntil, createdSince, createdUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListAnticipationResponse returnValue = _handleGetAnticipationsResponse(_context);
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
     * Builds the HttpRequest object for getAnticipations
     */
    private HttpRequest _buildGetAnticipationsRequest(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final String timeframe,
                final DateTime paymentDateSince,
                final DateTime paymentDateUntil,
                final DateTime createdSince,
                final DateTime createdUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/anticipations");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        if (status != null) {
            _queryParameters.put("status", status);
        }
        if (timeframe != null) {
            _queryParameters.put("timeframe", timeframe);
        }
        if (paymentDateSince != null) {
            _queryParameters.put("payment_date_since", DateTimeHelper.toRfc8601DateTime(paymentDateSince));
        }
        if (paymentDateUntil != null) {
            _queryParameters.put("payment_date_until", DateTimeHelper.toRfc8601DateTime(paymentDateUntil));
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
     * Processes the response for getAnticipations
     * @return An object of type ListAnticipationResponse
     */
    private ListAnticipationResponse _handleGetAnticipationsResponse(HttpContext _context)
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
        ListAnticipationResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListAnticipationResponse>(){});

        return _result;
    }

    /**
     * Updates a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Recipient data
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetRecipientResponse response from the API call 
     */
    public GetRecipientResponse updateRecipient(
                final String recipientId,
                final UpdateRecipientRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateRecipientRequest(recipientId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateRecipientResponse(_context);
    }

    /**
     * Updates a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Recipient data
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateRecipientAsync(
                final String recipientId,
                final UpdateRecipientRequest request,
                final String idempotencyKey,
                final APICallBack<GetRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateRecipientRequest(recipientId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetRecipientResponse returnValue = _handleUpdateRecipientResponse(_context);
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
     * Builds the HttpRequest object for updateRecipient
     */
    private HttpRequest _buildUpdateRecipientRequest(
                final String recipientId,
                final UpdateRecipientRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for updateRecipient
     * @return An object of type GetRecipientResponse
     */
    private GetRecipientResponse _handleUpdateRecipientResponse(HttpContext _context)
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
        GetRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetRecipientResponse>(){});

        return _result;
    }

    /**
     * Updates the default bank account from a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Bank account data
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetRecipientResponse response from the API call 
     */
    public GetRecipientResponse updateRecipientDefaultBankAccount(
                final String recipientId,
                final UpdateRecipientBankAccountRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateRecipientDefaultBankAccountRequest(recipientId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateRecipientDefaultBankAccountResponse(_context);
    }

    /**
     * Updates the default bank account from a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Bank account data
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateRecipientDefaultBankAccountAsync(
                final String recipientId,
                final UpdateRecipientBankAccountRequest request,
                final String idempotencyKey,
                final APICallBack<GetRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateRecipientDefaultBankAccountRequest(recipientId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetRecipientResponse returnValue = _handleUpdateRecipientDefaultBankAccountResponse(_context);
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
     * Builds the HttpRequest object for updateRecipientDefaultBankAccount
     */
    private HttpRequest _buildUpdateRecipientDefaultBankAccountRequest(
                final String recipientId,
                final UpdateRecipientBankAccountRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/default-bank-account");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for updateRecipientDefaultBankAccount
     * @return An object of type GetRecipientResponse
     */
    private GetRecipientResponse _handleUpdateRecipientDefaultBankAccountResponse(HttpContext _context)
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
        GetRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetRecipientResponse>(){});

        return _result;
    }

    /**
     * Retrieves recipient information
     * @param    recipientId    Required parameter: Recipiend id
     * @return    Returns the GetRecipientResponse response from the API call 
     */
    public GetRecipientResponse getRecipient(
                final String recipientId
    ) throws Throwable {

        HttpRequest _request = _buildGetRecipientRequest(recipientId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetRecipientResponse(_context);
    }

    /**
     * Retrieves recipient information
     * @param    recipientId    Required parameter: Recipiend id
     */
    public void getRecipientAsync(
                final String recipientId,
                final APICallBack<GetRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetRecipientRequest(recipientId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetRecipientResponse returnValue = _handleGetRecipientResponse(_context);
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
     * Builds the HttpRequest object for getRecipient
     */
    private HttpRequest _buildGetRecipientRequest(
                final String recipientId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for getRecipient
     * @return An object of type GetRecipientResponse
     */
    private GetRecipientResponse _handleGetRecipientResponse(HttpContext _context)
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
        GetRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetRecipientResponse>(){});

        return _result;
    }

    /**
     * Retrieves paginated recipients information
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @return    Returns the ListRecipientResponse response from the API call 
     */
    public ListRecipientResponse getRecipients(
                final Integer page,
                final Integer size
    ) throws Throwable {

        HttpRequest _request = _buildGetRecipientsRequest(page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetRecipientsResponse(_context);
    }

    /**
     * Retrieves paginated recipients information
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     */
    public void getRecipientsAsync(
                final Integer page,
                final Integer size,
                final APICallBack<ListRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetRecipientsRequest(page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListRecipientResponse returnValue = _handleGetRecipientsResponse(_context);
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
     * Builds the HttpRequest object for getRecipients
     */
    private HttpRequest _buildGetRecipientsRequest(
                final Integer page,
                final Integer size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients");

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
     * Processes the response for getRecipients
     * @return An object of type ListRecipientResponse
     */
    private ListRecipientResponse _handleGetRecipientsResponse(HttpContext _context)
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
        ListRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListRecipientResponse>(){});

        return _result;
    }

    /**
     * Get balance information for a recipient
     * @param    recipientId    Required parameter: Recipient id
     * @return    Returns the GetBalanceResponse response from the API call 
     */
    public GetBalanceResponse getBalance(
                final String recipientId
    ) throws Throwable {

        HttpRequest _request = _buildGetBalanceRequest(recipientId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetBalanceResponse(_context);
    }

    /**
     * Get balance information for a recipient
     * @param    recipientId    Required parameter: Recipient id
     */
    public void getBalanceAsync(
                final String recipientId,
                final APICallBack<GetBalanceResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetBalanceRequest(recipientId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetBalanceResponse returnValue = _handleGetBalanceResponse(_context);
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
     * Builds the HttpRequest object for getBalance
     */
    private HttpRequest _buildGetBalanceRequest(
                final String recipientId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/balance");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for getBalance
     * @return An object of type GetBalanceResponse
     */
    private GetBalanceResponse _handleGetBalanceResponse(HttpContext _context)
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
        GetBalanceResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetBalanceResponse>(){});

        return _result;
    }

    /**
     * Creates a transfer for a recipient
     * @param    recipientId    Required parameter: Recipient Id
     * @param    request    Required parameter: Transfer data
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetTransferResponse response from the API call 
     */
    public GetTransferResponse createTransfer(
                final String recipientId,
                final CreateTransferRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateTransferRequest(recipientId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateTransferResponse(_context);
    }

    /**
     * Creates a transfer for a recipient
     * @param    recipientId    Required parameter: Recipient Id
     * @param    request    Required parameter: Transfer data
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createTransferAsync(
                final String recipientId,
                final CreateTransferRequest request,
                final String idempotencyKey,
                final APICallBack<GetTransferResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateTransferRequest(recipientId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetTransferResponse returnValue = _handleCreateTransferResponse(_context);
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
     * Builds the HttpRequest object for createTransfer
     */
    private HttpRequest _buildCreateTransferRequest(
                final String recipientId,
                final CreateTransferRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/transfers");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for createTransfer
     * @return An object of type GetTransferResponse
     */
    private GetTransferResponse _handleCreateTransferResponse(HttpContext _context)
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
        GetTransferResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetTransferResponse>(){});

        return _result;
    }

    /**
     * Creates a new recipient
     * @param    request    Required parameter: Recipient data
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetRecipientResponse response from the API call 
     */
    public GetRecipientResponse createRecipient(
                final CreateRecipientRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateRecipientRequest(request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateRecipientResponse(_context);
    }

    /**
     * Creates a new recipient
     * @param    request    Required parameter: Recipient data
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createRecipientAsync(
                final CreateRecipientRequest request,
                final String idempotencyKey,
                final APICallBack<GetRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateRecipientRequest(request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetRecipientResponse returnValue = _handleCreateRecipientResponse(_context);
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
     * Builds the HttpRequest object for createRecipient
     */
    private HttpRequest _buildCreateRecipientRequest(
                final CreateRecipientRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients");
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
     * Processes the response for createRecipient
     * @return An object of type GetRecipientResponse
     */
    private GetRecipientResponse _handleCreateRecipientResponse(HttpContext _context)
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
        GetRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetRecipientResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    recipientId    Required parameter: Recipient Identificator
     * @param    request    Required parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetRecipientResponse response from the API call 
     */
    public GetRecipientResponse updateRecipientTransferSettings(
                final String recipientId,
                final UpdateTransferSettingsRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateRecipientTransferSettingsRequest(recipientId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateRecipientTransferSettingsResponse(_context);
    }

    /**
     * 
     * @param    recipientId    Required parameter: Recipient Identificator
     * @param    request    Required parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateRecipientTransferSettingsAsync(
                final String recipientId,
                final UpdateTransferSettingsRequest request,
                final String idempotencyKey,
                final APICallBack<GetRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateRecipientTransferSettingsRequest(recipientId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetRecipientResponse returnValue = _handleUpdateRecipientTransferSettingsResponse(_context);
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
     * Builds the HttpRequest object for updateRecipientTransferSettings
     */
    private HttpRequest _buildUpdateRecipientTransferSettingsRequest(
                final String recipientId,
                final UpdateTransferSettingsRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/transfer-settings");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for updateRecipientTransferSettings
     * @return An object of type GetRecipientResponse
     */
    private GetRecipientResponse _handleUpdateRecipientTransferSettingsResponse(HttpContext _context)
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
        GetRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetRecipientResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    recipientId    Required parameter: Example: 
     * @param    request    Required parameter: Example: 
     * @return    Returns the GetWithdrawResponse response from the API call 
     */
    public GetWithdrawResponse createWithdraw(
                final String recipientId,
                final CreateWithdrawRequest request
    ) throws Throwable {

        HttpRequest _request = _buildCreateWithdrawRequest(recipientId, request);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateWithdrawResponse(_context);
    }

    /**
     * 
     * @param    recipientId    Required parameter: Example: 
     * @param    request    Required parameter: Example: 
     */
    public void createWithdrawAsync(
                final String recipientId,
                final CreateWithdrawRequest request,
                final APICallBack<GetWithdrawResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateWithdrawRequest(recipientId, request);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetWithdrawResponse returnValue = _handleCreateWithdrawResponse(_context);
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
     * Builds the HttpRequest object for createWithdraw
     */
    private HttpRequest _buildCreateWithdrawRequest(
                final String recipientId,
                final CreateWithdrawRequest request) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/withdrawals");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>();
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
     * Processes the response for createWithdraw
     * @return An object of type GetWithdrawResponse
     */
    private GetWithdrawResponse _handleCreateWithdrawResponse(HttpContext _context)
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
        GetWithdrawResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetWithdrawResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    recipientId    Required parameter: Example: 
     * @param    withdrawalId    Required parameter: Example: 
     * @return    Returns the GetWithdrawResponse response from the API call 
     */
    public GetWithdrawResponse getWithdrawById(
                final String recipientId,
                final String withdrawalId
    ) throws Throwable {

        HttpRequest _request = _buildGetWithdrawByIdRequest(recipientId, withdrawalId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetWithdrawByIdResponse(_context);
    }

    /**
     * 
     * @param    recipientId    Required parameter: Example: 
     * @param    withdrawalId    Required parameter: Example: 
     */
    public void getWithdrawByIdAsync(
                final String recipientId,
                final String withdrawalId,
                final APICallBack<GetWithdrawResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetWithdrawByIdRequest(recipientId, withdrawalId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetWithdrawResponse returnValue = _handleGetWithdrawByIdResponse(_context);
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
     * Builds the HttpRequest object for getWithdrawById
     */
    private HttpRequest _buildGetWithdrawByIdRequest(
                final String recipientId,
                final String withdrawalId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/withdrawals/{withdrawal_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        _templateParameters.put("withdrawal_id", withdrawalId);
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
     * Processes the response for getWithdrawById
     * @return An object of type GetWithdrawResponse
     */
    private GetWithdrawResponse _handleGetWithdrawByIdResponse(HttpContext _context)
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
        GetWithdrawResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetWithdrawResponse>(){});

        return _result;
    }

    /**
     * Gets a paginated list of transfers for the recipient
     * @param    recipientId    Required parameter: Example: 
     * @param    page    Optional parameter: Example: 
     * @param    size    Optional parameter: Example: 
     * @param    status    Optional parameter: Example: 
     * @param    createdSince    Optional parameter: Example: 
     * @param    createdUntil    Optional parameter: Example: 
     * @return    Returns the ListWithdrawals response from the API call 
     */
    public ListWithdrawals getWithdrawals(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetWithdrawalsRequest(recipientId, page, size, status, createdSince, createdUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetWithdrawalsResponse(_context);
    }

    /**
     * Gets a paginated list of transfers for the recipient
     * @param    recipientId    Required parameter: Example: 
     * @param    page    Optional parameter: Example: 
     * @param    size    Optional parameter: Example: 
     * @param    status    Optional parameter: Example: 
     * @param    createdSince    Optional parameter: Example: 
     * @param    createdUntil    Optional parameter: Example: 
     */
    public void getWithdrawalsAsync(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil,
                final APICallBack<ListWithdrawals> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetWithdrawalsRequest(recipientId, page, size, status, createdSince, createdUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListWithdrawals returnValue = _handleGetWithdrawalsResponse(_context);
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
     * Builds the HttpRequest object for getWithdrawals
     */
    private HttpRequest _buildGetWithdrawalsRequest(
                final String recipientId,
                final Integer page,
                final Integer size,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/withdrawals");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        if (status != null) {
            _queryParameters.put("status", status);
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
     * Processes the response for getWithdrawals
     * @return An object of type ListWithdrawals
     */
    private ListWithdrawals _handleGetWithdrawalsResponse(HttpContext _context)
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
        ListWithdrawals _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListWithdrawals>(){});

        return _result;
    }

    /**
     * Updates recipient metadata
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetRecipientResponse response from the API call 
     */
    public GetRecipientResponse updateAutomaticAnticipationSettings(
                final String recipientId,
                final UpdateAutomaticAnticipationSettingsRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateAutomaticAnticipationSettingsRequest(recipientId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateAutomaticAnticipationSettingsResponse(_context);
    }

    /**
     * Updates recipient metadata
     * @param    recipientId    Required parameter: Recipient id
     * @param    request    Required parameter: Metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateAutomaticAnticipationSettingsAsync(
                final String recipientId,
                final UpdateAutomaticAnticipationSettingsRequest request,
                final String idempotencyKey,
                final APICallBack<GetRecipientResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateAutomaticAnticipationSettingsRequest(recipientId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetRecipientResponse returnValue = _handleUpdateAutomaticAnticipationSettingsResponse(_context);
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
     * Builds the HttpRequest object for updateAutomaticAnticipationSettings
     */
    private HttpRequest _buildUpdateAutomaticAnticipationSettingsRequest(
                final String recipientId,
                final UpdateAutomaticAnticipationSettingsRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/recipients/{recipient_id}/automatic-anticipation-settings");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("recipient_id", recipientId);
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
     * Processes the response for updateAutomaticAnticipationSettings
     * @return An object of type GetRecipientResponse
     */
    private GetRecipientResponse _handleUpdateAutomaticAnticipationSettingsResponse(HttpContext _context)
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
        GetRecipientResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetRecipientResponse>(){});

        return _result;
    }

}
