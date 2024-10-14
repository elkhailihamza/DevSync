CREATE TABLE IF NOT EXISTS utilisateurs(
	id SERIAL PRIMARY KEY,
	user_name VARCHAR(255) NOT NULL,
	user_pass VARCHAR(255) NOT NULL,
	nom VARCHAR(255),
	prenom VARCHAR(255),
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
	createdAt TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NOW(),
	dueDate TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NOW() + INTERVAL '1 day',
	status status_enum NOT NULL,
	creator_id INT NOT NULL,
	assignee_id INT,
	assignedByManager BOOLEAN NOT NULL DEFAULT false,

	CONSTRAINT start_before_end CHECK (createdAt < dueDate),
	CONSTRAINT creator_fk FOREIGN KEY (creator_id) REFERENCES utilisateurs(id),
    CONSTRAINT assignee_fk FOREIGN KEY (assignee_id) REFERENCES utilisateurs(id)
	
);

CREATE TABLE IF NOT EXISTS tags(
	id SERIAL PRIMARY KEY,
	tag_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS task_tags (
    task_id INT NOT NULL,
    tag_id INT NOT NULL,

    CONSTRAINT task_tag_fk FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    CONSTRAINT tag_task_fk FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE,
    PRIMARY KEY (task_id, tag_id)
);
