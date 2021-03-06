-- AGREGAR A TODAS LAS REFERENCIAS A TABLAS EL USUARIO AL INICIO.
-- USUARIO: C##_USR_ADMIN.CATEGORIA
-- EJEMPLO: C##_USR_ADMIN.CATEGORIA

-- • Agregar teléfono a usuario.
-- • Entrada: datos del nuevo teléfono.
-- • Usuario autorizado: admin

CREATE OR REPLACE PROCEDURE SP_AGREGAR_TEL
(
  ALIASv IN VARCHAR2
, NUMEROv IN VARCHAR2
, DESCRIPCIONv IN NVARCHAR2
)
IS 
-- Variable para saber si ya se encuentra registrado el usuario.
-- Si es 0, no existe el alias.
VAL SMALLINT;

BEGIN
    SELECT COUNT(*) INTO VAL
    FROM C##_USUARIO_ADMIN.USUARIO USUARIO
    WHERE USUARIO.ALIAS = ALIASv;

    IF VAL = 1 THEN
      INSERT INTO C##_USUARIO_ADMIN.TELEFONOXUSUARIO (ALIASUSUARIO, NUMERO, DESCRIPCION)
      VALUES (ALIASv, NUMEROv, DESCRIPCIONv);
      DBMS_OUTPUT.PUT_LINE('1');
    ELSE
      DBMS_OUTPUT.PUT_LINE('0');
    END IF;
    
    COMMIT;
END;
/

-- • Agregar ítem.
-- • Entrada: datos del nuevo ítem.
-- • Usuario autorizado: participante.

CREATE OR REPLACE PROCEDURE SP_AGREGAR_ITEM
(
  PRECIOv IN NUMBER
, DESCRIPCIONv IN NVARCHAR2
, IDSUBCATEGORIAv IN NUMBER
--, pdname IN VARCHAR2 DEFAULT NULL -- directory name
--, pfname IN VARCHAR2 DEFAULT NULL -- file name
)
IS 
  --src_file BFILE; -- source file
  --dst_file BLOB; -- designation file
  --lgh_file BINARY_INTEGER; -- file length
BEGIN
    --IF (pdname = NULL) THEN
      INSERT INTO C##_USUARIO_ADMIN.ITEM (PRECIO, DESCRIPCION, IDSUBCATEGORIA, FOTO)
      VALUES (PRECIOv, DESCRIPCIONv, IDSUBCATEGORIAv, EMPTY_BLOB());
    
    --ELSE
      --src_file := bfilename(pdname, pfname);

      -- Open the file
      --dbms_lob.fileopen(src_file, dbms_lob.file_readonly);

      -- Determine length
      --lgh_file := dbms_lob.getlength(src_file);

      -- read the file
      --dbms_lob.loadfromfile(dst_file, src_file, lgh_file);

      -- Update the blob field
      --UPDATE ITEM
      --SET ITEM.FOTO = dst_file
      --WHERE ITEM.ID = 5; -- CAMBIAR A SELECT COUNT(*) FROM ITEM;

      -- Close file
      --dbms_lob.fileclose(src_file);
    --END IF;

    COMMIT;
END;
/

-- • Hacer login desde aplicación.
-- • Entrada: alias, password.
-- • Usuario autorizado: cualquiera.

CREATE OR REPLACE PROCEDURE SP_LOGIN
(
  ALIASv IN VARCHAR2
, PASSWORDv IN VARCHAR2
)
IS 
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
PSS SMALLINT; -- Si es 0, el password es incorrecto.
TYP SMALLINT; -- 0: ADMINISTRADOR. 1: PARTICIPANTE.

