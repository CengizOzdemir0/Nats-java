INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(1, 204, 'GNL_KAYIT_BULUNAMADI', 'GNL1005', 'Kayıt Bulunamadı', 1, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(2, 500, 'TKN_KULLANICI_BULUNAMADI', 'TKN1001', 'Token kullanıcı bilgisi alınamadı!!! (TKN1001)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(3, 200, 'GNL_ISLEM_BASARILI', 'GNL1009', 'İşlem Başarılı', 1, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(4, 403, 'LGN_ERISIM_ENGELLENDI', 'LGN1006', 'Erişim yetkiniz bulunmamaktadır (LGN1006)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(5, 400, 'LGN_CIKIS_BASARISIZ', 'LGN1008', 'Çıkış işlemi yapılamamıştır. Lütfen öncelikle giriş yapınız. (LGN1008)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(6, 200, 'LGN_CIKIS_BASARILI', 'LGN1005', 'Oturum başarılı olarak sonlandırıldı', 1, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(7, 500, 'GNL_BEKLENMEYEN_HATA_OLUSTU', 'GNL1000', 'Beklenmeyen hata oluştu. Lütfen yazılım ekibi ile irtibata geçiniz. (GNL1000)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(8, 500, 'LGN_CACHE_OKUMA_YAZMA_HATASI', 'LGN1007', 'Cache bağlantı hatası. Lütfen daha sonra tekrar deneyin (LGN1007)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(9, 400, 'LGN_KULLANICI_ADI_PAROLA_HATALI', 'LGN1003', 'Kullanıcı adı ve/veya parola hatalı (LGN1003)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(10, 400, 'LGN_KULLANICI_BULUNAMADI', 'LGN1002', 'Kullanıcı bulunamadı (LGN1002)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(11, 400, 'LGN_GIRIS_BILGILERI_EKSIK', 'LGN1001', '@@version (LGN1001)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(12, 401, 'LGN_BASKA_YERDEN_GIRIS_YAPILMIS', 'LGN2001', 'Başka yerden giriş yaptığınızdan oturum sonlanmıştır. (LGN2001)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(13, 401, 'LGN_OTURUM_SONLANMIS_TEKRAR_GIRIS_YAPINIZ', 'LGN1004', 'Oturumunuz sonlanmış tekrar giriş yapınız (LGN1004)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(14, 401, 'LGN_TOKEN_GECERSIZ', 'LGN2000', 'Oturumunuz Sonlanmıştır. Tekrar giriş yapın. (LGN2000)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(15, 400, 'DOSYA_BASARISIZ', 'GNL1015', 'Dosya işlemi yapılamamıştır. (LGN1019)', 3, true, 'now()', -2);

-----
INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(16, 400, 'MODERATOR_YETKI_DOSYA', 'YTK1020', 'Moderator olmadan dosya yükleyemezsiniz. (YTK1020)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(17, 400, 'KRM_HEKIM_KAYITLI_DEGIL', 'KRM1032', 'Girilen T.C. Kimlik Numarası sistemde hekim veya yetkili olarak kayıtlı değildir.', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(18, 400, 'KATILIMCI_BULUNMADI', 'KTLMCI01', 'Katılımcı Tespit Edilemedi.', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(19, 428, 'GNL_TC_KIMLIK_NO_GECERSIZ', 'VT2016', 'Lütfen Geçerli T.C. Kimlik No Giriniz.', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(20, 400, 'GNL_RULE_HATALI_CALISTI', 'GNL1019', 'Kural hatalı çalıştı.', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(21, 400, 'VLD_CEP_TELEFON_FORMAT', 'VLD1019', 'Lütfen Geçerli Cep Telefon Numarası Giriniz. Örnek(555XXXXXXX)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(22, 403, 'YETKI_INSERT_BASARISIZ', 'YTK1000', 'Bu kişi için yetkilendirme yapamazsınız.', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(23, 403, 'YETKI_INSERT_YETKILI_MEVCUT', 'YTK1001', 'Sadece bir yetkili kişi tanımlayabilirsiniz.', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(24, 400, 'BELGE_SILME_YETKI_KONTROLU', 'BLG1001', 'Yüklemediğiniz belgeyi silemezsiniz!', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(25, 400, 'VTN_HRN_TC_ESLESMESI_HATALI', 'VT10017', 'T.C. Kimlik numarasına ait randevu kaydı bulunamamıştır. Lütfen T.C. kimlik Numarasını kontrol ediniz.(VT10017)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(26, 400, 'GORUSME_NOTU_BULUNAMADI', 'GNOT1000', 'Gorusme notu/Gorusme oda numarası boş olduğu için kayıt eklenemedi', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(27, 400, 'GNL_HASTA_TC_KIMLIK_NO_GECERSIZ', 'VT2006', 'Geçersiz bir Hasta T.C. Kimlik Numarası girdiniz. (VT2006)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(28, 400, 'GNL_AYNI_KAYIT_MEVCUT', 'GNL1039', 'Tabloda aynı şartlara ait kural bulunmaktadır. Lütfen girilen değerleri kontrol ediniz (GNL1039)', 3, true, 'now()', -2);

INSERT INTO genel.mesaj
(id, l_http_status, adi, kodu, mesaj, l_seviye_tipi, aktif, kayit_zamani, kaydeden_fk_kullanici_rol_id)
VALUES(29, 400, 'VTN_KAYIT_BULUNAMADI', 'GNL1025', 'Kayıt Bulunamadı', 3, true, 'now()', -2);
