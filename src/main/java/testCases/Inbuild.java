package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Inbuild {

	public static void main(String[] args) {
//		WebDriver driver;
//		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
//		ChromeOptions opt = new ChromeOptions();
////		opt.setBinary("C:\\Users\\hp\\AppData\\Local\\Postman\\Postman.exe");
////		ChromeDriverService chromeservices = new ChromeDriverService.Builder().build();
////		driver = new ChromeDriver(chromeservices, opt);
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		String remotedebug = "localhost:9222";
//		opt.setExperimentalOption("debuggerAddress", remotedebug);
//		capabilities.setCapability(ChromeOptions.CAPABILITY, opt);
//		driver = new ChromeDriver(capabilities);
////		capabilities.setBrowserName("chrome");
////		
////		 = new ChromeDriver(capabilities);

		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver-v8.2.2-win32-x64/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();     
		options.setBinary("C:\\Users\\hp\\AppData\\Local\\Postman\\Postman.exe");
		options.addArguments("remote-debugging-port=12346"); 
		//used to run the Electron app in http://localhost:12345/ in browser to inspect elements
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("chromeOptions", options);
		RemoteWebDriver driver = null;
		
		driver = new ChromeDriver(options);
		
		
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElementByXPath("//div[@class=\"btn btn-primary\"]").click();

		///////////////////////////////////////////////
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\Desktop\\Selenium Learnings\\chromedriver-v8.2.3-win32-ia32\\chromedriver.exe");
//		WebDriver driver = null;
//		ChromeOptions opt = new ChromeOptions();
//		opt.setBinary("C:\\Users\\hp\\AppData\\Local\\Postman\\Postman.exe");
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("chromeOptions", opt);
//		capabilities.setBrowserName("chrome");
//		driver = new ChromeDriver(capabilities);
		
		///////////////////////////////////////////////////////
//		ChromeOptions opt = new ChromeOptions();
//		//Enter the path of your Electron app
//		opt.setBinary("C:\\Users\\hp\\AppData\\Local\\Postman\\Postman.exe");
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\Desktop\\Selenium Learnings\\chromedriver-v8.2.3-win32-ia32\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver(opt);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

}
