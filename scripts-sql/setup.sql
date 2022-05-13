create database App_de_recettes;

USE App_de_recettes;

CREATE TABLE authors
(
    pseudo varchar(15) PRIMARY KEY,
    first_name varchar(30) not null,
    last_name varchar(30) not null
);

CREATE TABLE categories
(
    id varchar(10) PRIMARY KEY,
    cat_name varchar(20) not null
);

CREATE TABLE dietery_regimes
(
    id   varchar(10) PRIMARY KEY,
    dr_name varchar(20) not null
);

CREATE TABLE recipes
(
	id int PRIMARY KEY AUTO_INCREMENT,
    title varchar(100) not null,
    creation_date date not null,
    is_hot bit not null,
    is_sweet bit not null,
    is_salty bit not null,
    budget varchar(15) not null,
    difficulty varchar(15) not null,
    preparation_time varchar(15) not null,
    nb_persons int not null,
    season varchar(10),
    author varchar(15) not null,
	dietery_regime varchar(10),
	category varchar(10) not null,
    FOREIGN KEY (author) REFERENCES authors (pseudo),
	FOREIGN KEY (dietery_regime) REFERENCES dietery_regimes (id),
    FOREIGN KEY (category) REFERENCES categories (id)
);

CREATE TABLE ingredients
(
    ing_name varchar(30) PRIMARY KEY,
    unit varchar(10) not null
);

CREATE TABLE ingredient_quantities
(
    ingredient_id varchar(30) ,
    recipe_id     int,
    quantity      int         not null,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ing_name),
    FOREIGN KEY (recipe_id) REFERENCES recipes (id),
    PRIMARY KEY (ingredient_id, recipe_id)
);

CREATE TABLE steps
(
    order_number      int,
    recipe_id   int,
    content varchar(400) not null,
    FOREIGN KEY (recipe_id) REFERENCES recipes (id),
    PRIMARY KEY (order_number, recipe_id)
);

CREATE TABLE menus 
(
	id int PRIMARY KEY AUTO_INCREMENT,
    title varchar(100) not null,
    comment varchar(200)
);

CREATE TABLE menu_components
(
	order_number int not null,
    menu_id int ,
    recipe_id int not null,
    FOREIGN KEY (menu_id) REFERENCES menus (id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (id),
    PRIMARY KEY(order_number, menu_id)
);

insert into recipes
values ('Curry au tofu','2021-04-25',true,false,true,'Bon marche','Facile','Moyen',4,'hiver','bichon',null,'RC_PLAT');
insert into recipes
values ('Salade fraise menthe','2021-07-18',false,true,true,'Cout moyen','Tres facile','Rapide',4,'ete','marvin','D_VEGA','RC_DESS');
insert into recipes
values ('Soupe carotte gingembre','2022-01-13',true,false,true,'Bon marche','Tres Facile','Moyen',4,'automne','anonyme','D_VEGE','RC_SOU');


insert into categories values ('RC_ENT', 'Entree');
insert into categories values ('RC_PLAT', 'Plat principal');
insert into categories values ('RC_DESS', 'Dessert');
insert into categories values ('RC_AMU_G', 'Amuse-gueule');
insert into categories values ('RC_ACC', 'Accompagnement');
insert into categories values ('RC_SNA', 'Snack');
insert into categories values ('RC_SAU', 'Sauce');
insert into categories values ('RC_BOI', 'Boisson');
insert into categories values ('RC_SOU', 'Soupe');

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