package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class Day1MyntraApp {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions option = new ChromeOptions();

		option.addArguments("--disable-notifications");
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver(option);

		driver.get("https://www.myntra.com/");

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		WebElement womenMouseOverElement = driver
				.findElementByXPath("(//div[@class=\"desktop-navLink\"]/a)[position()=2]");

		Actions builder = new Actions(driver);

		builder.moveToElement(womenMouseOverElement).perform();

		driver.findElementByXPath("//a[text()=\"Jackets & Coats\"]").click();

		String titleCount = driver.findElementByXPath("//span[@class=\"title-count\"]").getText();
		
		String countInString = titleCount.replaceAll("\\D", "");
		
		int intCount = Integer.parseInt(countInString);
		
		String jacketCount = driver.findElementByXPath("//span[@class=\"categories-num\"]").getText();
		
//		System.out.println(jacketCount);
		String stringJacketCount = jacketCount.replaceAll("\\D", "");
//		System.out.println(stringJacketCount);
		String coatCount = driver.findElementByXPath("(//span[@class=\"categories-num\"])[2]").getText();
		
		String stringCountCount = coatCount.replaceAll("\\D", "");
		
//		System.out.println(stringJacketCount);
		
		int intJacketCount = Integer.parseInt(stringJacketCount);
		
		int intCoatCount = Integer.parseInt(stringCountCount);
		
		int totalJacketCoatCount = intJacketCount + intCoatCount;
		
		Assert.assertEquals(intCount,totalJacketCoatCount);
		
		driver.findElementByXPath("(//span[@class=\"categories-num\"]/following-sibling::div)[2]").click();
		
		driver.findElementByXPath("//div[@class=\"brand-more\"]").click();
		
		driver.findElementByXPath("//input[@class=\"FilterDirectory-searchInput\"]").sendKeys("Mango");
		
		driver.findElementByXPath("//span[@class=\"FilterDirectory-count\"]/following-sibling::div").click();
		
		driver.findElementByXPath("//ul[@class=\"FilterDirectory-indices\"]/following-sibling::span").click();
		
		Thread.sleep(5000);
		
		List<WebElement> BrandList = new ArrayList<WebElement>();
		
		BrandList = driver.findElementsByXPath("//h3[@class=\"product-brand\"]");
		
		int totalBrandCount = BrandList.size();
		
		for (int i = 0; i < totalBrandCount; i++) {
			String brand = BrandList.get(i).getText();
			Assert.assertEquals(brand, "MANGO");
		}
		
		builder.moveToElement(driver.findElementByXPath("//div[@class=\"sort-sortBy\"]")).perform();
		
		driver.findElementByXPath("//label[text()=\"Better Discount\"]").click();
		
		Thread.sleep(5000);
		
		builder.moveToElement(driver.findElementByXPath("//h3[@class=\"product-brand\"]")).perform();
		
		driver.findElementByXPath("//div[@class=\"product-actions product-prelaunchActions\"]/span").click();
	}

}
