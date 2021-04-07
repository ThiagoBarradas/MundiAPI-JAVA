package examples.customer;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.CustomersController;
import com.mundipagg.api.http.client.APICallBack;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.models.GetCustomerResponse;

public class GetCustomerById {
	
	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        String customerId = "cus_YL6zwglSxhg2X14g";

        CustomersController customers_controller = client.getCustomers();

        customers_controller.getCustomerAsync(customerId, new APICallBack<GetCustomerResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetCustomerResponse response) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Customer found!");
                System.out.println("Customer ID: " + response.getId());

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
