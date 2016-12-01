/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/11/25 20:44:47                          */
/*==============================================================*/

drop database if exists db_jxc;
create database db_jxc;

use db_jxc;

/*==============================================================*/
/* Table: tb_gysinfo                                            */
/*==============================================================*/
create table tb_gysinfo
(
   id                   varchar(50) not null,
   name                 varchar(60),
   jc                   varchar(50),
   address              varchar(100),
   bianma               varchar(50),
   tel                  varchar(50),
   fax                  varchar(50),
   lian                 varchar(50),
   ltel                 varchar(50),
   yh                   varchar(50),
   mail                 varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_khinfo                                             */
/*==============================================================*/
create table tb_khinfo
(
   id                   varchar(50) not null,
   khname               varchar(50),
   jian                 varchar(50),
   address              varchar(100),
   bianma               varchar(50),
   tel                  varchar(50),
   fax                  varchar(50),
   lian                 varchar(50),
   ltel                 varchar(50),
   mail                 varchar(50),
   xinhang              varchar(60),
   hao                  varchar(60),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_kucun                                              */
/*==============================================================*/
create table tb_kucun
(
   id                   varchar(30) not null,
   spname               varchar(50) not null,
   jc                   varchar(25),
   cd                   varchar(50),
   gg                   varchar(50),
   bz                   varchar(50),
   dw                   varchar(10),
   dj                   float(8,2),
   kcsl                 int,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_rkth_detail                                        */
/*==============================================================*/
create table tb_rkth_detail
(
   id                   int not null auto_increment,
   rkthID               varchar(30) not null,
   spid                 varchar(50) not null,
   dj                   float(8,2) not null,
   sl                   int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_rkth_main                                          */
/*==============================================================*/
create table tb_rkth_main
(
   rkthID               varchar(30) not null,
   pzs                  float(15) not null,
   je                   float(8,2) not null,
   ysjl                 varchar(50),
   gysname              varchar(100) not null,
   rtdate               datetime not null,
   czy                  varchar(30) not null,
   jsr                  varchar(30) not null,
   jsfs                 varchar(10) not null,
   primary key (rkthID)
);

/*==============================================================*/
/* Table: tb_ruku_detail                                        */
/*==============================================================*/
create table tb_ruku_detail
(
   id                   int not null auto_increment,
   rkID                 varchar(30) not null,
   spid                 varchar(50) not null,
   dj                   float(8,2) not null,
   sl                   int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_ruku_main                                          */
/*==============================================================*/
create table tb_ruku_main
(
   rkID                 varchar(30) not null,
   pzs                  int not null,
   je                   float(8,2) not null,
   ysjl                 varchar(50) not null,
   gysname              varchar(100) not null,
   rkdate               datetime not null,
   czy                  varchar(30) not null,
   jsr                  varchar(30) not null,
   jsfs                 varchar(10) not null,
   primary key (rkID)
);

/*==============================================================*/
/* Table: tb_sell_detail                                        */
/*==============================================================*/
create table tb_sell_detail
(
   id                   int not null auto_increment,
   sellID               varchar(30) not null,
   spid                 varchar(50) not null,
   dj                   float(8,2) not null,
   sl                   float(15) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_sell_main                                          */
/*==============================================================*/
create table tb_sell_main
(
   sellID               varchar(30) not null,
   pzs                  int not null,
   je                   float(8,2) not null default 0,
   ysjl                 varchar(50) default '0',
   khname               varchar(100) not null,
   xsdate               datetime not null,
   czy                  varchar(30) not null,
   jsr                  varchar(30) not null,
   jsfs                 varchar(10) not null,
   primary key (sellID)
);

/*==============================================================*/
/* Table: tb_spinfo                                             */
/*==============================================================*/
create table tb_spinfo
(
   id                   varchar(50) not null,
   spname               varchar(50) not null,
   jc                   varchar(50),
   cd                   varchar(60),
   dw                   varchar(50) not null,
   gg                   varchar(50) not null,
   bz                   varchar(50),
   ph                   varchar(50),
   pzwh                 varchar(50),
   memo                 varchar(90),
   gysname              varchar(100),
   primary key (id)
);

/*==============================================================*/
/* Index: IX_tab_spinfo                                         */
/*==============================================================*/
create index IX_tab_spinfo on tb_spinfo
(
   id
);

/*==============================================================*/
/* Table: tb_userlist                                           */
/*==============================================================*/
create table tb_userlist
(
   name                 varchar(50) not null,
   username             varchar(50) not null,
   pass                 varchar(50) not null,
   quan                 varchar(2) not null,
   primary key (name)
);

/*==============================================================*/
/* Table: tb_xsth_detail                                        */
/*==============================================================*/
create table tb_xsth_detail
(
   id                   int not null auto_increment,
   xsthID               varchar(30) not null,
   spid                 varchar(50) not null,
   dj                   float(8,2) not null,
   sl                   int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_xsth_main                                          */
/*==============================================================*/
create table tb_xsth_main
(
   xsthID               varchar(30) not null,
   pzs                  int not null,
   je                   float(8,2) not null,
   ysjl                 varchar(50),
   khname               varchar(100) not null,
   thdate               datetime not null,
   czy                  varchar(30) not null,
   jsr                  varchar(30) not null,
   jsfs                 varchar(10) not null,
   primary key (xsthID)
);

alter table tb_rkth_detail add constraint FK_tb_rkth_detail_tb_rkth_main foreign key (rkthID)
      references tb_rkth_main (rkthID) on delete cascade on update restrict;

alter table tb_ruku_detail add constraint FK_tb_ruku_detail_tb_ruku_main foreign key (rkID)
      references tb_ruku_main (rkID) on delete cascade on update cascade;

alter table tb_sell_detail add constraint FK_tb_sell_detail_tb_sell_main foreign key (sellID)
      references tb_sell_main (sellID) on delete cascade on update restrict;

alter table tb_xsth_detail add constraint FK_tb_xsth_detail_tb_xsth_main foreign key (xsthID)
      references tb_xsth_main (xsthID) on delete cascade on update restrict;

