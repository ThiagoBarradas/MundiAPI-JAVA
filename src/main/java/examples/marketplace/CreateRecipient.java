package examples.marketplace;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.controllers.RecipientsController;
import com.mundipagg.api.http.client.APICallBack;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.models.CreateBankAccountRequest;
import com.mundipagg.api.models.CreateRecipientRequest;
import com.mundipagg.api.models.GetRecipientResponse;

public class CreateRecipient {
    
    public static void main(String[] args) {
        
        String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        RecipientsController recipients_controller = client.getRecipients();

        CreateRecipientRequest request = new CreateRecipientRequest();

        request.setName("Tony Stark");
        request.setEmail("tstark@mundipagg.com");
        request.setDescription("Recebedor tony stark");
        request.setDocument("26224451990");
        request.setType("individual");


        request.setDefaultBankAccount(new CreateBankAccountRequest());
        request.getDefaultBankAccount().setHolderName("Tony Stark");
        request.getDefaultBankAccount().setHolderType("individual");
        request.getDefaultBankAccount().setHolderDocument("26224451990");
        request.getDefaultBankAccount().setBank("341");
        request.getDefaultBankAccount().setBranchNumber("12345");
        request.getDefaultBankAccount().setBranchCheckDigit("6");
        request.getDefaultBankAccount().setAccountNumber("12345");
        request.getDefaultBankAccount().setAccountCheckDigit("6");
        request.getDefaultBankAccount().setType("checking");

        recipients_controller.createRecipientAsync(request, null, new APICallBack<GetRecipientResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetRecipientResponse response) {

                System.out.println("Recipient create!");
                System.out.println("Recipient Id on mundipagg: " + response.getId());
                System.out.println("Recipient default_bank_account id: " + response.getDefaultBankAccount().getId());

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
