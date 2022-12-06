package src.damco.page;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityHelper {

	public static void waitForElementVisibility(WebDriver driver,String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}
	
	public static void waitForElementToBeClickable(WebDriver driver,String xpath) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}
	
	public static void mouseHoverClick(WebDriver driver, String xpath) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath(xpath)));
		actions.click().build().perform();
		
	}
	
	public static void sendKeysUsingJs(WebDriver driver,String xpath,String input) {
		JavascriptExecutor jse = ((JavascriptExecutor)driver);        	
		WebElement ele = driver.findElement(By.xpath(xpath));
		jse.executeScript("arguments[0].value='"+input+"';", ele);
	}
	
	public static void takeScreenShot(WebDriver driver,String fileWithPath) throws Exception{
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
		}
		
}
