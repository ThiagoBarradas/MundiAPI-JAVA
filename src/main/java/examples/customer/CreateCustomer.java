package examples.customer;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.http.client.*;
import com.mundipagg.api.models.*;
import com.mundipagg.api.controllers.*;

import java.util.LinkedHashMap;

public class CreateCustomer {
	
	public static void main(String[] args) {

        String basicAuthUserName = "sk_test_EegqODfvktNJn4YA"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication

        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);

        CustomersController customers_controller = client.getCustomers();

        CreateCustomerRequest request = new CreateCustomerRequest();
        request.setName("sdk customer test");
        request.setEmail("tonystark@avengers.com");
        request.setType("individual");
        request.setDocument("55342561094");
        request.setCode("MY_CUSTOMER_001");

        request.setAddress(new CreateAddressRequest());
        request.getAddress().setLine1("375, Av. General Justo, Centro");
        request.getAddress().setLine2("8� andar");
        request.getAddress().setZipCode("20021130");
        request.getAddress().setCity("Rio de Janeiro");
        request.getAddress().setState("RJ");
        request.getAddress().setCountry("BR");
        LinkedHashMap<String, String> metadata = new LinkedHashMap<String, String>();
        metadata.put("id","my_address_id");
        UpdateMetadataRequest updateMetadata = new UpdateMetadataRequest();
        updateMetadata.setMetadata(metadata);
        request.getAddress().setMetadata(updateMetadata.getMetadata());

        request.setPhones(new CreatePhonesRequest());
        request.getPhones().setHomePhone(new CreatePhoneRequest());
        request.getPhones().getHomePhone().setAreaCode("21");
        request.getPhones().getHomePhone().setCountryCode("55");
        request.getPhones().getHomePhone().setNumber("000000000");
        request.getPhones().setMobilePhone(new CreatePhoneRequest());
        request.getPhones().getMobilePhone().setAreaCode("21");
        request.getPhones().getMobilePhone().setCountryCode("55");
        request.getPhones().getMobilePhone().setNumber("000000000");

        customers_controller.createCustomerAsync(request, null, new APICallBack<GetCustomerResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetCustomerResponse response) {

                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Customer create !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
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
