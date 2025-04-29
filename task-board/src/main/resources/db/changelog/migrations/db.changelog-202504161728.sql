--liquibase formatted sql
--changeset nohana:202504161728
--comment: boards table create

CREATE TABLE BOARDS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
) ENGINE=InnoDb;

--rollback DROP TABLE BOARDS