create database App_de_recettes;

USE App_de_recettes;

CREATE TABLE authors
(
    pseudo varchar(15) PRIMARY KEY not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null
);

CREATE TABLE categories
(
    id varchar(10) PRIMARY KEY not null,
    cat_name varchar(20) not null
);

CREATE TABLE dietery_regimes
(
    id   varchar(10) PRIMARY KEY not null,
    dr_name varchar(20) not null
);

CREATE TABLE recipes
(
	id int PRIMARY KEY AUTO_INCREMENT not null,
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
    author varchar(15) not null,
	dietery_regime varchar(10) null,
	category varchar(10) not null,
    FOREIGN KEY (author) REFERENCES authors (pseudo),
	FOREIGN KEY (dietery_regime) REFERENCES dietery_regimes (id),
    FOREIGN KEY (category) REFERENCES categories (id)
);

CREATE TABLE ingredients
(
    ing_name varchar(30) PRIMARY KEY not null,
    unit varchar(10) not null
);

CREATE TABLE ingredient_quantities
(
    ingredient_id varchar(30) not null,
    recipe_id int not null,
    quantity int not null,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ing_name),
    FOREIGN KEY (recipe_id) REFERENCES recipes (id),
    PRIMARY KEY (ingredient_id, recipe_id)
);

CREATE TABLE steps
(
    order_number int not null,
    recipe_id int not null,
    content varchar(500) not null,
    FOREIGN KEY (recipe_id) REFERENCES recipes (id),
    PRIMARY KEY (order_number, recipe_id)
);

CREATE TABLE menus 
(
	id int PRIMARY KEY AUTO_INCREMENT not null,
    title varchar(100) not null,
    comment varchar(200) null
);

CREATE TABLE menu_components
(
	order_number int not null,
    menu_id int not null,
    recipe_id int not null,
    FOREIGN KEY (menu_id) REFERENCES menus (id),
    FOREIGN KEY (recipe_id) REFERENCES recipes (id),
    PRIMARY KEY(order_number, menu_id)
);


insert into authors values ('bichon','Elisabeth','Nyssens');
insert into authors values ('marvin','Julien','Hanquet');
insert into authors values ('anonyme','Ano','Nyme');
insert into authors values ('frandubi','Francoise','Dubisy');
insert into authors values ('abdobeir','Abdo','Beirekdar');


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


insert into ingredients values ('Ail','gousse');
insert into ingredients values ('Asperge verte', 'g');
insert into ingredients values ('Aubergine','unite');
insert into ingredients values ('Basilic', 'feuilles');
insert into ingredients values ('Beurre','g');
insert into ingredients values ('Bouillon de legumes', 'cubes');
insert into ingredients values ('Carotte','unite');
insert into ingredients values ('Chou-fleur', 'unite');
insert into ingredients values ('Ciboulette', 'c.a.s');
insert into ingredients values ('Citron jaune','unite');
insert into ingredients values ('Citron vert','unite');
insert into ingredients values ('Concentre de tomates','g');
insert into ingredients values ('Concombre','unite');
insert into ingredients values ('Coriandre','feuilles');
insert into ingredients values ('Courgette','unite');
insert into ingredients values ('Creme','cl');
insert into ingredients values ('Cumin', 'c.a.c');
insert into ingredients values ('Curcuma','c.a.c');
insert into ingredients values ('Curry','c.a.c');
insert into ingredients values ('Epinard frais','g');
insert into ingredients values ('Farine','g');
insert into ingredients values ('Feta','g');
insert into ingredients values ('Fraise','g');
insert into ingredients values ('Gingembre','g');
insert into ingredients values ('Graines de pavot','c.a.s');
insert into ingredients values ('Huile d\'arachide','cl');
insert into ingredients values ('Huile d\'olive','cl');
insert into ingredients values ('Jus de citron', 'cl');
insert into ingredients values ('Lait','cl');
insert into ingredients values ('Lait de coco','cl');
insert into ingredients values ('Lentilles','g');
insert into ingredients values ('Menthe','feuilles');
insert into ingredients values ('Miel', 'c.a.c');
insert into ingredients values ('Moutarde a l\'ancienne', 'c.a.C');
insert into ingredients values ('Noix','g');
insert into ingredients values ('Oignon jaune','unite');
insert into ingredients values ('Orange','unite');
insert into ingredients values ('Parmesan','g');
insert into ingredients values ('Pates','g');
insert into ingredients values ('Persil', 'c.a.s');
insert into ingredients values ('Pignons de pin', 'g');
insert into ingredients values ('Pois chiches','g');
insert into ingredients values ('Poivre','au feeling');
insert into ingredients values ('Riz','g');
insert into ingredients values ('Sel', 'au feeling');
insert into ingredients values ('Tofu','g');
insert into ingredients values ('Tomate','unite');
insert into ingredients values ('Vinaigre balsamique','c.a.s');
insert into ingredients values ('Vinaigre de cidre','c.a.s');
insert into ingredients values ('Vin blanc', 'cl');

