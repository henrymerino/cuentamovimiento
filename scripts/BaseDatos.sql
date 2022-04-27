/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     24/04/2022 12:13:05                          */
/*==============================================================*/

-- Database: BaseDatos

-- DROP DATABASE "BaseDatos";

CREATE DATABASE "BaseDatos"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


drop index CLIENTE_PK;

drop table CLIENTE;

drop index RELATIONSHIP_2_FK;

drop index CUENTA_PK;

drop table CUENTA;

drop index RELATIONSHIP_3_FK;

drop index MOVIMIENTOS_PK;

drop table MOVIMIENTOS;

drop index RELATIONSHIP_1_FK;

drop index PERSONA_PK;

drop table PERSONA;

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE (
   CLIENTEID            INT4                 not null,
   CONTRASENIA          VARCHAR(30)          null,
   ESTADO               BOOL                 null,
   constraint PK_CLIENTE primary key (CLIENTEID)
);

/*==============================================================*/
/* Index: CLIENTE_PK                                            */
/*==============================================================*/
create unique index CLIENTE_PK on CLIENTE (
CLIENTEID
);

/*==============================================================*/
/* Index: COMMENT CLIENTE                                     */
/*==============================================================*/
comment on column CLIENTE.CLIENTEID is 'Clave primaria';
comment on column CLIENTE.CONTRASENIA is 'Contrasenia del cliente';
comment on column CLIENTE.ESTADO is 'Estado del ciente true = Activo, False = Inactivo ';


/*==============================================================*/
/* Table: CUENTA                                                */
/*==============================================================*/
create table CUENTA (
   CUENTAID             INT4                 not null,
   CLIENTEID            INT4                 null,
   NUMEROCUENTA         INT4                 null,
   TIPOCUENTA           VARCHAR(20)          null,
   SALDOINICIAL         FLOAT20              null,
   ESTADO               BOOL                 null,
   constraint PK_CUENTA primary key (CUENTAID)
);

/*==============================================================*/
/* Index: CUENTA_PK                                             */
/*==============================================================*/
create unique index CUENTA_PK on CUENTA (
CUENTAID
);

/*==============================================================*/
/* Index: RELATIONSHIP_2_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_2_FK on CUENTA (
CLIENTEID
);


/*==============================================================*/
/* Index: COMMENT CUENTA                                     */
/*==============================================================*/
comment on column CUENTA.CUENTAID is 'Clave primaria';
comment on column CUENTA.CLIENTEID is 'Clave foranea tabla cliente';
comment on column CUENTA.NUMEROCUENTA is 'Numero de cuenta del cliente';
comment on column CUENTA.TIPOCUENTA is 'Ahorro, corriente';
comment on column CUENTA.SALDOINICIAL is 'Saldo inicial de la cuenta del cliente';
comment on column CUENTA.ESTADO is 'Estado de la cuenta true = Activo, False = Inactivo';

/*==============================================================*/
/* Table: MOVIMIENTOS                                           */
/*==============================================================*/
create table MOVIMIENTOS (
   MOVIMIENTOSID        INT4                 not null,
   CUENTAID             INT4                 null,
   FECHA                DATE                 null,
   TIPOMOVIMIENTO      VARCHAR(20)          null,
   VALOR                FLOAT20              null,
   SALDO                FLOAT20              null,
   constraint PK_MOVIMIENTOS primary key (MOVIMIENTOSID)
);

/*==============================================================*/
/* Index: MOVIMIENTOS_PK                                        */
/*==============================================================*/
create unique index MOVIMIENTOS_PK on MOVIMIENTOS (
MOVIMIENTOSID
);

/*==============================================================*/
/* Index: RELATIONSHIP_3_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_3_FK on MOVIMIENTOS (
CUENTAID
);

/*==============================================================*/
/* Index: COMMENT MOVIMIENTOS                                     */
/*==============================================================*/
comment on column MOVIMIENTOS.MOVIMIENTOSID is 'Clave primaria';
comment on column MOVIMIENTOS.CUENTAID is 'Clave foranea tabla Cuenta';
comment on column MOVIMIENTOS.FECHA is 'Fecha de movimiento';
comment on column MOVIMIENTOS.TIPOMOVIMIENTO is 'Retiro o deposito';
comment on column MOVIMIENTOS.VALOR is 'Valor de deposito o retiro';
comment on column MOVIMIENTOS.SALDO is 'Saldo disponible de la cuenta';

/*==============================================================*/
/* Table: PERSONA                                               */
/*==============================================================*/
create table PERSONA (
   PERSONAID            INT4                 not null,
   CLIENTEID            INT4                 null,
   NOMBRE               VARCHAR(50)          null,
   GENERO               VARCHAR(15)          null,
   EDAD                 INT4                 null,
   IDENTIFICACION       VARCHAR(20)          null,
   DIRECCION            VARCHAR(100)         null,
   TELEFONO             VARCHAR(10)          null,
   constraint PK_PERSONA primary key (PERSONAID)
);

/*==============================================================*/
/* Index: PERSONA_PK                                            */
/*==============================================================*/
create unique index PERSONA_PK on PERSONA (
PERSONAID
);

/*==============================================================*/
/* Index: RELATIONSHIP_1_FK                                     */
/*==============================================================*/
create  index RELATIONSHIP_1_FK on PERSONA (
CLIENTEID
);

/*==============================================================*/
/* Index: COMMENT PERSONA                                     */
/*==============================================================*/
comment on column PERSONA.PERSONAID is 'Clave primaria';
comment on column PERSONA.CLIENTEID is 'Clave foranea tabla Ciente';
comment on column PERSONA.NOMBRE is 'Nombres y apellidos de la persona';
comment on column PERSONA.GENERO is 'Masculino, Femenino';
comment on column PERSONA.EDAD is 'Edad persona';
comment on column PERSONA.IDENTIFICACION is 'Identificacion persona';
comment on column PERSONA.DIRECCION is 'Direccion';
comment on column PERSONA.TELEFONO is 'Telefono';

alter table CUENTA
   add constraint FK_CUENTA_RELATIONS_CLIENTE foreign key (CLIENTEID)
      references CLIENTE (CLIENTEID)
      on delete restrict on update restrict;

alter table MOVIMIENTOS
   add constraint FK_MOVIMIEN_RELATIONS_CUENTA foreign key (CUENTAID)
      references CUENTA (CUENTAID)
      on delete restrict on update restrict;

alter table PERSONA
   add constraint FK_PERSONA_RELATIONS_CLIENTE foreign key (CLIENTEID)
      references CLIENTE (CLIENTEID)
      on delete restrict on update restrict;



