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
insert into ingredients values ('Asperge verte','g');
insert into ingredients values ('Aubergine','unite');
insert into ingredients values ('Bacon','tranches');
insert into ingredients values ('Baguette','unite');
insert into ingredients values ('Basilic','feuilles');
insert into ingredients values ('Beurre','g');
insert into ingredients values ('Bouillon de legumes','cubes');
insert into ingredients values ('Carotte','unite');
insert into ingredients values ('Chou-fleur','unite');
insert into ingredients values ('Ciboulette','c.a.s');
insert into ingredients values ('Citron jaune','unite');
insert into ingredients values ('Citron vert','unite');
insert into ingredients values ('Concentre de tomates','g');
insert into ingredients values ('Concombre','unite');
insert into ingredients values ('Coriandre','feuilles');
insert into ingredients values ('Courgette','unite');
insert into ingredients values ('Creme','cl');
insert into ingredients values ('Cumin','c.a.c');
insert into ingredients values ('Curcuma','c.a.c');
insert into ingredients values ('Curry','c.a.c');
insert into ingredients values ('Epinard frais','g');
insert into ingredients values ('Farine','g');
insert into ingredients values ('Feta','g');
insert into ingredients values ('Fraise','g');
insert into ingredients values ('Fromage frais','g');
insert into ingredients values ('Gambas','unite');
insert into ingredients values ('Gingembre','g');
insert into ingredients values ('Graines de cumin','c.a.c');
insert into ingredients values ('Graines de pavot','c.a.s');
insert into ingredients values ('Gruyere rape','g');
insert into ingredients values ('Huile d\'arachide','c.a.s');
insert into ingredients values ('Huile d\'olive','c.a.s');
insert into ingredients values ('Jaune d\'oeuf','unite');
insert into ingredients values ('Jus de citron', 'cl');
insert into ingredients values ('Lait','cl');
insert into ingredients values ('Lait de coco','cl');
insert into ingredients values ('Lentilles','g');
insert into ingredients values ('Melange 5 baies','c.a.c');
insert into ingredients values ('Menthe','feuilles');
insert into ingredients values ('Miel','c.a.c');
insert into ingredients values ('Moutarde','c.a.c');
insert into ingredients values ('Moutarde a l\'ancienne','c.a.c');
insert into ingredients values ('Muscade','c.a.c');
insert into ingredients values ('Nectarine','unite');
insert into ingredients values ('Noix','g');
insert into ingredients values ('Oeufs','unite');
insert into ingredients values ('Oignon jaune','unite');
insert into ingredients values ('Orange','unite');
insert into ingredients values ('Parmesan','g');
insert into ingredients values ('Pates','g');
insert into ingredients values ('Persil','c.a.s');
insert into ingredients values ('Pignons de pin','g');
insert into ingredients values ('Pois chiches','g');
insert into ingredients values ('Poivre','c.a.c');
insert into ingredients values ('Rhum','cl');
insert into ingredients values ('Riz','g');
insert into ingredients values ('Saucisse de Strasbourg','unite');
insert into ingredients values ('Sel','c.a.c');
insert into ingredients values ('Sesame','c.a.c');
insert into ingredients values ('Sucre','g');
insert into ingredients values ('Tofu','g');
insert into ingredients values ('Tomate','unite');
insert into ingredients values ('Vinaigre a l\'estragon','c.a.s');
insert into ingredients values ('Vinaigre balsamique','c.a.s');
insert into ingredients values ('Vinaigre de cidre','c.a.s');
insert into ingredients values ('Vin blanc','cl');
insert into ingredients values ('Yaourt nature','unite');

insert into recipes
values ('Curry au tofu','2021-04-25',true,false,true,'Bon marche','Facile','Moyen',4,'hiver','bichon',null,'RC_PLAT');

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
values ('Graines de cumin',1,2);
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
values (1,1,"Egoutter les poids chiches, emincer les oignons et l'ail, hacher le persil et couper le chou-fleur en petits morceaux");
insert into steps
values (2,1,"Verser l'huile dans une sauteuse. Lorsqu'elle est chaude, y ajouter les oignons et les laisser revenir à feu moyen jusqu'a ce qu'ils commencent à colorer.");
insert into steps
values (3,1,"Ajouter les graines de cumin, melanger aux oignons et laisser cuire une minute.");
insert into steps
values (4,1,"Ajouter l'ail et le gingembre, melanger et laisser cuire encore une minute.");
insert into steps
values (5,1,"Ajouter le curry et le curcuma, melanger et laisser cuire une minute sans faire bruler les epices.");
insert into steps
values (6,1,"Delayer avec la puree de tomate, ajouter le chou fleur, les pois chiches, le tofu, le sel et un verre d'eau. Bien melanger et laisser mijoter a couvert jusqu'a ce que le chou-fleur soit bien tendre. Ajoutez un peu d'eau en cour de route si le melange s'asseche.");
insert into steps
values (7,1,"Ajouter le persil avant de servir.");


