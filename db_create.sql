DROP database IF EXISTS port_aventura;
CREATE database port_aventura;
USE port_aventura;

CREATE TABLE categoria_entrada(
	id INT,
	nom VARCHAR(30) NOT NULL,
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
	preu_adult INT NOT NULL,
	preu_nen_senior INT NOT NULL,
	preu_discapacitat INT NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE client(
	id INT AUTO_INCREMENT,
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
	url_foto VARCHAR(200) NOT NULL,
	PRIMARY KEY (id)
);
CREATE TABLE tipus_pasi_expres(
	id INT AUTO_INCREMENT,
	nom VARCHAR(30),
	preu_dia FLOAT,
	PRIMARY KEY (id),
	UNIQUE (nom)
);
CREATE TABLE entrada(
	id INT AUTO_INCREMENT,
	data DATETIME DEFAULT CURRENT_TIMESTAMP,
	dies_validesa INT,
	preu float,
	-- client_id INT,
	categoria_id INT,
	preu_id INT,
	PRIMARY KEY (id),
	-- FOREIGN KEY (client_id) REFERENCES client(id),
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
	parc_id INT,
	-- incidencia_actual_id INT REFERENCES incidencia(id),
	estat_actual_id INT,
	capacitat_maxima_ronda INT,
	descripcioHTML VARCHAR(300),
	nom VARCHAR(30) NOT NULL,
	temps_per_ronda INT,
	url_foto VARCHAR(200),
	clients_cua INT,
	alçada_minima_acompanyat INT,
	alçada_minima INT,
	PRIMARY KEY (id),
	FOREIGN KEY (zona_id,parc_id) REFERENCES zona(id,parc_id),
	FOREIGN KEY (estat_actual_id) REFERENCES estat_operatiu(id)
);

CREATE TABLE incidencia(
	id INT,
	oberta BOOLEAN NOT NULL DEFAULT FALSE,
	atraccio_id INT,
	estat_operatiu_id INT,
	data_inici DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	data_fi DATETIME,
	misatge_estat VARCHAR(50),
	data_fi_prevista DATETIME,
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
	tipus_pasi_id INT,
	atraccio_id INT,
	tipus_acces_id INT NOT NULL,
	PRIMARY KEY (tipus_pasi_id,atraccio_id),
	FOREIGN KEY (tipus_pasi_id) REFERENCES tipus_pasi_expres(id),
	FOREIGN KEY (atraccio_id) REFERENCES atraccio(id),
	FOREIGN KEY(tipus_acces_id) REFERENCES tipus_acces(id)
);
CREATE TABLE pasi_expres(
	id INT AUTO_INCREMENT,
	client_id INT,
	tipus_id INT,
	data DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	FOREIGN KEY (client_id) REFERENCES client(id),
	FOREIGN KEY (tipus_id) REFERENCES tipus_pasi_expres(id)
);
CREATE TABLE info_utilitzacio(
	id INT AUTO_INCREMENT,
	pasi_id INT,
	atraccio_id INT,
	num_usos INT NOT NULL,
	-- tipus_id int,
	PRIMARY KEY(id),
	FOREIGN KEY(pasi_id) REFERENCES pasi_expres(id),
	FOREIGN KEY(atraccio_id) REFERENCES atraccio(id)
	-- FOREIGN KEY(tipus_id) REFERENCES tipus_acces(id),
	
);
commit;