<<<<<<< HEAD

package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Day4HP {

	public static void main(String[] args) throws InterruptedException {
//		1) Go to https://store.hp.com/in-en/
		ChromeOptions option = new ChromeOptions();

		option.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

//		2) Mouse over on Laptops menu and click on Pavilion
		try {
			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.textToBe(
					By.xpath("//button[@class=\"optanon-allow-all accept-cookies-button\"]"), "Accept Cookies"));
			Thread.sleep(500);
			System.out.println(driver.findElementByXPath("//button[@class=\"optanon-allow-all accept-cookies-button\"]")
					.getText());
			driver.findElementByXPath("//button[@class=\"optanon-allow-all accept-cookies-button\"]").click();
		} catch (Exception e) {
			System.out.println("No Cookie window");
		}
		try {

			Thread.sleep(500);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("ifr_popup"));
			wait.until(ExpectedConditions.textToBe(By.xpath("//h2[@class=\"marketing_header\"]"), "SIGN UP NOW!"));
			System.out.println(driver.findElementByXPath("//h2[@class=\"marketing_header\"]").getText());
			System.out.println("Frame Available");
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			driver.findElementByXPath("//span[@class=\"optly-modal-close close-icon\"]").click();
		} catch (Exception e) {
			System.out.println("No Sign Up window");
		}

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElementByXPath("(//span[text()=\"Laptops\"])[1]")).perform();
		driver.findElementByXPath("(//span[text()=\"Pavilion\"])[1]").click();

//		3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@class=\"inside_closeButton fonticon icon-hclose\"]")));
			driver.findElementByXPath("//div[@class=\"inside_closeButton fonticon icon-hclose\"]").click();
		} catch (Exception e) {
			System.out.println("Element not present");
		}
		Thread.sleep(3000);
		driver.findElementByXPath("(//span[text()=\"Processor\"])[2]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()=\"Intel Core i7\"]/preceding-sibling::input").click();

//		4) Hard Drive Capacity -->More than 1TB

		Thread.sleep(3000);
		driver.findElementByXPath("//span[text()=\"More than 1 TB\"]/preceding-sibling::input").click();

//		5) Select Sort By: Price: Low to High
		Thread.sleep(3000);
		Select sort = new Select(driver.findElementById("sorter"));
		sort.selectByValue("price_asc");
//		6) Print the First resulting Product Name and Price
		Thread.sleep(5000);
		String productName = driver.findElementByXPath("//strong[@class=\"product name product-item-name\"]/a")
				.getText();
		String productPrice = driver.findElementByXPath("//span[@id=\"product-price-9580\"]/span").getText();
		String trimProductPrice = productPrice.replaceAll("[^0-9]", "");
		System.out.println(productName + " " + trimProductPrice);
//		7) Click on Add to Cart

		driver.findElementByXPath("//span[text()=\"Add To Cart\"]").click();

//		8) Click on Shopping Cart icon --> Click on View and Edit Cart

		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class=\"minicart-wrapper\"]/a").click();
		driver.findElementByXPath("//span[text()=\"View and edit cart\"]/..").click();
//		9) Check the Shipping Option --> Check availability at Pincode
		driver.findElementByXPath("//input[@name=\"pincode\"]").sendKeys("600100");
		driver.findElementByXPath("//button[text()=\"check\"]").click();
//		10) Verify the order Total against the product price
		String orderValue = driver.findElementByXPath("//tr[@class=\"grand totals\"]/td/strong/span").getText();
		String trimOrderValue = orderValue.replaceAll("[^0-9]", "");
		System.out.println(trimOrderValue);
		Assert.assertEquals(trimProductPrice, trimOrderValue);
//		11) Proceed to Checkout if Order Total and Product Price matches
		Thread.sleep(2000);
		driver.findElementById("sendIsCAC").click();
//		12) Click on Place Order
		driver.findElementByXPath("(//span[text()=\"Place Order\"])[4]").click();
//		13) Capture the Error message and Print
		System.out.println(driver.findElementByXPath("//div[@class=\"message notice\"]/span").getText());
//		14) Close Browser
		driver.close();
	}

}
=======
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
>>>>>>> 6133311ed7624b24d6173b2d3ed48147be55cd28
