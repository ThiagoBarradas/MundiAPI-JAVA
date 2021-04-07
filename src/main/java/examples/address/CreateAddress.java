package examples.address;

import java.util.LinkedHashMap;

import com.mundipagg.api.MundiAPIClient;
import com.mundipagg.api.http.client.APICallBack;
import com.mundipagg.api.http.client.HttpContext;
import com.mundipagg.api.models.CreateAddressRequest;
import com.mundipagg.api.models.GetAddressResponse;
import com.mundipagg.api.models.UpdateMetadataRequest;

public class CreateAddress {
	
	public static void main(String[] args) {
		
		String basicAuthUserName = "sk_test_q73YODBFQhyV9mod"; // The username to use with basic authentication
        String basicAuthPassword = ""; // The password to use with basic authentication
        
        MundiAPIClient client = new MundiAPIClient(basicAuthUserName, basicAuthPassword);
        
        String customerId = "cus_PzRyp10FeNca2rVB";
                
        CreateAddressRequest request = new CreateAddressRequest();
        
        request.setLine1("10880, Malibu Point, Malibu Central");
        request.setLine2("7� floor");
        request.setZipCode("90265");
        request.setCity("Malibu");
        request.setState("CA");
        request.setCountry("US");
        LinkedHashMap<String, String> metadata = new LinkedHashMap<String, String>();
        metadata.put("id","my_address_id");
        UpdateMetadataRequest updateMetadata = new UpdateMetadataRequest();
        updateMetadata.setMetadata(metadata);
        request.setMetadata(updateMetadata.getMetadata());
        
        client.getCustomers().createAddressAsync(customerId, request, null, new APICallBack<GetAddressResponse>() {
            @Override
            public void onSuccess(HttpContext context, GetAddressResponse response) {

                System.out.println("Address create !");
                System.out.println("Status response: " + context.getResponse().getStatusCode());
                System.out.println("Address id: " + response.getId());

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
