package testCases;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day17Azure {

	public static void main(String[] args) throws InterruptedException {

//		1) Go to https://azure.microsoft.com/en-in/

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver(ops);
		driver.manage().window().maximize();
		driver.get("https://azure.microsoft.com/en-in/");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions buider = new Actions(driver);

//		2) Click on Pricing
		driver.findElementByXPath("//a[@id='navigation-pricing']").click();

//		3) Click on Pricing Calculator
		driver.findElementByXPath("//a[contains(text(),'Pricing calculator')]").click();

//		4) Click on Containers
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Containers']")));
		driver.findElementByXPath("//button[text()='Containers']").click();

//		5) Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();

//		6) Click on Container Instance Added View
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='new-module-loc']")));
		driver.findElementByXPath("//a[@id='new-module-loc']").click();

//		7) Select Region as "South India"
		Select region = new Select(driver.findElementByXPath("//select[@name='region']"));
		region.selectByVisibleText("South India");

//		8) Set the Duration as 180000 seconds
		driver.findElementByXPath("//input[@name='seconds']").sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE);
		driver.findElementByXPath("//input[@name='seconds']").sendKeys("180000");

//		9) Select the Memory as 4GB
		Select memory = new Select(driver.findElementByXPath("//select[@name='memory']"));
		memory.selectByVisibleText("4 GB");

//		10) Enable SHOW DEV/TEST PRICING

		driver.findElementByXPath("//label[@for='devtest-toggler']").click();

//		11) Select Indian Rupee  as currency
		Select currency = new Select(driver.findElementByXPath("//select[@class='select currency-dropdown']"));
		currency.selectByValue("INR");

//		12) Print the Estimated monthly price
		String monthlyCost = driver.findElementByXPath("//span[text()='Monthly cost']/following-sibling::span/span")
				.getText().replaceAll("[^0-9.]", "");
		System.out.println("Monthly Cost: " + monthlyCost);

//		13) Click on Export to download the estimate as excel
		driver.findElementByXPath("//button[text()='Export']").click();

//		14) Verify the downloded file in the local folder
		Thread.sleep(5000);
		String downloadPath = "C:\\Users\\456875\\Downloads";
		String fileName = "ExportedEstimate.xlsx";

		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		boolean flag = false;
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName)) {
				flag = true;
			}
		}

		if (flag) {
			System.out.println("File Verification passed");
		} else {
			System.out.println("File Verification Failed");
		}

//		15) Navigate to Example Scenarios and Select CI/CD for Containers
		buider.moveToElement(driver.findElementByXPath("//a[text()='Example Scenarios']")).perform();

		driver.findElementByXPath("//a[text()='Example Scenarios']").click();
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();

//		16) Click Add to Estimate
		buider.moveToElement(driver.findElementByXPath("//button[text()='Add to estimate']")).perform();
		driver.findElementByXPath("//button[text()='Add to estimate']").click();

//		17) Change the Currency as Indian Rupee

		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//h4[text()='Loading Estimate']")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElementByXPath("//h4[text()='Loading Estimate']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='select currency-dropdown']")));
		Select currency2 = new Select(driver.findElementByXPath("//select[@class='select currency-dropdown']"));
		currency2.selectByValue("INR");

//		18) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//label[@for='devtest-toggler']").click();

//		19) Export the Estimate
		driver.findElementByXPath("//button[text()='Export']").click();

//		20) Verify the downloded file in the local folder
		String fileName2 = "ExportedEstimate (1).xlsx";

		flag = false;
		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName2)) {
				flag = true;
			}
		}

		if (flag) {
			System.out.println("File Verification passed");
		} else {
			System.out.println("File Verification Failed");
		}

	}

	
}

