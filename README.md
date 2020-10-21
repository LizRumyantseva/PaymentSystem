# Payment system
 QA Automation course project <br>
 Задание: Система платежи. Клиент имеет одну или несколько кредитных карт.

Реализация: <br>
 - Сущности Card и Client <br>
 - Общий интерфейс DAO (generic), интерфейсы CardDAO и ClientDAO  <br>
 - Сервисный слой: классы-сервисы CardService и ClientService, использующие DAO для получения данных из БД  <br>
 - Классы для ввода-вывода данных в консоль.
 

Действия клиента через меню в консоли: <br>
   0 - Exit menu <br>
   1 - Add a client <br>
   2 - Add a card to the client <br>
   3 - Delete a card by its number <br>
   4 - Delete a client by its id <br>
   5 - Show all cards <br>
   6 - Show all clients <br>
   7 - Get client cards <br>
   8 - Make purchase/receive money to a card <br>

Скрипт для создания базы данных находится в папке SQL scripts. <br>   




