package examples.subscription;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.SubscriptionsController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;

public class CreateCancelSubscription {
	
	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        SubscriptionsController subscriptions_controller = client.getSubscriptions();

        String subscriptionId = "sub_4qK9P5gtaipabMxV";

        CreateCancelSubscriptionRequest request = new CreateCancelSubscriptionRequest();

        request.setCancelPendingInvoices(true);

        subscriptions_controller.cancelSubscriptionAsync(subscriptionId, request, null, new APICallBack<GetSubscriptionResponse>() {
            public void onSuccess(HttpContext context, GetSubscriptionResponse response) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Subscription cancel !");
                System.out.println("Subscription id: " + response.getId());
                System.out.println("Subscription status: " + response.getStatus());

            }

            public void onFailure(HttpContext context, Throwable error) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println(error.getMessage());
                error.printStackTrace();

            }
        });
    }

}
