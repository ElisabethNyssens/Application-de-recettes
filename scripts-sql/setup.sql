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
    title varchar(100) PRIMARY KEY not null,
    creation_date date not null,
    is_hot bit not null,
    is_sweet bit not null,
    is_salty bit not null,
    budget varchar(15) not null,
    difficulty varchar(15) not null,
    preparation_time varchar(15) not null,
    nb_persons int not null,
    season varchar(15) null,
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
    recipe_id varchar(100) not null,
    quantity int not null,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ing_name),
    FOREIGN KEY (recipe_id) REFERENCES recipes (title),
    PRIMARY KEY (ingredient_id, recipe_id)
);

CREATE TABLE steps
(
    order_number int not null,
    recipe_id varchar(100) not null,
    description varchar(1000) not null,
    FOREIGN KEY (recipe_id) REFERENCES recipes (title),
    PRIMARY KEY (order_number, recipe_id)
);

CREATE TABLE menus
(
    title varchar(100) PRIMARY KEY not null,
    comment varchar(200) null
);

CREATE TABLE menu_components
(
    order_number int not null,
    menu_id varchar(100) not null,
    recipe_id varchar(100) not null,
    FOREIGN KEY (menu_id) REFERENCES menus (title),
    FOREIGN KEY (recipe_id) REFERENCES recipes (title),
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
insert into ingredients values ("Huile d'arachide",'c.a.s');
insert into ingredients values ("Huile d'olive",'c.a.s');
insert into ingredients values ("Jaune d'oeuf",'unite');
insert into ingredients values ('Jus de citron', 'cl');
insert into ingredients values ('Lait','cl');
insert into ingredients values ('Lait de coco','cl');
insert into ingredients values ('Lentilles','g');
insert into ingredients values ('Melange 5 baies','c.a.c');
insert into ingredients values ('Menthe','feuilles');
insert into ingredients values ('Miel','c.a.c');
insert into ingredients values ('Moutarde','c.a.c');
insert into ingredients values ("Moutarde a l'ancienne",'c.a.c');
insert into ingredients values ('Muscade','c.a.c');
insert into ingredients values ('Nectarine','unite');
insert into ingredients values ('Noix','g');
insert into ingredients values ('Oeuf','unite');
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
insert into ingredients values ("Vinaigre a l'estragon",'c.a.s');
insert into ingredients values ('Vinaigre balsamique','c.a.s');
insert into ingredients values ('Vinaigre de cidre','c.a.s');
insert into ingredients values ('Vin blanc','cl');
insert into ingredients values ('Yaourt nature','unite');

insert into recipes
values ('Curry au tofu','2021-04-25',true,false,true,'Bon marche','Facile','Moyen',4,'hiver','bichon',null,'RC_PLAT');

insert into ingredient_quantities
values ('Pois chiches','Curry au tofu',250);
insert into ingredient_quantities
values ('Concentre de tomates','Curry au tofu',500);
insert into ingredient_quantities
values ('Oignon jaune','Curry au tofu',2);
insert into ingredient_quantities
values ('Ail','Curry au tofu',1);
insert into ingredient_quantities
values ('Gingembre','Curry au tofu',20);
insert into ingredient_quantities
values ('Graines de cumin','Curry au tofu',2);
insert into ingredient_quantities
values ('Curry','Curry au tofu',2);
insert into ingredient_quantities
values ('Curcuma','Curry au tofu',1);
insert into ingredient_quantities
values ('Persil','Curry au tofu',2);
insert into ingredient_quantities
values ("Huile d'arachide",1,2);
insert into ingredient_quantities
values ('Sel','Curry au tofu',2);
insert into ingredient_quantities
values ('Chou-fleur','Curry au tofu',1);
insert into ingredient_quantities
values ('Tofu','Curry au tofu',250);
insert into ingredient_quantities
values ('Riz','Curry au tofu',500);

insert into steps
values (1,'Curry au tofu',"Egoutter les poids chiches, emincer les oignons et l'ail, hacher le persil et couper le chou-fleur en petits morceaux");
insert into steps
values (2,'Curry au tofu',"Verser l'huile dans une sauteuse. Lorsqu'elle est chaude, y ajouter les oignons et les laisser revenir à feu moyen jusqu'a ce qu'ils commencent à colorer.");
insert into steps
values (3,'Curry au tofu',"Ajouter les graines de cumin, melanger aux oignons et laisser cuire une minute.");
insert into steps
values (4,'Curry au tofu',"Ajouter l'ail et le gingembre, melanger et laisser cuire encore une minute.");
insert into steps
values (5,'Curry au tofu',"Ajouter le curry et le curcuma, melanger et laisser cuire une minute sans faire bruler les epices.");
insert into steps
values (6,'Curry au tofu',"Delayer avec la puree de tomate, ajouter le chou fleur, les pois chiches, le tofu, le sel et un verre d'eau. Bien melanger et laisser mijoter a couvert jusqu'a ce que le chou-fleur soit bien tendre. Ajoutez un peu d'eau en cour de route si le melange s'asseche.");
insert into steps
values (7,'Curry au tofu',"Ajouter le persil avant de servir.");


insert into recipes
values ('Salade fraise menthe','2021-07-18',false,true,true,'Cout moyen','< 30min',4,'ete','bichon','D_VEGE','RC_ACC');

insert into ingredient_quantities
values ('Epinard frais','Salade fraise menthe',600);
insert into ingredient_quantities
values ('Fraise','Salade fraise menthe',400);
insert into ingredient_quantities
values ('Feta','Salade fraise menthe',250);
insert into ingredient_quantities
values ('Noix','Salade fraise menthe',80);
insert into ingredient_quantities
values ('Menthe','Salade fraise menthe',50);
insert into ingredient_quantities
values ('Graines de pavot','Salade fraise menthe',2);
insert into ingredient_quantities
values ('Miel','Salade fraise menthe',1);
insert into ingredient_quantities
values ('Vinaigre balsamique','Salade fraise menthe',1);
insert into ingredient_quantities
values ("Huile d'olive",'Salade fraise menthe',6);
insert into ingredient_quantities
values ('Sel','Salade fraise menthe',2);
insert into ingredient_quantities
values ('Poivre','Salade fraise menthe',1);

insert into steps
values (1,'Salade fraise menthe',"Lavez et egouttez les épinards.");
insert into steps
values (2,'Salade fraise menthe',"Lavez et equeutez les fraises. Coupez-les en quartiers.");
insert into steps
values (3,'Salade fraise menthe',"Faites revenir les noix 2 min dans une poele antiadhesive.");
insert into steps
values (4,'Salade fraise menthe',"Preparez la vinaigrette : dans un bol, melangez le miel et le vinaigre. Emulsionnez avec l'huile d’olive, du sel et du poivre. Lavez et effeuillez la menthe.");
insert into steps
values (5,'Salade fraise menthe',"Disposez les épinards dans un large saladier avec les morceaux de fraises, la feta emiettee grossièrement, les noix et la menthe. Arrosez de sauce, saupoudrez de graines de pavot et servez frais avec des tranches de baguette toastees.");


insert into recipes
values ('Soupe carotte gingembre','2022-01-13',true,false,true,'Bon marche','< 30min',4,'automne','anonyme','D_VEGE','RC_SOU');

insert into ingredient_quantities
values ('Beurre','Soupe carotte gingembre',50);
insert into ingredient_quantities
values ('Oignon jaune','Soupe carotte gingembre',1);
insert into ingredient_quantities
values ('Gingembre','Soupe carotte gingembre',50);
insert into ingredient_quantities
values ('Ail','Soupe carotte gingembre',3);
insert into ingredient_quantities
values ('Bouillon de legumes','Soupe carotte gingembre',2);
insert into ingredient_quantities
values ('Vin blanc','Soupe carotte gingembre',25);
insert into ingredient_quantities
values ('Carotte','Soupe carotte gingembre',4);
insert into ingredient_quantities
values ('Jus de citron','Soupe carotte gingembre',3);
insert into ingredient_quantities
values ('Poivre','Soupe carotte gingembre',1);
insert into ingredient_quantities
values ('Sel','Soupe carotte gingembre',1);
insert into ingredient_quantities
values ('Ciboulette','Soupe carotte gingembre',2);

insert into steps
values (1,'Soupe carotte gingembre',"Emincer l'oignon et la ciboulette. Couper finement l'ail et le gingembre. Couper les carottes en lamelles de 1cm.");
insert into steps
values (2,'Soupe carotte gingembre',"Faire fondre, a feu moyen, le beurre dans une grande cocotte. Y jeter l'oignon, l'ail et le gingembre et faire revenir le tout pendant environ 10-15 minutes.");
insert into steps
values (3,'Soupe carotte gingembre',"Ajouter ensuite 1,5L d'eau, les cubes de bouillon, le vin blanc et les carottes. Porter a ebullition, puis reduire a feu moyen.");
insert into steps
values (4,'Soupe carotte gingembre',"Laisser cuire le tout, sans couvrir, pendant environ 45 minutes, jusqu'a ce que les carottes soient bien tendres.");
insert into steps
values (5,'Soupe carotte gingembre',"Mixer la soupe et assaisonner le tout de jus de citron, sel et poivre, pourquoi pas d'un peu de curry.");


insert into recipes
values ("Oeufs cocottes", "2022-05-13", true, false, true, "Bon marche", "30min >< 1h", 4, "Toute saison", "bichon", "D_VEGE", "RC_ENT");

insert into ingredient_quantities
values ('Gruyere rape',"Oeufs cocottes",150);
insert into ingredient_quantities
values ('Beurre',"Oeufs cocottes",60);
insert into ingredient_quantities
values ('Farine',"Oeufs cocottes",60);
insert into ingredient_quantities
values ('Lait',"Oeufs cocottes",40);
insert into ingredient_quantities
values ('Muscade',"Oeufs cocottes",1);
insert into ingredient_quantities
values ('Oeuf',"Oeufs cocottes",4);

insert into steps
values (1,"Oeufs cocottes","Prechauffer le four a 180°C (thermostat 6). Beurrer le moule.");
insert into steps
values (2,"Oeufs cocottes","Chauffer le beurre dans une casserole, ajouter la farine et remuer rapidement pendant 1 min. Ajouter le lait tiedi, remuer au fouet pendant quelques minutes a feu doux.");
insert into steps
values (3,"Oeufs cocottes","Retirer la casserole du feu.");
insert into steps
values (4,"Oeufs cocottes","Separer les blancs et les battre fermement (avec une pincee de sel).");
insert into steps
values (5,"Oeufs cocottes","Dans la casserole refroidie, ajouter les jaunes d'oeufs un a un, puis le fromage rape. Mettre une pincee de muscade, poivrer. Saler peu car le fromage contient deja du sel.");
insert into steps
values (6,"Oeufs cocottes","Incorporer les blancs d'oeufs battus en melangeant délicatement.");
insert into steps
values (7,"Oeufs cocottes","Verser dans le moule, au maximum jusqu'a 4 cm du bord.");
insert into steps
values (8,"Oeufs cocottes","Enfourner pendant 35 minutes en position chaleur tournante.");


insert into recipes
values ("Pate a crepes", "2022-05-13", true, true, false, "Bon marche", "< 30min", 4, null, "abdobeir", null, "RC_DESS");

insert into ingredient_quantities
values ('Farine',"Pate a crepes",300);
insert into ingredient_quantities
values ('Sucre',"Pate a crepes",45);
insert into ingredient_quantities
values ("Huile d'arachide","Pate a crepes",2);
insert into ingredient_quantities
values ('Beurre',"Pate a crepes",50);
insert into ingredient_quantities
values ('Lait',"Pate a crepes",60);
insert into ingredient_quantities
values ('Rhum',"Pate a crepes",5);
insert into ingredient_quantities
values ('Oeuf',"Pate a crepes",3);

insert into steps
values (1,"Pate a crepes","Faire fondre le beurre.");
insert into steps
values (2,"Pate a crepes","Mettre la farine dans une terrine et former un puits.");
insert into steps
values (3,"Pate a crepes","Y deposer les oeufs entiers, le sucre, l'huile et le beurre.");
insert into steps
values (4,"Pate a crepes","Melanger delicatement avec un fouet en ajoutant au fur et a mesure le lait. La pate ainsi obtenue doit avoir une consistance d'un liquide legerement epais.");
insert into steps
values (5,"Pate a crepes","Parfumer de rhum.");
insert into steps
values (6,"Pate a crepes","Faire chauffer une poele antiadhesive et la huiler tres legerement à l'aide d'un papier Essuie-tout. Y verser une louche de pate, la repartir dans la poele puis attendre qu'elle soit cuite d'un cote avant de la retourner. Cuire ainsi toutes les crepes a feu doux.");


insert into recipes
values ("Amuse-bouches de Gambas", "2022-05-13", false, true, true, "Assez cher", "< 30min", 6, null, "frandubi", "D_PESC", "RC_AMU_G");

insert into ingredient_quantities
values ('Miel',"Amuse-bouches de Gambas",3);
insert into ingredient_quantities
values ('Sesame',"Amuse-bouches de Gambas",1);
insert into ingredient_quantities
values ("Huile d'olive","Amuse-bouches de Gambas",2);
insert into ingredient_quantities
values ('Gambas',"Amuse-bouches de Gambas",6);
insert into ingredient_quantities
values ('Fromage frais',"Amuse-bouches de Gambas",120);
insert into ingredient_quantities
values ('Melange 5 baies',"Amuse-bouches de Gambas",1);

insert into steps
values (1,"Amuse-bouches de Gambas","Otez les tetes des gambas et decortiquez-les jusqu'a ce qu'il ne reste que la queue.");
insert into steps
values (2,"Amuse-bouches de Gambas","Roulez les queues de gambas dans les graines de sesame et faites-les revenir dans l'huile d'olive de chaque cote pendant 2/3 minutes (suivant coloration).");
insert into steps
values (3,"Amuse-bouches de Gambas","Melangez le melange 5 baies au fromage frais.");
insert into steps
values (4,"Amuse-bouches de Gambas","Dans des petits verres, versez une cuillere à soupe du melange fromage frais - cinq baies, deposez une gambas aux graines de sesame cuite et finissez par un filet de miel.");


insert into recipes
values ("Hot-dog flemmard du dimanche soir", "2022-05-14", true, false, true, "Bon marche", "< 30min", 2, null, "bichon", null, "RC_SNA");

insert into ingredient_quantities
values ('Gruyere rape',"Hot-dog flemmard du dimanche soir",100);
insert into ingredient_quantities
values ('Moutarde',"Hot-dog flemmard du dimanche soir",4);
insert into ingredient_quantities
values ('Bacon',"Hot-dog flemmard du dimanche soir",4);
insert into ingredient_quantities
values ('Saucisse de Strasbourg',"Hot-dog flemmard du dimanche soir",4);
insert into ingredient_quantities
values ('Baguette',"Hot-dog flemmard du dimanche soir",1);

insert into steps
values (1,"Hot-dog flemmard du dimanche soir","Tartiner le bacon de moutarde, enrouler le cote tartine autour des saucisses.");
insert into steps
values (2,"Hot-dog flemmard du dimanche soir","Faire revenir les saucisses + bacon dans un peu d'huile, jusqu'a que ce le bacon soit bruni de tous les cotes.");
insert into steps
values (3,"Hot-dog flemmard du dimanche soir","Couper le pain en deux, puis l'ouvrir. Y mettre du gruyere rape, une saucisse et ncore du gruyere rape par dessus.");
insert into steps
values (4,"Hot-dog flemmard du dimanche soir","Faire gratiner au four.");
insert into steps
values (5,"Hot-dog flemmard du dimanche soir","Servir accompagne d'une salade.");


insert into recipes
values ("Mayonnaise maison", "2022-05-14", false, false, true, "Bon marche", "< 30min", 4, null, "marvin", "D_SG", "RC_SAU");

insert into ingredient_quantities
values ('Moutarde',"Mayonnaise maison",2);
insert into ingredient_quantities
values ("Vinaigre a l'estragon","Mayonnaise maison",1);
insert into ingredient_quantities
values ("Huile d'arachide","Mayonnaise maison",7);
insert into ingredient_quantities
values ('Sel',"Mayonnaise maison",1);
insert into ingredient_quantities
values ('Poivre',"Mayonnaise maison",1);
insert into ingredient_quantities
values ("Jaune d'oeuf","Mayonnaise maison",1);

insert into steps
values (1,"Mayonnaise maison","Les ingredients doivent etre a temperature ambiante. Melangez le jaune d'oeuf, un peu de sel, poivre, la moutarde et le vinaigre.");
insert into steps
values (2,"Mayonnaise maison","Fouetter en versant peu a peu l'huile, la mayonnaise doit peu à peu epaissir.");
insert into steps
values (3,"Mayonnaise maison","On peut y ajouter des herbes ou du citron pour la parfumer.");


insert into recipes
values ("Smoothie nectarine","2022-05-14", false, true, false, "Bon marche", "< 30min", 1, "ete", "frandubi", "D_VEGE", "RC_BOI");

insert into ingredient_quantities
values ('Lait',"Smoothie nectarine",9);
insert into ingredient_quantities
values ('Nectarine',"Smoothie nectarine",1);
insert into ingredient_quantities
values ('Yaourt nature',"Smoothie nectarine",1);

insert into steps
values (1,"Smoothie nectarine","Mettre le yaourt, le lait et la nectarine lavee et denoyautee dans un mixeur et mixer jusqu'a obtenir une substance cremeuse ou liquide. Puis servir dans un verre avec 1 ou 2 glaçons.");


insert into menus
values ("Anniversaire Bichon","C'etait super bon, par contre un peu trop copieux");

insert into menu_components
values (1,"Anniversaire Bichon","Amuse-bouches de Gambas");
insert into menu_components
values (2,"Anniversaire Bichon","Oeufs cocottes");
insert into menu_components
values (3,"Anniversaire Bichon",'Curry au tofu');
insert into menu_components
values (4,"Anniversaire Bichon","Pate a crepes");


insert into menus
values ("Anniversaire Marvin", null);

insert into menu_components
values (1,"Anniversaire Marvin","Smoothie nectarine");
insert into menu_components
values (2,"Anniversaire Marvin",'Soupe carotte gingembre');
insert into menu_components
values (3,"Anniversaire Marvin","Hot-dog flemmard du dimanche soir");