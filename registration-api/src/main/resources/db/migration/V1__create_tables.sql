CREATE TABLE student (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(256) NOT NULL
);

CREATE TABLE course (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE registration (
    id VARCHAR(255) PRIMARY KEY,
    student_id VARCHAR(255) NOT NULL,
    course_id VARCHAR(255) NOT NULL
);
