
package testCases;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day11SnapDeal {

	public static void main(String[] args) throws InterruptedException {

//	1) Go to https://www.snapdeal.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

//		‎2) Mouse over on Toys, Kids' Fashion & more and click on Toys
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//span[contains(text(),\"Kids' Fashion & more\")]")).perform();

//		3) Click Educational Toys in Toys & Games
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElementByXPath("//span[contains(text(),\"Educational Toys\")]")));

		driver.findElementByXPath("//span[contains(text(),\"Educational Toys\")]").click();

//		‎4) Click the Customer Rating 4 star and Up 
//		Thread.sleep(3000);
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[@for=\"avgRating-4.0\"]")));
		driver.findElementByXPath("//label[@for=\"avgRating-4.0\"]").click();

//		5) Click the offer as 40-50

//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//label[@for='discount-40 - 50']")));
		Actions Builder = new Actions(driver);
		try {

			wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(
					By.xpath("//input[@id='discount-40%20-%2050']/following-sibling::label/a"))));
			Thread.sleep(2000);
			builder.moveToElement(
					driver.findElementByXPath("//input[@id='discount-40%20-%2050']/following-sibling::label/a")).click()
					.build().perform();
			System.out.println("succ1");
		} catch (Exception e) {
			System.out.println("Err1");
		}
		Thread.sleep(5000);
		System.out.println(driver.findElementByXPath("//input[@id='discount-40%20-%2050']/following-sibling::label/a").isSelected());
//		try {
//			builder.moveToElement(driver.findElementByXPath("//label[@for='discount-40 - 50']/a")).click().build()
//					.perform();
//			System.out.println("succ2");
//		} catch (Exception e) {
//			System.out.println("Err2");
//		}

////		6) Check the availability for the pincode
		driver.findElementByXPath("(//input[@class=\"sd-input\"])[2]").sendKeys("600100");
		driver.findElementByXPath("//button[text()='Check']").click();
		
////		7) Click the Quick View of the first product 
//		Thread.sleep(5000);
//		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//img[@class='product-image wooble']"))));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='product-image wooble']"))));
		builder.moveToElement(driver.findElementByXPath("//img[@class='product-image wooble']")).perform();
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Quick View')]"))));
		builder.moveToElement(driver.findElementByXPath("//div[contains(text(),'Quick View')]")).click().perform();
		
//		8) Click on View Details
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'view details')]")));
		driver.findElementByXPath("//a[contains(text(),'view details')]").click();
		
//		9) Capture the Price of the Product and Delivery Charge
		String text = driver.findElementByXPath("//span[@itemprop='price']").getText();
		System.out.println(text);
		String text2 = driver.findElementByXPath("(//span[@class='availCharges']/span)[3]").getText();
		System.out.println(text2);
		
//		10) Validate the You Pay amount matches the sum of (price+deliver charge)
		
		
//		11) Search for Sanitizer
		driver.findElementById("inputValEnter").sendKeys("Sanitizer");
		driver.findElementByXPath("(//a[@type='keyword'])[2]").click();
		
//		12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
//		13) Capture the Price and Delivery Charge
//		14) Click on Add to Cart
//		15) Click on Cart 
//		16) Validate the Proceed to Pay matches the total amount of both the products
//		17) Close all the windows
	}
}