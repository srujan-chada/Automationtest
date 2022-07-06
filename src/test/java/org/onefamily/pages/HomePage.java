package org.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class HomePage extends BasePage {

    private static HomePage instance;

    private HomePage(WebDriver driver){
        super(driver);
    }

    public static HomePage getInstance(final WebDriver driver){
        if(instance == null){
            instance = new HomePage(driver);
        }
        return instance;
    }

    public void open(final String url){
        driver.get(url);
    }

    public void acceptCookies(){
        WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#onetrust-accept-btn-handler")));
        pause(2000);
        acceptCookies.click();
    }

    public void searchHoliday(final String holiday){
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#searchtext_freetext_search_form")));
        searchBox.sendKeys(holiday);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void gotoMoreInfoOfHolidayByText(final String holiday){
        List<WebElement> packages = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#productlist_grid > div")));
        final WebElement desiredHoliday = packages.stream().filter( p -> {
                    final String holidayname = p.findElement(By.cssSelector("span.product-text")).getText();
                    System.out.println("hol : " + holidayname);
                    return holidayname.contains(holiday);
                })
                .findFirst()
                .orElse(null);

        final WebElement moreInfo = desiredHoliday.findElement(By.cssSelector("button.button-main"));
        moreInfo.click();
    }
}
