# Тестовое задание для VK

Необходимо реализовать rest api с использованием любого известного фреймворка или библиотеки на Java >=17.
В задании нужно будет написать rest api для перенаправления запросов на https://jsonplaceholder.typicode.com/
Реализовать базовую авторизацию, проработать ролевую модель доступа к данным и ведение аудита.

## Требования

1. **Проксирующие обработчики**: Реализовать обработчики (GET, POST, PUT, DELETE), которые проксируют запросы
   на [jsonplaceholder](https://jsonplaceholder.typicode.com/).
    - /api/posts/**
    - /api/users/**
    - /api/albums/**

2. **Базовая авторизация**: Реализовать базовую авторизацию с несколькими аккаунтами, имеющими разные роли.

3. **Ролевая модель доступа**: Реализовать ролевую модель с минимум 4 ролями:
    - ROLE_ADMIN: доступ ко всем обработчикам
    - ROLE_POSTS: доступ к обработчикам /posts/**
    - ROLE_USERS: доступ к обработчикам /users/**
    - ROLE_ALBUMS: доступ к обработчикам /albums/**

4. **Ведение аудита**: Реализовать ведение аудита действий (дата-время, пользователь, статус доступа, параметры запроса
   и т.д.).

5. **Inmemory кэш**: Реализовать кэш в памяти для уменьшения числа запросов к jsonplaceholder. Изменения данных должны
   сначала происходить в кэше, а затем отправляться запросы на jsonplaceholder.

## Будет плюсом

1. **Простота запуска приложения**: Желательно использовать Gradle или Maven для упрощения запуска вашего приложения.

2. **Использование базы данных**:
    - Для ведения аудита,
    - Для хранения данных пользователей

3. **REST API для пользователй**:
    - Добавление REST API для создания пользователей.

4. **Расширенная ролевая модель**: Например, ROLE_POSTS_VIEWER, ROLE_POSTS_EDITOR и т.д.

5. **Юнит-тесты**: Написать юнит-тесты для написанного кода.

6. **Конечная точка для WebSocket**: Реализовать конечную точку для запросов по WebSocket.
    - /ws
    - Все запросы на реализованную конечную точку перенаправлять на echo
      сервер https://websocket.org/tools/websocket-echo-server/
    - Также реализовать базовую авторизацию и ролевую модель, ведение аудита запросов. Кэш не требуется. Для подключения
      по websocket использовать wss://echo.websocket.org

## Выполнено

В основном блоке реализованы следующие пункты:

1. **Проксирующие обработчики**
2. **Базовая авторизация**
3. **Ролевая модель доступа**
4. **Inmemory кэш**

В дополнительном блоке реализованы следующие пункты:

1. **Использование базы данных**
2. **REST API для пользователй**
3. **Юнит-тесты**
