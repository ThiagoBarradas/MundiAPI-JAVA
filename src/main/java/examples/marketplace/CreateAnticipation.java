package examples.marketplace;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.RecipientsController;
import com.mundipagg.api.models.CreateAnticipationRequest;
import com.mundipagg.api.models.GetAnticipationResponse;
import com.mundipagg.api.http.client.*;
import org.joda.time.DateTime;

public class CreateAnticipation {
    
    public static void main(String[] args) {
        
        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        RecipientsController recipients_controller = client.getRecipients();

        CreateAnticipationRequest request = new CreateAnticipationRequest();

        request.setAmount(100);
        request.setTimeframe("start");
        request.setPaymentDate(DateTime.parse("2019-08-21"));

        String recipientId = "rp_RElaP4NMCJu08V9m";

        recipients_controller.createAnticipationAsync(recipientId, request, null, new APICallBack<GetAnticipationResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetAnticipationResponse response) {
                System.out.println("Anticipation create!");
                System.out.println("Anticipation ID: " + response.getId());
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
