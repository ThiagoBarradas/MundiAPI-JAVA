package com.mundipagg.api.controllers.syncwrapper;

import com.mundipagg.api.http.client.APICallBack;
import com.mundipagg.api.http.client.HttpContext;

/**
 * An APICallBack that captures the HTTP response and can be waited on
 *
 * An instance of this class can be passed to an API call as the APICallBack
 * instance to capture the results of that call. Call await() to block until
 * completion of the API call. Trying to get results of API call before 
 * calling wait() will also block.
 *  
 * @param <T> Type of the response object
 */
public class APICallBackCatcher<T> extends SynchronousBase implements APICallBack<T> {
    
    private T result = null;
    private Throwable error = null;
    private boolean success = false;

    /**
     * Get the result from the API callback.
     * Blocks if API call is not complete yet.
     * @return
     * @throws Throwable 
     */
    public T getResult() throws Throwable {
        await();
        return result;
    }

    /**
     * Set API callback result
     * @param response
     */
    private void setResult(T response) {
        this.result = response;
    }

    /**
     * Get the exception object thrown by this API callback if any.
     * Blocks if API call is not complete yet.
     * @return
     * @throws InterruptedException 
     */
    public Throwable getError() throws InterruptedException {
        await();
        return error;
    }

    /**
     * Set the exception object.
     * @param error
     */
    private void setError(Throwable error) {
        this.error = error;
    }

    /**
     * Was the API call successful?
     * Blocks if API call is not complete yet.
     * @return
     * @throws InterruptedException 
     */
    public boolean isSuccess() throws InterruptedException {
        await();
        return success;
    }

    /**
     * Set the success for API call.
     * @param success
     */
    private void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * On Success handler for APICallBack.
     */
    public void onSuccess(HttpContext context, T response) {
        setResult(response);
        setSuccess(true);
        setError(null);
        markAsDone();
    }

    /**
     * OnFailure handler for APICallBack.
     */
    public void onFailure(HttpContext responseContext, Throwable error) {
        setResult(null);
        setSuccess(false);
        setError(error);
        markAsDone();
    }
}

