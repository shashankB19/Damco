package src.damco.test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import src.damco.base.BaseTest;
import src.damco.page.MailHelper;
import src.damco.page.UtilityHelper;

public class TaskSendMailAndVerify extends BaseTest{
	
	WebDriver driver1,driver2;
	String urlTempMail="https://temp-mail.org/en/";
	String urlGmail="https://www.gmail.com";
	String subject="Test Subject";
	String body="Test Body";
	String userId="bisht_shashank";
	String password="Damco@12345";
	
	@Test
	public void testAirlineNameAndPriceHavingSecondndLowestPrice() throws Exception {
		
		driver1=BaseTest.initializeWebDriver();
		driver1.get(urlTempMail);

		MailHelper mailHelper=new MailHelper(driver1);
		String copieDMailId=mailHelper.copyMailId();
		
		driver2=BaseTest.initializeWebDriver();
		driver2.get("https://mail.yahoo.com/");
		MailHelper mailHelper2=new MailHelper(driver2);
		mailHelper2.yahooLogin(userId, password);
		mailHelper2.composeMail(copieDMailId, subject, body);
		driver2.quit();
		
	    mailHelper.verifyMail(subject, body);
		
				
	}
}
