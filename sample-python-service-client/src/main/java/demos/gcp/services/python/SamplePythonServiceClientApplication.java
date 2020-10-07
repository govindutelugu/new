package demos.gcp.services.python;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.google.gson.Gson;

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

	@GetMapping("/sample-response")
	public String fetchResponse() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return restTemplate.exchange(
				"https://sample-python-service-dot-cloudfunctionsdemo-291704.uc.r.appspot.com/sample-response",
				HttpMethod.GET, entity, String.class).getBody();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		restTemplate = new RestTemplate();
		return restTemplate;
	}
}
