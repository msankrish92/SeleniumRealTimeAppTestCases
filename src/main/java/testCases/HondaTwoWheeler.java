package testCases;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HondaTwoWheeler {

	static RemoteWebDriver driver;

	public static void setBrowser(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

	public static void main(String[] args) throws InterruptedException {
//			1) Go to https://www.honda2wheelersindia.com/
		HondaTwoWheeler.setBrowser("chrome");
		driver.manage().window().maximize();
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElementByClassName("close").click();

//			2) Click on scooters and click dio
		driver.findElementByLinkText("Scooter").click();
		driver.findElementByXPath("//div[@id='scooter']//div[@class='item']").click();

//			3) Click on Specifications and mouseover on Engine
		driver.findElementByLinkText("Specifications").click();
		Actions builder = new Actions(driver);
		Thread.sleep(3000);
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
		Thread.sleep(3000);
		builder.moveToElement(driver.findElementByLinkText("ENGINE")).perform();

//			6) Put All its Engine Specification into another Map same as like dio
		List<WebElement> activaEngineSpecList = driver.findElementsByXPath("(//ul[@class='tab_content'])[2]/li/span");
		Map<String, String> activaEngineKeyValue = new LinkedHashMap<String, String>();

		for (int i = 0; i < activaEngineSpecList.size() - 2; i = i + 2) {
			activaEngineKeyValue.put(activaEngineSpecList.get(i + 1).getText(), activaEngineSpecList.get(i + 2).getText());
		}
		System.out.println(activaEngineKeyValue);

//			7) Compare Dio and Activa Maps and print the different values of the samekeys.
		
		
		
//			9) Click FAQ from Menu and Click dio under Browse By Product
//			10) Click  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit
//			11) click the price link,  Go to the new Window and select the state, city
//			12) Print the price and model 
//			13) Click Product Enquiry and Fill all the * field except Mobile, check the terms and conditions box and click submit
//			14) Verify the error message under the mobile number field.
//			15) Close the Browser
	}

}
