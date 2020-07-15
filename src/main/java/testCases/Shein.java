package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLUListElement;

public class Shein {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		RemoteWebDriver driver = new ChromeDriver(options);
		

//	1) open https://www.shein.in/
		driver.get("https://www.shein.in/");
		driver.manage().window().maximize();
		driver.findElementByXPath("//i[@class='iconfont icon-arrow-left']").click();

//	2) Mouseover on Clothing and click Jeans
		driver.findElementByName("header-search").click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='jeans']")));
		driver.findElementByXPath("//a[@title='jeans']").click();

//	3) Choose Black under Jeans product count
		driver.findElementByXPath("//span[text()='Color']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Black']/following-sibling::i")));
		driver.findElementByXPath("//span[text()='Black']/following-sibling::i").click();
		
//	4) check size as medium
		driver.findElementByXPath("(//span[@class='attr-check-box'])[3]").click();
		
//	5) Click + in color
		String color = driver.findElementByXPath("//span[text()='Black']").getCssValue("color");
		System.out.println(color);
//	6) check whether the color is black
//	7) Click first item to Add to Bag 
//		Thread.sleep(5000);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("(//div[@class='c-goodsitem__ratiowrap']//img)[1]")).perform();
		builder.moveToElement(driver.findElementByXPath("//button[contains(text(),'Add to Bag')]")).click().perform();
//	8) Click the size as M abd click Submit
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class,'opt-real')])[2]")));
		driver.findElementByXPath("(//span[contains(@class,'opt-real')])[2]").click();
		driver.findElementByXPath("(//button[contains(text(),'Submit')])[1]").click();
//	9) Click view Bag 
		builder.moveToElement(driver.findElementByXPath("(//i[contains(@class,'iconfont-critical')])[2]")).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='view bag']")));
		driver.findElementByXPath("//a[text()='view bag']").click();
//	10) Check the size is Medium or not.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='gd-size']/em")));
		String size = driver.findElementByXPath("//span[@class='gd-size']/em").getText();
		System.out.println(size);
//	11) Close the browser.
		driver.close();
	}
}
