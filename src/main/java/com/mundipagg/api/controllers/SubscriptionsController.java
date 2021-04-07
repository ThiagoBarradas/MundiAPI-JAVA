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
import com.mundipagg.api.models.CreateCancelSubscriptionRequest;
import com.mundipagg.api.models.CreateDiscountRequest;
import com.mundipagg.api.models.CreateIncrementRequest;
import com.mundipagg.api.models.CreateSubscriptionItemRequest;
import com.mundipagg.api.models.CreateSubscriptionRequest;
import com.mundipagg.api.models.CreateUsageRequest;
import com.mundipagg.api.models.GetDiscountResponse;
import com.mundipagg.api.models.GetIncrementResponse;
import com.mundipagg.api.models.GetPeriodResponse;
import com.mundipagg.api.models.GetSubscriptionItemResponse;
import com.mundipagg.api.models.GetSubscriptionResponse;
import com.mundipagg.api.models.GetUsageReportResponse;
import com.mundipagg.api.models.GetUsageResponse;
import com.mundipagg.api.models.GetUsagesDetailsResponse;
import com.mundipagg.api.models.ListCyclesResponse;
import com.mundipagg.api.models.ListDiscountsResponse;
import com.mundipagg.api.models.ListIncrementsResponse;
import com.mundipagg.api.models.ListSubscriptionItemsResponse;
import com.mundipagg.api.models.ListSubscriptionsResponse;
import com.mundipagg.api.models.ListUsagesResponse;
import com.mundipagg.api.models.UpdateCurrentCycleEndDateRequest;
import com.mundipagg.api.models.UpdateCurrentCycleStatusRequest;
import com.mundipagg.api.models.UpdateMetadataRequest;
import com.mundipagg.api.models.UpdateSubscriptionAffiliationIdRequest;
import com.mundipagg.api.models.UpdateSubscriptionBillingDateRequest;
import com.mundipagg.api.models.UpdateSubscriptionCardRequest;
import com.mundipagg.api.models.UpdateSubscriptionDueDaysRequest;
import com.mundipagg.api.models.UpdateSubscriptionItemRequest;
import com.mundipagg.api.models.UpdateSubscriptionMinimumPriceRequest;
import com.mundipagg.api.models.UpdateSubscriptionPaymentMethodRequest;
import com.mundipagg.api.models.UpdateSubscriptionStartAtRequest;

import org.joda.time.DateTime;

public class SubscriptionsController extends BaseController {
   
    private Configuration Configuration; 

