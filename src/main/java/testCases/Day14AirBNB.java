package testCases;

import java.util.ArrayList;

import java.util.List;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Day14AirBNB {
	public static void main(String[] args) throws InterruptedException {
		// 1) Go to https://www.airbnb.co.in/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.airbnb.co.in/");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions builder = new Actions(driver);
		// 2) Type Coorg in location and Select Coorg, Karnataka

		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[text()='OK']")));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='OK']")));
		try {
			for (int i = 0; i < 2; i++) {
				driver.findElementByXPath("//button[text()='OK']").click();
			}

		} catch (Exception e) {

		}

		driver.findElementByXPath("//div[text()='Location']").click();

		driver.findElementByXPath("//input[@id='bigsearch-query-attached-query']").sendKeys("Coorg");
		driver.findElementByXPath("//div[text()='Coorg, Karnataka']").click();

		// 3) Select the Start Date as June 1st and End Date as June 5th
		driver.findElementByXPath("(//div[text()='1'])[3]").click();
		driver.findElementByXPath("(//div[text()='5'])[3]").click();

		// 4) Select guests as 6 adults, 3 child and Click Search
		driver.findElementByXPath("//div[text()='Guests']").click();
		for (int i = 0; i < 6; i++) {
			driver.findElementByXPath("(//button[@type='button']//*[name()='svg' and @focusable='false'])[2]").click();
		}

		for (int i = 0; i < 3; i++) {
			driver.findElementByXPath("(//button[@type='button']//*[name()='svg' and @focusable='false'])[4]").click();
		}
		driver.findElementByXPath("//button[@type='submit']").click();

		// 5) Click Cancellation flexibility and enable the filter and Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FMP-target']")));
		driver.findElementByXPath("//span[text()='Cancellation flexibility']").click();
		driver.findElementByXPath("//button[@id='filterItem-switch-flexible_cancellation-true']").click();

		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();

		// 6) Select Type of Place as Entire Place and Save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FMP-target']")));
		driver.findElementByXPath("//span[text()='Type of place']").click();
		driver.findElementByXPath("//input[@name='Entire place']//following-sibling::span").click();
		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();

		// 7) Set Min price as 3000 and max price as 5000
		Thread.sleep(5000);

		driver.findElementByXPath("//span[text()='Price']").click();
		driver.findElementByXPath("//input[@id='price_filter_min']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='FMP-target']")));

		for (int i = 0; i < 5; i++) {
			driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys(Keys.BACK_SPACE);

		}
		driver.findElementByXPath("//input[@id='price_filter_min']").sendKeys("3000");
		for (int i = 0; i < 10; i++) {
			driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys(Keys.BACK_SPACE);

		}
		driver.findElementByXPath("//input[@id='price_filter_max']").sendKeys("5000");
		driver.findElementByXPath("//button[@id='filter-panel-save-button']").click();
		// 8) Click More Filters and set 3 Bedrooms and 3 Bathrooms

		driver.findElementByXPath("//span[text()='More filters']").click();
		Thread.sleep(5000);
		for (int i = 0; i < 3; i++) {

			driver.findElementByXPath(
					"(//div[@id='filterItem-stepper-min_bedrooms-0']//button[@type='button']//*[name()='svg' and @role='presentation'])[2]")
					.click();
		}
		for (int i = 0; i < 3; i++) {
			driver.findElementByXPath(
					"(//div[@id='filterItem-stepper-min_bathrooms-0']//button[@type='button']//*[name()='svg' and @role='presentation'])[2]")
					.click();
		}

		// 9) Check the Amenities with Kitchen, Facilities with Free parking on
		// premisses, Property as House and Host Language as English and click on Stays
		// only when stays available

		driver.findElementByXPath("//input[@id='filterItem-checkbox-amenities-8']/following-sibling::span").click();
		driver.findElementByXPath("//input[@id='filterItem-checkbox-amenities-9']/following-sibling::span").click();

		driver.findElementByXPath("//input[@id='filterItem-checkbox-languages-1']/following-sibling::span").click();

		Thread.sleep(2000);
		driver.findElementByXPath("//button[contains(text(),\"Show\")]").click();

		// 10) Click Prahari Nivas, the complete house
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@itemprop='itemListElement']").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> listWindow = new ArrayList<String>(windowHandles);
		driver.switchTo().window(listWindow.get(1));

		// 11) Click on "Show all * amenities"
		Thread.sleep(5000);
		try {
			driver.findElementByXPath("//a[contains(text(),'Show all')]").click();

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			driver.findElementByXPath("//button[contains(text(),'Show all')]").click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// 12) Print all the Not included amenities
		Thread.sleep(5000);
		try {
			builder.moveToElement(driver.findElementByXPath("//div[text()='Not included']")).perform();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			builder.moveToElement(driver.findElementByXPath("//h4[text()='Not included']")).perform();
		} catch (Exception e) {
			// TODO: handle exception
		}

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		List<WebElement> notIncludedamenities = driver.findElementsByXPath("//span[contains(text(),\"Unavailable\")]");
		for (int i = 0; i < notIncludedamenities.size(); i++) {
			System.out.println(notIncludedamenities.get(i).getText());
		}
		driver.findElementByXPath("//div[@aria-label='Amenities']//div//button").click();

		// 13) Verify the Check-in date, Check-out date and Guests
		String checkIn;
		String checkOut;
		try {
			checkIn = driver.findElementById("checkin").getAttribute("value");
			Assert.assertEquals(checkIn, "2020-06-01");
			checkOut = driver.findElementById("checkout").getAttribute("value");
			Assert.assertEquals(checkOut, "2020-06-05");
			System.out.println("Check in and checkout date verified");

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			checkIn = driver.findElementByXPath("(//div[text()='Check-in']/following-sibling::div)[1]").getText();
			checkOut = driver.findElementByXPath("(//div[text()='Checkout']/following-sibling::div)[1]").getText();
			Assert.assertEquals(checkIn, "06/01/2020");
			Assert.assertEquals(checkOut, "06/05/2020");
			System.out.println("Check in and checkout date verified");
		} catch (Exception e) {
			// TODO: handle exception
		}

		// 14) Read all the Sleeping arrangements and Print
		List<WebElement> bedRooms = driver.findElementsByXPath("//div[contains(text(),'Bedroom')]");
		List<WebElement> bedType = driver
				.findElementsByXPath("//div[contains(text(),'Bedroom')]/following-sibling::div");

		for (int i = 0; i < 3; i++) {
			System.out.println(bedRooms.get(i).getText() + " --> " + bedType.get(i).getText());
		}

		for (int i = 0; i < bedRooms.size() - 3; i++) {
			Thread.sleep(2000);
			driver.findElementByXPath("//button[@type='button']//*[name()='svg' and @aria-label='Next']").click();

			bedRooms.set(3 + i, driver.findElementByXPath("(//div[contains(text(),'Bedroom')])[" + (4 + i) + "]"));
			Thread.sleep(5000);
		}

		for (int i = 3; i < bedRooms.size(); i++) {
			System.out.println(bedRooms.get(i).getText() + " --> " + bedType.get(i).getText());
		}
		driver.quit();
	}
}

