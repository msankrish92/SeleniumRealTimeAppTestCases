package dailytestcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day4HP {

	public static void main(String[] args) throws InterruptedException {
//		1) Go to https://store.hp.com/in-en/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.get("https://store.hp.com/in-en/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

//		2) Mouse over on Laptops menu and click on Pavilion
		try {
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.textToBe(By.xpath("//button[@class=\"optanon-allow-all accept-cookies-button\"]"), "Accept Cookies"));
			Thread.sleep(500);
			System.out.println(driver.findElementByXPath("//button[@class=\"optanon-allow-all accept-cookies-button\"]").getText());
			driver.findElementByXPath("//button[@class=\"optanon-allow-all accept-cookies-button\"]").click();
		}catch(Exception e) {
			System.out.println("No Cookie window");
		}
		try {
		
		Thread.sleep(500);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ifr_popup"));
		wait.until(ExpectedConditions.textToBe(By.xpath("//h2[@class=\"marketing_header\"]"), "SIGN UP NOW!"));
		driver.findElementByXPath("//h2[@class=\"marketing_header\"]").click();
		}catch(Exception e) {
			System.out.println("No Sign Up window");
		}
		
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElementByXPath("(//span[text()=\"Laptops\"])[1]")).perform();
		driver.findElementByXPath("(//span[text()=\"Pavilion\"])[1]").click();
//		Thread.sleep(3000);
//		driver.findElementByXPath("//div[@class=\"optanon-alert-box-corner-close\"]/button").click();
////		3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
//		Ex
//		driver.switchTo().frame("inside-bridge");
//		driver.switchTo().defaultContent();
//		
//		driver.findElementByLinkText("Intel Core i7").click();
		
//		4) Hard Drive Capacity -->More than 1TB
//		5) Select Sort By: Price: Low to High
//		6) Print the First resulting Product Name and Price
//		7) Click on Add to Cart
//		8) Click on Shopping Cart icon --> Click on View and Edit Cart
//		9) Check the Shipping Option --> Check availability at Pincode
//		10) Verify the order Total against the product price
//		11) Proceed to Checkout if Order Total and Product Price matches
//		12) Click on Place Order
//		13) Capture the Error message and Print
//		14) Close Browser
	}

}
