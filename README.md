# FR-Test

## Разворачивать локально

## Authorization: Basic

User: Admin

Password: 12345

## API:

### 1. Получение списка опросов
request: GET jsons/surveys

response body: Список survey_response.json

### 2. Получение опроса по идентификатору
request: GET jsons/surveys/{id}

response body: survey_response.json

### 3. Создание опроса
request: POST /surveys

request body: jsons/survey_request.json

### 4. Обновление основной информации опроса по идентификатору
request: PUT /surveys/{id}

request body: jsons/survey.json

### 5.Удаление опроса по идентификатору
request: DELETE /surveys/{id}

### 6. Добавление вопросов к опросу
request: POST /surveys/{id}/questions

request body: jsons/question_request.json

### 7. Обновление вопросов у опроса
request: PUT /surveys/{id}/questions

request body: jsons/question_request.json

### 8.Удаление вопросов у опроса
request: DELETE /surveys/{id}/questions

request body: Список id вопросов

### 9. Создание ответов на опрос
request: POST /surveys/{id}/answers

request param: userId

request body: jsons/answer_request.json

### 10.Получение ответов пользователя на опросы
request: GET /answers

request param: userId

response body: jsons/answer_response.json