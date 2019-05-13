drop database if exists port_aventura;
create database port_aventura;
use port_aventura;
create table preus(
	parc1_id int REFERENCES parc(id),
	parc2_id int REFERENCES parc(id),
	parc3_id int REFERENCES parc(id),
	num_dies int,
	preu_adult int,
	preu_nen_senior int,
	preu_discapacitat int
);
create table entrada(
	id int PRIMARY KEY AUTO_INCREMENT,
	data date,
	dies_validesa int,
	preu float,
	propietari_id int REFERENCES client(id),
	categoria_id int REFERENCES categoria(id)
);
create table entrada_parc(
	entrada_id int,
	parc_id int,
	PRIMARY KEY(entrada_id,parc_id)
);
create table categoria_entrada(
	id int PRIMARY KEY,
	nom varchar(30) UNIQUE
);
create table parc(
	id int PRIMARY KEY,
	nom varchar(30),
	url_foto varchar(50)
);
create table zona(
	id int,
	parc_id int,
	nom varchar(30),
	PRIMARY KEY(id,parc_id)
);
create table atraccio(
	id int PRIMARY KEY,
	zona_id int REFERENCES zona(id),
	--incidencia_actual_id int REFERENCES incidencia(id),
	estat_actual_id int REFERENCES estat_operatiu(id),
	capacitat_maxima_ronda int,
	descripcioHTML varchar(300),
	nom varchar(30),
	temps_per_ronda int,
	url_foto varchar(50),
	clients_cua int,
	alçada_minima_acompanyat int,
	alçada_minima int 
);
create table tipus_acces(
	id int PRIMARY KEY,
	nom varchar(30) UNIQUE
);
create table estat_operatiu(
	id int PRIMARY KEY,
	nom varchar(30) UNIQUE
);
create table incidencia(
	id int,
	atraccio_id int REFERENCES atraccio(id),
	estat_operatiu_id int REFERENCES estat_operatiu(id),
	data_fi date,
	data_inici date,
	missatge_estat varchar(50),
	data_fi_prevista date,
	PRIMARY KEY(id,atraccio_id)
);
create table tipus_passi_expres(
	id int PRIMARY KEY,
	nom varchar(30) UNIQUE,
	preu_dia float
);
create table passi_express(
	id int PRIMARY KEY,
	client int,
	data date
);
create table tipus_acces_atraccio(
	tipus_pasi_express_id int,
	atraccio_id int,
	PRIMARY KEY(tipus_pasi_express_id,atraccio_id)
);
create table client(
	id int PRIMARY KEY,
	nif varchar(10),
	nom varchar(30),
	cognom1 varchar(30),
	cognom2 varchar(30)
);

--una atracció pot tenir mes d'una incidencia oberta??, si pot incidencia actual es incoherent

--numero_dusos es redundant jja que o sera 1 o sera 0 o l'haurem de modificar cada cop que puja a l'atracció, seria mes util afegir un registre nou , i aixi podem controlar dates i hores.

--quines dates es guarden en entrada i en passi express

--passi expres es valid nomes el dia de compra??