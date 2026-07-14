ALTER TABLE project
ADD COLUMN owner_id BIGINT;

ALTER TABLE project
ADD CONSTRAINT fk_project_owner
FOREIGN KEY (owner_id)
REFERENCES users(id)