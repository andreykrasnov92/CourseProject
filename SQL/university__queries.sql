
/* 1. Вывести расписание конкретной группы. */

SELECT groups.group_number, timetable_records.week_parity,
	timetable_records.weekday, timetable_records.pair_number,
	disciplines.discipline_name, disciplines.lesson_type,
	timetable_records.room_number
FROM groups INNER JOIN timetable_records INNER JOIN disciplines
	ON groups.group_id = timetable_records.group_id
	AND timetable_records.discipline_id = disciplines.discipline_id
WHERE groups.group_number = "6407";

/* 2. Вывести информацию о конкретном преподавателе. */

SELECT teacher_name, teacher_login, teacher_password,
	academic_degree, academic_rank, department
FROM teachers
WHERE teacher_name = "Смирнов Виктор Андреевич";

/* 3. Вывести преподавателей, читающих конкретную дисциплину. */

SELECT teacher_name, academic_degree, academic_rank,
	department, discipline_name, lesson_type
FROM teachers INNER JOIN teachers_and_disciplines INNER JOIN disciplines
	ON teachers.teacher_id = teachers_and_disciplines.teacher_id
	AND teachers_and_disciplines.discipline_id
		= disciplines.discipline_id
WHERE discipline_name = "Информатика";

/* 4. Вывести преподавателей конкретной группы. */

SELECT DISTINCT teacher_name, academic_degree, academic_rank, department
FROM teachers INNER JOIN teachers_and_disciplines
	INNER JOIN timetable_records INNER JOIN groups
	ON teachers.teacher_id = teachers_and_disciplines.teacher_id
	AND teachers_and_disciplines.discipline_id
		= timetable_records.discipline_id
	AND timetable_records.group_id = groups.group_id
WHERE groups.group_number = "6406";

/* 5. Вывести студентов конкретной группы. */

SELECT students.student_name, students.student_login,
	students.student_password, groups.group_number
FROM students INNER JOIN groups
	ON students.group_id = groups.group_id
WHERE groups.group_number = "6408";

/* 6. Вывести дисциплины, которые изучает конкретная группа. */

SELECT DISTINCT discipline_name, lesson_type
FROM disciplines INNER JOIN timetable_records INNER JOIN groups
	ON disciplines.discipline_id = timetable_records.discipline_id
	AND timetable_records.group_id = groups.group_id
WHERE groups.group_number = "6407";

/* 7. Вывести дисциплины, которые читает конкретный преподаватель. */

SELECT disciplines.discipline_name, disciplines.lesson_type
FROM disciplines INNER JOIN teachers_and_disciplines INNER JOIN teachers
	ON (disciplines.discipline_id
		= teachers_and_disciplines.discipline_id)
	AND (teachers_and_disciplines.teacher_id = teachers.teacher_id)
WHERE teachers.teacher_name = "Безруков Александр Васильевич";

/* 8. Вывести преподавателей, читающих дисциплины, и сами эти дисциплины. */

SELECT teachers.teacher_name,
	disciplines.discipline_name, disciplines.lesson_type
FROM disciplines INNER JOIN teachers_and_disciplines INNER JOIN teachers
	ON (disciplines.discipline_id
		= teachers_and_disciplines.discipline_id)
	AND (teachers_and_disciplines.teacher_id = teachers.teacher_id);
