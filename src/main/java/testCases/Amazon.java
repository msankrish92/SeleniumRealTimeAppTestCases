package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Amazon {

	public static void main(String[] args) throws InterruptedException {

//			1. Launch URL: https://www.amazon.in/
//		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
//		RemoteWebDriver driver = new ChromeDriver();

		System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
//		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
//		ieOptions.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
//		ieOptions.addCommandSwitches("-private");
		RemoteWebDriver driver = new InternetExplorerDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 15);

//			2. Type "oneplus 7 pro mobiles" in Search Box and Enter
		driver.findElementById("twotabsearchtextbox").sendKeys("oneplus 7 pro mobiles", Keys.ENTER);

//			3. Print the price of the first resulting mobile
		List<WebElement> priceList = driver.findElementsByXPath("//span[@class='a-price-whole']");

		String firstElePrice = priceList.stream().map(ele -> ele.getText()).findFirst().get();
		System.out.println(firstElePrice);

//			4. Click on the Mobile (First resulting) image
		List<WebElement> imaList = driver.findElementsByXPath("//span[@class='rush-component']/a/div/img");
		imaList.get(0).click();

//			5. Switch to the new window
		Set<String> windowSet = driver.getWindowHandles();
		List<String> windowList = new ArrayList<String>(windowSet);
		driver.switchTo().window(windowList.get(1));
		System.out.println(driver.getTitle());

//			6. Print the number of customer ratings
		String cusRateCount = driver.findElementById("acrCustomerReviewText").getText();
		System.out.println(cusRateCount);

//			7. Click 'Add to Cart'
		driver.findElementByXPath("//a[contains(@class,'nav-a nav-a-2 a-pop')]").click();
		driver.findElementByXPath("//div[@role='button']/preceding-sibling::div/input").sendKeys("603303");
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//span[text()='Apply']")).click().perform();
		wait.until(ExpectedConditions
				.refreshed(ExpectedConditions.stalenessOf(driver.findElementById("add-to-cart-button"))));
		driver.findElementById("add-to-cart-button").click();

//			8. Confirm "Added to Cart" text message appeared
		String verifyAddToCartMsg = new String();

		Thread.sleep(5000);

		try {
			if (driver.findElements(By.xpath("//h1[@class='a-size-medium a-text-bold']")) != null) {
				verifyAddToCartMsg = driver.findElement(By.xpath("//h1[@class='a-size-medium a-text-bold']")).getText();

			} else if (driver.findElementsByXPath("(//h4[@class='a-alert-heading'])[3]") != null) {
				verifyAddToCartMsg = driver.findElementByXPath("(//h4[@class='a-alert-heading'])[3]").getText();

			}
		} catch (Exception e) {
			System.out.println("Element not found");
		}

		System.out.println(verifyAddToCartMsg);
		Assert.assertEquals(verifyAddToCartMsg, "Added to Cart");
//		
//			9. Click to Proceed to Buy

		try {
			if (driver.findElementsById("hlb-ptc-btn-native") != null) {
				driver.findElementById("hlb-ptc-btn-native").click();
			} else if (driver.findElementsByXPath("//input[@class='a-button-input']") != null) {
				driver.findElementByXPath("//input[@class='a-button-input']").click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

//			10. Confirm the title is "Amazon Sign In"
		String title = driver.getTitle();
		Assert.assertEquals(title, "Amazon Sign In");

//			11. Click Continue (without entering mobile number / email)
		driver.findElementById("continue").click();

//			12. Verify the error message: Enter your email or mobile phone number

		String errorMsg = new String();

		try {
			if (driver.findElementsByXPath("//span[@class='a-list-item']") != null) {
				errorMsg = driver.findElementByXPath("//span[@class='a-list-item']").getText();
			} else if (driver.findElementsByXPath("(//div[@class='a-alert-content'])[2]") != null) {
				errorMsg = driver.findElementByXPath("(//div[@class='a-alert-content'])[2]").getText();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		Assert.assertEquals(errorMsg, "Enter your email or mobile phone number");

//			13. Close both browsers

		driver.quit();

	}

}
