package examples.charge;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.ChargesController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.CreateCancelChargeRequest;
import com.mundipagg.api.models.GetChargeResponse;


public class CreateCancelChargePartial {

	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        ChargesController charges_controller = client.getCharges();

        String chargeId = "ch_J6nQVYBf7ZHDEoLj";

        CreateCancelChargeRequest request = new CreateCancelChargeRequest();

        request.setAmount(100);

        charges_controller.cancelChargeAsync(chargeId, request, null, new APICallBack<GetChargeResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetChargeResponse response) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Canceled amount:" + response.getAmount());
                System.out.println("Charge status: " + response.getStatus());
                System.out.println("Last transaction status: " + response.getLastTransaction().getStatus());
                System.out.println("Last transaction amount: " + response.getLastTransaction().getAmount());
                System.out.println("Last transaction id: " + response.getLastTransaction().getId());
                System.out.println("Charge is partial canceled");

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
