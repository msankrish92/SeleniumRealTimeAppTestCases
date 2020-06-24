package testCases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HondaTwoWheeler {

	static RemoteWebDriver driver;

	public static void setBrowser(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("fireFox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver_64 bit.exe");
			driver = new FirefoxDriver();
		}else if(browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "./drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
	}

	public static void main(String[] args) throws InterruptedException {
//			1) Go to https://www.honda2wheelersindia.com/
		HondaTwoWheeler.setBrowser("IE");
		driver.manage().window().maximize();
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElementByClassName("close").click();

//			2) Click on scooters and click dio
		driver.findElementByLinkText("Scooter").click();
		driver.findElementByXPath("//div[@id='scooter']//div[@class='item']").click();

//			3) Click on Specifications and mouseover on Engine
		driver.findElementByLinkText("Specifications").click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.refreshed(ExpectedConditions.elementToBeClickable(driver.findElementByLinkText("ENGINE"))));

		Actions builder = new Actions(driver);
//		Thread.sleep(3000);
		builder.moveToElement(driver.findElementByLinkText("ENGINE")).perform();

//			4) Put all the details as key and value into Map
		List<WebElement> dioEngineSpecList = driver.findElementsByXPath("(//ul[@class='tab_content'])[2]/li/span");

		System.out.println(dioEngineSpecList.get(0).getText());
		Map<String, String> dioEngineKeyValue = new LinkedHashMap<String, String>();

		for (int i = 0; i < dioEngineSpecList.size() - 2; i = i + 2) {
			dioEngineKeyValue.put(dioEngineSpecList.get(i + 1).getText(), dioEngineSpecList.get(i + 2).getText());
		}
		System.out.println(dioEngineKeyValue);

//			5) Go to Scooters and click Activa 125
		driver.findElementByLinkText("Scooter").click();
		driver.findElementByXPath("(//div[@id='scooter']//div[@class='item'])[3]").click();
		driver.findElementByLinkText("Specifications").click();
//		Actions builder = new Actions(driver);
//		Thread.sleep(3000);
		wait.until(ExpectedConditions
				.refreshed(ExpectedConditions.elementToBeClickable(driver.findElementByLinkText("ENGINE"))));
		builder.moveToElement(driver.findElementByLinkText("ENGINE")).perform();

//			6) Put All its Engine Specification into another Map same as like dio
		List<WebElement> activaEngineSpecList = driver.findElementsByXPath("(//ul[@class='tab_content'])[2]/li/span");
		Map<String, String> activaEngineKeyValue = new LinkedHashMap<String, String>();

		for (int i = 0; i < activaEngineSpecList.size() - 2; i = i + 2) {
			activaEngineKeyValue.put(activaEngineSpecList.get(i + 1).getText(),
					activaEngineSpecList.get(i + 2).getText());
		}
		System.out.println(activaEngineKeyValue);

//			7) Compare Dio and Activa Maps and print the different values of the samekeys.
		Map<String, String> a1 = new LinkedHashMap<String, String>();
		Iterator<Map.Entry<String, String>> iter1 = dioEngineKeyValue.entrySet().iterator();
		Iterator<Map.Entry<String, String>> iter2 = activaEngineKeyValue.entrySet().iterator();
		while (iter1.hasNext()) {
			Entry<String, String> entry1 = iter1.next();
			Entry<String, String> entry2 = iter2.next();
			if (!entry1.getValue().equalsIgnoreCase(entry2.getValue())) {
				a1.put(entry1.getValue(), entry2.getValue());
			}
		}
		System.out.println(a1);

//			9) Click FAQ from Menu and Click dio under Browse By Product
		driver.findElementByLinkText("FAQ").click();
		driver.findElementByPartialLinkText("Dio").click();

//			10) Click  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit
		driver.findElementByPartialLinkText("Vehicle Price").click();
		driver.findElementById("submit6").click();

//			11) click the price link,  Go to the new Window and select the state, city
		driver.findElementByPartialLinkText("Click here to know").click();
		Thread.sleep(10000);
//		wait.until(ExpectedConditions.numberOfWindowsToBe(1));
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> orderedWindowHandles = new ArrayList<String>(windowHandles);
		driver.switchTo().window(orderedWindowHandles.get(1));
		Select state = new Select(driver.findElementById("StateID"));
		state.selectByValue("28");

		Select city = new Select(driver.findElementById("CityID"));
		city.selectByValue("1524");

		driver.findElementByXPath("//button[text()='Search']").click();

//			12) Print the price and model 
		List<WebElement> modelAndPrice = driver.findElementsByXPath("//table[@id='gvshow']//tbody//td");
		for (int i = 1; i < modelAndPrice.size(); i++) {
			System.out.println(modelAndPrice.get(i).getText());
		}

//			13) Click Product Enquiry and Fill all the * field except Mobile, check the terms and conditions box and click submit

		driver.findElementByPartialLinkText("Product Enquiry").click();
		Select modelID = new Select(driver.findElementById("ModelID"));
		modelID.selectByValue("3");

		Select stateId = new Select(driver.findElementById("StateID"));
		stateId.selectByValue("28");

		Select cityId = new Select(driver.findElementById("CityID"));
		cityId.selectByValue("1524");

		Select titleId = new Select(driver.findElementById("TitleID"));
		titleId.selectByValue("Mr.");

		driver.findElementById("Name").sendKeys("Sanjay");

		driver.findElementById("Email").sendKeys("msankrish92@gmail.com");

		driver.findElementById("TermsAndConditions").click();

		driver.findElementById("submit").click();

		String text = driver.findElementByXPath("//span[@class='text-danger field-validation-error']/span").getText();
		System.out.println(text);

//			14) Verify the error message under the mobile number field.
		Assert.assertEquals(text, "Please enter mobile no.");

//			15) Close the Browser
		driver.quit();

	}

}
