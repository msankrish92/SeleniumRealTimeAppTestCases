package testCases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

public class Day5PostMan {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

//		1) Open Postman desktop application using Selenium
		DesktopOptions option = new DesktopOptions();
		 
		option.setApplicationPath("C:\\Users\\hp\\AppData\\Local\\Postman\\Postman.exe");
		 
		WiniumDriver driver = new WiniumDriver(new URL("http://localhost:9999"), option);
		

//		2) Create New Collection as RestLearning
		Thread.sleep(5000);
//		driver.findElementByClassName("Chrome_RenderWidgetHostHWND").click();
		driver.findElementByXPath("//div[@class=\"btn btn-primary\"]").click();
//		3) Check whether the collection already exist
//		4) If exist, delete the existing Collection
//		5) Create new Collection
	}

	
	
}
