create database App_de_recettes;

use App_de_recettes;

create table recipes
(
    id    int          not null
        primary key,
    title varchar(100) not null
);

create table category
(
    id varchar(10) not null
        primary key,
    name varchar(20) not null
);

create table dietery_regime
(
    id   varchar(10) not null
        primary key,
    name varchar(20) not null
);

create table ingredient_category
(
    id   varchar(10) not null
        primary key,
    name varchar(20) not null
);

insert into recipes (id, title) values (1, 'Curry au tofu');
insert into recipes (id, title) values (2, 'Salade fraise menthe');
insert into recipes (id, title) values (3, 'Soupe carotte gingembre');

insert into category values ('RC_ENT', 'Entrée');
insert into category values ('RC_PLAT', 'Plat principal');
insert into category values ('RC_DESS', 'Dessert');
insert into category values ('RC_AMU_G', 'Amuse-gueule');
insert into category values ('RC_ACC', 'Accompagnement');
insert into category values ('RC_SNA', 'Snack');
insert into category values ('RC_SAU', 'Sauce');
insert into category values ('RC_BOI', 'Boisson');

insert into dietery_regime values ('D_VEGA', 'Végan');
insert into dietery_regime values ('D_VEGE', 'Végétarien');
insert into dietery_regime values ('D_PESC', 'Péscétarien');
insert into dietery_regime values ('D_SG', 'Sans gluten');

insert into ingredient_category values ('IC_VIAN', 'Viande');
insert into ingredient_category values ('IC_POIS', 'Poisson');
insert into ingredient_category values ('IC_MER', 'Fruit de mer');
insert into ingredient_category values ('IC_LAIT', 'Produit laitier');
insert into ingredient_category values ('IC_RUCH', 'Produit de la ruche');
insert into ingredient_category values ('IC_OEUF', 'Oeuf');
insert into ingredient_category values ('IC_AUTR', 'Autre');