BEGIN
  SELECT COUNT(*) INTO USR
  FROM C##_USUARIO_ADMIN.USUARIO USUARIO
  WHERE C##_USUARIO_ADMIN.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    SELECT COUNT(*) INTO PSS
    FROM C##_USUARIO_ADMIN.USUARIO USUARIO
    WHERE USUARIO.ALIAS = ALIASv
    AND USUARIO.PASSWORD = PASSWORDv;

    IF PSS = 1 THEN -- Password y alias correcto.
      SELECT COUNT(*) INTO TYP
      FROM C##_USUARIO_ADMIN.TIPOXUSUARIO TIPOXUSUARIO
      WHERE TIPOXUSUARIO.ALIASUSUARIO = ALIASv
      AND (TIPOXUSUARIO.DESCRIPCION = 'PARTICIPANTE'
      OR TIPOXUSUARIO.DESCRIPCION = 'Participante'
      OR TIPOXUSUARIO.DESCRIPCION = 'participante');
    
      IF TYP = 0 THEN
        DBMS_OUTPUT.PUT_LINE('1,1,ADMINISTRADOR');
      ELSE
        DBMS_OUTPUT.PUT_LINE('1,1,PARTICIPANTE');
      END IF;
    ELSE -- Password incorrecto.
      DBMS_OUTPUT.PUT_LINE('1,0,NULL');
    END IF;
  ELSE -- Alias no encontrado.
    DBMS_OUTPUT.PUT_LINE('0,NULL,NULL');
  END IF;
END;
/

-- • Seleccionar todas las categorías.
-- • Entrada: -
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_SELECT_CATEGORIAS
IS 
BEGIN
  DECLARE 
  CURSOR CCATEGORIAS IS 
    SELECT CATEGORIA.DESCRIPCION
    FROM C##_USUARIO_ADMIN.CATEGORIA CATEGORIA
    ORDER BY CATEGORIA.ID;

  CATEGORIAr C##_USUARIO_ADMIN.CATEGORIA.DESCRIPCION%TYPE;

  BEGIN
    OPEN CCATEGORIAS;
      LOOP
        FETCH CCATEGORIAS INTO CATEGORIAr;
        EXIT WHEN CCATEGORIAS%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(CATEGORIAr);
      END LOOP;
    CLOSE CCATEGORIAS;
  END;
  COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
END;
/

-- • Seleccionar todas las categorías.
-- • Entrada: IDCATEGORIAv
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_SELECT_SUBCATEGORIAS
(
  IDCATEGORIAv NUMBER
)
IS 
BEGIN
  DECLARE 
  CURSOR CSUBCATEGORIAS IS 
    SELECT SUBCATEGORIA.DESCRIPCION
    FROM C##_USUARIO_ADMIN.SUBCATEGORIA SUBCATEGORIA
    WHERE SUBCATEGORIA.IDCATEGORIA = IDCATEGORIAv
    ORDER BY SUBCATEGORIA.ID;

  SUBCATEGORIAr C##_USUARIO_ADMIN.SUBCATEGORIA.DESCRIPCION%TYPE;

  BEGIN
    OPEN CSUBCATEGORIAS;
      LOOP
        FETCH CSUBCATEGORIAS INTO SUBCATEGORIAr;
        EXIT WHEN CSUBCATEGORIAS%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(SUBCATEGORIAr);
      END LOOP;
    CLOSE CSUBCATEGORIAS;
  END;
  COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
END;
/

-- • Obtener índice de subcategoria.
-- • Entrada: IDCATEGORIAv, DESCRIPCIONSUBCATEGORIAv
-- • Usuario autorizado: participante.

CREATE OR REPLACE PROCEDURE SP_GET_IND_SUBCATEGORIA
(
    IDCATEGORIAv NUMBER
  , DESCRIPCIONSUBCATEGORIAv VARCHAR2
)
IS 
BEGIN
  DECLARE 
  CURSOR CSUBCATEGORIAS IS 
    SELECT SUBCATEGORIA.ID
    FROM C##_USUARIO_ADMIN.SUBCATEGORIA SUBCATEGORIA
    WHERE SUBCATEGORIA.IDCATEGORIA = IDCATEGORIAv
    AND SUBCATEGORIA.DESCRIPCION = DESCRIPCIONSUBCATEGORIAv;

  SUBCATEGORIAr C##_USUARIO_ADMIN.SUBCATEGORIA.ID%TYPE;

  BEGIN
    OPEN CSUBCATEGORIAS;
      LOOP
        FETCH CSUBCATEGORIAS INTO SUBCATEGORIAr;
        EXIT WHEN CSUBCATEGORIAS%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(SUBCATEGORIAr);
      END LOOP;
    CLOSE CSUBCATEGORIAS;
  END;
  COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('0');
