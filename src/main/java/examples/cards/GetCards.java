package examples.cards;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.GetCardResponse;
import com.mundipagg.api.models.ListCardsResponse;

public class GetCards {
    
    public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        String customerId = "cus_G6gwy4xtJIOyNbrK";

        client.getCustomers().getCardsAsync(customerId, 1, 30, new APICallBack<ListCardsResponse>() {

            @Override
            public void onSuccess(HttpContext context, ListCardsResponse response) {
                System.out.println("Cards found !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());

                response.getData().isEmpty();

                if(response.getData().isEmpty()) {
                    System.out.println("My wallet is empty");
                } else {
                    for (GetCardResponse cardItem : response.getData()) {
                        System.out.println("Card ID: " + cardItem.getId());
                    }
                    System.out.println("Cards quantity in my wallet: " + response.getPaging().getTotal());
                }
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
