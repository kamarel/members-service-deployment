package myKcc.com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MembersService {

	@Value("${gateway.security.key}") // Make sure this key is configured in your application properties
	private String gatewaySecurityKey;

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
				.defaultHeader("X-Requested-By", gatewaySecurityKey) // Set the API key in the headers
				.build();
	}



	public static void main(String[] args) {
		SpringApplication.run(MembersService.class, args);
	}

}
