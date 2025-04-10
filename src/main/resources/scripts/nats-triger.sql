-- Trigger fonksiyonu
CREATE OR REPLACE FUNCTION kys.notify_kullanici_changes()
RETURNS trigger AS $$
DECLARE
notification json;
BEGIN
    IF (TG_OP = 'DELETE') THEN
        notification = json_build_object(
            'operation', TG_OP,
            'schema', TG_TABLE_SCHEMA,
            'table', TG_TABLE_NAME,
            'old_data', json_build_object(
                'id', OLD.id,
                'kullanici_adi', OLD.kullanici_adi,
                'ad', OLD.ad,
                'soyad', OLD.soyad,
                'uuid', OLD.uuid,
                'kayit_zamani', OLD.kayit_zamani,
                'kaydeden_fk_kullanici_id', OLD.kaydeden_fk_kullanici_id
            )
        );
ELSE
        notification = json_build_object(
            'operation', TG_OP,
            'schema', TG_TABLE_SCHEMA,
            'table', TG_TABLE_NAME,
            'data', json_build_object(
                'id', NEW.id,
                'kullanici_adi', NEW.kullanici_adi,
                'ad', NEW.ad,
                'soyad', NEW.soyad,
                'uuid', NEW.uuid,
                'kayit_zamani', NEW.kayit_zamani,
                'kaydeden_fk_kullanici_id', NEW.kaydeden_fk_kullanici_id
            )
        );
END IF;

    PERFORM pg_notify('kullanici_changes', notification::text);
RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Trigger'ı tabloya bağla
CREATE TRIGGER kullanici_change_trigger
    AFTER INSERT OR UPDATE OR DELETE ON kys.kullanici
    FOR EACH ROW EXECUTE FUNCTION kys.notify_kullanici_changes();