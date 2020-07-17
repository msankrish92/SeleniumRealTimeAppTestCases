package testCases;

import java.util.List;
import java.util.Map;

import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZoomCar {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, 15);

//		1. Launch URL: https://www.zoomcar.com/chennai
		driver.get("https://www.zoomcar.com/chennai");

//			2. Click on the Start your wonderful journey link
		driver.findElementByXPath("//a[@title='Start your wonderful journey']").click();

//			3. In the Search page, Click on any pick up point under POPULAR PICK-UP
		driver.findElementByXPath("//div[@class='items']").click();

//			4. Click on the Next button
		driver.findElementByXPath("//button[text()='Next']").click();

//			5. Specify the Start Date as tomorrow Date
//		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'day picked')]/following-sibling::div[1]")));
		String startDate = driver.findElementByXPath("//div[contains(@class,'day picked')]/following-sibling::div[1]")
				.getText().replaceAll("[\\D]", "");
		System.out.println(startDate);
		driver.findElementByXPath("//div[contains(@class,'day picked')]/following-sibling::div[1]").click();

//			6. Click on the Next Button
		driver.findElementByXPath("//button[text()='Next']").click();

//			7. Confirm the Start Date and Click on the Done button
		driver.findElementByXPath("//button[text()='Done']").click();

//			8. In the result page, capture the number of results displayed.
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='car-listing']")));
		int noOfResultsDisplayed = driver.findElementsByXPath("//div[@class='car-listing']").size();
		System.out.println(noOfResultsDisplayed);

//			9. Find the highest priced car ride.

		List<WebElement> priceList = driver.findElementsByXPath("//div[@class='price']");
		Map<Integer, String> act = new TreeMap<>();
		priceList.stream().forEach(ele -> act.put(Integer.parseInt(ele.getText().replaceAll("[^0-9]", "")),
				ele.getText().replaceAll("[^0-9,]", "")));
		int max = act.entrySet().stream().map(ele -> ele.getKey()).max(Integer::compare).get();
		System.out.println(act.get(max));

//		List<Integer> intPriceList = priceList.stream().map(ele -> Integer.parseInt(ele.getText().replaceAll("[^0-9]", ""))).collect(Collectors.toList());
//		System.out.println(intPriceList);
//		Optional<Integer> max = intPriceList.stream().max(Integer::compare);
//		System.out.println(max.get());
//			10. Click on the Book Now button for it.
		driver.findElementByXPath("//div[contains(text(),'" + act.get(max) + "')]/following-sibling::button").click();

//			11. Close the Browser.
		driver.quit();

	}

}
