package br.com.helpDesk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GPTconfig {

    @Value("${API_KEY}")
    private String apikey;

    @Value("${$API_URL}")
    private String url;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Authorization", "Bearer " + apikey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

}
