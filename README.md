# Automation of ITD Formation

Spring Boot web application for automating ITD formation during utility network construction.

## Prerequisites

- Docker Engine 24+
- Docker Compose plugin (`docker compose`)

No local Java or MySQL installation is required for containerized run.

## Quick Start (Docker Compose)

1. Copy environment template:
   - `cp .env.example .env`
2. Start services:
   - `docker compose up --build`
3. Open the app:
   - [http://localhost](http://localhost)

The stack starts:
- `db`: MySQL 8.3 with persistent volume `mysql_data`
- `app`: Spring Boot application built from `Dockerfile`
- `nginx`: reverse proxy that exposes the app on port `80` by default

## Default Environment Variables

Defined in `.env.example`:

- `APP_PORT=8080`
- `NGINX_PORT=80`
- `SPRING_PROFILES_ACTIVE=docker`
- `MYSQL_PORT=3306`
- `MYSQL_DATABASE=ITD_db`
- `MYSQL_USER=itd_user`
- `MYSQL_PASSWORD=itd_password`
- `MYSQL_ROOT_PASSWORD=root`

`app` connects to DB via service hostname `db` inside Compose network.
`nginx` proxies incoming HTTP requests to `app:8080`.

## Database Initialization

On first startup (fresh volume), MySQL executes:

- `docker/mysql/init/01-init-auth.sql`

This script creates security tables expected by `JdbcUserDetailsManager`:
- `user_data`
- `user_role`

It also seeds a default account for first login:
- Username: `admin`
- Password: `admin`
- Role: `DIRECTOR`

Important: change credentials immediately for non-local environments.

## Common Operations

- Start in background:
  - `docker compose up -d --build`
- View logs:
  - `docker compose logs -f app`
  - `docker compose logs -f db`
  - `docker compose logs -f nginx`
- Stop services:
  - `docker compose down`
- Stop and remove DB data (full reset):
  - `docker compose down -v`
- Rebuild only app image:
  - `docker compose build app`

## Troubleshooting

- Port already in use:
  - Change `NGINX_PORT` or `MYSQL_PORT` in `.env`, then restart.
- App cannot connect to DB:
  - Check `db` health: `docker compose ps`
  - Inspect DB logs: `docker compose logs db`
  - Ensure `.env` values are consistent between `MYSQL_*` variables.
- Seed user missing:
  - Initialization scripts run only on first DB volume creation.
  - Run `docker compose down -v` and start again to reinitialize.

## Local Non-Docker Run (optional)

If running without Docker, you need Java 22 and MySQL:

- `./mvnw spring-boot:run`
- `./mvnw test`
