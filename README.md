## Пример использования [Allure TestOps](https://docs.qameta.io/allure-testops/)
В данном мини-проекте представлен пример использования Allure TestOps для создания тестовой документации и запуска UI, API автотестов и ручных UI тестов, проверяющих функциональность web-версии сайта **[Tricentis Demo Web Shop](http://demowebshop.tricentis.com/)**.<br/>


### Использованный технологический стек
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" /> <img src="https://img.shields.io/badge/Selenide-b400b4?style=for-the-badge&logo=selenide&logoColor=white" /> <img src="https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white" /> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white" /> <img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white" /><br/>
<img src="https://img.shields.io/badge/Selenoid-0080c1?style=for-the-badge&logo=selenoid&logoColor=white" /> <img src="https://img.shields.io/badge/Docker-24b2e4?style=for-the-badge&logo=docker&logoColor=white" /> <img src="https://img.shields.io/badge/Allure TestOps-4ddf82?style=for-the-badge&logo=Allure TestOps&logoColor=white" /> <img src="https://img.shields.io/badge/Jira-0052CC?style=for-the-badge&logo=Jira&logoColor=white" /> 


### Запуск тестов в CI Jenkins и Allure TestOps
Для запуска автотестов сконфигурирована **[job](https://jenkins.autotests.cloud/job/08-WakeUpTheo-AllureExamples/)** в CI Jenkins с интеграцией Allure TestOps. В TestOps создан **[проект](https://allure.autotests.cloud/project/892/dashboards)** в котором генерируется тестовая документация при запуске job в CI. Также в проект TestOps добавлены ручные тест-кейсы:<br/><br/>
![](./images/manual_test.png)

С помощью аннтотаций Allure и настроек проекта TestOps прописана дополнительная информация, например, о создателе автотеста, наименование feature и микросервиса, test layer и др. Благодаря добавленной метаинформации можно фильтровать тесты в TestOps:<br/><br/>
![](./images/sorting.png)

Для запуска тестов из TestOps создается Run с выбранными тестами с привязкой к CI job. При этом в CI в формате json передается информация о тест-плане, т.е. о том, какие тесты и в каком количестве включены в Run:<br/><br/>
![](./images/test_plan.png)

Одновременно с запуском тестов в CI в реальном времени отображается информация о процессе выполнения тестов в TestOps:<br/><br/>
![](./images/run.png)

Прохождение ручных тест-кейсов, включенных в TestOps Run:<br/><br/>
![](./images/passing_manual_test.png)

Результаты по итогам выполнения TestOps Run:<br/><br/>
![](./images/test_results.png)


### Метрики в Allure TestOps
Для отображения метрик создаются различные dashboards.<br/><br/>
**Примеры**<br/>
Успешность и время выполнения тестов:<br/><br/>
![](./images/auto_metrics.png)

Статистика по запускам в разных environments:<br/><br/>
![](./images/stages_metrics.png)

Метрики по членам команды:<br/><br/>
![](./images/team_metrics.png)


### Интеграция с Jira
С помощью контекстного меню TestOps создана привязка информации о тест-кейсах и результатах запусков с **[задачей](https://jira.autotests.cloud/browse/HOMEWORK-314)** в Jira:<br/><br/>
![](./images/jira.png)
