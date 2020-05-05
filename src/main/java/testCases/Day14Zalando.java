package bigBasket;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day14Zalando {
	public static void main(String[] args) throws InterruptedException {

//		1) Go to https://www.zalando.com/
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://www.zalando.com/");
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;

//		2) Get the Alert text and print it
		wait.until(ExpectedConditions.alertIsPresent());
		String alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);
//		3) Close the Alert box and click on Zalando.uk
		driver.switchTo().alert().dismiss();
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();
//		4) Click Women--> Clothing and click Coat 
		driver.findElementByXPath("(//span[text()='Women'])[2]").click();
		builder.moveToElement(driver.findElementByXPath("//span[text()='Clothing']")).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Clothing']")));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Clothing']")));
		driver.findElementByXPath("//span[text()='Coats']").click();
//		5) Choose Material as cotton (100%) and Length as thigh-length
		driver.findElementByXPath("//span[text()='Material']").click();
		driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		js.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//button[text()='Save']").click();

//		6) Click on Q/S designed by MANTEL - Parka coat
		wait.until(ExpectedConditions.refreshed(
				ExpectedConditions.stalenessOf(driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']"))));
		driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']").click();

//		7) Check the availability for Color as Olive and Size as 'M'.
		driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
		js.executeScript("window.scrollBy(0,300)");
		driver.findElementById("picker-trigger").click();
		driver.findElementByXPath("//span[text()='M']").click();

//		8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
		try {
			String text = driver.findElementByXPath("//h2[text()='Out of stock']").getText();
			if(text.equals("Out of stock")) {
				driver.findElementByXPath("//h2[text()='Out of stock']/../../preceding-sibling::div//*[name()='svg' and @class='dx-icon']//*[local-name()='path']").click();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		try {
//		if (driver.findElementByXPath("//div[@id='z-pdp-topSection']/div/h2").getText().equals("Out of stock")) {
			driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
			driver.findElementByXPath("//span[text()='Choose your size']").click();
			js.executeScript("window.scrollBy(0,300)");
			driver.findElementByXPath("//span[text()='M']").click();
//		}
		}catch(Exception e) {
			
		}
//		9) Add to bag only if Standard Delivery is free
		try {
			boolean equals = driver.findElementByXPath("(//span[text()='Standard delivery']/following-sibling::div/div//span)[1]").getText().equals("Free");
			System.out.println(equals);
			if(driver.findElementByXPath("(//span[text()='Standard delivery']/following-sibling::div/div//span)[1]").getText().equals("Free")) {
				
				driver.findElementByXPath("//span[text()='Add to bag']").click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		10) Mouse over on Your Bag and Click on "Go to Bag"
		js.executeScript("window.scrollBy(0,-300)");
//		builder.moveToElement(driver.findElementByXPath("//a[@class='z-navicat-header_navToolItemLink']//*[name()='svg']")).perform();
//		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='Go to bag']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Go to bag']")));
		driver.findElementByXPath("//div[text()='Go to bag']").click();
//		11) Capture the Estimated Deliver Date and print
		String text = driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText();
		System.out.println(text);
//		12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
		Thread.sleep(1000);
		WebElement freedeli = driver.findElementByXPath("//a[contains(@href,'Do-delivery-and-returns')]");
		builder.moveToElement(freedeli).build().perform();
		System.out.println(driver.findElementByXPath("(//span[@class='z-navicat-header-uspBar_message-split_styled'])[2]").getAttribute("title"));
//		13) Click on FREE DELIVERY & RETURNS
		driver.findElementByXPath("//a[contains(@href,'Do-delivery-and-returns')]").click();
//		14) Click on Start chat in the Start chat and go to the new window
		
//		15) Enter you first name and a dummy email and click Start Chat
//		16) Type Hi, click Send and print thr reply message and close the chat window.
		

	}
}