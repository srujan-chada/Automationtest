package org.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Accomodation extends BasePage{

    private static Accomodation instance;

    private final By byNoRequired = By.cssSelector("#room-1673581-numselect");
    private final By bySelectRoomsAndContinue = By.cssSelector(".nbf_fancy_nbf_tpl_pms_book_room");

    private Accomodation(WebDriver driver) {
        super(driver);
    }

    public static Accomodation getInstance(WebDriver driver){
        if(instance == null){
            instance = new Accomodation(driver);
        }
        return instance;
    }

    public void selectDoubleRoom(final String noRequired){
        final Select doubleRoom = new Select(wait.until(ExpectedConditions.elementToBeClickable(byNoRequired)));
        doubleRoom.selectByVisibleText(noRequired);
    }

    public void selectRoomsAndContinue(){
        final WebElement continueAfterBookingRoom = wait.until(ExpectedConditions.elementToBeClickable(bySelectRoomsAndContinue));
        continueAfterBookingRoom.click();
    }
}
