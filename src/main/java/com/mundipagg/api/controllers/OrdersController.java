package com.mundipagg.api.controllers;

import java.io.*;
import java.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.joda.time.DateTime;

import com.mundipagg.api.*;
import com.mundipagg.api.models.*;
import com.mundipagg.api.exceptions.*;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.http.request.HttpRequest;
import com.mundipagg.api.http.response.HttpResponse;
import com.mundipagg.api.http.response.HttpStringResponse;
import com.mundipagg.api.http.client.APICallBack;

public class OrdersController extends BaseController {

    private Configuration Configuration; 

    public OrdersController(Configuration configuration) {
        this.Configuration = configuration;
    }
    
    /**
     * Gets an order
     * @param    orderId    Required parameter: Order id
     * @return    Returns the GetOrderResponse response from the API call 
     */
    public GetOrderResponse getOrder(
                final String orderId
    ) throws Throwable {

        HttpRequest _request = _buildGetOrderRequest(orderId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetOrderResponse(_context);
    }

    /**
     * Gets an order
     * @param    orderId    Required parameter: Order id
     */
    public void getOrderAsync(
                final String orderId,
                final APICallBack<GetOrderResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetOrderRequest(orderId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderResponse returnValue = _handleGetOrderResponse(_context);
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
     * Builds the HttpRequest object for getOrder
     */
    private HttpRequest _buildGetOrderRequest(
                final String orderId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders/{order_id}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("order_id", orderId);
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
     * Processes the response for getOrder
     * @return An object of type GetOrderResponse
     */
    private GetOrderResponse _handleGetOrderResponse(HttpContext _context)
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
        GetOrderResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderResponse>(){});

        return _result;
    }

    /**
     * Creates a new Order
     * @param    body    Required parameter: Request for creating an order
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetOrderResponse response from the API call 
     */
    public GetOrderResponse createOrder(
                final CreateOrderRequest body,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateOrderRequest(body, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateOrderResponse(_context);
    }

    /**
     * Creates a new Order
     * @param    body    Required parameter: Request for creating an order
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createOrderAsync(
                final CreateOrderRequest body,
                final String idempotencyKey,
                final APICallBack<GetOrderResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateOrderRequest(body, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderResponse returnValue = _handleCreateOrderResponse(_context);
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
     * Builds the HttpRequest object for createOrder
     */
    private HttpRequest _buildCreateOrderRequest(
                final CreateOrderRequest body,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders");
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
     * Processes the response for createOrder
     * @return An object of type GetOrderResponse
     */
    private GetOrderResponse _handleCreateOrderResponse(HttpContext _context)
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
        GetOrderResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderResponse>(){});

        return _result;
    }

    /**
     * Gets all orders
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for order's code
     * @param    status    Optional parameter: Filter for order's status
     * @param    createdSince    Optional parameter: Filter for order's creation date start range
     * @param    createdUntil    Optional parameter: Filter for order's creation date end range
     * @param    customerId    Optional parameter: Filter for order's customer id
     * @return    Returns the ListOrderResponse response from the API call 
     */
    public ListOrderResponse getOrders(
                final Integer page,
                final Integer size,
                final String code,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil,
                final String customerId
    ) throws Throwable {

        HttpRequest _request = _buildGetOrdersRequest(page, size, code, status, createdSince, createdUntil, customerId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetOrdersResponse(_context);
    }

    /**
     * Gets all orders
     * @param    page    Optional parameter: Page number
     * @param    size    Optional parameter: Page size
     * @param    code    Optional parameter: Filter for order's code
     * @param    status    Optional parameter: Filter for order's status
     * @param    createdSince    Optional parameter: Filter for order's creation date start range
     * @param    createdUntil    Optional parameter: Filter for order's creation date end range
     * @param    customerId    Optional parameter: Filter for order's customer id
     */
    public void getOrdersAsync(
                final Integer page,
                final Integer size,
                final String code,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil,
                final String customerId,
                final APICallBack<ListOrderResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetOrdersRequest(page, size, code, status, createdSince, createdUntil, customerId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            ListOrderResponse returnValue = _handleGetOrdersResponse(_context);
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
     * Builds the HttpRequest object for getOrders
     */
    private HttpRequest _buildGetOrdersRequest(
                final Integer page,
                final Integer size,
                final String code,
                final String status,
                final DateTime createdSince,
                final DateTime createdUntil,
                final String customerId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders");

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
        if (createdSince != null) {
            _queryParameters.put("created_since", DateTimeHelper.toRfc8601DateTime(createdSince));
        }
        if (createdUntil != null) {
            _queryParameters.put("created_until", DateTimeHelper.toRfc8601DateTime(createdUntil));
        }
        if (customerId != null) {
            _queryParameters.put("customer_id", customerId);
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
     * Processes the response for getOrders
     * @return An object of type ListOrderResponse
     */
    private ListOrderResponse _handleGetOrdersResponse(HttpContext _context)
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
        ListOrderResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<ListOrderResponse>(){});

        return _result;
    }

    /**
     * Updates the metadata from an order
     * @param    orderId    Required parameter: The order id
     * @param    request    Required parameter: Request for updating the order metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetOrderResponse response from the API call 
     */
    public GetOrderResponse updateOrderMetadata(
                final String orderId,
                final UpdateMetadataRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateOrderMetadataRequest(orderId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateOrderMetadataResponse(_context);
    }

    /**
     * Updates the metadata from an order
     * @param    orderId    Required parameter: The order id
     * @param    request    Required parameter: Request for updating the order metadata
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateOrderMetadataAsync(
                final String orderId,
                final UpdateMetadataRequest request,
                final String idempotencyKey,
                final APICallBack<GetOrderResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateOrderMetadataRequest(orderId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderResponse returnValue = _handleUpdateOrderMetadataResponse(_context);
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
     * Builds the HttpRequest object for updateOrderMetadata
     */
    private HttpRequest _buildUpdateOrderMetadataRequest(
                final String orderId,
                final UpdateMetadataRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/Orders/{order_id}/metadata");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("order_id", orderId);
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
     * Processes the response for updateOrderMetadata
     * @return An object of type GetOrderResponse
     */
    private GetOrderResponse _handleUpdateOrderMetadataResponse(HttpContext _context)
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
        GetOrderResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetOrderResponse response from the API call 
     */
    public GetOrderResponse deleteAllOrderItems(
                final String orderId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteAllOrderItemsRequest(orderId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteAllOrderItemsResponse(_context);
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteAllOrderItemsAsync(
                final String orderId,
                final String idempotencyKey,
                final APICallBack<GetOrderResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteAllOrderItemsRequest(orderId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderResponse returnValue = _handleDeleteAllOrderItemsResponse(_context);
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
     * Builds the HttpRequest object for deleteAllOrderItems
     */
    private HttpRequest _buildDeleteAllOrderItemsRequest(
                final String orderId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders/{orderId}/items");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("orderId", orderId);
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
     * Processes the response for deleteAllOrderItems
     * @return An object of type GetOrderResponse
     */
    private GetOrderResponse _handleDeleteAllOrderItemsResponse(HttpContext _context)
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
        GetOrderResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    itemId    Required parameter: Item Id
     * @param    request    Required parameter: Item Model
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetOrderItemResponse response from the API call 
     */
    public GetOrderItemResponse updateOrderItem(
                final String orderId,
                final String itemId,
                final UpdateOrderItemRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateOrderItemRequest(orderId, itemId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateOrderItemResponse(_context);
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    itemId    Required parameter: Item Id
     * @param    request    Required parameter: Item Model
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateOrderItemAsync(
                final String orderId,
                final String itemId,
                final UpdateOrderItemRequest request,
                final String idempotencyKey,
                final APICallBack<GetOrderItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateOrderItemRequest(orderId, itemId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderItemResponse returnValue = _handleUpdateOrderItemResponse(_context);
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
     * Builds the HttpRequest object for updateOrderItem
     */
    private HttpRequest _buildUpdateOrderItemRequest(
                final String orderId,
                final String itemId,
                final UpdateOrderItemRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders/{orderId}/items/{itemId}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("orderId", orderId);
        _templateParameters.put("itemId", itemId);
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
     * Processes the response for updateOrderItem
     * @return An object of type GetOrderItemResponse
     */
    private GetOrderItemResponse _handleUpdateOrderItemResponse(HttpContext _context)
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
        GetOrderItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderItemResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    itemId    Required parameter: Item Id
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetOrderItemResponse response from the API call 
     */
    public GetOrderItemResponse deleteOrderItem(
                final String orderId,
                final String itemId,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildDeleteOrderItemRequest(orderId, itemId, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleDeleteOrderItemResponse(_context);
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    itemId    Required parameter: Item Id
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void deleteOrderItemAsync(
                final String orderId,
                final String itemId,
                final String idempotencyKey,
                final APICallBack<GetOrderItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildDeleteOrderItemRequest(orderId, itemId, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderItemResponse returnValue = _handleDeleteOrderItemResponse(_context);
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
     * Builds the HttpRequest object for deleteOrderItem
     */
    private HttpRequest _buildDeleteOrderItemRequest(
                final String orderId,
                final String itemId,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders/{orderId}/items/{itemId}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("orderId", orderId);
        _templateParameters.put("itemId", itemId);
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
     * Processes the response for deleteOrderItem
     * @return An object of type GetOrderItemResponse
     */
    private GetOrderItemResponse _handleDeleteOrderItemResponse(HttpContext _context)
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
        GetOrderItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderItemResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    request    Required parameter: Order Item Model
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetOrderItemResponse response from the API call 
     */
    public GetOrderItemResponse createOrderItem(
                final String orderId,
                final CreateOrderItemRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildCreateOrderItemRequest(orderId, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleCreateOrderItemResponse(_context);
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    request    Required parameter: Order Item Model
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void createOrderItemAsync(
                final String orderId,
                final CreateOrderItemRequest request,
                final String idempotencyKey,
                final APICallBack<GetOrderItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildCreateOrderItemRequest(orderId, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderItemResponse returnValue = _handleCreateOrderItemResponse(_context);
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
     * Builds the HttpRequest object for createOrderItem
     */
    private HttpRequest _buildCreateOrderItemRequest(
                final String orderId,
                final CreateOrderItemRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders/{orderId}/items");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("orderId", orderId);
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
     * Processes the response for createOrderItem
     * @return An object of type GetOrderItemResponse
     */
    private GetOrderItemResponse _handleCreateOrderItemResponse(HttpContext _context)
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
        GetOrderItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderItemResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    itemId    Required parameter: Item Id
     * @return    Returns the GetOrderItemResponse response from the API call 
     */
    public GetOrderItemResponse getOrderItem(
                final String orderId,
                final String itemId
    ) throws Throwable {

        HttpRequest _request = _buildGetOrderItemRequest(orderId, itemId);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleGetOrderItemResponse(_context);
    }

    /**
     * 
     * @param    orderId    Required parameter: Order Id
     * @param    itemId    Required parameter: Item Id
     */
    public void getOrderItemAsync(
                final String orderId,
                final String itemId,
                final APICallBack<GetOrderItemResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildGetOrderItemRequest(orderId, itemId);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderItemResponse returnValue = _handleGetOrderItemResponse(_context);
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
     * Builds the HttpRequest object for getOrderItem
     */
    private HttpRequest _buildGetOrderItemRequest(
                final String orderId,
                final String itemId) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders/{orderId}/items/{itemId}");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("orderId", orderId);
        _templateParameters.put("itemId", itemId);
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
     * Processes the response for getOrderItem
     * @return An object of type GetOrderItemResponse
     */
    private GetOrderItemResponse _handleGetOrderItemResponse(HttpContext _context)
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
        GetOrderItemResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderItemResponse>(){});

        return _result;
    }

    /**
     * 
     * @param    id    Required parameter: Order Id
     * @param    request    Required parameter: Update Order Model
     * @param    idempotencyKey    Optional parameter: Example: 
     * @return    Returns the GetOrderResponse response from the API call 
     */
    public GetOrderResponse updateOrderStatus(
                final String id,
                final UpdateOrderStatusRequest request,
                final String idempotencyKey
    ) throws Throwable {

        HttpRequest _request = _buildUpdateOrderStatusRequest(id, request, idempotencyKey);
        HttpResponse _response = getClientInstance().executeAsString(_request);
        HttpContext _context = new HttpContext(_request, _response);

        return _handleUpdateOrderStatusResponse(_context);
    }

    /**
     * 
     * @param    id    Required parameter: Order Id
     * @param    request    Required parameter: Update Order Model
     * @param    idempotencyKey    Optional parameter: Example: 
     */
    public void updateOrderStatusAsync(
                final String id,
                final UpdateOrderStatusRequest request,
                final String idempotencyKey,
                final APICallBack<GetOrderResponse> callBack
    ) {
        Runnable _responseTask = new Runnable() {
            public void run() {

                HttpRequest _request;
                try {
                    _request = _buildUpdateOrderStatusRequest(id, request, idempotencyKey);
                } catch (Exception e) {
                    callBack.onFailure(null, e);
                    return;
                }

                // Invoke request and get response
                getClientInstance().executeAsStringAsync(_request, new APICallBack<HttpResponse>() {
                    public void onSuccess(HttpContext _context, HttpResponse _response) {
                        try {
                            GetOrderResponse returnValue = _handleUpdateOrderStatusResponse(_context);
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
     * Builds the HttpRequest object for updateOrderStatus
     */
    private HttpRequest _buildUpdateOrderStatusRequest(
                final String id,
                final UpdateOrderStatusRequest request,
                final String idempotencyKey) throws IOException, APIException {
        //the base uri for api requests
        String _baseUri = Configuration.baseUri;

        //prepare query string for API call
        StringBuilder _queryBuilder = new StringBuilder(_baseUri + "/orders/{id}/closed");

        //process template parameters
        Map<String, Object> _templateParameters = new HashMap<String, Object>();
        _templateParameters.put("id", id);
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
     * Processes the response for updateOrderStatus
     * @return An object of type GetOrderResponse
     */
    private GetOrderResponse _handleUpdateOrderStatusResponse(HttpContext _context)
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
        GetOrderResponse _result = APIHelper.deserialize(_responseBody,
                                                        new TypeReference<GetOrderResponse>(){});

        return _result;
    }

}
