package src.damco.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import src.damco.base.BaseTest;
import src.damco.page.MakeMyTripHelperPage;

public class TaskMakeMyTrip extends BaseTest {
	WebDriver driver;
	String url="https://www.makemytrip.com/flights/";
	
	@Test
	public void testAirlineNameAndPriceHavingSecondndLowestPrice() throws InterruptedException {
		
		driver=BaseTest.initializeWebDriver();
		driver.get(url);
		
		MakeMyTripHelperPage mmtHelper=new MakeMyTripHelperPage(driver);
		mmtHelper.selectFromCity("Delhi");
		mmtHelper.selectDestinationCity("Mumbai");
		mmtHelper.searchFlights();
		mmtHelper.sortByDeparture();
		mmtHelper.searchFlightNameWithSecondSlowestPrice();
		
		
	}

}
