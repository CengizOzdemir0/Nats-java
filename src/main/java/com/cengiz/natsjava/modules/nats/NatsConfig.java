package com.cengiz.natsjava.modules.nats;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class NatsConfig {

    @Value("${nats.url:nats://localhost:4222}")
    private String natsUrl;

    private final ObjectMapper objectMapper; // Mevcut ObjectMapper'ı inject et

    @Bean
    public Connection natsConnection() throws IOException, InterruptedException {
        Options options = new Options.Builder()
                .server(natsUrl)
                .reconnectWait(Duration.ofSeconds(5))
                .maxReconnects(-1)
                .build();

        return Nats.connect(options);
    }

    // ObjectMapper bean'ini kaldır, constructor injection kullan
    // Gerekli modülleri PostConstruct ile ekle
    @PostConstruct
    public void configureObjectMapper() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
