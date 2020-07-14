package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day14Zalando {
	public static void main(String[] args) throws InterruptedException {

//		1) Go to https://www.zalando.com/
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://www.zalando.com/");
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;

//		2) Get the Alert text and print it
		wait.until(ExpectedConditions.alertIsPresent());
		String alertText = driver.switchTo().alert().getText();
		System.out.println(alertText);
//		3) Close the Alert box and click on Zalando.uk
		driver.switchTo().alert().dismiss();
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();
//		4) Click Women--> Clothing and click Coat 
		driver.findElementByXPath("//span[text()='Women']").click();
		builder.moveToElement(driver.findElementByXPath("//span[text()='Clothing']")).click().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//a[text()='Coats'])[3]")));
		driver.findElementByXPath("(//a[text()='Coats'])[3]").click();
//		5) Choose Material as cotton (100%) and Length as thigh-length

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='uc-optin-timer-display']/..")));
		driver.findElementByXPath("//span[@id='uc-optin-timer-display']/..").click();
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("(//*[name()='svg' and @class='z-icon_svg']//*[local-name()='use'])[10]"))));
		builder.moveToElement(
				driver.findElementByXPath("(//*[name()='svg' and @class='z-icon_svg']//*[local-name()='use'])[10]"))
				.click().build().perform();

		driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		driver.findElementByXPath("//button[text()='Save']").click();

		for (int i = 0; i < 5; i++) {

			if (driver
					.findElementsByXPath(
							"//div[@class='cat_box-61TrD cat_brd-4-27afw cat_narrow-GAhUW cat_right-1U6ZW']")
					.size() != 0) {
				break;
			}
			try {
				builder.moveToElement(driver
						.findElementByXPath("(//*[name()='svg' and @class='z-icon_svg']//*[local-name()='use'])[13]"))
						.click().build().perform();

			} catch (Exception e) {

			}
		}

		driver.findElementByXPath("//span[text()='thigh-length']").click();
		driver.findElementByXPath("//button[text()='Save']").click();

//		6) Click on Q/S designed by MANTEL - Parka coat
		wait.until(ExpectedConditions.refreshed(
				ExpectedConditions.stalenessOf(driver.findElementByXPath("//div[text()='Q/S designed by']"))));
		wait.until(ExpectedConditions.stalenessOf(driver.findElementByXPath("//div[text()='Q/S designed by']")));
		driver.findElementByXPath("//div[text()='Q/S designed by']").click();

//		7) Check the availability for Color as Olive and Size as 'M'.
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//img[@alt='olive'])[2]")));
		driver.findElementByXPath("(//img[@alt='olive'])[2]").click();
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@id='picker-trigger']/span")));
		driver.findElementByXPath("//button[@id='picker-trigger']/span").click();
		driver.findElementByXPath("//span[text()='M']").click();

//		8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
		if (driver.findElementByXPath("//div[@class='Wqd6Qu']/h2").getText().equals("Out of stock")) {
			driver.findElementByXPath("(//img[@alt='navy'])[2]").click();
			wait.until(ExpectedConditions
					.visibilityOfAllElementsLocatedBy(By.xpath("//button[@id='picker-trigger']/span")));
			driver.findElementByXPath("//button[@id='picker-trigger']/span").click();
			driver.findElementByXPath("//span[text()='M']").click();
		}

//		9) Add to bag only if Standard Delivery is free
		if (driver.findElementByXPath("(//span[@class='AtOZbZ'])[2]").getText().equals("Free")) {
			driver.findElementByXPath("//span[text()='Add to bag']").click();
		}

//		10) Mouse over on Your Bag and Click on "Go to Bag"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Go to bag']")));
		driver.findElementByXPath("//div[text()='Go to bag']").click();

//		11) Capture the Estimated Deliver Date and print
		System.out.println(driver.findElementByXPath("//div[@data-id='delivery-estimation']/span").getText());

//		12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print

		builder.moveToElement(driver.findElementByXPath("//a[text()='Free delivery and returns*']")).perform();
		System.out.println(
				driver.findElementByXPath("//a[text()='Free delivery and returns*']/..").getAttribute("title"));
//		13) Click on FREE DELIVERY & RETURNS
		driver.findElementByXPath("//a[text()='Free delivery and returns*']").click();
//		14) Click on Start chat in the Start chat and go to the new window
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Start chat']")));
		driver.findElementByXPath("//span[text()='Start chat']").click();
		Set<String> windowSet = driver.getWindowHandles();
		List<String> windowList = new ArrayList<>(windowSet);
		driver.switchTo().window(windowList.get(1));
//		15) Enter you first name and a dummy email and click Start Chat
		driver.findElementById("prechat_customer_name_id").sendKeys("Sanjay");
		driver.findElementById("prechat_customer_email_id").sendKeys("msankrish@gmail.com");
		driver.findElementByXPath("//span[text()='Start Chat']").click();
//		16) Type Hi, click Send and print thr reply message and close the chat window.
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Please write your')]")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("liveAgentChatTextArea")));
		driver.findElementById("liveAgentChatTextArea").sendKeys("Hi");
		driver.findElementByXPath("//button[text()='Send']").click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='client']/following-sibling::span/span[2]")));
		System.out.println(
				driver.findElementByXPath("//span[@class='client']/following-sibling::span/span[2]").getText());

	}

}