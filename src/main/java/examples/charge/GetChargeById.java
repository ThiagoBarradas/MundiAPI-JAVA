package examples.charge;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.ChargesController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.GetChargeResponse;

public class GetChargeById {
	
	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        String chargeId = "ch_Dzk8rbVSynuM80Ad";

        ChargesController charges_controller = client.getCharges();

        charges_controller.getChargeAsync(chargeId, new APICallBack<GetChargeResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetChargeResponse response) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Charge found!");
                System.out.println("Charge ID: " + response.getId());

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
