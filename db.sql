DROP database IF EXISTS port_aventura;
CREATE database port_aventura;
USE port_aventura;

CREATE TABLE categoria_entrada(
	id INT AUTO_INCREMENT,
	nom VARCHAR(30),
	PRIMARY KEY (id),
	UNIQUE (nom)
);
CREATE TABLE tipus_acces(
	id INT,
	nom VARCHAR(30) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (nom)
);
CREATE TABLE estat_operatiu(
	id INT,
	nom VARCHAR(30) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (nom)
);
CREATE TABLE preu(
	id INT,
	dies INT NOT NULL,
	preu_adult INT,
	preu_nen_senior INT,
	preu_discapacitat INT,
	PRIMARY KEY (id)
);
CREATE TABLE client(
	id INT,
	nif VARCHAR(10) NOT NULL,
	contrasenya VARCHAR(30),
	nom VARCHAR(30),
	cognom1 VARCHAR(30),
	cognom2 VARCHAR(30),
	PRIMARY KEY (id)
);
CREATE TABLE parc(
	id INT,
	nom VARCHAR(30) NOT NULL,
	url_foto VARCHAR(50),
	PRIMARY KEY (id)
);
CREATE TABLE tipus_passi_expres(
	id INT,
	nom VARCHAR(30),
	preu_dia FLOAT,
	PRIMARY KEY (id),
	UNIQUE (nom)
);


CREATE TABLE entrada(
	id INT AUTO_INCREMENT,
	data DATE,
	dies_validesa INT,
	preu float,
	client_id INT,
	categoria_id INT,
	preu_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (client_id) REFERENCES client(id),
	FOREIGN KEY (categoria_id) REFERENCES categoria_entrada(id),
	FOREIGN KEY (preu_id) REFERENCES preu(id)
);
CREATE TABLE zona(
	id INT,
	parc_id INT,
	nom VARCHAR(30) NOT NULL,
	PRIMARY KEY (id,parc_id),
	FOREIGN KEY (parc_id) REFERENCES parc(id)
);
CREATE TABLE atraccio(
	id INT,
	zona_id INT,
	-- incidencia_actual_id INT REFERENCES incidencia(id),
	estat_actual_id INT,
	capacitat_maxima_ronda INT,
	descripcioHTML VARCHAR(300),
	nom VARCHAR(30) NOT NULL,
	temps_per_ronda INT,
	url_foto VARCHAR(50),
	clients_cua INT,
	alçada_minima_acompanyat INT,
	alçada_minima INT,
	PRIMARY KEY (id),
	FOREIGN KEY (zona_id) REFERENCES zona(id),
	FOREIGN KEY (estat_actual_id) REFERENCES estat_operatiu(id)
);
CREATE TABLE incidencia(
	id INT,
	atraccio_id INT,
	estat_operatiu_id INT,
	data_fi DATE,
	data_inici DATE,
	missatge_estat VARCHAR(50),
	data_fi_prevista DATE,
	PRIMARY KEY(id,atraccio_id),
	FOREIGN KEY (atraccio_id) REFERENCES atraccio(id),
	FOREIGN KEY (estat_operatiu_id) REFERENCES estat_operatiu(id)
);

CREATE TABLE preu_parc(
	preu_id INT,
	parc_id INT,
	PRIMARY KEY(preu_id,parc_id),
	FOREIGN KEY (preu_id) REFERENCES preu(id),
	FOREIGN KEY (parc_id) REFERENCES parc(id)
);
CREATE TABLE entrada_parc(
	entrada_id INT,
	parc_id INT,
	PRIMARY KEY(entrada_id,parc_id),
	FOREIGN KEY (entrada_id) REFERENCES entrada(id),
	FOREIGN KEY (parc_id) REFERENCES parc(id)
);
CREATE TABLE tipus_acces_atraccio(
	tipus_passi_id INT,
	atraccio_id INT,
	PRIMARY KEY (tipus_passi_id,atraccio_id),
	FOREIGN KEY (tipus_passi_id) REFERENCES tipus_passi_expres(id),
	FOREIGN KEY (atraccio_id) REFERENCES atraccio(id)
);
CREATE TABLE passi_express(
	id INT,
	client_id INT,
	tipus_id INT,
	data DATE,
	PRIMARY KEY (id),
	FOREIGN KEY (client_id) REFERENCES client(id),
	FOREIGN KEY (tipus_id) REFERENCES tipus_passi_expres(id)
);

-- una atracció pot tenir mes d'una incidencia oberta??, si pot incidencia actual es incoherent

-- numero_dusos es redundant jja que o sera 1 o sera 0 o l'haurem de modificar cada cop que puja a l'atracció, seria mes util afegir un registre nou , i aixi podem controlar dates i hores.

-- en accedir a una atraccio amb passi express s'incrementa numero d'usos d'un registre unic per passi o be es fa un registe per cada acces amb PK passi i atraccio, o be es fa un registr per atraccio i si existeix es modifica i si no es crea?

-- quines dates es guarden en entrada i en passi express

-- passi expres es valid nomes el dia de compra??

-- falten taules? usuaris/passwords? cua atraccio?

--control errors al conectar db inexistent
commit;
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
