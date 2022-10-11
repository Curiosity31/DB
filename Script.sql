
CREATE TABLE CLASSDIAGRAM (
    Codice VARCHAR2(30),
	Nome VARCHAR2(30) NOT NULL,
	FileC VARCHAR2(30) NOT NULL,
	DataCreazione DATE NOT NULL,
	DataAggiornamento DATE ,
	CONSTRAINT pkclassdiagram PRIMARY KEY (Codice),
	CONSTRAINT Vincolo_Data1 CHECK (DataCreazione <= DataAggiornamento)
);
/

CREATE TABLE  COMMENTO (
    Descrizione VARCHAR2(200) NOT NULL,
    CodiceC INTEGER ,
    Codice  VARCHAR2(30) NOT NULL,
	DataInserimento DATE NOT NULL,
	DataModifica DATE ,
    CONSTRAINT pkcommento  PRIMARY KEY (CodiceC),
	CONSTRAINT Vincolo_Data2 CHECK (DataInserimento <= DataModifica)
);
/


CREATE OR REPLACE TRIGGER PKEY_COMMENTO
BEFORE INSERT ON COMMENTO
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF((:NEW.CodiceC   = 0) OR (:NEW.CodiceC  IS NULL)) THEN
            SELECT MAX (CodiceC  ) INTO pkey FROM COMMENTO;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.CodiceC  := pkey;
        END IF;
    END;
END;
/

CREATE TABLE ASSOCIAZIONE (
	CodAss INTEGER,
	NomeAss VARCHAR(30) ,
	Grado INTEGER NOT NULL,
	Codice  VARCHAR(30) NOT NULL,
	IdAss INTEGER , 
	CONSTRAINT pkassociazione PRIMARY KEY (CodAss)
);
/


CREATE OR REPLACE TRIGGER PKEY_ASSOCIAZIONE
BEFORE INSERT ON ASSOCIAZIONE
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF((:NEW.CodAss  = 0) OR (:NEW.CodAss  IS NULL)) THEN
            SELECT MAX (CodAss ) INTO pkey FROM ASSOCIAZIONE;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.CodAss := pkey;
        END IF;
    END;
END;
/

CREATE TABLE CLASSE (
    IdC INTEGER  ,
	NomeC VARCHAR2(30) NOT NULL ,
	Descrizione VARCHAR2(100),
	Codice  VARCHAR2(30) NOT NULL,
	Generale INTEGER ,
	Aggregata INTEGER ,
	Composta INTEGER ,
	IdAss INTEGER ,
	TipoClasse VARCHAR2(30) ,
	DataInserimento DATE NOT NULL,
	DataModifica DATE ,
	CONSTRAINT pkclasse PRIMARY KEY (IdC),
	CONSTRAINT Unico_Classe UNIQUE (NomeC,Codice),
	CONSTRAINT Scelta_Classe CHECK  (( IdAss IS NOT NULL AND TipoClasse IS NULL AND IdC=IdAss) OR (  IdAss IS NULL AND TipoClasse IS NOT NULL AND (TipoClasse ='classe_concreta' OR TipoClasse='classe_astratta') )),
	CONSTRAINT NO_Classe CHECK (( Idc = IdAss AND Generale IS NULL AND Aggregata IS NULL AND Composta IS NULL) OR (Idc <> IdAss) ) ,
	CONSTRAINT Vincolo_Specializzazione CHECK (IdC <> Generale),
	CONSTRAINT Vincolo_Componente CHECK (IdC <> Composta),
	CONSTRAINT Vincolo_Aggregante CHECK (IdC <> Aggregata),
	CONSTRAINT Vincolo_Data3 CHECK (DataInserimento <= DataModifica)
);
/


CREATE OR REPLACE TRIGGER PKEY_CLASSE
BEFORE INSERT ON CLASSE
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF(:NEW.IdC IS NULL)THEN
            SELECT MAX ( IdC ) INTO pkey FROM CLASSE;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.IdC := pkey;
        END IF;
    END;
END;
/

CREATE TABLE ATTRIBUTO(
    CodiceA INTEGER,
    NomeAt VARCHAR2(30) NOT NULL,
	Cardinalità VARCHAR2(30) NOT NULL,
	ModifyAcc  VARCHAR2(9) NOT NULL,
	Descrizione VARCHAR2(100) ,
	CodiceT    INTEGER NOT NULL,
	IdC    INTEGER,
	IdAss    INTEGER ,
	DataInserimento DATE NOT NULL,
	DataModifica DATE ,
	CONSTRAINT pkattributo PRIMARY KEY (CodiceA),
    CONSTRAINT Unico_Attributo UNIQUE (NomeAt,IdC,IdAss),
	CONSTRAINT Appartenenza_Attributo CHECK ((IdC =IdAss ) OR (IdC IS NOT NULL AND IdAss IS  NULL )),
	CONSTRAINT Mod1 CHECK ((ModifyAcc='Private' OR ModifyAcc='Public' OR ModifyAcc ='Package' OR ModifyAcc='Protected') OR (ModifyAcc='private' OR ModifyAcc='public' OR ModifyAcc ='package' OR ModifyAcc='protected')),
	CONSTRAINT Vincolo_Data4 CHECK (DataInserimento<=DataModifica)
);
/


CREATE OR REPLACE TRIGGER PKEY_ATTRIBUTO
BEFORE INSERT ON ATTRIBUTO
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF((:NEW.CodiceA = 0) OR (:NEW.CodiceA IS NULL)) THEN
            SELECT MAX ( CodiceA) INTO pkey FROM ATTRIBUTO;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.CodiceA:= pkey;
        END IF;
    END;
END;
/

