package org.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class PassengerDetails extends BasePage{
    private static PassengerDetails instance;
    public PassengerDetails(WebDriver driver) {
        super(driver);
    }

    public static PassengerDetails getInstance(final WebDriver driver){
        if(instance == null){
            instance = new PassengerDetails(driver);
        }
        return instance;
    }

    public PassengerDetails enterAdultOneDetails(final Map<String, String> passengerDetails){
        final By adult1Title = By.cssSelector("#pax-a-title-1");
        final By adult1Firstname = By.cssSelector("#pax-a-first-1");
        final By adult1Lastname = By.cssSelector("#pax-a-last-1");
        final By adult1DOBDt = By.cssSelector("#pax-a-dobd-1");
        final By adult1DOBMonth = By.cssSelector("#pax-a-dobm-1");
        final By adult1DOBYear = By.cssSelector("#pax-a-doby-1");

        selectText(adult1Title, passengerDetails.get("title"));
        enterText(adult1Firstname, passengerDetails.get("firstname"));
        enterText(adult1Lastname, passengerDetails.get("lastname"));
        selectText(adult1DOBDt, passengerDetails.get("dob_dt"));
        selectText(adult1DOBMonth, passengerDetails.get("dob_month"));
        selectText(adult1DOBYear, passengerDetails.get("dob_yr"));

        return this;
    }

    public PassengerDetails enterAdultTwoDetails(final Map<String, String> passengerDetails){
        final By adult2Title = By.cssSelector("#pax-a-title-2");
        final By adult2Firstname = By.cssSelector("#pax-a-first-2");
        final By adult2Lastname = By.cssSelector("#pax-a-last-2");
        final By adult2DOBDt = By.cssSelector("#pax-a-dobd-2");
        final By adult2DOBMonth = By.cssSelector("#pax-a-dobm-2");
        final By adult2DOBYear = By.cssSelector("#pax-a-doby-2");

        selectText(adult2Title, passengerDetails.get("title"));
        enterText(adult2Firstname, passengerDetails.get("firstname"));
        enterText(adult2Lastname, passengerDetails.get("lastname"));
        selectText(adult2DOBDt, passengerDetails.get("dob_dt"));
        selectText(adult2DOBMonth, passengerDetails.get("dob_month"));
        selectText(adult2DOBYear, passengerDetails.get("dob_yr"));

        return this;
    }

    public PassengerDetails enterLeadContactDetails(final String name, final String phone, final Map<String, String> address){
        final By byName = By.cssSelector("#contact-name");
        final By byPhone = By.cssSelector("#contact-mobile");
        final By byEmailAddress = By.cssSelector("#contact-email");
        final By byAddressLine1 = By.cssSelector("#contact-address1");
        final By byAddressLine2 = By.cssSelector("#contact-address2");
        final By byCity = By.cssSelector("#contact-city");
        final By byPostcode = By.cssSelector("#contact-postcode");
        final By byCountry = By.cssSelector("#contact-country");

        enterText(byName, name);
        enterText(byPhone, phone);
        enterText(byEmailAddress, address.get("emailaddress"));
        enterText(byAddressLine1, address.get("addressline1"));
        enterText(byAddressLine2, address.get("addressline2"));
        enterText(byCity, address.get("city"));
        enterText(byPostcode, address.get("postcode"));
        selectText(byCountry, address.get("country"));

        return this;
    }

    public PassengerDetails enterHowDidYouHear(final String option){
        final By byHear = By.cssSelector("#contact-hearabout");
        selectText(byHear, option);
        return this;
    }

    public PassengerDetails contd(){
        final By bycontd = By.cssSelector("#paxform-select > button");
        final WebElement contd = getElement(bycontd);
        scrollToElement(contd);
        final String text = contd.getText();
        System.out.println("btn text:" + text);
        ((JavascriptExecutor)driver).executeScript("document.querySelector('#paxform-select > button').click()");
        return this;
    }
}
