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
    preparation_time varchar(15) not null,
    nb_persons int not null check(nb_persons > 0),
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
    quantity numeric(5,1) not null check(quantity > 0),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (ing_name),
    FOREIGN KEY (recipe_id) REFERENCES recipes (title),
    PRIMARY KEY (ingredient_id, recipe_id)
);

CREATE TABLE steps
(
    order_number int not null check(order_number > 0),
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
    order_number int not null check(order_number > 0),
    menu_id varchar(100) not null,
    recipe_id varchar(100) not null,
    FOREIGN KEY (menu_id) REFERENCES menus (title),
    FOREIGN KEY (recipe_id) REFERENCES recipes (title),
    PRIMARY KEY(order_number, menu_id)
);

CREATE VIEW wrong_menus
as select title, comment
from menus;

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


insert into ingredients values ('ail','gousse');
insert into ingredients values ('asperge verte','g');
insert into ingredients values ('aubergine','unite');
insert into ingredients values ('bacon','tranches');
insert into ingredients values ('baguette','unite');
insert into ingredients values ('basilic','feuilles');
insert into ingredients values ('boudoirs','g');
insert into ingredients values ('beurre','g');
insert into ingredients values ('bouillon de legumes','cubes');
insert into ingredients values ('brocoli','unite');
insert into ingredients values ('cacao','c.a.c');
insert into ingredients values ('cafe','cl');
insert into ingredients values ('capres','g');
insert into ingredients values ('carotte','unite');
insert into ingredients values ('chapelure','c.a.s');
insert into ingredients values ('champignons','g');
insert into ingredients values ('chocolat','g');
insert into ingredients values ('chou-fleur','unite');
insert into ingredients values ('ciboulette','c.a.s');
insert into ingredients values ('citron jaune','unite');
insert into ingredients values ('citron vert','unite');
insert into ingredients values ('concentre de tomates','g');
insert into ingredients values ('concombre','unite');
insert into ingredients values ('coriandre','feuilles');
insert into ingredients values ('courgette','unite');
insert into ingredients values ('creme','cl');
insert into ingredients values ('cumin','c.a.c');
insert into ingredients values ('curcuma','c.a.c');
insert into ingredients values ('curry','c.a.c');
insert into ingredients values ('epinard frais','g');
insert into ingredients values ('farine','g');
insert into ingredients values ('feta','g');
insert into ingredients values ('fraise','g');
insert into ingredients values ('fromage frais','g');
insert into ingredients values ('gambas','unite');
insert into ingredients values ('gingembre','g');
insert into ingredients values ('graines de cumin','c.a.c');
insert into ingredients values ('graines de pavot','c.a.s');
insert into ingredients values ('gruyere rape','g');
insert into ingredients values ("huile d'arachide",'c.a.s');
insert into ingredients values ("huile d'olive",'c.a.s');
insert into ingredients values ("huile de tournesol",'c.a.s');
insert into ingredients values ("jaune d'oeuf",'unite');
insert into ingredients values ('jus de citron', 'cl');
insert into ingredients values ('lait','cl');
insert into ingredients values ('lait de coco','cl');
insert into ingredients values ('lentilles','g');
insert into ingredients values ('levure chimique','sachet');
insert into ingredients values ('melange 5 baies','c.a.c');
insert into ingredients values ('menthe','feuilles');
insert into ingredients values ('miel','c.a.c');
insert into ingredients values ('mascarpone','g');
insert into ingredients values ('moutarde','c.a.c');
insert into ingredients values ('mozzarella','g');
insert into ingredients values ('muscade','c.a.c');
insert into ingredients values ('nectarine','unite');
insert into ingredients values ('noix','g');
insert into ingredients values ('oeuf','unite');
insert into ingredients values ('oignon jaune','unite');
insert into ingredients values ('oignon nouveau','unite');
insert into ingredients values ('orange','unite');
insert into ingredients values ('parmesan','c.a.s');
insert into ingredients values ('pates','g');
insert into ingredients values ('persil','c.a.s');
insert into ingredients values ('pave de saumon','unite');
insert into ingredients values ('pignons de pin','g');
insert into ingredients values ('pois chiches','g');
insert into ingredients values ('poivre','c.a.c');
insert into ingredients values ('poivron','unite');
insert into ingredients values ('pomme','unite');
insert into ingredients values ('rhum','cl');
insert into ingredients values ('riz','g');
insert into ingredients values ('saucisse de Strasbourg','unite');
insert into ingredients values ('sel','c.a.c');
insert into ingredients values ('sesame','c.a.c');
insert into ingredients values ('sucre','g');
insert into ingredients values ('sucre vanille','sachet');
insert into ingredients values ('tofu','g');
insert into ingredients values ('tomate','unite');
insert into ingredients values ("vinaigre a l'estragon",'c.a.s');
insert into ingredients values ('vinaigre balsamique','c.a.s');
insert into ingredients values ('vinaigre de cidre','c.a.s');
insert into ingredients values ('vin blanc','cl');
insert into ingredients values ('yaourt de soja','unite');
insert into ingredients values ('yaourt nature','unite');


