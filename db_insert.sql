insert into categoria_entrada(id,nom)
values
	(1,'ADULT'),
	(2,'SENIOR'),
	(3,'DISCAPACITAT');

insert into tipus_acces(id,nom)
values
	(1,'UN_SOL_US'),
	(2,'ILIMITAT'),
	(3,'UN_SOL_US_1A'),
	(4,'ILIMITAT_UN_1A');

insert into estat_operatiu(id,nom)
values
	(1,'OPERATIU'),
	(2,'AVERIADA'),
	(3,'TANCADA'),
	(4,'ATURADA_TEMPORALMENT');

insert into preu(id,dies,preu_adult,preu_nen_senior,preu_discapacitat)
values
	(1,1,10,10,10);

insert into client(nif,contrasenya,nom)
values
	('12345678T','1234','test');

INSERT INTO parc(id,nom,url_foto)
VALUES
	(1, 'Caribe Aquatic Parck', 'https://m.guiadelocio.com/var/guiadelocio.com/storage/images/ninos/tarragona/tarragona/caribe-aquatic-park/14482075-18-esl-ES/caribe-aquatic-park.jpg'),
	(2, 'Ferrari Land Parck', 'https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/004/733/small_square/atracciones-ferrari-land.jpg'),
	(3, 'PortAventura Parck', 'https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/000/794/small_square/portaventura-park.jpg');
	
insert into preu_parc(preu_id,parc_id)
values
	(1,1);
	
insert into tipus_passi_expres(nom,preu_dia) 
values
	('test',10);
	
insert into entrada(dies_validesa,preu,categoria_id,preu_id)
values
	(1,12.21,1,1);
	
insert into zona(id,parc_id,nom)
values
	(1,3,'Mediterranea'),
	(2,3,'China'),
	(3,3,'Polynesia'),
	(4,3,'Mexico'),
	(5,3,'Sesamo'),
	(6,3,'Farwest');
	
INSERT INTO atraccio(id,zona_id,parc_id,estat_actual_id,capacitat_maxima_ronda,descripcioHTML,nom,temps_per_ronda,url_foto,clients_cua,alçada_minima_acompanyat,alçada_minima) 
VALUES
	(1, 1, 3, 1, 10, '', 'Furius Bacus', 10, 'https://s3-eu-west-1.amazonaws.com/portaventura-world-production-files/wilson_cms/images/images/000/000/110/small_square/EXPLORA_OTRAS_DRAGON_KHAN_PAP-CH_ATR_DK_EXT_0615_012.jpg', 0, 10, 10);
	
insert into incidencia(atraccio_id,estat_operatiu_id,missatge_estat) 
values
	(1,1,'');
	
insert into entrada_parc(entrada_id,parc_id)
values
	(1,1);
	
insert into tipus_acces_atraccio(tipus_passi_id,atraccio_id,tipus_acces_id)
values
	(1,1,1);
	
insert into passi_express(client_id,tipus_id)
values
	(1,1);

-- quines dates es guarden en entrada i en passi express

-- control errors al conectar db inexistent
commit;