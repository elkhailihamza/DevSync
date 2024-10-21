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
	creator_id INT REFERENCES utilisateurs(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
	assignee_id INT REFERENCES utilisateurs(id) ON DELETE CASCADE ON UPDATE CASCADE,
	isreplaceable BOOLEAN NOT NULL DEFAULT true,
	replacementdate TIMESTAMP(0) WITHOUT TIME ZONE,

	CONSTRAINT start_before_end CHECK (createdAt < dueDate)
);

CREATE TABLE IF NOT EXISTS tags(
	id SERIAL PRIMARY KEY,
	tag_name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS task_tags (
    task_id INT REFERENCES tasks(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
    tag_id INT REFERENCES tags(id) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,

    PRIMARY KEY (task_id, tag_id)
);

CREATE TABLE IF NOT EXISTS user_tokens (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES utilisateurs(id),
    daily_update_tokens INT DEFAULT 2,
    monthly_deletion_tokens INT DEFAULT 1,
    last_reset_date DATE,
    UNIQUE(user_id)
);

CREATE TYPE request_type_enum AS ENUM (
	'Update',
	'Delete'
);

CREATE TABLE IF NOT EXISTS task_requests(
	id SERIAL PRIMARY KEY,
	task_id INT REFERENCES tasks(id),
	askRequest VARCHAR(255),
	managerapproved BOOLEAN,
	request_type request_type_enum NOT NULL,
	UNIQUE(task_id)
);