insert into recipes
values ('Curry au tofu','2021-04-25',true,false,true,'Bon marche','30min >< 1h',4,'hiver','bichon','D_VEGA','RC_PLAT');

insert into ingredient_quantities
values ('pois chiches','Curry au tofu',250);
insert into ingredient_quantities
values ('concentre de tomates','Curry au tofu',500);
insert into ingredient_quantities
values ('oignon jaune','Curry au tofu',2);
insert into ingredient_quantities
values ('ail','Curry au tofu',1);
insert into ingredient_quantities
values ('gingembre','Curry au tofu',20);
insert into ingredient_quantities
values ('graines de cumin','Curry au tofu',2);
insert into ingredient_quantities
values ('curry','Curry au tofu',2);
insert into ingredient_quantities
values ('curcuma','Curry au tofu',1);
insert into ingredient_quantities
values ('persil','Curry au tofu',2);
insert into ingredient_quantities
values ("huile d'arachide",'Curry au tofu',2);
insert into ingredient_quantities
values ('sel','Curry au tofu',2);
insert into ingredient_quantities
values ('chou-fleur','Curry au tofu',1);
insert into ingredient_quantities
values ('tofu','Curry au tofu',250);
insert into ingredient_quantities
values ('riz','Curry au tofu',500);

insert into steps
values (1,'Curry au tofu',"Egoutter les poids chiches, emincer les oignons et l'ail, hacher le persil et couper le chou-fleur en petits morceaux");
insert into steps
values (2,'Curry au tofu',"Verser l'huile dans une sauteuse. Lorsqu'elle est chaude, y ajouter les oignons et les laisser revenir a feu moyen jusqu'a ce qu'ils commencent a colorer.");
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
values ('epinard frais','Salade fraise menthe',600);
insert into ingredient_quantities
values ('fraise','Salade fraise menthe',400);
insert into ingredient_quantities
values ('feta','Salade fraise menthe',250);
insert into ingredient_quantities
values ('noix','Salade fraise menthe',80);
insert into ingredient_quantities
values ('menthe','Salade fraise menthe',50);
insert into ingredient_quantities
values ('graines de pavot','Salade fraise menthe',2);
insert into ingredient_quantities
values ('miel','Salade fraise menthe',1);
insert into ingredient_quantities
values ('vinaigre balsamique','Salade fraise menthe',1);
insert into ingredient_quantities
values ("huile d'olive",'Salade fraise menthe',6);
insert into ingredient_quantities
values ('sel','Salade fraise menthe',2);
insert into ingredient_quantities
values ('poivre','Salade fraise menthe',1);