END;
/

-- • Seleccionar todos los usuarios.
-- • Entrada: -
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_SELECT_USERS
IS 
BEGIN
  DECLARE 
  CURSOR CUSERS IS 
    SELECT USUARIO.ALIAS
    FROM C##_USUARIO_ADMIN.USUARIO USUARIO
    ORDER BY USUARIO.ALIAS;

  ALIASr C##_USUARIO_ADMIN.USUARIO.ALIAS%TYPE;

  BEGIN
    OPEN CUSERS;
      LOOP
        FETCH CUSERS INTO ALIASr;
        EXIT WHEN CUSERS%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(ALIASr);
      END LOOP;
    CLOSE CUSERS;
  END;
  COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
END;
/

-- • Consulta si es PARTICIPANTE o ADMINISTRADOR y llama el SP correspondiente.
-- • Entrada: ALIASv
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO
(
  ALIASv IN VARCHAR2
, NOMBREv IN NVARCHAR2 DEFAULT NULL
, APELLIDOSv IN NVARCHAR2 DEFAULT NULL
, DIRECCIONv IN NVARCHAR2 DEFAULT NULL
, CEDULAv IN INTEGER DEFAULT NULL
, CORREOv IN VARCHAR2 DEFAULT NULL
, PASSWORDv IN VARCHAR2 DEFAULT NULL
, NUMEROv IN VARCHAR2 DEFAULT NULL
, DESCRIPCIONv IN NVARCHAR2 DEFAULT 'PRINCIPAL'
)
IS 
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
TYP SMALLINT; -- 0: ADMINISTRADOR. 1: PARTICIPANTE.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM C##_USUARIO_ADMIN.USUARIO USUARIO
  WHERE USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    SELECT COUNT(*) INTO TYP
    FROM C##_USUARIO_ADMIN.TIPOXUSUARIO TIPOXUSUARIO
    WHERE TIPOXUSUARIO.ALIASUSUARIO = ALIASv
    AND TIPOXUSUARIO.DESCRIPCION = 'PARTICIPANTE';
    
    IF TYP = 0 THEN
      SP_MODIFICAR_ADMIN(ALIASv=>ALIASv,NOMBREv=>NOMBREv,APELLIDOSv=>APELLIDOSv,DIRECCIONv=>DIRECCIONv,CEDULAv=>CEDULAv,CORREOv=>CORREOv,PASSWORDv=>PASSWORDv,NUMEROv=>NUMEROv,DESCRIPCIONv=>DESCRIPCIONv);
      DBMS_OUTPUT.PUT_LINE('1,ADMINISTRADOR');
    ELSE
      SP_MODIFICAR_PARTICIPANTE(ALIASv=>ALIASv,NOMBREv=>NOMBREv,APELLIDOSv=>APELLIDOSv,DIRECCIONv=>DIRECCIONv,CEDULAv=>CEDULAv,CORREOv=>CORREOv,PASSWORDv=>PASSWORDv,NUMEROv=>NUMEROv,DESCRIPCIONv=>DESCRIPCIONv);
      DBMS_OUTPUT.PUT_LINE('1,PARTICIPANTE');
    END IF;
  ELSE -- Alias no encontrado.
    DBMS_OUTPUT.PUT_LINE('0,NULL');
  END IF;
END;
/

-- • Agregar comentario a subasta ganada por PARTICIPANTE.
-- • Entrada: COMENTARIOCOMPRADORr, CALIFICACIONr.
-- • Usuario autorizado: PARTICIPANTE.
CREATE OR REPLACE PROCEDURE SP_AGREGAR_COMENTARIO_COMPRADOR
(
    IDSUBASTAv NUMBER
  , COMENTARIOCOMPRADORv NVARCHAR2
  , CALIFICACIONv SMALLINT
)
IS 
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  UPDATE C##_USUARIO_ADMIN.SUBASTA
  SET COMENTARIOCOMPRADOR = COMENTARIOCOMPRADORv,
      CALIFICACIONSUBASTA = CALIFICACIONv
  WHERE ID = IDSUBASTAv;
  DBMS_OUTPUT.PUT_LINE('1');
  COMMIT;

  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('0');
