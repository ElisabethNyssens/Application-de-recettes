create database App_de_recettes;

use App_de_recettes;

create table recipes
(
    id               int auto_increment
        primary key,
    title            varchar(100) not null,
    creation_date    date         not null,
    is_hot           bit          not null,
    is_sweet         bit          not null,
    is_salty         bit          not null,
    budget           varchar(15)  not null,
    difficulty       varchar(15)  not null,
    preparation_time varchar(15)  not null,
    nb_persons       int          not null,
    season           varchar(10)  null,
    author           varchar(15)  not null,
    dietery_regime   varchar(10)  null,
    category         varchar(10)  not null,
    constraint recipes_ibfk_1
        foreign key (author) references authors (pseudo),
    constraint recipes_ibfk_2
        foreign key (dietery_regime) references dietery_regimes (id),
    constraint recipes_ibfk_3
        foreign key (category) references categories (id)
);

create index author
    on recipes (author);

create index category
    on recipes (category);

create index dietery_regime
    on recipes (dietery_regime);


create table authors
(
    pseudo           varchar(15) not null
        primary key,
    first_name       varchar(30) not null,
    last_name        varchar(30) not null
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

create table ingredients
(
    name varchar(30) not null
        primary key,
    unit varchar(10) not null
);

create table ingredient_quantities
(
    ingredient_id varchar(30)  not null,
    recipe_id     varchar(100) not null,
    quantity      int          not null,
    primary key (ingredient_id, recipe_id)
);

create table steps
(
    number      int          not null,
    recipe_id   varchar(100) not null,
    description varchar(200) not null,
    primary key (number, recipe_id)
);

insert into recipes
values (null,'Curry au tofu','2021-04-25',true,false,true,'Bon marche','Facile','Moyen',4,'hiver','marvin','D_VEGA','RC_PLAT');
insert into recipes
values (null,'Salade fraise menthe','2021-07-18',false,true,true,'Cout moyen','Tres facile','Rapide',4,'ete','bichon','D_VEGE','RC_ACC');
insert into recipes
values (null,'Soupe carotte gingembre','2022-01-13',true,false,true,'Bon marche','Tres Facile','Moyen',4,'automne','marvin','D_VEGE','RC_SOU');

insert into categories values ('RC_ENT', 'Entree');
insert into categories values ('RC_PLAT', 'Plat principal');
insert into categories values ('RC_DESS', 'Dessert');
insert into categories values ('RC_AMU_G', 'Amuse-gueule');
insert into categories values ('RC_ACC', 'Accompagnement');
insert into categories values ('RC_SNA', 'Snack');
insert into categories values ('RC_SAU', 'Sauce');
insert into categories values ('RC_BOI', 'Boisson');

insert into dietery_regimes values ('D_VEGA', 'Vegan');
insert into dietery_regimes values ('D_VEGE', 'Vegetarien');
insert into dietery_regimes values ('D_PESC', 'Pescetarien');
insert into dietery_regimes values ('D_SG', 'Sans gluten');

insert into authors values ('bichon','Elisabeth','Nyssens');
insert into authors values ('marvin','Julien','Hanquet');
insert into authors values ('anonyme','Ano','Nyme');
insert into authors values ('frandubi','Francoise','Dubisy');
insert into authors values ('abdobeir','Abdo','Beirekdar');

insert into ingredients values ('Carotte','unite');
insert into ingredients values ('Oignon','unite');
insert into ingredients values ('Tomate','unite');
insert into ingredients values ('Concombre','unite');
insert into ingredients values ('Courgette','unite');
insert into ingredients values ('Aubergine','unite');
insert into ingredients values ('Pois chiches','g');
insert into ingredients values ('Lentilles','g');
insert into ingredients values ('Citron jaune','unite');
insert into ingredients values ('Citron vert','unite');
insert into ingredients values ('Fraise','g');
insert into ingredients values ('Epinards frais','g');
insert into ingredients values ('Noix','g');
insert into ingredients values ('Curry','c.a.c');
insert into ingredients values ('Curcuma','c.a.c');
insert into ingredients values ('Menthe','feuilles');
insert into ingredients values ('Coriandre','feuilles');
insert into ingredients values ('Vinaigre balsamique','c.a.s');
insert into ingredients values ('Graines de pavot','c.a.s');
insert into ingredients values ('Ail','gousse');
insert into ingredients values ('Gingembre','g');
insert into ingredients values ('Lait','cl');
insert into ingredients values ('Creme','cl');
insert into ingredients values ('Feta','g');
insert into ingredients values ('Parmesan','g');
insert into ingredients values ('Lait de coco','cl');
insert into ingredients values ('Beurre','g');
insert into ingredients values ('Farine','g');
insert into ingredients values ('Riz','g');
insert into ingredients values ('Pates','g');
insert into ingredients values ('Tofu','g');
insert into ingredients values ('Concentre de tomates','g');

insert into ingredient_quantities
values ('Lait de coco', 'Curry au tofu',20);
insert into ingredient_quantities
values ('Riz', 'Curry au tofu',500);
insert into ingredient_quantities
values ('Tofu', 'Curry au tofu',250);
insert into ingredient_quantities
values ('Oignon', 'Curry au tofu',1);