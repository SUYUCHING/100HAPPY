
    alter table t_antertainment_game 
        drop constraint FK_qpapd91q2a5l5br11putwc9pn;

    alter table t_antertainment_game_category 
        drop constraint FK_8nfsst2b1qly0oo542ewy4vuv;

    alter table t_antertainment_game_type 
        drop constraint FK_kw6vnv12lgeb43sri90gxfb7d;

    alter table t_domain_setting 
        drop constraint FK_s2moaw3huvl54ky9b4qhh066;

    alter table t_entity_wallet 
        drop constraint FK_cwtkxw2qluia2ym7xg8q4sqrf;

    alter table t_entity_wallet_log 
        drop constraint FK_p542rhts46q6dj34orfodpjmt;

    alter table t_game_log 
        drop constraint FK_axfrcus6iepc9g7ksahvl3hu5;

    alter table t_game_log 
        drop constraint FK_7ffdqlaoy1xbp42yj9e0omrxt;

    alter table t_player 
        drop constraint FK_8dxkk8fyonnvfakr4rutwhlwe;

    alter table t_player_game_log 
        drop constraint FK_eyaxix84udjo8d9ftcg0v50w;

    alter table t_player_game_log 
        drop constraint FK_7rjxnfm6ckw5j1kcy44sp18w1;

    alter table t_player_game_status 
        drop constraint FK_7uub7mudv01cxkn6a8lnark3q;

    alter table t_player_game_status 
        drop constraint FK_7cabvx5xlue31anhgf5cf3flc;

    alter table t_player_login_track 
        drop constraint FK_h1sl62quaqcft9onqf68rll5q;

    alter table t_player_wallet 
        drop constraint FK_7arutjolwv4n1vx7wtc79l0gs;

    alter table t_player_wallet_log 
        drop constraint FK_rfvvgpa2epan4nuhnieykw6ny;

    alter table t_stie_log 
        drop constraint FK_837uu9ryxnmldv6ql5coj03oc;

    drop table if exists t_antertainment_city cascade;

    drop table if exists t_antertainment_game cascade;

    drop table if exists t_antertainment_game_category cascade;

    drop table if exists t_antertainment_game_type cascade;

    drop table if exists t_card cascade;

    drop table if exists t_domain_setting cascade;

    drop table if exists t_entity cascade;

    drop table if exists t_entity_wallet cascade;

    drop table if exists t_entity_wallet_log cascade;

    drop table if exists t_game_log cascade;

    drop table if exists t_player cascade;

    drop table if exists t_player_game_log cascade;

    drop table if exists t_player_game_status cascade;

    drop table if exists t_player_login_track cascade;

    drop table if exists t_player_wallet cascade;

    drop table if exists t_player_wallet_log cascade;

    drop table if exists t_stie_log cascade;

    drop table if exists t_wallet_setting cascade;

    drop sequence seq_antertainment_city_id;

    drop sequence seq_antertainment_game_category_id;

    drop sequence seq_antertainment_game_id;

    drop sequence seq_antertainment_game_type_id;

    drop sequence seq_card_id;

    drop sequence seq_domain_setting_id;

    drop sequence seq_entity_id;

    drop sequence seq_entity_wallet_id;

    drop sequence seq_entity_wallet_log_id;

    drop sequence seq_game_log_id;

    drop sequence seq_player_game_log_id;

    drop sequence seq_player_game_status_id;

    drop sequence seq_player_id;

    drop sequence seq_player_login_track_id;

    drop sequence seq_player_wallet_id;

    drop sequence seq_player_wallet_log_id;

    drop sequence seq_stie_log_id;

    drop sequence seq_wallet_setting_id;

    create table t_antertainment_city (
        f_id int8 not null,
        f_code varchar(255),
        f_name varchar(255),
        f_data text,
        primary key (f_id)
    );

    create table t_antertainment_game (
        f_id int8 not null,
        f_code varchar(255),
        f_name varchar(255),
        f_type int4,
        f_antertainment_game_type_id int8,
        primary key (f_id)
    );

    create table t_antertainment_game_category (
        f_id int8 not null,
        f_code varchar(255),
        f_name varchar(255),
        f_antertainment_city_id int8,
        primary key (f_id)
    );

    create table t_antertainment_game_type (
        f_id int8 not null,
        f_code varchar(255),
        f_name varchar(255),
        f_antertainment_game_category_id int8,
        primary key (f_id)
    );

    create table t_card (
        f_id int8 not null,
        f_p1 int4,
        f_p2 int4,
        f_p3 int4,
        f_p4 int4,
        f_p5 int4,
        f_p6 int4,
        f_p7 int4,
        f_p8 int4,
        f_p9 int4,
        f_p10 int4,
        f_p11 int4,
        f_p12 int4,
        f_p13 int4,
        f_s1 int4,
        f_s2 int4,
        f_s3 int4,
        f_s4 int4,
        f_s5 int4,
        f_s6 int4,
        f_s7 int4,
        f_s8 int4,
        f_s9 int4,
        f_s10 int4,
        f_s11 int4,
        f_s12 int4,
        f_s13 int4,
        f_h1 int4,
        f_h2 int4,
        f_h3 int4,
        f_h4 int4,
        f_h5 int4,
        f_h6 int4,
        f_h7 int4,
        f_h8 int4,
        f_h9 int4,
        f_h10 int4,
        f_h11 int4,
        f_h12 int4,
        f_h13 int4,
        f_b1 int4,
        f_b2 int4,
        f_b3 int4,
        f_b4 int4,
        f_b5 int4,
        f_b6 int4,
        f_b7 int4,
        f_b8 int4,
        f_b9 int4,
        f_b10 int4,
        f_b11 int4,
        f_b12 int4,
        f_b13 int4,
        primary key (f_id)
    );

    create table t_domain_setting (
        f_id int8 not null,
        f_domain varchar(255),
        f_auto_approve_reg int4,
        f_type int4,
        f_entity_id int8,
        primary key (f_id)
    );

    create table t_entity (
        f_id int8 not null,
        f_name varchar(255),
        f_code varchar(255),
        primary key (f_id)
    );

    create table t_entity_wallet (
        f_id int8 not null,
        f_credit numeric(19, 2),
        f_type int4,
        f_entity_id int8,
        primary key (f_id)
    );

    create table t_entity_wallet_log (
        f_id int8 not null,
        f_amount numeric(19, 2),
        f_before_credit numeric(19, 2),
        f_after_credit numeric(19, 2),
        f_type int4,
        f_create_date timestamp,
        f_remark varchar(255),
        f_action_by varchar(255),
        f_action_id int8,
        f_ref_id int8,
        f_ref_type int4,
        f_credit_type int4,
        f_entity_wallet_id int8,
        primary key (f_id)
    );

    create table t_game_log (
        f_id int8 not null,
        f_winner int4,
        f_card_str text,
        f_card_type int4,
        f_seq int4,
        f_create_date timestamp,
        f_number int4,
        f_card_id int8,
        f_antertainment_game_id int8,
        primary key (f_id)
    );

    create table t_player (
        f_id int8 not null,
        f_name varchar(255),
        f_password varchar(255),
        f_email varchar(255),
        f_phone varchar(255),
        f_status int4,
        f_create_date timestamp,
        f_create_ip varchar(255),
        f_login_date timestamp,
        f_login_ip varchar(255),
        f_last_login_date timestamp,
        f_last_login_ip varchar(255),
        f_online int4,
        f_ref_player_id varchar(255),
        f_entity_id int8,
        primary key (f_id)
    );

    create table t_player_game_log (
        f_id int8 not null,
        f_bet_amt numeric(19, 2),
        f_win_lose numeric(19, 2),
        f_create_date timestamp,
        f_player_id int8,
        f_antertainment_game_id int8,
        primary key (f_id)
    );

    create table t_player_game_status (
        f_id int8 not null,
        f_tt_win_lose numeric(19, 2),
        f_seq int4,
        f_status int4,
        f_tt_bet numeric(19, 2),
        f_create_date timestamp,
        f_end_date timestamp,
        f_player_id int8,
        f_antertainment_game_type_id int8,
        primary key (f_id)
    );

    create table t_player_login_track (
        f_id int8 not null,
        f_ip varchar(255),
        f_login_date timestamp,
        f_type int4,
        f_logout_date timestamp,
        f_login_url varchar(255),
        f_login_type int4,
        f_player_id int8 not null,
        primary key (f_id)
    );

    create table t_player_wallet (
        f_id int8 not null,
        f_credit numeric(19, 2),
        f_credit_yype int4,
        f_player_id int8,
        primary key (f_id)
    );

    create table t_player_wallet_log (
        f_id int8 not null,
        f_amount numeric(19, 2),
        f_before_credit numeric(19, 2),
        f_after_credit numeric(19, 2),
        f_type int4,
        f_create_date timestamp,
        f_remark varchar(255),
        f_action_by varchar(255),
        f_action_id int8,
        f_ref_id int8,
        f_ref_type int4,
        f_credit_type int4,
        f_player_wallet_id int8,
        primary key (f_id)
    );

    create table t_stie_log (
        f_id int8 not null,
        f_status int4,
        f_antertainment_game_id int8,
        primary key (f_id)
    );

    create table t_wallet_setting (
        f_id int8 not null,
        f_credit_yype int4,
        f_convert_credit_type int4,
        f_rate numeric(19, 2),
        primary key (f_id)
    );

    alter table t_antertainment_game 
        add constraint FK_qpapd91q2a5l5br11putwc9pn 
        foreign key (f_antertainment_game_type_id) 
        references t_antertainment_game_type;

    alter table t_antertainment_game_category 
        add constraint FK_8nfsst2b1qly0oo542ewy4vuv 
        foreign key (f_antertainment_city_id) 
        references t_antertainment_city;

    alter table t_antertainment_game_type 
        add constraint FK_kw6vnv12lgeb43sri90gxfb7d 
        foreign key (f_antertainment_game_category_id) 
        references t_antertainment_game_category;

    alter table t_domain_setting 
        add constraint FK_s2moaw3huvl54ky9b4qhh066 
        foreign key (f_entity_id) 
        references t_entity;

    alter table t_entity_wallet 
        add constraint FK_cwtkxw2qluia2ym7xg8q4sqrf 
        foreign key (f_entity_id) 
        references t_entity;

    alter table t_entity_wallet_log 
        add constraint FK_p542rhts46q6dj34orfodpjmt 
        foreign key (f_entity_wallet_id) 
        references t_entity_wallet;

    alter table t_game_log 
        add constraint FK_axfrcus6iepc9g7ksahvl3hu5 
        foreign key (f_card_id) 
        references t_card;

    alter table t_game_log 
        add constraint FK_7ffdqlaoy1xbp42yj9e0omrxt 
        foreign key (f_antertainment_game_id) 
        references t_antertainment_game;

    alter table t_player 
        add constraint FK_8dxkk8fyonnvfakr4rutwhlwe 
        foreign key (f_entity_id) 
        references t_entity;

    alter table t_player_game_log 
        add constraint FK_eyaxix84udjo8d9ftcg0v50w 
        foreign key (f_player_id) 
        references t_player;

    alter table t_player_game_log 
        add constraint FK_7rjxnfm6ckw5j1kcy44sp18w1 
        foreign key (f_antertainment_game_id) 
        references t_antertainment_game;

    alter table t_player_game_status 
        add constraint FK_7uub7mudv01cxkn6a8lnark3q 
        foreign key (f_player_id) 
        references t_player;

    alter table t_player_game_status 
        add constraint FK_7cabvx5xlue31anhgf5cf3flc 
        foreign key (f_antertainment_game_type_id) 
        references t_antertainment_game_type;

    alter table t_player_login_track 
        add constraint FK_h1sl62quaqcft9onqf68rll5q 
        foreign key (f_player_id) 
        references t_player;

    alter table t_player_wallet 
        add constraint FK_7arutjolwv4n1vx7wtc79l0gs 
        foreign key (f_player_id) 
        references t_player;

    alter table t_player_wallet_log 
        add constraint FK_rfvvgpa2epan4nuhnieykw6ny 
        foreign key (f_player_wallet_id) 
        references t_player_wallet;

    alter table t_stie_log 
        add constraint FK_837uu9ryxnmldv6ql5coj03oc 
        foreign key (f_antertainment_game_id) 
        references t_antertainment_game;

    create sequence seq_antertainment_city_id;

    create sequence seq_antertainment_game_category_id;

    create sequence seq_antertainment_game_id;

    create sequence seq_antertainment_game_type_id;

    create sequence seq_card_id;

    create sequence seq_domain_setting_id;

    create sequence seq_entity_id;

    create sequence seq_entity_wallet_id;

    create sequence seq_entity_wallet_log_id;

    create sequence seq_game_log_id;

    create sequence seq_player_game_log_id;

    create sequence seq_player_game_status_id;

    create sequence seq_player_id;

    create sequence seq_player_login_track_id;

    create sequence seq_player_wallet_id;

    create sequence seq_player_wallet_log_id;

    create sequence seq_stie_log_id;

    create sequence seq_wallet_setting_id;
