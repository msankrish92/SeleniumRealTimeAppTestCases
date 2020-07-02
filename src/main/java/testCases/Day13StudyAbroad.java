package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day13StudyAbroad {
	public static void main(String[] args) throws InterruptedException {
//	1) Go to https://studyabroad.shiksha.com/
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.get("https://studyabroad.shiksha.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		JavascriptExecutor js = (JavascriptExecutor) driver;

//	2) Mouse over on Colleges and click MS in Computer Science &Engg under MS Colleges
		Actions builder = new Actions(driver);
		builder.moveToElement(
				driver.findElementByXPath("(//nav[@id='menu']//ul//label[contains(@class,\"fnt-wt melabel\")])[3]"))
				.perform();
		driver.findElementByXPath("//a[text()='MS in Computer Science &Engg']").click();

//	3) Select GRE under Exam Accepted and Score 300 & Below 
		driver.findElementByXPath("//p[text()='GRE']/preceding-sibling::span").click();
		wait.until(ExpectedConditions
				.refreshed(ExpectedConditions.stalenessOf(driver.findElementByClassName("score-select-field"))));
		Select score = new Select(driver.findElementByClassName("score-select-field"));
		score.selectByValue("GRE--300--4");

//	4) Max 10 Lakhs under 1st year Total fees, USA under countries

		wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.stalenessOf(driver.findElementByXPath("//input[@id='fee-0']/following-sibling::label/span"))));
		driver.findElementByXPath("//input[@id='fee-0']/following-sibling::label/span").click();

//	5) Select Sort By: Low to high 1st year total fees
		wait.until(
				ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElementById("categorySorter"))));
		Select sortBy = new Select(driver.findElementById("categorySorter"));
		sortBy.selectByValue("fees_ASC");

//	6) Click Add to compare of the College having least fees with Public University, Scholarship and Accomadation
		wait.until(ExpectedConditions.refreshed(ExpectedConditions
				.stalenessOf(driver.findElementByXPath("(//div[contains(@class,'uni-course-details flLt')])"))));

		List<WebElement> noOfColl = driver.findElementsByXPath("(//div[contains(@class,'uni-course-details flLt')])");

		List<WebElement> findElements = new ArrayList<WebElement>();
		for (int i = 1; i <= noOfColl.size(); i++) {

			String publicUniversity = driver
					.findElementByXPath(
							"((//div[contains(@class,'uni-course-details flLt')])[" + i + "]//div[3]//span)[1]")
					.getAttribute("class");
			String Scholarship = driver
					.findElementByXPath(
							"((//div[contains(@class,'uni-course-details flLt')])[" + i + "]//div[3]//span)[2]")
					.getAttribute("class");

			String Accommodation = driver
					.findElementByXPath(
							"((//div[contains(@class,'uni-course-details flLt')])[" + i + "]//div[3]//span)[3]")
					.getAttribute("class");

			if (driver
					.findElementByXPath(
							"((//div[contains(@class,'uni-course-details flLt')])[" + i + "]//div[3]//span)[1]")
					.getAttribute("class").equals("tick-mark")
					&& driver
							.findElementByXPath(
									"((//div[contains(@class,'uni-course-details flLt')])[" + i + "]//div[3]//span)[2]")
							.getAttribute("class").equals("tick-mark")
					&& driver
							.findElementByXPath(
									"((//div[contains(@class,'uni-course-details flLt')])[" + i + "]//div[3]//span)[3]")
							.getAttribute("class").equals("tick-mark")) {
				findElements.add(driver.findElement(
						By.xpath("(//strong[text()=' 1st Year Total Fees'])[" + i + "]//following-sibling::p")));

			}
		}

		TreeMap<String, String> rate = new TreeMap<String, String>();

		for (int i = 0; i < findElements.size(); i++) {
			rate.put(findElements.get(i).getText(), findElements.get(i).getText().replaceAll("[^0-9.]", ""));
		}

		driver.findElementByXPath(
				"//p[text()=\"" + rate.firstKey() + "\"]/parent::div/parent::div/parent::div/following-sibling::div[2]")
				.click();

//	7) Select the first college under Compare with similar colleges
		driver.findElementByXPath("(//a[text()='OK'])[2]").click();
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElementByXPath("//ul[@class='sticky-suggestion-list']/li/a")));
		driver.findElementByXPath("//ul[@class='sticky-suggestion-list']/li/a").click();
//	8) Click on Compare College>
		driver.findElementByXPath("//strong[text()='Compare Colleges >']").click();
//	9) Select When to Study as 2021
		driver.findElementByXPath("(//span[@class='common-sprite'])[2]").click();
//	10) Select Preferred Countries as USA
		driver.findElementByXPath("//div[@class='input']").click();
		driver.findElementByXPath("//input[contains(@id,'USA')]/following-sibling::label/span").click();
		driver.findElementByXPath("//a[@class='ok-btn']").click();
//	11) Select Level of Study as Masters
		driver.findElementByXPath("//label[contains(@for,\"Masters\")]/span").click();

//	12) Select Preferred Course as MS
		driver.findElementByXPath("(//div[@class='input'])[2]").click();
		driver.findElementByXPath("//li[text()='MS']").click();

//	13) Select Specialization as "Computer Science & Engineering"
		driver.findElementByXPath("(//div[@class='input'])[3]").click();
		driver.findElementByXPath("//li[text()='Computer Science & Engineering']").click();
//	14) Click on Sign Up
		driver.findElementById("signup").click();
//	15) Print all the warning messages displayed on the screen for missed
		List<WebElement> WarningMessages = driver.findElementsByXPath("//div[contains(text(),\"Please\")]");
		for (int i = 0; i < WarningMessages.size(); i++) {
			System.out.println(WarningMessages.get(i).getText());

		}
		driver.quit();
	}
}

