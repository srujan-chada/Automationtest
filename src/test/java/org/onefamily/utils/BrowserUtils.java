package org.onefamily.utils;

import com.typesafe.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtils {

    private static WebDriver driver;

    public static WebDriver startBrowser(final String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            driver = WebDriverManager.chromedriver().create();
        }else if(browserName.equalsIgnoreCase("firefox")){
            driver = WebDriverManager.firefoxdriver().create();
        }
        driver.manage().window().maximize();

        return driver;
    }

    public static void close(){
        driver.quit();
    }
}
