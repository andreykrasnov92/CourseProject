
CREATE SCHEMA IF NOT EXISTS university
    DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE university;

CREATE TABLE IF NOT EXISTS groups (
    group_id INT NOT NULL AUTO_INCREMENT,
    group_number VARCHAR(45) NOT NULL,
    PRIMARY KEY (group_id)
);

CREATE TABLE IF NOT EXISTS students (
    student_id INT NOT NULL AUTO_INCREMENT,
    student_name VARCHAR(45) NOT NULL,
    student_login VARCHAR(45) NOT NULL,
    student_password VARCHAR(45) NOT NULL,
    group_id INT NOT NULL,
    PRIMARY KEY (student_id),
    CONSTRAINT un_student_login UNIQUE (student_login),
    CONSTRAINT fk_students_groups
        FOREIGN KEY (group_id)
        REFERENCES groups (group_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS teachers (
    teacher_id INT NOT NULL AUTO_INCREMENT,
    teacher_name VARCHAR(45) NOT NULL,
    teacher_login VARCHAR(45) NOT NULL,
    teacher_password VARCHAR(45) NOT NULL,
    academic_degree VARCHAR(45) NULL,
    academic_rank VARCHAR(45) NULL,
    department VARCHAR(45) NULL,
    CONSTRAINT un_teacher_login UNIQUE (teacher_login),
    PRIMARY KEY (teacher_id)
);

CREATE TABLE IF NOT EXISTS disciplines (
    discipline_id INT NOT NULL AUTO_INCREMENT,
    discipline_name VARCHAR(45) NOT NULL,
    lesson_type VARCHAR(45) NOT NULL,
    PRIMARY KEY (discipline_id)
);

CREATE TABLE IF NOT EXISTS teachers_and_disciplines (
    teacher_id INT NOT NULL,
    discipline_id INT NOT NULL,
    PRIMARY KEY (teacher_id, discipline_id),
    CONSTRAINT fk_teachers_and_disciplines_teachers
        FOREIGN KEY (teacher_id)
        REFERENCES teachers (teacher_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_teachers_and_disciplines_disciplines
        FOREIGN KEY (discipline_id)
        REFERENCES disciplines (discipline_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS timetable_records (
    group_id INT NOT NULL,
    week_parity INT NOT NULL,
    weekday VARCHAR(45) NOT NULL,
    pair_number INT NOT NULL,
    discipline_id INT NOT NULL,
    room_number VARCHAR(45) NULL,
    PRIMARY KEY (group_id, week_parity, weekday, pair_number),
    CONSTRAINT fk_timetable_records_groups
        FOREIGN KEY (group_id)
        REFERENCES groups (group_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT fk_timetable_records_disciplines
        FOREIGN KEY (discipline_id)
        REFERENCES disciplines (discipline_id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS administrator (
    administrator_id INT NOT NULL AUTO_INCREMENT,
    administrator_name VARCHAR(45) NOT NULL,
    administrator_login VARCHAR(45) NOT NULL,
    administrator_password VARCHAR(45) NOT NULL,
    PRIMARY KEY (administrator_id),
    CONSTRAINT un_administrator_login UNIQUE (administrator_login)
);
