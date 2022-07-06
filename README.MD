The project consists of API and UI tests. Below are the files containing tests:
- AppTest.java - API tests
- MailTravelTests.java - UI tests

###Pre-requisites:
- JDK 11+
- Apache Maven 3.6 or more

###Instructions to run:
- Open a terminal and cd to project root directory i.e the directory containing pom.xml
- Execute below command:

> mvn clean test


###Notes:
- By default UI test runs in chrome. To run tests using firefox, please change parameter "browser" to firefox in testng.xml. This file is in project root folder.
- Once execution is complete, html test reports are created and located at  folder "test-output/html/index.html"
- The UI test is failing while clicking 'continue' button after filling the passenger details. The error message is 
>' MailTravelTests.verifyHolidayBooking:96 » WebDriver unknown error: cannot determine loading status'. 
It looks to be specific to chromedriver version.
- The project is using WebDriverManager and hence there is no need to explicitely download geckodriver or chromedriver and set the path.