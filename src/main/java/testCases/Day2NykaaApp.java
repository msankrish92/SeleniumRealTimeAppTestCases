package testCases;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Day2NykaaApp {

	/**
	 * 1) Go to https://www.nykaa.com/ 2) Mouseover on Brands and Mouseover on
	 * Popular 3) Click L'Oreal Paris 4) Go to the newly opened window and check the
	 * title contains L'Oreal Paris 5) Click sort By and select customer top rated
	 * 6) Click Category and click Shampoo 7) check whether the Filter is applied
	 * with Shampoo 8) Click on L'Oreal Paris Colour Protect Shampoo 9) GO to the
	 * new window and select size as 175ml 10) Print the MRP of the product 11)
	 * Click on ADD to BAG 12) Go to Shopping Bag 13) Print the Grand Total amount
	 * 14) Click Proceed 15) Click on Continue as Guest 16) Print the warning
	 * message (delay in shipment) 17) Close all windows
	 * 
	 * @param args
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException {
		// 1) Go to https://www.nykaa.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		RemoteWebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://www.nykaa.com/");
//		2) Mouseover on Brands and Mouseover on Popular
		Actions builder = new Actions(driver);

		builder.moveToElement(driver.findElementByXPath("//a[text()=\"brands\"]")).perform();

		builder.moveToElement(driver.findElementByXPath("//a[text()=\"Popular\"]")).perform();
//		3) Click L'Oreal Paris
		driver.findElementByXPath("(//li[@class=\"brand-logo menu-links\"]//img)[5]").click();

		Thread.sleep(5000);
//		4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windows = driver.getWindowHandles();

		List<String> orderedWindow = new ArrayList<String>(windows);

		driver.switchTo().window(orderedWindow.get(1));

		if (driver.getTitle().contains("L'Oreal Paris")) {
			System.out.println("Title Matched");
		}

		Thread.sleep(5000);
		try {
			driver.findElementByXPath("//button[text()=\"Maybe later\"]").click();
		} catch (Exception e) {
			System.out.println(e);
		}
		Thread.sleep(5000);

//		5) Click sort By and select customer top rated

		builder.moveToElement(driver.findElementByXPath("//span[text()=\"Sort By : \"]")).perform();

		driver.findElementByXPath("//span[@class=\"pull-left\"]").click();

		driver.findElementByXPath("//span[text()=\"customer top rated\"]").click();

		Thread.sleep(5000);
//		6) Click Category and click Shampoo

		driver.findElementByXPath("//div[text()=\"Category\"]").click();

		driver.findElementByXPath("//input[@id=\"chk_Shampoo_undefined\"]/following-sibling::label").click();

		Thread.sleep(3000);
//		7) check whether the Filter is applied with Shampoo
		String filterText = driver.findElementByXPath("//ul[@class=\"pull-left applied-filter-lists\"]/li").getText();

		if (filterText.contains("Shampoo")) {
			System.out.println("Filter Applied");
		}

//		8) Click on L'Oreal Paris Colour Protect Shampoo

		driver.findElementByXPath("//span[contains(text(),\"Paris Colour Protect Shampoo\")]").click();

//		GO to the new window and select size as 175ml

		Set<String> window2 = driver.getWindowHandles();

		List<String> orderedWindow2 = new ArrayList<String>(window2);

		driver.switchTo().window(orderedWindow2.get(2));

		Thread.sleep(5000);

		driver.findElementByXPath("//span[text()=\"175ml\"]").click();

//		10) Print the MRP of the product   

		String actualMrpText = driver.findElementByXPath("(//span[@class=\"post-card__content-price-offer\"])[1]")
				.getText();

		String trimmedMrpText = actualMrpText.replaceAll("[^0-9]", "");

		System.out.println("Mrp is " + trimmedMrpText);

//		11) Click on ADD to BAG

		driver.findElementByXPath("//div[@class=\"pull-left\"]//button").click();

//		12) Go to Shopping Bag 

		driver.findElementByXPath("//div[@class=\"AddBagIcon\"]").click();

		Thread.sleep(3000);
//		13) Print the Grand Total amount
		String grandTotal = driver.findElementByXPath("//div[@class=\"value medium-strong\"]").getText();

		String TrimmedGrandTotal = grandTotal.replaceAll("[^0-9]", "");

		System.out.println("Grand Total = " + TrimmedGrandTotal);
//		14) Click Proceed  

		driver.findElementByXPath("//span[text()=\"Proceed\"]").click();
//		15) Click on Continue as Guest
		driver.findElementByXPath("//button[contains(text(),\"CONTINUE AS GUEST\")]").click();

		String message = driver.findElementByXPath("//div[@class=\"message\"]").getText();
//		16) Print the warning message (delay in shipment) 
		System.out.println(message);
//		17) Close all windows
		driver.quit();
		
	}

}