END;
/

-- • Agregar comentario a subasta ganada por PARTICIPANTE.
-- • Entrada: COMENTARIOVENDEDORv.
-- • Usuario autorizado: PARTICIPANTE.
CREATE OR REPLACE PROCEDURE SP_AGREGAR_COMENTARIO_ESTADO_VENDEDOR
(
    IDSUBASTAv NUMBER
  , COMENTARIOVENDEDORv NVARCHAR2 DEFAULT NULL
  , ESTADOSUBASTAv SMALLINT DEFAULT NULL
)
IS 
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  IF COMENTARIOVENDEDORv IS NOT NULL THEN
    UPDATE C##_USUARIO_ADMIN.SUBASTA
    SET   COMENTARIOVENDEDOR = COMENTARIOVENDEDORv
    WHERE ID = IDSUBASTAv;
  END IF;

  IF ESTADOSUBASTAv IS NOT NULL THEN
    UPDATE C##_USUARIO_ADMIN.SUBASTA
    SET   ESTADOSUBASTA = ESTADOSUBASTAv
    WHERE ID = IDSUBASTAv;
  END IF;

  COMMIT;
  DBMS_OUTPUT.PUT_LINE('1');
  
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('0');
END;
/

-- • Lista todas las subastas ganadoras de un usuario.
-- • Entrada: alias usuario.
-- • Usuario autorizado: cualquiera

CREATE OR REPLACE PROCEDURE SP_GET_SUBASTAS_GANADORAS_USUARIO
(
  ALIASv IN VARCHAR2
)
IS
BEGIN
  -- No es necesario buscar la categoría, pues ya al seleccionar subcategoría la categoría enviada está asegurada.
  DECLARE 
  CURSOR CSUBASTA IS 
    SELECT SUBASTA.ID, SUBASTA.ALIASVENDEDOR, SUBASTA.PRECIOINICIAL, SUBASTA.PRECIOACTUAL, SUBASTA.FECHAHORAFIN, SUBASTA.COMENTARIOVENDEDOR
    FROM C##_USUARIO_ADMIN.SUBASTA SUBASTA
    WHERE SUBASTA.ESTADOSUBASTA > 1
    AND SUBASTA.ALIASCOMPRADOR = ALIASv
    ORDER BY SUBASTA.FECHAHORAFIN DESC;

  IDSUBASTAr C##_USUARIO_ADMIN.SUBASTA.ID%TYPE;
  ALIASVENDEDORr C##_USUARIO_ADMIN.SUBASTA.ALIASVENDEDOR%TYPE;
  PRECIOINICIALr C##_USUARIO_ADMIN.SUBASTA.PRECIOINICIAL%TYPE;
  PRECIOACTUALr C##_USUARIO_ADMIN.SUBASTA.PRECIOACTUAL%TYPE;
  FECHAHORAFINr C##_USUARIO_ADMIN.SUBASTA.FECHAHORAFIN%TYPE;
  COMENTARIOVENDEDORr C##_USUARIO_ADMIN.SUBASTA.COMENTARIOVENDEDOR%TYPE;

  BEGIN
    OPEN CSUBASTA;
      LOOP
        FETCH CSUBASTA INTO IDSUBASTAr, ALIASVENDEDORr, PRECIOINICIALr, PRECIOACTUALr, FECHAHORAFINr, COMENTARIOVENDEDORr;
        EXIT WHEN CSUBASTA%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(IDSUBASTAr || ', ' || ALIASVENDEDORr || ', ' || PRECIOACTUALr || ', ' || TO_CHAR(FECHAHORAFINr,'DD-MON-YYYY') || ', ' || TO_CHAR(FECHAHORAFINr,'HH24:MI'));
      END LOOP;
    CLOSE CSUBASTA;
  END;

  COMMIT;

  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
