package examples.order;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.OrdersController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.GetOrderResponse;

public class GetOrderById {
	
	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        String orderId = "or_9j8m1E4f6HonwYA0";

        OrdersController orders_controller = client.getOrders();

        orders_controller.getOrderAsync(orderId, new APICallBack<GetOrderResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetOrderResponse response) {

                System.out.println("Order found!");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Order ID: " + response.getId());

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
