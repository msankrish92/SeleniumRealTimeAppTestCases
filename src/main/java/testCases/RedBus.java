package testCases;


import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBus {

	public static void main(String[] args) {
//			1. Launch URL: https://www.redbus.in/
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64 bit.exe");

		FirefoxOptions opt = new FirefoxOptions();
		opt.addPreference("dom.webnotifications.enabled", false);
		
		RemoteWebDriver driver = new FirefoxDriver(opt);
		driver.get("https://www.redbus.in/");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
//			2. Enter 'from' place as 'Chennai'
		driver.findElementById("src").sendKeys("Chennai");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("selected")));
		driver.findElementByClassName("selected").click();
		
//			3. Enter 'to' place as 'Bangalore'
		driver.findElementById("dest").sendKeys("Bangalore");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("selected")));
		driver.findElementByClassName("selected").click();
		
		
//			4. Pick onward date as today
		driver.findElementByXPath("//td[@class='current day']").click();
		
//			5. Click on 'Search Buses'
		driver.findElementById("search_btn").click();
		
//			6. Check 'After 6 pm' under 'DEPARTURE TIME'
//			7. Check 'Sleeper' under 'BUS TYPE'
//			8. Click on 'Seats Available' to sort
//			9. Get the number of seats available in the (extract the # alone) first result
//			10. Click on 'VIEW SEATS'
//			11. Take a screenshot of the Seat Map for the highest seats available bus
	}
	
}
