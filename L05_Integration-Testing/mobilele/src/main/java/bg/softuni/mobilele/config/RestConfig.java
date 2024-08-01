package bg.softuni.mobilele.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.awt.*;

@Configuration
public class RestConfig {

    @Bean("genericRestClient")
    public RestClient restClient() {
        return RestClient.create();
    }

    @Bean("offersRestClient")
    public RestClient offersRestClient(OfferApiConfig offerApiConfig) {
        return RestClient
                .builder()
                .baseUrl(offerApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
