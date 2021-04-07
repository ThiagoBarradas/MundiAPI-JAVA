package examples.cards;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;

public class CreateCard {

    public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        String customerId = "cus_YL6zwglSxhg2X14g";

        CreateCardRequest request = new CreateCardRequest();

        request.setNumber("4000000000000010");
        request.setHolderName("Tony Starkk");
        request.setHolderDocument("93095135273");
        request.setExpMonth(1);
        request.setExpYear(25);
        request.setCvv("351");

        //Brand is Optional field
        request.setBrand("Mastercard");
        request.setPrivateLabel(false);

        //Billing Address
        request.setBillingAddress(new CreateAddressRequest());
        request.getBillingAddress().setLine1("10880, Malibu Point, Malibu Central");
        request.getBillingAddress().setLine2("7º floor");
        request.getBillingAddress().setZipCode("90265");
        request.getBillingAddress().setCity("Malibu");
        request.getBillingAddress().setState("CA");
        request.getBillingAddress().setCountry("US");
        
        //Card Options: Verify OneDollarAuth
        request.setOptions(new CreateCardOptionsRequest());
        request.getOptions().setVerifyCard(true);

        client.getCustomers().createCardAsync(customerId, request, null, new APICallBack<GetCardResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetCardResponse response) {
                System.out.println("Card create !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Card ID: " + response.getId());

            }

            @Override
            public void onFailure(HttpContext context, Throwable error) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println(error.getMessage());
                error.printStackTrace();

            }
        });
    }
}
