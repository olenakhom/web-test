# Coding challenge

- [Test Automation Task](UI_API_TestAutomation.pdf "Testing Task") 
- [Test Cases](TESTCASES.md "Test Cases")

### Stack of technologies
1. [Java 11](https://www.oracle.com/java/)
2. [Maven](https://maven.apache.org/)
3. [Selenide](https://selenide.org/)
4. [Junit5](https://junit.org/junit5/docs/current/user-guide/)
5. [Rest Assured](http://rest-assured.io/)
6. [Allure Reporting Tool](http://allure.qatools.ru/)

### Required installation
1. [Java 11 or higher](https://openjdk.java.net/install/index.html)
2. [IntelliJ IDEA](https://www.jetbrains.com/idea/download)
3. [Lombok Plugin](https://projectlombok.org/setup/intellij)
4. [Allure commandline for generation test report](https://docs.qameta.io/allure/#_installing_a_commandline)
5. [Maven for running tests from command line](https://maven.apache.org/install.html)
6. [Latest Chrome browser](https://www.google.com/chrome/) need to be installed for running UI tests

### Available Test Configurations via Maven:
* -Dplatform: **local**, (**linux**, **windows**, **mac**, **selenoid** - will be available after configuring remote machines). Default value is **local**.
* -Denv: **local**, **qa**, **prod**. Default value is **prod**
* -DtestTags: **smoke**, **regression**, **employee**. Default value is **regression**
* -Dbrowser: **safari**, **chrome**, **firefox**. Default value is **chrome**
* -DthreadCount: number of threads for parallel execution of UI tests, for safari browser should be 1. API tests are running always in 1 thread. Default value for UI tests is **3**.
* -Pui, -Papi: profiles to run UI or API test suites. Default value is **api**

### Prerequisites for Running UI Tests on Local Machine (Platform)
* Run script `startSeleniumGrid.sh` for Mac/Linux or `startSeleniumGrid.bat` for Windows to start Selenium Grid locally.
For MacOs to enable running tests on Safari browser write in terminal `safaridriver --enable` and config in maven command -Dbrowser=safari -DthreadCount=1
* Open URL `http://localhost:4444/grid/console` to check the Selenium Nodes.

### UI Test Execution
Run tests from command line
```
mvn clean -Pui -Denv=prod -Dplatform=local -Dbrowser=chrome -DtestTags=regression -DthreadCount=3 test
```
or 
```
mvn clean -Pui test
```

### API Test Execution
Run tests from command line
```
mvn clean -Papi -Denv=prod -DtestTags=regression test
```
or 
```
mvn clean -Papi test
```

### Allure test report generation
This is already enough to see the Allure report in one command:
```
allure serve /{project_root}/target/allure-results
```
If report generated successfully, report page should be opened automatically in the browser.

Example of UI tests execution:
![Allure Report](resources/allure_report_ui.png?raw=true "Allure Report UI Tests Example")

Example of API tests execution:
![Allure Report](resources/allure_report_api.png?raw=true "Allure Report API Tests Example")