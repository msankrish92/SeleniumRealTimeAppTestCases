package testCases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ACME {
	
	public static void main(String[] args) {
		
//			1) Launch URL: https://acme-test.uipath.com/account/login
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64 bit.exe");
		RemoteWebDriver driver = new FirefoxDriver();
		driver.get("https://acme-test.uipath.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
//			2) Enter UserName (kumar.testleaf@gmail.com) and TAB
		driver.findElementById("email").sendKeys("kumar.testleaf@gmail.com");
		
//			3) Enter Password (leaf@12)
		driver.findElementById("password").sendKeys("leaf@12");
		
//			4) Click Login
		driver.findElementByXPath("//button[@type='submit']").click();
		
//			5) Mouse Over on Vendors
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("(//button[@type='button']/i)[5]")).perform();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		
//			6) Click Search Vendor
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Search for Vendor"))).click();
		
		
//			7) Enter Vendor Name (Blue Lagoon)
		driver.findElementById("vendorName").sendKeys("Blue Lagoon");
		
		
//			8) Click Search
		driver.findElementById("buttonSearch").click();
		
		
//			9) Find the Country Name based on the Vendor
		System.out.println(driver.findElementByXPath("(//th[text()='Country']/following::td)[5]").getText());
		
		
//			10) Click Log Out
		driver.findElementByXPath("//a[text()='Log Out']").click();
		
//			11) Close browser
		driver.quit();
		
		
		
	}
	

}
