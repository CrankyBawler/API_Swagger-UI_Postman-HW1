create table vehicle (
                         id serial primary key ,
                         brand varchar(225),
                         model varchar(225),
                         cost money
                         );
ALTER TABLE vehicle
    ADD PRIMARY KEY (id);


create table person (
                        id serial primary key ,
                        name varchar(225),
                        age int,
                        vehicle_owner boolean);
                        vehicle_id int REFERENCES vehicle (id)
                        );





select student.name, student.age, faculty.name
from student
         join faculty on student.faculty_id = faculty.id;

select s.id, s.name, avtr.id
from student as s
         join avatar avtr on s.id = avtr.student_id;