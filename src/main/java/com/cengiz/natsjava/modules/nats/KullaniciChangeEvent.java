package com.cengiz.natsjava.modules.nats;

import lombok.Data;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class KullaniciChangeEvent {
    private String operation;
    private String schema;
    private String table;
    private KullaniciData data;
    private KullaniciData oldData;

    @Data
    @Builder
    public static class KullaniciData {
        private Long id;
        private Long kullaniciAdi;
        private String ad;
        private String soyad;
        private UUID uuid;
        private LocalDateTime kayitZamani;
        private Long kaydedenFkKullaniciId;
    }
}
