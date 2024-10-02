CREATE TABLE IF NOT EXISTS utilisateurs(
	id SERIAL PRIMARY KEY,
	user_name VARCHAR(255) NOT NULL,
	user_pass VARCHAR(255) NOT NULL,
	nom VARCHAR(255) NOT NULL,
	prenom VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	manager BOOLEAN NOT NULL
);

CREATE TYPE status_enum AS ENUM(
	'En attente',
	'En cours',
	'Termine'
);

CREATE TABLE IF NOT EXISTS tasks(
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	description VARCHAR(255) NOT NULL,
	createdAt Date NOT NULL,
	dueDate Date NOT NULL,
	status status_enum NOT NULL,
	creator_id INT NOT NULL,
	assignee_id INT,

	CONSTRAINT creator_fk FOREIGN KEY (creator_id) REFERENCES utilisateurs(id),
    CONSTRAINT assignee_fk FOREIGN KEY (assignee_id) REFERENCES utilisateurs(id)
	
);

CREATE TABLE IF NOT EXISTS tag(
	id SERIAL PRIMARY KEY,
	tag_name VARCHAR(255) NOT NULL,
	task_id INT NOT NULL,
    CONSTRAINT task_fk FOREIGN KEY (task_id) REFERENCES tasks(id)
);