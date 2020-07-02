package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.Runner;

public class Naukri extends Runner {

	@Given("the user load naukri url")
	public void the_user_load_naukri_url() {
		
		driver.get("https://www.naukri.com/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	static public Set<String> windowHandles;
	static List<String> orderedWindow; 
	@Given("the user goes the first opened window and get the company Name")
	public void the_user_goes_the_first_opened_window_and_get_the_company_Name() {
		windowHandles = driver.getWindowHandles();
		orderedWindow = new ArrayList<>(windowHandles);
		System.out.println(orderedWindow);
		driver.switchTo().window(orderedWindow.get(1));
		driver.manage().window().maximize();
		String firstCompany = driver.findElementByXPath("//body/a/img").getAttribute("alt");
		System.out.println(firstCompany);
		driver.close();
		
		
	}

//	@Given("the user closes that window")
//	public void the_user_closes_that_window() {
//		driver.switchTo().window(orderedWindow.get(2));
//		driver.manage().window().maximize();
//		String secondCompany = driver.findElementByXPath("//body/a/img").getAttribute("alt");
//		System.out.println(secondCompany);
//		driver.close();
////		Thread.sleep(500);
//		driver.switchTo().window(orderedWindow.get(0));driver.findElementById("block").click();
//	}

	@Given("the user goes the second opened window and get the company Name")
	public void the_user_goes_the_second_opened_window_and_get_the_company_Name() {
	}

	@When("the user uploads unsupported file as CV")
	public void the_user_uploads_unsupported_file_as_CV() {
		WebElement findElementById = driver.findElementById("file_upload");
//		findElementById.click();
	findElementById.sendKeys("C:\\Users\\hp\\Downloads\\317820-Mike-Tyson-Quote-Discipline-is-doing-what-you-hate-to-do-but.jpg");

	System.out.println(driver.findElementByXPath("//div[@class='error-header-desc error']").getText());

	}

	@Then("the user validates the error message")
	public void the_user_validates_the_error_message() {
	}


}
