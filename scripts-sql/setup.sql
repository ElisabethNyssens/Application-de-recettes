create database App_de_recettes;

use App_de_recettes;

create table recipes
(
    id    int          not null
        primary key,
    title varchar(100) not null,
    creation_date date not null,
    is_hot bit not null,
    is_sweet bit not null,
    is_salty bit not null,
    budget varchar(15) not null,
    difficulty varchar(15) not null,
    preparation_time varchar(15) not null,
    nb_persons int not null,
    season varchar(10) null,
    comment varchar(200) null
);

create table authors
(
    pseudo           varchar(15) not null
        primary key,
    first_name       varchar(30) not null,
    last_name        varchar(30) null,
    is_cooking_lover bit         not null
);


create table categories
(
    id varchar(10) not null
        primary key,
    name varchar(20) not null
);

create table dietery_regimes
(
    id   varchar(10) not null
        primary key,
    name varchar(20) not null
);

create table ingredient_categories
(
    id   varchar(10) not null
        primary key,
    name varchar(20) not null
);


insert into recipes
values (1, 'Curry au tofu','2021-04-25',true,false,true,'Bon marché','Facile','Moyen',4,'hiver',null);

insert into recipes
values (2, 'Salade fraise menthe','2021-07-18',false,true,true,'Coût moyen','Très facile','Rapide',4,'été',null);

insert into recipes
values (3, 'Soupe carotte gingembre','2022-01-13',true,false,true,'Bon marché','Très Facile','Moyen',4,'automne',null);


insert into categories values ('RC_ENT', 'Entrée');
insert into categories values ('RC_PLAT', 'Plat principal');
insert into categories values ('RC_DESS', 'Dessert');
insert into categories values ('RC_AMU_G', 'Amuse-gueule');
insert into categories values ('RC_ACC', 'Accompagnement');
insert into categories values ('RC_SNA', 'Snack');
insert into categories values ('RC_SAU', 'Sauce');
insert into categories values ('RC_BOI', 'Boisson');

insert into dietery_regimes values ('D_VEGA', 'Végan');
insert into dietery_regimes values ('D_VEGE', 'Végétarien');
insert into dietery_regimes values ('D_PESC', 'Péscétarien');
insert into dietery_regimes values ('D_SG', 'Sans gluten');

insert into ingredient_categories values ('IC_VIAN', 'Viande');
insert into ingredient_categories values ('IC_POIS', 'Poisson');
insert into ingredient_categories values ('IC_MER', 'Fruit de mer');
insert into ingredient_categories values ('IC_LAIT', 'Produit laitier');
insert into ingredient_categories values ('IC_RUCH', 'Produit de la ruche');
insert into ingredient_categories values ('IC_OEUF', 'Oeuf');
insert into ingredient_categories values ('IC_AUTR', 'Autre');

insert into authors values ('bichon','Elisabeth','Nyssens',true);
insert into authors values ('marvin','Julien','Hanquet',true);
insert into authors values ('anonyme','Ano','Nyme',false);