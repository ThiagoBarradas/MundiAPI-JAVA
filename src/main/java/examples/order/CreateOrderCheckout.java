package examples.order;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.OrdersController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;
import org.joda.time.DateTime;

import java.util.*;

public class CreateOrderCheckout {

    public static void main(String[] args) {

        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        OrdersController orders_controller = client.getOrders();


        CreateCustomerRequest create_customer_request = new CreateCustomerRequest();
        create_customer_request.setName("Tony Stark");
        create_customer_request.setEmail("tonystark@avengers.com");


        CreateCheckoutPaymentRequest create_checkout_payment_request = new CreateCheckoutPaymentRequest();
        create_checkout_payment_request.setCustomerEditable(false);
        create_checkout_payment_request.setSkipCheckoutSuccessPage(true);
        List<String> acceptedPaymentMethods = new ArrayList<String>();
        acceptedPaymentMethods.add("credit_card");
        acceptedPaymentMethods.add("boleto");
        acceptedPaymentMethods.add("bank_transfer");
        acceptedPaymentMethods.add("debit_card");
        create_checkout_payment_request.setAcceptedPaymentMethods(acceptedPaymentMethods);


        ArrayList<ArrayList<String>> multiPayments = new ArrayList<ArrayList<String>>();
        ArrayList<String> paymentOne = new ArrayList<String>();
        paymentOne.add("credit_card");
        paymentOne.add("credit_card");
        multiPayments.add(paymentOne);
        ArrayList<String> paymentTwo = new ArrayList<String>();
        paymentTwo.add("credit_card");
        paymentTwo.add("boleto");
        multiPayments.add(paymentTwo);
        //create_checkout_payment_request.setAcceptedMultiPaymentMethods(Collections.singletonList(multiPayments));

        create_checkout_payment_request.setSuccessUrl("https://www.mundipagg.com'");

        create_checkout_payment_request.setBankTransfer(new CreateCheckoutBankTransferRequest());
        List<String> banksTransfer = new ArrayList<String>();
        banksTransfer.add("237");
        banksTransfer.add("001");
        banksTransfer.add("341");
        create_checkout_payment_request.getBankTransfer().setBank(banksTransfer);


        create_checkout_payment_request.setBoleto(new CreateCheckoutBoletoPaymentRequest());
        create_checkout_payment_request.getBoleto().setBank("033");
        create_checkout_payment_request.getBoleto().setInstructions("Pagar ate o vencimento");
        create_checkout_payment_request.getBoleto().setDueAt(DateTime.parse("2021-07-25T00:00:00Z"));


        create_checkout_payment_request.setCreditCard(new CreateCheckoutCreditCardPaymentRequest());
        create_checkout_payment_request.getCreditCard().setStatementDescriptor("Descriptor example");


        CreateCheckoutCardInstallmentOptionRequest create_checkout_card_installment_option_request_one = new CreateCheckoutCardInstallmentOptionRequest();
        create_checkout_card_installment_option_request_one.setNumber(1);
        create_checkout_card_installment_option_request_one.setTotal(2000);
        CreateCheckoutCardInstallmentOptionRequest create_checkout_card_installment_option_request_two = new CreateCheckoutCardInstallmentOptionRequest();
        create_checkout_card_installment_option_request_two.setNumber(1);
        create_checkout_card_installment_option_request_two.setTotal(2500);
        List<CreateCheckoutCardInstallmentOptionRequest> listInstallment = new ArrayList<CreateCheckoutCardInstallmentOptionRequest>();
        listInstallment.add(create_checkout_card_installment_option_request_one);
        listInstallment.add(create_checkout_card_installment_option_request_two);
        create_checkout_payment_request.getCreditCard().setInstallments(listInstallment);


        create_checkout_payment_request.setDebitCard(new CreateCheckoutDebitCardPaymentRequest());
        create_checkout_payment_request.getDebitCard().setAuthentication(new CreatePaymentAuthenticationRequest());
        create_checkout_payment_request.getDebitCard().getAuthentication().setType("threed_secure");
        create_checkout_payment_request.getDebitCard().getAuthentication().setThreedSecure(new CreateThreeDSecureRequest());
        create_checkout_payment_request.getDebitCard().getAuthentication().getThreedSecure().setMpi("acquirer");
        create_checkout_payment_request.getDebitCard().getAuthentication().getThreedSecure().setSuccessUrl("https://www.mundipagg.com");

        CreateOrderRequest request = new CreateOrderRequest();
        request.setCode("test-SDK-Java");

        CreateOrderItemRequest create_order_item_request = new CreateOrderItemRequest();
        create_order_item_request.setDescription("Tesseract Bracelet");
        create_order_item_request.setQuantity(1);
        create_order_item_request.setAmount(29090);
        List<CreateOrderItemRequest> listOrder = new ArrayList<CreateOrderItemRequest>();
        listOrder.add(create_order_item_request);
        request.setItems(listOrder);

        CreatePaymentRequest create_payment_request = new CreatePaymentRequest();
        create_payment_request.setAmount(2000);
        create_payment_request.setPaymentMethod("checkout");
        create_payment_request.setCheckout(create_checkout_payment_request);
        List<CreatePaymentRequest> listPaymentItems = new ArrayList<CreatePaymentRequest>();
        listPaymentItems.add(create_payment_request);
        request.setPayments(listPaymentItems);

        request.setCustomer(create_customer_request);


        orders_controller.createOrderAsync(request, null, new APICallBack<GetOrderResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetOrderResponse response) {

                System.out.println("Order checkout create !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Order result status: " + response.getStatus());
                System.out.println("Order id: " + response.getId());

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
