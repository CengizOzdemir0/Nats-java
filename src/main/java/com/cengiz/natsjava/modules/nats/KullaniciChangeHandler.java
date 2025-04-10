package com.cengiz.natsjava.modules.nats;

import com.cengiz.natsjava.config.domain.BeanUtil;
import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
@Slf4j
@RequiredArgsConstructor
public class KullaniciChangeHandler {

    private final Connection natsConnection;

    @PostConstruct
    public void subscribe() {
        Dispatcher dispatcher = natsConnection.createDispatcher((msg) -> {
            String change = new String(msg.getData());
            processChange(change);
        });

        dispatcher.subscribe("kullanici.changes");
        log.info("Subscribed to kullanici.changes");
    }

    private void processChange(String changeJson) {
        try {
            ObjectMapper mapper = BeanUtil.getBean(ObjectMapper.class);
            KullaniciChangeEvent event = mapper.readValue(changeJson, KullaniciChangeEvent.class);

            switch (event.getOperation()) {
                case "INSERT":
                    handleInsert(event.getData());
                    break;
                case "UPDATE":
                    handleUpdate(event.getData());
                    break;
                case "DELETE":
                    handleDelete(event.getOldData());
                    break;
            }
        } catch (Exception e) {
            log.error("Error processing kullanici change", e);
        }
    }

    private void handleInsert(KullaniciChangeEvent.KullaniciData data) {
        log.info("Yeni kullanıcı eklendi: {}", data);
        // İşlemler...
    }

    private void handleUpdate(KullaniciChangeEvent.KullaniciData data) {
        log.info("Kullanıcı güncellendi: {}", data);
        // İşlemler...
    }

    private void handleDelete(KullaniciChangeEvent.KullaniciData data) {
        log.info("Kullanıcı silindi: {}", data);
        // İşlemler...
    }
}
