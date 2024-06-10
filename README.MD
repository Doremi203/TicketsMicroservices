# TicketsMicroservices

## Описание проекта
TicketsMicroservices это проект микросервисов для управления билетами. Проект включает в себя два сервиса: сервис аутентификации и сервис билетов. Сервис аутентификации отвечает за аутентификацию пользователей и авторизацию, а сервис билетов управляет созданием, и получением заказов билетов.

## Архитектура проекта
[![temp-Image-Gh3-LP9.avif](https://i.postimg.cc/C5sn75Xm/temp-Image-Gh3-LP9.avif)](https://postimg.cc/K34YvGZM)
## Services

### Authentication Service
Отвечает за аутентификацию пользователей и авторизацию.

### Tickets Service
Отвечает за управление заказами билетов.

## Запуск проекта

### Требования
- Docker
- Docker Compose

### Конфигурация
Создайте файл `.env` в корневой директории проекта и добавьте следующие переменные окружения:
```env
# Example .env file content
AUTH_SERVICE_DB_USER=auth-service
AUTH_SERVICE_DB_PASSWORD=auth-service
AUTH_SERVICE_DB_NAME=auth-service
AUTH_SERVICE_DB_HOST=authentication-service-db
AUTH_SERVICE_DB_PORT=5432
AUTH_SERVICE_JWT_SECRET="your 256-bit secret"
TICKETS_SERVICE_DB_USER=tickets-service
TICKETS_SERVICE_DB_PASSWORD=tickets-service
TICKETS_SERVICE_DB_NAME=tickets-service
TICKETS_SERVICE_DB_HOST=tickets-service-db
TICKETS_SERVICE_DB_PORT=5432
```

### Сборка образов и запуск контейнеров
Сборка образов с помощью jib плагина:
```bash
./gradlew tickets-service:api:jibDockerBuild
./gradlew authentication-service:api:jibDockerBuild
```
P.s. Если появляется ошибка 2 об отсутствии docker, то нужно перезапустить daemon gradle
```bash
./gradlew --stop
```

### Запуск с помощью Docker Compose
```bash
docker compose up
```

## Документация OpenAPI

### Authentication Service
- [API Documentation](http://localhost/auth-api/swagger-ui.html)

### Tickets Service
- [API Documentation](http://localhost/tickets-api/swagger-ui.html)

## License
This project is licensed under the MIT License.