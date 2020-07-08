
package testCases;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

		wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.elementToBeClickable(By.xpath("//input[@id='discount-40%20-%2050']/following-sibling::label/a"))));
//			Thread.sleep(2000);
		builder.moveToElement(
				driver.findElementByXPath("//input[@id='discount-40%20-%2050']/following-sibling::label/a")).click()
				.build().perform();
		
		if(driver.findElementById("discount-40%20-%2050").isSelected()) {
			System.out.println("selected");
		}else {
			builder.moveToElement(
					driver.findElementByXPath("//input[@id='discount-40%20-%2050']/following-sibling::label/a")).click()
					.build().perform();
			
			System.out.println("not selected");
		}

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
		wait.until(ExpectedConditions.refreshed(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='product-image wooble']"))));
		builder.moveToElement(driver.findElementByXPath("//img[@class='product-image wooble']")).perform();
		wait.until(ExpectedConditions.refreshed(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Quick View')]"))));
		builder.moveToElement(driver.findElementByXPath("//div[contains(text(),'Quick View')]")).click().perform();

//		8) Click on View Details
//		Thread.sleep(5000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'view details')]")));
		driver.findElementByXPath("//a[contains(text(),'view details')]").click();

		Thread.sleep(2000);
//		driver.findElement(By.xpath("//a[contains(text(),'view details')]"))
		if (driver.findElements(By.xpath("//a[contains(text(),'view details')]")).size()!=0) {
			driver.findElementByXPath("//a[contains(text(),'view details')]").click();
			System.out.println("element present");
		}else {
			System.out.println("element not present");
		}

//		9) Capture the Price of the Product and Delivery Charge
//		String text = driver.findElementByXPath("//span[@itemprop='price']").getText();
//		System.out.println(text);
		int firstProd = Integer
				.parseInt(driver.findElementByXPath("//span[@itemprop='price']").getText());
		System.out.println(firstProd);

//		10) Validate the You Pay amount matches the sum of (price+deliver charge)
		driver.findElementById("add-cart-button-id").click();

//		11) Search for Sanitizer
		wait.until(ExpectedConditions
				.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("inputValEnter"))));
		driver.findElementById("inputValEnter").sendKeys("Sanitizer");
		driver.findElementByXPath("(//a[@type='keyword'])[2]").click();

//		12) Click on Product "BioAyurveda Neem Power Hand Sanitizer"
		wait.until(ExpectedConditions.refreshed(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@class,'product-image')]"))));
		driver.findElementByXPath("//img[contains(@class,'product-image')]").click();
//		13) Capture the Price and Delivery Charge
		Set<String> windows = driver.getWindowHandles();
		List<String> orderedWindow = new LinkedList<String>(windows);
		driver.switchTo().window(orderedWindow.get(1));
		int secProd = Integer.parseInt(driver.findElementByXPath("//span[@itemprop='price']").getText());
		System.out.println(secProd);
//		14) Click on Add to Cart
		driver.findElementById("add-cart-button-id").click();

//		15) Click on Cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Cart']")));
		driver.findElementByXPath("//span[@class='cartTextSpan']").click();
		int total = firstProd +  secProd;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='rfloat']")));
		int cartTotal = Integer.parseInt(driver.findElementByXPath("//span[@class='rfloat']").getText().replaceAll("[^0-9]", ""));
		System.out.println(total);
		System.out.println(cartTotal);
//		16) Validate the Proceed to Pay matches the total amount of both the products
		Assert.assertEquals(total, cartTotal);
//		17) Close all the windows
		driver.findElementByXPath("//form[@id='checkout-continue']").click();
		driver.close();
		
	}
}