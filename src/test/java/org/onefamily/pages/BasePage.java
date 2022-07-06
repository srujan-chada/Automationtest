package org.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    protected void pause(final int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void selectText(final By byelement, final String textToSelect){
        final WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(byelement));
        final Select select = new Select(selectElement);
        select.selectByVisibleText(textToSelect);
    }

    protected void enterText(final By byelement, final String text){
        WebElement txtElement = wait.until(ExpectedConditions.visibilityOfElementLocated(byelement));
        txtElement.sendKeys(text);
    }

    protected void click(final By byelement){
        WebElement btnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(byelement));
        btnElement.click();
    }

    protected WebElement getElement(final By byelement){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(byelement));
    }

    protected void scrollToElement(final WebElement element){
        ((JavascriptExecutor)driver).executeScript("return arguments[0].scrollIntoView();", element);
    }
}
