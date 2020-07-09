package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrmCloud {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

//			1) Go to https://demo.1crmcloud.com/
		driver.get("https://demo.1crmcloud.com/");

//			2) Give username as admin and password as admin
		driver.findElementById("login_user").sendKeys("admin");
		driver.findElementById("login_pass").sendKeys("admin");

//			3) Choose theme as Claro Theme
		Select theme = new Select(driver.findElementById("login_theme"));
		theme.selectByVisibleText("Claro Theme");
		driver.findElementByXPath("//span[text()='Login']").click();

//			4) Click on Sales and Marketting 
		driver.findElementByXPath("//div[text()='Sales & Marketing']").click();

//			5) Click Create contact
		driver.findElementByXPath("//div[text()='Create Contact']").click();

//			6) Select Title and type First name, Last Name, Email and Phone Numbers
//		Thread.sleep(5000);
//		driver.findElement(By.xpath("Sales & Marketing"))
		WebDriverWait wait = new WebDriverWait(driver, 15);
//		driver.findElement(By.xpath("//li[@class='menubar-item current']//div"))
		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//li[@class='menubar-item current']//div"), "Sales & Marketing"));
//		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//li[@class='menubar-item current']//div")), "Sales & Marketing"));
//		wait.until(ExpectedConditions.textToBePresentInElementValue(driver.findElementByXPath("//div[text()='Sales & Marketing']/../.."), "menubar-item current"));

		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementById("DetailFormsalutation-input-label")).click().perform();

		driver.findElementByXPath("//div[text()='Mr.']").click();
		driver.findElementById("DetailFormfirst_name-input").sendKeys("Sanjay");
		driver.findElementById("DetailFormlast_name-input").sendKeys("M");
		driver.findElementById("DetailFormemail1-input").sendKeys("msankrish92@gmail.com");
		driver.findElementById("DetailFormphone_work-input").sendKeys("9940157064");
//			7) Select Lead Source as "Public Relations"
		driver.findElementById("DetailFormlead_source-input").click();
		driver.findElementByXPath("//div[text()='Public Relations']").click();

//			8) Select Business Roles as "Sales"
		driver.findElementById("DetailFormbusiness_role-input").click();
		driver.findElementByXPath("//div[text()='Sales']").click();

//			9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("Nookampalayam Road");
		driver.findElementById("DetailFormalt_address_city-input").sendKeys("chennai");
		driver.findElementById("DetailFormalt_address_state-input").sendKeys("TN");
		driver.findElementById("DetailFormalt_address_country-input").sendKeys("India");
		driver.findElementById("DetailFormalt_address_postalcode-input").sendKeys("600100");
		driver.findElementById("DetailForm_save2").click();

//			10) Mouse over on Today's Activities and click Meetings
		builder.moveToElement(driver.findElementById("grouptab-0")).perform();
		driver.findElementByXPath("//div[text()='Meetings']").click();

//			11) Click Create 
		builder.moveToElement(driver.findElementByXPath("//span[text()='Create']")).click().perform();

//			12) Type Subject as "Project Status" , Status as "Planned" 
		wait.until(ExpectedConditions
				.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id("DetailFormname-input"))));
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");

//			13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
//		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='DetailFormdate_start-input']/div/following-sibling::div"))));
		builder.moveToElement(
				driver.findElementByXPath("//div[@id='DetailFormdate_start-input']/div/following-sibling::div")).click()
				.perform();
		for (int i = 0; i < 5; i++) {

			if (driver
					.findElementsByXPath("//div[contains(@class,'current selected responsive')]/following-sibling::div")
					.size() == 0) {
				builder.moveToElement(
						driver.findElementByXPath("//div[@id='DetailFormdate_start-input']/div/following-sibling::div"))
						.click().perform();
				break;
			}
		}

		driver.findElementByXPath("//div[contains(@class,'current selected responsive')]/following-sibling::div")
				.click();
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input").clear();
		driver.findElementByXPath("//div[@id='DetailFormdate_start-calendar-text']//input").sendKeys("15");
		driver.findElementByXPath("(//div[@id='DetailFormdate_start-calendar-text']//following-sibling::div)[5]").click();
//			14) Click Add paricipants, add your created Contact name and click Save
//			15) Go to Sales and Marketting-->Contacts
//			16) search the lead Name and click the name from the result
//			17) Check weather the Meeting is assigned to the contact under Activities Section.
	}
}
