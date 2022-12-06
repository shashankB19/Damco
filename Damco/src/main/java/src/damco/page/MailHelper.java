package src.damco.page;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MailHelper {

    WebDriver driver;
	
	public MailHelper(WebDriver driver) {
		this.driver=driver;
		
	}
	
	String copyMailButton="(//button[@type='button'][@data-clipboard-action='copy'])[2]";
	String gmailEmail="//input[@type='email']";
	
	
	
	public String copyMailId() throws UnsupportedFlavorException, IOException {
		
		UtilityHelper.waitForElementToBeClickable(driver, copyMailButton);
		driver.findElement(By.xpath(copyMailButton)).click();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String actualCopedText = (String) clipboard.getData(DataFlavor.stringFlavor);
        System.out.println("String from Clipboard:" + actualCopedText);
        return actualCopedText;
	}
	
	public void yahooLogin(String mail,String password) {
		
		if (driver.findElement(By.xpath("//a[@alt='Sign in']")).isDisplayed()) {
			driver.findElement(By.xpath("//a[@alt='Sign in']")).click();
		}
		
		driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys(mail);
		driver.findElement(By.xpath("//input[@id='login-signin']")).click();
		driver.findElement(By.xpath("//input[@id='login-passwd']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='login-signin']")).click();
		
		
	}
	
	public void composeMail(String sendTo, String sub, String body) {
		
		driver.findElement(By.xpath("//a[@data-test-id='compose-button']")).click();
		driver.findElement(By.xpath("(//div[@data-test-id='compose-header-field-to']//input)[1]")).sendKeys(sendTo);
		driver.findElement(By.xpath("//input[@data-test-id='compose-subject']")).sendKeys(sub);
		driver.findElement(By.xpath("//div[@aria-label='Message body']//div")).sendKeys(body);
        driver.findElement(By.xpath("(//div[@data-test-id='compose-toolbar']//button)[1]")).click();
        
		
		
	} 
	
	public void verifyMail(String subject,String mailBody) throws Exception {
		
		UtilityHelper.takeScreenShot(driver, "./screenshots/beforeValidationScrenshotOfEmail.png");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)", "");

		try {
		driver.findElement(By.xpath("(//a[contains(@href,'https://temp-mail.org/en/view')])[1]")).click();
		}catch(Exception e){
			UtilityHelper.takeScreenShot(driver, "./screenshots/AfterValidationScrenshotOfEmail.png");

		}
		UtilityHelper.takeScreenShot(driver, "./screenshots/AfterValidationScrenshotOfEmail.png");
		
		String sub=driver.findElement(By.xpath("//div[@class='user-data-subject']//h4")).getText();
		String body=driver.findElement(By.xpath("//div[@class='inbox-data-content-intro']//div")).getText();
		
		
		Assert.assertEquals(subject, sub, "subject not matched");
		Assert.assertEquals(mailBody, body, "body not matched");
		
	}
}
