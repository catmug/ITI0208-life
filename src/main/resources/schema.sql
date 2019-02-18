DROP SCHEMA public CASCADE;

CREATE SEQUENCE seq1 START WITH 1;

CREATE TABLE text_table (
                          id BIGINT NOT NULL PRIMARY KEY,
                          text VARCHAR(255) NULL,
                          amount DECIMAL NOT NULL
);
