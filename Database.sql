/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  03/03/2015 14:24:25                      */
/*==============================================================*/


drop table if exists MESSAGES;

drop table if exists NESWGROUP;

drop table if exists POST;

drop table if exists USER;

/*==============================================================*/
/* Table : MESSAGES                                             */
/*==============================================================*/
create table MESSAGES
(
   ID_MESSAGE           int not null auto_increment,
   ID_USER_FROM         int not null,
   ID_USER_TO           int not null,
   CONTENT              varchar(255),
   CREATED_AT           timestamp,
   primary key (ID_MESSAGE)
);

/*==============================================================*/
/* Table : NESWGROUP                                            */
/*==============================================================*/
create table NESWGROUP
(
   ID_NEWSGROUP         int not null auto_increment,
   NAME                 varchar(255) not null,
   primary key (ID_NEWSGROUP)
);

/*==============================================================*/
/* Table : POST                                                 */
/*==============================================================*/
create table POST
(
   ID_POST              int not null,
   ID_USER_FROM         int not null,
   ID_NEWSGROUP         int not null,
   ID_USER_TO           int not null,
   CONTENT              varchar(255),
   CREATED_AT           timestamp,
   primary key (ID_POST)
);

/*==============================================================*/
/* Table : USER                                                 */
/*==============================================================*/
create table USER
(
   ID_USER              int not null auto_increment,
   ID_NEWSGROUP         int not null,
   USERNAME             varchar(255) not null,
   ROLE                 int not null,
   MAILBOX              varchar(255),
   PASSWORD             varchar(255),
   READ_RIGHT           bool not null,
   WRITE_RIGHT          bool not null,
   primary key (ID_USER)
);

alter table MESSAGES add constraint FK_RECEIVE foreign key (ID_USER_FROM)
      references USER (ID_USER) on delete restrict on update restrict;

alter table MESSAGES add constraint FK_SEND foreign key (ID_USER_TO)
      references USER (ID_USER) on delete restrict on update restrict;

alter table POST add constraint FK_INCLUDE foreign key (ID_NEWSGROUP)
      references NESWGROUP (ID_NEWSGROUP) on delete restrict on update restrict;

alter table POST add constraint FK_PUBLISH foreign key (ID_USER_FROM)
      references USER (ID_USER) on delete restrict on update restrict;

alter table POST add constraint FK_READ foreign key (ID_USER_TO)
      references USER (ID_USER) on delete restrict on update restrict;

alter table USER add constraint FK_PARTICIPATE foreign key (ID_NEWSGROUP)
      references NESWGROUP (ID_NEWSGROUP) on delete restrict on update restrict;

