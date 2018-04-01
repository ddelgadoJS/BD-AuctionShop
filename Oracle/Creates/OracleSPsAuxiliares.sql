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
    FROM SYS.USUARIO
    WHERE SYS.USUARIO.ALIAS = ALIASv;

    IF VAL = 1 THEN
      INSERT INTO SYS.TELEFONOXUSUARIO (ALIASUSUARIO, NUMERO, DESCRIPCION)
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
      INSERT INTO SYS.ITEM (PRECIO, DESCRIPCION, IDSUBCATEGORIA, FOTO)
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
      --UPDATE SYS.ITEM
      --SET SYS.ITEM.FOTO = dst_file
      --WHERE SYS.ITEM.ID = 5; -- CAMBIAR A SELECT COUNT(*) FROM ITEM;

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
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    SELECT COUNT(*) INTO PSS
    FROM SYS.USUARIO
    WHERE SYS.USUARIO.ALIAS = ALIASv
    AND SYS.USUARIO.PASSWORD = PASSWORDv;

    IF PSS = 1 THEN -- Password y alias correcto.
      SELECT COUNT(*) INTO TYP
      FROM SYS.TIPOXUSUARIO
      WHERE SYS.TIPOXUSUARIO.ALIASUSUARIO = ALIASv
      AND SYS.TIPOXUSUARIO.DESCRIPCION = 'PARTICIPANTE';
    
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
    FROM SYS.CATEGORIA CATEGORIA
    ORDER BY CATEGORIA.ID;

  CATEGORIAr SYS.CATEGORIA.DESCRIPCION%TYPE;

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
    FROM SYS.SUBCATEGORIA SUBCATEGORIA
    WHERE SUBCATEGORIA.IDCATEGORIA = IDCATEGORIAv
    ORDER BY SUBCATEGORIA.ID;

  SUBCATEGORIAr SYS.SUBCATEGORIA.DESCRIPCION%TYPE;

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
    FROM SYS.SUBCATEGORIA SUBCATEGORIA
    WHERE SUBCATEGORIA.IDCATEGORIA = IDCATEGORIAv
    AND SUBCATEGORIA.DESCRIPCION = DESCRIPCIONSUBCATEGORIAv;

  SUBCATEGORIAr SYS.SUBCATEGORIA.ID%TYPE;

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
    FROM SYS.USUARIO USUARIO
    ORDER BY USUARIO.ALIAS;

  ALIASr SYS.USUARIO.ALIAS%TYPE;

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
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv_;

  IF USR = 1 THEN -- Alias encontrado.
    SELECT COUNT(*) INTO TYP
    FROM SYS.TIPOXUSUARIO
    WHERE SYS.TIPOXUSUARIO.ALIASUSUARIO = ALIASv_
    AND SYS.TIPOXUSUARIO.DESCRIPCION = 'PARTICIPANTE';
    
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

-- • Actualiza NOMBRE de usuario.
-- • Entrada: ALIASv, NOMBREv
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO_NOMBRE
(
  ALIASv IN VARCHAR2 -- Alias actual.
, NOMBREv IN NVARCHAR2
)
IS
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    UPDATE SYS.USUARIO 
      SET   NOMBRE = NOMBREv
      WHERE ALIAS = ALIASv;
  END IF;
END;
/

-- • Actualiza APELLIDOS de usuario.
-- • Entrada: ALIASv, APELLIDOSv.
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO_APELLIDOS
(
  ALIASv IN VARCHAR2 -- Alias actual.
, APELLIDOSv IN NVARCHAR2
)
IS
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    UPDATE SYS.USUARIO 
      SET   APELLIDOS = APELLIDOSv
      WHERE ALIAS = ALIASv;
  END IF;
END;
/

-- • Actualiza DIRECCION de usuario.
-- • Entrada: ALIASv, DIRECCIONv.
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO_DIRECCION
(
  ALIASv IN VARCHAR2 -- Alias actual.
, DIRECCIONv IN NVARCHAR2
)
IS
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    UPDATE SYS.USUARIO 
      SET   DIRECCION = DIRECCIONv
      WHERE ALIAS = ALIASv;
  END IF;
END;
/

-- • Actualiza CEDULA de usuario.
-- • Entrada: ALIASv, CEDULAv.
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO_CEDULA
(
  ALIASv IN VARCHAR2 -- Alias actual.
, CEDULAv IN INTEGER
)
IS
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    UPDATE SYS.USUARIO 
      SET   CEDULA = CEDULAv
      WHERE ALIAS = ALIASv;
  END IF;
END;
/

-- • Actualiza CORREO de usuario.
-- • Entrada: ALIASv, CORREOv.
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO_CORREO
(
  ALIASv IN VARCHAR2 -- Alias actual.
, CORREOv IN VARCHAR2
)
IS
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    UPDATE SYS.USUARIO 
      SET   CORREO = CORREOv
      WHERE ALIAS = ALIASv;
  END IF;
END;
/

-- • Actualiza PASSWORD de usuario.
-- • Entrada: ALIASv, PASSWORDv.
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO_PASSWORD
(
  ALIASv IN VARCHAR2 -- Alias actual.
, PASSWORDv IN VARCHAR2
)
IS
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    UPDATE SYS.USUARIO 
      SET   PASSWORD = PASSWORDv
      WHERE ALIAS = ALIASv;
  END IF;
END;
/

-- • Actualiza TELEFONO PRINCIPAL de usuario.
-- • Entrada: ALIASv, TELEFONO.
-- • Usuario autorizado: admin.

CREATE OR REPLACE PROCEDURE SP_MODIFICAR_USUARIO_NUMERO
(
  ALIASv IN VARCHAR2 -- Alias actual.
, NUMEROv IN VARCHAR2
)
IS
-- Variables para saber si ya se encuentra registrado el usuario.
USR SMALLINT; -- Si es 0, no existe el alias.
BEGIN
  SELECT COUNT(*) INTO USR
  FROM SYS.USUARIO
  WHERE SYS.USUARIO.ALIAS = ALIASv;

  IF USR = 1 THEN -- Alias encontrado.
    UPDATE SYS.TELEFONOXUSUARIO 
      SET   NUMERO = NUMEROv
      WHERE ALIASUSUARIO = ALIASv;
  END IF;
END;
/