CREATE TABLE METODO(
	Segnatura VARCHAR2(30),
	Nome     VARCHAR2(30) NOT NULL,
	ModifyAcc VARCHAR2(9) NOT NULL,
	Corpo   VARCHAR2(30) ,
	CodiceT   INTEGER ,
	IdC    INTEGER NOT NULL,
	Procedura INTEGER NOT NULL,
	Funzione  INTEGER NOT NULL,
	CONSTRAINT pkmetodo PRIMARY KEY (Segnatura),
	CONSTRAINT Mod2 CHECK ( (ModifyAcc='Private') OR ( ModifyAcc='Public' ) OR ( ModifyAcc ='Package' ) OR (ModifyAcc='Protected') OR 
  	                       (ModifyAcc='private') OR ( ModifyAcc='public' ) OR ( ModifyAcc ='package' ) OR (ModifyAcc='protected') ) ,
	CONSTRAINT Pro_OR_Fun CHECK ((Procedura = 1 AND Funzione = 0 AND CodiceT IS NULL) OR (Procedura = 0 AND Funzione = 1 AND CODICET IS NOT NULL)),
	CONSTRAINT Unico_Metodo UNIQUE (Segnatura,IdC)
);
/

CREATE TABLE TIPO(
	CodiceT INTEGER,
	TipoPrimitivo VARCHAR2(7) ,
	TipoClasse VARCHAR2(30),
	IdC INTEGER , 
	CodiceE INTEGER ,
	CONSTRAINT pktipo PRIMARY KEY (CodiceT),
	CONSTRAINT TipoP CHECK ( (TipoPrimitivo= 'integer') OR  (TipoPrimitivo = 'char') OR (TipoPrimitivo = 'boolean') OR (TipoPrimitivo = 'float') OR (TipoPrimitivo = 'date')
							OR (TipoPrimitivo = 'double') OR (TipoPrimitivo = 'short') OR (TipoPrimitivo = 'long') OR (TipoPrimitivo = 'byte') ),
	CONSTRAINT VincoloTipo CHECK ( ((TipoPrimitivo IS NOT NULL) AND (TipoClasse IS NULL) AND (CodiceE IS NULL ) ) OR ( (TipoPrimitivo IS  NULL) AND (TipoClasse IS NOT NULL) AND (CodiceE IS NULL))
							 OR ( (TipoPrimitivo IS  NULL) AND (TipoClasse IS NULL) AND (CodiceE IS NOT NULL)) )
);
/

CREATE OR REPLACE TRIGGER PKEY_TIPO
BEFORE INSERT ON TIPO
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF((:NEW.CodiceT = 0) OR (:NEW.CodiceT IS NULL)) THEN
            SELECT MAX (CodiceT) INTO pkey FROM TIPO;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.CodiceT:= pkey;
        END IF;
    END;
END;
/

CREATE TABLE PARTECIPA(
	CodAss INTEGER NOT NULL,
	Ruoli VARCHAR2(30) ,
	Cardinalità VARCHAR2(30) NOT NULL,
	IdC INTEGER NOT NULL ,
	Direzionalità VARCHAR2 (14)
	);
/

CREATE TABLE CLASSE_DI_ASSOCIAZIONE(
    IdAss INTEGER ,
	NomeA VARCHAR2(30) NOT NULL,
	Codice  VARCHAR2(30) NOT NULL,
	CONSTRAINT pkclasse_di_associazione PRIMARY KEY (IdAss),
    CONSTRAINT Unico_Classe_Di_Associazione UNIQUE (NomeA,Codice)
);
/


CREATE OR REPLACE TRIGGER PKEY_C_ASSOCIAZIONE
BEFORE INSERT ON CLASSE_DI_ASSOCIAZIONE
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF((:NEW.IdAss = 0) OR (:NEW.IdAss IS NULL)) THEN
            SELECT MAX (IdC) INTO pkey FROM CLASSE;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.IdAss:= pkey;
        END IF;
    END;
END;
/

CREATE TABLE ENUMERATION(
	CodiceE INTEGER ,
	NomeE  VARCHAR2(30) NOT NULL,
	Codice  VARCHAR2(30) NOT NULL,
    CONSTRAINT pkenumeration PRIMARY KEY (CodiceE),
	CONSTRAINT Vincolo_Enum  UNIQUE (NomeE,Codice )
);
/

CREATE OR REPLACE TRIGGER PKEY_ENUMERATION
BEFORE INSERT ON ENUMERATION
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF((:NEW.CodiceE = 0)OR(:NEW.CodiceE IS NULL )) THEN
            SELECT MAX (CodiceE) INTO pkey FROM ENUMERATION;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.CodiceE := pkey;
        END IF;
    END;
END;
/

CREATE TABLE VALORE(
	NomeV VARCHAR2(30),
	CONSTRAINT pkvalore PRIMARY KEY(NomeV)
);
/


CREATE TABLE PARAMETRO (
	CodiceP INTEGER,
	NomeP VARCHAR2(30) NOT NULL,
	CodiceT INTEGER NOT NULL,
	P_IN INTEGER NOT NULL,
	P_OUT INTEGER NOT NULL,
	CONSTRAINT P_IN_OR_P_OUT CHECK ((P_IN = 1  OR  P_IN = 0)  AND ( P_OUT = 0 OR P_OUT = 1)),
	CONSTRAINT pkparametro PRIMARY KEY (CodiceP)
);
/


CREATE OR REPLACE TRIGGER PKEY_PARAMETRO
BEFORE INSERT ON PARAMETRO
FOR EACH ROW
BEGIN
    DECLARE
    pkey integer;
    BEGIN
        IF((:NEW.CodiceP = 0) OR (:NEW.CodiceP IS NULL )) THEN
            SELECT MAX (CodiceP) INTO pkey FROM PARAMETRO;
            IF pkey IS NULL THEN 
                 pkey := 1;
            ELSE
                pkey:=pkey +1 ;
            END IF; 
            :NEW.CodiceP := pkey;
        END IF;
    END;
END;
/

CREATE TABLE COMPONIMENTO(
    N_valori INTEGER NOT NULL,
	NomeV VARCHAR2(30) NOT NULL,
    CodiceE INTEGER NOT NULL
);
/

CREATE TABLE  DOMINIO(
    N_Parametri INTEGER NOT NULL,
	Segnatura VARCHAR2(30) NOT NULL,
	CodiceP INTEGER NOT NULL
);
/
	
