--liquibase formatted sql
--changeset nohana:202504161729
--comment: BOARD_COLUMNS table create

CREATE TABLE BOARD_COLUMNS(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    `order` int NOT NULL,
    kind VARCHAR(10) NOT NULL,
    board_id BIGINT NOT NULL,
    CONSTRAINT board__boards_columns_fk FOREIGN KEY (board_id) REFERENCES BOARDS(id) ON DELETE CASCADE,
    CONSTRAINT id_order_pk UNIQUE unique_id_order (board_id, `order`)
) ENGINE=InnoDb;

--rollback DROP TABLE BOARD_COLUMNS