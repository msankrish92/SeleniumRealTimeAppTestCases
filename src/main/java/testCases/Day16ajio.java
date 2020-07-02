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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Day16ajio {

	public static void main(String[] args) {
//			1) Go to //www.ajio.com/shop/sale

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://www.ajio.com/shop/sale");
		WebDriverWait wait = new WebDriverWait(driver, 15);

//			2) Enter Bags in the Search field and Select Bags in Women Handbags
		driver.findElementByXPath("//input[@placeholder='Search AJIO']").sendKeys("Bags");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Women Handbags']")));
		driver.findElementByXPath("//span[text()='Women Handbags']").click();

//			3) Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//div[@class='five-grid']").click();
		Select dropDown = new Select(driver.findElementByXPath("//div[@class='filter-dropdown']/select"));
		dropDown.selectByVisibleText("What's New");

//			4) Enter Price Range Min as 2500 and Max as 5000
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElementByXPath("//span[text()='price']").click();
		driver.findElementByXPath("//input[@id='minPrice']").sendKeys("2500");
		driver.findElementByXPath("//input[@id='maxPrice']").sendKeys("5000");
		driver.findElementByXPath("//input[@id='maxPrice']/following-sibling::button").click();

//			5) Click on the product "Puma Ferrari LS Shoulder Bag"
		wait.until(ExpectedConditions.textToBe(
				By.xpath("//div[@role='rowgroup']/div[2]//div//div/following-sibling::div/div[2]"),
				"Ferrari LS Shoulder Bag"));
		driver.findElementByXPath("//div[text()='Ferrari LS Shoulder Bag']").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));

//			6) Verify the Coupon code for the price above 2690 is applicable for your product, if applicable then get the Coupon Code and Calculate the discount price for the coupon
		String disAppPrice = driver.findElementByXPath("//div[@class='promo-desc']").getText();
		String disAppPriceSub = disAppPrice.replaceAll("[^0-9]", "");
		String disAppPriceSb2 = disAppPriceSub.substring(2);
		System.out.println("Discount only applicable for products above: " + disAppPriceSb2);
		int disAppPriceInt = Integer.parseInt(disAppPriceSb2);
		String actPrice = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		String actPricesub = actPrice.replaceAll("[^0-9]", "");
		System.out.println("Select product Price: " + actPricesub);
		int actPriceInt = Integer.parseInt(actPricesub);
		int discountedAmount = 0;
		if (actPriceInt > disAppPriceInt) {
			System.out.println("Epic coupon applicable");
			String afterDisPrice = driver.findElementByXPath("//div[@class='promo-discounted-price']/span").getText();
			String afterDisPriceSub = afterDisPrice.replaceAll("[^0-9]", "");
			int afterDisPriceInt = Integer.parseInt(afterDisPriceSub);

			discountedAmount = actPriceInt - afterDisPriceInt;

		}
		System.out.println("Calculated discount price: " + discountedAmount);

//			7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available

		driver.findElementByXPath("//span[contains(text(),\"Enter pin-code\")]").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='pincode']")));
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("560043");
		driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']").click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//ul[@class='edd-message-success-details']/li/span")));
		String expDel = driver.findElementByXPath("//ul[@class='edd-message-success-details']/li/span").getText();
		System.out.println("Expected Delivery: " + expDel);

//			8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
		driver.findElementByXPath("//div[@class='other-info-toggle']").click();
		String address = driver.findElementByXPath("(//span[@class='other-info'])[6]").getText();
		System.out.println("Address: " + address);

//			9) Click on ADD TO BAG and then GO TO BAG

		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='GO TO BAG']")));
		driver.findElementByXPath("//span[text()='GO TO BAG']").click();

//			10) Check the Order Total before apply coupon
		String orderValue = driver.findElementByXPath("//span[@class='price-value bold-font']").getText();
		String orderValueSub = orderValue.replaceAll("[^0-9.]", "");
		String orderValueSub2 = orderValueSub.substring(1);
		System.out.println("Order Value Before applying coupon: " + orderValueSub2);

//			11) Enter Coupon Code and Click Apply
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='couponCodeInput']")));
		driver.findElementByXPath("//input[@id='couponCodeInput']").sendKeys("EPIC");
		driver.findElementByXPath("//button[text()='Apply']").click();

//			12) Verify the Coupon Savings amount(round off if it in decimal) under Order Summary and the matches the amount calculated in Product details
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//span[@class='price-value discount-price'])[2]")));
		String CouponSavings = driver.findElementByXPath("(//span[@class='price-value discount-price'])[2]").getText();
		String CouponSavingsSub = CouponSavings.replaceAll("[^0-9.]", "");
		String CouponSavingsSub1 = CouponSavingsSub.substring(1);
		double CouponSavingsdouble = Double.parseDouble(CouponSavingsSub1);
		int roundCouponSavings = (int) Math.round(CouponSavingsdouble);
		Assert.assertEquals(roundCouponSavings, discountedAmount);
		System.out.println("Coupon Savings amount matched the amount calculated in Product details");

//			13) Click on Delete and Delete the item from Bag
		driver.findElementByXPath("//div[@class='delete-btn']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='DELETE']")));
		driver.findElementByXPath("//div[text()='DELETE']").click();
		wait.until(ExpectedConditions.textToBe(By.xpath("//p[@class='empty-msg']"), "Your Shopping Bag is Empty!!"));

//			14) Close all the browsers
		driver.quit();
	}

}

