package examples.invoice;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.InvoicesController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.GetInvoiceResponse;

public class GetInvoiceById {

    public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        String invoiceId = "in_DKRdGqpsaVS4rmpl";

        InvoicesController invoices_controller = client.getInvoices();

        invoices_controller.getInvoiceAsync(invoiceId, new APICallBack<GetInvoiceResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetInvoiceResponse response) {

                System.out.println("Invoice found!");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
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
