package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckBoxJava8 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		RemoteWebDriver driver = new ChromeDriver();
		
		driver.get("http://www.leafground.com/pages/checkbox.html");
		
		List<WebElement> checkBoxes = driver.findElementsByXPath("//input[@type='checkbox']");
		
		checkBoxes.forEach(ele -> ele.click());
		
		
	}
}