insert into recipes
values ('Salade fraise menthe','2021-07-18',false,true,true,'Cout moyen','Tres facile','Rapide',4,'ete','marvin','D_VEGA','RC_ACC');

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
values ('Huile d\'olive',2,6);
insert into ingredient_quantities
values ('Sel',2,2);
insert into ingredient_quantities
values ('Poivre',2,1);

insert into steps
values (1,2,"Pelez les asperges, coupez les bouts plus secs et faites-les cuire a l'eau bouillante salée, 7 min. Plongez-les dans de l'eau glacee. Egouttez et coupez-les en tronçons de 3 cm.");
insert into steps
values (2,2,"Faites dorer les pignons 2 min dans une poele antiadhesive.");
insert into steps
values (3,2,"Lavez et equeutez les fraises. Coupez-les en quartiers.");
insert into steps
values (4,2,"Preparez la vinaigrette : dans un bol, melangez la moutarde, avec le miel et le vinaigre. Emulsionnez avec le jus d’orange, l'huile d’olive, du sel et du poivre. Lavez et effeuillez le basilic.");
insert into steps
values (5,2,"Disposez les asperges dans un large saladier avec les morceaux de fraises, la feta emiettee grossièrement, les pignons et le basilic. Arrosez de sauce et servez frais avec des tranches de baguette toastees.");


insert into recipes
values ('Soupe carotte gingembre','2022-01-13',true,false,true,'Bon marche','Tres Facile','Moyen',4,'automne','anonyme','D_VEGE','RC_SOU');

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
values ('Poivre',3,1);
insert into ingredient_quantities
values ('Sel',3,1);
insert into ingredient_quantities
values ('Ciboulette',3,2);

insert into steps
values (1,3,"Emincer l'oignon et la ciboulette. Couper finement l'ail et le gingembre. Couper les carottes en lamelles de 1cm.");
insert into steps
values (2,3,"Faire fondre, a feu moyen, le beurre dans une grande cocotte. Y jeter l'oignon, l'ail et le gingembre et faire revenir le tout pendant environ 10-15 minutes.");
insert into steps
values (3,3,"Ajouter ensuite 1,5L d'eau, les cubes de bouillon, le vin blanc et les carottes. Porter a ebullition, puis reduire a feu moyen.");
insert into steps
values (4,3,"Laisser cuire le tout, sans couvrir, pendant environ 45 minutes, jusqu'a ce que les carottes soient bien tendres.");
insert into steps
values (5,3,"Mixer la soupe et assaisonner le tout de jus de citron, sel et poivre, pourquoi pas d'un peu de curry.");


insert into recipes 
values ("Oeufs cocottes", "2022-05-13", true, false, true, "Bon marche", "30min >< 1h", 4, null, "bichon", "D_VEGE", "RC_ENT");

insert into ingredient_quantities
values ('Gruyere rape',4,150);
insert into ingredient_quantities
values ('Beurre',4,60);
insert into ingredient_quantities
values ('Farine',4,60);
insert into ingredient_quantities
values ('Lait',4,40);
insert into ingredient_quantities
values ('Muscade',4,1);
insert into ingredient_quantities
values ('Oeuf',4,4);

insert into steps
values (1,4,"Prechauffer le four a 180°C (thermostat 6). Beurrer le moule.");
insert into steps
values (2,4,"Chauffer le beurre dans une casserole, ajouter la farine et remuer rapidement pendant 1 min. Ajouter le lait tiedi, remuer au fouet pendant quelques minutes a feu doux.");
insert into steps
values (3,4,"Retirer la casserole du feu.");
insert into steps
values (4,4,"Separer les blancs et les battre fermement (avec une pincee de sel).");
insert into steps
values (5,4,"Dans la casserole refroidie, ajouter les jaunes d'oeufs un a un, puis le fromage rape. Mettre une pincee de muscade, poivrer. Saler peu car le fromage contient deja du sel.");
insert into steps
values (6,4,"Incorporer les blancs d'oeufs battus en melangeant délicatement.");
insert into steps
values (7,4,"Verser dans le moule, au maximum jusqu'a 4 cm du bord.");
insert into steps
values (8,4,"Enfourner pendant 35 minutes en position chaleur tournante.");


insert into ingredients
values ("Pate a crepes", "2022-05-13", true, true, false, "Bon marche", "Facile", "< 30min", 4, null, "abdobeir", null, "RD_DESS");

insert into ingredient_quantities
values ('Farine',5,300);
insert into ingredient_quantities
values ('Sucre',5,45);
insert into ingredient_quantities
values ('Huile d\'arachide',5,2);
insert into ingredient_quantities
values ('Beurre',5,50);
insert into ingredient_quantities
values ('Lait',5,60);
insert into ingredient_quantities
values ('Rhum',5,5);
insert into ingredient_quantities
values ('Oeuf',5,3);

