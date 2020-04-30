package bigBasket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day12carwala {
	public static void main(String[] args) throws InterruptedException {

//		1) Go to https://www.carwale.com/

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver(option);
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//		2) Click on Used
		driver.findElementByXPath("//li[contains(text(),\"Used\")]").click();

//		3) Select the City as Chennai

		driver.findElementById("usedCarsList").sendKeys("Chenna");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),\"i, Tamil Nadu\")]")));
		driver.findElementByXPath("//a[contains(text(),\"i, Tamil Nadu\")]").click();

		// 4) Select budget min (8L) and max(12L) and Click Search
		driver.findElementByXPath("//li[text()='8 Lakh']").click();
		driver.findElementByXPath("(//li[text()='12 Lakh'])[2]").click();
		driver.findElementByXPath("(//span[@class='welcome-box__search-icon'])[2]").click();

//		5) Select Cars with Photos under Only Show Cars With

		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElementByXPath("//span[text()='Cars with Photos']")));
		driver.findElementByXPath("//span[text()='Cars with Photos']").click();

//		6) Select Manufacturer as "Hyundai" --> Creta

		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[contains(text(),\"Hyundai\")]")));
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//span[contains(text(),\"Hyundai\")]")).perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		driver.findElementByXPath("//span[contains(text(),\"Hyundai\")]").click();
//		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Creta']")));
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Creta']")));

		new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class).until((WebDriver d) -> {
			d.findElement(By.xpath("//span[text()='Creta']")).click();
			return true;
		});

//		driver.findElementByXPath("//span[text()='Creta']").click();

//		7) Select Fuel Type as Petrol

		js.executeScript("arguments[0].scrollIntoView();",
				driver.findElementByXPath("//h3[contains(text(),\"Fuel\")]"));
		wait.until(
				ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//h3[contains(text(),\"Fuel\")]")));
		driver.findElementByXPath("//h3[contains(text(),\"Fuel\")]").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Petrol']")));
		driver.findElementByXPath("//span[text()='Petrol']").click();

//		8) Select Best Match as "KM: Low to High"
		Select id = new Select(driver.findElementById("sort"));
		id.selectByValue("1");

//		9) Validate the Cars are listed with KMs Low to High

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElementByXPath("//span[contains(@class,\"slkms vehicle-data__item\")]")));
		List<WebElement> kmEle = driver.findElementsByXPath("//span[contains(@class,\"slkms vehicle-data__item\")]");
		TreeMap<Integer, String> ordEle = new TreeMap<Integer, String>();
		for (int i = 0; i < kmEle.size(); i++) {
			ordEle.put(Integer.parseInt(kmEle.get(i).getText().replaceAll("[^0-9]", "")), kmEle.get(i).getText());
		}

//		10) Add the least KM ran car to Wishlist
		WebElement wishele = driver.findElementByXPath("//span[text()='" + ordEle.firstEntry().getValue()
				+ "']/ancestor::table/../../../../..//div//div//div/span/following-sibling::span");

		js.executeScript("arguments[0].click();", wishele);
//		11) Go to Wishlist and Click on More Details
		driver.findElementByXPath("//li[text()='& Compare']").click();
//		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[text()='More details »']")));
		driver.findElementByXPath("//a[text()='More details »']").click();
//		12) Print all the details under Overview in the Same way as displayed in application 
		Set<String> windowSet = driver.getWindowHandles();
		List<String> orderedSet = new ArrayList<String>(windowSet);

		driver.switchTo().window(orderedSet.get(1));

		List<WebElement> overviewLiEle = driver.findElementsByXPath("//div[@id='overview']//ul/li");

		Map<String, String> overview = new LinkedHashMap<String, String>();
		for (int i = 0; i < overviewLiEle.size(); i++) {
			overview.put(overviewLiEle.get(i).findElement(By.xpath("div[1]")).getText(),
					overviewLiEle.get(i).findElement(By.xpath("div[2]")).getText());
		}

		Iterator<Map.Entry<String, String>> itertor = overview.entrySet().iterator();

		while (itertor.hasNext()) {
			Map.Entry<String, String> entry = itertor.next();
			System.out.println(entry.getKey() + " = " + entry.getValue());

		}

//		13) Close the browser.

		driver.quit();
	}
}