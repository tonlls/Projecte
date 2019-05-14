DROP database IF EXISTS port_aventura;
CREATE database port_aventura;
USE port_aventura;
-- enums----------------------------------
CREATE TABLE categoria_entrada(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nom VARCHAR(30) UNIQUE
);
CREATE TABLE tipus_acces(
	id INT PRIMARY KEY,
	nom VARCHAR(30) NOT NULL UNIQUE
);
CREATE TABLE estat_operatiu(
	id INT PRIMARY KEY,
	nom VARCHAR(30) NOT NULL UNIQUE
);
-- -----------------------------------------
CREATE TABLE preu(
	id INT PRIMARY KEY,
	dies INT NOT NULL,
	preu_adult INT,
	preu_nen_senior INT,
	preu_discapacitat INT
);
/*
CREATE TABLE preus(
	parc1_id INT REFERENCES parc(id),
	parc2_id INT REFERENCES parc(id),
	parc3_id INT REFERENCES parc(id),
	num_dies INT,
	preu_adult INT,
	preu_nen_senior INT,
	preu_discapacitat INT
);
*/
CREATE TABLE entrada(
	id INT PRIMARY KEY AUTO_INCREMENT,
	data DATE,
	dies_validesa INT,
	preu float,
	propietari_id INT REFERENCES client(id),
	categoria_id INT REFERENCES categoria(id)
);
CREATE TABLE client(
	id INT PRIMARY KEY,
	nif VARCHAR(10) NOT NULL,
	nom VARCHAR(30),
	cognom1 VARCHAR(30),
	cognom2 VARCHAR(30)
);
CREATE TABLE parc(
	id INT PRIMARY KEY,
	nom VARCHAR(30) NOT NULL,
	url_foto VARCHAR(50)
);
CREATE TABLE zona(
	id INT,
	parc_id INT REFERENCES parc(id),
	nom VARCHAR(30) NOT NULL,
	PRIMARY KEY(id,parc_id)
);
CREATE TABLE atraccio(
	id INT PRIMARY KEY,
	zona_id INT REFERENCES zona(id),
	-- incidencia_actual_id INT REFERENCES incidencia(id),
	estat_actual_id INT REFERENCES estat_operatiu(id),
	capacitat_maxima_ronda INT,
	descripcioHTML VARCHAR(300),
	nom VARCHAR(30) NOT NULL,
	temps_per_ronda INT,
	url_foto VARCHAR(50),
	clients_cua INT,
	alçada_minima_acompanyat INT,
	alçada_minima INT 
);
CREATE TABLE incidencia(
	id INT,
	atraccio_id INT REFERENCES atraccio(id),
	estat_operatiu_id INT REFERENCES estat_operatiu(id),
	data_fi DATE,
	data_inici DATE,
	missatge_estat VARCHAR(50),
	data_fi_prevista DATE,
	PRIMARY KEY(id,atraccio_id)
);
CREATE TABLE passi_express(
	id INT PRIMARY KEY,
	client INT,
	data DATE
);
-- -------------------------
CREATE TABLE preu_parc(
	preu_id INT REFERENCES preu(id),
	parc_id INT REFERENCES parc(id),
	PRIMARY KEY(preu_id,parc_id)
);
CREATE TABLE entrada_parc(
	entrada_id INT REFERENCES entrada(id),
	parc_id INT REFERENCES parc(id),
	PRIMARY KEY(entrada_id,parc_id)
);
CREATE TABLE tipus_passi_expres(
	id INT PRIMARY KEY,
	nom VARCHAR(30) UNIQUE,
	preu_dia FLOAT
);
CREATE TABLE tipus_acces_atraccio(
	tipus_pasi_id INT REFERENCES tipus_passi_expres(id),
	atraccio_id INT REFERENCES atraccio(id),
	PRIMARY KEY(tipus_pasi_id,atraccio_id)
);

-- una atracció pot tenir mes d'una incidencia oberta??, si pot incidencia actual es incoherent

-- numero_dusos es redundant jja que o sera 1 o sera 0 o l'haurem de modificar cada cop que puja a l'atracció, seria mes util afegir un registre nou , i aixi podem controlar dates i hores.

-- en accedir a una atraccio amb passi express s'incrementa numero d'usos d'un registre unic per passi o be es fa un registe per cada acces amb PK passi i atraccio, o be es fa un registr per atraccio i si existeix es modifica i si no es crea?

-- quines dates es guarden en entrada i en passi express

-- passi expres es valid nomes el dia de compra??

-- falten taules? usuaris/passwords? cua atraccio?

commit;
