package src.damco.page;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MakeMyTripHelperPage {
	WebDriver driver;
	
	public MakeMyTripHelperPage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	String fromcityLoc="//input[@id='fromCity']";
    String toCity="//input[@id='toCity']";
	
    String departureDateOnFrom="//div[@class='DayPicker-Months']//following::div[@class='DayPicker-Day DayPicker-Day--today']";
	String departureDateOnTo="//div[@class='DayPicker-Day DayPicker-Day--selected DayPicker-Day--today']";
   
	String serachButton="//a[text()='Search']";
	String sortByDeparture="//span[@class='pointer']/span[text()='Departure']";
	String paylaterPopUp="//p[text()='Now Lock Prices & Pay Later!	']";
	String okGotIt="//button[text()='OKAY, GOT IT!']";
	
	public void selectFromCity(String fromCity) throws InterruptedException {
		
		
		UtilityHelper.waitForElementToBeClickable(driver, fromcityLoc);
		UtilityHelper.mouseHoverClick(driver, fromcityLoc);
		driver.findElement(By.xpath(fromcityLoc)).click();
		
		UtilityHelper.waitForElementToBeClickable(driver, "//ul[@role='listbox']");
		driver.findElement(By.xpath("(//ul[@role='listbox']//li//following::p[contains(text(),'"+fromCity+"')])[1]")).click();
		
		UtilityHelper.waitForElementToBeClickable(driver, departureDateOnFrom);
		UtilityHelper.mouseHoverClick(driver, departureDateOnFrom);
		
		Thread.sleep(1000);
		
	}
	
	public void selectDestinationCity(String destCity) throws InterruptedException {
		
		UtilityHelper.waitForElementToBeClickable(driver, toCity);
		driver.findElement(By.xpath(toCity)).click();
		
		UtilityHelper.waitForElementToBeClickable(driver, "//ul[@role='listbox']");
		driver.findElement(By.xpath("(//ul[@role='listbox']//li//following::p[contains(text(),'"+destCity+"')])[1]")).click();
		
		UtilityHelper.waitForElementToBeClickable(driver, departureDateOnTo);
		UtilityHelper.mouseHoverClick(driver, departureDateOnTo);
		Thread.sleep(1000);
	}
	
	public void searchFlights() {
		
		UtilityHelper.waitForElementToBeClickable(driver, serachButton);
		driver.findElement(By.xpath(serachButton)).click();
		
		
	}
	public void sortByDeparture() throws InterruptedException {
		
		if (driver.findElement(By.xpath(paylaterPopUp)).isDisplayed()){
			driver.findElement(By.xpath(okGotIt)).click();
		}
		UtilityHelper.waitForElementToBeClickable(driver, sortByDeparture);
		driver.findElement(By.xpath(sortByDeparture)).click();
		Thread.sleep(1000);
		
	}
	
	public void searchFlightNameWithSecondSlowestPrice() {
		
		Set<Integer> setOfPrices=new HashSet<Integer>();
		List<WebElement> flightDetails=driver.findElements(By.xpath("//div[@class='listingCard']//div[@class='makeFlex simpleow']//following::div[@class='priceSection']//div//div//p"));
		for(int i=0;i<flightDetails.size();i++) {
			WebElement flightPrice=flightDetails.get(i);
			int price=Integer.parseInt((flightPrice.getText().split(" "))[1].replace(",", ""));
			setOfPrices.add(price);            	
		}
		List<Integer> listOfPrices = new ArrayList<Integer>(setOfPrices);
		Collections.sort(listOfPrices);
		int secondLowestPrice=listOfPrices.get(1);
		
		System.out.print("------------------------------------");
		System.out.println("Second Lowest Price : "+secondLowestPrice);
		
		
		String str = Integer.toString(secondLowestPrice);
		
		str = new StringBuilder(str).insert(str.length()-3, ",").toString();
		WebElement flightNameEle=driver.findElement(By.xpath("(//div[@class='priceSection']//div//div//p[contains(text(),'₹ "+str+"')]/../../../..//div//div//p)[1]"));
		System.out.print("------------------------------------");
		System.out.println("Flight name with second Lowest Price : "+flightNameEle.getText());
		System.out.print("------------------------------------");

	}

}
