package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.Runner;

public class LensKart extends Runner {

	static Actions builder; 
	@Given("the user opens the browser and loads the given url")
	public void the_user_opens_the_browser_and_loads_the_given_url() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.lenskart.com/");
	}

	@Given("the user Mouseover on Contact Lenses")
	public void the_user_Mouseover_on_Contact_Lenses() {
		builder = new Actions(driver);
		builder.moveToElement(driver.findElementByLinkText("Contact Lenses")).perform();
	}

	@Given("the user Click on Monthly under Explore By Disposability")
	public void the_user_Click_on_Monthly_under_Explore_By_Disposability() {
		driver.findElementByLinkText("Monthly").click();
	}

	@Given("the user Select brand as Aqualens")
	public void the_user_Select_brand_as_Aqualens() {
		driver.findElementByXPath("(//span[contains(text(),\"Aqualens\")])[2]").click();
	}

	@Given("the user Click on the first product")
	public void the_user_Click_on_the_first_product() {
	    driver.findElementByXPath("//div[contains(@class,\"pos-rel\")]").click();
	}

	@Given("the user Click Buy Now")
	public void the_user_Click_Buy_Now() {
		builder.moveToElement(driver.findElementByXPath("//div[@class='nav-level-1 try-at-home']")).perform();
		builder.moveToElement(driver.findElementByXPath("//button[text()='BUY NOW']")).perform();
		driver.findElementByXPath("//button[text()='BUY NOW']").click();
	}

	@Given("the user Select No of boxes as {int} and Power as {int} for both eyes.")
	public void the_user_Select_No_of_boxes_as_and_Power_as_for_both_eyes(int int1, int int2) {
		Select box = new Select(driver.findElementByName("boxes"));
		box.selectByValue(int1 + "");
		driver.findElementByXPath("//span[text()='Please Select']").click();;
		
	}

	@Given("the user Type your name in User's name")
	public void the_user_Type_your_name_in_User_s_name() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user And click Save and continue")
	public void the_user_And_click_Save_and_continue() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the user Print total amount and click Proceed to Checkout")
	public void the_user_Print_total_amount_and_click_Proceed_to_Checkout() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
}
