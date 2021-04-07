package examples.subscription;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.SubscriptionsController;
import com.mundipagg.api.http.client.APICallBack;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.models.*;

import java.util.*;

public class CreateSubscriptionPrePaidBankSlip {
	
	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        SubscriptionsController subscriptions_controller = client.getSubscriptions();

        CreateSubscriptionRequest request = new CreateSubscriptionRequest();

        request.setPaymentMethod("boleto");
        request.setCurrency("BRL");
        request.setInterval("month");
        request.setIntervalCount(3);
        request.setBillingType("prepaid");
        request.setInstallments(3);
        request.setGatewayAffiliationId("8a2dd57f-1ed9-4153-b4ce-69683efadad5");
        request.setMinimumPrice(10000);
        request.setBoletoDueDays(5);

        request.setCustomer(new CreateCustomerRequest());
        request.getCustomer().setName("Tony Stark'");
        request.getCustomer().setEmail("tonystark@avengers.com");

        CreateDiscountRequest discountRequest = new CreateDiscountRequest();
        discountRequest.setCycles(3);
        discountRequest.setValue(10);
        discountRequest.setDiscountType("percentage");
        ArrayList<CreateDiscountRequest> listDiscountItem =  new ArrayList<CreateDiscountRequest>();
        listDiscountItem.add(discountRequest);
        request.setDiscounts(listDiscountItem);

        CreateIncrementRequest incrementRequest = new CreateIncrementRequest();
        incrementRequest.setCycles(2);
        incrementRequest.setValue(20);
        ArrayList<CreateIncrementRequest> listIncrementItem = new ArrayList<CreateIncrementRequest>();
        listIncrementItem.add(incrementRequest);
        request.setIncrements(listIncrementItem);


        CreateSubscriptionItemRequest create_subscription_item_request_one = new CreateSubscriptionItemRequest();
        create_subscription_item_request_one.setDescription("Musculação");
        create_subscription_item_request_one.setQuantity(1);
        create_subscription_item_request_one.setPricingScheme(new CreatePricingSchemeRequest());
        create_subscription_item_request_one.getPricingScheme().setPrice(18990);
        
        CreateSubscriptionItemRequest create_subscription_item_request_two = new CreateSubscriptionItemRequest();
        create_subscription_item_request_two.setDescription("Matrícula");
        create_subscription_item_request_two.setQuantity(1);
        create_subscription_item_request_two.setCycles(1);
        create_subscription_item_request_two.setPricingScheme(new CreatePricingSchemeRequest());
        create_subscription_item_request_two.getPricingScheme().setPrice(5990);
        
        List<CreateSubscriptionItemRequest> listItem = new ArrayList<CreateSubscriptionItemRequest>();
        listItem.add(create_subscription_item_request_one);
        listItem.add(create_subscription_item_request_two);

        request.setItems(listItem);

        subscriptions_controller.createSubscriptionAsync(request, null, new APICallBack<GetSubscriptionResponse>() {
            public void onSuccess(HttpContext context, GetSubscriptionResponse response) {

                System.out.println("Subscription pre paid bank slip !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Subscription id: " + response.getId());
                System.out.println("Subscription status: " + response.getStatus());
                System.out.println("Subscription interval: " + response.getInterval());
                System.out.println("Subscription boleto duedays: " + response.getBoletoDueDays());
                System.out.println("Current cycle: " + response.getCurrentCycle().getId());
                System.out.println("Current cycle Start_At: " + response.getCurrentCycle().getStartAt());
                System.out.println("Current cycle End_At:" + response.getCurrentCycle().getEndAt());
                System.out.println("Current cycle Billing At: " + response.getCurrentCycle().getBillingAt());

            }

            public void onFailure(HttpContext context, Throwable error) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println(error.getMessage());
                error.printStackTrace();

            }
        });
    }
}
