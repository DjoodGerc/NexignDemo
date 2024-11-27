Данное задание было предоставлено для выполнения компанией Nexign.

url для сваггера:
- http://localhost:8080/swagger-ui/index.html swagger ui
- http://localhost:8080/api-docs для yaml формата сваггера
- http://localhost:8080/api-docs.yaml скачать yaml сваггера


бд:
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

в KafkaService лежит продюсер, который каждые 6 секунд отправляет Task в топик. Время выполнения (duration) каждого отправленного таска - 10 секунд.
Консьюмер слушает топик и скидывает задачи в executor (FixedThreadPool, колличество пулов указывается в application.yml).
в момент передачи консьюмером задачи в сервис, она сохраняется в монитор со статусом 0 (указывается время сохранения).
Когда задача передается в executor, она сохраняется в monitor со статусом 1, после чего начинает исполняться (Thread.sleep(duration)).
После окончания выполнения сохраняется в monitor со статусом 2.


+Slf4j
+liquibase
+docker
