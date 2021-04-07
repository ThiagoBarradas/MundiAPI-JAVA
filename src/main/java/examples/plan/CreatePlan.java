package examples.plan;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.PlansController;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;

import java.util.*;

public class CreatePlan {

    public static void main(String[] args) {
        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        PlansController plans_controller = client.getPlans();

        CreatePlanRequest request = new CreatePlanRequest();

        request.setName("Plano Gold");
        request.setCurrency("BRL");
        request.setInterval("month");
        request.setIntervalCount(3);
        request.setBillingType("prepaid");
        request.setMinimumPrice(10000);


        int[] numberOfInstallments = {3};
        List<Integer> listInstallments = new ArrayList<>(numberOfInstallments.length);
        for (int installments : numberOfInstallments) {
            listInstallments.add(installments);
        }
        request.setInstallments(listInstallments);


        String[] typesOfPaymentMethods = {"credit_card", "boleto"};
        List<String> listPaymentMethods = new ArrayList<>(typesOfPaymentMethods.length);
        listPaymentMethods.addAll(Arrays.asList(typesOfPaymentMethods));
        request.setPaymentMethods(listPaymentMethods);


        CreatePlanItemRequest planItemOne = new CreatePlanItemRequest();
        planItemOne.setName("Musculação");
        planItemOne.setQuantity(1);
        planItemOne.setPricingScheme(new CreatePricingSchemeRequest());
        planItemOne.getPricingScheme().setPrice(18990);
        
        CreatePlanItemRequest planItemTwo = new CreatePlanItemRequest();
        planItemTwo.setName("Matrícula");
        planItemTwo.setCycles(1);
        planItemTwo.setQuantity(1);
        planItemTwo.setPricingScheme(new CreatePricingSchemeRequest());
        planItemTwo.getPricingScheme().setPrice(18990);
        
        List<CreatePlanItemRequest> listPlan = new ArrayList<CreatePlanItemRequest>();
        listPlan.add(planItemOne);
        listPlan.add(planItemTwo);
        request.setItems(listPlan);
        



        plans_controller.createPlanAsync(request, null, new APICallBack<GetPlanResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetPlanResponse response) {

                System.out.println("Plan create !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Plan result status: " + response.getStatus());
                System.out.println("Plan id: " + response.getId());
                System.out.println("Type plan: " + response.getBillingType());

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
