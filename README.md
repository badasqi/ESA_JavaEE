# ЛР 1 JavaEE
В качестве сервера приложений был использован Glassfish 6.0.25
БД - MySQL

## Data Layer
Были созданы 2 сущности (Client, Provider).
Provider - провайдер интернета. Client - клиенты провайдеров.
Один провайдер может иметь множество клиентов ManyToOne, клиенту хватает одного провайдера OneToMany.

## Business Layer
Логика приложения реализована в классах ClientBean, ProviderBean
## Конфигурация GlassFish
В консоли администратора были сконфигурированы ConnectionPool, DataSource для подключения сервера к БД.

![image](https://github.com/badasqi/ESA_JavaEE/assets/78803025/13c2d961-ee77-4a5d-a4a3-53ed10754df1)

Задеплоенное приложение

![image](https://github.com/badasqi/ESA_JavaEE/assets/78803025/53639364-346b-4c5b-a5fd-577dd5d8e834)


## Пример запросов в PostMan
Был реализован CRUD с помощью сервлетов ClientListServlet, ProviderListServlet. Список провайдеров и клиентов
отображается на JSP страницах.

### Пост запрос на добавление провайдера, после добавления перенаправляет на список провайдеров
![image](https://github.com/badasqi/ESA_JavaEE/assets/78803025/bb4773de-022b-49b7-991f-160950fe7c43)

### Пост запрос на добавление клиента, после добавления перенаправляет на список клиентов
![image](https://github.com/badasqi/ESA_JavaEE/assets/78803025/548b3589-d656-4ada-ba0b-7ae2a026e1c6)