insert into steps
values (1,5,"Faire fondre le beurre.");
insert into steps
values (2,5,"Mettre la farine dans une terrine et former un puits.");
insert into steps
values (3,5,"Y deposer les oeufs entiers, le sucre, l'huile et le beurre.");
insert into steps
values (4,5,"Melanger delicatement avec un fouet en ajoutant au fur et a mesure le lait. La pate ainsi obtenue doit avoir une consistance d'un liquide legerement epais.");
insert into steps
values (5,5,"Parfumer de rhum.");
insert into steps
values (6,5,"Faire chauffer une poele antiadhesive et la huiler tres legerement à l'aide d'un papier Essuie-tout. Y verser une louche de pate, la repartir dans la poele puis attendre qu'elle soit cuite d'un cote avant de la retourner. Cuire ainsi toutes les crepes a feu doux.");


insert into recipes 
values ("Amuse-bouches de Gambas", "2022-05-13", false, true, true, "Bon marche", "Facile", "< 30min", 6, null, "frandubi", "D_PESC", "RC_AMU_G");

insert into ingredient_quantities
values ('Miel',6,3);
insert into ingredient_quantities
values ('Sesame',6,1);
insert into ingredient_quantities
values ('Huile d\'olive',6,2);
insert into ingredient_quantities
values ('Gambas',6,6);
insert into ingredient_quantities
values ('Fromage frais',6,120);
insert into ingredient_quantities
values ('Melange 5 baies',6,1);

insert into steps
values (1,6,"Otez les tetes des gambas et decortiquez-les jusqu'a ce qu'il ne reste que la queue.");
insert into steps
values (2,6,"Roulez les queues de gambas dans les graines de sesame et faites-les revenir dans l'huile d'olive de chaque cote pendant 2/3 minutes (suivant coloration).");
insert into steps
values (3,6,"Melangez le melange 5 baies au fromage frais.");
insert into steps
values (4,6,"Dans des petits verres, versez une cuillere à soupe du melange fromage frais - cinq baies, deposez une gambas aux graines de sesame cuite et finissez par un filet de miel.");


insert into recipes 
values ("Hot-dog flemmard du dimanche soir", "2022-05-14", true, false, true, "Bon marche", "Tres facile", "< 30min", 2, null, "bichon", null, "RC_SNA");

insert into ingredient_quantities
values ('Gruyere rape',7,100);
insert into ingredient_quantities
values ('Moutarde',7,4);
insert into ingredient_quantities
values ('Bacon',7,4);
insert into ingredient_quantities
values ('Saucisse de Strasbourg',7,4);
insert into ingredient_quantities
values ('Baguette',7,1);

insert into steps
values (1,7,"Tartiner le bacon de moutarde, enrouler le cote tartine autour des saucisses.");
insert into steps
values (2,7,"Faire revenir les saucisses + bacon dans un peu d'huile, jusqu'a que ce le bacon soit bruni de tous les cotes.");
insert into steps
values (3,7,"Couper le pain en deux, puis l'ouvrir. Y mettre du gruyere rape, une saucisse et ncore du gruyere rape par dessus.");
insert into steps
values (4,7,"Faire gratiner au four.");
insert into steps
values (5,7,"Servir accompagne d'une salade.");


insert into recipes
values ("Mayonnaise maison", "2022-05-14", false, false, true, "Bon marche", "Tres facile", "< 30min", 4, null, "marvin", "D_SG", "RC_SAU");

insert into ingredient_quantities
values ('Moutarde',8,2);
insert into ingredient_quantities
values ('Vinaigre a l\'estragon',8,1);
insert into ingredient_quantities
values ('Huile d\'arachide',8,7);
insert into ingredient_quantities
values ('Sel',8,1);
insert into ingredient_quantities
values ('Poivre',8,1);
insert into ingredient_quantities
values ('Jaune d\'oeuf',8,1);

insert into steps
values (1,8,"Les ingredients doivent etre a temperature ambiante. Melangez le jaune d'oeuf, un peu de sel, poivre, la moutarde et le vinaigre.");
insert into steps
values (2,8,"Fouetter en versant peu a peu l'huile, la mayonnaise doit peu à peu epaissir.");
insert into steps
values (3,8,"On peut y ajouter des herbes ou du citron pour la parfumer.");


insert into recipes
values ("Smoothie nectarine","2022-05-14", false, true, false, "Bon marche", "Tres facile", "< 30min", 1, "ete", "frandubi", "D_VEGE", "RC_BOI");

insert into ingredient_quantities
values ('Lait',9,9);
insert into ingredient_quantities
values ('Nectarine',9,1);
insert into ingredient_quantities
values ('Yaourt nature',9,1);

insert into steps
values (1,9,"Mettre le yaourt, le lait et la nectarine lavee et denoyautee dans un mixeur et mixer jusqu'a obtenir une substance cremeuse ou liquide. Puis servir dans un verre avec 1 ou 2 glaçons.");


insert into menu
values ("Anniversaire Bichon","C'etait super bon, par contre un peu trop copieux");

insert into menu_components
values (1,1,6);
insert into menu_components
values (2,1,4);
insert into menu_components
values (3,1,1);
insert into menu_components
values (4,1,5);


insert into menu
values ("Anniversaire Marvin", null);

insert into menu_components
values (1,2,9);
insert into menu_components
values (2,2,3);
insert into menu_components
values (3,2,7);