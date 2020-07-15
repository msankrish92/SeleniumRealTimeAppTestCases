package testCases;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AmazonJava8 {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--disable-notifications");
		RemoteWebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");

		List<WebElement> linkEle = driver.findElementsByTagName("a");
//		
//		linkEle.forEach(ele -> System.out.println(ele.getText()));
//		
//		System.out.println("=======================================");
//		
//		List<String> eleValue = linkEle.stream().filter(ele -> !ele.getText().equals("")).map(ele -> ele.getText()).collect(Collectors.toList());
//		
//		eleValue.forEach(ele -> System.out.println(ele));
//		
//		System.out.println("=======================================");
//		
//		String firstEle = linkEle.stream().filter(ele -> !ele.getText().equals("")).findFirst().get().getText();
//		
//		System.out.println(firstEle);
//		
//		System.out.println("=======================================");
//		
//		String anyEle = linkEle.stream().filter(ele -> !ele.getText().equals("")).findAny().get().getText();
//		
//		System.out.println(anyEle);
//		
//		System.out.println("=======================================");
//		
//		List<String> collect = linkEle.stream().filter(ele -> !ele.getText().equals("") && ele.getText().contains("Amazon")).map(ele -> ele.getText()).collect(Collectors.toList());
//
//		collect.forEach(ele -> System.out.println(ele));
//		
//		System.out.println("=======================================");

		List<String> noBlankList = linkEle.stream().filter(ele -> !ele.getText().isEmpty()).filter(ele -> !ele.getText().startsWith(" "))
				.map(ele -> ele.getText().trim()).collect(Collectors.toList());

		noBlankList.forEach(ele->System.out.println(ele));
		
		driver.quit();

	}

}