CREATE TABLE CHIAVE_ESTERNA (
    Codice INTEGER, 
    CodiceCE VARCHAR2(30), 
    Nome VARCHAR2(30), 
    Appartenenza_Classe VARCHAR2(30), 
    CONSTRAINT PKCHIAVE_ESTERNA PRIMARY KEY (CODICE)
);
/

create or replace TRIGGER PKEY_CHIAVE_ESTERNA
BEFORE INSERT ON CHIAVE_ESTERNA
FOR EACH ROW
DECLARE
pkey integer;
BEGIN
    IF((:NEW.Codice = 0) OR (:NEW.Codice IS NULL )) THEN
        SELECT MAX (Codice) INTO pkey FROM CHIAVE_ESTERNA;
        IF pkey IS NULL THEN 
             pkey := 1;
        ELSE
             pkey:=pkey +1 ;
        END IF; 
     	:NEW.Codice := pkey;
    END IF;
END;
/



CREATE OR REPLACE TRIGGER DELE_INS_UP_CA
AFTER INSERT OR UPDATE OR DELETE ON CLASSE_DI_ASSOCIAZIONE
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.IDASS,:NEW.NOMEA,'Classe_Di_Associazione');
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.IDASS=CODICECE AND APPARTENENZA_CLASSE='Classe_Di_Associazione';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOMEA WHERE CODICECE=:NEW.IDASS AND APPARTENENZA_CLASSE='Classe_Di_Associazione';
	END IF;
END;
/

create or replace TRIGGER DELE_INS_UP_CLASSDIAGRAM
AFTER INSERT OR UPDATE OR DELETE ON CLASSDIAGRAM
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.CODICE,:NEW.NOME,'ClassDiagram');
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.CODICE=CODICECE AND APPARTENENZA_CLASSE='ClassDiagram';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOME WHERE CODICECE=:NEW.CODICE AND APPARTENENZA_CLASSE='ClassDiagram';
	END IF;
end;
/

create or replace TRIGGER DELE_INS_UP_CLASSE
AFTER INSERT OR UPDATE OR DELETE ON CLASSE
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.IDC,:NEW.NOMEC,'Classe');
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.IDC=CODICECE AND APPARTENENZA_CLASSE='Classe';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOMEC WHERE CODICECE=:NEW.IDC AND APPARTENENZA_CLASSE='Classe';
	END IF;
END;
/

create or replace TRIGGER DELE_INS_UP_TIPO
AFTER INSERT OR UPDATE OR DELETE ON TIPO
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		IF(:NEW.TIPOPRIMITIVO IS NOT NULL)THEN
			INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.CODICET,:NEW.TIPOPRIMITIVO,'Tipo');
		END IF;
		IF(:NEW.TIPOCLASSE IS NOT NULL)THEN
			INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.CODICET,:NEW.TIPOCLASSE,'Tipo');
		END IF;
		IF(:NEW.CODICEE IS NOT NULL)THEN
			INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.CODICET,:NEW.CODICEE ||' '||'Codice Enumeration','Tipo');
		END IF;
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.CODICET=CODICECE AND APPARTENENZA_CLASSE='Tipo';
	END IF;
	IF(UPDATING)THEN
		IF(:NEW.TIPOPRIMITIVO<>:OLD.TIPOPRIMITIVO)THEN
			UPDATE CHIAVE_ESTERNA SET NOME =:NEW.TIPOPRIMITIVO WHERE CODICECE=:NEW.CODICET AND APPARTENENZA_CLASSE='Tipo';
		END IF;
		IF(:NEW.TIPOCLASSE<>:OLD.TIPOCLASSE)THEN
			UPDATE CHIAVE_ESTERNA SET NOME =:NEW.TIPOCLASSE WHERE CODICECE=:NEW.CODICET AND APPARTENENZA_CLASSE='Tipo';
		END IF;
	END IF;
END;
/


create or replace TRIGGER DELE_INS_UP_ENUMERATION
AFTER INSERT OR UPDATE OR DELETE ON ENUMERATION
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.CODICEE,:NEW.NOMEE,'Enumeration');
	end if;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.CODICEE=CODICECE AND APPARTENENZA_CLASSE='Enumeration';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOMEE WHERE CODICECE=:NEW.CODICEE AND APPARTENENZA_CLASSE='Enumeration';
	END IF;
END;
/

create or replace TRIGGER DELE_INS_UP_METODO
AFTER INSERT OR UPDATE OR DELETE ON METODO
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.SEGNATURA,:NEW.NOME,'Metodo');
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.SEGNATURA=CODICECE AND APPARTENENZA_CLASSE='Metodo';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOME WHERE CODICECE=:NEW.SEGNATURA AND APPARTENENZA_CLASSE='Metodo';
	END IF;
END;
/


create or replace TRIGGER DELE_INS_UP_ASSOCIAZIONE
AFTER INSERT OR UPDATE OR DELETE ON ASSOCIAZIONE
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.CodAss,:NEW.NOMEASS,'Associazione');
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.CODASS=CODICECE AND APPARTENENZA_CLASSE='Associazione';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOMEASS WHERE CODICECE=:NEW.CODASS AND APPARTENENZA_CLASSE='Associazione';
	END IF;
END;
/


create or replace TRIGGER DELE_INS_UP_PARAMETRO
AFTER INSERT OR UPDATE OR DELETE ON PARAMETRO
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.CODICEP,:NEW.NOMEP,'Parametro');
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.CODICEP=CODICECE AND APPARTENENZA_CLASSE='Parametro';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOMEP WHERE CODICECE=:NEW.CODICEP AND APPARTENENZA_CLASSE='Parametro';
	END IF;
end;
/

