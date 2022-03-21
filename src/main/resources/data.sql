DELETE
FROM possible_answer;
DELETE
FROM question;
DELETE
FROM input_answer;
DELETE
FROM user_answer;
DELETE
FROM survey;

INSERT INTO survey (id, name, start_date)
VALUES (1, 'First survey', '2022-03-18'),
       (2, 'Second survey', '2022-03-18');

INSERT INTO question (id, content, answer_type, survey_id)
VALUES (1, 'First question of first survey', 'TEXT', 1),
       (2, 'Second question of first survey', 'ONE_SELECT', 1),
       (3, 'Third question of first survey', 'SEVERAL_SELECT', 1),
       (4, 'First question of second survey', 'TEXT', 2);

INSERT INTO possible_answer (question_id, number, answer)
VALUES (2, 1, 'First answer of first question'),
       (2, 2, 'Second answer of first question'),
       (2, 3, 'Third answer of first question'),
       (2, 4, 'fourth answer of first question'),
       (3, 1, 'First answer of second question'),
       (3, 2, 'Second answer of second question'),
       (3, 3, 'Third answer of second question'),
       (3, 4, 'fourth answer of second question');

INSERT INTO user_answer (id, user, date_answer, survey_id)
VALUES (1, 1, '2022-03-18', 1),
       (2, 1, '2022-03-18', 2),
       (3, null, '2022-03-18', 2);

INSERT INTO input_answer (input_id, question, answer)
VALUES (1, 1, 'Answer user on first survey'),
       (1, 2, '1'),
       (1, 3, '1 3'),
       (2, 4, 'Answer user on second survey'),
       (3, 4, 'Answer somebody second survey');