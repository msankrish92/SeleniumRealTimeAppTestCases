package testCases;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.Headers;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WindowsAuthentication {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
//		RemoteWebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://the-internet.herokuapp.com/");
//		driver.findElementByXPath("//a[text()='Basic Auth']").click();
////		driver.navigate().to("https://admin:admin@the-internet.herokuapp.com/digest_auth");
//		
//		
//		String username = "admin"; 
//		String password = "admin";
////		ChromeDevToolsService devToolsService = DevToolsService.getDevToolsService(driver);
//
//		DevTools devTools = driver.getD
//		devTools.createSession();
		
//		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();	
				
		String username = "admin"; // authentication username
		String password = "admin"; // authentication password
		
		// Get the devtools from the running driver and create a session
		DevTools devTools = driver.getDevTools();		
		devTools.createSession(); 
		
		// Enable the Network domain of devtools
		devTools.send(Network.enable(Optional.of(100000), Optional.of(100000), Optional.of(100000)));
		String auth = username +":"+ password;
		
		// Encoding the username and password using Base64 (java.util)
		String encodeToString = Base64.getEncoder().encodeToString(auth.getBytes());
		
		// Pass the network header -> Authorization : Basic <encoded String>
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Authorization", "Basic "+encodeToString);				
		devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
		
		System.out.println(headers);
		// Load the url
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		
		String text = driver.findElementByXPath("//p").getText();
		System.out.println(text);
		
		
	}

}