create or replace TRIGGER DELE_INS_UP_VALORE
AFTER INSERT OR UPDATE OR DELETE ON VALORE
FOR EACH ROW 
BEGIN
	IF(INSERTING)THEN 
		INSERT INTO CHIAVE_ESTERNA VALUES(NULL,:NEW.NOMEV,:NEW.NOMEV,'Valore');
	END IF;
	IF(DELETING)THEN
		DELETE FROM CHIAVE_ESTERNA WHERE :OLD.NOMEV=CODICECE AND APPARTENENZA_CLASSE='Valore';
	END IF;
	IF(UPDATING)THEN
		UPDATE CHIAVE_ESTERNA SET NOME =:NEW.NOMEV WHERE CODICECE=:NEW.NOMEV AND APPARTENENZA_CLASSE='Valore';
	END IF;
end;
/

	
ALTER TABLE COMMENTO  ADD CONSTRAINT fkcommento  FOREIGN KEY (Codice ) REFERENCES CLASSDIAGRAM (Codice ) ON DELETE CASCADE;
/
ALTER TABLE ASSOCIAZIONE ADD CONSTRAINT fkassociazione1 FOREIGN KEY (Codice) REFERENCES CLASSDIAGRAM (Codice) ;
/
ALTER TABLE ASSOCIAZIONE ADD CONSTRAINT fkassociazione2 FOREIGN KEY (IdAss) REFERENCES CLASSE_DI_ASSOCIAZIONE (IdAss);
/
ALTER TABLE CLASSE ADD CONSTRAINT fkclasse1 FOREIGN KEY (Generale) REFERENCES CLASSE(IdC)ON DELETE SET NULL;
/
ALTER TABLE CLASSE ADD CONSTRAINT fkclasse2 FOREIGN KEY (Aggregata) REFERENCES CLASSE (IdC)ON DELETE SET NULL;
/
ALTER TABLE CLASSE ADD CONSTRAINT fkclasse3 FOREIGN KEY (Composta) REFERENCES CLASSE (IdC)ON DELETE CASCADE;
/
ALTER TABLE CLASSE ADD CONSTRAINT fkclasse4 FOREIGN KEY (Codice) REFERENCES CLASSDIAGRAM(Codice)ON DELETE CASCADE;
/
ALTER TABLE CLASSE ADD CONSTRAINT fkclasse5 FOREIGN KEY (IdAss) REFERENCES CLASSE_DI_ASSOCIAZIONE(IdAss)  ON DELETE CASCADE;
/
ALTER TABLE ATTRIBUTO ADD CONSTRAINT fkattributo1 FOREIGN KEY (CodiceT) REFERENCES TIPO(CodiceT)ON DELETE CASCADE;
/
ALTER TABLE ATTRIBUTO ADD CONSTRAINT fkattributo2 FOREIGN KEY (IdC) REFERENCES CLASSE (IdC)ON DELETE CASCADE;
/
ALTER TABLE ATTRIBUTO ADD CONSTRAINT fkattributo3 FOREIGN KEY (IdAss) REFERENCES CLASSE_DI_ASSOCIAZIONE (IdAss)ON DELETE CASCADE;
/
ALTER TABLE METODO ADD CONSTRAINT fkmetodo1 FOREIGN KEY (CodiceT) REFERENCES TIPO (CodiceT)ON DELETE CASCADE;
/
ALTER TABLE METODO ADD CONSTRAINT fkmetodo2 FOREIGN KEY (IdC) REFERENCES CLASSE (IdC)ON DELETE CASCADE;
/
ALTER TABLE TIPO ADD CONSTRAINT fktipo1 FOREIGN KEY (IdC) REFERENCES CLASSE (IdC)ON DELETE CASCADE;
/
ALTER TABLE TIPO ADD CONSTRAINT fktipo2 FOREIGN KEY (CodiceE) REFERENCES ENUMERATION(CodiceE)ON DELETE CASCADE;
/
ALTER TABLE PARTECIPA ADD CONSTRAINT fkpartecipa1 FOREIGN KEY (CodAss) REFERENCES ASSOCIAZIONE (CodAss) ON DELETE CASCADE;
/
ALTER TABLE PARTECIPA ADD CONSTRAINT fkpartecipa2 FOREIGN KEY (IdC) REFERENCES CLASSE (IdC);
/
ALTER TABLE CLASSE_DI_ASSOCIAZIONE ADD CONSTRAINT fkclasse_di_associazione FOREIGN KEY (Codice) REFERENCES CLASSDIAGRAM (Codice) ON DELETE CASCADE;
/
ALTER TABLE ENUMERATION ADD	CONSTRAINT fkenumeration FOREIGN KEY (Codice) REFERENCES CLASSDIAGRAM (Codice)ON DELETE CASCADE;
/
ALTER TABLE PARAMETRO ADD  CONSTRAINT fkparametro FOREIGN KEY (CodiceT) REFERENCES  TIPO(CodiceT) ON DELETE CASCADE;
/
ALTER TABLE COMPONIMENTO ADD CONSTRAINT fkcomponimento1 FOREIGN KEY (CodiceE) REFERENCES ENUMERATION(CodiceE) ;
/
ALTER TABLE COMPONIMENTO ADD CONSTRAINT fkcomponimento2 FOREIGN KEY (NomeV) REFERENCES VALORE(NomeV) ON DELETE CASCADE;
/
ALTER TABLE  DOMINIO ADD CONSTRAINT fk1dominio FOREIGN KEY (Segnatura) REFERENCES METODO(Segnatura) ;
/
ALTER TABLE  DOMINIO ADD CONSTRAINT fk2dominio FOREIGN KEY (CodiceP) REFERENCES PARAMETRO(CodiceP) ;
/

INSERT INTO CLASSDIAGRAM VALUES('1','UML','CLASSDIAGRAM.UML',CURRENT_DATE,NULL);
COMMIT;