    public SubscriptionsController(Configuration configuration) {
        this.Configuration = configuration;
    }
    
    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription Id
     * @param    incrementId    Required parameter: The increment Id
     * @return    Returns the GetIncrementResponse response from the API call 
     */
    public GetIncrementResponse getIncrementById(
                final String subscriptionId,
                final String incrementId
    ) throws Throwable {

        HttpRequest _request = _buildGetIncrementByIdRequest(subscriptionId, incrementId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetIncrementByIdResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription Id
     * @param    incrementId    Required parameter: The increment Id
     */
    public void getIncrementByIdAsync(
                final String subscriptionId,
                final String incrementId,
                final APICallBack<GetIncrementResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetIncrementByIdRequest(subscriptionId, incrementId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetIncrementResponse returnValue = _handleGetIncrementByIdResponse(_context);
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
     * Builds the HttpRequest object for getIncrementById
     */
    private HttpRequest _buildGetIncrementByIdRequest(
                final String subscriptionId,
                final String incrementId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/increments/{increment_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("increment_id", incrementId);
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
     * Processes the response for getIncrementById
     * @return An object of type GetIncrementResponse
     */
    private GetIncrementResponse _handleGetIncrementByIdResponse(HttpContext _context)
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
        GetIncrementResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetIncrementResponse>(){});

        return _result;
    }

    /**
     * Updates the start at date from a subscription
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    request    Required parameter: Request for updating the subscription start date
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionStartAt(
                final String subscriptionId,
                final UpdateSubscriptionStartAtRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionStartAtRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionStartAtResponse(_context);
    }

    /**
     * Updates the start at date from a subscription
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    request    Required parameter: Request for updating the subscription start date
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionStartAtAsync(
                final String subscriptionId,
                final UpdateSubscriptionStartAtRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionStartAtRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionStartAtResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionStartAt
     */
    private HttpRequest _buildUpdateSubscriptionStartAtRequest(
                final String subscriptionId,
                final UpdateSubscriptionStartAtRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/start-at");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionStartAt
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionStartAtResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Updates the credit card from a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for updating a card
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionCard(
                final String subscriptionId,
                final UpdateSubscriptionCardRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionCardRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionCardResponse(_context);
    }

    /**
     * Updates the credit card from a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for updating a card
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionCardAsync(
                final String subscriptionId,
                final UpdateSubscriptionCardRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionCardRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionCardResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionCard
     */
    private HttpRequest _buildUpdateSubscriptionCardRequest(
                final String subscriptionId,
                final UpdateSubscriptionCardRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/card");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionCard
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionCardResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Updates a subscription item
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    itemId    Required parameter: Item id
     * @param    body    Required parameter: Request for updating a subscription item
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionItemResponse response from the API call 
     */
    public GetSubscriptionItemResponse updateSubscriptionItem(
                final String subscriptionId,
                final String itemId,
                final UpdateSubscriptionItemRequest body,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionItemRequest(subscriptionId, itemId, body, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionItemResponse(_context);
    }

    /**
     * Updates a subscription item
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    itemId    Required parameter: Item id
     * @param    body    Required parameter: Request for updating a subscription item
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionItemAsync(
                final String subscriptionId,
                final String itemId,
                final UpdateSubscriptionItemRequest body,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionItemRequest(subscriptionId, itemId, body, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionItemResponse returnValue = _handleUpdateSubscriptionItemResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionItem
     */
    private HttpRequest _buildUpdateSubscriptionItemRequest(
                final String subscriptionId,
                final String itemId,
                final UpdateSubscriptionItemRequest body,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items/{item_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("item_id", itemId);
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
        HttpRequest _request = getClientInstance().putBody(_queryUrl, _headers, APIHelper.serialize(body),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for updateSubscriptionItem
     * @return An object of type GetSubscriptionItemResponse
     */
    private GetSubscriptionItemResponse _handleUpdateSubscriptionItemResponse(HttpContext _context)
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
        GetSubscriptionItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionItemResponse>(){});

        return _result;
    }

    /**
     * Creates a usage
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    itemId    Required parameter: Item id
     * @param    body    Required parameter: Request for creating a usage
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetUsageResponse response from the API call 
     */
    public GetUsageResponse createUsage(
                final String subscriptionId,
                final String itemId,
                final CreateUsageRequest body,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateUsageRequest(subscriptionId, itemId, body, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateUsageResponse(_context);
    }

    /**
     * Creates a usage
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    itemId    Required parameter: Item id
     * @param    body    Required parameter: Request for creating a usage
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createUsageAsync(
                final String subscriptionId,
                final String itemId,
                final CreateUsageRequest body,
                final String idempotencyKey,
                final APICallBack<GetUsageResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateUsageRequest(subscriptionId, itemId, body, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetUsageResponse returnValue = _handleCreateUsageResponse(_context);
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
     * Builds the HttpRequest object for createUsage
     */
    private HttpRequest _buildCreateUsageRequest(
                final String subscriptionId,
                final String itemId,
                final CreateUsageRequest body,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items/{item_id}/usages");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("item_id", itemId);
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
        HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(body),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for createUsage
     * @return An object of type GetUsageResponse
     */
    private GetUsageResponse _handleCreateUsageResponse(HttpContext _context)
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
        GetUsageResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetUsageResponse>(){});

        return _result;
    }

    /**
     * Gets a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse getSubscription(
                final String subscriptionId
    ) throws Throwable {

        HttpRequest _request = _buildGetSubscriptionRequest(subscriptionId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetSubscriptionResponse(_context);
    }

    /**
     * Gets a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     */
    public void getSubscriptionAsync(
                final String subscriptionId,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetSubscriptionRequest(subscriptionId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleGetSubscriptionResponse(_context);
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
     * Builds the HttpRequest object for getSubscription
     */
    private HttpRequest _buildGetSubscriptionRequest(
                final String subscriptionId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}");

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
     * Processes the response for getSubscription
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleGetSubscriptionResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Updates the payment method from a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for updating the paymentmethod from a subscription
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionPaymentMethod(
                final String subscriptionId,
                final UpdateSubscriptionPaymentMethodRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionPaymentMethodRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionPaymentMethodResponse(_context);
    }

    /**
     * Updates the payment method from a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for updating the paymentmethod from a subscription
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionPaymentMethodAsync(
                final String subscriptionId,
                final UpdateSubscriptionPaymentMethodRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionPaymentMethodRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionPaymentMethodResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionPaymentMethod
     */
    private HttpRequest _buildUpdateSubscriptionPaymentMethodRequest(
                final String subscriptionId,
                final UpdateSubscriptionPaymentMethodRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/payment-method");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionPaymentMethod
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionPaymentMethodResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Creates a new subscription
     * @param    body    Required parameter: Request for creating a subscription
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse createSubscription(
                final CreateSubscriptionRequest body,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateSubscriptionRequest(body, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateSubscriptionResponse(_context);
    }

    /**
     * Creates a new subscription
     * @param    body    Required parameter: Request for creating a subscription
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createSubscriptionAsync(
                final CreateSubscriptionRequest body,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateSubscriptionRequest(body, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleCreateSubscriptionResponse(_context);
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
     * Builds the HttpRequest object for createSubscription
     */
    private HttpRequest _buildCreateSubscriptionRequest(
                final CreateSubscriptionRequest body,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions");
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
        HttpRequest _request = getClientInstance().postBody(_queryUrl, _headers, APIHelper.serialize(body),
                Configuration.basicAuthUserName, Configuration.basicAuthPassword);

        // Invoke the callback before request if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnBeforeRequest(_request);
        }

        return _request;
    }

    /**
     * Processes the response for createSubscription
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleCreateSubscriptionResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Creates a new Subscription item
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for creating a subscription item
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionItemResponse response from the API call 
     */
    public GetSubscriptionItemResponse createSubscriptionItem(
                final String subscriptionId,
                final CreateSubscriptionItemRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateSubscriptionItemRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateSubscriptionItemResponse(_context);
    }

    /**
     * Creates a new Subscription item
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for creating a subscription item
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createSubscriptionItemAsync(
                final String subscriptionId,
                final CreateSubscriptionItemRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateSubscriptionItemRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionItemResponse returnValue = _handleCreateSubscriptionItemResponse(_context);
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
     * Builds the HttpRequest object for createSubscriptionItem
     */
    private HttpRequest _buildCreateSubscriptionItemRequest(
                final String subscriptionId,
                final CreateSubscriptionItemRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for createSubscriptionItem
     * @return An object of type GetSubscriptionItemResponse
     */
    private GetSubscriptionItemResponse _handleCreateSubscriptionItemResponse(HttpContext _context)
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
        GetSubscriptionItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionItemResponse>(){});

        return _result;
    }

    /**
     * Creates a discount
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for creating a discount
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetDiscountResponse response from the API call 
     */
    public GetDiscountResponse createDiscount(
                final String subscriptionId,
                final CreateDiscountRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateDiscountRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateDiscountResponse(_context);
    }

    /**
     * Creates a discount
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for creating a discount
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createDiscountAsync(
                final String subscriptionId,
                final CreateDiscountRequest request,
                final String idempotencyKey,
                final APICallBack<GetDiscountResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateDiscountRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetDiscountResponse returnValue = _handleCreateDiscountResponse(_context);
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
     * Builds the HttpRequest object for createDiscount
     */
    private HttpRequest _buildCreateDiscountRequest(
                final String subscriptionId,
                final CreateDiscountRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/discounts");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for createDiscount
     * @return An object of type GetDiscountResponse
     */
    private GetDiscountResponse _handleCreateDiscountResponse(HttpContext _context)
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
        GetDiscountResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetDiscountResponse>(){});

        return _result;
    }

    /**
     * Get Subscription Item
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    itemId    Required parameter: Item id
     * @return    Returns the GetSubscriptionItemResponse response from the API call 
     */
    public GetSubscriptionItemResponse getSubscriptionItem(
                final String subscriptionId,
                final String itemId
    ) throws Throwable {

        HttpRequest _request = _buildGetSubscriptionItemRequest(subscriptionId, itemId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetSubscriptionItemResponse(_context);
    }

    /**
     * Get Subscription Item
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    itemId    Required parameter: Item id
     */
    public void getSubscriptionItemAsync(
                final String subscriptionId,
                final String itemId,
                final APICallBack<GetSubscriptionItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetSubscriptionItemRequest(subscriptionId, itemId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionItemResponse returnValue = _handleGetSubscriptionItemResponse(_context);
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
     * Builds the HttpRequest object for getSubscriptionItem
     */
    private HttpRequest _buildGetSubscriptionItemRequest(
                final String subscriptionId,
                final String itemId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items/{item_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("item_id", itemId);
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
     * Processes the response for getSubscriptionItem
     * @return An object of type GetSubscriptionItemResponse
     */
    private GetSubscriptionItemResponse _handleGetSubscriptionItemResponse(HttpContext _context)
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
        GetSubscriptionItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionItemResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Example: 
     * @param    request    Required parameter: Request for updating a subscription affiliation id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionAffiliationId(
                final String subscriptionId,
                final UpdateSubscriptionAffiliationIdRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionAffiliationIdRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionAffiliationIdResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Example: 
     * @param    request    Required parameter: Request for updating a subscription affiliation id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionAffiliationIdAsync(
                final String subscriptionId,
                final UpdateSubscriptionAffiliationIdRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionAffiliationIdRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionAffiliationIdResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionAffiliationId
     */
    private HttpRequest _buildUpdateSubscriptionAffiliationIdRequest(
                final String subscriptionId,
                final UpdateSubscriptionAffiliationIdRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/gateway-affiliation-id");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionAffiliationId
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionAffiliationIdResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Create Usage
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    itemId    Required parameter: Item id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetUsageResponse response from the API call 
     */
    public GetUsageResponse createAnUsage(
                final String subscriptionId,
                final String itemId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateAnUsageRequest(subscriptionId, itemId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateAnUsageResponse(_context);
    }

    /**
     * Create Usage
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    itemId    Required parameter: Item id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createAnUsageAsync(
                final String subscriptionId,
                final String itemId,
                final String idempotencyKey,
                final APICallBack<GetUsageResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateAnUsageRequest(subscriptionId, itemId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetUsageResponse returnValue = _handleCreateAnUsageResponse(_context);
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
     * Builds the HttpRequest object for createAnUsage
     */
    private HttpRequest _buildCreateAnUsageRequest(
                final String subscriptionId,
                final String itemId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items/{item_id}/usages");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("item_id", itemId);
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
     * Processes the response for createAnUsage
     * @return An object of type GetUsageResponse
     */
    private GetUsageResponse _handleCreateAnUsageResponse(HttpContext _context)
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
        GetUsageResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetUsageResponse>(){});

        return _result;
    }

    /**
     * Gets all subscriptions
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for subscription's code
     * @param    billingType    Optional parameter: Filter for subscription's billing type
     * @param    customerId    Optional parameter: Filter for subscription's customer id
     * @param    planId    Optional parameter: Filter for subscription's plan id
     * @param    cardId    Optional parameter: Filter for subscription's card id
     * @param    status    Optional parameter: Filter for subscription's status
     * @param    nextBillingSince    Optional parameter: Filter for subscription's next billing date start range
     * @param    nextBillingUntil    Optional parameter: Filter for subscription's next billing date end range
     * @param    createdSince    Optional parameter: Filter for subscription's creation date start range
     * @param    createdUntil    Optional parameter: Filter for subscriptions creation date end range
     * @return    Returns the ListSubscriptionsResponse response from the API call 
     */
    public ListSubscriptionsResponse getSubscriptions(
                final Integer page,
                final Integer size,
                final String code,
                final String billingType,
                final String customerId,
                final String planId,
                final String cardId,
                final String status,
                final DateTime nextBillingSince,
                final DateTime nextBillingUntil,
                final DateTime createdSince,
                final DateTime createdUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetSubscriptionsRequest(page, size, code, billingType, customerId, planId, cardId, status, nextBillingSince, nextBillingUntil, createdSince, createdUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetSubscriptionsResponse(_context);
    }

    /**
     * Gets all subscriptions
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for subscription's code
     * @param    billingType    Optional parameter: Filter for subscription's billing type
     * @param    customerId    Optional parameter: Filter for subscription's customer id
     * @param    planId    Optional parameter: Filter for subscription's plan id
     * @param    cardId    Optional parameter: Filter for subscription's card id
     * @param    status    Optional parameter: Filter for subscription's status
     * @param    nextBillingSince    Optional parameter: Filter for subscription's next billing date start range
     * @param    nextBillingUntil    Optional parameter: Filter for subscription's next billing date end range
     * @param    createdSince    Optional parameter: Filter for subscription's creation date start range
     * @param    createdUntil    Optional parameter: Filter for subscriptions creation date end range
     */
    public void getSubscriptionsAsync(
                final Integer page,
                final Integer size,
                final String code,
                final String billingType,
                final String customerId,
                final String planId,
                final String cardId,
                final String status,
                final DateTime nextBillingSince,
                final DateTime nextBillingUntil,
                final DateTime createdSince,
                final DateTime createdUntil,
                final APICallBack<ListSubscriptionsResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetSubscriptionsRequest(page, size, code, billingType, customerId, planId, cardId, status, nextBillingSince, nextBillingUntil, createdSince, createdUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListSubscriptionsResponse returnValue = _handleGetSubscriptionsResponse(_context);
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
     * Builds the HttpRequest object for getSubscriptions
     */
    private HttpRequest _buildGetSubscriptionsRequest(
                final Integer page,
                final Integer size,
                final String code,
                final String billingType,
                final String customerId,
                final String planId,
                final String cardId,
                final String status,
                final DateTime nextBillingSince,
                final DateTime nextBillingUntil,
                final DateTime createdSince,
                final DateTime createdUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions");

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
        if (billingType != null) {
            _queryParameters.put("billing_type", billingType);
        }
        if (customerId != null) {
            _queryParameters.put("customer_id", customerId);
        }
        if (planId != null) {
            _queryParameters.put("plan_id", planId);
        }
        if (cardId != null) {
            _queryParameters.put("card_id", cardId);
        }
        if (status != null) {
            _queryParameters.put("status", status);
        }
        if (nextBillingSince != null) {
            _queryParameters.put("next_billing_since", DateTimeHelper.toRfc8601DateTime(nextBillingSince));
        }
        if (nextBillingUntil != null) {
            _queryParameters.put("next_billing_until", DateTimeHelper.toRfc8601DateTime(nextBillingUntil));
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
     * Processes the response for getSubscriptions
     * @return An object of type ListSubscriptionsResponse
     */
    private ListSubscriptionsResponse _handleGetSubscriptionsResponse(HttpContext _context)
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
        ListSubscriptionsResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListSubscriptionsResponse>(){});

        return _result;
    }

    /**
     * Updates the metadata from a subscription
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    request    Required parameter: Request for updating the subscrption metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionMetadata(
                final String subscriptionId,
                final UpdateMetadataRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionMetadataRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionMetadataResponse(_context);
    }

    /**
     * Updates the metadata from a subscription
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    request    Required parameter: Request for updating the subscrption metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionMetadataAsync(
                final String subscriptionId,
                final UpdateMetadataRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionMetadataRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionMetadataResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionMetadata
     */
    private HttpRequest _buildUpdateSubscriptionMetadataRequest(
                final String subscriptionId,
                final UpdateMetadataRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/Subscriptions/{subscription_id}/metadata");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionMetadata
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionMetadataResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Deletes a subscription item
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    subscriptionItemId    Required parameter: Subscription item id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionItemResponse response from the API call 
     */
    public GetSubscriptionItemResponse deleteSubscriptionItem(
                final String subscriptionId,
                final String subscriptionItemId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteSubscriptionItemRequest(subscriptionId, subscriptionItemId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteSubscriptionItemResponse(_context);
    }

    /**
     * Deletes a subscription item
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    subscriptionItemId    Required parameter: Subscription item id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteSubscriptionItemAsync(
                final String subscriptionId,
                final String subscriptionItemId,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteSubscriptionItemRequest(subscriptionId, subscriptionItemId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionItemResponse returnValue = _handleDeleteSubscriptionItemResponse(_context);
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
     * Builds the HttpRequest object for deleteSubscriptionItem
     */
    private HttpRequest _buildDeleteSubscriptionItemRequest(
                final String subscriptionId,
                final String subscriptionItemId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items/{subscription_item_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("subscription_item_id", subscriptionItemId);
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
     * Processes the response for deleteSubscriptionItem
     * @return An object of type GetSubscriptionItemResponse
     */
    private GetSubscriptionItemResponse _handleDeleteSubscriptionItemResponse(HttpContext _context)
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
        GetSubscriptionItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionItemResponse>(){});

        return _result;
    }

    /**
     * Deletes a usage
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    itemId    Required parameter: The subscription item id
     * @param    usageId    Required parameter: The usage id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetUsageResponse response from the API call 
     */
    public GetUsageResponse deleteUsage(
                final String subscriptionId,
                final String itemId,
                final String usageId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteUsageRequest(subscriptionId, itemId, usageId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteUsageResponse(_context);
    }

    /**
     * Deletes a usage
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    itemId    Required parameter: The subscription item id
     * @param    usageId    Required parameter: The usage id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteUsageAsync(
                final String subscriptionId,
                final String itemId,
                final String usageId,
                final String idempotencyKey,
                final APICallBack<GetUsageResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteUsageRequest(subscriptionId, itemId, usageId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetUsageResponse returnValue = _handleDeleteUsageResponse(_context);
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
     * Builds the HttpRequest object for deleteUsage
     */
    private HttpRequest _buildDeleteUsageRequest(
                final String subscriptionId,
                final String itemId,
                final String usageId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items/{item_id}/usages/{usage_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("item_id", itemId);
        _templateParameters.put("usage_id", usageId);
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
     * Processes the response for deleteUsage
     * @return An object of type GetUsageResponse
     */
    private GetUsageResponse _handleDeleteUsageResponse(HttpContext _context)
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
        GetUsageResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetUsageResponse>(){});

        return _result;
    }

    /**
     * Deletes a discount
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    discountId    Required parameter: Discount Id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetDiscountResponse response from the API call 
     */
    public GetDiscountResponse deleteDiscount(
                final String subscriptionId,
                final String discountId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteDiscountRequest(subscriptionId, discountId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteDiscountResponse(_context);
    }

    /**
     * Deletes a discount
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    discountId    Required parameter: Discount Id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteDiscountAsync(
                final String subscriptionId,
                final String discountId,
                final String idempotencyKey,
                final APICallBack<GetDiscountResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteDiscountRequest(subscriptionId, discountId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetDiscountResponse returnValue = _handleDeleteDiscountResponse(_context);
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
     * Builds the HttpRequest object for deleteDiscount
     */
    private HttpRequest _buildDeleteDiscountRequest(
                final String subscriptionId,
                final String discountId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/discounts/{discount_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("discount_id", discountId);
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
     * Processes the response for deleteDiscount
     * @return An object of type GetDiscountResponse
     */
    private GetDiscountResponse _handleDeleteDiscountResponse(HttpContext _context)
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
        GetDiscountResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetDiscountResponse>(){});

        return _result;
    }

    /**
     * Cancels a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Optional parameter: Request for cancelling a subscription
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse cancelSubscription(
                final String subscriptionId,
                final CreateCancelSubscriptionRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCancelSubscriptionRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCancelSubscriptionResponse(_context);
    }

    /**
     * Cancels a subscription
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Optional parameter: Request for cancelling a subscription
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void cancelSubscriptionAsync(
                final String subscriptionId,
                final CreateCancelSubscriptionRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCancelSubscriptionRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleCancelSubscriptionResponse(_context);
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
     * Builds the HttpRequest object for cancelSubscription
     */
    private HttpRequest _buildCancelSubscriptionRequest(
                final String subscriptionId,
                final CreateCancelSubscriptionRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for cancelSubscription
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleCancelSubscriptionResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    discountId    Required parameter: Example: 
     * @return    Returns the GetDiscountResponse response from the API call 
     */
    public GetDiscountResponse getDiscountById(
                final String subscriptionId,
                final String discountId
    ) throws Throwable {

        HttpRequest _request = _buildGetDiscountByIdRequest(subscriptionId, discountId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetDiscountByIdResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    discountId    Required parameter: Example: 
     */
    public void getDiscountByIdAsync(
                final String subscriptionId,
                final String discountId,
                final APICallBack<GetDiscountResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetDiscountByIdRequest(subscriptionId, discountId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetDiscountResponse returnValue = _handleGetDiscountByIdResponse(_context);
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
     * Builds the HttpRequest object for getDiscountById
     */
    private HttpRequest _buildGetDiscountByIdRequest(
                final String subscriptionId,
                final String discountId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/discounts/{discountId}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("discountId", discountId);
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
     * Processes the response for getDiscountById
     * @return An object of type GetDiscountResponse
     */
    private GetDiscountResponse _handleGetDiscountByIdResponse(HttpContext _context)
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
        GetDiscountResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetDiscountResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    page    Required parameter: Page number
     * @param    size    Required parameter: Page size
     * @return    Returns the ListDiscountsResponse response from the API call 
     */
    public ListDiscountsResponse getDiscounts(
                final String subscriptionId,
                final int page,
                final int size
    ) throws Throwable {

        HttpRequest _request = _buildGetDiscountsRequest(subscriptionId, page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetDiscountsResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    page    Required parameter: Page number
     * @param    size    Required parameter: Page size
     */
    public void getDiscountsAsync(
                final String subscriptionId,
                final int page,
                final int size,
                final APICallBack<ListDiscountsResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetDiscountsRequest(subscriptionId, page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListDiscountsResponse returnValue = _handleGetDiscountsResponse(_context);
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
     * Builds the HttpRequest object for getDiscounts
     */
    private HttpRequest _buildGetDiscountsRequest(
                final String subscriptionId,
                final int page,
                final int size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/discounts/");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        _queryParameters.put("page", page);
        _queryParameters.put("size", size);
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
     * Processes the response for getDiscounts
     * @return An object of type ListDiscountsResponse
     */
    private ListDiscountsResponse _handleGetDiscountsResponse(HttpContext _context)
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
        ListDiscountsResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListDiscountsResponse>(){});

        return _result;
    }

    /**
     * Creates a increment
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for creating a increment
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetIncrementResponse response from the API call 
     */
    public GetIncrementResponse createIncrement(
                final String subscriptionId,
                final CreateIncrementRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateIncrementRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateIncrementResponse(_context);
    }

    /**
     * Creates a increment
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    request    Required parameter: Request for creating a increment
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createIncrementAsync(
                final String subscriptionId,
                final CreateIncrementRequest request,
                final String idempotencyKey,
                final APICallBack<GetIncrementResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateIncrementRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetIncrementResponse returnValue = _handleCreateIncrementResponse(_context);
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
     * Builds the HttpRequest object for createIncrement
     */
    private HttpRequest _buildCreateIncrementRequest(
                final String subscriptionId,
                final CreateIncrementRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/increments");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for createIncrement
     * @return An object of type GetIncrementResponse
     */
    private GetIncrementResponse _handleCreateIncrementResponse(HttpContext _context)
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
        GetIncrementResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetIncrementResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @return    Returns the ListIncrementsResponse response from the API call 
     */
    public ListIncrementsResponse getIncrements(
                final String subscriptionId,
                final Integer page,
                final Integer size
    ) throws Throwable {

        HttpRequest _request = _buildGetIncrementsRequest(subscriptionId, page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetIncrementsResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     */
    public void getIncrementsAsync(
                final String subscriptionId,
                final Integer page,
                final Integer size,
                final APICallBack<ListIncrementsResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetIncrementsRequest(subscriptionId, page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListIncrementsResponse returnValue = _handleGetIncrementsResponse(_context);
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
     * Builds the HttpRequest object for getIncrements
     */
    private HttpRequest _buildGetIncrementsRequest(
                final String subscriptionId,
                final Integer page,
                final Integer size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/increments/");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for getIncrements
     * @return An object of type ListIncrementsResponse
     */
    private ListIncrementsResponse _handleGetIncrementsResponse(HttpContext _context)
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
        ListIncrementsResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListIncrementsResponse>(){});

        return _result;
    }

    /**
     * Deletes a increment
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    incrementId    Required parameter: Increment id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetIncrementResponse response from the API call 
     */
    public GetIncrementResponse deleteIncrement(
                final String subscriptionId,
                final String incrementId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteIncrementRequest(subscriptionId, incrementId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteIncrementResponse(_context);
    }

    /**
     * Deletes a increment
     * @param    subscriptionId    Required parameter: Subscription id
     * @param    incrementId    Required parameter: Increment id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteIncrementAsync(
                final String subscriptionId,
                final String incrementId,
                final String idempotencyKey,
                final APICallBack<GetIncrementResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteIncrementRequest(subscriptionId, incrementId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetIncrementResponse returnValue = _handleDeleteIncrementResponse(_context);
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
     * Builds the HttpRequest object for deleteIncrement
     */
    private HttpRequest _buildDeleteIncrementRequest(
                final String subscriptionId,
                final String incrementId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/increments/{increment_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("increment_id", incrementId);
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
     * Processes the response for deleteIncrement
     * @return An object of type GetIncrementResponse
     */
    private GetIncrementResponse _handleDeleteIncrementResponse(HttpContext _context)
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
        GetIncrementResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetIncrementResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Identifier
     * @param    cycleId    Optional parameter: Cycle id
     * @param    size    Optional parameter: Page size
     * @param    page    Optional parameter: Page number
     * @param    itemId    Optional parameter: Identificador do item
     * @param    group    Optional parameter: identificador da loja (account) de cada item
     * @return    Returns the GetUsagesDetailsResponse response from the API call 
     */
    public GetUsagesDetailsResponse getUsagesDetails(
                final String subscriptionId,
                final String cycleId,
                final Integer size,
                final Integer page,
                final String itemId,
                final String group
    ) throws Throwable {

        HttpRequest _request = _buildGetUsagesDetailsRequest(subscriptionId, cycleId, size, page, itemId, group);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetUsagesDetailsResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Identifier
     * @param    cycleId    Optional parameter: Cycle id
     * @param    size    Optional parameter: Page size
     * @param    page    Optional parameter: Page number
     * @param    itemId    Optional parameter: Identificador do item
     * @param    group    Optional parameter: identificador da loja (account) de cada item
     */
    public void getUsagesDetailsAsync(
                final String subscriptionId,
                final String cycleId,
                final Integer size,
                final Integer page,
                final String itemId,
                final String group,
                final APICallBack<GetUsagesDetailsResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetUsagesDetailsRequest(subscriptionId, cycleId, size, page, itemId, group);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetUsagesDetailsResponse returnValue = _handleGetUsagesDetailsResponse(_context);
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
     * Builds the HttpRequest object for getUsagesDetails
     */
    private HttpRequest _buildGetUsagesDetailsRequest(
                final String subscriptionId,
                final String cycleId,
                final Integer size,
                final Integer page,
                final String itemId,
                final String group) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/usages-details/");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (cycleId != null) {
            _queryParameters.put("cycle_id", cycleId);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (itemId != null) {
            _queryParameters.put("item_id", itemId);
        }
        if (group != null) {
            _queryParameters.put("group", group);
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
     * Processes the response for getUsagesDetails
     * @return An object of type GetUsagesDetailsResponse
     */
    private GetUsagesDetailsResponse _handleGetUsagesDetailsResponse(HttpContext _context)
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
        GetUsagesDetailsResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetUsagesDetailsResponse>(){});

        return _result;
    }

    /**
     * Lists all usages from a subscription item
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    itemId    Required parameter: The subscription item id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Identification code in the client system
     * @param    group    Optional parameter: Identification group in the client system
     * @param    usedSince    Optional parameter: Example: 
     * @param    usedUntil    Optional parameter: Example: 
     * @return    Returns the ListUsagesResponse response from the API call 
     */
    public ListUsagesResponse getUsages(
                final String subscriptionId,
                final String itemId,
                final Integer page,
                final Integer size,
                final String code,
                final String group,
                final DateTime usedSince,
                final DateTime usedUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetUsagesRequest(subscriptionId, itemId, page, size, code, group, usedSince, usedUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetUsagesResponse(_context);
    }

    /**
     * Lists all usages from a subscription item
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    itemId    Required parameter: The subscription item id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Identification code in the client system
     * @param    group    Optional parameter: Identification group in the client system
     * @param    usedSince    Optional parameter: Example: 
     * @param    usedUntil    Optional parameter: Example: 
     */
    public void getUsagesAsync(
                final String subscriptionId,
                final String itemId,
                final Integer page,
                final Integer size,
                final String code,
                final String group,
                final DateTime usedSince,
                final DateTime usedUntil,
                final APICallBack<ListUsagesResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetUsagesRequest(subscriptionId, itemId, page, size, code, group, usedSince, usedUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListUsagesResponse returnValue = _handleGetUsagesResponse(_context);
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
     * Builds the HttpRequest object for getUsages
     */
    private HttpRequest _buildGetUsagesRequest(
                final String subscriptionId,
                final String itemId,
                final Integer page,
                final Integer size,
                final String code,
                final String group,
                final DateTime usedSince,
                final DateTime usedUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items/{item_id}/usages");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("item_id", itemId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

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
        if (group != null) {
            _queryParameters.put("group", group);
        }
        if (usedSince != null) {
            _queryParameters.put("used_since", DateTimeHelper.toRfc8601DateTime(usedSince));
        }
        if (usedUntil != null) {
            _queryParameters.put("used_until", DateTimeHelper.toRfc8601DateTime(usedUntil));
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
     * Processes the response for getUsages
     * @return An object of type ListUsagesResponse
     */
    private ListUsagesResponse _handleGetUsagesResponse(HttpContext _context)
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
        ListUsagesResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListUsagesResponse>(){});

        return _result;
    }

    /**
     * Get Subscription Items
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    name    Optional parameter: The item name
     * @param    code    Optional parameter: Identification code in the client system
     * @param    status    Optional parameter: The item statis
     * @param    description    Optional parameter: The item description
     * @param    createdSince    Optional parameter: Filter for item's creation date start range
     * @param    createdUntil    Optional parameter: Filter for item's creation date end range
     * @return    Returns the ListSubscriptionItemsResponse response from the API call 
     */
    public ListSubscriptionItemsResponse getSubscriptionItems(
                final String subscriptionId,
                final Integer page,
                final Integer size,
                final String name,
                final String code,
                final String status,
                final String description,
                final String createdSince,
                final String createdUntil
    ) throws Throwable {

        HttpRequest _request = _buildGetSubscriptionItemsRequest(subscriptionId, page, size, name, code, status, description, createdSince, createdUntil);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetSubscriptionItemsResponse(_context);
    }

    /**
     * Get Subscription Items
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    name    Optional parameter: The item name
     * @param    code    Optional parameter: Identification code in the client system
     * @param    status    Optional parameter: The item statis
     * @param    description    Optional parameter: The item description
     * @param    createdSince    Optional parameter: Filter for item's creation date start range
     * @param    createdUntil    Optional parameter: Filter for item's creation date end range
     */
    public void getSubscriptionItemsAsync(
                final String subscriptionId,
                final Integer page,
                final Integer size,
                final String name,
                final String code,
                final String status,
                final String description,
                final String createdSince,
                final String createdUntil,
                final APICallBack<ListSubscriptionItemsResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetSubscriptionItemsRequest(subscriptionId, page, size, name, code, status, description, createdSince, createdUntil);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListSubscriptionItemsResponse returnValue = _handleGetSubscriptionItemsResponse(_context);
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
     * Builds the HttpRequest object for getSubscriptionItems
     */
    private HttpRequest _buildGetSubscriptionItemsRequest(
                final String subscriptionId,
                final Integer page,
                final Integer size,
                final String name,
                final String code,
                final String status,
                final String description,
                final String createdSince,
                final String createdUntil) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/items");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        if (page != null) {
            _queryParameters.put("page", page);
        }
        if (size != null) {
            _queryParameters.put("size", size);
        }
        if (name != null) {
            _queryParameters.put("name", name);
        }
        if (code != null) {
            _queryParameters.put("code", code);
        }
        if (status != null) {
            _queryParameters.put("status", status);
        }
        if (description != null) {
            _queryParameters.put("description", description);
        }
        if (createdSince != null) {
            _queryParameters.put("created_since", createdSince);
        }
        if (createdUntil != null) {
            _queryParameters.put("created_until", createdUntil);
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
     * Processes the response for getSubscriptionItems
     * @return An object of type ListSubscriptionItemsResponse
     */
    private ListSubscriptionItemsResponse _handleGetSubscriptionItemsResponse(HttpContext _context)
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
        ListSubscriptionItemsResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListSubscriptionItemsResponse>(){});

        return _result;
    }

    /**
     * Updates the boleto due days from a subscription
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    request    Required parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionDueDays(
                final String subscriptionId,
                final UpdateSubscriptionDueDaysRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionDueDaysRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionDueDaysResponse(_context);
    }

    /**
     * Updates the boleto due days from a subscription
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    request    Required parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionDueDaysAsync(
                final String subscriptionId,
                final UpdateSubscriptionDueDaysRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionDueDaysRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionDueDaysResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionDueDays
     */
    private HttpRequest _buildUpdateSubscriptionDueDaysRequest(
                final String subscriptionId,
                final UpdateSubscriptionDueDaysRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/boleto-due-days");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionDueDays
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionDueDaysResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Atualização do valor mínimo da assinatura
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    request    Required parameter: Request da requisição com o valor mínimo que será configurado
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionMiniumPrice(
                final String subscriptionId,
                final UpdateSubscriptionMinimumPriceRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionMiniumPriceRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionMiniumPriceResponse(_context);
    }

    /**
     * Atualização do valor mínimo da assinatura
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    request    Required parameter: Request da requisição com o valor mínimo que será configurado
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionMiniumPriceAsync(
                final String subscriptionId,
                final UpdateSubscriptionMinimumPriceRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionMiniumPriceRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionMiniumPriceResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionMiniumPrice
     */
    private HttpRequest _buildUpdateSubscriptionMiniumPriceRequest(
                final String subscriptionId,
                final UpdateSubscriptionMinimumPriceRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/minimum_price");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionMiniumPrice
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionMiniumPriceResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * Updates the billing date from a subscription
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    request    Required parameter: Request for updating the subscription billing date
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateSubscriptionBillingDate(
                final String subscriptionId,
                final UpdateSubscriptionBillingDateRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateSubscriptionBillingDateRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateSubscriptionBillingDateResponse(_context);
    }

    /**
     * Updates the billing date from a subscription
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    request    Required parameter: Request for updating the subscription billing date
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateSubscriptionBillingDateAsync(
                final String subscriptionId,
                final UpdateSubscriptionBillingDateRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateSubscriptionBillingDateRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateSubscriptionBillingDateResponse(_context);
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
     * Builds the HttpRequest object for updateSubscriptionBillingDate
     */
    private HttpRequest _buildUpdateSubscriptionBillingDateRequest(
                final String subscriptionId,
                final UpdateSubscriptionBillingDateRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/billing-date");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateSubscriptionBillingDate
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateSubscriptionBillingDateResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Example: 
     * @param    request    Required parameter: Request for updating the end date of the current signature cycle
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetSubscriptionResponse response from the API call 
     */
    public GetSubscriptionResponse updateLatestPeriodEndAt(
                final String subscriptionId,
                final UpdateCurrentCycleEndDateRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateLatestPeriodEndAtRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateLatestPeriodEndAtResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Example: 
     * @param    request    Required parameter: Request for updating the end date of the current signature cycle
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateLatestPeriodEndAtAsync(
                final String subscriptionId,
                final UpdateCurrentCycleEndDateRequest request,
                final String idempotencyKey,
                final APICallBack<GetSubscriptionResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateLatestPeriodEndAtRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetSubscriptionResponse returnValue = _handleUpdateLatestPeriodEndAtResponse(_context);
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
     * Builds the HttpRequest object for updateLatestPeriodEndAt
     */
    private HttpRequest _buildUpdateLatestPeriodEndAtRequest(
                final String subscriptionId,
                final UpdateCurrentCycleEndDateRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/periods/latest/end-at");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for updateLatestPeriodEndAt
     * @return An object of type GetSubscriptionResponse
     */
    private GetSubscriptionResponse _handleUpdateLatestPeriodEndAtResponse(HttpContext _context)
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
        GetSubscriptionResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetSubscriptionResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    request    Required parameter: Request for updating the end date of the subscription current status
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateCurrentCycleStatus(
                final String subscriptionId,
                final UpdateCurrentCycleStatusRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateCurrentCycleStatusRequest(subscriptionId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        _handleUpdateCurrentCycleStatusResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    request    Required parameter: Request for updating the end date of the subscription current status
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateCurrentCycleStatusAsync(
                final String subscriptionId,
                final UpdateCurrentCycleStatusRequest request,
                final String idempotencyKey,
                final APICallBack<Object> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateCurrentCycleStatusRequest(subscriptionId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            _handleUpdateCurrentCycleStatusResponse(_context);
                            callBack.onSuccess(_context, null);
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
     * Builds the HttpRequest object for updateCurrentCycleStatus
     */
    private HttpRequest _buildUpdateCurrentCycleStatusRequest(
                final String subscriptionId,
                final UpdateCurrentCycleStatusRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/cycle-status");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);
        //validate and preprocess url
        String _queryUrl = APIHelper.cleanUrl(_queryBuilder);

        //load all headers for the outgoing API request
        Map<String, String> _headers = new HashMap<String, String>();
        if (idempotencyKey != null) {
            _headers.put("idempotency-key", idempotencyKey);
        }
        _headers.put("user-agent", BaseController.userAgent);
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
     * Processes the response for updateCurrentCycleStatus
     */
    private void _handleUpdateCurrentCycleStatusResponse(HttpContext _context)
            throws APIException, IOException {
        HttpResponse _response = _context.getResponse();

        //invoke the callback after response if its not null
        if (getHttpCallBack() != null) {
            getHttpCallBack().OnAfterResponse(_context);
        }

        //handle errors defined at the API level
        validateResponse(_response, _context);


    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    page    Required parameter: Page number
     * @param    size    Required parameter: Page size
     * @return    Returns the ListCyclesResponse response from the API call 
     */
    public ListCyclesResponse getSubscriptionCycles(
                final String subscriptionId,
                final String page,
                final String size
    ) throws Throwable {

        HttpRequest _request = _buildGetSubscriptionCyclesRequest(subscriptionId, page, size);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetSubscriptionCyclesResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Subscription Id
     * @param    page    Required parameter: Page number
     * @param    size    Required parameter: Page size
     */
    public void getSubscriptionCyclesAsync(
                final String subscriptionId,
                final String page,
                final String size,
                final APICallBack<ListCyclesResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetSubscriptionCyclesRequest(subscriptionId, page, size);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListCyclesResponse returnValue = _handleGetSubscriptionCyclesResponse(_context);
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
     * Builds the HttpRequest object for getSubscriptionCycles
     */
    private HttpRequest _buildGetSubscriptionCyclesRequest(
                final String subscriptionId,
                final String page,
                final String size) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/cycles");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        APIHelper.appendUrlWithTemplateParameters(_queryBuilder, _templateParameters);

        //process query parameters
        Map<String, Object> _queryParameters = new HashMap<String, Object>();
        _queryParameters.put("page", page);
        _queryParameters.put("size", size);
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
     * Processes the response for getSubscriptionCycles
     * @return An object of type ListCyclesResponse
     */
    private ListCyclesResponse _handleGetSubscriptionCyclesResponse(HttpContext _context)
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
        ListCyclesResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListCyclesResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    cycleId    Required parameter: Example: 
     * @return    Returns the GetPeriodResponse response from the API call 
     */
    public GetPeriodResponse getSubscriptionCycleById(
                final String subscriptionId,
                final String cycleId
    ) throws Throwable {

        HttpRequest _request = _buildGetSubscriptionCycleByIdRequest(subscriptionId, cycleId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetSubscriptionCycleByIdResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription id
     * @param    cycleId    Required parameter: Example: 
     */
    public void getSubscriptionCycleByIdAsync(
                final String subscriptionId,
                final String cycleId,
                final APICallBack<GetPeriodResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetSubscriptionCycleByIdRequest(subscriptionId, cycleId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetPeriodResponse returnValue = _handleGetSubscriptionCycleByIdResponse(_context);
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
     * Builds the HttpRequest object for getSubscriptionCycleById
     */
    private HttpRequest _buildGetSubscriptionCycleByIdRequest(
                final String subscriptionId,
                final String cycleId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/cycles/{cycleId}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("cycleId", cycleId);
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
     * Processes the response for getSubscriptionCycleById
     * @return An object of type GetPeriodResponse
     */
    private GetPeriodResponse _handleGetSubscriptionCycleByIdResponse(HttpContext _context)
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
        GetPeriodResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetPeriodResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetPeriodResponse response from the API call 
     */
    public GetPeriodResponse renewSubscription(
                final String subscriptionId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildRenewSubscriptionRequest(subscriptionId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleRenewSubscriptionResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: Example: 
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void renewSubscriptionAsync(
                final String subscriptionId,
                final String idempotencyKey,
                final APICallBack<GetPeriodResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildRenewSubscriptionRequest(subscriptionId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetPeriodResponse returnValue = _handleRenewSubscriptionResponse(_context);
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
     * Builds the HttpRequest object for renewSubscription
     */
    private HttpRequest _buildRenewSubscriptionRequest(
                final String subscriptionId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/cycles");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
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
     * Processes the response for renewSubscription
     * @return An object of type GetPeriodResponse
     */
    private GetPeriodResponse _handleRenewSubscriptionResponse(HttpContext _context)
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
        GetPeriodResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetPeriodResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription Id
     * @param    periodId    Required parameter: The period Id
     * @return    Returns the GetUsageReportResponse response from the API call 
     */
    public GetUsageReportResponse getUsageReport(
                final String subscriptionId,
                final String periodId
    ) throws Throwable {

        HttpRequest _request = _buildGetUsageReportRequest(subscriptionId, periodId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetUsageReportResponse(_context);
    }

    /**
     * 
     * @param    subscriptionId    Required parameter: The subscription Id
     * @param    periodId    Required parameter: The period Id
     */
    public void getUsageReportAsync(
                final String subscriptionId,
                final String periodId,
                final APICallBack<GetUsageReportResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetUsageReportRequest(subscriptionId, periodId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetUsageReportResponse returnValue = _handleGetUsageReportResponse(_context);
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
     * Builds the HttpRequest object for getUsageReport
     */
    private HttpRequest _buildGetUsageReportRequest(
                final String subscriptionId,
                final String periodId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/subscriptions/{subscription_id}/periods/{period_id}/usages/report");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("subscription_id", subscriptionId);
        _templateParameters.put("period_id", periodId);
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
     * Processes the response for getUsageReport
     * @return An object of type GetUsageReportResponse
     */
    private GetUsageReportResponse _handleGetUsageReportResponse(HttpContext _context)
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
        GetUsageReportResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetUsageReportResponse>(){});

        return _result;
    }

}