insert into recipes
values (null,'Curry au tofu','2021-04-25',true,false,true,'Bon marche','Facile','Moyen',4,'hiver','bichon',null,'RC_PLAT');

insert into ingredient_quantities
values ('Pois chiches',1,250);
insert into ingredient_quantities
values ('Concentre de tomates',1,500);
insert into ingredient_quantities
values ('Oignon jaune',1,2);
insert into ingredient_quantities
values ('Ail',1,1);
insert into ingredient_quantities
values ('Gingembre',1,20);
insert into ingredient_quantities
values ('Cumin',1,2);
insert into ingredient_quantities
values ('Curry',1,2);
insert into ingredient_quantities
values ('Curcuma',1,1);
insert into ingredient_quantities
values ('Persil',1,2);
insert into ingredient_quantities
values ('Huile d\'arachide',1,2);
insert into ingredient_quantities
values ('Sel',1,2);
insert into ingredient_quantities
values ('Chou-fleur',1,1);
insert into ingredient_quantities
values ('Tofu',1,250);
insert into ingredient_quantities
values ('Riz',1,500);

insert into steps
values (1,1,'Egoutter les poids chiches, emincer les oignons et l\'ail, hacher le persil et couper le chou-fleur en petits morceaux');


insert into recipes
values (null,'Salade fraise menthe','2021-07-18',false,true,true,'Cout moyen','Tres facile','Rapide',4,'ete','marvin','D_VEGA','RC_ACC');

insert into ingredient_quantities
values ('Asperge verte',2,600);
insert into ingredient_quantities
values ('Fraise',2,400);
insert into ingredient_quantities
values ('Feta',2,250);
insert into ingredient_quantities
values ('Pignons de pin',2,80);
insert into ingredient_quantities
values ('Basilic',2,50);
insert into ingredient_quantities
values ('Moutarde a l\'ancienne',2,2);
insert into ingredient_quantities
values ('Miel',2,1);
insert into ingredient_quantities
values ('Vinaigre de cidre',2,1);
insert into ingredient_quantities
values ('Orange',2,1);
insert into ingredient_quantities
values ('Huile d\'olive',2,9);
insert into ingredient_quantities
values ('Sel',2,2);
insert into ingredient_quantities
values ('Poivre',2,1);


insert into recipes
values (null, 'Soupe carotte gingembre','2022-01-13',true,false,true,'Bon marche','Tres Facile','Moyen',4,'automne','anonyme','D_VEGE','RC_SOU');

insert into ingredient_quantities
values ('Beurre',3,50);
insert into ingredient_quantities
values ('Oignon jaune',3,1);
insert into ingredient_quantities
values ('Gingembre',3,50);
insert into ingredient_quantities
values ('Ail',3,3);
insert into ingredient_quantities
values ('Bouillon de legumes',3,2);
insert into ingredient_quantities
values ('Vin blanc',3,25);
insert into ingredient_quantities
values ('Carotte',3,4);
insert into ingredient_quantities
values ('Jus de citron',3,3);
insert into ingredient_quantities
values ('Poivre',3,0);
insert into ingredient_quantities
values ('Sel',3,0);
insert into ingredient_quantities
values ('Ciboulette',3,2);