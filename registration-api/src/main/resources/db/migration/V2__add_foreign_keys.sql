
ALTER TABLE registration
ADD CONSTRAINT fk_student
FOREIGN KEY (student_id) REFERENCES student(id);

ALTER TABLE registration
ADD CONSTRAINT fk_course
FOREIGN KEY (course_id) REFERENCES course(id);
