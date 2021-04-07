package examples.subscription;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.SubscriptionsController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;

public class UpdateSubscription {
	
	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        SubscriptionsController subscriptions_controller = client.getSubscriptions();

        String subscriptionId = "sub_3l4xYr7uliQkAqmz";

        UpdateSubscriptionCardRequest request = new UpdateSubscriptionCardRequest();

        request.setCard(new CreateCardRequest());
        request.getCard().setNumber("4532912167490007");
        request.getCard().setHolderName("Tony Stark");
        request.getCard().setExpMonth(1);
        request.getCard().setExpYear(2028);
        request.getCard().setCvv("123");

        request.getCard().setBillingAddress(new CreateAddressRequest());
        request.getCard().getBillingAddress().setLine1("375  Av. General Justo  Centro");
        request.getCard().getBillingAddress().setLine2("8º andar");
        request.getCard().getBillingAddress().setZipCode("20021130");
        request.getCard().getBillingAddress().setCity("Rio de Janeiro");
        request.getCard().getBillingAddress().setState("RJ");
        request.getCard().getBillingAddress().setCountry("BR");

        subscriptions_controller.updateSubscriptionCardAsync(subscriptionId, request, null, new APICallBack<GetSubscriptionResponse>() {
            public void onSuccess(HttpContext context, GetSubscriptionResponse response) {
                System.out.println("Card updated !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Card id: " + response.getCard().getId());
                System.out.println("First six digits: " + response.getCard().getFirstSixDigits());
                System.out.println("Last four digits: " + response.getCard().getLastFourDigits());
            }

            public void onFailure(HttpContext context, Throwable error) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println(error.getMessage());
                error.printStackTrace();

            }
        });
    }

}