insert into steps
values (1,'Salade fraise menthe',"Lavez et egouttez les epinards.");
insert into steps
values (2,'Salade fraise menthe',"Lavez et equeutez les fraises. Coupez-les en quartiers.");
insert into steps
values (3,'Salade fraise menthe',"Faites revenir les noix 2 min dans une poele antiadhesive.");
insert into steps
values (4,'Salade fraise menthe',"Preparez la vinaigrette : dans un bol, melangez le miel et le vinaigre. Emulsionnez avec l'huile d'olive, du sel et du poivre. Lavez et effeuillez la menthe.");
insert into steps
values (5,'Salade fraise menthe',"Disposez les epinards dans un large saladier avec les morceaux de fraises, la feta emiettee grossierement, les noix et la menthe. Arrosez de sauce, saupoudrez de graines de pavot et servez frais avec des tranches de baguette toastees.");


insert into recipes
values ('Soupe carotte gingembre','2022-01-13',true,false,true,'Bon marche','< 30min',4,'automne','anonyme','D_VEGE','RC_SOU');

insert into ingredient_quantities
values ('beurre','Soupe carotte gingembre',50);
insert into ingredient_quantities
values ('oignon jaune','Soupe carotte gingembre',1);
insert into ingredient_quantities
values ('gingembre','Soupe carotte gingembre',50);
insert into ingredient_quantities
values ('ail','Soupe carotte gingembre',3);
insert into ingredient_quantities
values ('bouillon de legumes','Soupe carotte gingembre',2);
insert into ingredient_quantities
values ('vin blanc','Soupe carotte gingembre',25);
insert into ingredient_quantities
values ('carotte','Soupe carotte gingembre',4);
insert into ingredient_quantities
values ('jus de citron','Soupe carotte gingembre',3);
insert into ingredient_quantities
values ('poivre','Soupe carotte gingembre',1);
insert into ingredient_quantities
values ('sel','Soupe carotte gingembre',1);
insert into ingredient_quantities
values ('ciboulette','Soupe carotte gingembre',2);

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
values ("Oeufs cocottes", "2022-05-13", true, false, true, "Bon marche", "30min >< 1h", 4, "toute saison", "bichon", "D_VEGE", "RC_ENT");

insert into ingredient_quantities
values ('gruyere rape',"Oeufs cocottes",150);
insert into ingredient_quantities
values ('beurre',"Oeufs cocottes",60);
insert into ingredient_quantities
values ('farine',"Oeufs cocottes",60);
insert into ingredient_quantities
values ('lait',"Oeufs cocottes",40);
insert into ingredient_quantities
values ('muscade',"Oeufs cocottes",1);
insert into ingredient_quantities
values ('oeuf',"Oeufs cocottes",4);

insert into steps
values (1,"Oeufs cocottes","Prechauffer le four a 180 degres (thermostat 6). Beurrer le moule.");
insert into steps
values (2,"Oeufs cocottes","Chauffer le beurre dans une casserole, ajouter la farine et remuer rapidement pendant 1 min. Ajouter le lait tiedi, remuer au fouet pendant quelques minutes a feu doux.");
insert into steps
values (3,"Oeufs cocottes","Retirer la casserole du feu.");
insert into steps
values (4,"Oeufs cocottes","Separer les blancs et les battre fermement (avec une pincee de sel).");
insert into steps
values (5,"Oeufs cocottes","Dans la casserole refroidie, ajouter les jaunes d'oeufs un a un, puis le fromage rape. Mettre une pincee de muscade, poivrer. Saler peu car le fromage contient deja du sel.");
insert into steps
values (6,"Oeufs cocottes","Incorporer les blancs d'oeufs battus en melangeant delicatement.");
insert into steps
values (7,"Oeufs cocottes","Verser dans le moule, au maximum jusqu'a 4 cm du bord.");
insert into steps
values (8,"Oeufs cocottes","Enfourner pendant 35 minutes en position chaleur tournante.");


insert into recipes
values ("Pate a crepes", "2022-05-13", true, true, false, "Bon marche", "< 30min", 4, "toute saison", "abdobeir", null, "RC_DESS");

