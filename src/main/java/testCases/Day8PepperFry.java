package bigBasket;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class Day8PepperFry {

	public static void main(String[] args) throws InterruptedException, IOException {
//			1) Go to https://www.pepperfry.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.pepperfry.com/");

//			2) Mouseover on Furniture and click Office Chairs under Chairs
		driver.findElementByXPath("//div[@id=\"reg_login_box\"]//a").click();
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//div[@id=\"menu_wrapper\"]//a")).perform();
		driver.findElementByXPath("//a[text()='Office Chairs']").click();

//			3) click Executive Chairs
		driver.findElementByXPath("(//div[@class='cat-wrap-img'])[2]").click();

//			4) Change the minimum Height as 50 in under Dimensions
		driver.findElementByXPath("//input[@class='clipFilterDimensionHeightValue']").clear();
		driver.findElementByXPath("//input[@class='clipFilterDimensionHeightValue']").sendKeys("50", Keys.ENTER);

//			5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
		Thread.sleep(2000);
		driver.findElementByXPath("(//a[@id='clip_wishlist_'])[1]").click();

//			6) Mouseover on Homeware and Click Pressure Cookers under Cookware
		builder.moveToElement(driver.findElementByXPath("//a[text()=\"Homeware\"]")).perform();
		driver.findElementByXPath("//a[text()='Pressure Cookers']").click();

//			7) Select Prestige as Brand
		driver.findElementByXPath("//input[@id=\"brandsnamePrestige\"]/following-sibling::label").click();

//			8) Select Capacity as 1-3 Ltr
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@id=\"capacity_db1_Ltr_-_3_Ltr\"]/following-sibling::label").click();

//			9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
		Thread.sleep(2000);
		driver.findElementByXPath(
				"(//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr']/../following::div//a)[2]").click();

//			10) Verify the number of items in Wishlist
		Thread.sleep(2000);
		String count = driver.findElementByXPath("(//span[@class='count_alert'])[2]").getText();
		Assert.assertEquals(count, "2");

//			11) Navigate to Wishlist
		driver.findElementByXPath("//div[@class='wishlist_bar']/a").click();

//			12) Move Pressure Cooker only to Cart from Wishlist
		driver.findElementByXPath(
				"//a[text()='Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr By...']/following::div//a/i").click();

//			13) Check for the availability for Pincode 600128
		driver.findElementByXPath("//input[@class=\"srvc_pin_text\"]").sendKeys("600100");
		driver.findElementByXPath("//a[@class=\"check_available\"]").click();

//			14) Click Proceed to Pay Securely
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();

//			15 Click Proceed to Pay
		driver.findElementByXPath("//a[text()='PLACE ORDER']").click();

//			16) Capture the screenshot of the item under Order Item
		driver.findElementByXPath("//span[text()='ORDER SUMMARY']").click();
		Thread.sleep(2000);
		WebElement orderItemElement = driver.findElementByXPath("//li[@id='payment_cart_1676140']");
		File scr = driver.getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(scr);
		Point point = orderItemElement.getLocation();

		int eleWidth = orderItemElement.getSize().getWidth();
		int eleHeight = orderItemElement.getSize().getHeight();

		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		ImageIO.write(eleScreenshot, "png", scr);

		File screenshotLocation = new File("./screenshots/screenshot.png");
		FileUtils.copyFile(scr, screenshotLocation);

//			17) Close the browse
		driver.quit();
	}

}
