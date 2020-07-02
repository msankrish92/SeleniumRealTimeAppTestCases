package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Assert;

public class Day6BigBasket {

	public static void main(String[] args) throws InterruptedException {

// 1) Go to https://www.bigbasket.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.get("https://www.bigbasket.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElementByClassName("arrow-marker").click();
		driver.findElementByName("skipandexplore").click();

//		2) mouse over on  Shop by Category 
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//li[@class=\"dropdown full-wid hvr-drop\"]/a")).perform();

//		3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS 
//		Thread.sleep(3000);
		builder.moveToElement(driver.findElementByXPath("(//a[contains(text(),\"Foodgrains, Oil & Masala\")])[2]"))
				.perform();
		builder.moveToElement(driver.findElementByXPath("(//a[contains(text(),\"Rice & Rice Products\")])[2]"))
				.perform();

//		4) Click on Boiled & Steam Rice
		driver.findElementByXPath("(//a[contains(text(),\"Boiled & Steam Rice\")])[2]").click();

//		5) Choose the Brand as bb Royal
		driver.findElementByXPath("(//i[@class=\"cr-icon fa fa-check\"])[3]").click();

//		6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		Thread.sleep(5000);
		driver.findElementByXPath("(//button[@class=\"btn btn-default dropdown-toggle form-control\"]/i)[3]").click();
		driver.findElementByXPath(
				"//a[text()=\"Ponni Boiled Rice - Super Premium\"]/following::div/div/span/ul/li/a/span").click();

//		7) print the price of Rice
		String ricePrice = driver.findElementByXPath("(//span[@class=\"discnt-price\"]/span)[3]").getText();
		System.out.println("Price of the Rice is: " + ricePrice);
		int intRicePrice = Integer.parseInt(ricePrice);

//		8) Click Add button
		driver.findElementByXPath("(//button[text()=\"Add \"])[3]").click();

//		9) Verify the success message displayed
		System.out.println(driver.findElementByClassName("toast-title").getText());

//		10) Type Dal in Search field and enter
		driver.findElementById("input").sendKeys("dhal", Keys.ENTER);

//		12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		driver.findElementByXPath("//a[text()=\"Organic - Toor Dal / Thuvaram Paruppu\"]/following::div/span/button")
				.click();
		driver.findElementByXPath(
				"//a[text()=\"Organic - Toor Dal / Thuvaram Paruppu\"]/following::div/span/button/following-sibling::ul/li/a")
				.click();
		driver.findElementByXPath(
				"//a[text()=\"Organic - Toor Dal / Thuvaram Paruppu\"]/following::div[3]//div[3]/div[2]/div[1]/div/input")
				.clear();
		driver.findElementByXPath(
				"//a[text()=\"Organic - Toor Dal / Thuvaram Paruppu\"]/following::div[3]//div[3]/div[2]/div[1]/div/input")
				.sendKeys("2");

//		13) Print the price of Dal
		String dalPrice = driver.findElementByXPath("(//span[@class=\"discnt-price\"])[4]").getText();
		System.out.println("Price of Dhal is " + dalPrice);
		int intDalPrice = Integer.parseInt(dalPrice.replaceAll("[^0-9]", ""));

//		14) Click Add button
		driver.findElementByXPath(
				"//a[text()=\"Organic - Toor Dal / Thuvaram Paruppu\"]/following::div[3]//div[3]/div[2]/div[2]/button")
				.click();

//		15) Mouse hover on My Basket 
		builder.moveToElement(driver.findElementByXPath("//span[@class=\"basket-content\"]")).perform();

//		16) Validate the Sub Total displayed for the selected items
		String subTotal = driver.findElementByXPath("//div[@class=\"row sub-cost ng-scope\"]/p/span/span").getText();
		int Total = intRicePrice + (intDalPrice * 2);
		System.out.println("Sub total is " + subTotal);
		System.out.println("Total is " + Total);
		int intSubTotal = Integer.parseInt(subTotal.replaceAll("[^1-9]", ""));
		Assert.assertEquals(Total, intSubTotal);
		System.out.println("Total Mached");

//		17) Reduce the Quantity of Dal as 1 
		driver.findElementByXPath("(//div[@class=\"btn-counter row\"]/button)[3]").click();
//		18) Validate the Sub Total for the current items
		Thread.sleep(2000);
		subTotal = driver.findElementByXPath("//div[@class=\"row sub-cost ng-scope\"]/p/span/span").getText();
		Total = intRicePrice + intDalPrice;
		System.out.println("Sub total is " + subTotal);
		System.out.println("Total is " + Total);
		intSubTotal = Integer.parseInt(subTotal.replaceAll("[^1-9]", ""));
		Assert.assertEquals(Total, intSubTotal);
		System.out.println("Total Mached");

//		19) Close the Browser
		driver.close();
	}

}
