package testCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

public class Test {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", "C:\\Users\\hp\\AppData\\Local\\Postman\\Postman.exe");
//		capabilities.setCapability("deviceName", "windows 10");
		WindowsDriver driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);
		builder.moveByOffset(91, 61).click();
//		driver.findElementByName("New").click();
//		driver.findElementByXPath("//div[@class=\"btn btn-primary\"]").click();
//		Thread.sleep(5000);
//		driver.findElementByName("Collection").click();
//		Thread.sleep(5000);
//		driver.findElementByName("Collection Name").sendKeys("RestLearning");
//		Thread.sleep(5000);
//		driver.findElementByName("Create").click();
		
	}
	
}
