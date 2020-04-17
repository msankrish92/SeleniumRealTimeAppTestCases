package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class Day3MakeMyTrip {

	public static void main(String[] args) throws InterruptedException {

//		 1) Go to https://www.makemytrip.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

//		 2) Click Hotels
		driver.findElementByXPath("//span[text()=\"Hotels\"]").click();

//		 3) Enter city as Goa, and choose Goa, India
		driver.findElementByXPath("//input[@id=\"city\"]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//div[@id=\"react-autowhatever-1\"]/preceding-sibling::input").sendKeys("Goa");
		driver.findElementByXPath("//p[text()=\"Goa, India\"]").click();
		Thread.sleep(3000);

//		Enter Check in date as Next month 15th (May 15) and Check out as start date+5
		driver.findElementByXPath("(//div[text()=\"25\"])[2]").click();
		String startDate = driver.findElementByXPath("//div[contains(@class,\"start DayPicker-Day\")]").getText();
		int endDate = Integer.parseInt(startDate) + 5;
		driver.findElementByXPath("(//div[text()=" + endDate + "])[2]").click();

//		5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		driver.findElementById("guest").click();
		driver.findElementByXPath("//li[text()=\"2\"]").click();
		driver.findElementByXPath("(//li[text()=\"1\"])[2]").click();
		Select childAge = new Select(driver.findElementById("0"));
		childAge.selectByVisibleText("12");
		driver.findElementByXPath("//button[text()=\"APPLY\"]").click();

//		6) Click Search button
		driver.findElementById("hsw_search_button").click();

//		7) Select locality as Baga
		Thread.sleep(5000);
		driver.findElementByXPath("//a[@class=\"mapCont\"]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[@class=\"mapClose\"]").click();
		Thread.sleep(3000);
		driver.switchTo().frame("notification-frame-3175461c");
		driver.findElementByXPath("//div[@class=\"minimize tablecell\"]/i").click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		driver.findElementByXPath("//input[@id=\"mmLocality_checkbox_35\"]/following-sibling::label").click();

//		8) Select 5 start in Star Category under Select Filters
		Thread.sleep(3000);
		driver.findElementByXPath("//label[text()=\"5 Star\"]").click();

//		9) Click on the first resulting hotel and go to the new window
		Thread.sleep(2000);
		driver.findElementById("Listing_hotel_0").click();
		Set<String> windows = driver.getWindowHandles();
		List<String> orderedWindow = new ArrayList<String>(windows);
		driver.switchTo().window(orderedWindow.get(1));

//		10) Print the Hotel Name 
		Thread.sleep(2000);
		System.out.println(driver.findElementById("detpg_hotel_name").getText());

//		11) Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElementByXPath("//span[text()=\"MORE OPTIONS\"]").click();
		driver.findElementByXPath("//span[text()=\"SELECT\"]").click();
		driver.findElementByXPath("//span[@class=\"close\"]").click();

//		12) Click on BOOK THIS NOW
		driver.findElementByLinkText("BOOK THIS NOW").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//span[@class=\"close\"]").click();

//		13) Print the Total Payable amount
		String totalAmount = driver.findElementById("revpg_total_payable_amt").getText();
		String trimTotalAmount = totalAmount.replaceAll("[^0-9]", "");
		System.out.println(trimTotalAmount);

//		14) Close the browser
		driver.quit();
	}

}