insert into ingredient_quantities
values ('farine',"Pate a crepes",300);
insert into ingredient_quantities
values ('sucre',"Pate a crepes",45);
insert into ingredient_quantities
values ("huile d'arachide","Pate a crepes",2);
insert into ingredient_quantities
values ('beurre',"Pate a crepes",50);
insert into ingredient_quantities
values ('lait',"Pate a crepes",60);
insert into ingredient_quantities
values ('rhum',"Pate a crepes",5);
insert into ingredient_quantities
values ('oeuf',"Pate a crepes",3);

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
values (6,"Pate a crepes","Faire chauffer une poele antiadhesive et la huiler tres legerement a l'aide d'un papier Essuie-tout. Y verser une louche de pate, la repartir dans la poele puis attendre qu'elle soit cuite d'un cote avant de la retourner. Cuire ainsi toutes les crepes a feu doux.");


insert into recipes
values ("Amuse-bouches de Gambas", "2022-05-13", false, true, true, "Assez cher", "< 30min", 6, null, "frandubi", "D_PESC", "RC_AMU_G");

insert into ingredient_quantities
values ('miel',"Amuse-bouches de Gambas",3);
insert into ingredient_quantities
values ('sesame',"Amuse-bouches de Gambas",1);
insert into ingredient_quantities
values ("huile d'olive","Amuse-bouches de Gambas",2);
insert into ingredient_quantities
values ('gambas',"Amuse-bouches de Gambas",6);
insert into ingredient_quantities
values ('fromage frais',"Amuse-bouches de Gambas",120);
insert into ingredient_quantities
values ('melange 5 baies',"Amuse-bouches de Gambas",1);

insert into steps
values (1,"Amuse-bouches de Gambas","Otez les tetes des gambas et decortiquez-les jusqu'a ce qu'il ne reste que la queue.");
insert into steps
values (2,"Amuse-bouches de Gambas","Roulez les queues de gambas dans les graines de sesame et faites-les revenir dans l'huile d'olive de chaque cote pendant 2/3 minutes (suivant coloration).");
insert into steps
values (3,"Amuse-bouches de Gambas","Melangez le melange 5 baies au fromage frais.");
insert into steps
values (4,"Amuse-bouches de Gambas","Dans des petits verres, versez une cuillere a soupe du melange fromage frais - cinq baies, deposez une gambas aux graines de sesame cuite et finissez par un filet de miel.");


insert into recipes
values ("Hot-dog flemmard du dimanche soir", "2022-05-14", true, false, true, "Bon marche", "< 30min", 2, "toute saison", "bichon", null, "RC_SNA");

insert into ingredient_quantities
values ('gruyere rape',"Hot-dog flemmard du dimanche soir",100);
insert into ingredient_quantities
values ('moutarde',"Hot-dog flemmard du dimanche soir",4);
insert into ingredient_quantities
values ('bacon',"Hot-dog flemmard du dimanche soir",4);
insert into ingredient_quantities
values ('saucisse de Strasbourg',"Hot-dog flemmard du dimanche soir",4);
insert into ingredient_quantities
values ('baguette',"Hot-dog flemmard du dimanche soir",1);

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
values ("Mayonnaise maison", "2022-05-14", false, false, true, "Bon marche", "< 30min", 4, "toute saison", "marvin", "D_SG", "RC_SAU");

insert into ingredient_quantities
values ('moutarde',"Mayonnaise maison",2);
insert into ingredient_quantities
values ("vinaigre a l'estragon","Mayonnaise maison",1);
insert into ingredient_quantities
values ("huile d'arachide","Mayonnaise maison",7);
insert into ingredient_quantities
values ('sel',"Mayonnaise maison",1);
insert into ingredient_quantities
values ('poivre',"Mayonnaise maison",1);
insert into ingredient_quantities
values ("jaune d'oeuf","Mayonnaise maison",1);

