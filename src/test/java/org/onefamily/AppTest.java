package org.onefamily;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.*;

import org.onefamily.response.EmployeeResponse;
import org.onefamily.response.EmployeesResponse;
import org.onefamily.service.EmployeeService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Slf4j
public class AppTest extends BaseTest {

    private EmployeeService empService;

    @BeforeTest
    public void init(){
        config = ConfigFactory.load("testConfig.properties");
        RestAssured.baseURI = config.getString("employees.app.uri");
        mapper = new ObjectMapper();
        empService = EmployeeService.getInstance(config);
    }

    @Test
    public void verifyEmployees() throws JsonProcessingException {
        final Response response = empService.getEmployees();

        log.info("Response Status code: {}", response.statusCode());
        log.info("Response: \n{}", response.asPrettyString());
        assertThat(response.statusCode())
                .as("Employees api should respond with status code 200")
                .isEqualTo(200);

        final EmployeesResponse employeesResponse = mapper.readValue(response.asString(), EmployeesResponse.class);
        assertThat(employeesResponse)
                .extracting(EmployeesResponse::getStatus)
                .as("Response status should be 'success'")
                .isEqualTo("success");
        assertThat(employeesResponse.getMessage())
                .as("'Successfully! All records has been fetched")
                .contains("Successfully! All records has been fetched.");
        assertThat(employeesResponse.getData())
                .as("'Successfully! all records has been fetched")
                .isNotNull()
                .hasSizeGreaterThan(0);

    }

    @DataProvider(name = "test1")
    public static Object[][] employeeIds() {
        return new Object[][] {{2}, {5}};
    }

    @Test(dataProvider = "test1")
    public void verifyEmployeeById(final int employeeId) throws JsonProcessingException {
        final Response response = empService.getEmployeeById(employeeId);

        log.info("Response Status code: {}", response.statusCode());
        log.info("Response: \n{}", response.asPrettyString());
        assertThat(response.statusCode())
                .as("Employees api should respond with status code 200")
                .isEqualTo(200);

        final EmployeeResponse employeeResponse = mapper.readValue(response.asString(), EmployeeResponse.class);
        assertThat(employeeResponse)
                .extracting(EmployeeResponse::getStatus)
                .as("Response status should be 'success'")
                .isEqualTo("success");
        assertThat(employeeResponse.getMessage())
                .as("'Successfully! Record has been fetched.' message should be in response")
                .contains("Successfully! Record has been fetched.");
        assertThat(employeeResponse.getData().getId())
                .as("Employee id '%d' should be fetched")
                .isNotNull()
                .isEqualTo(employeeId);

    }
}
