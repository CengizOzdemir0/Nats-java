package com.cengiz.natsjava.modules.nats;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamApiException;
import io.nats.client.api.RetentionPolicy;
import io.nats.client.api.StorageType;
import io.nats.client.api.StreamConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Statement;
import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class KullaniciChangeListener {

    private final DataSource dataSource;
    private final Connection natsConnection;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void startListening() {
        new Thread(() -> {
            try {
                java.sql.Connection sqlConn = dataSource.getConnection();
                PGConnection pgConn = sqlConn.unwrap(PGConnection.class);

                Statement stmt = sqlConn.createStatement();
                stmt.execute("LISTEN kullanici_changes");

                log.info("Started listening for kullanici changes...");

                while (true) {
                    try {
                        PGNotification[] notifications = pgConn.getNotifications();
                        if (notifications != null) {
                            for (PGNotification notification : notifications) {
                                publishToNats(notification.getParameter());
                            }
                        }
                        Thread.sleep(500);
                    } catch (Exception e) {
                        log.error("Error processing notifications", e);
                    }
                }
            } catch (Exception e) {
                log.error("Database listener error", e);
            }
        }).start();
    }

    private void publishToNats(String change) {
        try {
            natsConnection.publish("kullanici.changes", change.getBytes());
            log.debug("Published kullanici change to NATS: {}", change);
        } catch (Exception e) {
            log.error("Error publishing to NATS", e);
        }
    }


}



