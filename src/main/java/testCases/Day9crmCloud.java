package bigBasket;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class Day9crmCloud {
	
//	@FindBy(how = How.ID,using = "login_user")
//	private static WebElement eleUserName;

	public static void main(String[] args) throws InterruptedException {
		
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
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
		Thread.sleep(3000);
		driver.findElementByXPath("(//div[text()='(none)'])[1]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("(//div[text()='Mr.'])").click();
		driver.findElementById("DetailFormfirst_name-input").sendKeys("sanjay");
		driver.findElementById("DetailFormlast_name-input").sendKeys("M");
		driver.findElementById("DetailFormemail1-input").sendKeys("msankrish92@gmail.com");
		driver.findElementById("DetailFormphone_work-input").sendKeys("9940157064");
		
		
//			7) Select Lead Source as "Public Relations"
		driver.findElementByXPath("//p[text()=\"Lead Source\"]/following-sibling::div/div/div").click();
		driver.findElementByXPath("//div[text()='Public Relations']").click();
		
//			8) Select Business Roles as "Sales"
		driver.findElementByXPath("//input[@name='business_role']/following-sibling::div").click();
		driver.findElementByXPath("//div[text()='Sales']").click();
		
//			9) Fill the Primary Address, City, State, Country and Postal Code and click Save
		driver.findElementById("DetailFormprimary_address_street-input").sendKeys("Plaza Pristine Acres");
		driver.findElementById("DetailFormprimary_address_city-input").sendKeys("chennai");
		driver.findElementByXPath("(//p[text()='State']/following-sibling::div/input[2])[1]").sendKeys("Tamil Nadu");
		driver.findElementById("DetailFormprimary_address_country-input").sendKeys("India");
		driver.findElementById("DetailFormprimary_address_postalcode-input").sendKeys("600100");
		driver.findElementById("DetailForm_save2-label").click();
		
//			10) Mouse over on Today's Activities and click Meetings
		Thread.sleep(10000);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElementByXPath("//div[@class=\"menu-label\"]")).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='menu-source-1-popup']/div/div[4]/div[2]").click();
		
//			11) Click Create 
		driver.findElementByXPath("//span[text()='Create']").click();
//			12) Type Subject as "Project Status" , Status as "Planned" 
		driver.findElementById("DetailFormname-input").sendKeys("Project Status");
		
//			13) Start Date & Time as tomorrow 3 pm and Duration as 1hr
//			14) Click Add paricipants, add your created Contact name and click Save
//			15) Go to Sales and Marketting-->Contacts
//			16) search the lead Name and click the name from the result
//			17) Check weather the Meeting is assigned to the contact under Activities Section.
	}

}
