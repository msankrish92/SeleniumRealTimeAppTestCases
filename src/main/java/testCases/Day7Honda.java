package bigBasket;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Day7Honda {

	public static void main(String[] args) throws InterruptedException {

//			1) Go to https://www.honda2wheelersindia.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedrivers.exe");
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.honda2wheelersindia.com/");
		driver.findElementByXPath("//button[@class=\"close\"]").click();

//			2) Click on scooters and click dio
		driver.findElementById("link_Scooter").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id=\"scooter\"]//div//div//div//a").click();

//			3) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()=\"Specifications\"]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//a[text()=\"ENGINE\"]").click();

//			4) Get Displacement value
		String dioDisplacement = driver.findElementByXPath("//span[text()=\"Displacement\"]/following-sibling::span")
				.getText();
		double intDioDisplacement = Double.parseDouble(dioDisplacement.replaceAll("[^0-9.]", ""));
		String dioName = driver.getTitle();
		String exactDioName = dioName.substring(17);
		System.out.println("Displacement of " + exactDioName + " " + intDioDisplacement);

//			5) Go to Scooters and click Activa 125
		driver.findElementById("link_Scooter").click();
		driver.findElementByXPath("(//div[@id=\"scooter\"]//div//div//div//a)[3]").click();

//			6) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()=\"Specifications\"]").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//a[text()=\"ENGINE\"]").click();

//			7) Get Displacement value
		String activaDisplacement = driver.findElementByXPath("//span[text()=\"Displacement\"]/following-sibling::span")
				.getText();
		double intActivaDisplacement = Double.parseDouble(activaDisplacement.replaceAll("[^0-9.]", ""));
		String activaName = driver.getTitle();
		String exactActivaName = activaName.substring(17);
		System.out.println("Displacement of " + exactActivaName + " " + intDioDisplacement);

//			8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		String BetterDisplacement;
		if (intActivaDisplacement > intDioDisplacement) {
			BetterDisplacement = exactActivaName;
			System.out.println("The Scooter that has better displacement is " + BetterDisplacement);
		} else {
			BetterDisplacement = exactDioName;
			System.out.println("The Scooter that has better displacement is " + BetterDisplacement);
		}

//			9) Click FAQ from Menu 
		driver.findElementByXPath("//a[text()=\"FAQ\"]").click();

//			10) Click Activa 125 BS-VI under Browse By Product
		driver.findElementByXPath("(//div[@id='style-3'])[2]//a[contains(text(),'125 BS-VI')]").click();

//			11) Click  Vehicle Price 
		driver.findElementByXPath("//li[@id='li6']/a").click();

//			12) Make sure Activa 125 BS-VI selected and click submit
		Select sel = new Select(driver.findElementById("ModelID6"));
		List<WebElement> allSelectedOptions = sel.getAllSelectedOptions();
		String selected = allSelectedOptions.get(0).getText();
		Assert.assertEquals(selected, "Activa 125 BS-VI");
		System.out.println(selected + " Selected");
		driver.findElementById("submit6").click();

//			13) click the price link
		driver.findElementByXPath("//a[contains(text(),'Click here to know the price')]").click();

//			14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> handles = driver.getWindowHandles();
		List<String> orderHandles = new ArrayList<String>(handles);
		driver.switchTo().window(orderHandles.get(1));
		Select state = new Select(driver.findElementById("StateID"));
		state.selectByVisibleText("Tamil Nadu");
		Thread.sleep(2000);
//			Actions Builder = new Actions(driver);
//			Builder.moveToElement(driver.findElementById("CityID")).click().perform();
		Select city = new Select(driver.findElementById("CityID"));
		city.selectByVisibleText("Chennai");

//			15) Click Search
		driver.findElementByXPath("//button[text()='Search']").click();

//			16) Print all the 3 models and their prices
		Thread.sleep(3000);
		List<WebElement> tableRow = driver.findElementsByXPath("//table[@id='gvshow']//tr");
		for (int i = 2; i < tableRow.size(); i++) {
			List<WebElement> tableColumn = tableRow.get(i).findElements(By.tagName("td"));
			for (int j = 0; j < tableColumn.size(); j++) {
				System.out.println(tableColumn.get(j).getText());
			}
		}

//			17) Close the Browser
		driver.quit();
	}
}
