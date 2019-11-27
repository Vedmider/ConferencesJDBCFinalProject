# ConferencesJDBCFinalProject

Conference project:
Система Управления Конференциями. Существуют роли: Администратор, Модератор, Спикер и обычный Пользователь. Модератор может закреплять, предлагать, изменять тему доклада за спикером, а также регулировать время и место проведения мероприятия. Необходимо учесть возможность просмотра прошедших/будущих митингов. У каждого Спикера существует свой рейтинг, в зависимости от рейтинга начисляется больше бонусов. Спикер может предлагать свой Доклад. Должна быть статистика зарегистрировавшихся людей и сколько физически всего пришли на Доклад. Реализовать уведомление участников о предстоящих события.

How to install:
1. Clone project
2. Run schema.sql from resources/ folder
3. Run populate.sql from resources/data/ folder
4. Run in terminal command a. mvn clean tomcat7:run b. ? Add configuration / command
5. Go to link localhost:8888/conferences

Conferences web app. Manage conferences events.
Functionality:
1. Create/update/delete conferences events.
2. Create/update/delete reports to conferences events.
3. Create/update/delete site users with different roles: ADMINISTRATION, SPEAKERS, USERS, MODERATORS.
4. Anonymous users have acces only to index page with login.
   USER have acces to page with speakers and conferences list
   SPEAKER have acces to Admin page and Speakers page
   MODERATOR users can update/create reports
   ADMINISTRATOR can update/delete/create conferences, reports and users.
5. There are different levels of access to application pages based on users role.
7. There are login, logout and registration functionality.

