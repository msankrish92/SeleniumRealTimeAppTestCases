package testCases;

import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.Runner;

public class Shein extends Runner {
	@Given("open https:\\/\\/www.shein.in\\/")
	public void open_https_www_shein_in() {
		driver.get("https://www.shein.in/");
		driver.findElementByXPath("//div[@class='c-coupon-box']/i").click();
	}

	@Given("Mouseover on Clothing and click Jeans")
	public void mouseover_on_Clothing_and_click_Jeans() throws InterruptedException {
	 Actions builder = new Actions(driver);
	 builder.moveToElement(driver.findElementByLinkText("CLOTHING")).perform();
	 Thread.sleep(5000);
	 driver.findElementByXPath("//a[contains(text(),\"Jeans\")]").click();
	}

	@Given("Choose Black under Jeans product count")
	public void choose_Black_under_Jeans_product_count() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("check size as medium")
	public void check_size_as_medium() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("Click + in color")
	public void click_in_color() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("check whether the color is black")
	public void check_whether_the_color_is_black() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("Click first item to Add to Bag")
	public void click_first_item_to_Add_to_Bag() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("Click the size as M abd click Submit")
	public void click_the_size_as_M_abd_click_Submit() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("Click view Bag")
	public void click_view_Bag() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("Check the size is Medium or not.")
	public void check_the_size_is_Medium_or_not() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("Close the browser.")
	public void close_the_browser() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

}