insert into steps
values (1,"Mayonnaise maison","Les ingredients doivent etre a temperature ambiante. Melangez le jaune d'oeuf, un peu de sel, poivre, la moutarde et le vinaigre.");
insert into steps
values (2,"Mayonnaise maison","Fouetter en versant peu a peu l'huile, la mayonnaise doit peu a peu epaissir.");
insert into steps
values (3,"Mayonnaise maison","On peut y ajouter des herbes ou du citron pour la parfumer.");


insert into recipes
values ("Smoothie nectarine","2022-05-14", false, true, false, "Bon marche", "< 30min", 1, "ete", "frandubi", "D_SG", "RC_BOI");

insert into ingredient_quantities
values ('lait',"Smoothie nectarine",9);
insert into ingredient_quantities
values ('nectarine',"Smoothie nectarine",1);
insert into ingredient_quantities
values ('yaourt nature',"Smoothie nectarine",1);

insert into steps
values (1,"Smoothie nectarine","Mettre le yaourt, le lait et la nectarine lavee et denoyautee dans un mixeur et mixer jusqu'a obtenir une substance cremeuse ou liquide. Puis servir dans un verre avec 1 ou 2 glaçons.");


insert into recipes
values ("Gateau au yaourt de soja et pommes", "2022-05-15", false, true, false, "Bon marche", "> 1h", 8, "automne", "anonyme", "D_VEGA", "RC_DESS");

insert into ingredient_quantities
values ('pomme',"Gateau au yaourt de soja et pommes",2);
insert into ingredient_quantities
values ('farine',"Gateau au yaourt de soja et pommes",200);
insert into ingredient_quantities
values ('yaourt de soja',"Gateau au yaourt de soja et pommes",3);
insert into ingredient_quantities
values ('huile de tournesol',"Gateau au yaourt de soja et pommes",2);
insert into ingredient_quantities
values ('jus de citron',"Gateau au yaourt de soja et pommes",1.5);
insert into ingredient_quantities
values ('sucre',"Gateau au yaourt de soja et pommes",80);
insert into ingredient_quantities
values ('sucre vanille',"Gateau au yaourt de soja et pommes",1);
insert into ingredient_quantities
values ('levure chimique',"Gateau au yaourt de soja et pommes",0.5);

insert into steps
values (1,"Gateau au yaourt de soja et pommes","Prechauffez votre four a 180 degres.");
insert into steps
values (2,"Gateau au yaourt de soja et pommes","Epluchez les pommes, coupez-les en des et faites-les cuire a la poele avec une cuillere a soupe de sucre et une autre d'eau. Laissez carameliser pendant 5 a 10 minutes. Reservez.");
insert into steps
values (3,"Gateau au yaourt de soja et pommes","Dans un saladier, melangez les yaourts de soja, le sucre, l'huile, le jus de citron et une cuillere a soupe d'eau. Puis, ajoutez progressivement la farine et la levure chimique.");
insert into steps
values (4,"Gateau au yaourt de soja et pommes","Ajoutez les pommes a la pate, melangez bien l'ensemble.");
insert into steps
values (5,"Gateau au yaourt de soja et pommes","Graissez le moule, versez la preparation puis saupoudrez le tout de sucre.");
insert into steps
values (6,"Gateau au yaourt de soja et pommes","Enfournez pour environ 50 minutes et laissez refroidir avant de demouler le gateau au yaourt de soja et pommes.");


insert into recipes
values ("Paves de saumon au four", "2022-05-15", true, false, true, "Moyen", "30min >< 1h", 2, "printemps", "marvin", "D_PESC", "RC_PLAT");

insert into ingredient_quantities
values ('citron jaune',"Paves de saumon au four",1);
insert into ingredient_quantities
values ('vin blanc',"Paves de saumon au four",10);
insert into ingredient_quantities
values ('oignon nouveau',"Paves de saumon au four",4);
insert into ingredient_quantities
values ("huile d'olive","Paves de saumon au four",4);
insert into ingredient_quantities
values ('poivre',"Paves de saumon au four",1);
insert into ingredient_quantities
values ('sel',"Paves de saumon au four",1);
insert into ingredient_quantities
values ('capres',"Paves de saumon au four",50);
insert into ingredient_quantities
values ('pave de saumon',"Paves de saumon au four",2);

