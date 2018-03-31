-- • Agregar teléfono a usuario.
-- • Entrada: datos del nuevo teléfono.
-- • Usuario autorizado: admin

CREATE OR REPLACE FUNCTION FT_AGREGAR_TEL
(
  ALIASv IN TEXT
, NUMEROv IN TEXT
, DESCRIPCIONv IN TEXT
)
RETURNS void AS
$BODY$
BEGIN
    IF ((SELECT COUNT(*) FROM public."USUARIO" WHERE "ALIAS" = ALIASv) = 1) THEN
      
      INSERT INTO SYS.TELEFONOXUSUARIO (ALIASUSUARIO, NUMERO, DESCRIPCION)
      VALUES (ALIASv, NUMEROv, DESCRIPCIONv);

    END IF;
END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE
COST 100;