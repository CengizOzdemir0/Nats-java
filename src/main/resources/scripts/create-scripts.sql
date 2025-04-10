create schema kys;
CREATE TABLE kys.kullanici (
                               id int8 NOT NULL,
                               kullanici_adi int8 NOT NULL,
                               ad varchar NOT NULL,
                               soyad varchar NULL,
                               uuid uuid NOT NULL,
                               kayit_zamani timestamp(6) NOT NULL DEFAULT now(),
                               kaydeden_fk_kullanici_id int8 NULL,
                               CONSTRAINT kullanici_adi_unq UNIQUE (kullanici_adi),
                               CONSTRAINT kullanici_pkey PRIMARY KEY (id)
);



-------------------------------------------------------------------------------------------------------------------

CREATE TABLE kys.kullanici_iletisim (
                                        id int4 NOT NULL,
                                        fk_kullanici_id int8 NOT NULL,
                                        cep_telefonu int8 NOT NULL,
                                        aktif bool NOT NULL,
                                        kayit_zamani timestamp(6) NOT NULL DEFAULT now(),
                                        kaydeden_fk_kullanici_id int8 NOT NULL,
                                        CONSTRAINT kullanici_iletisim_pkey PRIMARY KEY (id)
);

-------------------------------------------------------------------------------------------------------------------


CREATE TABLE kys.kullanici_parola (
                                      id int4 NOT NULL,
                                      fk_kullanici_rol_id int8 NOT NULL,
                                      parola varchar NOT NULL,
                                      aktif bool NOT NULL,
                                      kayit_zamani timestamp(6) NOT NULL DEFAULT now(),
                                      kaydeden_fk_kullanici_rol_id int8 NOT NULL,
                                      CONSTRAINT kullanici_parola_pkey PRIMARY KEY (id)
);

-------------------------------------------------------------------------------------------------------------------

CREATE TABLE kys.kullanici_rol (
                                   id int8 NOT NULL,
                                   fk_kullanici_id int8 NOT NULL,
                                   l_rol int4 NOT NULL,
                                   aktif bool NOT NULL DEFAULT true, -
                                   kayit_zamani timestamp(6) NOT NULL DEFAULT now(),
                                   kaydeden_fk_kullanici_rol_id int8 NOT NULL,
                                   CONSTRAINT kullanici_rol_pkey PRIMARY KEY (id)
);
CREATE UNIQUE INDEX kullanici_rol_fk_kullanici_id_idx ON kys.kullanici_rol USING btree (fk_kullanici_id, l_rol);

-------------------------------------------------------------------------------------------------------------------
CREATE TABLE kys.kullanici_yetki (
                                     id int4 NOT NULL ,
                                     fk_kullanici_id int8 NOT NULL,
                                     fk_yetki_id int4 NOT NULL,
                                     aktif bool NOT NULL,
                                     kayit_zamani timestamp(6) NOT NULL DEFAULT now(),
                                     kaydeden_fk_kullanici_rol_id int8 NOT NULL,
                                     CONSTRAINT kullanici_yetki_pkey PRIMARY KEY (id)
);

-------------------------------------------------------------------------------------------------------------------

CREATE TABLE kys.rol_yetki (
                               id int4 NOT NULL ,
                               l_rol int4 NOT NULL,
                               fk_yetki_id int4 NOT NULL,
                               aktif bool NOT NULL,
                               kayit_zamani timestamp(6) NOT NULL DEFAULT now(),
                               kaydeden_fk_kullanici_rol_id int8 NOT NULL,
                               CONSTRAINT rol_yetki_pkey PRIMARY KEY (id)
);

-------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------
CREATE TABLE kys.yetki (
                           id int4 NOT NULL ,
                           yetki_adi varchar NOT NULL,
                           aciklama varchar NOT NULL,
                           aktif bool NOT NULL,
                           kayit_zamani timestamp(6) NOT NULL DEFAULT now(),
                           kaydeden_fk_kullanici_rol_id int4 NOT NULL,
                           CONSTRAINT yetki_pkey PRIMARY KEY (id)
);

-------------------------------------------------------
create schema genel;
CREATE TABLE genel.mesaj (
                             id BIGSERIAL PRIMARY KEY,
                             l_http_status INTEGER,
                             adi VARCHAR(150),
                             kodu VARCHAR(50),
                             mesaj VARCHAR(1000),
                             l_seviye_tipi INTEGER,
                             aktif BOOLEAN,
                             kayit_zamani TIMESTAMP,
                             kaydeden_fk_kullanici_rol_id BIGINT
);

CREATE TABLE genel.parametre (
                                 id SERIAL PRIMARY KEY,
                                 l_veri_tipi INTEGER,
                                 adi VARCHAR(255),
                                 deger VARCHAR(255),
                                 aciklama VARCHAR(500),
                                 aktif BOOLEAN,
                                 kayit_zamani TIMESTAMP,
                                 kaydeden_fk_kullanici_rol_id BIGINT
);

