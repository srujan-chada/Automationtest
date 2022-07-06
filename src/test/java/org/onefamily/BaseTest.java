package org.onefamily;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTest {

    protected  Config config;
    protected  ObjectMapper mapper;

}
