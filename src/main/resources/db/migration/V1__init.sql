CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL
);

CREATE TABLE project (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE task (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       status VARCHAR(50),
                       project_id BIGINT,
                       CONSTRAINT fk_project
                           FOREIGN KEY(project_id)
                               REFERENCES project(id)
                               ON DELETE CASCADE
);