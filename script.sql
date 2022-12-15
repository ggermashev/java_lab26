create table subjects (
    id serial primary key,
    title varchar(64) NOT NULL UNIQUE,
    sub_day varchar(16) NOT NULL CHECK ( sub_day in ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'))
);

create table rooms (
    id serial primary key,
    number int UNIQUE NOT NULL
);

create table schedule_subjects (
    id serial primary key,
    subject_id int NOT NULL references subjects(id),
    room_id int NOT NULL references rooms(id)
);

create table teachers (
    id serial primary key,
    fio varchar(128) NOT NULL
);

create table schedule_teachers (
    id serial primary key,
    teacher_id int NOT NULL references teachers(id),
    subject_id int NOT NULL references subjects(id),
    lessons_amount int NOT NULL DEFAULT 0,
    students_amount int NOT NULL,
    UNIQUE (teacher_id, subject_id)
);