package org.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HolidayPage extends BasePage{

    private static HolidayPage instance;

    private HolidayPage(WebDriver driver){
        super(driver);
    }

    public static HolidayPage getInstance(final WebDriver driver){
        if(instance == null){
            instance = new HolidayPage(driver);
        }
        return instance;
    }

    public WebElement getPriceDetails(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#price-pin_pin-container")));
    }

    public void bookOnline(){
        final WebElement bookOnline = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='enterbookingflow']")));
        bookOnline.click();
    }

    public void continueBooking(){
        final WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.nbf_fancy_nbf_tpl_pms_continue.nbf_fg_pms_button_text")));
        cont.click();
    }
}
