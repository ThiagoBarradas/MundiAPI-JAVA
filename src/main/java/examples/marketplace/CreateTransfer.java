package examples.marketplace;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.RecipientsController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;

public class CreateTransfer {

    public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        RecipientsController recipients_controller = client.getRecipients();

        CreateTransferRequest request = new CreateTransferRequest();

        request.setAmount(100);

        String recipientId = "rp_RElaP4NMCJu08V9m";

        recipients_controller.createTransferAsync(recipientId, request, null, new APICallBack<GetTransferResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetTransferResponse response) {

                System.out.println("Transfer create!");
                System.out.println("Transfer ID: " + response.getId());
                System.out.println("Transfer status: " + response.getStatus());

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
