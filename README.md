# test-task-microservice

В корне проекта находится `docker-compose.yml` и `Dockerfile`. Из `docker-compose.yml` сразу собирается `Dcokerfile`

## endpoint-ы
- POST `/api/v1/users` - создать пользователя
- GET `/api/v1/users/{id}` - получить информацию о пользователе
- PUT `/api/v1/users/{id}` - обновить пользователя
- DELETE `/api/v1/users/{id}` - удалить пользователя
- POST `/api/v1/users/{id}/subscriptions` - добавить подписку
- GET `/api/v1/users/{id}/subscriptions` - получить подписки пользователя
- DELETE `/api/v1/users/{id}/subscriptions/{sub_id}` - удалить подписку
- GET `/api/v1/subscriptions/top` - получить ТОП-3 популярных подписок
