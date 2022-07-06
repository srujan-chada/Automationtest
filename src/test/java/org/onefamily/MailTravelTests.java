package org.onefamily;

import com.typesafe.config.ConfigFactory;
import org.assertj.core.api.Assertions;
import org.onefamily.pages.Accomodation;
import org.onefamily.pages.HolidayPage;
import org.onefamily.pages.HomePage;
import org.onefamily.pages.PassengerDetails;
import org.onefamily.utils.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.Map;

public class MailTravelTests extends BaseTest{

    private WebDriver driver;
    private HomePage homepage;
    private HolidayPage holidayPage;
    private Accomodation accomodation;
    private PassengerDetails passengerDetails;

    @BeforeTest
    @Parameters({"browser"})
    public void init(final String browser){
        driver = BrowserUtils.startBrowser(browser);
        config = ConfigFactory.load("testConfig.properties");
        homepage = HomePage.getInstance(driver);
        holidayPage = HolidayPage.getInstance(driver);
        accomodation = Accomodation.getInstance(driver);
        passengerDetails = PassengerDetails.getInstance(driver);
    }

    @AfterTest
    public void afterTest(){
       BrowserUtils.close();
    }

    @DataProvider(name="holidayData")
    public Object[][] holidayTestData(){
        return new Object[][]{{"India", "11 Days", "£1,499", "11", "1"}};
    }

    @Test(dataProvider = "holidayData")
    public void verifyHolidayBooking(String country, String holidayText, String price, String holidays, String noOfDoubleRooms) throws InterruptedException {

        final String phonePattern = "[0-9\\s]";
        homepage.open(config.getString("home.page"));
        homepage.acceptCookies();
        homepage.searchHoliday(country);
        homepage.gotoMoreInfoOfHolidayByText(holidayText);

        WebElement pricePinContainer = holidayPage.getPriceDetails();//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#price-pin_pin-container")));
        Assertions.assertThat(pricePinContainer.findElement(By.cssSelector("#price-pin_pin-container span#price-pin_riviera-days-num")).getText())
                .contains(holidays);

        Assertions.assertThat(pricePinContainer.findElement(By.cssSelector("#price-pin_pin-container span.ibecurr")).getText())
                .contains(price);

        Assertions.assertThat(driver.findElement(By.cssSelector("#supplier-phone-cont #supplier-phone")).getText())
                        .containsPattern(phonePattern);

        holidayPage.bookOnline();

        //Click Continue
        holidayPage.continueBooking();
        accomodation.selectDoubleRoom(noOfDoubleRooms);
        accomodation.selectRoomsAndContinue();

        final Map<String, String> adultOneDetails = Map.of("title", "Mr",
                "firstname", "Srujan",
                "lastname", "Chada",
                "dob_dt", "23",
                "dob_month", "April",
                "dob_yr", "1981");
        final Map<String, String> adultTwoDetails = Map.of("title", "Mrs",
                "firstname", "Firstname2",
                "lastname", "Lastname2",
                "dob_dt", "25",
                "dob_month", "September",
                "dob_yr", "1985");

        final Map<String, String> address = Map.of("emailaddress", "schada@xxx.com",
                "addressline1", "line1",
                "addressline2", "line2",
                "city", "Feltham",
                "postcode", "Fl423Y",
                "country", "United Kingdom");
         passengerDetails
                .enterAdultOneDetails(adultOneDetails)
                .enterAdultTwoDetails(adultTwoDetails)
                 .enterLeadContactDetails("Srujan Chada", "1234 343 33", address)
                 .enterHowDidYouHear("Email")
                 .contd();


        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.nbf_button.nbf_tpl_pms_book_button"))).click();
        Thread.sleep(3000);

    }
}
