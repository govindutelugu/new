package demos.gcp.serviceclients.python;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SamplePythonServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplePythonServiceClientApplication.class, args);
	}

}

@RestController
class PythonServiceClient {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${bypass.api.gateway}")
	private String bypassAPIGateWay;

	private static String baseServiceURL = "https://sample-python-service-dot-cloudfunctionsdemo-291704.uc.r.appspot.com";

	private static String baseApiURL = "https://sample-python-api-gateway-dkpu0pz4.uc.gateway.dev";

	private static String apiKey = "AIzaSyDSzv7mLkg3a1CwMMrglZ3J0t44GoKtj4g";

	private String serviceUrl;

	private String serviceURI = "/sample-response";

	@GetMapping("/sample-response")
	public String fetchResponse() {

		if (bypassAPIGateWay.isEmpty() || !Boolean.getBoolean(bypassAPIGateWay)) {
			serviceUrl = baseApiURL + serviceURI + "?key=" + apiKey;
		} else {
			serviceUrl = baseServiceURL + serviceURI;

		}

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(serviceUrl, HttpMethod.GET, entity, String.class).getBody();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		restTemplate = new RestTemplate();
		return restTemplate;
	}
}
