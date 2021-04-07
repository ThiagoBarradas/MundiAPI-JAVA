package examples.marketplace;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.OrdersController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;

import java.util.*;

public class CreateSplit {

    public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        OrdersController orders_controller = client.getOrders();

        CreateCustomerRequest customer = new CreateCustomerRequest();
        customer.setName("sdk customer order");
        customer.setEmail("tonystark@avengers.com");

        CreateCreditCardPaymentRequest credit_card_payment_request = new CreateCreditCardPaymentRequest();
        credit_card_payment_request.setCapture(true);
        credit_card_payment_request.setInstallments(2);
        credit_card_payment_request.setStatementDescriptor("description");

        credit_card_payment_request.setCard(new CreateCardRequest());
        credit_card_payment_request.getCard().setNumber("4000000000000010");
        credit_card_payment_request.getCard().setHolderName("Tony Stark");
        credit_card_payment_request.getCard().setExpMonth(1);
        credit_card_payment_request.getCard().setExpYear(2025);
        credit_card_payment_request.getCard().setCvv("123");

        CreateOrderRequest request = new CreateOrderRequest();

        CreateOrderItemRequest orderItem = new CreateOrderItemRequest();
        orderItem.setDescription("Tesseract Bracelet");
        orderItem.setQuantity(1);
        orderItem.setAmount(200000);
        ArrayList<CreateOrderItemRequest> listOrderItem = new ArrayList<CreateOrderItemRequest>();
        listOrderItem.add(orderItem);
        request.setItems(listOrderItem);

        CreatePaymentRequest paymentItem = new CreatePaymentRequest();
        paymentItem.setPaymentMethod("credit_card");
        paymentItem.setCreditCard(credit_card_payment_request);
        request.setCustomer(customer);
        ArrayList<CreatePaymentRequest> lisPaymentItem = new ArrayList<CreatePaymentRequest>();
        lisPaymentItem.add(paymentItem);
        request.setPayments(lisPaymentItem);

        CreateSplitRequest splitItemOne = new CreateSplitRequest();
        splitItemOne.setRecipientId("rp_L4kwWE5FDuNdXYQP");
        splitItemOne.setAmount(100000);
        splitItemOne.setType("flat");
        CreateSplitRequest splitItemTwo = new CreateSplitRequest();
        splitItemTwo.setRecipientId("rp_4jl0ra2h3bI8VBvR");
        splitItemTwo.setAmount(100000);
        splitItemTwo.setType("flat");


        List<CreateSplitRequest> listSplit = new ArrayList<CreateSplitRequest>();
        listSplit.add(splitItemOne);
        listSplit.add(splitItemTwo);
        paymentItem.setSplit(listSplit);

        orders_controller.createOrderAsync(request, null, new APICallBack<GetOrderResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetOrderResponse response) {
                System.out.println("Split create!");
                System.out.println("Order ID: " + response.getId());

                for (GetChargeResponse chargeCreate : response.getCharges()) {
                    System.out.println("Charge ID: " + chargeCreate.getId());
                }

                System.out.println("Order Status: " + response.getStatus());

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