END;
/

-- • Lista todas las subastas realizadas de un usuario.
-- • Entrada: alias usuario.
-- • Usuario autorizado: cualquiera

CREATE OR REPLACE PROCEDURE SP_GET_SUBASTAS_REALIZADAS_USUARIO
(
  ALIASv IN VARCHAR2
)
IS
BEGIN
  -- No es necesario buscar la categoría, pues ya al seleccionar subcategoría la categoría enviada está asegurada.
  DECLARE 
  CURSOR CSUBASTA IS 
    SELECT SUBASTA.ID, SUBASTA.ALIASVENDEDOR, SUBASTA.PRECIOINICIAL, SUBASTA.PRECIOACTUAL, SUBASTA.FECHAHORAFIN, SUBASTA.COMENTARIOVENDEDOR
    FROM C##_USUARIO_ADMIN.SUBASTA SUBASTA
    WHERE SUBASTA.ESTADOSUBASTA > 1
    AND SUBASTA.ALIASVENDEDOR = ALIASv
    ORDER BY SUBASTA.FECHAHORAFIN DESC;

  IDSUBASTAr C##_USUARIO_ADMIN.SUBASTA.ID%TYPE;
  ALIASVENDEDORr C##_USUARIO_ADMIN.SUBASTA.ALIASVENDEDOR%TYPE;
  PRECIOINICIALr C##_USUARIO_ADMIN.SUBASTA.PRECIOINICIAL%TYPE;
  PRECIOACTUALr C##_USUARIO_ADMIN.SUBASTA.PRECIOACTUAL%TYPE;
  FECHAHORAFINr C##_USUARIO_ADMIN.SUBASTA.FECHAHORAFIN%TYPE;
  COMENTARIOVENDEDORr C##_USUARIO_ADMIN.SUBASTA.COMENTARIOVENDEDOR%TYPE;

  BEGIN
    OPEN CSUBASTA;
      LOOP
        FETCH CSUBASTA INTO IDSUBASTAr, ALIASVENDEDORr, PRECIOINICIALr, PRECIOACTUALr, FECHAHORAFINr, COMENTARIOVENDEDORr;
        EXIT WHEN CSUBASTA%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(IDSUBASTAr || ', ' || ALIASVENDEDORr || ', ' || PRECIOACTUALr || ', ' || TO_CHAR(FECHAHORAFINr,'DD-MON-YYYY') || ', ' || TO_CHAR(FECHAHORAFINr,'HH24:MI'));
      END LOOP;
    CLOSE CSUBASTA;
  END;

  COMMIT;

  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
END;
/

CREATE OR REPLACE PROCEDURE SP_ACTUALIZAR_PARAMETROS
  (INCREMENTOMINIMOv INTEGER, PORCENTAJEMEJORAv NUMBER)

IS

BEGIN

  UPDATE C##_USUARIO_ADMIN.PARAMETROS
  SET INCREMENTOMINIMO = INCREMENTOMINIMOv,
      PORCENTAJEMEJORA = PORCENTAJEMEJORAv
  WHERE ID = 1;

  DBMS_OUTPUT.PUT_LINE('1');

COMMIT;

EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
END;
/

CREATE OR REPLACE PROCEDURE SP_SELECT_PARAMETROS
IS
  INCREMENTOMINIMOv INTEGER;
  PORCENTAJEMEJORAv NUMBER;
BEGIN

  SELECT INCREMENTOMINIMO INTO INCREMENTOMINIMOv FROM C##_USUARIO_ADMIN.PARAMETROS WHERE ID = 1;
  SELECT PORCENTAJEMEJORA INTO PORCENTAJEMEJORAv FROM C##_USUARIO_ADMIN.PARAMETROS WHERE ID = 1;
  DBMS_OUTPUT.PUT_LINE(INCREMENTOMINIMOv || ', ' || PORCENTAJEMEJORAv);

END;
/