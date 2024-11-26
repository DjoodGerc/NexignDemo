CREATE TABLE if NOT EXISTS status (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE if NOT EXISTS task (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    duration bigint NOT NULL,
    status_id INTEGER NOT NULL,
    FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS monitor  (
    id SERIAL PRIMARY KEY,
    task_id INTEGER,
    FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE CASCADE,
    check_time TIMESTAMP NOT NULL,
    status_id INTEGER,
    FOREIGN KEY (status_id) REFERENCES status(id) ON DELETE CASCADE
);

INSERT INTO status (id, name) VALUES
    (0, 'Not Started'),
    (1, 'In Progress'),
    (2, 'Finished')
ON CONFLICT (id) DO NOTHING;