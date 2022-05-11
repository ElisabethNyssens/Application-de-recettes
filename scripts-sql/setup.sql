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
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;;

create table authors
(
    pseudo           varchar(15) not null
        primary key,
    first_name       varchar(30) not null,
    last_name        varchar(30) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;;


create table categories
(
    id varchar(10) not null
        primary key,
    name varchar(20) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;;

create table dietery_regimes
(
    id   varchar(10) not null
        primary key,
    name varchar(20) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;;

create table ingredient_categories
(
    id   varchar(10) not null
        primary key,
    name varchar(20) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;;

create table ingredients
(
    name varchar(30) not null
        primary key,
    unit varchar(10) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;;

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

insert into authors values ('bichon','Elisabeth','Nyssens');
insert into authors values ('marvin','Julien','Hanquet');
insert into authors values ('anonyme','Ano','Nyme');
insert into authors values ('frandubi','Françoise','Dubisy');
insert into authors values ('abdobeir','Abdo','Beirekdar');

insert into ingredients values ('Carotte','unité');
insert into ingredients values ('Oignon','unité');
insert into ingredients values ('Tomate','unité');
insert into ingredients values ('Concombre','unité');
insert into ingredients values ('Courgette','unité');
insert into ingredients values ('Aubergine','unité');
insert into ingredients values ('Pois chiches','g');
insert into ingredients values ('Lentilles','g');
insert into ingredients values ('Citron jaune','unité');
insert into ingredients values ('Citron vert','unité');
insert into ingredients values ('Fraise','g');
insert into ingredients values ('Epinards frais','g');
insert into ingredients values ('Noix','g');
insert into ingredients values ('Curry','c.à.c');
insert into ingredients values ('Curcuma','c.à.c');
insert into ingredients values ('Menthe','feuilles');
insert into ingredients values ('Coriandre','feuilles');
insert into ingredients values ('Vinaigre balsamique','c.à.s');
insert into ingredients values ('Graines de pavot','c.à.s');
insert into ingredients values ('Ail','gousse');
insert into ingredients values ('Gingembre','g');
insert into ingredients values ('Lait','cl');
insert into ingredients values ('Crème','cl');
insert into ingredients values ('Féta','g');
insert into ingredients values ('Parmesan','g');
insert into ingredients values ('Lait de coco','cl');
insert into ingredients values ('Beurre','g');
insert into ingredients values ('Farine','g');
insert into ingredients values ('Riz','g');
insert into ingredients values ('Pâtes','g');
insert into ingredients values ('Tofu','g');
insert into ingredients values ('Concentré de tomates','g');