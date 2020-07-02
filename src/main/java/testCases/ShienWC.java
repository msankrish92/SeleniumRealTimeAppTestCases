package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class ShienWC {


	public static void main(String[] args) throws InterruptedException {
		RemoteWebDriver driver;
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("https://www.shein.in/");
		driver.findElementByXPath("//div[@class='c-coupon-box']/i").click();
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByLinkText("CLOTHING")).perform();
		
		builder.moveToElement(driver.findElementByXPath("//a[contains(text(),\"Jeans\")]")).click().build().perform();
		
		
		driver.findElementByXPath("//a[text()='Black']").click();
		
		
		driver.findElementByXPath("//span[text()='Size']").click();
		driver.findElementByXPath("(//span[@class='attr-check-box'])[8]").click();
		
		String color = driver.findElementByXPath("//span[text()='Color']/../following-sibling::div//a//i").getCssValue("color");
		
		Assert.assertEquals(color, "rgba(102, 102, 102, 1)");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		builder.moveToElement(driver.findElementByXPath("//div[@class='c-goodsitem__ratiowrap']/a/img")).perform();
		builder.moveToElement(driver.findElementByXPath("//button[contains(text(),'Add to Bag')]")).click().build().perform();
		
		
		driver.findElementByXPath("(//input[@class='opt']/following-sibling::span)[2]").click();
		driver.findElementByXPath("//button[contains(text(),'Submit')]").click();
		
		driver.findElementByXPath("//a[text()='view bag']").click();
		
		String size = driver.findElementByXPath("//span[@class='gd-size']/em").getText();
		
		Assert.assertEquals(size, "M");
		
//		System.out.println(size);
//		driver.findElementByXPath("//button[contains(text(),'Add to Bag')]").click();
		
		driver.close();
		
	}

}