INSERT INTO Classe VALUES(1,'ClassDiagram',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(2,'Classe',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(3,'Attributo',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(4,'Associazione',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(5,'Commento',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(6,'Metodo',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(7,'Tipo',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(8,'Enumeration',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(9,'Classe_Di_Associazione',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(10,'Valore',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe_Di_Associazione VALUES(11,'Partecipa','1');
INSERT INTO Classe VALUES(11,'Partecipa',NULL,'1',NULL,NULL,NULL,11,NULL,CURRENT_DATE,NULL);
INSERT INTO Classe VALUES(12,'Parametro',NULL,'1',NULL,NULL,NULL,NULL,'classe_concreta',CURRENT_DATE,NULL);
INSERT INTO Classe_Di_Associazione VALUES(13,'Componimento','1');
INSERT INTO Classe VALUES(13,'Componimento',NULL,'1',NULL,NULL,NULL,13,NULL,CURRENT_DATE,NULL);
INSERT INTO Classe_Di_Associazione VALUES(14,'Dominio','1');
INSERT INTO Classe VALUES(14,'Dominio',NULL,'1',NULL,NULL,NULL,14,NULL,CURRENT_DATE,NULL);
COMMIT;

INSERT INTO Commento VALUES('1) Eliminazione della relazione composizione  (tra class e class diagram), trasformata in associazione con oppurtuna molteplicità.','1','1',CURRENT_DATE,NULL);
COMMIT;

INSERT INTO Enumeration VALUES(1,'TipoP','1');
INSERT INTO Enumeration VALUES(2,'Mod' , '1');
INSERT INTO Enumeration VALUES(3,'TipoC' , '1');
INSERT INTO Enumeration VALUES(4,'TipoBool' , '1');
COMMIT;

INSERT INTO VALORE VALUES ('integer');
INSERT INTO VALORE VALUES ('char');
INSERT INTO VALORE VALUES ('boolean');
INSERT INTO VALORE VALUES ('float');
INSERT INTO VALORE VALUES ('date');
INSERT INTO VALORE VALUES ('double');
INSERT INTO VALORE VALUES ('short');
INSERT INTO VALORE VALUES ('long');
INSERT INTO VALORE VALUES ('byte');
INSERT INTO VALORE VALUES ('Public');
INSERT INTO VALORE VALUES ('Private');
INSERT INTO VALORE VALUES ('Protected');
INSERT INTO VALORE VALUES ('Package');
INSERT INTO VALORE VALUES ('classe_astratta');
INSERT INTO VALORE VALUES ('classe_concreta');
INSERT INTO VALORE VALUES ('1');
INSERT INTO VALORE VALUES ('0');
COMMIT;

INSERT INTO COMPONIMENTO VALUES(9,'integer',1);
INSERT INTO COMPONIMENTO VALUES(9,'char',1);
INSERT INTO COMPONIMENTO VALUES(9,'boolean',1);
INSERT INTO COMPONIMENTO VALUES(9,'float',1);
INSERT INTO COMPONIMENTO VALUES(9,'date',1);
INSERT INTO COMPONIMENTO VALUES(9,'double',1 );
INSERT INTO COMPONIMENTO VALUES(9,'short',1);
INSERT INTO COMPONIMENTO VALUES(9,'long',1);
INSERT INTO COMPONIMENTO VALUES(9,'byte',1);
INSERT INTO COMPONIMENTO VALUES(4,'Private',2);
INSERT INTO COMPONIMENTO VALUES(4,'Public',2);
INSERT INTO COMPONIMENTO VALUES(4,'Package',2);
INSERT INTO COMPONIMENTO VALUES(4,'Protected',2);
INSERT INTO COMPONIMENTO VALUES(2,'classe_astratta',3);
INSERT INTO COMPONIMENTO VALUES(2,'classe_concreta',3);
INSERT INTO COMPONIMENTO VALUES(2,'1',4);
INSERT INTO COMPONIMENTO VALUES(2,'0',4);
COMMIT;

INSERT INTO Tipo VALUES(1 , 'integer' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(2 , 'char' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(3 , 'boolean' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(4 , 'float' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(5 , 'date' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(6 , 'double' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(7 , 'short' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(8 , 'long' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(9 , 'byte' ,NULL, NULL,NULL);
INSERT INTO Tipo VALUES(10 , NULL ,'String', NULL,NULL);
INSERT INTO Tipo VALUES(11 , NULL ,'Class Diagram' ,1,NULL);
INSERT INTO Tipo VALUES(12 , NULL ,'Classe',2,NULL);
INSERT INTO Tipo VALUES(13 , NULL ,'Attributo',3,NULL);
INSERT INTO Tipo VALUES(14 , NULL ,'Associazione',4,NULL);
INSERT INTO Tipo VALUES(15 , NULL ,'Commento',5,NULL);
INSERT INTO Tipo VALUES(16 , NULL ,'Metodo',6,NULL);
INSERT INTO Tipo VALUES(17 , NULL ,'Tipo',7,NULL);
INSERT INTO Tipo VALUES(18 , NULL ,'Enumeration',8,NULL);
INSERT INTO Tipo VALUES(19 , NULL ,'Classe_Di_Associazione',9,NULL);
INSERT INTO Tipo VALUES(20 , NULL ,'Valore',10,NULL); 
INSERT INTO Tipo VALUES(21 , NULL ,'Parametro',12,NULL);
INSERT INTO Tipo VALUES(22 , NULL ,NULL,NULL,1);  
INSERT INTO Tipo VALUES(23 , NULL ,NULL,NULL,2);  
INSERT INTO Tipo VALUES(24 , NULL ,NULL,NULL,3); 
INSERT INTO Tipo VALUES(25 , NULL ,NULL,NULL,4); 
COMMIT;


INSERT INTO Attributo VALUES(1,'Nome','1','Public',NULL,10,1,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(2,'FileC','1','Public',NULL,10,1,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(3,'Descrizione','1','Public', NULL ,10,5, NULL , CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(4,'CodiceC','1','Public', NULL, 1,5,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(5,'CodAss','1','Public',NULL, 1 , 4,NULL, CURRENT_DATE,NULL); 
INSERT INTO Attributo VALUES(6,'NomeAss','1','Public',NULL, 10, 4,NULL,  CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(7,'Grado','1','Public',NULL, 1 , 4,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(8,'IdC','1','Public',NULL, 1 , 2,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(9,'NomeC','1','Public',NULL, 10 , 2,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(10,'Descrizione','1','Public',NULL, 10 , 2,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(11,'NomeAt','1','Public',NULL, 10 , 3,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(12,'Cardinalità','1','Public',NULL, 10 , 3,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(13,'ModyfyAcc','1','Public',NULL, 23 , 3,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(14,'Descrizione','0..1','Public',NULL, 10 , 3,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(15,'CodiceA','1','Public',NULL, 1 , 3,NULL,  CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(16,'NomeA','1','Public',NULL, 10 , 9,NULL,  CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(17,'IdAss','1','Public',NULL, 10 , 9,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(18,'Ruoli','1','Public',NULL, 10 , 11,11, CURRENT_DATE,NULL);   
INSERT INTO Attributo VALUES(19,'Cardinalità','1','Public',NULL, 11 , 11,11,  CURRENT_DATE,NULL); 
INSERT INTO Attributo VALUES(20,'CodiceE','1','Public',NULL, 1 , 8,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(21,'NomeE','1','Public',NULL, 10 , 8,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(22,'Nomev','1','Public',NULL, 10 , 10,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(23,'Segnatura','1','Public',NULL, 10 , 6,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(24,'Nome','1','Public',NULL, 10 , 6,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(25,'ModyfyAcc','1','Public',NULL, 23 , 6,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(26,'Corpo','0..1','Public',NULL, 10 , 6,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(27,'TipoPrimitivo','1','Public',NULL, 22 , 7,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(28,'TipoClass','1','Public',NULL, 10 , 7,NULL,  CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(29,'CodiceT','1','Public',NULL, 1, 7,NULL,  CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(30,'DataInserimento','1','Public',NULL, 5, 5, NULL, CURRENT_DATE,NULL); 
INSERT INTO Attributo VALUES(31,'DataModifica','0..1','Public',NULL, 5, 5, NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(32,'DataCreazione','1','Public',NULL, 5, 1,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(33,'DataAggiornamento','0..1','Public',NULL, 5, 1 ,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(34,'DataInserimento','1','Public',NULL, 5, 3, NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(35,'DataModifica','0..1','Public',NULL, 5, 3, NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(36,'DataInserimento','1','Public',NULL, 5, 2, NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(37,'DataModifica','0..1','Public',NULL, 5, 2, NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(38,'Direzionalità','1','Public',NULL, 10, 11 ,11,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(39,'Procedura','1','Public',NULL, 25, 6,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(40,'Funzione','1','Public',NULL,25, 6 ,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(41,'TipoClasse','1','Public',NULL, 10, 2 ,NULL,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(42,'CodiceP','1','Public',NULL, 1,12,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(43,'NomeP','1','Public',NULL, 10, 12 ,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(44,'P_IN','1','Public',NULL, 25, 12,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(45,'P_OUT','1','Public',NULL, 25, 12 ,NULL, CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(46,'N_Parametri','1','Public',NULL, 1, 14,14,CURRENT_DATE,NULL);
INSERT INTO Attributo VALUES(47,'N_Valori','1','Public',NULL, 1, 13 ,13,CURRENT_DATE,NULL);
COMMIT;

  
INSERT INTO Associazione VALUES(1,'Contenimento',2,'1',NULL);
INSERT INTO Associazione VALUES(2,'Compreso',2,'1',NULL);
INSERT INTO Associazione VALUES(3,'Descrizione',2,'1',NULL);
INSERT INTO Associazione VALUES(4,'Possesso',2,'1',NULL);
INSERT INTO Associazione VALUES(5,'possesso',2,'1',NULL);
INSERT INTO Associazione VALUES(6,'Aggregazione',1,'1',NULL);
INSERT INTO Associazione VALUES(7,'Generalizzazione',1,'1',NULL);
INSERT INTO Associazione VALUES(8,'Consiste',2,'1',NULL);
INSERT INTO Associazione VALUES(9,'Composizione',1,'1',NULL);
INSERT INTO Associazione VALUES(10,'Realizzazione',2,'1',NULL);
INSERT INTO Associazione VALUES(11,'Partecipa',2,'1',11); 
INSERT INTO Associazione VALUES(12,'Dotazione',2,'1',NULL);
INSERT INTO Associazione VALUES(13,'Caratterizzazione',2,'1',NULL);
INSERT INTO Associazione VALUES(14,'Caratterizzazione',2,'1',NULL);
INSERT INTO Associazione VALUES(15,'Composizione',2,'1',NULL);
INSERT INTO Associazione VALUES(16,'Compreso',2,'1',NULL);
INSERT INTO Associazione VALUES(17,'Compreso',2,'1',NULL);
INSERT INTO Associazione VALUES(18,'Componimento',2,'1',13);
INSERT INTO Associazione VALUES(19,'Consistenza',2,'1',NULL);
INSERT INTO Associazione VALUES(20,'Caratterizzazione',2,'1',NULL);
INSERT INTO Associazione VALUES(21,'Dominio',2,'1',14);
COMMIT;
	
	
INSERT INTO Partecipa VALUES(1,'è contenuto','1',5,'bidirezionale');
INSERT INTO Partecipa VALUES(1,'contiene','*',1,'bidirezionale');
INSERT INTO Partecipa VALUES(2,'comprende','1..*',1,'bidirezionale');
INSERT INTO Partecipa VALUES(2,'è compresa','1',4,'bidirezionale');
INSERT INTO Partecipa VALUES(3,'è descritta','0..1',4,'bidirezionale');
INSERT INTO Partecipa VALUES(3,'descrive','1',9,'bidirezionale');
INSERT INTO Partecipa VALUES(4,'possiede','0..*',9,'bidirezionale');
INSERT INTO Partecipa VALUES(4,'è posseduto','0..1',3,'bidirezionale');
INSERT INTO Partecipa VALUES(5,'possiede','0..*',2,'bidirezionale');
INSERT INTO Partecipa VALUES(5,'è posseduto','0..1',3,'bidirezionale');
INSERT INTO Partecipa VALUES(6,'aggregata','1..*',2,'bidirezionale');
INSERT INTO Partecipa VALUES(6,'aggreganti','0..1',2,'bidirezionale');
INSERT INTO Partecipa VALUES(7,'generale','1..*',2,'bidirezionale');
INSERT INTO Partecipa VALUES(7,'specializzata','0..1',2,'bidirezionale');
INSERT INTO Partecipa VALUES(8,'è','1',9,'bidirezionale');
INSERT INTO Partecipa VALUES(8,'può essere','0..1',2,'bidirezionale');
INSERT INTO Partecipa VALUES(9,'composta','1..*',2,'bidirezionale');
INSERT INTO Partecipa VALUES(9,'componenti','0..1',2,'bidirezionale');
INSERT INTO Partecipa VALUES(10,'è realizzato','0..1',7,'bidirezionale');
INSERT INTO Partecipa VALUES(10,'realizza','1',2,'bidirezionale');
INSERT INTO Partecipa VALUES(11,'connette','1..*',4,'bidirezionale');  
INSERT INTO Partecipa VALUES(11,'è connessa','1..*',2,'bidirezionale'); 
INSERT INTO Partecipa VALUES(12,'è dote','1',6,'bidirezionale');
INSERT INTO Partecipa VALUES(12,'è dotata','0..*',2,'bidirezionale');
INSERT INTO Partecipa VALUES(13,'ha','0..1',6,'bidirezionale');
INSERT INTO Partecipa VALUES(13,'caratterizza','0..*',7,'bidirezionale');
INSERT INTO Partecipa VALUES(14,'caratterizza','0..*',7,'bidirezionale');
INSERT INTO Partecipa VALUES(14,'ha','0..1',3,'bidirezionale');
INSERT INTO Partecipa VALUES(15,'è composta','1..*',1,'bidirezionale');
INSERT INTO Partecipa VALUES(15,'compone','1',2,'bidirezionale');
INSERT INTO Partecipa VALUES(16,'comprende','0..*',1,'bidirezionale');
INSERT INTO Partecipa VALUES(16,'è compresa','1',4,'bidirezionale');
INSERT INTO Partecipa VALUES(17,'è compresa','1',8,'bidirezionale');
INSERT INTO Partecipa VALUES(17,'comprende','0..*',1,'bidirezionale');
INSERT INTO Partecipa VALUES(18,'è associato','1..*',10,'bidirezionale');
INSERT INTO Partecipa VALUES(18,'assume','1..*',8,'bidirezionale');
INSERT INTO Partecipa VALUES(19,'può essere ','0..1',7,'bidirezionale');
INSERT INTO Partecipa VALUES(19,'è','1',8,'bidirezionale');
INSERT INTO Partecipa VALUES(20,'caratterizza','0..*',7,'bidirezionale');
INSERT INTO Partecipa VALUES(20,'è caratterizzato','1',12,'bidirezionale');
INSERT INTO Partecipa VALUES(21,'ha','0..*',6,'bidirezionale');
INSERT INTO Partecipa VALUES(21,'è posseduto','1..*',12,'bidirezionale');
COMMIT ;



create or replace TRIGGER DELETE_PAR_AS
BEFORE DELETE ON CLASSE
FOR EACH ROW
BEGIN
  IF(:OLD.IDC=:OLD.IDASS)THEN
     DELETE FROM ASSOCIAZIONE WHERE IDASS = :OLD.IDASS;
   ELSE
      FOR I IN (SELECT CodAss FROM PARTECIPA WHERE :OLD.IdC = IdC)
          LOOP
               DELETE FROM PARTECIPA WHERE I.CodAss = CodAss;
               DELETE FROM ASSOCIAZIONE WHERE I.CodAss = CodAss;
           END LOOP;
    END IF;
END;
/


create or replace TRIGGER UPTipoC
BEFORE UPDATE OF NomeC ON CLASSE
FOR EACH ROW
BEGIN
	UPDATE CLASSDIAGRAM SET DataAggiornamento = CURRENT_DATE
	WHERE :OLD.Codice = Codice;
	UPDATE TIPO  SET TipoClasse = :NEW.NomeC WHERE IdC = :NEW.IdC;
	IF (:OLD.NomeC <> :NEW.NomeC ) THEN
		:NEW.DataModifica := CURRENT_DATE;
	END IF;
END;
/



create or replace TRIGGER UP_INS_CL_AT
BEFORE INSERT OR UPDATE ON ATTRIBUTO  
FOR EACH ROW
DECLARE 
I VARCHAR (30);
BEGIN
SELECT Codice INTO I  FROM CLASSE  WHERE :NEW.IdC = Idc;
	IF (INSERTING ) THEN
		UPDATE CLASSE SET DataModifica = CURRENT_DATE  
		WHERE ((:NEW.IdC = IdC) OR (:NEW.IdAss = IdAss) OR (:OLD.IdC = IdC) OR (:OLD.IdAss = IdAss) );
		UPDATE CLASSDIAGRAM SET DataAggiornamento = CURRENT_DATE
		WHERE I = Codice;
	END IF;
	IF (UPDATING) THEN
		IF ((:New.NomeAt <> :OLD.NomeAt) OR (:OLD.Descrizione IS NULL AND :NEW.Descrizione IS NOT NULL)
            OR(:NEW.ModifyAcc <> :OLD.ModifyAcc) OR (:NEW.Cardinalità <> :OLD.Cardinalità)OR (:NEW.Descrizione <> :OLD.Descrizione))THEN
			:NEW.DataModifica := CURRENT_DATE;
		END IF;
        UPDATE CLASSE SET DataModifica = CURRENT_DATE  
        WHERE ((:OLD.IdC = IdC) OR (:OLD.IdAss = IdAss)  );
		UPDATE CLASSDIAGRAM SET DataAggiornamento = CURRENT_DATE
		WHERE I = Codice;
	END IF;	
END;
/


create or replace TRIGGER UP_CL_ME
AFTER INSERT OR UPDATE ON METODO
FOR EACH ROW
DECLARE 
I VARCHAR (30);
BEGIN
	SELECT Codice INTO I  FROM CLASSE  WHERE :NEW.IdC = Idc;
		UPDATE CLASSE SET DataModifica = CURRENT_DATE  
		WHERE ((:NEW.IdC = IdC) OR (:OLD.IdC = IdC) );
		UPDATE CLASSDIAGRAM SET DataAggiornamento = CURRENT_DATE
		WHERE I = Codice;
END;
/  


create or replace TRIGGER Tipo_C 
AFTER INSERT ON CLASSE
FOR EACH ROW
BEGIN
    DECLARE 
	Tip INTEGER;
	BEGIN
	IF (:NEW.IdC IS NOT NULL AND :NEW.IdAss IS NULL)THEN
        SELECT MAX(CodiceT) +1 INTO Tip
	    FROM TIPO;
        INSERT INTO Tipo VALUES(Tip, NULL ,:NEW.NomeC, :NEW.IdC,NULL);
	END IF;
	UPDATE CLASSDIAGRAM SET DATAAGGIORNAMENTO = CURRENT_DATE
	WHERE :OLD.Codice=Codice;
   END;
END;
/


create or replace TRIGGER UPCommento   
BEFORE UPDATE OF Descrizione ON COMMENTO
FOR EACH ROW
BEGIN
    IF (:NEW.Descrizione <> :OLD.Descrizione) THEN
        :NEW.DataModifica := CURRENT_DATE;
		UPDATE CLASSDIAGRAM SET DATAAGGIORNAMENTO=CURRENT_DATE
		WHERE :OLD.Codice=Codice;
    END IF;
END;
/

create or replace TRIGGER DELETE_VAL_ENUM
BEFORE DELETE ON ENUMERATION
FOR EACH ROW
DECLARE
X INTEGER ;
BEGIN
    FOR I IN (SELECT NomeV  FROM COMPONIMENTO WHERE :OLD.CodiceE = CodiceE )
    LOOP
         SELECT COUNT(NomeV) INTO X FROM COMPONIMENTO WHERE NomeV=I.NomeV;
         DELETE FROM COMPONIMENTO WHERE NomeV=I.NomeV AND CodiceE=:OLD.CodiceE;
         IF(X=1)THEN
              DELETE FROM VALORE WHERE NomeV=I.NomeV;
         END IF;
    END LOOP; 
END;
/


create or replace TRIGGER DELETE_DOM_PA
BEFORE DELETE ON METODO
FOR EACH ROW
DECLARE
X INTEGER ;
BEGIN
      DELETE FROM DOMINIO WHERE  :OLD.SEGNATURA = SEGNATURA ;
END;
/

create or replace trigger UP_INS_CA
AFTER INSERT OR UPDATE ON CLASSE_DI_ASSOCIAZIONE
FOR EACH ROW
BEGIN
	IF(INSERTING)THEN
		INSERT INTO CLASSE VALUES(:NEW.IdAss, :NEW.NomeA, NULL, :NEW.Codice ,NULL, NULL ,NULL, :NEW.IdAss, NULL,CURRENT_DATE,NULL);
	END IF;
	IF(UPDATING)THEN
		UPDATE CLASSE SET NomeC=:NEW.NomeA WHERE IdAss=:NEW.IdAss;
	END IF;
END;
/

create or replace trigger UP_ClassD
BEFORE UPDATE ON CLASSDIAGRAM
FOR EACH ROW
BEGIN
	IF ((:NEW.Nome <> :OLD.Nome ) OR (:NEW.FileC <> :OLD.FileC)) THEN	
		:NEW.DataAggiornamento := CURRENT_DATE;
	END IF;
END;
/

create or replace trigger delete_cd
BEFORE DELETE ON classdiagram
FOR EACH ROW
DECLARE
BEGIN
	FOR i IN (SELECT codass FROM associazione WHERE codass NOT IN (SELECT codass FROM partecipa ))
    LOOP
         DELETE FROM associazione WHERE i.codass=codass;
    END LOOP;
END;
/


create or replace FUNCTION Cerca_At (Tab attributo.nomeat%type )
RETURN VARCHAR2 IS 
res1 VARCHAR(60):=' ';
res VARCHAR(60):=' ';
Mia_eccezione EXCEPTION;
BEGIN
FOR I IN (SELECT NomeAt FROM ATTRIBUTO)
    LOOP
		IF(Tab=I.NomeAt)THEN
			res:=tab;
		END IF;
    END LOOP;
    IF(res=tab)then 
		FOR N IN (SELECT NomeC FROM CLASSE C JOIN ATTRIBUTO A ON C.IdC = A.IdC OR C.IdAss = A.IdAss WHERE A.NomeAt=res)
            LOOP
				res1 := N.NomeC||' '||res1;
            END LOOP;
    ELSE 
           res1 := 'Attributo non presente in nessuna classe';
           RAISE Mia_eccezione;
    END IF ;
    RETURN res1;
    DBMS_OUTPUT.PUT_line(res1);
    EXCEPTION
    WHEN Mia_eccezione THEN
        return res1;
        DBMS_OUTPUT.PUT_line(res1);  
END;
/



create or replace FUNCTION Cerca_Ass (Tab associazione.nomeass%type ) 
RETURN VARCHAR2 IS
CURR VARCHAR2(50):='NO';
RES VARCHAR2(50):='';
Mia_eccezione EXCEPTION;
BEGIN
FOR N IN (SELECT CODASS  FROM ASSOCIAZIONE WHERE Tab  = NomeAss)
LOOP
    CURR:=N.CODASS;
END LOOP;
IF(CURR ='NO')THEN 
      RES:='Associazione non presente nel class diagram';
      RAISE Mia_eccezione;
ELSE
      FOR I IN (SELECT NOMEC
                FROM CLASSE C JOIN PARTECIPA P ON C.IDC=P.IDC
                WHERE P.CODASS=CURR)
      LOOP
            RES:=I.NOMEC||'----'||RES;
      END LOOP;
END IF;        
RETURN RES;
DBMS_OUTPUT.PUT_line(RES);
EXCEPTION
WHEN Mia_eccezione THEN
        return RES;
        DBMS_OUTPUT.PUT_line(RES);  
END;
/		