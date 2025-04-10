package com.cengiz.natsjava.base.tipler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Mesajlar {
  GNL_KAYIT_BULUNAMADI(HttpStatus.NO_CONTENT, "GNL1005", MesajSeviye.INFO),
  TKN_KULLANICI_BULUNAMADI(HttpStatus.INTERNAL_SERVER_ERROR, "TKN1001", MesajSeviye.ERROR),
  GNL_ISLEM_BASARILI(HttpStatus.OK, "GNL1009", MesajSeviye.INFO),
  LGN_ERISIM_ENGELLENDI(HttpStatus.FORBIDDEN, "LGN1006", MesajSeviye.ERROR),
  LGN_CIKIS_BASARISIZ(HttpStatus.BAD_REQUEST, "LGN1008", MesajSeviye.ERROR),
  LGN_CIKIS_BASARILI(HttpStatus.OK, "LGN1005", MesajSeviye.INFO),
  GNL_BEKLENMEYEN_HATA_OLUSTU(HttpStatus.INTERNAL_SERVER_ERROR, "GNL1000", MesajSeviye.ERROR),
  LGN_CACHE_OKUMA_YAZMA_HATASI(HttpStatus.INTERNAL_SERVER_ERROR, "LGN1007",
      MesajSeviye.ERROR),
  LGN_KULLANICI_ADI_PAROLA_HATALI(HttpStatus.BAD_REQUEST, "LGN1003", MesajSeviye.ERROR),
  LGN_KULLANICI_BULUNAMADI(HttpStatus.BAD_REQUEST, "LGN1002", MesajSeviye.ERROR),
  LGN_GIRIS_BILGILERI_EKSIK(HttpStatus.BAD_REQUEST, "LGN1001", MesajSeviye.ERROR),
  LGN_BASKA_YERDEN_GIRIS_YAPILMIS(HttpStatus.UNAUTHORIZED, "LGN2001",
      MesajSeviye.ERROR),
  KLN_PAROLA_BOS_OLAMAZ(HttpStatus.BAD_REQUEST, "KLN1001", MesajSeviye.ERROR),
  KLN_KULLANICI_ADI_BOS_OLAMAZ(HttpStatus.BAD_REQUEST, "KLN1002", MesajSeviye.ERROR),
  KLN_KULLANICI_ROL_YOK(HttpStatus.BAD_REQUEST, "KLN1003", MesajSeviye.ERROR),
  KLN_KULLANICI_PAROLA_YOK(HttpStatus.BAD_REQUEST, "KLN1004", MesajSeviye.ERROR),
  KLN_KULLANICI_YETKI_YOK(HttpStatus.BAD_REQUEST, "KLN1005", MesajSeviye.ERROR),
  LGN_OTURUM_SONLANMIS_TEKRAR_GIRIS_YAPINIZ(HttpStatus.UNAUTHORIZED, "LGN1004",
      MesajSeviye.ERROR),
  LGN_TOKEN_GECERSIZ(HttpStatus.UNAUTHORIZED, "LGN2000", MesajSeviye.ERROR),
  LGN_GIRIS_ROLU_BULUNMAMAKTADIR(HttpStatus.BAD_REQUEST, "LGN1009", MesajSeviye.ERROR),

  GNL_AYNI_KAYIT_MEVCUT(HttpStatus.BAD_REQUEST, "VLD1000", MesajSeviye.ERROR),
  VTN_KAYIT_BULUNAMADI(HttpStatus.BAD_REQUEST, "GNL1025", MesajSeviye.ERROR),

  YETKISI_YOKTUR(HttpStatus.BAD_REQUEST, "GNL1010", MesajSeviye.ERROR),

  VLD_GECERSIZ_PAYLOAD(HttpStatus.BAD_REQUEST, "VLD1004", MesajSeviye.ERROR),
  VLD_GECERSIZ_KEY(HttpStatus.BAD_REQUEST, "VLD1006", MesajSeviye.ERROR);


  private final HttpStatus httpStatus;
  private final String kodu;
  private final MesajSeviye seviye;

  public static Mesajlar of(String kodu) {
    for (Mesajlar inam : values()) {
      if (inam.kodu.equals(kodu) || inam.name().equals(kodu)) {
        return inam;
      }
    }
    return GNL_BEKLENMEYEN_HATA_OLUSTU;
  }

}