package examples.order;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.OrdersController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;
import org.joda.time.DateTime;

import java.util.ArrayList;

public class CreateOrderBankSlip {
	
	public static void main(String[] args) {
		
        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        OrdersController orders_controller = client.getOrders();

        CreateCustomerRequest customer = new CreateCustomerRequest();
        customer.setName("sdk customer order");
        customer.setEmail("tonystark@avengers.com");
        customer.setAddress(new CreateAddressRequest());
        customer.getAddress().setStreet("Malibu Point");
        customer.getAddress().setNumber("10880");
        customer.getAddress().setZipCode("90265");
        customer.getAddress().setNeighborhood("Central Malibu");
        customer.getAddress().setCity("Malibu");
        customer.getAddress().setState("CA");
        customer.getAddress().setCountry("US");

        CreateBoletoPaymentRequest create_boleto_payment_request = new CreateBoletoPaymentRequest();
        create_boleto_payment_request.setBank("033");
        create_boleto_payment_request.setInstructions("Pagar até o vencimento");
        create_boleto_payment_request.setDueAt(DateTime.parse("2019-12-31T00:00:00Z"));

        CreateOrderRequest request = new CreateOrderRequest();

        CreateOrderItemRequest orderItem = new CreateOrderItemRequest();
        orderItem.setDescription("Tesseract Bracelet");
        orderItem.setQuantity(3);
        orderItem.setAmount(1490);
        ArrayList<CreateOrderItemRequest> listOrderItem = new ArrayList<CreateOrderItemRequest>();
        listOrderItem.add(orderItem);
        request.setItems(listOrderItem);

        CreatePaymentRequest paymentItem = new CreatePaymentRequest();
        paymentItem.setPaymentMethod("boleto");
        paymentItem.setBoleto(create_boleto_payment_request);
        request.setCustomer(customer);
        ArrayList<CreatePaymentRequest> lisPaymentItem = new ArrayList<CreatePaymentRequest>();
        lisPaymentItem.add(paymentItem);
        request.setPayments(lisPaymentItem);


        orders_controller.createOrderAsync(request, null, new APICallBack<GetOrderResponse>() {
            public void onSuccess(HttpContext context, GetOrderResponse response) {

                System.out.println("Order bank slip create !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Order result status: " + response.getStatus());
                System.out.println("Order id: " + response.getId());

            }

            public void onFailure(HttpContext context, Throwable error) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println(error.getMessage());
                error.printStackTrace();
            }
        });
    }

}
