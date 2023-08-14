-- liquibase formatted sql

-- changeset lborisov:1
CREATE INDEX student_name_index ON student (name);

-- changeset lborisov:2
CREATE INDEX faculty_name_index ON faculty (name, color);