insert into steps
values (1,"Paves de saumon au four","Mettre les paves de saumon dans un plat allant au four.");
insert into steps
values (2,"Paves de saumon au four","Couper un citron en 2 et le presser sur les paves. Couper le demi-citron restant en rondelles et les deposer directement sur le saumon.");
insert into steps
values (3,"Paves de saumon au four","Ciseler les petits oignons ainsi que le 'vert' puis les mettre sur les paves.");
insert into steps
values (4,"Paves de saumon au four","Ecraser les capres et les poser sur le saumon (facultatif).");
insert into steps
values (5,"Paves de saumon au four","Verser le vin blanc et un filet d'huile d'olive sur les saumons, saler (tres peu), poivrer et faire cuire a 180 degres, thermostat 6, pendant environ 25 min.");

insert into recipes
values ("Flan de courgettes", "2022-05-22", true, false, true, "Moyen", "30min >< 1h", 4, "ete", "marvin", "D_VEGE", "RC_ACC");

insert into ingredient_quantities
values ('courgette',"Flan de courgettes",1);
insert into ingredient_quantities
values ('oignon jaune',"Flan de courgettes",1);
insert into ingredient_quantities
values ('oeuf',"Flan de courgettes",3);
insert into ingredient_quantities
values ('chapelure',"Flan de courgettes",1);
insert into ingredient_quantities
values ('parmesan',"Flan de courgettes",1);
insert into ingredient_quantities
values ('mozzarella',"Flan de courgettes",30);

insert into steps
values (1,"Flan de courgettes","Laver et couper en fines tranches les courgettes. Emincer l'oignon.");
insert into steps
values (2,"Flan de courgettes","Mettre le tout dans un plat a four pendant 15min a 180 degres.");
insert into steps
values (3,"Flan de courgettes","Melanger les oeufs, la chapelure et le parmesan dans un saladier. Rajouter la preparation aux courgettes precuites.");
insert into steps
values (4,"Flan de courgettes","Mettre la mozza (rapee ou en morceaux) sur le dessus. Remettre au four 20 minutes a 180 degres.");



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


insert into menus
values ("Menu vegan", "Tous les plats de ce menu ont l'etiquette vegan");

insert into menu_components
values (1,"Menu vegan","Curry au tofu");
insert into menu_components
values (2,"Menu vegan","Gateau au yaourt de soja et pommes");


insert into menus
values ("Menu vegetarien", "Tous les plats de ce menu ont l'etiquette vegetarien");

insert into menu_components
values (1, "Menu vegetarien", 'Soupe carotte gingembre');
insert into menu_components
values (2, "Menu vegetarien", "Oeufs cocottes");
insert into menu_components
values (3, "Menu vegetarien", 'Salade fraise menthe');


insert into menus
values ("Menu Pescetarien", "Tous les plats de ce menu ont l'etiquette pescetarien");

insert into menu_components
values (1, "Menu Pescetarien", "Amuse-bouches de Gambas");
insert into menu_components
values (2, "Menu Pescetarien", "Paves de saumon au four");


insert into menus
values ("Menu sans-gluten", "Tous les plats de ce menu ont l'etiquette sans-gluten, du coup le menu est peut-etre un peu bizarre");

insert into menu_components
values (1, "Menu sans-gluten", "Mayonnaise maison");
insert into menu_components
values (2, "Menu sans-gluten", "Smoothie nectarine");


insert into menus
values ("Menu sans etiquette de regime", null);

insert into menu_components
values (1, "Menu sans etiquette de regime", "Hot-dog flemmard du dimanche soir");
insert into menu_components
values (2, "Menu sans etiquette de regime", "Pate a crepes");